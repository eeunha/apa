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

@WebServlet("/admin/user/general/black/del.do")
public class Del extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/user/general/black/del.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    
		req.setCharacterEncoding("UTF-8");
		
		String userSeq = req.getParameter("userSeq");  // 폼에서 입력한 아이디를 가져옴

	    AdminBlackDAO dao = new AdminBlackDAO();


	    AdminBlackDTO dto = new AdminBlackDTO();

	    int result = dao.deleteBlackUser(userSeq);
	    
	    System.out.println(result);

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
