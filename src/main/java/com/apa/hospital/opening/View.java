package com.apa.hospital.opening;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.HospitalInfoDTO;
import com.apa.model.HospitalMyPageInfoDTO;
import com.apa.model.OpenTimeDTO;
import com.apa.repository.HospitalMyPageDAO;

/**
 * @author 이재용
 * 병원 영업 정보를 조회하고 수정하는 서블릿 클래스입니다.
 */
@WebServlet("/hospital/opening/view.do")
public class View extends HttpServlet {
	
	/**
	 * 병원 영업 정보를 조회하여 뷰 페이지로 포워딩합니다.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		
		// HospitalMyPageDAO 객체 생성
		HospitalMyPageDAO dao = new HospitalMyPageDAO();
		
		// 병원 정보 조회
		HospitalMyPageInfoDTO dto = dao.get(id);
		HospitalInfoDTO infodto = dao.infoget(id);
		OpenTimeDTO opendto = dao.getOpenTime(id);
		String dayoff = dao.getDayOff(id);
		
		// 운영 시간 문자열 형식 변경
		opendto.setCloseTime(opendto.getCloseTime().substring(10));
		opendto.setOpenTime(opendto.getOpenTime().substring(10));
		
		// 요청 속성 설정
		req.setAttribute("id", id);
		req.setAttribute("infodto", infodto);
		req.setAttribute("dayoff", dayoff);
		req.setAttribute("dto", dto);
		req.setAttribute("opendto", opendto);
		
		// JSP 페이지로 포워딩
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/hospital/opening/view.jsp");
		dispatcher.forward(req, resp);
	}
	
	/**
	 * 병원 영업 정보를 수정하고 결과를 클라이언트에게 응답합니다.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		// 수정할 병원 정보 파라미터 수집
		String info1 = req.getParameter("info1");
		String info2 = req.getParameter("info2");
		String info3 = req.getParameter("info3");
		String info4 = req.getParameter("info4");
		String info5 = req.getParameter("info5");
		String info6 = req.getParameter("info6");
		String info7 = req.getParameter("info7");
		String seq = req.getParameter("info8");
		
		// HospitalMyPageDAO 객체 생성
		HospitalMyPageDAO dao = new HospitalMyPageDAO();
		
		// 병원 정보 업데이트
		int result = dao.updateHospitalInfo(info1, info2, info3, info4, info5, info6, info7, seq);
		
		// 클라이언트에게 결과 전송
		PrintWriter writer = resp.getWriter();
		writer.write(result);
		writer.close();
	}
}
