package com.apa.magazine;

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

import com.apa.magazine.MagazineDTO;
import com.apa.magazine.MagazineDAO;

/**
 * @author 안대명
 * 
 * Magazine 목록 조회 및 페이징을 담당하는 서블릿 클래스입니다.
 */
@WebServlet("/magazine/list.do")
public class List extends HttpServlet {
	
    /**
     * GET 요청을 처리합니다.
     * Magazine 목록을 조회하고 페이징하여 화면에 표시합니다.
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException Servlet 예외
     * @throws IOException      입출력 예외
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//magazineList.java
				//A. list.do > 호출(목록보기)
				//B. list.do?column=subject&word=검색어 > 호출(검색하기)
						
				String column = req.getParameter("column");
				String word = req.getParameter("word");
				String search = "n"; //검색중("y"), 목록보기("n")
						
				if ((column == null && word == null) || (column.equals("") && word.equals(""))) {
					search = "n";
				} else {
					search = "y";
				}
				
				
				HashMap<String,String> map = new HashMap<String,String>();
						
				map.put("column", column);
				map.put("word", word);		
				map.put("search", search);		
							
				
				//페이징
				//- list.do > 1페이지 간주
				//- list.do?page=1
				//- list.do?page=5
				
				int nowPage = 0;	///현재 페이지 번호
				int totalCount = 0; //총 게시물 수
				int pageSize = 10;	//한페이지에서 출력할 게시물 수
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
						
						
				//list.do?page=1 > where rnum between 1 and 10
				//list.do?page=2 > where rnum between 11 and 20
				//list.do?page=3 > where rnum between 21 and 30
						
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
				MagazineDAO dao = new MagazineDAO();
						
				ArrayList<MagazineDTO> list = dao.list(map);
						
						
				//1.5 데이터 가공
				for (MagazineDTO dto : list) {
							
					//날짜 자르기
					//String regdate = dto.getRegdate();			
					//dto.setRegdate(regdate.substring(0, 10));
							
							
					//제목 길이 자르기
					String magazineTitle = dto.getMagazineTitle();
							
					if (magazineTitle.length() > 20) {
						magazineTitle = magazineTitle.substring(0, 20) + "..";
					}
							
							
							
					//태그 비활성화			
					magazineTitle = magazineTitle.replace("<", "&lt;");
					magazineTitle = magazineTitle.replace(">", "&gt;");
							
					dto.setMagazineTitle(magazineTitle);
							
							
				}
						
						
						
				//총 게시물 수
				totalCount = dao.getTotalCount(); //200
				totalPage = (int)Math.ceil((double)totalCount / pageSize); //20

				StringBuilder sb = new StringBuilder();
				
				
				/*
				for (int i=1; i<=totalPage; i++) {
					
					if (i == nowPage) {
						sb.append(String.format(" <a href='#!' style='color:tomato;'>%d</a> ", i, i));
					} else {
						sb.append(String.format(" <a href='/toy/board/list.do?page=%d'>%d</a> ", i, i));
					}
				}
				*/
				
				
				//list.do?page=1
				//[이전] 1 2 3 4 5 6 7 8 9 10 [다음]
				
				//list.do?page=5
				//[이전] 1 2 3 4 5 6 7 8 9 10 [다음]
				
				//list.do?page=10
				//[이전] 1 2 3 4 5 6 7 8 9 10 [다음]
				
				//list.do?page=11
				//[이전] 11 12 13 14 15 16 17 18 19 20 [다음]

				
				loop = 1; //루프 변수(10바퀴)
				//n = 1; //출력 페이지 번호
				n = ((nowPage - 1) / blockSize) * blockSize + 1;
						
						
				//이전 10페이지
				if (n == 1) {
					sb.append(String.format("<a href='#!'>[이전 %d페이지]</a>", blockSize));
				} else {
					sb.append(String.format("<a href='/apa/magazine/list.do?page=%d'>[이전 %d페이지]</a>", n - 1, blockSize));
				}
						
						
				while (!(loop > blockSize || n > totalPage)) {
							
					if (n == nowPage) {
						sb.append(String.format(" <a href='#!' style='color:tomato;'>%d</a> ", n));
					} else {
						sb.append(String.format(" <a href='/apa/magazine/list.do?page=%d'>%d</a> ", n, n));
					}
							
					loop++;
					n++;
				}
						
						
				//다음 10페이지
				if (n > totalPage) {
					sb.append(String.format("<a href='#!'>[다음 %d페이지]</a>", blockSize));
				} else {
					sb.append(String.format("<a href='/apa/magazine/list.do?page=%d'>[다음 %d페이지]</a>", n, blockSize));
				}
				
				while (!(loop > blockSize || n > totalPage)) {
					
					if (n == nowPage) {
						sb.append(String.format(" <a href='#!' style='color:tomato;'>%d</a> ", n));
					} else {
						sb.append(String.format(" <a href='/apa/magazine/list.do?page=%d'>%d</a> ", n, n));
					}
					
					loop++;
					n++;
				}
						
				//2.
				req.setAttribute("list", list);
				req.setAttribute("map", map);
						
				req.setAttribute("totalCount", totalCount);
				req.setAttribute("totalPage", totalPage);
				req.setAttribute("nowPage", nowPage);
						
				req.setAttribute("pagebar", sb.toString());

				RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/magazine/list.jsp");
				dispatcher.forward(req, resp);
	}
}