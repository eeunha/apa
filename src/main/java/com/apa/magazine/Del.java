package com.apa.magazine;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.magazine.MagazineDAO;

/**
 * @author 안대명
 * 
 * Magazine 삭제 기능을 담당하는 서블릿 클래스입니다.
 */
@WebServlet("/magazine/del.do")
public class Del extends HttpServlet {
    
    /**
     * GET 요청을 처리합니다.
     * 삭제할 Magazine 정보를 가져와 화면을 표시합니다.
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException Servlet 예외
     * @throws IOException      입출력 예외
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 데이터 가져오기
        String magazineSeq = req.getParameter("magazineSeq");
        
        // 2. 데이터 전달하여 화면 표시
        req.setAttribute("magazineSeq", magazineSeq);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/magazine/del.jsp");
        dispatcher.forward(req, resp);
    }
    
    /**
     * POST 요청을 처리합니다.
     * Magazine을 삭제하고 결과에 따라 작업을 처리합니다.
     * @param req  HttpServletRequest 객체
     * @param resp HttpServletResponse 객체
     * @throws ServletException Servlet 예외
     * @throws IOException      입출력 예외
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 데이터 가져오기 (삭제할 Magazine의 seq)
        String magazineSeq = req.getParameter("magazineSeq");
        
        // 2. DB 작업 > delete
        MagazineDAO dao = new MagazineDAO();
        int result = dao.del(magazineSeq); // 댓글 존재할 경우 문제 발생할 수 있음
        
        // 3. 피드백 처리
        if (result == 1) {
            resp.sendRedirect("/apa/magazine/list.do");
        } else {
            PrintWriter writer = resp.getWriter();
            writer.print("<script>alert('failed');history.back();</script>");
            writer.close();
        }
    }
}