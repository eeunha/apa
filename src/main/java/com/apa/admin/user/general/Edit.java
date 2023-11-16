package com.apa.admin.user.general;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apa.model.AdminUserDTO;
import com.apa.repository.AdminUserDAO;

@WebServlet("/admin/user/general/edit.do")
public class Edit extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1.
		String userSeq = req.getParameter("userSeq");
		
		//2.
		AdminUserDAO dao = new AdminUserDAO();
		
		AdminUserDTO dto = dao.detail(userSeq);
		
		//3.
		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/user/general/edit.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//EditOk.java 역할
		
		//1. 데이터 가져오기
		//2. DB 작업 > update
		//3. 피드백
		
		//HttpSession session = req.getSession();		
		
		req.setCharacterEncoding("UTF-8");
		
		//1.		
		String userName = req.getParameter("userName");
		String userPw = req.getParameter("userPw");
		String isUserUnregister = req.getParameter("isUserUnregister");
		String userSeq = req.getParameter("userSeq"); //수정할 사람
		
		//2.
		AdminUserDAO dao = new AdminUserDAO();
		
		AdminUserDTO dto = new AdminUserDTO();
		
		dto.setUserName(userName);
		dto.setUserPw(userPw);
		dto.setIsUserUnregister(isUserUnregister);
		//dto.setId(session.getAttribute("id").toString());
		dto.setUserSeq(userSeq);
		
		
		System.out.println(dto);
		
		int result = dao.edit(dto);
		
		//3.
		if (result == 1) {
				
			resp.sendRedirect("/apa/admin/user/general/view.do?userSeq=" + userSeq);
			
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
		}
		
	}
}

