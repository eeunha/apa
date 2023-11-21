package com.apa.admin.user.general;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apa.model.AdminUserDTO;
import com.apa.repository.AdminUserDAO;

/**
 * @author 이혜진
 * 일반 회원 정보 수정을 처리하는 서블릿 클래스
 */
@WebServlet("/admin/user/general/edit.do")
public class Edit extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 일반 회원 일련번호 파라미터에서 가져오기
		String userSeq = req.getParameter("userSeq");
		
		//2. AdminUserDAO의 인스턴스 생성
		AdminUserDAO dao = new AdminUserDAO();
		
		//일반 회원 상세 정보 조회
		AdminUserDTO dto = dao.detail(userSeq);
		
		//3. 조회한 DTO를 요청 속성으로 설정
		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/user/general/edit.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//HttpSession session = req.getSession();		
		
		req.setCharacterEncoding("UTF-8");
		
		//1.		
		String userName = req.getParameter("userName");
		String userPw = req.getParameter("userPw");
		String isUserUnregister = req.getParameter("isUserUnregister");
		String userSeq = req.getParameter("userSeq"); //수정할 사람
		
		//2.
		AdminUserDAO dao = new AdminUserDAO();
		
		AdminUserDTO dto = new AdminUserDTO();
		
		dto.setUserName(userName);
		dto.setUserPw(userPw);
		dto.setIsUserUnregister(isUserUnregister);
		//dto.setId(session.getAttribute("id").toString());
		dto.setUserSeq(userSeq);
		
		
		System.out.println(dto);
		
		// DB 작업 > update
		int result = dao.edit(dto);
		
		//3.
		if (result == 1) {
				
			resp.sendRedirect("/apa/admin/user/general/view.do?userSeq=" + userSeq);
			
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
		}
		
	}
}

