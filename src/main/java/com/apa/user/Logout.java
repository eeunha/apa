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
    //Logout.java
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().removeAttribute("id");
        req.getSession().removeAttribute("pw");
        req.getSession().removeAttribute("name");

        req.getSession().invalidate();
        resp.sendRedirect("/apa/main.do");

    }
}
