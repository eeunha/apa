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

/**
 * @author 이혜진
 * 관리자 정보를 조회하고 해당 정보를 JSP 페이지로 전달하는 서블릿 클래스
 */
@WebServlet("/admin/info/view.do")
public class View extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1. 요청 매개변수에서 adminId를 가져오기
        String adminId = req.getParameter("adminId"); // req.getAttribute 대신 req.getParameter 사용

        //2. AdminInfoDAO의 인스턴스를 생성
        AdminInfoDAO dao = new AdminInfoDAO();

        // adminId를 기반으로 AdminInfoDTO를 검색
        AdminInfoDTO dto = dao.get(adminId);

        //System.out.println(dto);

        // 3. JSP 페이지에서 사용할 수 있도록 검색한 DTO를 요청 속성으로 설정
        req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/admin/info/view.jsp");
		dispatcher.forward(req, resp);
	}
}

