package com.apa.find;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.PharmacyInfoDTO;
import com.apa.model.SelfsymtomDTO;
import com.apa.repository.MainDAO;
import com.apa.repository.UserDAO;

@WebServlet("/find/pharmacylist.do")
public class PharmacyList extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		UserDAO dao = new UserDAO();
		
		ArrayList<PharmacyInfoDTO> list = dao.pharmacylist();
		
		req.setAttribute("list", list);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/find/pharmacylist.jsp");
		dispatcher.forward(req, resp);
	}

}
