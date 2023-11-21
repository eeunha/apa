package com.apa.user.search;

import com.apa.model.LoginHospitalDTO;
import com.apa.model.LoginPharmacyDTO;
import com.apa.model.LoginUserDTO;
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

/**
 * 사용자, 병원, 약국 아이디 찾기를 처리하는 서블릿 클래스입니다.
 * 이 서블릿은 사용자가 "/user/search/id.do" 주소로 접근하면 아이디 찾기 화면을 표시하고,
 * HTTP POST 요청을 통해 입력된 정보를 검증하여 결과 페이지로 이동합니다.
 * 검증된 정보에 따라 각 유형의 아이디를 세션에 저장하고 결과 페이지로 이동합니다.
 * 해당 서블릿은 HTTP GET 요청에 응답하여 아이디 찾기 화면을 표시하고,
 * HTTP POST 요청에 응답하여 입력된 정보를 검증하고 결과 페이지로 이동합니다.
 *
 * @author 신수정
 * @version 1.0
 * @since 2023-11-20
 */
@WebServlet("/user/search/id.do")
public class Id extends HttpServlet {
    /**
     * 아이디 찾기 화면을 표시하는 HTTP GET 요청을 처리합니다.
     *
     * @param req  클라이언트의 요청을 포함하는 HttpServletRequest 객체입니다.
     * @param resp 클라이언트에게 응답을 보내기 위한 HttpServletResponse 객체입니다.
     * @throws ServletException 서블릿의 정상 작동을 방해하는 예외가 발생할 경우입니다.
     * @throws IOException      입출력 예외가 발생할 경우입니다.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 아이디 찾기 화면으로 포워딩
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/search/id.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     * 입력된 정보를 검증하고 아이디 찾기 결과 페이지로 이동하는 HTTP POST 요청을 처리합니다.
     *
     * @param req  클라이언트의 요청을 포함하는 HttpServletRequest 객체입니다.
     * @param resp 클라이언트에게 응답을 보내기 위한 HttpServletResponse 객체입니다.
     * @throws ServletException 서블릿의 정상 작동을 방해하는 예외가 발생할 경우입니다.
     * @throws IOException      입출력 예외가 발생할 경우입니다.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 인코딩 설정 및 콘텐츠 타입 설정
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");

        // 폼에서 입력된 정보 수신
        String checked = req.getParameter("checked");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String tel1 = req.getParameter("tel1");
        String tel2 = req.getParameter("tel2");
        String tel3 = req.getParameter("tel3");
        String tel = tel1 + "-" + tel2 + "-" + tel3;

        // 각 유형별로 아이디를 찾아 세션에 저장하고 결과 페이지로 이동
        if (checked.equals("1")) {
            LoginUserDAO dao = new LoginUserDAO();
            LoginUserDTO dto = new LoginUserDTO();

            dto.setUserName(name);
            dto.setUserEmail(email);
            dto.setUserTel(tel);

            LoginUserDTO result = dao.searchId(dto);

            if (result != null) {
                // 아이디를 세션에 저장하고 결과 페이지로 이동
                req.getSession().setAttribute("seq", result.getUserSeq());
                req.getSession().setAttribute("name", name);
                req.getSession().setAttribute("tel", tel);
                req.getSession().setAttribute("lv", "1");
                req.getSession().setAttribute("email", email);
                req.getSession().setAttribute("id", result.getUserId());

                resp.sendRedirect("/apa/user/search/searchidresult.do");
            } else {
                // 검증 실패 시 메시지를 출력하고 이전 페이지로 이동
                PrintWriter writer = resp.getWriter();
                writer.print("<script>alert('올바른 정보를 입력해주세요.');history.back();</script>");
                writer.close();
            }
            } else if (checked.equals("2")) {

                LoginHospitalDAO dao = new LoginHospitalDAO();
                LoginHospitalDTO dto = new LoginHospitalDTO();

                dto.setHospitalName(name);
                dto.setHospitalEmail(email);
                dto.setHospitalTel(tel);

                LoginHospitalDTO result = dao.searchId(dto);


                if (result != null) {
                    req.getSession().setAttribute("name", name);
                    req.getSession().setAttribute("tel", tel);
                    req.getSession().setAttribute("lv", "2");
                    req.getSession().setAttribute("email", email);
                    req.getSession().setAttribute("id", result.getHospitalId());
;
                    resp.sendRedirect("/apa/user/search/searchidresult.do");

                } else {
                    PrintWriter writer = resp.getWriter();
                    writer.print("<script>alert('올바른 정보를 입력해주세요.');history.back();</script>");
                    writer.close();

                }

            } else if (checked.equals("3")) {
                LoginPharmacyDAO dao = new LoginPharmacyDAO();
                LoginPharmacyDTO dto = new LoginPharmacyDTO();

                dto.setPharmacyName(name);
                dto.setPharmacyEmail(email);
                dto.setPharmacyTel(tel);

                LoginPharmacyDTO result = dao.searchId(dto);

                if (result != null) {
                    req.getSession().setAttribute("name", name);
                    req.getSession().setAttribute("id", result.getPharmacyId());
                    req.getSession().setAttribute("tel", tel);
                    req.getSession().setAttribute("lv", "3");
                    req.getSession().setAttribute("email", email);

                    resp.sendRedirect("/apa/user/search/searchidresult.do");

                } else {
                    PrintWriter writer = resp.getWriter();
                    writer.print("<script>alert('올바른 정보를 입력해주세요.');history.back();</script>");
                    writer.close();




            }
        }
    }
}