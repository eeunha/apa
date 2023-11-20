package com.apa.user.storage.advice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.MediCounselQuestionViewDTO;
import com.apa.repository.UserDAO;
/**
 * 서블릿 입니다.
 * 
 * 회원 마이페이지 -> 보관함 -> 의학 상담 글 상세 보기
 * 
 */
@WebServlet("/user/storage/advice/view.do")
public class View extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");
		
		UserDAO dao = new UserDAO();
		
		MediCounselQuestionViewDTO dto = dao.getMediCounselQuestionView(seq);
		
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/storage/advice/view.jsp");
		dispatcher.forward(req, resp);
	}
}
