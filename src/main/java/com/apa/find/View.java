package com.apa.find;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.HospitalDoctorDTO;
import com.apa.model.HospitalInfoDTO;
import com.apa.repository.MainDAO;
import com.apa.repository.ReservationDAO;

/**
 * @author 이재용
 * 병원 상세 정보 및 의사 목록을 가져와서 뷰로 전달하는 서블릿 클래스
 */
@WebServlet("/find/view.do")
public class View extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 요청 파라미터에서 병원 ID를 가져옴
        String seq = req.getParameter("id");

        // MainDAO 및 ReservationDAO 객체 생성
        MainDAO dao = new MainDAO();
        ReservationDAO dao2 = new ReservationDAO();

        // 응답 설정
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // 병원 상세 정보를 가져옴
        HospitalInfoDTO dto = dao.hospitalinfo(seq);

        // 시간 정보 일부를 추출하여 설정
        dto.setOpentime(dto.getOpentime().substring(11, 16));
        dto.setClosetime(dto.getClosetime().substring(11, 16));
        dto.setBreakopen(dto.getBreakopen().substring(11, 16));
        dto.setBreakclose(dto.getBreakclose().substring(11, 16));

        // 요청 속성으로 병원 상세 정보 설정
        req.setAttribute("dto", dto);

        // 해당 병원의 진료과목 목록을 가져옴
        ArrayList<HospitalInfoDTO> deptlist = dao.deptnameslist(seq);

        // 의사 목록을 저장할 리스트 및 반복문을 통해 각 진료과에 해당하는 의사 목록을 가져옴
        ArrayList<HospitalDoctorDTO> doclist = new ArrayList<>();
        for (HospitalInfoDTO docdto : deptlist) {
            doclist.addAll(dao2.doctorlist(seq, docdto.getDeptnames()));
        }

        // 요청 속성으로 의료진 및 진료과목 목록 설정
        req.setAttribute("doclist", doclist);
        req.setAttribute("deptlist", deptlist);

        // 뷰로 포워드
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/find/view.jsp");
        dispatcher.forward(req, resp);
    }
}
