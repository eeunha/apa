package com.apa.admin.company.after;

import java.io.IOException;
import java.util.ArrayList;

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
 * 부정 태그 비율이 70% 이상인 병원 후기 및 상세 정보를 조회하는 서블릿 클래스
 */
@WebServlet("/admin/company/after/view.do")
public class View extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//1. 병원 ID 파라미터 가져오기
		String hospitalId = req.getParameter("hospitalId");
		
		// 2. DAO를 통해 병원 상세 정보 조회
		AdminAfterDAO dao = new AdminAfterDAO();
		
		AdminAfterDTO dto = dao.detail(hospitalId);
		
		// 3. DAO를 통해 병원 후기 리스트 조회
		ArrayList<AdminAfterDTO> dtoReview = dao.detailReviews(hospitalId);

		
		for (AdminAfterDTO reviewDto : dtoReview) {
			
			String revRegdate = reviewDto.getRevRegdate();			
			reviewDto.setRevRegdate(revRegdate.substring(0, 10));
			
		}
		
		// 4. 조회 결과를 request에 설정
		req.setAttribute("dto", dto);
		req.setAttribute("dtoReview", dtoReview);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/company/after/view.jsp");
		dispatcher.forward(req, resp);
	}
}
