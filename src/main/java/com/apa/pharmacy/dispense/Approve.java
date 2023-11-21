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
 * 제조 승인을 위한 서블릿 
 */
@WebServlet("/pharmacy/dispense/approve.do")
public class Approve extends HttpServlet {
    
    /**
     * POST 요청 처리
     * @param req HTTP 요청 객체
     * @param resp HTTP 응답 객체
     * @throws ServletException 서블릿 예외
     * @throws IOException 입출력 예외
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 요청 매개변수에서 dispenseIds를 가져옴
        String[] dispenseIds = req.getParameterValues("dispenseIds");

        // dispenseIds가 존재하고 비어있지 않은지 확인
        if (dispenseIds != null && dispenseIds.length > 0) {
            // 데이터베이스와 상호작용하기 위해 DispenseDAO 초기화
            DispenseDAO dispenseDAO = new DispenseDAO();
            
            // 각 dispenseId를 루프하며 제조 상태 업데이트
            for (String dispenseId : dispenseIds) {
                dispenseDAO.updateDispensing(dispenseId);
            }

            // 업데이트가 수행된 경우 성공 메시지 전송
            resp.getWriter().write("Success");
        } else {
            // 선택된 dispenseId가 없는 경우 메시지 전송
            resp.getWriter().write("NoSelection");
        }
    }
}
