package com.apa.community;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * @author 최진희
 * 댓글을 삭제하는 서블릿입니다.
 */
@WebServlet("/community/delcomment.do")
public class DelComment extends HttpServlet {
    /**
     * HTTP POST 요청을 처리하고 커뮤니티 댓글을 삭제합니다.
     *
     * @param req  클라이언트로부터의 HTTP 요청 객체
     * @param resp 서블릿이 클라이언트로 응답을 보낼 때 사용하는 HTTP 응답 객체
     * @throws ServletException 서블릿에서 발생한 일반적인 예외
     * @throws IOException      입출력 작업 중 발생한 예외
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//DelComment.java

		String seq = req.getParameter("seq");
		
		communityDAO dao = new communityDAO();
		
		int result = dao.delComment(seq);
		
		resp.setContentType("application/json");
		
		JSONObject obj = new JSONObject();
		obj.put("result", result);
		
		PrintWriter writer = resp.getWriter();
		writer.write(obj.toString());
		writer.close();		
		
	}	

}
