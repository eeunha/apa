package com.apa.user.children;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.apa.repository.UserDAO;
/**
 * 서블릿 입니다.
 * 
 * 회원 마이페이지 -> 자녀 관리 -> 자녀 정보 수정
 * 
 */
@WebServlet("/user/children/edit.do")
public class Edit extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/children/edit.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("name");
		//String ssn = req.getParameter("ssn");
		String tel = req.getParameter("tel");
		String child = req.getParameter("child");
		String childSeq = req.getParameter("childSeq");
		String seq = req.getParameter("seq");
		
		UserDAO dao = new UserDAO();
		
		HashMap<String,String> map = new HashMap<String,String>();
		
		map.put("name", name);
		//map.put("ssn", ssn);
		map.put("tel", tel);
		map.put("child", child);
		map.put("childSeq", childSeq);
		map.put("seq", seq);
		
		int result = dao.editChild(map);
		
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
