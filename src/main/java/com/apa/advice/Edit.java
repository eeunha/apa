package com.apa.advice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 최진희
 * 이 서블릿은 상담 글을 수정하는 페이지로 이동하는 기능을 제공합니다.
 */
@WebServlet("/apa/advice/edit.do")
public class Edit extends HttpServlet {
	 /**
     * HTTP GET 요청을 처리하고 상담 글 수정 페이지로 이동합니다.
     *
     * @param req  클라이언트로부터의 HTTP 요청 객체
     * @param resp 서블릿이 클라이언트로 응답을 보낼 때 사용하는 HTTP 응답 객체
     * @throws ServletException 서블릿에서 발생한 일반적인 예외
     * @throws IOException      입출력 작업 중 발생한 예외
     */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
				//1.
				String seq = req.getParameter("seq");
				
				//2.
				adviceListDAO dao = new adviceListDAO();
				
				//수정할 번호를 내가 알고 있으니까 제목과 내용이 뭐였는지 알려줌
				adviceListDTO dto = dao.get(seq);
				
				//수정하기 제목에서 생기는 스타일태그 오류 수정하기
			
				
				//3. JSP에게 데이터가 넘어감
				req.setAttribute("dto", dto);
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/advice/edit.jsp");
		dispatcher.forward(req, resp);
	}
}

