package com.apa.magazine;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.magazine.MagazineDTO;
import com.apa.magazine.MagazineDAO;

@WebServlet("/magazine/del.do")
public class Del extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//magazine > del.java
		//1.
		String magazineSeq = req.getParameter("magazineSeq");
				
		//2.
		req.setAttribute("magazineSeq", magazineSeq);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/magazine/del.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//DelOk.java 역할
		//1. 데이터 가져오기(seq)
		//2. DB 작업 > delete
		//3. 피드백
		
		//1.
		String magazineSeq = req.getParameter("magazineSeq");
		
		//2.
		MagazineDAO dao = new MagazineDAO();

		int result = dao.del(magazineSeq); //댓글 존재 > 문제 발생
		
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
