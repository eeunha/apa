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
 * 일반 회원을 블랙리스트에 추가하는 서블릿 클래스
 */
@WebServlet("/admin/user/general/black/add.do")
public class Add extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/user/general/black/add.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    
		req.setCharacterEncoding("UTF-8");
		
		// 폼에서 입력한 아이디를 가져옴
		String id = req.getParameter("seq");
	    String content = req.getParameter("content");

	    AdminBlackDAO dao = new AdminBlackDAO();

	    // getUserSeq 메서드를 호출할 때 id를 전달
	    String seq = dao.getUserSeq(id);

	    AdminBlackDTO dto = new AdminBlackDTO();
	    dto.setUserId(seq);
	    dto.setBlacklistReason(content);
	    
	    // 블랙리스트에 사용자 추가
	    int result = dao.InsertBlackUser(seq, content);

	    if (result == 1) {
	        resp.setContentType("text/html; charset=UTF-8");
	        PrintWriter writer = resp.getWriter();
	        writer.print("<script>alert('정상적으로 작성되었습니다.');window.close();</script>");
	        resp.sendRedirect("/apa/admin/user/general/black/list.do");
	        writer.close();
	    } else {
	        PrintWriter writer = resp.getWriter();
	        writer.print("<script>alert('failed');history.back();</script>");
	        writer.close();
	    }
	}
}
