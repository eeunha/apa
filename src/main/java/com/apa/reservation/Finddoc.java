package com.apa.reservation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.apa.model.HospitalDoctorDTO;
import com.apa.model.HospitalInfoDTO;
import com.apa.repository.ReservationDAO;

@WebServlet("/reservation/finddoc.do")
public class Finddoc extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String deptname = req.getParameter("deptname");
		String seq = req.getParameter("seq");
		
		
		ReservationDAO dao = new ReservationDAO();
		
		ArrayList<HospitalDoctorDTO> list = dao.doctorlist(seq, deptname);
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		JSONArray arr = new JSONArray();
		
		for (HospitalDoctorDTO dto : list) {
			//DTO > JSONObject
			JSONObject obj = new JSONObject();
			
			obj.put("seq", dto.getSeq());
			obj.put("name", dto.getName());
			obj.put("img", dto.getImg());
			obj.put("deptname", dto.getDeptname());
			
			arr.add(obj);
			
		}
		
		PrintWriter writer = resp.getWriter();
		writer.write(arr.toString());
		writer.close();
	}

}