package com.apa.admin.community;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 최진희
 * 이 서블릿은 관리자 페이지에서 커뮤니티 게시물을 삭제하는 기능을 제공합니다.
 */
@WebServlet("/admin/community/del.do")
public class Del extends HttpServlet {
	 /**
     * HTTP GET 요청을 처리하고 커뮤니티 게시물 삭제 페이지로 이동합니다.
     *
     * @param req  클라이언트로부터의 HTTP 요청 객체
     * @param resp 서블릿이 클라이언트로 응답을 보낼 때 사용하는 HTTP 응답 객체
     * @throws ServletException 서블릿에서 발생한 일반적인 예외
     * @throws IOException      입출력 작업 중 발생한 예외
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/* if(Auth.check(req,resp)) { return;} */
		
		//1. 
		String seq = req.getParameter("seq");
		String subject = req.getParameter("subject");
		
		//2.
		req.setAttribute("seq", seq);
		req.setAttribute("subject", subject);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/community/del.jsp");
		dispatcher.forward(req, resp);
	}
	
	 /**
     * HTTP POST 요청을 처리하고 커뮤니티 게시물을 삭제합니다.
     *
     * @param req  클라이언트로부터의 HTTP 요청 객체
     * @param resp 서블릿이 클라이언트로 응답을 보낼 때 사용하는 HTTP 응답 객체
     * @throws ServletException 서블릿에서 발생한 일반적인 예외
     * @throws IOException      입출력 작업 중 발생한 예외
     */
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//DelOk.java 역할
		//1. 데이터 가져오기(seq)
		//2. DB 작업 > delete
		//3. 피드백
		
		//1.
		String seq = req.getParameter("seq");
		
		//2.
		adminCommunityDAO dao = new adminCommunityDAO();
		
		dao.delCommentAll(seq);
		
		int result = dao.del(seq);
		
		//3.
		if (result == 1) {
			
			resp.sendRedirect("/apa/admin/community/list.do");
					
		} else {
			System.out.println(seq);
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
		}
	
		
		
		
	}
}
