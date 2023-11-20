package com.apa.user.storage.community;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.CommunityStorageViewDTO;
import com.apa.repository.UserDAO;
/**
 * 서블릿 입니다.
 * 
 * 회원 마이페이지 -> 보관함 -> 커뮤니티 보관함 글 수정
 * 
 */
@WebServlet("/user/storage/community/edit.do")
public class Edit extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/storage/community/edit.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		String seq = req.getParameter("seq");
		String useq = req.getParameter("useq");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
	
		UserDAO dao = new UserDAO();
		
		CommunityStorageViewDTO dto = new CommunityStorageViewDTO();
		dto.setCommunitySeq(seq);
		dto.setCommunityTitle(title);
		dto.setCommunityContent(content);
		
		int result = dao.updateCommunity(dto);
		
		if (result == 1) {
			
			resp.setContentType("text/html; charset=UTF-8"); 
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('정상적으로 수정하였습니다.');window.close();</script>");
			writer.close();			
			
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
		}
		
	}
	
}
