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

@WebServlet("/reservation/pharmacysubject.do")
public class PharmacySubject extends HttpServlet {
	
	/**
	 * @author 이재용
	 * 약국 상세 정보 조회
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		// 약국 아이디 파라미터 수집
		String id = req.getParameter("id");
		
		// ReservationDAO 객체 생성
		ReservationDAO dao = new ReservationDAO();
		
		// 현재 날짜 및 시간 정보 가져오기
		Date now = new Date();
		
		// 약국의 오픈 시간 정보 가져오기
		OpenTimeDTO timedto = dao.time2(id);
		timedto.setCloseTime(timedto.getCloseTime().substring(11));
		timedto.setOpenTime(timedto.getOpenTime().substring(11));
		
		// 요청 속성에 설정
		req.setAttribute("nowtime", now.getHours() + ":" + now.getMinutes() + ":00");
		req.setAttribute("timedto", timedto);
		req.setAttribute("id", id);
		
		// JSP 페이지로 포워딩
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/reservation/pharmacysubject.jsp");
		dispatcher.forward(req, resp);
	}
}
