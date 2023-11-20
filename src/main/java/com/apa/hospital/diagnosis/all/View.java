<<<<<<<< HEAD:src/main/java/com/apa/hospital/advice/Pick.java
package com.apa.hospital.advice;
========
package com.apa.hospital.diagnosis.all;
>>>>>>>> 60d7dc7293d0081493b63ba1cbb00222c69b54cc:src/main/java/com/apa/hospital/diagnosis/all/View.java

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hospital/advice/pick.do")
public class Pick extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/hospital/advice/pick.jsp");
		dispatcher.forward(req, resp);
	}
}
