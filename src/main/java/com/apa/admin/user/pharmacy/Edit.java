package com.apa.admin.user.pharmacy;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.AdminPharmacyDTO;
import com.apa.repository.AdminPharmacyDAO;

/**
 * @author 이혜진
 * 약국 회원 정보 수정을 처리하는 서블릿 클래스
 */
@WebServlet("/admin/user/pharmacy/edit.do")
public class Edit extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1.. 회원 아이디 파라미터에서 가져오기
		String pharmacyId = req.getParameter("pharmacyId");
		
		//2. AdminPharmacyDAO의 인스턴스 생성
		AdminPharmacyDAO dao = new AdminPharmacyDAO();
		
		//회원 상세 정보 조회
		AdminPharmacyDTO dto = dao.detail(pharmacyId);
		
		//3. 조회한 DTO를 요청 속성으로 설정
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/user/pharmacy/edit.jsp");
		dispatcher.forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//HttpSession session = req.getSession();		
		
		req.setCharacterEncoding("UTF-8");
		
		//1.		
		String pharmacyName = req.getParameter("pharmacyName");
		String pharmacyPw = req.getParameter("pharmacyPw");
		String isPharmacy = req.getParameter("isPharmacy");
		String pharmacyId = req.getParameter("pharmacyId"); //수정할 약국
		
		//2.
		AdminPharmacyDAO dao = new AdminPharmacyDAO();
		
		AdminPharmacyDTO dto = new AdminPharmacyDTO();
		
		dto.setPharmacyName(pharmacyName);
		dto.setPharmacyPw(pharmacyPw);
		dto.setIsPharmacy(isPharmacy);
		//dto.setId(session.getAttribute("id").toString());
		dto.setPharmacyId(pharmacyId);
		
		System.out.println(dto);
		
		// DB 작업 > update
		int result = dao.edit(dto);
		
		//3.
		if (result == 1) {
				
			resp.sendRedirect("/apa/admin/user/pharmacy/view.do?pharmacyId=" + pharmacyId);
			
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
		}
		
	}
}
