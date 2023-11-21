package com.apa.reservation;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apa.model.HospitalInfoDTO;
import com.apa.model.MagazineDTO;
import com.apa.model.UserChildDTO;
import com.apa.model.UserDTO;
import com.apa.repository.MainDAO;
import com.apa.repository.ReservationDAO;

@WebServlet("/reservation/select.do")
public class Select extends HttpServlet {
	
	/**
	 * @author 이재용
	 * 진료방식 선택 페이지
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		// 병원 아이디 파라미터 수집
		String id = req.getParameter("id");
		
		// MainDAO 객체 생성
		MainDAO dao = new MainDAO();
		
		// 선택한 병원의 정보를 가져오기
		HospitalInfoDTO hospitalinfo = dao.hospitalinfo(id);
		req.setAttribute("hospitalinfo", hospitalinfo);
		
		// 요청 속성 설정
		req.setAttribute("id", id);
		
		// JSP 페이지로 포워딩
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/reservation/select.jsp");
		dispatcher.forward(req, resp);
	}
}
