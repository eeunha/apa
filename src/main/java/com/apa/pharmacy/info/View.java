package com.apa.pharmacy.info;

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

@WebServlet("/pharmacy/info/view.do")
public class View extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String pharmacyId = session.getAttribute("id").toString();
		
		System.out.println(pharmacyId);
		if(pharmacyId == null || "".equals(pharmacyId)) {
			pharmacyId = req.getParameter("pharmacyId");
		}
	
			
		//2.
		PharmacyDAO dao = new PharmacyDAO();
		
		PharmacyDTO dto = dao.get(pharmacyId); //회원 정보
		
		String[] ssnArr = dto.getPharmacySSN().split("-");
		
		dto.setPharmacySSNs(ssnArr[0]);
		dto.setPharmacySSNm(ssnArr[1]);
		dto.setPharmacySSNe(ssnArr[2]);
		
		
		String[] telArr = dto.getPharmacyTel().split("-");
		
		dto.setPharmacyTels(telArr[0]);
		dto.setPharmacyTelm(telArr[1]);
		dto.setPharmacyTele(telArr[2]);
		
		//3.
		req.setAttribute("dto", dto);
		req.setAttribute("maskingSSN", ssnArr[2].charAt(0) + "******");

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/pharmacy/info/view.jsp");
		dispatcher.forward(req, resp);
	}

	
}
