package com.apa.community;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

@WebServlet("/community/editcomment.do")
public class EditComment extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//EditComment.java
		
		String content = req.getParameter("content");
		String seq = req.getParameter("seq");
		
		communityDAO dao = new communityDAO();
		
		CommentDTO dto = new CommentDTO();
		dto.setCommentContent(content);
		dto.setCommentSeq(seq);
		
		int result = dao.editComment(dto);
		
		resp.setContentType("application/json");
		
		JSONObject obj = new JSONObject();
		obj.put("result", result);
		
		PrintWriter writer = resp.getWriter();
		writer.write(obj.toString());
		writer.close();		
		
		
	}

}
