package com.apa.find;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.HospitalInfoDTO;
import com.apa.model.SelfsymtomDTO;
import com.apa.repository.MainDAO;

@WebServlet("/find/list.do")
public class List extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		MainDAO dao = new MainDAO();
		
		ArrayList<SelfsymtomDTO> symtomlist = dao.symtomlist();
		
		 		
		ArrayList<HospitalInfoDTO> hosdto = dao.hospitallistmain();
		
		req.setAttribute("symtomlist", symtomlist);
		req.setAttribute("hosdto", hosdto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/find/list.jsp");
		dispatcher.forward(req, resp);
	}
}
