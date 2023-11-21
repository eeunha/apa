package com.apa.find;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.HospitalInfoDTO;
import com.apa.model.SelfsymtomDTO;
import com.apa.repository.MainDAO;

/**
 * @author 이재용
 * 증상선택에 따른 병원 목록을 검색하여 출력하는 서블릿 클래스
 */
@WebServlet("/find/list.do")
public class List extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // MainDAO 객체 생성
        MainDAO dao = new MainDAO();

        // 증상 목록을 가져옴
        ArrayList<SelfsymtomDTO> symtomlist = dao.symtomlist();

        // 병원 정보 목록을 가져옴
        ArrayList<HospitalInfoDTO> hosdto = dao.hospitallistmain();

        // 요청 속성으로 증상 및 병원 목록을 설정
        req.setAttribute("symtomlist", symtomlist);
        req.setAttribute("hosdto", hosdto);

        // 뷰로 포워드
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/find/list.jsp");
        dispatcher.forward(req, resp);
    }
}