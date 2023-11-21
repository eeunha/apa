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

@WebServlet("/reservation/subject.do")
public class Subject extends HttpServlet {
	
	/**
	 * @author 이재용
	 * 진료과, 의사, 날짜 선택 페이지
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		// 병원 아이디 및 예약 유형 파라미터 수집
		String id = req.getParameter("id");
		String choicetype = req.getParameter("choicetype");
		
		// 선택한 예약 유형이 '왕진'인 경우, find/visit.do로 리다이렉트
		if (choicetype.equals("왕진")) {
			resp.sendRedirect("/apa/find/visit.do");
		} else {
			// MainDAO 객체 생성
			MainDAO dao = new MainDAO();

			// 선택한 병원의 진료과목 리스트 가져오기
			ArrayList<HospitalInfoDTO> deptlist = dao.deptnameslist(id);
			req.setAttribute("deptlist", deptlist);
			
			// ReservationDAO 객체 생성
			ReservationDAO dao2 = new ReservationDAO();
			
			// 현재 날짜 및 시간 정보 가져오기
			Date now = new Date();
			
			// 선택한 병원의 운영 시간 정보 가져오기
			OpenTimeDTO timedto = dao2.time(id);
			timedto.setCloseTime(timedto.getCloseTime().substring(11));
			timedto.setOpenTime(timedto.getOpenTime().substring(11));
			
			// 요청 속성 설정
			req.setAttribute("nowtime", now.getHours() + ":" + now.getMinutes() + ":00");
			req.setAttribute("timedto", timedto);
			req.setAttribute("id", id);
			req.setAttribute("choicetype", choicetype);
			
			// JSP 페이지로 포워딩
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/reservation/subject.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
