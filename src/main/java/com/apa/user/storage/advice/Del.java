package com.apa.user.storage.advice;

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
 * 회원 마이페이지 -> 보관함 -> 의학 상담 글 삭제
 * 
 */
@WebServlet("/user/storage/advice/del.do")
public class Del extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/storage/advice/del.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//1.
		String qseq = req.getParameter("qseq");
		String aseq = req.getParameter("aseq");
		String bseq = req.getParameter("bseq");
		
		System.out.println(qseq);
		System.out.println(aseq);
		System.out.println(bseq);
		
		//2.
		UserDAO dao = new UserDAO();
		
		int result = 0;
		
		if (qseq != null && aseq != null && bseq == null) {

			dao.deleteStorageAdvice(aseq); //보관함의 모든 질문 삭제
			dao.deleteAdviceAnswer(qseq); //질문의 답변 삭제
			
			result = dao.deleteAdvice(qseq); //질문 삭제
			
		} else {
			
			result = dao.deleteAdviceBox(bseq);
			
		}
		
		
		
		
		//3.
		JSONObject obj = new JSONObject();
		obj.put("result", result);
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = resp.getWriter();
		writer.print(obj.toString());
		writer.close();		
		
	}
}
