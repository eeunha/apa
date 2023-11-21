package com.apa.admin.community;

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
 * 이 서블릿은 관리자 페이지에서 커뮤니티 게시물 목록을 조회하고 페이징하여 출력하는 기능을 제공합니다.
 */
@WebServlet("/admin/community/list.do")
public class List extends HttpServlet {
    /**
     * HTTP GET 요청을 처리하고 커뮤니티 게시물 목록을 조회하여 페이징하여 출력합니다.
     *
     * @param req  클라이언트로부터의 HTTP 요청 객체
     * @param resp 서블릿이 클라이언트로 응답을 보낼 때 사용하는 HTTP 응답 객체
     * @throws ServletException 서블릿에서 발생한 일반적인 예외
     * @throws IOException      입출력 작업 중 발생한 예외
     */	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//List.java
		
				//A. list.do 호출(목록보기)
				//B. list.do?column=subject&word=검색어 > 호출(검색하기)
				
				String column = req.getParameter("column");
				String word = req.getParameter("word");
				String search = "n"; // 검색중("y"), 목록보기("n")
				
				//뒤에 아무것도 안넘어오면 무조건 서치는 n 이고 넘어오면 y
				if((column == null && word == null) || (column.equals("") && word.equals(""))) {
					search = "n";
				} else {
					search = "y";
				}
				
				
				HashMap<String,String> map = new HashMap<String,String>();
				
				map.put("column", column);
				map.put("word", word);
				map.put("search", search);
				

				
				int nowPage = 0;		//현재 페이지 번호
				int totalCount = 0;		//총 게시물 수
				int pageSize = 20;		//한페에지에서 출력할 게시물 수
				int totalPage = 0;		//총게시물을 가지고 와서 몇페이지 만들 수 있는지
				int begin = 0;			//페이징 시작 위치
				int end = 0;			//페이징 끝 위치
				int n = 0;
				int loop = 0;
				int blockSize = 10;
				
				String page = req.getParameter("page");
				
				if(page == null || page.equals("")) {
					nowPage = 1;
				} else {
					nowPage = Integer.parseInt(page);
				}
				
				
				
				begin = ((nowPage - 1) * pageSize) + 1;
				end = begin + pageSize - 1;
				
				map.put("begin", begin + "");
				map.put("end", end + "");
				
			
				
				
				//1. DB 작업 > select
				//2. 반환 > JSP 호출하기
				
				HttpSession session = req.getSession();
				
				//조회수 티켓
				session.setAttribute("read", "n");
				
				
				
				//1. 
				adminCommunityDAO dao = new adminCommunityDAO();
				
				//DB에서 데이터를 가져와서 출력하는 방식
				ArrayList<adminCommunityDTO> list = dao.list(map);
				
				//1.5 데이터 가공
				for (adminCommunityDTO dto : list) {
					
					
					String subject = dto.getSubject();
			
					
					if(subject.length() > 30) {
						subject = subject.substring(0, 30) + "..";
					}
					
					dto.setRegdate(dto.getRegdate().substring(0,10));
					
				
					
					//태그 비활성화
					
					subject = subject.replace("<", "&lt");
					subject = subject.replace(">", "&gt");
					
					dto.setSubject(subject);
					
				}
				
				
				
				// 총 게시물 수 
				totalCount = dao.getTotalCount(); 
				totalPage = (int)Math.ceil((double)totalCount / pageSize); 
				
				
				//페이지바 만들기
				//잦은 문자열 조작이 이루어질것이기 때문에 StringBuilder
				StringBuilder sb = new StringBuilder();
				
				
				loop = 1; //루프 변수(10바퀴)
				//n = 1; //출력할 페이지 번호
				//자신의 시작 숫자를 리턴함
				n = ((nowPage - 1) / blockSize) * blockSize + 1;
				
				//이전 10페이지
				if(n == 1) {
					sb.append(String.format("<a href='#!'>이전| </a>"));
				} else {
					sb.append(String.format("<a href='/apa/admin/community/list.do?page=%d'>이전 |</a>", n -1 ));
				}
				
				
				
				//현재 출력되고 있는 페이지 번호(n)가 토탈페이지 보다 크면 나가라고 조건 걸기
				while (!(loop > blockSize || n > totalPage)) {
					
					if(n == nowPage) {
						sb.append(String.format(" <a href='#!'style='color:tomato;'>%d</a> ", n));
					} else {
						sb.append(String.format(" <a href='/apa/admin/community/list.do?page=%d'>%d</a> ", n, n));
					}
					
					loop++;
					n++;
				}
				
			
				if(n > totalPage) {
					sb.append(String.format("<a href='#!'> | 다음 </a>"));
				} else {
					sb.append(String.format("<a href='/apa/admin/community/list.do?page=%d'> | 다음 </a>", n));
				}
				
				//2.
				req.setAttribute("list", list);
				req.setAttribute("map", map);
				
				req.setAttribute("totalCount", totalCount);
				req.setAttribute("totalPage", totalPage);
				req.setAttribute("nowPage", nowPage);
				
				req.setAttribute("pagebar", sb.toString());
				
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/community/list.jsp");
		dispatcher.forward(req, resp);
	}
}
