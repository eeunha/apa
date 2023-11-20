package com.apa.reservation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apa.model.HospitalInfoDTO;
import com.apa.model.MediQuestionDTO;
import com.apa.model.OpenTimeDTO;
import com.apa.repository.MainDAO;
import com.apa.repository.ReservationDAO;

@WebServlet("/reservation/subject.do")
public class Subject extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		req.setCharacterEncoding("UTF-8");
		
		String id = req.getParameter("id");
		String choicetype = req.getParameter("choicetype");
		
		if (choicetype.equals("왕진")) {
			resp.sendRedirect("/apa/find/visit.do");
		}else {
			MainDAO dao = new MainDAO();

			ArrayList<HospitalInfoDTO> deptlist = dao.deptnameslist(id);
			req.setAttribute("deptlist", deptlist);
			
			ReservationDAO dao2 = new ReservationDAO();
			
			Date now = new Date();
			
			OpenTimeDTO timedto = dao2.time(id);
			timedto.setCloseTime(timedto.getCloseTime().substring(11));
			timedto.setOpenTime(timedto.getOpenTime().substring(11));
			
			
			req.setAttribute("nowtime", now.getHours()+":"+now.getMinutes()+":00");
			req.setAttribute("timedto", timedto);
			
			req.setAttribute("id", id);
			req.setAttribute("choicetype", choicetype);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/reservation/subject.jsp");
			dispatcher.forward(req, resp);
		}
		
		
	}
}
