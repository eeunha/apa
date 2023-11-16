package com.apa.community;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/community/add.do")
public class Add extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/community/add.jsp");
		dispatcher.forward(req, resp);
	}
	
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//AddOk.java
		
		//1. 데이터 가져오기
		//2. DB 작업 > insert
		//3. 피드백
		
		HttpSession session = req.getSession(); //아이디를 여기서 꺼냄
		req.setCharacterEncoding("utf-8");
		
		//1.
		//req.setCharacterEncoding("UTF-8");
		
		String subject = req.getParameter("subject");
		String content = req.getParameter("content");
		
		//2.
		communityDAO dao = new communityDAO();
		
		communityDTO dto = new communityDTO();
		dto.setSubject(subject);
		dto.setContent(content);
		dto.setUserSeq(session.getAttribute("seq").toString());
		
		int result = dao.add(dto);
		
		//3.
		if (result == 1) {
			
			resp.sendRedirect("/apa/community/list.do");
			
		} else {
			
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
		}
	}
}
