package com.apa.admin.user.general;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.AdminUserDTO;
import com.apa.repository.AdminUserDAO;

/**
 * @author 이혜진
 * 일반 회원 상세 정보를 조회하여 JSP 페이지로 전달하는 서블릿 클래스
 */
@WebServlet("/admin/user/general/view.do")
public class View extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. 회원 일련번호 파라미터에서 가져오기
		String userSeq = req.getParameter("userSeq");
		
		// 2. AdminUserDAO의 인스턴스 생성
		AdminUserDAO dao = new AdminUserDAO();
	
		// 3. 회원 상세 정보 조회
		AdminUserDTO dto  = dao.detail(userSeq);
		
		//System.out.println(dto);
		
		// 4. 검색한 DTO를 요청 속성으로 설정
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/user/general/view.jsp");
		dispatcher.forward(req, resp);
	}
}
