package com.apa.hospital.customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.hospital.PatientDTO;
import com.apa.repository.hospital.CustomerDAO;

/**
 * 병원 - 내 진료의 고객 관리 중 내원환자를 담당하는 클래스입니다.
 * @author Eunha
 *
 */
@WebServlet("/hospital/customer/list.do")
public class List extends HttpServlet {
	
	/**
	 * 내원 환자 목록을 불러오는 메소드입니다.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String hospitalId = req.getSession().getAttribute("id").toString();

		CustomerDAO dao = new CustomerDAO();

		// 페이징
		int nowPage = 0; // 현재 페이지 번호
		int totalCount = 0; // 총 게시물 수
		int pageSize = 10; // 한 페이지에서 출력할 게시물 수
		int totalPage = 0; // 총 페이지 수
		int begin = 0; // 페이징 시작 위치
		int end = 0; // 페이징 끝 위치
		int n = 0;
		int loop = 0;
		int blockSize = 10; // 한번에 보여줄 페이지 개수

		String page = req.getParameter("page");

		if (page == null || page.equals("")) {
			nowPage = 1;
		} else {
			nowPage = Integer.parseInt(page);
		}

		begin = ((nowPage - 1) * pageSize) + 1;
		end = begin + pageSize - 1;

		HashMap<String, String> map = new HashMap<>();
		map.put("begin", begin + "");
		map.put("end", end + "");

		map.put("hospitalId", hospitalId);

		// 내원환자 목록 가져오기
//		ArrayList<PatientDTO> list = dao.getPatientList(hospitalId);
		ArrayList<PatientDTO> list = dao.getPatientList(map);

		// 총 게시물 수
		totalCount = dao.getPatientListCount(hospitalId);
		//System.out.println("totalCount: " + totalCount);

		totalPage = (int) Math.ceil((double) totalCount / pageSize);

		// 페이지바
		StringBuilder sb = new StringBuilder();

		loop = 1; // 루프 변수(10바퀴)
		n = ((nowPage - 1) / blockSize) * blockSize + 1; // 출력 페이지 번호

		// 이전 10페이지
		if (n == 1) {
			sb.append(" <a href='#!';>[이전 페이지]</a>&nbsp;&nbsp;");
		} else {
			sb.append(String.format(" <a href='/apa/hospital/customer/list.do?page=%d';>[이전 페이지]</a>&nbsp;&nbsp;",
					n - 1));
		}

		while (!(loop > blockSize || n > totalPage)) {

			if (n == nowPage) {
				sb.append(String.format(" <a href='#!' style='color:tomato;'>%d</a>&nbsp;&nbsp;", n));
			} else {
				sb.append(String.format(" <a href='/apa/hospital/customer/list.do?page=%d'>%d</a>&nbsp;&nbsp;", n, n));
			}

			loop++;
			n++;
		}

		// 다음 10페이지
		if (n > totalPage) {
			sb.append(" <a href='#!';>[다음 페이지]</a> ");
		} else {
			sb.append(String.format(" <a href='/apa/hospital/customer/list.do?page=%d';>[다음 페이지]</a> ", n));
		}

		req.setAttribute("list", list);
		
		req.setAttribute("map", map);
		
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("nowPage", nowPage);

		req.setAttribute("pagebar", sb.toString());

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/hospital/customer/list.jsp");
		dispatcher.forward(req, resp);
	}
}
