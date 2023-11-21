package com.apa.community;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author 최진희
 * 이 서블릿은 커뮤니티 댓글에 관련된 요청을 처리합니다.
 */
@WebServlet("/community/comment.do")
public class Comment extends HttpServlet {
	/**
     * HTTP GET 요청을 처리하고 특정 커뮤니티 글에 대한 댓글 목록을 반환합니다.
     *
     * @param req  클라이언트로부터의 HTTP 요청 객체
     * @param resp 서블릿이 클라이언트로 응답을 보낼 때 사용하는 HTTP 응답 객체
     * @throws ServletException 서블릿에서 발생한 일반적인 예외
     * @throws IOException      입출력 작업 중 발생한 예외
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String communitySeq = req.getParameter("communitySeq");
		
		communityDAO dao = new communityDAO();
		
		ArrayList<CommentDTO> clist = dao.listComment(communitySeq);
		
		JSONArray arr = new JSONArray(); //= ArrayList
		
		for (CommentDTO dto : clist) {
				
			//CommentDTO 1개 > JSONObject 1개
			JSONObject obj = new JSONObject();
				
			obj.put("seq", dto.getCommentSeq());
			obj.put("content", dto.getCommentContent());
			obj.put("id", dto.getCommentId());
			obj.put("regdate", dto.getCommentRegdate());
			obj.put("communitySeq", dto.getCommunitySeq());
			
				
			arr.add(obj);
				
		}//for
			
			
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
			
		PrintWriter writer = resp.getWriter();
		writer.write(arr.toString());//댓글 목록
		writer.close();		

	
	}
	 /**
     * HTTP POST 요청을 처리하고 커뮤니티 글에 댓글을 추가합니다.
     *
     * @param req  클라이언트로부터의 HTTP 요청 객체
     * @param resp 서블릿이 클라이언트로 응답을 보낼 때 사용하는 HTTP 응답 객체
     * @throws ServletException 서블릿에서 발생한 일반적인 예외
     * @throws IOException      입출력 작업 중 발생한 예외
     */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		String content = req.getParameter("content");
		String seq = req.getParameter("seq");
		
		
		
		String id = session.getAttribute("seq").toString();

		
		communityDAO dao = new communityDAO();
		
		CommentDTO dto = new CommentDTO();
		dto.setCommentContent(content);
		dto.setCommunitySeq(seq);
		dto.setUserSeq(id);
		
		int result = dao.addComment(dto);
		
		resp.setContentType("application/json");
		
		JSONObject obj = new JSONObject();
		obj.put("result", result);
			
		PrintWriter writer = resp.getWriter();
		writer.write(obj.toString());
		writer.close();		
		
		
	}

}