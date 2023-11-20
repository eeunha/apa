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

@WebServlet("/admin/user/general/report/decline.do")
public class Decline extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//1.
		String userSeq = req.getParameter("userSeq");
		
		//2.
		AdminReportDAO dao = new AdminReportDAO();
		
		AdminReportDTO dto = dao.detail(userSeq);
		
		//3.
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/user/general/report/decline.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//EditOk.java 역할
		
		//1. 데이터 가져오기
		//2. DB 작업 > update
		//3. 피드백
		
		//HttpSession session = req.getSession();		
		
		req.setCharacterEncoding("UTF-8");
		
		//1.		
		String userSeq = req.getParameter("userSeq"); //수정할 병원
		
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
