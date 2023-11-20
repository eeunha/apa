package com.apa.admin.user.hospital;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.repository.AdminHospitalDAO;


@WebServlet("/admin/user/hospital/del.do")
public class Del extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1.
		String hospitalId = req.getParameter("hospitalId");
		
		//2.
		req.setAttribute("hospitalId", hospitalId);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/user/hospital/del.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1.
		String hospitalId = req.getParameter("hospitalId");
		
		//2.
		AdminHospitalDAO dao = new AdminHospitalDAO();
		
		int result = dao.del(hospitalId);
		
		System.out.println(result);
		
		//3.
		if (result == 1) {
			
			resp.sendRedirect("/apa/admin/user/hospital/list.do");
			
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
		}
	}
}
