package com.apa.hospital.diagnosis.all.register;

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
 * 병원 - 내 진료 기능 중 모든 진료 예약의 상세 페이지를 담당하는 클래스입니다.
 * @author Eunha
 *
 */
@WebServlet("/hospital/diagnosis/all/register/view.do")
public class View extends HttpServlet {
	
	/**
	 * 모든 진료 예약의 상세 페이지를 불러오는 메소드입니다.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String mediSeq = req.getParameter("mediSeq");

		DiagnosisDAO dao = new DiagnosisDAO();

		DiagnosisRgstDTO dto = dao.getAllRegisterDetail(mediSeq);

		if (dto.getSymptom() != null) {
			// 개행문자처리
			String symptom = dto.getSymptom();
			symptom = symptom.replace("\r\n", "<br>");
			dto.setSymptom(symptom);
		}

		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/views/hospital/diagnosis/all/register/view.jsp");
		dispatcher.forward(req, resp);
	}
}
