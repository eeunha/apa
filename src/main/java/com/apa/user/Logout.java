package com.apa.user;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/logout.do")
public class Logout extends HttpServlet {
    /**
     * GET 방식으로 "/user/logout.do" 주소로 요청이 들어올 때 호출되는 메소드입니다.
     * 사용자의 세션에서 로그인 정보를 삭제하고 메인 페이지로 리다이렉트합니다.
     *
     * @param req  HttpServletRequest 객체로 클라이언트의 요청 정보를 받습니다.
     * @param resp HttpServletResponse 객체로 클라이언트에게 응답을 보냅니다.
     * @throws ServletException Servlet 예외가 발생할 경우 던져집니다.
     * @throws IOException      입출력 예외가 발생할 경우 던져집니다.
     */
    //Logout.java
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 세션에서 로그인 정보 삭제
        req.getSession().removeAttribute("id");
        req.getSession().removeAttribute("pw");
        req.getSession().removeAttribute("name");

        // 메인 페이지로 리다이렉트
        resp.sendRedirect("/apa/main.do");

    }
}
