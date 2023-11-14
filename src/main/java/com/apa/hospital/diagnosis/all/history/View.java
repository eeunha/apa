package com.apa.hospital.diagnosis.all.history;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.hospital.DiagnosisRgstDTO;
import com.apa.repository.hospital.DiagnosisDAO;

@WebServlet("/hospital/diagnosis/all/history/view.do")
public class View extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String mediSeq = req.getParameter("mediSeq");

		DiagnosisDAO dao = new DiagnosisDAO();

		//예약정보
		DiagnosisRgstDTO dto = dao.getOrderDetail(mediSeq);
		
//		DiagnosisWriteDTO writeDTO = dao.getWriteDiagnosis(mediSeq);

		req.setAttribute("dto", dto); //예약정보
//		req.setAttribute("writeDto", writeDto); //진료내역서 dto

		System.out.println("childSeq: " + dto.getChildSeq());

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/views/hospital/diagnosis/all/history/view.jsp");
		dispatcher.forward(req, resp);
	}
}
