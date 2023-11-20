package com.apa.user.info;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.UserDTO;
import com.apa.repository.UserDAO;
/**
 * 서블릿 입니다.
 * 
 * 회원 마이페이지 -> 내 정보 -> 내 정보 조회
 * 
 */
@WebServlet("/user/info/view.do")
public class View extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");
		
		//1.
		//String id = req.getSession().getAttribute("id").toString();
		
		//2.
		UserDAO dao = new UserDAO();
		
		UserDTO dto = dao.get(seq); //회원 정보
		
		String[] ssnArr = dto.getUserSsn().split("-");
		
		dto.setUserSSNs(ssnArr[0]);
		dto.setUserSSNe(ssnArr[1]);
		
		String[] telArr = dto.getUserTel().split("-");
		
		dto.setUserTels(telArr[0]);
		dto.setUserTelm(telArr[1]);
		dto.setUserTele(telArr[2]);
		
		String maskingSSN = ssnArr[1].charAt(0) + "******";
		
		//3.
		req.setAttribute("dto", dto);
		req.setAttribute("maskingSSN", maskingSSN);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/info/view.jsp");
		dispatcher.forward(req, resp);

	}

}