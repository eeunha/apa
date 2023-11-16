package com.apa.admin.user.general;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.AdminUserDTO;
import com.apa.repository.AdminUserDAO;

@WebServlet("/admin/user/general/view.do")
public class View extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		String userSeq = req.getParameter("userSeq");
		
		AdminUserDAO dao = new AdminUserDAO();
		
		AdminUserDTO dto  = dao.detail(userSeq);
		
		//System.out.println(dto);
		
		req.setAttribute("dto", dto);
		
		/*
		//생년월일 날짜 자르기
		String birthdate = dto.getBirthDate();			
		dto.setBirthDate(birthdate.substring(0, 10));
		
		
		//가입일 날짜 자르기
		String joindate = dto.getJoinDate();			
		dto.setJoinDate(joindate.substring(0, 10));
		*/
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/user/general/view.jsp");
		dispatcher.forward(req, resp);
	}
}
