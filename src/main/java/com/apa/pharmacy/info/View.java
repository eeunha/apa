package com.apa.pharmacy.info;

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
 * 약국 정보를 확인하는 서블릿
 * GET 요청 처리 
 * @param req HTTP 요청 객체
 * @param resp HTTP 응답 객체
 * @throws ServletException 서블릿 예외
 * @throws IOException 입출력 예외
 */
@WebServlet("/pharmacy/info/view.do")
public class View extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String pharmacyId = session.getAttribute("id").toString();
        
        System.out.println(pharmacyId);
        if(pharmacyId == null || "".equals(pharmacyId)) {
            pharmacyId = req.getParameter("pharmacyId");
        }
        
        //2. DAO를 사용하여 해당 약국 ID에 대한 정보를 가져옵니다.
        PharmacyDAO dao = new PharmacyDAO();
        PharmacyDTO dto = dao.get(pharmacyId); // 회원 정보
        
        // 주민등록번호와 전화번호를 나누어 설정합니다.
        String[] ssnArr = dto.getPharmacySSN().split("-");
        dto.setPharmacySSNs(ssnArr[0]);
        dto.setPharmacySSNm(ssnArr[1]);
        dto.setPharmacySSNe(ssnArr[2]);
        
        String[] telArr = dto.getPharmacyTel().split("-");
        dto.setPharmacyTels(telArr[0]);
        dto.setPharmacyTelm(telArr[1]);
        dto.setPharmacyTele(telArr[2]);
        
        //3. 결과를 request 객체에 저장하고 view 페이지로 포워딩합니다.
        req.setAttribute("dto", dto);
        req.setAttribute("maskingSSN", ssnArr[2].charAt(0) + "******");
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/pharmacy/info/view.jsp");
        dispatcher.forward(req, resp);
    }
}
