package com.apa.magazine;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apa.magazine.MagazineDTO;
import com.apa.magazine.MagazineDAO;

/**
 * @author 안대명
 * 
 * Magazine 수정 기능을 담당하는 서블릿 클래스입니다.
 */
@WebServlet("/magazine/edit.do")
public class Edit extends HttpServlet {
	
    /**
     * GET 요청을 처리합니다.
     * 수정할 Magazine 정보를 가져와 수정 화면을 표시합니다.
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException Servlet 예외
     * @throws IOException      입출력 예외
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. 데이터 가져오기(seq)
		String magazineSeq = req.getParameter("magazineSeq");
				
		// 2. DB 작업 > select
		MagazineDAO dao = new MagazineDAO();
		MagazineDTO dto = dao.get(magazineSeq);
				
		String magazineTitle = dto.getMagazineTitle();
		// Replace double quotes with HTML entity to handle in HTML context
		magazineTitle = magazineTitle.replace("\"", "&quot;");
		dto.setMagazineTitle(magazineTitle);
				
		// 3. 결과를 JSP 호출하기 위해 설정
		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/magazine/edit.jsp");
		dispatcher.forward(req, resp);
		
	}
	
    /**
     * POST 요청을 처리합니다.
     * 수정된 Magazine 정보를 받아와 DB에 업데이트하고 결과에 따라 작업을 처리합니다.
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException Servlet 예외
     * @throws IOException      입출력 예외
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		
		// 1. 수정할 Magazine 정보 가져오기
		String magazineTitle = req.getParameter("magazineTitle");
		String magazineSubtitle = req.getParameter("magazineSubTitle");
		String magazineContent = req.getParameter("magazineContent");
		String magazineSeq = req.getParameter("magazineSeq"); 
				   
		// 2. DB 작업 > update
		MagazineDAO dao = new MagazineDAO();
		MagazineDTO dto = new MagazineDTO(); 
		dto.setMagazineTitle(magazineTitle);
		dto.setMagazineSubTitle(magazineSubtitle);
		dto.setMagazineContent(magazineContent);
		dto.setMagazineSeq(magazineSeq);
		
		int result = dao.edit(dto);
		
		// 3. 피드백 처리
		if (result == 1) {
			resp.sendRedirect("/apa/magazine/view.do?magazineSeq=" + magazineSeq);
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
		}
	}
}