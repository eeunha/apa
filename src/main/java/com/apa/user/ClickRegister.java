package com.apa.user;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/clickregister.do")
public class ClickRegister extends HttpServlet {
    /**
     * GET 방식으로 "/user/clickregister.do" 주소로 요청이 들어올 때 호출되는 메소드입니다.
     * 사용자가 클릭하여 회원가입 페이지로 이동할 수 있도록 해당 페이지로 포워딩합니다.
     *
     * @param req  HttpServletRequest 객체로 클라이언트의 요청 정보를 받습니다.
     * @param resp HttpServletResponse 객체로 클라이언트에게 응답을 보냅니다.
     * @throws ServletException Servlet 예외가 발생할 경우 던져집니다.
     * @throws IOException      입출력 예외가 발생할 경우 던져집니다.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher
                = req.getRequestDispatcher("/WEB-INF/views/user/clickregister.jsp");
        dispatcher.forward(req, resp);
    }
}
