package com.apa.community;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 최진희
 * 이 클래스는 사용자 권한을 체크하는 유틸리티 클래스입니다.
 */
public class Auth {
    /**
     * 사용자의 권한을 체크하여 권한이 없는 경우 경고창을 띄우고 이전 페이지로 이동합니다.
     *
     * @param req  클라이언트로부터의 HTTP 요청 객체
     * @param resp 서블릿이 클라이언트로 응답을 보낼 때 사용하는 HTTP 응답 객체
     * @return 사용자의 권한이 없는 경우 true, 그렇지 않은 경우 false
     */	
	public static boolean check(HttpServletRequest req, HttpServletResponse resp) {
		
		HttpSession session = req.getSession();
		String seq = req.getParameter("seq");
		
		communityDAO dao = new communityDAO();
		communityDTO dto = dao.get(seq);
		
		if (!session.getAttribute("id").toString().equals(dto.getId())
				&& !session.getAttribute("lv").toString().equals("2")) {
			
			try {
				
				PrintWriter writer = resp.getWriter();
				writer.print("<script>alert('failed');history.back();</script>");
				writer.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			return true;
		}
		
		return false;
	}

	
}
