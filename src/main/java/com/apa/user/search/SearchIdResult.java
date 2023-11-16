package com.apa.user.search;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/search/searchidresult.do")
public class SearchIdResult extends HttpServlet {
    //SearchIdResult.java
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher
                = req.getRequestDispatcher("/WEB-INF/views/user/search/searchidresult.jsp");
        dispatcher.forward(req, resp);
    }
}
