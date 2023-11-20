package com.apa.user;

import com.apa.model.LoginUserDTO;
import com.apa.repository.LoginUserDAO;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/userregister.do")
public class UserRegister extends HttpServlet {


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/userregister.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		try{

			req.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html; charset=utf-8");

			String userId = req.getParameter("id");
			String userPw = req.getParameter("pw");
			String userName = req.getParameter("name");
			String ssn1 = req.getParameter("ssn1");
			String ssn2 = req.getParameter("ssn2");
			String userSsn = ssn1 + "-" + ssn2;

			String tel1 = req.getParameter("tel1");
			String tel2 = req.getParameter("tel2");
			String tel3 = req.getParameter("tel3");

			String userTel = tel1 + "-" + tel2 + "-" + tel3;
			String userEmail = req.getParameter("email");
			String userAddress = req.getParameter("address");

			LoginUserDTO dto = new LoginUserDTO();
			LoginUserDAO dao = new LoginUserDAO();

			dto.setUserId(userId);
			dto.setUserPw(userPw);
			dto.setUserName(userName);
			dto.setUserSsn(userSsn);
			dto.setUserTel(userTel);
			dto.setUserEmail(userEmail);
			dto.setUserAddress(userAddress);

			LoginUserDTO user = dao.getUser(dto);

			if(user != null && dto.getUserId().equals(user.getUserId())){
				PrintWriter writer = resp.getWriter();
				writer.print("<script>alert('중복된 아이디 입니다.');history.back();</script>");
				writer.close();
				return;
			}

			int usersResult = dao.userRegister(dto);

			if(usersResult == 1){
				resp.sendRedirect("/apa/user/success.do");
			}
		} catch (Exception e) {
			System.out.println("UserRegister.doPost()");
			e.printStackTrace();
		}
		//0또는 에러
		PrintWriter writer = resp.getWriter();
		writer.print("<script>alert('failed');history.back();</script>");
		writer.close();
	}
}

