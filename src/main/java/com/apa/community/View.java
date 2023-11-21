package com.apa.community;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 최진희
 * 커뮤니티 게시물을 상세히 보여주는 서블릿입니다.
 * "/community/view.do" 경로로의 GET 요청을 처리합니다.
 */
@WebServlet("/community/view.do")
public class View extends HttpServlet {
	/**
     * 커뮤니티 게시물을 상세히 보여주는 GET 요청을 처리합니다.
     *
     * @param req  요청 정보를 포함하는 {@code HttpServletRequest} 객체
     * @param resp 응답 정보를 포함하는 {@code HttpServletResponse} 객체
     * @throws ServletException 서블릿 관련 오류가 발생한 경우
     * @throws IOException      I/O 오류가 발생한 경우
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		String seq = req.getParameter("seq");
		String search = req.getParameter("search");
		String column = req.getParameter("column");
		String word = req.getParameter("word");
		

		communityDAO dao = new communityDAO();

		communityDTO dto = dao.get(seq);

		 String content = dto.getContent();
		 content = content.replace("<", "&lt");
		 content = content.replace(">", "&gt");
		 
		  // 개행문자 처리 
		 content = content.replace("\r\n", "<br>");
		   
		  
		 dto.setContent(content);
		
		String subject = dto.getSubject();
		subject = subject.replace("<", "&lt");
		subject = subject.replace(">", "&gt");

		dto.setSubject(subject);
		// 3. 데이터 담기
		req.setAttribute("dto", dto);
		req.setAttribute("seq", seq);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/community/view.jsp");
		dispatcher.forward(req, resp);
	}
}
