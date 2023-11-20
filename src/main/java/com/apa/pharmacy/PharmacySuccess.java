package com.apa.pharmacy;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pharmacy/pharmacysuccess.do")
public class PharmacySuccess extends HttpServlet {
    //PharmacySuccess.java
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher
                = req.getRequestDispatcher("/WEB-INF/views/pharmacy/pharmacysuccess.jsp");
        dispatcher.forward(req, resp);
    }
}