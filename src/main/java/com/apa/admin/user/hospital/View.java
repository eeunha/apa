package com.apa.admin.user.hospital;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.AdminHospitalDTO;
import com.apa.model.AdminUserDTO;
import com.apa.repository.AdminHospitalDAO;
import com.apa.repository.AdminUserDAO;

/**
 * @author 이혜진
 * 병원 사용자 정보를 조회하는 서블릿 클래스
 */
@WebServlet("/admin/user/hospital/view.do")
public class View extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1. 회원 아이디 파라미터에서 가져오기
		String hospitalId = req.getParameter("hospitalId");
		
		// 2. AdminHospitalDAO의 인스턴스 생성
		AdminHospitalDAO dao = new AdminHospitalDAO();
		
		// 3. 회원 상세 정보 조회
		AdminHospitalDTO dto  = dao.detail(hospitalId);
		
		//System.out.println(dto);
		
		// 4. 검색한 DTO를 요청 속성으로 설정
		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/user/hospital/view.jsp");
		dispatcher.forward(req, resp);
	}
}
