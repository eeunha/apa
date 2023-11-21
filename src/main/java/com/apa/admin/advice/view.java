package com.apa.admin.advice;

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
 * 이 서블릿은 특정 게시물을 상세히 보여주는 기능을 제공합니다.
 */
@WebServlet("/admin/advice/view.do")
public class view extends HttpServlet {
	  /**
     * HTTP GET 요청을 처리하고 특정 게시물을 상세히 보여줍니다.
     *
     * @param req  클라이언트로부터의 HTTP 요청 객체
     * @param resp 서블릿이 클라이언트로 응답을 보낼 때 사용하는 HTTP 응답 객체
     * @throws ServletException 서블릿에서 발생한 일반적인 예외
     * @throws IOException      입출력 작업 중 발생한 예외
     */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		 // 요청 파라미터에서 필요한 정보 추출
		String seq = req.getParameter("seq");
		String search = req.getParameter("search");
		String column = req.getParameter("column");
		String word = req.getParameter("word");
		
		// DAO를 사용하여 특정 게시물 정보를 가져옴
		adminAdviceDAO dao = new adminAdviceDAO();

		adminAdviceDTO dto = dao.get(seq);

	
		// 요청 속성(attribute) 설정
		req.setAttribute("dto", dto);
		req.setAttribute("seq", seq);
		
		// JSP로 포워딩
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/advice/view.jsp");
		dispatcher.forward(req, resp);
	}
}
