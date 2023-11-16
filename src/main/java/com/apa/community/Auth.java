package com.apa.community;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Auth {
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
