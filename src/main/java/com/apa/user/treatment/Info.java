package com.apa.user.treatment;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.MediCheckupReservationDTO;
import com.apa.model.MediHistoryDTO;
import com.apa.model.RegisterDTO;
import com.apa.model.UserDTO;
import com.apa.repository.UserDAO;
/**
 * 서블릿 입니다.
 * 
 * 회원 마이페이지 -> 진료 정보 -> 진료 정보 조회
 * 
 */
@WebServlet("/user/treatment/info.do")
public class Info extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");
		
		UserDAO dao = new UserDAO();
		
		UserDTO dto = dao.get(seq); // 회원 정보
		ArrayList<RegisterDTO> rlist = dao.getRegister(seq); // 예약 진료 리스트
		ArrayList<MediHistoryDTO> hlist = dao.getMediHistory(seq); // 진료 내역 리스트		
		ArrayList<MediCheckupReservationDTO> clist = dao.getMediCheckupReservation(seq); // 건강검진 내역 리스트		
		
		req.setAttribute("dto", dto);
		req.setAttribute("rlist", rlist);
		req.setAttribute("hlist", hlist); 
		req.setAttribute("clist", clist);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/treatment/info.jsp");
		dispatcher.forward(req, resp);
	}
}
