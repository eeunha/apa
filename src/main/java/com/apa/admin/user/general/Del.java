package com.apa.admin.user.general;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.repository.AdminUserDAO;


@WebServlet("/admin/user/general/del.do")
public class Del extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1.
		String userSeq = req.getParameter("userSeq");
		
		//2.
		req.setAttribute("userSeq", userSeq);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/user/general/del.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1.
		String userSeq = req.getParameter("userSeq");
		
		//2.
		AdminUserDAO dao = new AdminUserDAO();
		
		int result = dao.del(userSeq);
		
		System.out.println(result);
		
		//3.
		if (result == 1) {
			
			resp.sendRedirect("/apa/admin/user/general/list.do");
			
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
		}
	}
}

