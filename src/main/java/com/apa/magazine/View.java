package com.apa.magazine;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 안대명
 * 매거진을 조회하는 서블릿 클래스입니다.
 */
@WebServlet("/magazine/view.do")
public class View extends HttpServlet {

    /**
     * HTTP GET 요청을 처리하고 매거진 데이터를 가져와서 화면에 표시합니다.
     *
     * @param req  HTTP 요청 객체
     * @param resp HTTP 응답 객체
     * @throws ServletException 서블릿 관련 예외 발생 시
     * @throws IOException      입출력 관련 예외 발생 시
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // magazine/view.java
        HttpSession session = req.getSession();

        // 1. 데이터 불러오기
        String magazineSeq = req.getParameter("magazineSeq");

        String search = req.getParameter("search");
        String column = req.getParameter("column");
        String word = req.getParameter("word");

        // 2.
        MagazineDAO dao = new MagazineDAO();

        if (session.getAttribute("read") != null && session.getAttribute("read").toString().equals("n")) {
            // 2.3 조회수 증가
            dao.updateMagazineReadcount(magazineSeq);

            session.setAttribute("read", "y");
        }

        MagazineDTO dto = dao.get(magazineSeq);

        // 2.5 데이터 조작

        // 태그 비활성화
        //- <div>   ->   &lt;div&gt;
        String magazineContent = dto.getMagazineContent();

        magazineContent = magazineContent.replace("<", "&lt;");
        magazineContent = magazineContent.replace(">", "&gt;");

        // 개행 문자 처리 MagazineContent = MagazineContent.replace("\r\n", "<br>");

        magazineContent = magazineContent.replace("\r\n", "<br>");

        dto.setMagazineContent(magazineContent);

        String magazineTitle = dto.getMagazineTitle();

        magazineTitle = magazineTitle.replace("<", "&lt;");
        magazineTitle = magazineTitle.replace(">", "&gt;");

        dto.setMagazineTitle(magazineTitle);

        // 내용으로 검색 > 검색어 강조!!
        if ((search != null && column != null) && search.equals("y") && column.equals("content")) {

            // 이제 넷플릭스 구독을 해지합니다. //이제
            //<span style="background-color:gold;color:tomato;">넷플릭스</span> 구독을 해지합니다.
            dto.setMagazineContent(dto.getMagazineContent().replace(word,
                    "<span style='background-color:gold;color:tomato;'>" + word + "</span>"));
        }

        // 3. 피드백
        req.setAttribute("dto", dto);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/magazine/view.jsp");
        dispatcher.forward(req, resp);
    }
}