package com.apa.user.treatment;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.MediHistoryViewDTO;
import com.apa.repository.UserDAO;
/**
 * 서블릿 입니다.
 * 
 * 회원 마이페이지 -> 진료 정보 -> 진료 내역 상세보기
 * 
 */
@WebServlet("/user/treatment/view.do")
public class View extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");
		String cseq = req.getParameter("cseq");
		String name = "";
				
		UserDAO dao = new UserDAO();
		
		MediHistoryViewDTO dto = dao.getMediHistoryView(seq);
		
		if (!cseq.equals("-1")) {
			name = dao.getChildName(cseq);
		} else {
			name = "X";
		}
		
		req.setAttribute("dto", dto);
		req.setAttribute("name", name);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/treatment/view.jsp");
		dispatcher.forward(req, resp);
	}
}
