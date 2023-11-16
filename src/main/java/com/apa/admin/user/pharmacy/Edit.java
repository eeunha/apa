package com.apa.admin.user.pharmacy;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.AdminPharmacyDTO;
import com.apa.repository.AdminPharmacyDAO;

@WebServlet("/admin/user/pharmacy/edit.do")
public class Edit extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1.
		String pharmacyId = req.getParameter("pharmacyId");
		
		//2.
		AdminPharmacyDAO dao = new AdminPharmacyDAO();
		
		AdminPharmacyDTO dto = dao.detail(pharmacyId);
		
		//3.
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/user/pharmacy/edit.jsp");
		dispatcher.forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//EditOk.java 역할
		
		//1. 데이터 가져오기
		//2. DB 작업 > update
		//3. 피드백
		
		//HttpSession session = req.getSession();		
		
		req.setCharacterEncoding("UTF-8");
		
		//1.		
		String pharmacyName = req.getParameter("pharmacyName");
		String pharmacyPw = req.getParameter("pharmacyPw");
		String isPharmacy = req.getParameter("isPharmacy");
		String pharmacyId = req.getParameter("pharmacyId"); //수정할 병원
		
		//2.
		AdminPharmacyDAO dao = new AdminPharmacyDAO();
		
		AdminPharmacyDTO dto = new AdminPharmacyDTO();
		
		dto.setPharmacyName(pharmacyName);
		dto.setPharmacyPw(pharmacyPw);
		dto.setIsPharmacy(isPharmacy);
		//dto.setId(session.getAttribute("id").toString());
		dto.setPharmacyId(pharmacyId);
		
		
		System.out.println(dto);
		
		int result = dao.edit(dto);
		
		//3.
		if (result == 1) {
				
			resp.sendRedirect("/apa/admin/user/pharmacy/view.do?pharmacyId=" + pharmacyId);
			
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
		}
		
	}
}
