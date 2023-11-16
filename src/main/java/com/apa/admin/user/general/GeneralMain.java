package com.apa.admin.user.general;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.AdminBlackDTO;
import com.apa.model.AdminReportDTO;
import com.apa.model.AdminUserDTO;
import com.apa.repository.AdminUserDAO;


@WebServlet("/admin/user/general/generalmain.do")
public class GeneralMain extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// list.java
		

		AdminUserDAO dao = new AdminUserDAO();
		
		ArrayList<AdminUserDTO> userList = dao.userList();

		// System.out.println(userList);

		req.setAttribute("userList", userList);
		
		
		ArrayList<AdminUserDTO> blackList = dao.blackList();
		
		//1.5 데이터 가공
		for (AdminUserDTO dto : blackList) {
			
			//가입일 날짜 자르기
			String blacklistDate = dto.getBlacklistDate();			
			dto.setBlacklistDate(blacklistDate.substring(0, 10));
		}
		
		// System.out.println(blackList);
		
		req.setAttribute("blackList", blackList);
		
		
		ArrayList<AdminUserDTO> reporList = dao.reporList();
		
		//1.5 데이터 가공
		for (AdminUserDTO dto : reporList) {
			
			//가입일 날짜 자르기
			String regdate = dto.getRegdate();			
			dto.setRegdate(regdate.substring(0, 10));
		}
		
		// System.out.println(reporList);
		
		req.setAttribute("reporList", reporList);

		 


		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/user/general/generalmain.jsp");
		dispatcher.forward(req, resp);
	}
}
