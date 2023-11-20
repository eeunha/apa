package com.apa.admin.info;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.AdminInfoDTO;
import com.apa.model.AdminUserDTO;
import com.apa.repository.AdminInfoDAO;
import com.apa.repository.AdminUserDAO;

@WebServlet("/admin/info/view.do")
public class View extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1.
        String adminId = req.getParameter("id"); // req.getAttribute 대신 req.getParameter 사용

        // 2.
        AdminInfoDAO dao = new AdminInfoDAO();

        AdminInfoDTO dto = dao.get(adminId);

        // 3.
        req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/info/view.jsp");
		dispatcher.forward(req, resp);
	}
}

