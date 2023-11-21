package com.apa.selftest;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 자가진단 목록을 표시하는 서블릿 클래스입니다.
 * 이 서블릿은 자가진단 목록 화면을 표시하기 위한 기능을 제공합니다.
 * 사용자가 "/selftest/list.do" 주소로 접근하면 자가진단 목록 페이지를 표시합니다.
 * 해당 서블릿은 HTTP GET 요청에 응답합니다.
 *
 * @author 신수정
 * @version 1.0
 * @since 2023-11-20
 */
@WebServlet("/selftest/list.do")
public class List extends HttpServlet {

    /**
     * 자가진단 목록 화면을 표시하는 HTTP GET 요청을 처리합니다.
     *
     * @param req  클라이언트의 요청을 포함하는 HttpServletRequest 객체입니다.
     * @param resp 클라이언트에게 응답을 보내기 위한 HttpServletResponse 객체입니다.
     * @throws ServletException 서블릿의 정상 작동을 방해하는 예외가 발생할 경우입니다.
     * @throws IOException      입출력 예외가 발생할 경우입니다.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 자가진단 목록 페이지로 포워딩
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/self-test/list.jsp");
        dispatcher.forward(req, resp);
    }
}
