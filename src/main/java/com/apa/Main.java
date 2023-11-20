package com.apa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.MagazineDTO;
import com.apa.model.MediQuestionDTO;
import com.apa.repository.MainDAO;

@WebServlet("/main.do")
public class Main extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		MainDAO dao = new MainDAO();
		
		ArrayList<MediQuestionDTO> list = dao.list();
		
		for (MediQuestionDTO dto : list) {
			if (dto.getContent().length() > 22) {
				dto.setContent(dto.getContent().substring(0, 22) + "..");
			}
			if (dto.getAnswer().equals("y") || dto.getAnswer().equals("Y")) {
				dto.setAnswer("답변 완료");
			} else {
				dto.setAnswer("미답변");
			}
		}
		
		req.setAttribute("list", list);
		
		ArrayList<MagazineDTO> magazineList = dao.magazinelist();

		req.setAttribute("magazinemain", magazineList.get(1));
		
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
		
		req.setAttribute("magazinelist", magazineList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/main.jsp");
		dispatcher.forward(req, resp);
	}
}
