package com.apa.hospital.doctor;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.ChildrenDTO;
import com.apa.model.HospitalDoctorDTO;
import com.apa.model.HospitalMyPageInfoDTO;
import com.apa.model.UserDTO;
import com.apa.repository.HospitalMyPageDAO;
import com.apa.repository.UserDAO;

@WebServlet("/hospital/doctor/list.do")
public class List extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		
		
		//2.
		HospitalMyPageDAO dao = new HospitalMyPageDAO();
		
		HospitalMyPageInfoDTO dto = dao.get(id); //병원 정보
		ArrayList<HospitalDoctorDTO> list = dao.listDoctor(id);
		
		/* String userChild = dao.getUserChild(seq); */
		
		//3.
		req.setAttribute("list", list);
		req.setAttribute("id", id);
		req.setAttribute("dto", dto);
		/* req.setAttribute("userChild", userChild); */
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/hospital/doctor/list.jsp");
		dispatcher.forward(req, resp);
	}
}

