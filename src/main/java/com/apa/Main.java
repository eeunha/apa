package com.apa;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.MagazineDTO;
import com.apa.model.MediQuestionDTO;
import com.apa.repository.MainDAO;

/**
 * @author 이재용
 * 메인 페이지를 처리하는 서블릿 클래스
 */
@WebServlet("/main.do")
public class Main extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // MainDAO 객체 생성
        MainDAO dao = new MainDAO();

        // 의료 질문 목록을 가져옴
        ArrayList<MediQuestionDTO> list = dao.list();

        // 의료 질문 내용을 일정 길이로 생략, 답변 상태를 업데이트
        for (MediQuestionDTO dto : list) {
            if (dto.getContent().length() > 22) {
                dto.setContent(dto.getContent().substring(0, 22) + "..");
            }
            if ("y".equalsIgnoreCase(dto.getAnswer())) {
                dto.setAnswer("답변 완료");
            } else {
                dto.setAnswer("미답변");
            }
        }

        // 의료 질문 목록을 요청 속성으로 설정
        req.setAttribute("list", list);

        // 매거진 목록을 가져옴
        ArrayList<MagazineDTO> magazineList = dao.magazinelist();

        // 메인 매거진내용을 요청 속성으로 설정
        req.setAttribute("magazinemain", magazineList.get(1));

        // 매거진 기사 제목, 부제목, 내용이 일정 길이로 생략
        for (MagazineDTO dto : magazineList) {
            if (dto.getTitle().length() > 20) {
                dto.setTitle(dto.getTitle().substring(0, 20) + "..");
            }
            if (dto.getSubtitle().length() > 50) {
                dto.setSubtitle(dto.getSubtitle().substring(0, 50) + "..");
            }
            if (dto.getContent().length() > 150) {
                dto.setContent(dto.getContent().substring(0, 150) + "..");
            }
        }

        // 서브 매거진내용을 요청 속성으로 설정
        req.setAttribute("magazinelist", magazineList);

        // 메인 뷰로 포워드
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/main.jsp");
        dispatcher.forward(req, resp);
    }
}
