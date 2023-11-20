package com.apa.user;

import com.apa.model.LoginAdminDTO;
import com.apa.model.LoginHospitalDTO;
import com.apa.model.LoginPharmacyDTO;
import com.apa.model.LoginUserDTO;
import com.apa.repository.LoginAdminDAO;
import com.apa.repository.LoginHospitalDAO;
import com.apa.repository.LoginPharmacyDAO;
import com.apa.repository.LoginUserDAO;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/login.do")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
        String checked = req.getParameter("checked");
        System.out.println(checked);
        String id = req.getParameter("id");
        String pw = req.getParameter("pw");


        if (checked.equals("1")) {
            // 일반 사용자 로그인
            LoginUserDAO userDAO = new LoginUserDAO();
            LoginUserDTO userDTO = new LoginUserDTO();

            userDTO.setUserId(id);
            userDTO.setUserPw(pw);

            LoginUserDTO userResult = userDAO.login(userDTO);

            // 관리자 로그인
            LoginAdminDAO adminDAO = new LoginAdminDAO();
            LoginAdminDTO adminDTO = new LoginAdminDTO();

            adminDTO.setAdminId(id);
            adminDTO.setAdminPw(pw);

            LoginAdminDTO adminResult = adminDAO.login(adminDTO);

            if (userResult != null) {
                // 일반 사용자 로그인 성공
                req.getSession().setAttribute("seq", userResult.getUserSeq());
                req.getSession().setAttribute("id", id);
                req.getSession().setAttribute("pw", pw);
                req.getSession().setAttribute("name", userResult.getUserName());
                req.getSession().setAttribute("lv", "1");

                resp.sendRedirect("/apa/main.do");
            } else if (adminResult != null) {
                // 관리자 로그인 성공
                req.getSession().setAttribute("id", id);
                req.getSession().setAttribute("pw", pw);
                req.getSession().setAttribute("lv", "0");

                resp.sendRedirect("/apa/main.do"); // 관리자 페이지 이동
            } else {
                // 로그인 실패
                PrintWriter writer = resp.getWriter();
                writer.print("<script>alert('아이디/비밀번호가 일치하지 않습니다.');history.back();</script>");
                writer.close();
            }


        } else if (checked.equals("2")) {

            LoginHospitalDAO dao = new LoginHospitalDAO();
            LoginHospitalDTO dto = new LoginHospitalDTO();

            dto.setHospitalId(id);
            dto.setHospitalPw(pw);

            LoginHospitalDTO result = dao.login(dto);

            if (result != null) {
                req.getSession().setAttribute("id", id);
                req.getSession().setAttribute("pw", pw);
                req.getSession().setAttribute("name", result.getHospitalName());
                req.getSession().setAttribute("lv", "2");

                resp.sendRedirect("/apa/main.do");

            } else {
                PrintWriter writer = resp.getWriter();
                writer.print("<script>alert('아이디/비밀번호가 일치하지 않습니다.');history.back();</script>");
                writer.close();

            }

        } else if (checked.equals("3")) {
            LoginPharmacyDAO dao = new LoginPharmacyDAO();
            LoginPharmacyDTO dto = new LoginPharmacyDTO();

            dto.setPharmacyId(id);
            dto.setPharmacyPw(pw);

            LoginPharmacyDTO result = dao.login(dto);

            if (result != null) {
                req.getSession().setAttribute("id", id);
                req.getSession().setAttribute("pw", pw);
                req.getSession().setAttribute("name", result.getPharmacyName());
                req.getSession().setAttribute("lv", "3");

                resp.sendRedirect("/apa/main.do");

            } else {
                PrintWriter writer = resp.getWriter();
                writer.print("<script>alert('아이디/비밀번호가 일치하지 않습니다.');history.back();</script>");
                writer.close();

            }
//		} else if(checked == null) { //관리자 DAO, DTO 만들기
//			LoginAdminDAO dao = new LoginAdminDAO();
//			LoginAdminDTO dto = new LoginAdminDTO();
//
//			dto.setAdminId(id);
//			dto.setAdminPw(pw);
//
//			LoginAdminDTO result = dao.login(dto);
//
//			if(result != null){
//				req.getSession().setAttribute("id", id);
//				req.getSession().setAttribute("pw", pw);
//				req.getSession().setAttribute("lv", "4");
//
//				resp.sendRedirect("/apa/main.do");//관리자 페이지 이동
//
//			}else{
//				PrintWriter writer = resp.getWriter();
//				writer.print("<script>alert('아이디/비밀번호가 일치하지 않습니다.');history.back();</script>");
//				writer.close();
//
//			}
        }


    }
}

