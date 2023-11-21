package com.apa.pharmacy;

import com.apa.model.LoginPharmacyDTO;
import com.apa.repository.LoginPharmacyDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 약국 등록을 처리하는 서블릿 클래스입니다.
 * 이 서블릿은 등록 양식을 표시하고 제출된 양식 데이터를 처리하여 약국을 등록하는 기능을 제공합니다.
 *
 * @author 신수정
 * @version 1.0
 * @since 2023-11-20
 */
@WebServlet("/pharmacy/pharmacyregister.do")
public class PharmacyRegister extends HttpServlet {

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
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/pharmacy/pharmacyregister.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     * 약국 등록 양식 제출을 처리하기 위한 HTTP POST 요청을 처리합니다.
     *
     * @param req  클라이언트의 요청을 포함하는 HttpServletRequest 객체입니다.
     * @param resp 클라이언트에게 응답을 보내기 위한 HttpServletResponse 객체입니다.
     * @throws ServletException 서블릿의 정상 작동을 방해하는 예외가 발생할 경우입니다.
     * @throws IOException      입출력 예외가 발생할 경우입니다.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 콘텐츠 타입 설정
            resp.setContentType("text/html; charset=utf-8");

            // 요청 매개변수에서 양식 데이터 검색
            String pharmacyId = req.getParameter("id");
            String pharmacyPw = req.getParameter("pw");
            String pharmacyName = req.getParameter("name");
            String ssn1 = req.getParameter("ssn1");
            String ssn2 = req.getParameter("ssn2");
            String pharmacySsn = ssn1 + "-" + ssn2;

            String tel1 = req.getParameter("tel1");
            String tel2 = req.getParameter("tel2");
            String tel3 = req.getParameter("tel3");
            String pharmacyTel = tel1 + "-" + tel2 + "-" + tel3;

            String pharmacyEmail = req.getParameter("email");
            String pharmacyAddress = req.getParameter("address");

            // 약국 등록을 위한 DTO 및 DAO 객체 생성
            LoginPharmacyDTO dto = new LoginPharmacyDTO();
            LoginPharmacyDAO dao = new LoginPharmacyDAO();

            // 양식 데이터로 DTO 속성 설정
            dto.setPharmacyId(pharmacyId);
            dto.setPharmacyPw(pharmacyPw);
            dto.setPharmacyName(pharmacyName);
            dto.setPharmacySsn(pharmacySsn);
            dto.setPharmacyTel(pharmacyTel);
            dto.setPharmacyEmail(pharmacyEmail);
            dto.setPharmacyAddress(pharmacyAddress);

            // 중복 약국 ID 확인
            LoginPharmacyDTO pharmacy = dao.getPharmacy(dto);
            if (pharmacy != null && dto.getPharmacyId().equals(pharmacy.getPharmacyId())) {
                PrintWriter writer = resp.getWriter();
                writer.print("<script>alert('중복된 아이디 입니다.');history.back();</script>");
                writer.close();
                return;
            }

            // 약국 등록 처리 결과 확인 및 리다이렉션
            int pharmacyResult = dao.PharmacyRegister(dto);
            if (pharmacyResult == 1) {
                resp.sendRedirect("/apa/main.do");
            }
        } catch (Exception e) {
            System.out.println("pharmacyRegister.doPost()");
            e.printStackTrace();
        }
        // 0 또는 에러 발생 시 처리
        PrintWriter writer = resp.getWriter();
        writer.print("<script>alert('failed');history.back();</script>");
        writer.close();
    }
}
