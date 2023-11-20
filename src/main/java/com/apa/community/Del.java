package com.apa.community;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/community/del.do")
public class Del extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 if(Auth.check(req,resp)) { return; }
		
		//1. 
		String seq = req.getParameter("seq");
		
		//2.
		req.setAttribute("seq", seq);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/community/del.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//DelOk.java 역할
		//1. 데이터 가져오기(seq)
		//2. DB 작업 > delete
		//3. 피드백
		
		//1.
		String seq = req.getParameter("seq");
		
		//2.
		communityDAO dao = new communityDAO();
		
		dao.delCommentAll(seq);
		
		int result = dao.del(seq);
		
		//3.
		if (result == 1) {
			
			resp.sendRedirect("/apa/community/list.do");
					
		} else {
			System.out.println(seq);
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
		}
	
		
		
		
	}
}

