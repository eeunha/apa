package com.apa.admin.company.after;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.AdminAfterDTO;
import com.apa.repository.AdminAfterDAO;


@WebServlet("/admin/company/after/list.do")
public class List extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//After > list.java
		
		AdminAfterDAO dao =  new AdminAfterDAO();
		
		ArrayList<AdminAfterDTO> list = dao.list();
		
		for (AdminAfterDTO dto : list) {
			
			//가입일 날짜 자르기
			String regdate = dto.getRegdate();			
			dto.setRegdate(regdate.substring(0, 10));
			
		}
		
		req.setAttribute("list", list);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/company/after/list.jsp");
		dispatcher.forward(req, resp);
	}
}
