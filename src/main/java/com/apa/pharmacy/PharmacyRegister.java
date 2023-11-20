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

@WebServlet("/pharmacy/pharmacyregister.do")
public class PharmacyRegister extends HttpServlet {
    //PharmacyRegister.java
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher
                = req.getRequestDispatcher("/WEB-INF/views/pharmacy/pharmacyregister.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{

            resp.setContentType("text/html; charset=utf-8");

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


            LoginPharmacyDTO dto = new LoginPharmacyDTO();
            LoginPharmacyDAO dao = new LoginPharmacyDAO();

            dto.setPharmacyId(pharmacyId);
            dto.setPharmacyPw(pharmacyPw);
            dto.setPharmacyName(pharmacyName);
            dto.setPharmacySsn(pharmacySsn);
            dto.setPharmacyTel(pharmacyTel);
            dto.setPharmacyEmail(pharmacyEmail);
            dto.setPharmacyAddress(pharmacyAddress);



            LoginPharmacyDTO pharmacy = dao.getPharmacy(dto);

            if(pharmacy != null && dto.getPharmacyId().equals(pharmacy.getPharmacyId())){
                PrintWriter writer = resp.getWriter();
                writer.print("<script>alert('중복된 아이디 입니다.');history.back();</script>");
                writer.close();
                return;
            }


            int HospitalResult = dao.PharmacyRegister(dto);

            if(HospitalResult == 1){
                resp.sendRedirect("/apa/pharmacy/pharmacysuccess.do");
            }
        } catch (Exception e) {
            System.out.println("pharmacyRegister.doPost()");
            e.printStackTrace();
        }
        //0또는 에러
        PrintWriter writer = resp.getWriter();
        writer.print("<script>alert('failed');history.back();</script>");
        writer.close();
    }
}



