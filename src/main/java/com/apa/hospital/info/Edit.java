package com.apa.hospital.info;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.HospitalMyPageInfoDTO;
import com.apa.model.UserDTO;
import com.apa.repository.HospitalMyPageDAO;
import com.apa.repository.UserDAO;

@WebServlet("/hospital/info/edit.do")
public class Edit extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
req.setCharacterEncoding("UTF-8");
		
		//0.
		String id = req.getParameter("id");
		
		//1.
		HospitalMyPageDAO dao = new HospitalMyPageDAO();
		
		int result = dao.delete(id);
		
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
		String seq = req.getParameter("seq");
		
		String name = req.getParameter("name");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		String ssn1 = req.getParameter("ssn1");
		String ssn2 = req.getParameter("ssn2");
		
		String ssn = ssn1 + "-" + ssn2;
		
		String tel1 = req.getParameter("tel1");
		String tel2 = req.getParameter("tel2");
		String tel3 = req.getParameter("tel3");

		String tel = tel1 + "-" + tel2 + "-" + tel3;
		
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		
		//2.
		HospitalMyPageDAO dao = new HospitalMyPageDAO();
		
		HospitalMyPageInfoDTO dto = new HospitalMyPageInfoDTO();
		dto.setHospitalId(id);
		dto.setHospitalName(name);
		dto.setHospitalPw(pw);
		dto.setHospitalSsn(ssn);
		dto.setHospitalTel(tel);
		dto.setHospitalEmail(email);
		dto.setHospitalAddress(address);
		
		int result = dao.edit(dto);
		
		//3.
		if (result == 1) {
		    resp.setContentType("text/html; charset=UTF-8");
		    PrintWriter writer = resp.getWriter();
		    writer.println("<script>");
		    writer.println("alert('정상적으로 수정하였습니다.');");
		    writer.println("location.href='/apa/hospital/info/view.do?id=" + id + "';"); // 리디렉션
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
