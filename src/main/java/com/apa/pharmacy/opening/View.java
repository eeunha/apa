package com.apa.pharmacy.opening;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apa.pharmacy.model.OpenDTO;
import com.apa.pharmacy.repository.OpenDAO;

@WebServlet("/pharmacy/opening/view.do")
public class View extends HttpServlet {
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
		OpenDAO openDAO = new OpenDAO();

		OpenDTO openDTO = openDAO.getOpenInfo(pharmacyId);

		req.setAttribute("dto", openDTO);
		req.setAttribute("pharmacyId", pharmacyId);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/pharmacy/opening/view.jsp");
		dispatcher.forward(req, resp);
	}
}
