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
import com.apa.model.UserChildDTO;
import com.apa.model.UserDTO;
import com.apa.repository.MainDAO;
import com.apa.repository.ReservationDAO;

@WebServlet("/reservation/detail.do")
public class Detail extends HttpServlet {
	
	/**
	 * @author 이재용
	 * 예약 상세 정보 페이지로 이동 (POST 방식 요청 처리)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		// 요청 파라미터 수집
		String id = req.getParameter("id");
		String choicetype = req.getParameter("choicetype");
		String choicedept = req.getParameter("choicedept");
		String choicedoc = req.getParameter("choicedoc");
		String choiceday = req.getParameter("choiceday");
		String choicetime = req.getParameter("choicetime");
		
		System.out.println(choicetype);
		
		// 현재 날짜와 시간 정보
		Date now = new Date();
		
		// 세션에서 사용자 정보 가져오기
		HttpSession session = req.getSession();
		
		// ReservationDAO 객체 생성
		ReservationDAO dao = new ReservationDAO();
		  
		// 사용자 정보 및 자녀 정보 가져오기
		UserDTO dto = dao.userinfo(session.getAttribute("seq").toString());
		ArrayList<UserChildDTO> childdto = dao.userchild(session.getAttribute("seq").toString());
		
		// 사용자 정보에서 '-'로 구분된 부분을 나누어 속성에 설정
		req.setAttribute("firstssn", dto.getUserSsn().substring(0,6));
		req.setAttribute("lastssn", dto.getUserSsn().substring(7));
		
		req.setAttribute("firsttel", dto.getUserTel().substring(0,3));
		req.setAttribute("middeltel", dto.getUserTel().substring(4,8));
		req.setAttribute("lastltel", dto.getUserTel().substring(9));
		
		req.setAttribute("childfirsttel", dto.getUserTel().substring(0,3));
		req.setAttribute("childmiddeltel", dto.getUserTel().substring(4,8));
		req.setAttribute("childlastltel", dto.getUserTel().substring(9));
		
		req.setAttribute("emailid", dto.getUserEmail().substring(0,dto.getUserEmail().indexOf("@")));
		req.setAttribute("emailaddress", dto.getUserEmail().substring(dto.getUserEmail().indexOf("@")+1));
		
		// 속성에 사용자 정보 및 자녀 정보 설정
		req.setAttribute("dto", dto);
		req.setAttribute("childdto", childdto);
		
		// 속성에 예약 정보 설정
		req.setAttribute("id", id);//병원아이디
		req.setAttribute("choicetype", choicetype);//진료방식
		req.setAttribute("choicedoc", choicedoc);//의사번호
		req.setAttribute("choicetime", now.getYear()+1900+"-"+(now.getMonth()+1)+"-"+choiceday+" "+choicetime+":00");//예약일
		req.setAttribute("nowtime", now.getYear()+1900+"-"+(now.getMonth()+1)+"-"+now.getDate()+" "+now.getHours()+":"+now.getMinutes()+":00");
		
		// 예약 상세 정보 페이지로 포워딩
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/reservation/detail.jsp");
		dispatcher.forward(req, resp);
	}
}
