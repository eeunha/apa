package com.apa.pharmacy.entry;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apa.pharmacy.model.PharmacyDTO;
import com.apa.pharmacy.repository.PharmacyDAO;

/**
 * @author 김민정 
 * 입점신청을 위한 약국 정보를 처리하는 서블릿
 * GET 요청 처리
 * @param req HTTP 요청 객체
 * @param resp HTTP 응답 객체
 * @throws ServletException 서블릿 예외
 * @throws IOException 입출력 예외
 */
@WebServlet("/pharmacy/entry/list.do")
public class List extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 세션에서 약국 ID 가져오기
        HttpSession session = req.getSession();
        String pharmacyId = session.getAttribute("id").toString();
        System.out.println(pharmacyId);

        // 세션에 약국 ID가 없는 경우, 파라미터에서 가져오기
        if (pharmacyId == null || "".equals(pharmacyId)) {
            pharmacyId = req.getParameter("pharmacyId");
        }
        // PharmacyDAO 인스턴스 생성
        PharmacyDAO dao = new PharmacyDAO();

        // 약국 정보 가져오기
        PharmacyDTO dto = dao.getEntryInfo(pharmacyId);

        // 요청에 약국 정보 및 ID 속성 추가
        req.setAttribute("dto", dto);
        req.setAttribute("pharmacyId", pharmacyId);
        
        // JSP로 포워딩
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/pharmacy/entry/list.jsp");
        dispatcher.forward(req, resp);
    }
}
