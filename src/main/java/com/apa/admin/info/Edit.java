package com.apa.admin.info;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.AdminInfoDTO;
import com.apa.repository.AdminInfoDAO;

/**
 * @author 이혜진
 * 관리자 정보를 수정하는 서블릿 클래스
 */
@WebServlet("/admin/info/edit.do")
public class Edit extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 요청 매개변수에서 adminId를 가져오기
		String adminId = req.getParameter("adminId");
		
		//2. AdminInfoDAO의 인스턴스를 생성
		AdminInfoDAO dao = new AdminInfoDAO();
		
		//adminId를 기반으로 AdminInfoDTO를 검색
		AdminInfoDTO dto = dao.get(adminId);
		
		//3. JSP 페이지에서 사용할 수 있도록 검색한 DTO를 요청 속성으로 설정
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/info/edit.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//HttpSession session = req.getSession();		
		
		req.setCharacterEncoding("UTF-8");
		
		//1.		
		String adminPw = req.getParameter("adminPw");
		String adminEmail = req.getParameter("adminEmail");
		String adminTel = req.getParameter("adminTel");
		String adminId = req.getParameter("adminId");
		
		//2.
		AdminInfoDAO dao = new AdminInfoDAO();
		
		AdminInfoDTO dto = new AdminInfoDTO();
		
		dto.setAdminPw(adminPw);
		dto.setAdminEmail(adminEmail);
		dto.setAdminTel(adminTel);
		dto.setAdminId(adminId);
		
		int result = dao.edit(dto);
		
		//3.
		if (result == 1) {
				
			resp.sendRedirect("/apa/admin/admin/info/view.do");
			
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
		}
		
	}
}
