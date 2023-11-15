package com.apa.hospital.diagnosis;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.hospital.DgnsMediHistoryDTO;
import com.apa.repository.hospital.DiagnosisDAO;

@WebServlet("/hospital/diagnosis/write-diagnosis.do")
public class WriteDiagnosis extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String mediSeq = req.getParameter("mediSeq");
		
		DiagnosisDAO dao = new DiagnosisDAO();
		
		String doctorName = dao.getDoctorName(mediSeq);
		
		req.setAttribute("mediSeq", mediSeq);
		req.setAttribute("doctorName", doctorName);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/hospital/diagnosis/write-diagnosis.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String mediSeq = req.getParameter("mediSeq");
		String mediName = req.getParameter("mediName");
		String mediCode = req.getParameter("mediCode");
		String mediContent = req.getParameter("mediContent");
		
		DiagnosisDAO dao = new DiagnosisDAO();
		
		//진료내역서 작성 insert

		DgnsMediHistoryDTO dto = new DgnsMediHistoryDTO();
		dto.setMediName(mediName);
		dto.setMediCode(mediCode);
		dto.setMediContent(mediContent);
		dto.setMediSeq(mediSeq);
		
		int insertResult = dao.addMediHistory(dto);
		
		if (insertResult != 1) {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('failed');history.back();</script>");
			writer.close();
		}
		
		
		//진료대기환자 수정 update
		int updateResult = dao.updateWaitingStatus(mediSeq);
		
		PrintWriter writer = resp.getWriter();
		if (updateResult == 1) {
			
			resp.sendRedirect("/apa/hospital/diagnosis/list.do");
		} else {
			writer.print("<script>alert('failed');history.back();</script>");
		}
		writer.close();
	}
}
