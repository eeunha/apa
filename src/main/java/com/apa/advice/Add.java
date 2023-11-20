package com.apa.advice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/advice/add.do")
public class Add extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/advice/add.jsp");
		dispatcher.forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(); //아이디를 여기서 꺼냄
		System.out.println(session.getAttribute("seq").toString());
		
		req.setCharacterEncoding("UTF-8");
		
		String subject = req.getParameter("subject");
		String content = req.getParameter("content");
		String department = req.getParameter("department");
		
		//2.
		adviceListDAO dao = new adviceListDAO();
		
		adviceListDTO dto = new adviceListDTO();
		
		dto.setAdviceTitle(subject);
		dto.setAdviceContent(content);
		dto.setDepartSeq(department);
		dto.setUserSeq(session.getAttribute("seq").toString());
		
		int result = dao.add(dto);
				
		//3.
		if (result == 1) {
			
			resp.sendRedirect("/apa/advice/list.do?page=1");
			
		} else {
			
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
		}
		
	}
}

