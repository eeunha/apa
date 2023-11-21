package com.apa.user.storage.community;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.CommunityStorageCommentDTO;
import com.apa.model.CommunityStorageViewDTO;
import com.apa.repository.UserDAO;
/**
 * 서블릿 입니다.
 * 
 * 회원 마이페이지 -> 보관함 -> 커뮤니티 보관함 글 상세보기
 * 
 */
@WebServlet("/user/storage/community/view.do")
public class View extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");
				
		UserDAO dao = new UserDAO();
		
		CommunityStorageViewDTO cdto = dao.getCommunityStorageView(seq);
		ArrayList<CommunityStorageCommentDTO> cclist = dao.getCommunityStorageComment(seq); 
		
		req.setAttribute("cdto", cdto);
		req.setAttribute("cclist", cclist);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/storage/community/view.jsp");
		dispatcher.forward(req, resp);
	}
}
