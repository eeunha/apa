package com.apa.find;

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

import com.apa.model.HospitalInfoDTO;
import com.apa.repository.MainDAO;

@WebServlet("/find/find.do")
public class Find extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String seq1 = req.getParameter("seq1");
		String seq2 = req.getParameter("seq2");
		String seq3 = req.getParameter("seq3");
		String seq4 = req.getParameter("seq4");
		String seq5 = req.getParameter("seq5");
		String seq6 = req.getParameter("seq6");
		String seq7 = req.getParameter("seq7");
		String seq8 = req.getParameter("seq8");
		
		
		MainDAO dao = new MainDAO();
		
		ArrayList<HospitalInfoDTO> list = dao.hospitallist(seq1,seq2,seq3,seq4,seq5,seq6,seq7,seq8);
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		JSONArray arr = new JSONArray();
		
		for (HospitalInfoDTO dto : list) {
			//DTO > JSONObject
			JSONObject obj = new JSONObject();
			
			obj.put("id", dto.getId());
			obj.put("name", dto.getName());
			obj.put("address", dto.getAddress());
			obj.put("email", dto.getEmail());
			obj.put("tel", dto.getTel());
			obj.put("open", dto.getOpen());
			obj.put("deptname", dto.getDeptname());
			obj.put("breakopen", dto.getBreakopen());
			obj.put("breakclose", dto.getBreakclose());
			obj.put("opentime", dto.getOpentime());
			obj.put("closetime", dto.getClosetime());
			obj.put("face", dto.getFace());
			obj.put("unface", dto.getUnface());
			obj.put("call", dto.getCall());
			obj.put("check", dto.getCheck());
			obj.put("vaccin", dto.getVaccin());
			obj.put("night", dto.getNight());
			obj.put("holiday", dto.getHoliday());
			
			arr.add(obj);
			
		}
		
		PrintWriter writer = resp.getWriter();
		writer.write(arr.toString());
		writer.close();
	}

}
