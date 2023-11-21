package com.apa.hospital;

import com.apa.model.LoginHospitalDTO;
import com.apa.repository.LoginHospitalDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 병원 등록을 처리하는 서블릿 클래스입니다.
 * 이 서블릿은 등록 양식을 표시하고 제출된 양식 데이터를 처리하여 병원을 등록하는 기능을 제공합니다.
 *
 * @author 신수정
 * @version 1.0
 * @since 2023-11-20
 */
@WebServlet("/hospital/hospitalregister.do")
public class HospitalRegister extends HttpServlet {

    /**
     * 등록 양식을 표시하기 위한 HTTP GET 요청을 처리합니다.
     *
     * @param req  클라이언트의 요청을 포함하는 HttpServletRequest 객체입니다.
     * @param resp 클라이언트에게 응답을 보내기 위한 HttpServletResponse 객체입니다.
     * @throws ServletException 서블릿의 정상 작동을 방해하는 예외가 발생할 경우입니다.
     * @throws IOException      입출력 예외가 발생할 경우입니다.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/hospital/hospitalregister.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     * 병원 등록 양식 제출을 처리하기 위한 HTTP POST 요청을 처리합니다.
     *
     * @param req  클라이언트의 요청을 포함하는 HttpServletRequest 객체입니다.
     * @param resp 클라이언트에게 응답을 보내기 위한 HttpServletResponse 객체입니다.
     * @throws ServletException 서블릿의 정상 작동을 방해하는 예외가 발생할 경우입니다.
     * @throws IOException      입출력 예외가 발생할 경우입니다.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 양식 데이터를 올바르게 처리하기 위한 문자 인코딩 및 콘텐츠 타입 설정
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=utf-8");

            // 요청 매개변수에서 양식 데이터 검색
            String hospitalId = req.getParameter("id");
            String hospitalPw = req.getParameter("pw");
            String hospitalName = req.getParameter("name");
            String ssn1 = req.getParameter("ssn1");
            String ssn2 = req.getParameter("ssn2");
            String hospitalSsn = ssn1 + "-" + ssn2;

            String tel1 = req.getParameter("tel1");
            String tel2 = req.getParameter("tel2");
            String tel3 = req.getParameter("tel3");
            String hospitalTel = tel1 + "-" + tel2 + "-" + tel3;

            String hospitalEmail = req.getParameter("email");
            String hospitalAddress = req.getParameter("address");
            String departmentSeq = req.getParameter("department");

            // 병원 등록을 위한 DTO 및 DAO 객체 생성
            LoginHospitalDTO dto = new LoginHospitalDTO();
            LoginHospitalDAO dao = new LoginHospitalDAO();

            // 양식 데이터로 DTO 속성 설정
            dto.setHospitalId(hospitalId);
            dto.setHospitalPw(hospitalPw);
            dto.setHospitalName(hospitalName);
            dto.setHospitalSsn(hospitalSsn);
            dto.setHospitalTel(hospitalTel);
            dto.setHospitalEmail(hospitalEmail);
            dto.setHospitalAddress(hospitalAddress);
            dto.setDepartmentSeq(departmentSeq);

            // 중복 병원 ID 확인
            LoginHospitalDTO hospital = dao.getHospital(dto);
            if (hospital != null && dto.getHospitalId().equals(hospital.getHospitalId())) {
                PrintWriter writer = resp.getWriter();
                writer.print("<script>alert('중복된 아이디 입니다.');history.back();</script>");
                writer.close();
                return;
            }

            // 병원 등록 처리 결과 확인 및 리다이렉션
            int hospitalResult = dao.HospitalRegister(dto);
            if (hospitalResult == 1) {
                resp.sendRedirect("/apa/main.do");
            }
        } catch (Exception e) {
            System.out.println("HospitalRegister.doPost()");
            e.printStackTrace();
        }
        // 0 또는 에러 발생 시 처리
        PrintWriter writer = resp.getWriter();
        writer.print("<script>alert('failed');history.back();</script>");
        writer.close();
    }
}
