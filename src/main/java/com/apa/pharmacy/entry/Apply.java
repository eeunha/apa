package com.apa.pharmacy.entry;

import java.io.IOException;
import java.io.PrintWriter;

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
 * 입점 신청서를 처리하는 서블릿
 * GET 요청 처리
 * @param req HTTP 요청 객체
 * @param resp HTTP 응답 객체
 * @throws ServletException 서블릿 예외
 * @throws IOException 입출력 예외
 */
@WebServlet("/pharmacy/entry/apply.do")
public class Apply extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 세션에서 약국 ID 가져오기
        HttpSession session = req.getSession();
        String pharmacyId = session.getAttribute("id").toString();

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
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/pharmacy/entry/apply.jsp");
        dispatcher.forward(req, resp);
    }
    
    /**
     * 
     * POST 요청 처리
     * @param req HTTP 요청 객체
     * @param resp HTTP 응답 객체
     * @throws ServletException 서블릿 예외
     * @throws IOException 입출력 예외
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 세션에서 약국 ID 가져오기
        HttpSession session = req.getSession();
        String pharmacyId = session.getAttribute("id").toString();

        // 세션에 약국 ID가 없는 경우, 파라미터에서 가져오기
        if (pharmacyId == null || "".equals(pharmacyId)) {
            pharmacyId = req.getParameter("pharmacyId");
        }
        req.setCharacterEncoding("UTF-8");
        
        // entrySeq 가져오기
        String entrySeq = req.getParameter("entrySeq");
   
        // PharmacyDAO 인스턴스 생성
        PharmacyDAO dao = new PharmacyDAO();
      
        // PharmacyDTO 인스턴스 생성 및 값 설정
        PharmacyDTO dto = new PharmacyDTO();
        dto.setPharmacyId(pharmacyId);
        dto.setEntrySeq(entrySeq);
      
        // entryRegister 및 entryRegisterDate 메서드를 이용하여 데이터베이스에 등록
        int entryRegister = dao.entryRegister(dto);
        int entryRegisterDate = dao.entryRegisterDate(dto, entrySeq);

        // 성공적으로 등록된 경우, 세션에 성공 메시지 설정 후 목록 페이지로 리다이렉트
        if (entryRegister == 1 && entryRegisterDate == 1) {
            req.getSession().setAttribute("insertSuccess", "true");
            resp.sendRedirect("/apa/pharmacy/entry/list.do?pharmacyId=" + pharmacyId);
        } else {
            // 실패한 경우, 경고창을 띄우고 이전 페이지로 이동
            PrintWriter writer = resp.getWriter();
            writer.print("<script>alert('failed');history.back();</script>");
            writer.close();
        }
    }    
}
