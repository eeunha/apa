package com.apa.magazine;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apa.magazine.MagazineDAO;
import com.apa.magazine.MagazineDTO;

/**
 * @author 안대명
 * 
 * Magazine 추가 기능을 담당하는 서블릿 클래스입니다.
 */
@WebServlet("/magazine/add.do")
public class Add extends HttpServlet {
	
    /**
     * GET 요청을 처리합니다.
     * Magazine 추가 화면을 표시합니다.
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException Servlet 예외
     * @throws IOException 입출력 예외
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/magazine/add.jsp");
		dispatcher.forward(req, resp);
	}
	
    /**
     * POST 요청을 처리합니다.
     * Magazine을 추가하고 결과에 따라 작업을 처리합니다.
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException Servlet 예외
     * @throws IOException 입출력 예외
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		// 데이터 가져오기
		req.setCharacterEncoding("UTF-8");
		String magazineTitle = req.getParameter("magazineTitle"); 
		String magazineSubTitle = req.getParameter("magazineSubTitle"); 
		String magazineContent = req.getParameter("magazineContent"); 
	
		// DB 작업 > insert
		MagazineDAO dao = new MagazineDAO();
		MagazineDTO dto = new MagazineDTO();
		dto.setMagazineTitle(magazineTitle);
		dto.setMagazineSubTitle(magazineSubTitle);
		dto.setMagazineContent(magazineContent);
		
		int result = dao.add(dto);

		// 피드백
		if (result == 1) {
			resp.sendRedirect("/apa/magazine/list.do");
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
		}
	}
}