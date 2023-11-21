package com.apa.user.storage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apa.model.CommunityStorageDTO;
import com.apa.model.MediCounselQuestionDTO;
import com.apa.model.MediCounselingBoxDTO;
import com.apa.model.MediTestSaveDTO;
import com.apa.model.UserDTO;
import com.apa.repository.UserDAO;
/**
 * 서블릿 입니다.
 * 
 * 회원 마이페이지 -> 보관함 -> 보관함 목록 조회
 * 
 */
@WebServlet("/user/storage/list.do")
public class List extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");
		
		UserDAO dao = new UserDAO();

		UserDTO dto = dao.get(seq);
		ArrayList<MediTestSaveDTO> mlist = dao.getMediTestSave(seq);
		ArrayList<MediCounselQuestionDTO> qlist = dao.getMediCounselQuestion(seq);
		ArrayList<MediCounselingBoxDTO> blist = dao.getMediCounselBox(seq);
		ArrayList<CommunityStorageDTO> clist = dao.getCommnuityStorage(seq);
		
		for (MediCounselQuestionDTO qdto : qlist) {
			
			String subject = qdto.getCounselTitle();
			
			if (subject.length() > 14) {
				subject = subject.substring(0, 14) + "...";
			}
			
			subject = subject.replace("<", "&lt;");
			subject = subject.replace(">", "&gt;");
			
			qdto.setCounselTitle(subject);
			
		}

		for (MediCounselingBoxDTO bdto : blist) {
			
			String subject = bdto.getCounselTitle();
			
			if (subject.length() > 14) {
				subject = subject.substring(0, 14) + "...";
			}
			
			subject = subject.replace("<", "&lt;");
			subject = subject.replace(">", "&gt;");
			
			bdto.setCounselTitle(subject);
			
		}
		
		for (CommunityStorageDTO cdto : clist) {
			
			String subject = cdto.getCommunityTitle();
			
			if (subject.length() > 14) {
				subject = subject.substring(0, 14) + "...";
			}
			
			subject = subject.replace("<", "&lt;");
			subject = subject.replace(">", "&gt;");
			
			cdto.setCommunityTitle(subject);
			
		}
		
		
		req.setAttribute("dto", dto);
		req.setAttribute("mlist", mlist);
		req.setAttribute("qlist", qlist);
		req.setAttribute("blist", blist);
		req.setAttribute("clist", clist);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/storage/list.jsp");
		dispatcher.forward(req, resp);
	}
}
