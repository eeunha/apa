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
    /**
     * GET 방식으로 "/user/login.do" 주소로 요청이 들어올 때 호출되는 메소드입니다.
     * 사용자가 로그인 페이지로 이동할 수 있도록 해당 페이지로 포워딩합니다.
     *
     * @param req  HttpServletRequest 객체로 클라이언트의 요청 정보를 받습니다.
     * @param resp HttpServletResponse 객체로 클라이언트에게 응답을 보냅니다.
     * @throws ServletException Servlet 예외가 발생할 경우 던져집니다.
     * @throws IOException      입출력 예외가 발생할 경우 던져집니다.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 로그인 페이지로 이동
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/login.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     * POST 방식으로 "/user/login.do" 주소로 요청이 들어올 때 호출되는 메소드입니다.
     * 사용자가 입력한 정보로 로그인을 처리하고 성공시 해당 사용자의 정보를 세션에 저장하여 메인 페이지로 리다이렉트합니다.
     * 로그인 실패시 적절한 경고창을 띄워주고 이전 페이지로 이동합니다.
     *
     * @param req  HttpServletRequest 객체로 클라이언트의 요청 정보를 받습니다.
     * @param resp HttpServletResponse 객체로 클라이언트에게 응답을 보냅니다.
     * @throws ServletException Servlet 예외가 발생할 경우 던져집니다.
     * @throws IOException      입출력 예외가 발생할 경우 던져집니다.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        // 사용자가 선택한 유형에 따라 로그인 처리
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
            //병원 로그인 처리
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
            //약국 로그인 처리
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

        }


    }
}

