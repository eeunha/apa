package com.apa.admin.user.general.report;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.apa.model.AdminReportDTO;
import com.apa.repository.AdminReportDAO;

/**
 * @author 이혜진
 * 신고를 거절하는 서블릿 클래스
 */
@WebServlet("/admin/user/general/report/decline.do")
public class Decline extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//1. 회원 일련번호 파라미터 가져오기
		String userSeq = req.getParameter("userSeq");
		
		//2. DAO를 통해 신고 상세 정보 조회
		AdminReportDAO dao = new AdminReportDAO();
		
		AdminReportDTO dto = dao.detail(userSeq);
		
		//3. 조회된 정보를 request에 설정
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/user/general/report/decline.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//HttpSession session = req.getSession();		
		
		req.setCharacterEncoding("UTF-8");
		
		//1.		
		String userSeq = req.getParameter("userSeq");
		
		//2.
		AdminReportDAO dao = new AdminReportDAO();
		
		AdminReportDTO dto = new AdminReportDTO();
		
		//dto.setId(session.getAttribute("id").toString());
		dto.setUserSeq(userSeq);
		
		
		System.out.println(userSeq);		
		int result = dao.decline(dto);
		
		//3.
		if (result == 1) {
				
			resp.sendRedirect("/apa/admin/user/general/report/list.do");
			
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
		}
		
	}
	
}
