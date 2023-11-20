package com.apa.user;

import com.apa.repository.TestDAO;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthFilter implements Filter{

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest)req;
        HttpServletResponse httpResp = (HttpServletResponse)resp;

        HttpSession session = httpReq.getSession();

        if(session.getAttribute("userSeq") == null){
            System.out.println("비회원");
        } else {
            System.out.println("회원: "+ session.getAttribute("userSeq"));
        }
        System.out.println();

        //익명 사용자 > 배체
//        System.out.println(httpReq.getRequestURL());

        if(session.getAttribute("userSeq") == null) {

            if(httpReq.getRequestURI().endsWith("add.do")
                    || httpReq.getRequestURI().endsWith("edit.do")
                    || httpReq.getRequestURI().endsWith("del.do")
                    || httpReq.getRequestURI().endsWith("info.do")) {

//                httpResp.sendRedirect("/index.do");

                PrintWriter writer = httpResp.getWriter();
                writer.write("<script>alert('unauthorized');location.href='index/do';</script>");
                writer.close();
                return;

            }
        }
        //필터 > 서블릿 호출
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void destroy() {

    }

}
