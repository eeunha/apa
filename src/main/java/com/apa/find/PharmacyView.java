package com.apa.find;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.PharmacyInfoDTO;
import com.apa.repository.MainDAO;

/**
 * @author 이재용
 * 약국 상세 정보를 가져와서 뷰로 전달하는 서블릿 클래스
 */
@WebServlet("/find/pharmacyview.do")
public class PharmacyView extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // 요청 파라미터에서 약국 ID를 가져옴
        String seq = req.getParameter("id");
        
        // MainDAO 객체 생성
        MainDAO dao = new MainDAO();

        // 응답 인코딩 설정
        resp.setCharacterEncoding("UTF-8");

        // 약국 상세 정보를 가져옴
        PharmacyInfoDTO dto = dao.pharmacyInfo(seq);
        
        // 요청 속성으로 약국 상세 정보를 설정
        req.setAttribute("dto", dto);

        // 뷰로 포워드
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/find/pharmacyview.jsp");
        dispatcher.forward(req, resp);
    }
}
