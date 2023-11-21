package com.apa.user.review;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.ReviewInsertInfoDTO;
import com.apa.repository.UserDAO;
/**
 * 서블릿 입니다.
 * 
 * 회원 마이페이지 -> 진료 내역 -> 리뷰 작성
 * 
 */
@WebServlet("/user/review/add.do")
public class Add extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String seq = req.getParameter("seq");
		
		UserDAO dao = new UserDAO();
		
		ReviewInsertInfoDTO dto = dao.getReviewInsertInfo(seq);
		
		req.setAttribute("seq", seq);
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/review/add.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		String seq = req.getParameter("seq");
		String content = req.getParameter("content");
		String input = req.getParameter("tag-input");
		
		String tag[] = input.split(",");
		
		UserDAO dao = new UserDAO();
		
		int result = dao.insertReview(seq, content);
		
		String rseq = dao.selectLastReview();
		
		System.out.println(tag[0]);
		
		if (tag.length > 0 && !tag[0].isEmpty()) {
			for (int i=0; i<tag.length; i++) {
				result = dao.insertTag(rseq, tag[i]);			
			}			
		}
		
		if (result == 1) {
			
			resp.setContentType("text/html; charset=UTF-8"); 
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('정상적으로 작성되었습니다.');window.close();</script>");
			writer.close();			
			
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
		}
		
	}
}
