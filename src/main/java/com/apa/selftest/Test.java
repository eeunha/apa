package com.apa.selftest;

import com.apa.model.TestDTO;
import com.apa.repository.TestDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/selftest/test.do")
public class Test extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String seq = req.getParameter("seq");
		TestDAO dao = new TestDAO();

		ArrayList<TestDTO> list = dao.questionList(seq);

		req.setAttribute("seq", seq);
		req.setAttribute("list", list);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/self-test/test.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=utf-8");

		int sum = 0;

		String seq = req.getParameter("seq");

		if("1".equals(req.getParameter("seq"))){
			for (int i = 1; i<=10; i++) {
				sum += Integer.parseInt(req.getParameter("q" + i));
			}
			if (sum >= 0 && sum <=13){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			} else if(sum > 13 && sum <= 18){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 18 && sum <= 40){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}



		}else if("2".equals(req.getParameter("seq"))){
			for (int i = 1; i<=7; i++) {
				sum += Integer.parseInt(req.getParameter("q"+i));
			}
			if(sum >= 0 && sum <= 4){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 4 && sum <= 9){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 9  && sum <= 14){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 14 && sum <= 21){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}

		}else if("3".equals(req.getParameter("seq"))){
			for (int i = 1; i<=20; i++) {
				sum += Integer.parseInt(req.getParameter("q"+i));
			}
			if(sum >= 0 && sum <= 19){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 19 && sum <= 31){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 31 && sum <= 40){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 40 && sum <= 60) {
				resp.sendRedirect("/apa/selftest/result.do?sum=" + sum + "&seq=" + seq);
			}

		}else if("4".equals(req.getParameter("seq"))){
			for (int i = 1; i<=13; i++) {
				sum += Integer.parseInt(req.getParameter("q"+i));
			}

			if(sum >= 0 && sum <= 4){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 4 && sum <= 13){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}

		}else if("5".equals(req.getParameter("seq"))){
			for (int i = 1; i<=10; i++) {
				sum += Integer.parseInt(req.getParameter("q"+i));
			}
			if(sum >= 0 && sum <= 7){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 7 && sum <= 14){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 14 && sum <= 20){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}
		}else if("6".equals(req.getParameter("seq"))){
			for (int i = 1; i<=7; i++) {
				sum += Integer.parseInt(req.getParameter("q"+i));
			}
			if(sum >= 0 && sum <= 7){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 7 && sum <= 14){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 14 && sum <= 20){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}
		}else if("7".equals(req.getParameter("seq"))){
			for (int i = 1; i<=8; i++) {
				sum += Integer.parseInt(req.getParameter("q"+i));
			}
			if(sum >= 0 && sum <= 7){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 7 && sum <= 14){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 14 && sum <= 20){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}
		}else if("8".equals(req.getParameter("seq"))){
			for (int i = 1; i<=10; i++) {
				sum += Integer.parseInt(req.getParameter("q"+i));
			}
			if(sum >= 0 && sum <= 7){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 7 && sum <= 14){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 14 && sum <= 20){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}
		}


	}
}
