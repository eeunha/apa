package com.apa.pharmacy.opening;

import java.io.IOException;
import java.io.PrintWriter;

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
 * 약국 운영정보를 수정하는 서블릿
 *
 */
@WebServlet("/pharmacy/opening/edit.do")
public class Edit extends HttpServlet {
	
	// GET 요청 처리 메서드
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 세션에서 약국 ID를 가져옵니다.
		HttpSession session = req.getSession();
		String pharmacyId = session.getAttribute("id").toString();
		System.out.println(pharmacyId);

		if (pharmacyId == null || "".equals(pharmacyId)) {
			pharmacyId = req.getParameter("pharmacyId");
		}
		// OpenDAO를 사용하여 해당 약국의 오픈 정보를 가져옵니다.
		OpenDAO openDAO = new OpenDAO();
		OpenDTO openDTO = openDAO.getOpenInfo(pharmacyId);

		// 가져온 정보를 요청 객체에 설정하고 JSP 페이지로 전달합니다.
		req.setAttribute("dto", openDTO);
		req.setAttribute("pharmacyId", pharmacyId);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/pharmacy/opening/edit.jsp");
		dispatcher.forward(req, resp);
	}

	// POST 요청 처리 메서드
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 세션에서 약국 ID를 가져옵니다.
		HttpSession session = req.getSession();
		String pharmacyId = session.getAttribute("id").toString();
		System.out.println(pharmacyId);

		if (pharmacyId == null || "".equals(pharmacyId)) {
			pharmacyId = req.getParameter("pharmacyId");
		}
		req.setCharacterEncoding("UTF-8");
    	
        // POST 요청 파라미터들을 가져옵니다.
        String openTime = req.getParameter("openTime");
        String closeTime = req.getParameter("closeTime");
        String isPharmarcyNightCare = req.getParameter("isPharmarcyNightCare");
        String isPharmarcyHoliday = req.getParameter("isPharmarcyHoliday");
        String pharmacyDayOff = req.getParameter("pharmacyDayOff");

        // OpenDAO 객체를 생성하여 약국의 오픈 정보를 업데이트합니다.
        OpenDAO dao = new OpenDAO();
        OpenDTO dto = new OpenDTO();
        dto.setPharmacyId(pharmacyId);
        dto.setOpenTime(openTime);
        dto.setCloseTime(closeTime);
        dto.setIsPharmarcyNightCare(isPharmarcyNightCare);
        dto.setIsPharmarcyHoliday(isPharmarcyHoliday);
        dto.setPharmacyDayOff(pharmacyDayOff);
        
        // 오픈 정보 업데이트를 수행하고 결과를 받아옵니다.
        int resultTimeInfo = dao.updateTimeInfo(dto);
        int resultIsPharmarcy = dao.updateIsPharmarcy(dto);
        int resultDayOff = dao.updateDayOff(dto);

        // 오픈 정보 업데이트 성공 여부를 확인하여 처리합니다.
        if (resultTimeInfo == 1 && resultIsPharmarcy == 1 && resultDayOff == 1) {
            // 성공 시 메시지를 설정하고 다른 페이지로 리다이렉션합니다.
            req.getSession().setAttribute("updateSuccess", "true");
            resp.sendRedirect("/apa/pharmacy/opening/view.do?pharmacyId=" + pharmacyId );
        } else {
            // 실패 시 에러 메시지를 표시하고 이전 페이지로 돌아갑니다.
            PrintWriter writer = resp.getWriter();
            writer.print("<script>alert('failed');history.back();</script>");
            writer.close();
        }
    }    
}
