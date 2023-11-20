package com.apa.admin.user.pharmacy;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.repository.AdminPharmacyDAO;


@WebServlet("/admin/user/pharmacy/del.do")
public class Del extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1.
		String pharmacyId = req.getParameter("pharmacyId");
		
		//2.
		req.setAttribute("pharmacyId", pharmacyId);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/user/pharmacy/del.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1.
		String pharmacyId = req.getParameter("pharmacyId");
		
		//2.
		AdminPharmacyDAO dao = new AdminPharmacyDAO();
		
		int result = dao.del(pharmacyId);
		
		System.out.println(result);
		
		//3.
		if (result == 1) {
			
			resp.sendRedirect("/apa/admin/user/pharmacy/list.do");
			
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
		}
	}
}
