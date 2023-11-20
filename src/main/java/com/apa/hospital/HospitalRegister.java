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

@WebServlet("/hospital/hospitalregister.do")
public class HospitalRegister extends HttpServlet {
    //HospitalRegister.java
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher
                = req.getRequestDispatcher("/WEB-INF/views/hospital/hospitalregister.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try{

            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=utf-8");

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

            LoginHospitalDTO dto = new LoginHospitalDTO();
            LoginHospitalDAO dao = new LoginHospitalDAO();

            dto.setHospitalId(hospitalId);
            dto.setHospitalPw(hospitalPw);
            dto.setHospitalName(hospitalName);
            dto.setHospitalSsn(hospitalSsn);
            dto.setHospitalTel(hospitalTel);
            dto.setHospitalEmail(hospitalEmail);
            dto.setHospitalAddress(hospitalAddress);
            dto.setDepartmentSeq(departmentSeq);

            LoginHospitalDTO hospital = dao.getHospital(dto);

            if(hospital != null && dto.getHospitalId().equals(hospital.getHospitalId())){
                PrintWriter writer = resp.getWriter();
                writer.print("<script>alert('중복된 아이디 입니다.');history.back();</script>");
                writer.close();
                return;
            }


            int HospitalResult = dao.HospitalRegister(dto);

            if(HospitalResult == 1){
                resp.sendRedirect("/apa/hospital/hospitalsuccess.do");
            }
        } catch (Exception e) {
            System.out.println("HospitalRegister.doPost()");
            e.printStackTrace();
        }
        //0또는 에러
        PrintWriter writer = resp.getWriter();
        writer.print("<script>alert('failed');history.back();</script>");
        writer.close();
    }
}

