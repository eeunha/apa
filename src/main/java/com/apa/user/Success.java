package com.apa.user;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/success.do")
public class Success extends HttpServlet {
    //Success.java
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher
                = req.getRequestDispatcher("/WEB-INF/views/user/success.jsp");
        dispatcher.forward(req, resp);
    }
}
