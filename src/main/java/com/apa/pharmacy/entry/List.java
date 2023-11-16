package com.apa.pharmacy.entry;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apa.pharmacy.model.PharmacyDTO;
import com.apa.pharmacy.repository.PharmacyDAO;

@WebServlet("/pharmacy/entry/list.do")
public class List extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
//		String pharmacyId = (String) session.getAttribute("pharmacyId");
		String pharmacyId = session.getAttribute("id").toString();
//		session.setAttribute("sla0623", "sla1234");
		System.out.println(pharmacyId);

		if (pharmacyId == null || "".equals(pharmacyId)) {
			pharmacyId = req.getParameter("pharmacyId");
		}
		// 1.
		PharmacyDAO dao = new PharmacyDAO();

		PharmacyDTO dto = dao.getEntryInfo(pharmacyId);

		
		req.setAttribute("dto", dto);
		req.setAttribute("pharmacyId", pharmacyId);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/pharmacy/entry/list.jsp");
		dispatcher.forward(req, resp);
	}
}
