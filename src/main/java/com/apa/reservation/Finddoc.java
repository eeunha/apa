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
	
	/**
	 * @author 이재용
	 * 특정 진료과에 속한 의사 목록 조회
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청 파라미터 수집
		String deptname = req.getParameter("deptname");
		String seq = req.getParameter("seq");
		
		// ReservationDAO 객체 생성
		ReservationDAO dao = new ReservationDAO();
		
		// 특정 부서에 속한 의사 목록 가져오기
		ArrayList<HospitalDoctorDTO> list = dao.doctorlist(seq, deptname);
		
		// 응답 데이터의 형식을 JSON으로 설정
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		// JSON 배열 생성
		JSONArray arr = new JSONArray();
		
		// 의사 목록을 JSON 객체로 변환하여 배열에 추가
		for (HospitalDoctorDTO dto : list) {
			JSONObject obj = new JSONObject();
			
			obj.put("seq", dto.getSeq());
			obj.put("name", dto.getName());
			obj.put("img", dto.getImg());
			obj.put("deptname", dto.getDeptname());
			
			arr.add(obj);
		}
		
		// 응답 데이터 전송
		PrintWriter writer = resp.getWriter();
		writer.write(arr.toString());
		writer.close();
	}
}
