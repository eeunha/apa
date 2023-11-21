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

/**
 * @author 이재용
 * 병원의 의사 목록을 조회하는 서블릿 클래스입니다.
 */
@WebServlet("/hospital/doctor/list.do")
public class List extends HttpServlet {
	
	/**
	 * @author 이재용
	 * 병원의 의사 목록을 조회하여 목록 페이지로 이동하는 GET 요청 처리 메서드입니다.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 0. 요청 파라미터 수집
		String id = req.getParameter("id");
		
		// 1. HospitalMyPageDAO 객체 생성
		HospitalMyPageDAO dao = new HospitalMyPageDAO();
		
		// 2. 병원 정보 및 의사 목록 조회
		HospitalMyPageInfoDTO dto = dao.get(id); // 병원 정보
		ArrayList<HospitalDoctorDTO> list = dao.listDoctor(id); // 의사 목록
		
		// 3. 요청 속성 설정
		req.setAttribute("list", list);
		req.setAttribute("id", id);
		req.setAttribute("dto", dto);
		
		// 4. 뷰 페이지로 이동
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/hospital/doctor/list.jsp");
		dispatcher.forward(req, resp);
	}
}
