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

import com.apa.magazine.MagazineDAO;
import com.apa.magazine.MagazineDTO;

@WebServlet("/magazine/add.do")
public class Add extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//magazine add.java
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/magazine/add.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//AddOk.java 역할
		
		//1. 데이터 가져오기
		//2. DB 작업 > insert
		//3. 피드백
		
		HttpSession session = req.getSession();		
		
		//1.
		req.setCharacterEncoding("UTF-8");
		
		String magazineTitle = req.getParameter("magazineTitle"); 
		String magazineSubTitle = req.getParameter("magazineSubTitle"); 
		String magazineContent = req.getParameter("magazineContent"); 
	
		//2.
		MagazineDAO dao = new MagazineDAO();
		
		MagazineDTO dto = new MagazineDTO();
		dto.setMagazineTitle(magazineTitle);
		dto.setMagazineSubTitle(magazineSubTitle);
		dto.setMagazineContent(magazineContent);
		

		int result = dao.add(dto);

		//3.
		if (result == 1) {
				
			resp.sendRedirect("/apa/magazine/list.do");
			
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
		}
		
		
	}
}
