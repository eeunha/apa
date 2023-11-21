package com.apa.user.info;

import java.io.IOException;
import java.io.PrintWriter;

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
 * 회원 마이페이지 -> 내 정보 -> 내 정보 수정 및 탈퇴
 * 
 */
@WebServlet("/user/info/edit.do")
public class Edit extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		//0.
		String seq = req.getParameter("seq");
		
		//1.
		UserDAO dao = new UserDAO();
		
		int result = dao.delete(seq);
		
		//2.
		if (result == 1) {
		    resp.setContentType("text/html; charset=UTF-8");
		    PrintWriter writer = resp.getWriter();
		    writer.println("<script>");
		    writer.println("alert('정상적으로 탈퇴하였습니다.');");
		    writer.println("location.href='/apa/main.do';"); // 리디렉션
		    writer.println("</script>");
		    writer.close();
		} else {
		    resp.setContentType("text/html; charset=UTF-8");
		    PrintWriter writer = resp.getWriter();
		    writer.println("<script>");
		    writer.println("alert('탈퇴를 실패하였습니다.');");
		    writer.println("history.back();");
		    writer.println("</script>");
		    writer.close();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		//0.
		String inputCurrentPw = req.getParameter("inputCurrentPw");
		String currentPw = req.getParameter("currentPw");
		
		if (!inputCurrentPw.equals(currentPw)) {
		    resp.setContentType("text/html; charset=UTF-8");
		    PrintWriter out = resp.getWriter();
		    out.println("<script>");
		    out.println("alert('현재 비밀번호가 일치하지 않습니다.');");
		    out.println("history.back();"); // 이전 페이지로 돌아갑니다.
		    out.println("</script>");
		    out.close();
		    return;
		}
		
		//1.
		int seq = Integer.parseInt(req.getParameter("seq"));
		
		String name = req.getParameter("name");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		String ssn1 = req.getParameter("ssn1");
		String ssn2 = req.getParameter("ssn2");
		
		System.out.println(ssn2);
		
		String ssn = ssn1 + "-" + ssn2;
		
		String tel1 = req.getParameter("tel1");
		String tel2 = req.getParameter("tel2");
		String tel3 = req.getParameter("tel3");

		String tel = tel1 + "-" + tel2 + "-" + tel3;
		
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		
		//2.
		UserDAO dao = new UserDAO();
		
		UserDTO dto = new UserDTO();
		dto.setUserSeq(seq);
		dto.setUserName(name);
		dto.setUserId(id);
		dto.setUserPw(pw);
		dto.setUserSsn(ssn);
		dto.setUserTel(tel);
		dto.setUserEmail(email);
		dto.setUserAddress(address);
		
		int result = dao.edit(dto);
		
		//3.
		if (result == 1) {
		    resp.setContentType("text/html; charset=UTF-8");
		    PrintWriter writer = resp.getWriter();
		    writer.println("<script>");
		    writer.println("alert('정상적으로 수정하였습니다.');");
		    writer.println("location.href='/apa/user/info/view.do?seq=" + seq + "';"); // 리디렉션
		    writer.println("</script>");
		    writer.close();
		} else {
		    resp.setContentType("text/html; charset=UTF-8");
		    PrintWriter writer = resp.getWriter();
		    writer.println("<script>");
		    writer.println("alert('수정에 실패하였습니다.);");
		    writer.println("history.back();");
		    writer.println("</script>");
		    writer.close();
		}
	}
}
