package com.apa.hospital.info;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.HospitalMyPageInfoDTO;
import com.apa.model.UserDTO;
import com.apa.repository.HospitalMyPageDAO;
import com.apa.repository.UserDAO;

/**
 * @author 이재용
 * 병원 정보를 조회하는 서블릿 클래스입니다.
 */
@WebServlet("/hospital/info/view.do")
public class View extends HttpServlet {
	
	/**
	 * 병원 정보를 조회하여 상세페이지로 이동하는 GET 요청 처리 메서드입니다.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 0. 요청 파라미터 수집
		String id = req.getParameter("id");
		
		// 1. HospitalMyPageDAO 객체 생성
		HospitalMyPageDAO dao = new HospitalMyPageDAO();
		
		// 2. 병원 정보 조회
		HospitalMyPageInfoDTO dto = dao.get(id);
		
		// 주민등록번호 및 전화번호 마스킹 처리
		String[] ssnArr = dto.getHospitalSsn().split("-");
		dto.setHospitalSSNs(ssnArr[0]);
		dto.setHospitalSSNm(ssnArr[1]);
		dto.setHospitalSSNe(ssnArr[2]);
		
		String[] telArr = dto.getHospitalTel().split("-");
		dto.setHospitalTels(telArr[0]);
		dto.setHospitalTelm(telArr[1]);
		dto.setHospitalTele(telArr[2]);
		
		// 3. 요청 속성 설정
		req.setAttribute("dto", dto);
		req.setAttribute("maskingSSN", ssnArr[2].charAt(0) + "******");
		
		// 4. 뷰 페이지로 이동
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/hospital/info/view.jsp");
		dispatcher.forward(req, resp);
	}
}
