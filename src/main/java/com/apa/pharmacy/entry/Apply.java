package com.apa.pharmacy.entry;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apa.pharmacy.model.PharmacyDTO;
import com.apa.pharmacy.repository.PharmacyDAO;

@WebServlet("/pharmacy/entry/apply.do")
public class Apply extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
//		String pharmacyId = (String) session.getAttribute("pharmacyId");
		String pharmacyId = session.getAttribute("id").toString();
//		session.setAttribute("sla0623", "sla1234");
		System.out.println(pharmacyId);

		if (pharmacyId == null || "".equals(pharmacyId)) {
			pharmacyId = req.getParameter("pharmacyId");
		}
		// 1.
		PharmacyDAO dao = new PharmacyDAO();

		PharmacyDTO dto = dao.getEntryInfo(pharmacyId);

		req.setAttribute("dto", dto);
		req.setAttribute("pharmacyId", pharmacyId);
		
	
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/pharmacy/entry/apply.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
//		String pharmacyId = (String) session.getAttribute("pharmacyId");
		String pharmacyId = session.getAttribute("id").toString();
//		session.setAttribute("sla0623", "sla1234");
		System.out.println(pharmacyId);

		if (pharmacyId == null || "".equals(pharmacyId)) {
			pharmacyId = req.getParameter("pharmacyId");
		}
		req.setCharacterEncoding("UTF-8");
    	
       
        String entrySeq = req.getParameter("entrySeq");
   
        PharmacyDAO dao = new PharmacyDAO();
      
        PharmacyDTO dto = new PharmacyDTO();
        dto.setPharmacyId(pharmacyId);
        dto.setEntrySeq(entrySeq);
      
        
        int entryRegister = dao.entryRegister(dto);
        int entryRegisterDate = dao.entryRegisterDate(dto,entrySeq);

        if (entryRegister == 1 && entryRegisterDate == 1) {
          
            req.getSession().setAttribute("insertSuccess", "true");
            resp.sendRedirect("/apa/pharmacy/entry/list.do?pharmacyId=" + pharmacyId );
        } else {
            PrintWriter writer = resp.getWriter();
            writer.print("<script>alert('failed');history.back();</script>");
            writer.close();
        }
    }    

	
	
}

