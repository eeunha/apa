package com.apa.hospital.diagnosis;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/apa/hospital/diagnosis/write.do") // 가상주소 바로 생성
public class Write extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1.
		String mediSeq = req.getParameter("mediSeq");
		System.out.println(mediSeq);

		// 2.
		req.setAttribute("mediSeq", mediSeq); // 전달용

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/apa/hospital/diagnosis/write.jsp");
		dispatcher.forward(req, resp);

	}
}
