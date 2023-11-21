package com.apa.find;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 이재용
 * 왕진 안내 페이지로 포워딩하는 서블릿 클래스
 */
@WebServlet("/find/visit.do")
public class Visit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // 왕진 안내 페이지로 포워딩
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/find/visit.jsp");
        dispatcher.forward(req, resp);
    }
}
