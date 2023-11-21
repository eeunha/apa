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
 * 제조 완료된 약을 수령완료 처리 하는 서블릿
 */
@WebServlet("/pharmacy/dispense/pickup.do")
public class Pickup extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 세션에서 약국 ID를 가져옴
        HttpSession session = req.getSession();
        String pharmacyId = session.getAttribute("id").toString();

        // 약국 ID가 없으면 요청 파라미터에서 가져옴
        if (pharmacyId == null || "".equals(pharmacyId)) {
            pharmacyId = req.getParameter("pharmacyId");
        }

        // DispenseDAO 인스턴스 생성
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
        HashMap<String, String> map = new HashMap<String, String>();

        begin = ((nowPage - 1) * pageSize) + 1;
        end = begin + pageSize - 1;

        map.put("begin", begin + "");
        map.put("end", end + "");
        map.put("pharmacyId", pharmacyId);

        // 수령 완료목록을 페이지 별로 가져옴
        ArrayList<DispenseDTO> dtoList = dispenseDAO.getDispensePickUpPagingList(map);

        req.setAttribute("dtoList", dtoList);

        // 제조 상태 건수를 가져옴
        DispenseCntDTO cntDto = dispenseDAO.getCountByStatus(pharmacyId);
        req.setAttribute("cntDto", cntDto);

        int totalPage = 0;    // 총 페이지 수
        totalPage = (int) Math.ceil((double) Integer.parseInt(cntDto.getSurungFinCnt()) / pageSize);
        StringBuilder sb = new StringBuilder();

        int n = 0;
        int loop = 0;
        loop = 1;    // 루프변수 
        n = ((nowPage - 1) / pageSize) * pageSize + 1;
        
        //이전페이지
        if (n == 1) {
            sb.append(String.format(("<a href='#!'><<<</a>"), pageSize));
        } else {
            sb.append(String.format(("<a href='/apa/pharmacy/dispense/pickup.do?page=%d'><<<</a>"), n - 1, pageSize));
        }

        while (!(loop > pageSize || n > totalPage)) {

            if (n == nowPage) {
                sb.append(String.format(" <a href='#!' style='color:tomato;'>%d</a> ", n));
            } else {
                sb.append(String.format(" <a href='/apa/pharmacy/dispense/pickup.do?page=%d'>%d</a> ", n, n));
            }

            loop++;
            n++;
        }

        //다음페이지
        if (n > totalPage) {
            sb.append(String.format(("<a href='#!'>>>></a>"), pageSize));
        } else {
            sb.append(String.format(("<a href='/apa/pharmacy/dispense/pickup.do?page=%d'>>>></a>"), n, pageSize));
        }

        req.setAttribute("pagebar", sb.toString());
        req.setAttribute("pharmacyId", pharmacyId);

        // JSP 페이지로 포워드
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/pharmacy/dispense/pickup.jsp");
        dispatcher.forward(req, resp);
    }
}
