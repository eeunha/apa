package com.apa.pharmacy.opening;

import java.io.IOException;
import java.io.PrintWriter;

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
 * 약국의 운영 정보를 추가하는 기능을 처리하는 서블릿.
 */
@WebServlet("/pharmacy/opening/add.do")
public class Add extends HttpServlet {

    /**
     * 약국의 운영정보를 가져오기 위한 GET 요청을 처리합니다.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 세션 또는 URL 매개변수에서 약국 ID를 가져옵니다.
        HttpSession session = req.getSession();
        String pharmacyId = session.getAttribute("id").toString();

        if (pharmacyId == null || "".equals(pharmacyId)) {
            pharmacyId = req.getParameter("pharmacyId");
        }
        
        // OpenDAO를 사용하여 운영정보를 가져옵니다.
        OpenDAO openDAO = new OpenDAO();
        OpenDTO openDTO = openDAO.getOpenInfo(pharmacyId);

        // 가져온 정보를 요청 객체에 설정하고 JSP 페이지로 전달합니다.
        req.setAttribute("dto", openDTO);
        req.setAttribute("pharmacyId", pharmacyId);
        req.getRequestDispatcher("/WEB-INF/views/pharmacy/opening/add.jsp").forward(req, resp);
    }

    /**
     * 약국의 새로운 운영정보를 추가하기 위한 POST 요청을 처리합니다.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 세션 또는 URL 매개변수에서 약국 ID를 가져옵니다.
        HttpSession session = req.getSession();
        String pharmacyId = session.getAttribute("id").toString();

        if (pharmacyId == null || "".equals(pharmacyId)) {
            pharmacyId = req.getParameter("pharmacyId");
        }
        req.setCharacterEncoding("UTF-8");
        
        // 데이터를 가져옵니다.
        String openTime = req.getParameter("openTime");
        String closeTime = req.getParameter("closeTime");
        String isPharmarcyNightCare = req.getParameter("isPharmarcyNightCare");
        String isPharmarcyHoliday = req.getParameter("isPharmarcyHoliday");
        String pharmacyDayOff = req.getParameter("pharmacyDayOff");

        // OpenDTO 객체에 데이터를 설정합니다.
        OpenDAO dao = new OpenDAO();
        OpenDTO dto = new OpenDTO();
        dto.setPharmacyId(pharmacyId);
        dto.setOpenTime(openTime);
        dto.setCloseTime(closeTime);
        dto.setIsPharmarcyNightCare(isPharmarcyNightCare);
        dto.setIsPharmarcyHoliday(isPharmarcyHoliday);
        dto.setPharmacyDayOff(pharmacyDayOff);
        
        // 데이터베이스에 정보를 추가합니다.
        int resultTimeInfo = dao.addTimeInfo(dto);
        int resultIsPharmarcy = dao.addIsPharmarcy(dto);
        int resultDayOff = dao.addDayOff(dto);

        // 추가가 성공하면 보기 페이지로 리디렉션합니다. 실패하면 에러 메시지를 표시하고 이전 페이지로 이동합니다.
        if (resultTimeInfo == 1 && resultIsPharmarcy == 1 && resultDayOff == 1) {
            req.getSession().setAttribute("updateSuccess", "true");
            resp.sendRedirect("/apa/pharmacy/opening/view.do?pharmacyId=" + pharmacyId );
        } else {
            PrintWriter writer = resp.getWriter();
            writer.print("<script>alert('failed');history.back();</script>");
            writer.close();
        }
    }    
}
