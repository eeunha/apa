package com.apa.user.review;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.ReviewListDTO;
import com.apa.model.UserDTO;
import com.apa.repository.UserDAO;
/**
 * 서블릿 입니다.
 * 
 * 회원 마이페이지 -> 리뷰 관리 -> 작성 리뷰 목록
 * 
 */
@WebServlet("/user/review/list.do")
public class List extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");
				
		UserDAO dao = new UserDAO();
		
		UserDTO dto = dao.get(seq);
		
		ArrayList<ReviewListDTO> rlist = dao.getReviewList(seq);
		
		for (ReviewListDTO rdto : rlist) {
			
			String subject = rdto.getReviewContent();
			
			if (subject.length() > 14) {
				subject = subject.substring(0, 14) + "...";
			}
			
			subject = subject.replace("<", "&lt;");
			subject = subject.replace(">", "&gt;");
			
			rdto.setReviewContent(subject);
		}
		
		req.setAttribute("dto", dto);
		req.setAttribute("rlist", rlist);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/review/list.jsp");
		dispatcher.forward(req, resp);
	}
}
