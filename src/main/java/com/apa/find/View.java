package com.apa.find;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.HospitalDoctorDTO;
import com.apa.model.HospitalInfoDTO;
import com.apa.model.MagazineDTO;
import com.apa.model.MediQuestionDTO;
import com.apa.repository.MainDAO;
import com.apa.repository.ReservationDAO;

@WebServlet("/find/view.do")
public class View extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String seq = req.getParameter("id");
		
		MainDAO dao = new MainDAO();
		ReservationDAO dao2 = new ReservationDAO();
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		HospitalInfoDTO dto = dao.hospitalinfo(seq);
		
		dto.setOpentime(dto.getOpentime().substring(11,16));
		dto.setClosetime(dto.getClosetime().substring(11,16));
		dto.setBreakopen(dto.getBreakopen().substring(11,16));
		dto.setBreakclose(dto.getBreakclose().substring(11,16));
		
		req.setAttribute("dto", dto);
		
		
		ArrayList<HospitalInfoDTO> deptlist = dao.deptnameslist(seq);
		ArrayList<HospitalDoctorDTO> doclist = new ArrayList<HospitalDoctorDTO>();
		
		int i = 0;
		for (HospitalInfoDTO docdto : deptlist) {
			doclist.addAll(dao2.doctorlist(seq, docdto.getDeptnames()));
			i++;
		}
		
		
		
		
		req.setAttribute("doclist", doclist);
		req.setAttribute("deptlist", deptlist);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/find/view.jsp");
		dispatcher.forward(req, resp);
	}
}
