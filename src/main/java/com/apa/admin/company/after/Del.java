package com.apa.admin.company.after;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.AdminAfterDTO;
import com.apa.repository.AdminAfterDAO;


/**
 * @author 이혜진
 * 입점 취소를 하는 페이지로 이동하는 서블릿 클래스
 */
@WebServlet("/admin/company/after/del.do")
public class Del extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 삭제할 병원의 아이디를 가져옴
		String hospitalId = req.getParameter("hospitalId");
		
		//2. DAO를 통해 병원의 후기 상세 정보 조회
		AdminAfterDAO dao = new AdminAfterDAO();
		
		AdminAfterDTO dto = dao.detail(hospitalId);
		
		//3. 조회된 정보를 request에 설정
		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/company/after/del.jsp");
		dispatcher.forward(req, resp);
	}
	
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//EditOk.java 역할
		
		//1. 데이터 가져오기
		//2. DB 작업 > update
		//3. 피드백
		
		//HttpSession session = req.getSession();		
		
		req.setCharacterEncoding("UTF-8");
		
		//1.		
		String hospitalId = req.getParameter("hospitalId"); //수정할 병원
		
		//2.
		AdminAfterDAO dao = new AdminAfterDAO();
		
		AdminAfterDTO dto = new AdminAfterDTO();
		
		dto.setHospitalId(hospitalId);
		
		
		int result = dao.edit(dto);
		
		//3.
		if (result == 1) {
				
			resp.sendRedirect("/apa/admin/company/after/list.do");
			
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
		}
		
	}
}
