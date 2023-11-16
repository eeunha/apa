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

@WebServlet("/magazine/edit.do")
public class Edit extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Edit.java
		//- view.do > 수정하기 버튼 클릭 > edit.do?seq=5
		//1. 데이터 가져오기(seq)
		//2. DB 작업 > select
		//3. 결과 + JSP 호출하기
				
		//1.
		String magazineSeq = req.getParameter("magazineSeq");
				
		//2.
		MagazineDAO dao = new MagazineDAO();
				
		MagazineDTO dto = dao.get(magazineSeq);
				
		String MagazineTitle = dto.getMagazineTitle();
				
		// " > \"
		MagazineTitle = MagazineTitle.replace("\"", "&qout;");
		dto.setMagazineTitle(MagazineTitle);
				
		//3.
		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/magazine/edit.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		
		//1.
		String magazineTitle = req.getParameter("magazineTitle");
		String magazineSubtitle = req.getParameter("magazineSubTitle");
		String magazineContent = req.getParameter("magazineContent");
		String magazineSeq = req.getParameter("magazineSeq"); 
				   
		
		//2.
		MagazineDAO dao = new MagazineDAO();
		
		MagazineDTO dto = new MagazineDTO(); 
		dto.setMagazineTitle(magazineTitle);
		dto.setMagazineSubTitle(magazineSubtitle);
		dto.setMagazineContent(magazineContent);
		dto.setMagazineSeq(magazineSeq);
		
		int result = dao.edit(dto);
		
		//3.
		if (result == 1) {

			resp.sendRedirect("/apa/magazine/view.do?magazineSeq=" + magazineSeq);
			
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
		}
		
	}
	
	
}







