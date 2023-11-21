package com.apa.admin.user.hospital;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.AdminHospitalDTO;
import com.apa.repository.AdminHospitalDAO;

/**
 * @author 이혜진
 * 병원 회원 정보 수정을 처리하는 서블릿 클래스
 */
@WebServlet("/admin/user/hospital/edit.do")
public class Edit extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 회원 아이디 파라미터에서 가져오기
		String hospitalId = req.getParameter("hospitalId");
		
		//2. AdminHospitalDAO의 인스턴스 생성
		AdminHospitalDAO dao = new AdminHospitalDAO();
		
		//회원 상세 정보 조회
		AdminHospitalDTO dto = dao.detail(hospitalId);
		
		//3. 조회한 DTO를 요청 속성으로 설정
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/user/hospital/edit.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//HttpSession session = req.getSession();		
		
		req.setCharacterEncoding("UTF-8");
		
		//1.		
		String hospitalName = req.getParameter("hospitalName");
		String hospitalPw = req.getParameter("hospitalPw");
		String isHospital = req.getParameter("isHospital");
		String hospitalId = req.getParameter("hospitalId"); //수정할 병원
		
		//2.
		AdminHospitalDAO dao = new AdminHospitalDAO();
		
		AdminHospitalDTO dto = new AdminHospitalDTO();
		
		dto.setHospitalName(hospitalName);
		dto.setHospitalPw(hospitalPw);
		dto.setIsHospital(isHospital);
		//dto.setId(session.getAttribute("id").toString());
		dto.setHospitalId(hospitalId);
		
		// DB 작업 > update
		int result = dao.edit(dto);
		
		//3.
		if (result == 1) {
				
			resp.sendRedirect("/apa/admin/user/hospital/view.do?hospitalId=" + hospitalId);
			
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
		}
		
	}
}
