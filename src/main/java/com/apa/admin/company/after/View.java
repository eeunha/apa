package com.apa.admin.company.after;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.AdminAfterDTO;
import com.apa.repository.AdminAfterDAO;


@WebServlet("/admin/company/after/view.do")
public class View extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String hospitalId = req.getParameter("hospitalId");
		
		AdminAfterDAO dao = new AdminAfterDAO();
		
		AdminAfterDTO dto  = dao.detail(hospitalId);
		
		//System.out.println(dto);
		
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/company/after/view.jsp");
		dispatcher.forward(req, resp);
	}
}
