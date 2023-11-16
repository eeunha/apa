package com.apa.find;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.PharmacyInfoDTO;
import com.apa.repository.MainDAO;

@WebServlet("/find/pharmacyview.do")
public class PharmacyView extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String seq = req.getParameter("id");
		
		MainDAO dao = new MainDAO();
	
		resp.setCharacterEncoding("UTF-8");
		
		PharmacyInfoDTO dto = dao.pharmacyInfo(seq);
		
		/*
		 * dto.setOpentime(dto.getOpentime().substring(11,16));
		 * dto.setClosetime(dto.getClosetime().substring(11,16));
		 */
		
		req.setAttribute("dto", dto);
		
		
		
		/*
		 * ArrayList<HospitalInfoDTO> deptlist = dao.deptnameslist(seq);
		 * req.setAttribute("deptlist", deptlist);
		 */
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/find/pharmacyview.jsp");
		dispatcher.forward(req, resp);
	}
}
