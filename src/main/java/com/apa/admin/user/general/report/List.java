package com.apa.admin.user.general.report;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.AdminReportDTO;
import com.apa.repository.AdminReportDAO;

/**
 * @author 이혜진
 * 일반 회원 신고 현황 리스트를 조회하는 서블릿 클래스
 */
@WebServlet("/admin/user/general/report/list.do")
public class List extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HashMap<String,String> map = new HashMap<String,String>();
		
		//페이징
		int nowPage = 0;	///현재 페이지 번호
		int totalCount = 0; //총 게시물 수
		int pageSize = 20;	//한페이지에서 출력할 게시물 수
		int totalPage = 0;	//총 페이지 수
		int begin = 0;		//페이징 시작 위치
		int end = 0;		//페이지 끝 위치
		int n = 0;
		int loop = 0;
		int blockSize = 10;
		
		
		
		String page = req.getParameter("page");
		
		if (page == null || page.equals("")) {
			nowPage = 1;
		} else {
			nowPage = Integer.parseInt(page);
		}
		
		begin = ((nowPage - 1) * pageSize) + 1;
		end = begin + pageSize - 1;
		
		// 페이징 정보를 HashMap에 추가
		map.put("begin", begin + "");
		map.put("end", end + "");
		
		// DAO를 통해 신고 목록 조회
		AdminReportDAO dao =  new AdminReportDAO();
		
		ArrayList<AdminReportDTO> list = dao.list(map);
		
		//총 게시물 수
		totalCount = dao.getTotalCount();
		
		totalPage = (int)Math.ceil((double)totalCount / pageSize);
		
		
		StringBuilder sb = new StringBuilder();
		
		loop = 1; //루프 변수(10바퀴)
		n = ((nowPage - 1) / blockSize) * blockSize + 1;
		
		//이전 10페이지
		if (n == 1) {
			sb.append(String.format("<a href='#!'>[이전]</a>"));
		} else {
			sb.append(String.format("<a href='/apa/admin/user/general/report/list.do?page=%d'>[이전]</a>", n - 1));
		}
		
		
		
		while (!(loop > blockSize || n > totalPage)) {
			
			if (n == nowPage) {
				sb.append(String.format(" <a href='#!' style='color:tomato;'>%d</a>", n));
			} else {
				sb.append(String.format(" <a href='/apa/admin/user/general/report/list.do?page=%d'>%d</a>", n, n));
			}
			
			loop++;
			n++;
		}
		
		
		//다음 10페이지
		if (n > totalPage) {
			sb.append(String.format("<a href='#!'>[다음]</a>"));
		} else {
			sb.append(String.format("<a href='/apa/admin/user/general/report/list.do?page=%d'>[다음]</a>", n));
		}
		
		// request에 필요한 속성 설정
		req.setAttribute("list", list);
		req.setAttribute("map", map);
		
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("nowPage", nowPage);
		
		req.setAttribute("pagebar", sb.toString());
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/user/general/report/list.jsp");
		dispatcher.forward(req, resp);
	}
}
