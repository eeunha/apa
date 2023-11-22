package com.apa.pharmacy.info;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.pharmacy.model.PharmacyDTO;
import com.apa.pharmacy.repository.PharmacyDAO;

/**
 * @author 김민정 
 * 약국 회원 탈퇴를 처리하는 서블릿
 * GET 요청 처리 
 * @param req HTTP 요청 객체
 * @param resp HTTP 응답 객체
 * @throws ServletException 서블릿 예외
 * @throws IOException 입출력 예외
 */
@WebServlet("/pharmacy/info/edit.do")
public class Edit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        //0. 약국 ID 파라미터를 가져옵니다.
        String pharmacyId = req.getParameter("pharmacyId");
    
        // 1. DAO 객체를 생성하여 해당 약국 ID에 대한 정보를 삭제합니다.
        PharmacyDAO dao = new PharmacyDAO();
        int result = dao.delete(pharmacyId);
    
        // 2. 결과에 따라 성공 또는 실패 메시지를 출력하고, 페이지를 이동합니다.
        if(result == 1) {
            resp.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.println("<script>");
            writer.println("alert('정상적으로 탈퇴하였습니다.');");
            writer.println("location.href='/apa/main.do';"); // 리디렉션
            writer.println("</script>");
            writer.close();
        } else {
            resp.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.println("<script>");
            writer.println("alert('탈퇴를 실패하였습니다.');");
            writer.println("history.back();");
            writer.println("</script>");
            writer.close();
        }
    }
    
    /**
     * POST 요청 처리 - 약국 정보 수정
     * @param req HTTP 요청 객체
     * @param resp HTTP 응답 객체
     * @throws ServletException 서블릿 예외
     * @throws IOException 입출력 예외
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        //0. 현재 비밀번호 입력값과 약국 ID를 파라미터로부터 가져옵니다.
        String inputCurrentPw = req.getParameter("inputCurrentPw");
        String currentPw = req.getParameter("currentPw");
        
        // 현재 비밀번호와 입력한 비밀번호가 일치하지 않으면 경고 메시지를 출력하고 이전 페이지로 이동합니다.
        if (!inputCurrentPw.equals(currentPw)) {
            resp.setContentType("text/html; charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("<script>");
            out.println("alert('현재 비밀번호가 일치하지 않습니다.');");
            out.println("history.back();"); // 이전 페이지로 돌아갑니다.
            out.println("</script>");
            out.close();
            return;
        }
        
        //1. 약국 정보를 파라미터로부터 받아옵니다.
        String seq = req.getParameter("seq");
        String name = req.getParameter("name");
        String id = req.getParameter("id");
        String pw = req.getParameter("pw");
        String ssn1 = req.getParameter("ssn1");
        String ssn2 = req.getParameter("ssn2");
        String ssn3 = req.getParameter("ssn3");
        String ssn = ssn1 + "-" + ssn2+ "-" + ssn3;
        String tel1 = req.getParameter("tel1");
        String tel2 = req.getParameter("tel2");
        String tel3 = req.getParameter("tel3");
        String tel = tel1 + "-" + tel2 + "-" + tel3;
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        
        //2. DAO 객체를 생성하여 약국 정보를 수정합니다.
        PharmacyDAO dao = new PharmacyDAO();
        PharmacyDTO dto = new PharmacyDTO();		
        dto.setPharmacyName(name);
        dto.setPharmacyId(id);
        dto.setPharmacyPw(pw);
        dto.setPharmacySSN(ssn);
        dto.setPharmacyTel(tel);
        dto.setPharmacyEmail(email);
        dto.setPharmacyAddress(address);
        int result = dao.edit(dto);
        
        //3. 수정 결과에 따라 메시지를 출력하고 페이지를 이동합니다.
        if (result == 1) {
            resp.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.println("<script>");
            writer.println("alert('정상적으로 수정하였습니다.');");
            writer.println("location.href='/apa/pharmacy/info/view.do?seq=" + seq + "';"); // 리디렉션
            writer.println("</script>");
            writer.close();
        } else {
            resp.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.println("<script>");
            writer.println("alert('수정에 실패하였습니다.');");
            writer.println("history.back();");
            writer.println("</script>");
            writer.close();
        }
    }
}
