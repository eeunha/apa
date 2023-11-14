package com.apa.hospital.diagnosis;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.hospital.DiagnosisRgstDTO;
import com.apa.repository.hospital.DiagnosisDAO;

@WebServlet("/hospital/diagnosis/history-view.do")
public class HistoryView extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String mediSeq = req.getParameter("mediSeq");

		DiagnosisDAO dao = new DiagnosisDAO();

		DiagnosisRgstDTO dto = dao.getAllRegisterDetail(mediSeq);

		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/hospital/diagnosis/history-view.jsp");
		dispatcher.forward(req, resp);
	}
}
