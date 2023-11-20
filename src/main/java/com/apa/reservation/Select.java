package com.apa.reservation;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apa.model.HospitalInfoDTO;
import com.apa.model.MagazineDTO;
import com.apa.model.UserChildDTO;
import com.apa.model.UserDTO;
import com.apa.repository.MainDAO;
import com.apa.repository.ReservationDAO;

@WebServlet("/reservation/select.do")
public class Select extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		req.setCharacterEncoding("UTF-8");
		
		String id = req.getParameter("id");
		
		MainDAO dao = new MainDAO();
		HospitalInfoDTO hospitalinfo = dao.hospitalinfo(id);
		req.setAttribute("hospitalinfo", hospitalinfo);
		
		req.setAttribute("id", id);
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/reservation/select.jsp");
		dispatcher.forward(req, resp);
	}
}

