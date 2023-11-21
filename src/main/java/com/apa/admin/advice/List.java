package com.apa.admin.advice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author 최진희
 * 게시판 목록을 페이징하여 보여주는 서블릿 클래스
 */
@WebServlet("/admin/advice/list.do")
public class List extends HttpServlet {
    /**
     * HTTP GET 요청을 처리하고 게시판 목록을 페이징하여 출력합니다.
     *
     * @param req  클라이언트로부터의 HTTP 요청 객체
     * @param resp 서블릿이 클라이언트로 응답을 보낼 때 사용하는 HTTP 응답 객체
     * @throws ServletException 서블릿에서 발생한 일반적인 예외
     * @throws IOException      입출력 작업 중 발생한 예외
     */
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 세션 생성 또는 기존 세션 가져오기
		HttpSession session = req.getSession();
		HashMap<String,String> map = new HashMap<String,String>();
		
		 // 페이징에 사용되는 변수들 초기화
		int nowPage = 0;	///현재 페이지 번호
		int totalCount = 0; //총 게시물 수
		int pageSize = 20;	//한페이지에서 출력할 게시물 수
		int totalPage = 0;	//총 페이지 수
		int begin = 0;		//페이징 시작 위치
		int end = 0;		//페이지 끝 위치
		int n = 0;
		int loop = 0;
		int blockSize = 10;
		
		// 요청 파라미터로부터 현재 페이지 번호 확인
		String page = req.getParameter("page");
		
		if (page == null || page.equals("")) {
			nowPage = 1;
		} else {
			nowPage = Integer.parseInt(page);
		}
		
        // 페이징을 위한 시작 위치와 끝 위치 계산
		begin = ((nowPage - 1) * pageSize) + 1;
		end = begin + pageSize - 1;
		
		
		map.put("begin", begin + "");
		map.put("end", end + "");
		
		//1. 리스트 가져오기
		adminAdviceDAO dao = new adminAdviceDAO();
		ArrayList<adminAdviceDTO> list = dao.adminAdvice(map);
		
		// 리스트의 각 요소에 대한 처리 (답변 여부에 따라 상태 설정)
		for(adminAdviceDTO dto : list) {
			if(dto.getIsAnswer().equals("y") || dto.getIsAnswer().equals("Y")) {
				dto.setIsAnswer("답변완료");
			} else {
				dto.setIsAnswer("대기중");
			}
		}
		
		
		 // 전체 게시물 수와 페이지 수 계산
		totalCount = dao.getTotalCount(); 
		totalPage = (int)Math.ceil((double)totalCount / pageSize); 
		
		
		StringBuilder sb = new StringBuilder();

        // 페이징을 위한 루프 시작		
		loop = 1; //루프 변수(10바퀴)
		n = ((nowPage - 1) / blockSize) * blockSize + 1;
        
		// 이전 10페이지 링크 생성		
		if (n == 1) {
			sb.append(String.format("<a href='#!'> 이전 | </a>"));
		} else {
			sb.append(String.format("<a href='/apa/admin/advice/list.do?page=%d'> 이전 | </a>", n - 1));
		}
		
		


        // 페이징 번호 링크 생성		
		while (!(loop > blockSize || n > totalPage)) {
			
			if (n == nowPage) {
				sb.append(String.format(" <a href='#!' style='color:tomato;'>%d</a> ", n));
			} else {
				sb.append(String.format(" <a href='/apa/admin/advice/list.do?page=%d'>%d</a> ", n, n));
			}
			
			loop++;
			n++;
		}
		
		
		//다음 10페이지
		if (n > totalPage) {
			sb.append(String.format("<a href='#!'> | 다음 </a>"));
		} else {
			sb.append(String.format("<a href='/apa/admon/advice/list.do?page=%d'> | 다음</a>", n));
		}
		
		
		//2.
		req.setAttribute("list", list);
		req.setAttribute("map", map);
		
		
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("nowPage", nowPage);
		
		req.setAttribute("pagebar", sb.toString());
        
		// JSP로 포워딩		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/advice/list.jsp");
		  dispatcher.forward(req, resp);
	}
}
