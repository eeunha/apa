package com.apa.pharmacy.opening;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apa.pharmacy.model.OpenDTO;
import com.apa.pharmacy.repository.OpenDAO;


/**
 * @author 김민정 
 * 약국 운영정보를 확인하는 서블릿
 *
 */
@WebServlet("/pharmacy/opening/view.do")
public class View extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 세션에서 약국 ID를 가져옵니다.
		HttpSession session = req.getSession();
		String pharmacyId = session.getAttribute("id").toString();

		if (pharmacyId == null || "".equals(pharmacyId)) {
			pharmacyId = req.getParameter("pharmacyId");
		}
		// OpenDAO를 사용하여 해당 약국의 오픈 정보를 가져옵니다.
		OpenDAO openDAO = new OpenDAO();
		OpenDTO openDTO = openDAO.getOpenInfo(pharmacyId);

		// 가져온 정보를 요청 객체에 설정하고 JSP 페이지로 전달합니다.
		req.setAttribute("dto", openDTO);
		req.setAttribute("pharmacyId", pharmacyId);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/pharmacy/opening/view.jsp");
		dispatcher.forward(req, resp);
	}
}
