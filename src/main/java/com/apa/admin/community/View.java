package com.apa.admin.community;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apa.community.communityDAO;
import com.apa.community.communityDTO;

/**
 * @author 최진희
 * 이 서블릿은 관리자 페이지에서 커뮤니티 게시물을 상세히 보여주는 기능을 제공합니다.
 */
@WebServlet("/admin/community/view.do")
public class View extends HttpServlet {
    /**
     * HTTP GET 요청을 처리하고 특정 커뮤니티 게시물을 상세히 보여줍니다.
     *
     * @param req  클라이언트로부터의 HTTP 요청 객체
     * @param resp 서블릿이 클라이언트로 응답을 보낼 때 사용하는 HTTP 응답 객체
     * @throws ServletException 서블릿에서 발생한 일반적인 예외
     * @throws IOException      입출력 작업 중 발생한 예외
     */	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		String seq = req.getParameter("seq");
		String search = req.getParameter("search");
		String column = req.getParameter("column");
		String word = req.getParameter("word");
		

		adminCommunityDAO dao = new adminCommunityDAO();

		adminCommunityDTO dto = dao.get(seq);

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

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/community/view.jsp");
		dispatcher.forward(req, resp);
	}
}
