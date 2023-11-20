package com.apa.pharmacy.dispense;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.pharmacy.repository.DispenseDAO;

@WebServlet("/pharmacy/dispense/decline.do")
public class Decline extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    try {
	        String[] selectedDispenseIds = req.getParameterValues("dispenseIds");

	        DispenseDAO dispenseDAO = new DispenseDAO();
	        boolean success = dispenseDAO.updateDispenseDecline(selectedDispenseIds);

	        if (success) {
	            resp.getWriter().write("Success");
	        } else {
	            resp.getWriter().write("NoSelection");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "서버에서 오류가 발생했습니다.");
	    }
	}
}