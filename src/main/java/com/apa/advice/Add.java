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

/**
 * @author 최진희
 * 이 서블릿은 상담 글을 추가하는 기능을 제공합니다.
 */
@WebServlet("/advice/add.do")
public class Add extends HttpServlet {
    /**
     * HTTP GET 요청을 처리하고 상담 글 추가 페이지로 이동합니다.
     *
     * @param req  클라이언트로부터의 HTTP 요청 객체
     * @param resp 서블릿이 클라이언트로 응답을 보낼 때 사용하는 HTTP 응답 객체
     * @throws ServletException 서블릿에서 발생한 일반적인 예외
     * @throws IOException      입출력 작업 중 발생한 예외
     */	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/advice/add.jsp");
		dispatcher.forward(req, resp);
	}

    /**
     * HTTP POST 요청을 처리하고 상담 글을 추가합니다.
     *
     * @param req  클라이언트로부터의 HTTP 요청 객체
     * @param resp 서블릿이 클라이언트로 응답을 보낼 때 사용하는 HTTP 응답 객체
     * @throws ServletException 서블릿에서 발생한 일반적인 예외
     * @throws IOException      입출력 작업 중 발생한 예외
     */
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

