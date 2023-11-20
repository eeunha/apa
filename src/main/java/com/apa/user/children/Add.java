package com.apa.user.children;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.apa.model.ChildrenDTO;
import com.apa.repository.UserDAO;
/**
 * 서블릿 입니다.
 * 
 * 회원 마이페이지 -> 자녀 관리 -> 자녀 등록
 * 
 */
@WebServlet("/user/children/add.do")
public class Add extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/children/add.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		//1.
		String name = req.getParameter("name");
		
		String ssn1 = req.getParameter("ssn1");
		String ssn2 = req.getParameter("ssn2");
		
		String ssn = ssn1 + "-" + ssn2;
		
		String tel1 = req.getParameter("tel1");
		String tel2 = req.getParameter("tel2");
		String tel3 = req.getParameter("tel3");
		
		String tel = tel1 + "-" + tel2 + "-" + tel3;
		
		String parent = req.getParameter("parent");
		String seq = req.getParameter("seq");
		
		//2.
		UserDAO dao = new UserDAO();
		
		ChildrenDTO dto = new ChildrenDTO();
		dto.setChildName(name);
		dto.setChildSSN(ssn);
		dto.setChildTel(tel);
		dto.setUserSeq(seq);
		dto.setUserChild(parent);
		
		int result = dao.childInsert(dto);

		if (parent != null) {			
			result = dao.userChildUpdate(dto);
		}
		
		//3.
		JSONObject obj = new JSONObject();
		obj.put("result", result);
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = resp.getWriter();
		writer.print(obj.toString());
		writer.close();
		
	}
}
