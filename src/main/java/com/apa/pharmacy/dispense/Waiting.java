package com.apa.pharmacy.dispense;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apa.pharmacy.model.DispenseCntDTO;
import com.apa.pharmacy.model.DispenseDTO;
import com.apa.pharmacy.repository.DispenseDAO;

/**
 * @author 김민정
 * 제조 대기중인 목록을 처리하는 서블릿
 * 대기 중인 Dispense 객체 목록을 가져와서 화면에 표시하는 GET 요청 처리
 * @param req HTTP 요청 객체
 * @param resp HTTP 응답 객체
 * @throws ServletException 서블릿 예외
 * @throws IOException 입출력 예외
 */
@WebServlet("/pharmacy/dispense/waiting.do")
public class Waiting extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        HttpSession session = req.getSession();
        String pharmacyId = session.getAttribute("id").toString();
        System.out.println(pharmacyId);
        
        if(pharmacyId == null || "".equals(pharmacyId)) {
            pharmacyId = req.getParameter("pharmacyId");
        }
        
        // DispenseDAO 객체 생성
        DispenseDAO dispenseDAO = new DispenseDAO();
        
        int nowPage = 0;    // 현재 페이지 번호
        int begin = 0;      // 페이지 시작 위치
        int end = 0;        // 페이지 끝 위치
        int pageSize = 10;  // 한 페이지에서 출력할 게시물 수
        
        String page = req.getParameter("page");
        
        if (page == null || page.equals("")) {
            nowPage = 1;
        } else {
            nowPage = Integer.parseInt(page);
        }
        HashMap<String,String> map = new HashMap<String,String>();
        
        begin = ((nowPage - 1) * pageSize) + 1;
        end = begin + pageSize - 1;
        
        
        map.put("begin", begin + "");
        map.put("end", end + "");
        map.put("pharmacyId", pharmacyId);
        
        // 제조 대기 중인 Dispense 객체 목록을 가져옴
        ArrayList<DispenseDTO> dtoList = dispenseDAO.getDispensesWaitingPagingList(map);
        
        req.setAttribute("dtoList", dtoList);
        
        // 제조 상태 건수 가지고오기
        DispenseCntDTO cntDto = dispenseDAO.getCountByStatus(pharmacyId);
        req.setAttribute("cntDto", cntDto);
        
        int totalPage = 0;  // 총 페이지수
        if (cntDto.getWatingCnt() == null) {
            
        } else {            
            totalPage = (int)Math.ceil((double)Integer.parseInt(cntDto.getWatingCnt()) / pageSize);
        }
        StringBuilder sb = new StringBuilder();
        
        int n = 0;
        int loop = 0;
        loop = 1;   // 루프변수 (10바퀴)
        n=((nowPage -1)/pageSize) * pageSize + 1;
        
        // 페이지 링크 생성
        //이전페이지
        if(n == 1) {
            sb.append(String.format(("<a href='#!'><<<</a>"), pageSize));
        } else {
            
            sb.append(String.format(("<a href='/apa/pharmacy/dispense/waiting.do?page=%d'><<<</a>"), n-1, pageSize));
            
        }
        
        while(!(loop > pageSize || n > totalPage)) {
            
            if (n == nowPage) {
                sb.append(String.format(" <a href='#!' style='color:tomato;'>%d</a> ", n));
            } else {
                sb.append(String.format(" <a href='/apa/pharmacy/dispense/waiting.do?page=%d'>%d</a> ", n, n));
            }
            
            loop++;
            n++;
        }
        
        //다음페이지
        if(n > totalPage) {
            sb.append(String.format(("<a href='#!'>>>></a>"), pageSize));
        } else {
            sb.append(String.format(("<a href='/apa/pharmacy/dispense/waiting.do?page=%d'>>>></a>"), n, pageSize));
        }
        
        req.setAttribute("pagebar", sb.toString());
        req.setAttribute("pharmacyId", pharmacyId);
    
        // JSP 페이지로 포워드
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/pharmacy/dispense/waiting.jsp");
        dispatcher.forward(req, resp);
    }
}
