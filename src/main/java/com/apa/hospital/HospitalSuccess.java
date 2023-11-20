package com.apa.hospital;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/hospital/hospitalsuccess.do")
public class HospitalSuccess extends HttpServlet {
    //HospitalSuccess.java
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher
                = req.getRequestDispatcher("/WEB-INF/views/hospital/hospitalsuccess.jsp");
        dispatcher.forward(req, resp);
    }
}
