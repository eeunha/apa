package com.apa.hospital.doctor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.apa.repository.HospitalMyPageDAO;

/**
 * @author 이재용
 * 병원의 의사를 삭제하는 서블릿 클래스입니다.
 */
@WebServlet("/hospital/doctor/del.do")
public class Del extends HttpServlet {
	
	/**
	 * 의사 삭제 페이지로 이동하는 GET 요청 처리 메서드입니다.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/hospital/doctor/del.jsp");
		dispatcher.forward(req, resp);
	}

	/**
	 * 의사를 삭제하는 POST 요청 처리 메서드입니다.
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 요청 파라미터 수집
		String id = req.getParameter("id");
		
		// 2. HospitalMyPageDAO 객체 생성
		HospitalMyPageDAO dao = new HospitalMyPageDAO();
		
		// 3. 의사 삭제 메서드 호출
		int result = dao.doctordelete(id);
		
		// 4. 응답을 위한 JSON 객체 생성
		JSONObject obj = new JSONObject();
		obj.put("result", result);
		
		// 5. JSON 형태로 응답
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = resp.getWriter();
		writer.print(obj.toString());
		writer.close();
	}
}
