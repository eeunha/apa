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

@WebServlet("/community/comment.do")
public class Comment extends HttpServlet {
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