package com.apa.admin.company.after;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
 * 부정 태그 비율이 70% 이상인 병원 목록을 조회하는 서블릿 클래스
 */
@WebServlet("/admin/company/after/list.do")
public class List extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//DAO를 통해 모든 병원의 후기 목록 조회
		AdminAfterDAO dao =  new AdminAfterDAO();
		
		ArrayList<AdminAfterDTO> list = dao.list();
		
		for (AdminAfterDTO dto : list) {
			
			//가입일 날짜 자르기
			String regdate = dto.getRegdate();			
			dto.setRegdate(regdate.substring(0, 10));
			
		}
		
		//조회 결과를 request에 설정
		req.setAttribute("list", list);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/company/after/list.jsp");
		dispatcher.forward(req, resp);
	}
}
