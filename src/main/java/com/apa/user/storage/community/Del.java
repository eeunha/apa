package com.apa.user.storage.community;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.apa.repository.UserDAO;
/**
 * 서블릿 입니다.
 * 
 * 회원 마이페이지 -> 보관함 -> 커뮤니티 보관함 글 삭제
 * 
 */
@WebServlet("/user/storage/community/del.do")
public class Del extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/storage/community/del.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String cseq = req.getParameter("cseq");
		
		UserDAO dao = new UserDAO();
	
		dao.deleteComment(cseq);
		
		int result = dao.deleteCommunity(cseq);
		
		JSONObject obj = new JSONObject();
		obj.put("result", result);
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = resp.getWriter();
		writer.print(obj.toString());
		writer.close();
		
	}
}
