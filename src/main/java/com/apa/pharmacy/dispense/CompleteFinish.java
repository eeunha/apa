package com.apa.pharmacy.dispense;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apa.pharmacy.repository.DispenseDAO;

/**
 * @author 김민정
 * 제조 완료 처리를 마치는 서블릿
 */
@WebServlet("/pharmacy/dispense/completefinish.do")
public class CompleteFinish extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        // 세션에서 약국 ID 가져오기
        String pharmacyId = session.getAttribute("id").toString();
        
        // dispenseIds 가져오기
        String[] dispenseIds = req.getParameterValues("dispenseIds");

        if (dispenseIds != null && dispenseIds.length > 0) {
            DispenseDAO dispenseDAO = new DispenseDAO();

            // 선택된 dispenseIds를 반복하며 updatecompleteFinish 실행
            for (String dispenseId : dispenseIds) {
                dispenseDAO.updateCompleteFinish(dispenseId);
            }

            resp.getWriter().write("Success"); // 성공 메시지 전송
        } else {
            resp.getWriter().write("NoSelection"); // 선택된 것이 없는 경우 메시지 전송
        }
    } 
}
