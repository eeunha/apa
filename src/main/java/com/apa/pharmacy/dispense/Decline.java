package com.apa.pharmacy.dispense;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.pharmacy.repository.DispenseDAO;

/**
 * @author 김민정
 * 제조를 거부하는 서블릿
 */
@WebServlet("/pharmacy/dispense/decline.do")
public class Decline extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 선택된 dispenseIds 가져오기
            String[] selectedDispenseIds = req.getParameterValues("dispenseIds");

            DispenseDAO dispenseDAO = new DispenseDAO();
            // 선택된 dispenseIds에 대해 거부 업데이트 수행
            boolean success = dispenseDAO.updateDispenseDecline(selectedDispenseIds);

            if (success) {
                resp.getWriter().write("Success"); // 성공 메시지 전송
            } else {
                resp.getWriter().write("NoSelection"); // 선택된 것이 없는 경우 메시지 전송
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "서버에서 오류가 발생했습니다."); // 서버 오류 메시지 전송
        }
    }
}
