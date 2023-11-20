package com.apa.user.children;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.ChildrenDTO;
import com.apa.model.UserDTO;
import com.apa.repository.UserDAO;
/**
 * 서블릿 입니다.
 * 
 * 회원 마이페이지 -> 자녀 관리 -> 자녀 정보 목록 조회
 * 
 */
@WebServlet("/user/children/view.do")
public class View extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");
		
		
		//1.
		//String id = req.getSession().getAttribute("id").toString();
		
		//2.
		UserDAO dao = new UserDAO();
		
		UserDTO dto = dao.get(seq); //회원 정보
		ArrayList<ChildrenDTO> list = dao.listChildren(seq);
		
		String userChild = dao.getUserChild(seq);
		
		//3.
		req.setAttribute("list", list);
		req.setAttribute("seq", seq);
		req.setAttribute("dto", dto);
		req.setAttribute("userChild", userChild);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/children/view.jsp");
		dispatcher.forward(req, resp);
	}
}
