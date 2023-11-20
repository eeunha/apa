package com.apa.pharmacy.dispense;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apa.pharmacy.repository.DispenseDAO;

@WebServlet("/pharmacy/dispense/completefinish.do")
public class CompleteFinish extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
//		String pharmacyId = (String) session.getAttribute("pharmacyId");
		String pharmacyId = session.getAttribute("id").toString();
		
		String[] dispenseIds = req.getParameterValues("dispenseIds");

	    if (dispenseIds != null && dispenseIds.length > 0) {
	        DispenseDAO dispenseDAO = new DispenseDAO();

	        // Iterate over the selected dispenseIds and updateCompleteFinish
	        for (String dispenseId : dispenseIds) {
	            dispenseDAO.updatecompleteFinish(dispenseId);
	        }

	        resp.getWriter().write("Success");
	    } else {
	        resp.getWriter().write("NoSelection");
	    }
	} 
}