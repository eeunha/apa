package com.apa.selftest;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/selftest/result.do")
public class Result extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//점수랑 문제번호 받고
		String seq = req.getParameter("seq");
		String sum = req.getParameter("sum");

		req.setAttribute("seq", seq);
		req.setAttribute("sum", sum);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/self-test/result.jsp");
		dispatcher.forward(req, resp);
	}
}

