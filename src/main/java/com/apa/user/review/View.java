package com.apa.user.review;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.ReviewDetailViewDTO;
import com.apa.model.TagViewDTO;
import com.apa.repository.UserDAO;

@WebServlet("/user/review/view.do")
public class View extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");
		
		UserDAO dao = new UserDAO();
		
		ReviewDetailViewDTO dto = dao.getReviewDetailView(seq);
		ArrayList<TagViewDTO> tlist = dao.getTagView(seq);
		
		req.setAttribute("dto", dto);
		req.setAttribute("tlist", tlist);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/review/view.jsp");
		dispatcher.forward(req, resp);
	}
}

