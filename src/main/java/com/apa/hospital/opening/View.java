package com.apa.hospital.opening;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.HospitalInfoDTO;
import com.apa.model.HospitalMyPageInfoDTO;
import com.apa.model.OpenTimeDTO;
import com.apa.repository.HospitalMyPageDAO;

@WebServlet("/hospital/opening/view.do")
public class View extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		
		
		//2.
		HospitalMyPageDAO dao = new HospitalMyPageDAO();
		
		HospitalMyPageInfoDTO dto = dao.get(id); //병원 정보
		
		HospitalInfoDTO infodto = dao.infoget(id);
		
		OpenTimeDTO opendto = dao.getOpenTime(id);
		
		String dayoff = dao.getDayOff(id);
		
		opendto.setCloseTime(opendto.getCloseTime().substring(10));
		opendto.setOpenTime(opendto.getOpenTime().substring(10));
		
		
		req.setAttribute("id", id);
		req.setAttribute("infodto", infodto);
		req.setAttribute("dayoff", dayoff);
		req.setAttribute("dto", dto);
		req.setAttribute("opendto", opendto);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/hospital/opening/view.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		String info1 = req.getParameter("info1");
		String info2 = req.getParameter("info2");
		String info3 = req.getParameter("info3");
		String info4 = req.getParameter("info4");
		String info5 = req.getParameter("info5");
		String info6 = req.getParameter("info6");
		String info7 = req.getParameter("info7");
		String seq = req.getParameter("info8");
		System.out.println(info1);
		System.out.println(seq);
		
		
		HospitalMyPageDAO dao = new HospitalMyPageDAO();
		
		int result = dao.updateHospitalInfo(info1,info2,info3,info4,info5,info6,info7,seq);
		
		PrintWriter writer = resp.getWriter();
		writer.write(result);
		writer.close();
		
	}
}

