package com.apa.user.treatment;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.repository.UserDAO;
/**
 * 서블릿 입니다.
 * 
 * 회원 마이페이지 -> 진료 정보 -> 제증명 서류 요청
 * 
 */
@WebServlet("/user/treatment/request.do")
public class Request extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");
		
		req.setAttribute("seq", seq);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/treatment/request.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String seq = req.getParameter("seq");
		String content = req.getParameter("contentDocument");
		
		UserDAO dao = new UserDAO();
		
		int result = dao.insertDocument(seq, content);
		
		if (result == 1) {
			
			resp.setContentType("text/html; charset=UTF-8"); 
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('정상적으로 신청되었습니다.');window.close();</script>");
			writer.close();			
			
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
		}
		
	}
	
}
