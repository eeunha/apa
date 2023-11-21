package com.apa.find;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.PharmacyInfoDTO;
import com.apa.repository.UserDAO;

/**
 * @author 이재용
 * 약국 목록을 검색하여 뷰로 전달하는 서블릿 클래스
 */
@WebServlet("/find/pharmacylist.do")
public class PharmacyList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // UserDAO 객체 생성
        UserDAO dao = new UserDAO();

        // 약국 목록을 가져옴
        ArrayList<PharmacyInfoDTO> list = dao.pharmacylist();

        // 요청 속성으로 약국 목록을 설정
        req.setAttribute("list", list);

        // 뷰로 포워드
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/find/pharmacylist.jsp");
        dispatcher.forward(req, resp);
    }
}
