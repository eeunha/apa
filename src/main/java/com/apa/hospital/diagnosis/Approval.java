package com.apa.hospital.diagnosis;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.apa.repository.hospital.DiagnosisDAO;

/**
 * 병원 - 내 진료 기능 중 진료 예약을 승인하는 클래스입니다.
 * @author Eunha
 *
 */
@WebServlet("/hospital/diagnosis/approval.do")
public class Approval extends HttpServlet {
	
	/**
	 * 진료 예약 승인 처리를 하고 결과를 반환하는 메소드입니다.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mediSeq = req.getParameter("mediSeq");
//		System.out.println(mediSeq);
		
		DiagnosisDAO dao = new DiagnosisDAO();
	
		int result = dao.approvalRegister(mediSeq);
		
		resp.setContentType("application/json");

		JSONObject obj = new JSONObject();
		obj.put("result", result);
		
		PrintWriter writer = resp.getWriter();
		writer.write(obj.toString());
		writer.close();
	}
}
