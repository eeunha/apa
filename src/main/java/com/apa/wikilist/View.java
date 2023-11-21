package com.apa.wikilist;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apa.wikilist.WikilistDAO;
import com.apa.wikilist.WikilistDTO;

/**
 * @author 안대명
 * ㄴ
 * 위키 정보를 조회하는 서블릿 클래스입니다.
 */

@WebServlet("/wikilist/view.do")
public class View extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//wikilist/view.java
		HttpSession session = req.getSession();
		
		/**
		 * 1.데이터 불러오기
		 */
		String wikiSeq = req.getParameter("wikiSeq");

		String search = req.getParameter("search");
		String column = req.getParameter("column");
		String word = req.getParameter("word");
		
		/**
		 * 2.DB작업 수행
		 */
		WikilistDAO dao = new WikilistDAO();
		
		if (session.getAttribute("read") != null
				   && session.getAttribute("read").toString().equals("n")) {
				//2.3 조회수 증가
				//dao.updateWikiReadcount(wikiSeq);
				
				session.setAttribute("read", "y");
			}
		
		WikilistDTO dto = dao.get(wikiSeq);
		
		/**
		 * 2.5 데이터 조작
		 */

		//태그 비활성화
		//- <div>   ->   &lt;div&gt;
		String diseaseCause  = dto.getDiseaseCause();
		String wikiSymptom = dto.getWikiSymptom();
		String diagnosis = dto.getDiagnosis();
		String care = dto.getCare();
		
		diseaseCause = diseaseCause.replace("<", "&lt;");
		diseaseCause = diseaseCause.replace(">", "&gt;");
		wikiSymptom = wikiSymptom.replace("<", "&lt;");
		wikiSymptom = wikiSymptom.replace(">", "&gt;");
		diagnosis = diagnosis.replace("<", "&lt;");
		diagnosis = diagnosis.replace(">", "&gt;");
		care = care.replace("<", "&lt;");
		care = care.replace(">", "&gt;");
				 
		//개행 문자 처리 MagazineContent = MagazineContent.replace("\r\n", "<br>");
				 
		diseaseCause = diseaseCause.replace("\r\n", "<br>");
		wikiSymptom = wikiSymptom.replace("\r\n", "<br>");
		diagnosis = diagnosis.replace("\r\n", "<br>");
		care = care.replace("\r\n", "<br>");
					
		dto.setDiseaseCause(diseaseCause);
		dto.setWikiSymptom(wikiSymptom);
		dto.setDiagnosis(diagnosis);
		dto.setCare(care);

		String diseaseName = dto.getDiseaseName();
					
		diseaseName = diseaseName.replace("<", "&lt;");
		diseaseName = diseaseName.replace(">", "&gt;");

		dto.setDiseaseName(diseaseName);
		
				
		//내용으로 검색 > 검색어 강조!!
		if ((search != null && column != null) && search.equals("y") &&
		 column.equals("content")) {
		 
		 //이제 넷플릭스 구독을 해지합니다. //이제
		 //<span style="background-color:gold;color:tomato;">넷플릭스</span> 구독을 해지합니다.
		 dto.setDiseaseExplanation(dto.getDiseaseExplanation().replace(word,
		 "<span style='background-color:gold;color:tomato;'>" + word + "</span>")); }
		
		

		/**
		 * 3.피드백
		 */
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/wikilist/view.jsp");
		dispatcher.forward(req, resp);
	}

}
