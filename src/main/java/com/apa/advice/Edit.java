package com.apa.advice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/apa/advice/edit.do")
public class Edit extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
				//1.
				String seq = req.getParameter("seq");
				
				//2.
				adviceListDAO dao = new adviceListDAO();
				
				//수정할 번호를 내가 알고 있으니까 제목과 내용이 뭐였는지 알려줘!
				adviceListDTO dto = dao.get(seq);
				
				//수정하기 제목에서 생기는 스타일태그 오류 수정하기
			
				
				//3. JSP에게 데이터가 넘어감
				req.setAttribute("dto", dto);
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/advice/edit.jsp");
		dispatcher.forward(req, resp);
	}
}

