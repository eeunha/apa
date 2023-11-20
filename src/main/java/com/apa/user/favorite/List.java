package com.apa.user.favorite;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.apa.model.FavoriteDTO;
import com.apa.model.UserDTO;
import com.apa.repository.UserDAO;
/**
 * 서블릿 입니다.
 * 
 * 회원 마이페이지 -> 즐겨찾기 병원 -> 즐겨찾기 목록 조회
 * 
 */
@WebServlet("/user/favorite/list.do")
public class List extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");
		
		UserDAO dao = new UserDAO();
		
		UserDTO dto = dao.get(seq);
		
		ArrayList<FavoriteDTO> list = dao.getFavorite(seq); 
		
		req.setAttribute("list", list);
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/favorite/list.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");

		UserDAO dao = new UserDAO();
		
		int result = dao.delFavor(seq);
		
		JSONObject obj = new JSONObject();
		obj.put("result", result);
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = resp.getWriter();
		writer.print(obj.toString());
		writer.close();
	}
}
