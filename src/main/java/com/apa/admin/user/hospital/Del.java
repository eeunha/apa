package com.apa.admin.user.hospital;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.repository.AdminHospitalDAO;

/**
 * @author 이혜진
 * 병원 회원 정보 삭제를 처리하는 서블릿 클래스
 */
@WebServlet("/admin/user/hospital/del.do")
public class Del extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 회원 아이디 파라미터에서 가져오기
		String hospitalId = req.getParameter("hospitalId");
		
		//2. 요청 속성으로 일련번호 설정
		req.setAttribute("hospitalId", hospitalId);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/user/hospital/del.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 회원 아이디 파라미터에서 가져오기
		String hospitalId = req.getParameter("hospitalId");
		
		//2. AdminHospitalDAO의 인스턴스 생성
		AdminHospitalDAO dao = new AdminHospitalDAO();
		
		//회원 정보 삭제
		int result = dao.del(hospitalId);
		
		//System.out.println(result);
		
		//3. 피드백
		if (result == 1) {
			
			resp.sendRedirect("/apa/admin/user/hospital/list.do");
			
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
		}
	}
}
