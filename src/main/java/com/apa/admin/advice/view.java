package com.apa.admin.advice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/admin/advice/view.do")
public class view extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		String seq = req.getParameter("seq");
		String search = req.getParameter("search");
		String column = req.getParameter("column");
		String word = req.getParameter("word");
		

		adminAdviceDAO dao = new adminAdviceDAO();

		adminAdviceDTO dto = dao.get(seq);

	
	
		req.setAttribute("dto", dto);
		req.setAttribute("seq", seq);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/advice/view.jsp");
		dispatcher.forward(req, resp);
	}
}
