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
	/**
	 * GET 방식으로 "/user/userregister.do" 주소로 요청이 들어올 때 호출되는 메소드입니다.
	 * 사용자 등록 페이지로 포워딩합니다.
	 *
	 * @param req  HttpServletRequest 객체로 클라이언트의 요청 정보를 받습니다.
	 * @param resp HttpServletResponse 객체로 클라이언트에게 응답을 보냅니다.
	 * @throws ServletException Servlet 예외가 발생할 경우 던져집니다.
	 * @throws IOException      입출력 예외가 발생할 경우 던져집니다.
	 */

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/userregister.jsp");
		dispatcher.forward(req, resp);
	}

	/**
	 * POST 방식으로 "/user/userregister.do" 주소로 요청이 들어올 때 호출되는 메소드입니다.
	 * 사용자의 입력 정보를 받아 회원가입을 처리하고 결과에 따라 페이지를 리다이렉트합니다.
	 *
	 * @param req  HttpServletRequest 객체로 클라이언트의 요청 정보를 받습니다.
	 * @param resp HttpServletResponse 객체로 클라이언트에게 응답을 보냅니다.
	 * @throws ServletException Servlet 예외가 발생할 경우 던져집니다.
	 * @throws IOException      입출력 예외가 발생할 경우 던져집니다.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		try{

			req.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html; charset=utf-8");

			// 사용자가 입력한 정보 가져오기
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

			// 사용자 정보 객체 생성
			LoginUserDTO dto = new LoginUserDTO();
			LoginUserDAO dao = new LoginUserDAO();

			dto.setUserId(userId);
			dto.setUserPw(userPw);
			dto.setUserName(userName);
			dto.setUserSsn(userSsn);
			dto.setUserTel(userTel);
			dto.setUserEmail(userEmail);
			dto.setUserAddress(userAddress);

			// 중복된 아이디 체크
			LoginUserDTO user = dao.getUser(dto);

			if(user != null && dto.getUserId().equals(user.getUserId())){
				PrintWriter writer = resp.getWriter();
				writer.print("<script>alert('중복된 아이디 입니다.');history.back();</script>");
				writer.close();
				return;
			}

			// 사용자 등록
			int usersResult = dao.userRegister(dto);

			if(usersResult == 1){
				resp.sendRedirect("/apa/user/login.do");
			}
		} catch (Exception e) {
			System.out.println("UserRegister.doPost()");
			e.printStackTrace();
		}
		// 회원가입 실패
		PrintWriter writer = resp.getWriter();
		writer.print("<script>alert('failed');history.back();</script>");
		writer.close();
	}
}

