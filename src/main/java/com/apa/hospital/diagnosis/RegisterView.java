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

/**
 * 병원 - 내 진료 기능 중 오늘의 진료의 예약 상세 페이지를 담당하는 클래스입니다.
 * @author Eunha
 *
 */
@WebServlet("/hospital/diagnosis/register-view.do")
public class RegisterView extends HttpServlet {
	
	/**
	 * 예약 상세 페이지를 불러오는 메소드입니다.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String mediSeq = req.getParameter("mediSeq");

		DiagnosisDAO dao = new DiagnosisDAO();

		DiagnosisRgstDTO dto = dao.getRegisterDetail(mediSeq);

		if (dto.getSymptom() != null) {
			// 개행문자처리
			String symptom = dto.getSymptom();
			symptom = symptom.replace("\r\n", "<br>");
			dto.setSymptom(symptom);
		}

		req.setAttribute("dto", dto);
		req.setAttribute("mediSeq", mediSeq);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/hospital/diagnosis/register-view.jsp");
		dispatcher.forward(req, resp);
	}
}
