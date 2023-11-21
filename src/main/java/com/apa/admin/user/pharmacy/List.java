package com.apa.admin.user.pharmacy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.AdminHospitalDTO;
import com.apa.model.AdminPharmacyDTO;
import com.apa.repository.AdminPharmacyDAO;

/**
 * @author 이혜진
 * 약국 회원 목록을 제어하는 서블릿 클래스
 */
@WebServlet("/admin/user/pharmacy/list.do")
public class List extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 페이징 정보를 담은 맵 생성
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
		
		
		map.put("begin", begin + "");
		map.put("end", end + "");
		
		// AdminPharmacyDAO를 이용하여 사용자 목록 조회
		AdminPharmacyDAO dao =  new AdminPharmacyDAO();
		
		ArrayList<AdminPharmacyDTO> list = dao.list(map);
		
		for (AdminPharmacyDTO dto : list) {
			
			//가입일 날짜 자르기
			String regdate = dto.getRegdate();			
			dto.setRegdate(regdate.substring(0, 10));
			
		}
		
		//총 게시물 수
		totalCount = dao.getTotalCount();
		
		totalPage = (int)Math.ceil((double)totalCount / pageSize);
		
		
		StringBuilder sb = new StringBuilder();
		
		loop = 1; //루프 변수(10바퀴)
		n = ((nowPage - 1) / blockSize) * blockSize + 1;
		
		//이전 10페이지
		if (n == 1) {
			sb.append(String.format("<a href='#!'>[이전 %d페이지]</a>", blockSize));
		} else {
			sb.append(String.format("<a href='/apa/admin/user/pharmacy/list.do?page=%d'>[이전 %d페이지]</a>", n - 1, blockSize));
		}
		
		
		
		while (!(loop > blockSize || n > totalPage)) {
			
			if (n == nowPage) {
				sb.append(String.format(" <a href='#!' style='color:tomato;'>%d</a>", n));
			} else {
				sb.append(String.format(" <a href='/apa/admin/user/pharmacy/list.do?page=%d'>%d</a>", n, n));
			}
			
			loop++;
			n++;
		}
		
		
		//다음 10페이지
		if (n > totalPage) {
			sb.append(String.format("<a href='#!'>[다음 %d페이지]</a>", blockSize));
		} else {
			sb.append(String.format("<a href='/apa/admin/user/pharmacy/list.do?page=%d'>[다음 %d페이지]</a>", n, blockSize));
		}

		// JSP 페이지로 전달할 속성 설정
		req.setAttribute("list", list);
		req.setAttribute("map", map);
		
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("nowPage", nowPage);
		
		req.setAttribute("pagebar", sb.toString());
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/user/pharmacy/list.jsp");
		dispatcher.forward(req, resp);
	}
}
