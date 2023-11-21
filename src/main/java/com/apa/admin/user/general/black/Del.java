package com.apa.admin.user.general.black;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.AdminBlackDTO;
import com.apa.repository.AdminBlackDAO;

/**
 * @author 이혜진
 * 블랙리스트에 있는 일반 회원을 삭제하는 서블릿 클래스
 */
@WebServlet("/admin/user/general/black/del.do")
public class Del extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 회원 번호(userSeq) 가져오기
		String userSeq = req.getParameter("userSeq");
		
		//2. DAO를 통해 해당 사용자의 블랙리스트 정보 가져오기
		AdminBlackDAO dao = new AdminBlackDAO();
		
		AdminBlackDTO dto = dao.detail(userSeq);
		
		//3. 블랙리스트 정보를 request에 설정
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/user/general/black/del.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    
		req.setCharacterEncoding("UTF-8");

	    // 1. 삭제할 사용자의 번호(userSeq) 가져오기
	    String userSeq = req.getParameter("userSeq");

	    // 2. DAO를 통해 블랙리스트에서 사용자 삭제
	    AdminBlackDAO dao = new AdminBlackDAO();
	    int result = dao.deleteBlackUser(userSeq);

	    if (result == 1) {
	        resp.sendRedirect("/apa/admin/user/general/black/list.do");
	    } else {
	        resp.setContentType("text/html; charset=UTF-8");
	        PrintWriter writer = resp.getWriter();
	        writer.print("<script>alert('삭제 실패');history.back();</script>");
	        writer.close();
	    }
	}
}
