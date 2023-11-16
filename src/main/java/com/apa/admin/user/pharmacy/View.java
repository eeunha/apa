package com.apa.admin.user.pharmacy;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.AdminHospitalDTO;
import com.apa.model.AdminPharmacyDTO;
import com.apa.repository.AdminHospitalDAO;
import com.apa.repository.AdminPharmacyDAO;

@WebServlet("/admin/user/pharmacy/view.do")
public class View extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String pharmacyId = req.getParameter("pharmacyId");
		
		AdminPharmacyDAO dao = new AdminPharmacyDAO();
		
		AdminPharmacyDTO dto  = dao.detail(pharmacyId);
		
		//System.out.println(dto);
		
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/user/pharmacy/view.jsp");
		dispatcher.forward(req, resp);
	}
}

