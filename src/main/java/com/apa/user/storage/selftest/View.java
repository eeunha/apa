package com.apa.user.storage.selftest;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.MediTestSaveViewDTO;
import com.apa.repository.UserDAO;
/**
 * 서블릿 입니다.
 * 
 * 회원 마이페이지 -> 보관함 -> 의학 테스트 상세보기
 * 
 */
@WebServlet("/user/storage/selftest/view.do")
public class View extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");
		
		UserDAO dao = new UserDAO();
		
		MediTestSaveViewDTO dto = dao.getMediTestSaveView(seq);
		
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/storage/self-test/view.jsp");
		dispatcher.forward(req, resp);
	}
}
