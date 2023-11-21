package com.apa.admin.user.pharmacy;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.AdminHospitalDTO;
import com.apa.model.AdminPharmacyDTO;
import com.apa.repository.AdminHospitalDAO;
import com.apa.repository.AdminPharmacyDAO;

/**
 * @author 이혜진
 * 약국 사용자 정보를 조회하는 서블릿 클래스
 */
@WebServlet("/admin/user/pharmacy/view.do")
public class View extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. 회원 아이디 파라미터에서 가져오기
		String pharmacyId = req.getParameter("pharmacyId");
		
		// 2. AdminPharmacyDAO의 인스턴스 생성
		AdminPharmacyDAO dao = new AdminPharmacyDAO();
		
		// 3. 회원 상세 정보 조회
		AdminPharmacyDTO dto  = dao.detail(pharmacyId);
		
		//System.out.println(dto);
		
		// 4. 검색한 DTO를 요청 속성으로 설정
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/user/pharmacy/view.jsp");
		dispatcher.forward(req, resp);
	}
}

