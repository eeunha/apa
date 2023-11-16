package com.apa.pharmacy.opening;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apa.pharmacy.model.OpenDTO;
import com.apa.pharmacy.repository.OpenDAO;

@WebServlet("/pharmacy/opening/edit.do")
public class Edit extends HttpServlet {
	
	
	 @Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			HttpSession session = req.getSession();
//			String pharmacyId = (String) session.getAttribute("pharmacyId");
			String pharmacyId = session.getAttribute("id").toString();
//			session.setAttribute("sla0623", "sla1234");
			System.out.println(pharmacyId);

			if (pharmacyId == null || "".equals(pharmacyId)) {
				pharmacyId = req.getParameter("pharmacyId");
			}
			OpenDAO openDAO = new OpenDAO();

			OpenDTO openDTO = openDAO.getOpenInfo(pharmacyId);

			req.setAttribute("dto", openDTO);
			req.setAttribute("pharmacyId", pharmacyId);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/pharmacy/opening/edit.jsp");
			dispatcher.forward(req, resp);
		}

	    @Override

	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	       
	    	HttpSession session = req.getSession();
//			String pharmacyId = (String) session.getAttribute("pharmacyId");
			String pharmacyId = session.getAttribute("id").toString();
//			session.setAttribute("sla0623", "sla1234");
			System.out.println(pharmacyId);

			if (pharmacyId == null || "".equals(pharmacyId)) {
				pharmacyId = req.getParameter("pharmacyId");
			}
			req.setCharacterEncoding("UTF-8");
	    	
	        String openTime = req.getParameter("openTime");
	        String closeTime = req.getParameter("closeTime");
	        String isPharmarcyNightCare = req.getParameter("isPharmarcyNightCare");
	        String isPharmarcyHoliday = req.getParameter("isPharmarcyHoliday");
	        String pharmacyDayOff = req.getParameter("pharmacyDayOff");

	
	        OpenDAO dao = new OpenDAO();
	      
	        OpenDTO dto = new OpenDTO();
	        dto.setPharmacyId(pharmacyId);
	        dto.setOpenTime(openTime);
	        dto.setCloseTime(closeTime);
	        dto.setIsPharmarcyNightCare(isPharmarcyNightCare);
	        dto.setIsPharmarcyHoliday(isPharmarcyHoliday);
	        dto.setPharmacyDayOff(pharmacyDayOff);
	        
	        int resultTimeInfo = dao.updateTimeInfo(dto);
	        int resultIsPharmarcy = dao.updateIsPharmarcy(dto);
	        int resultDayOff = dao.updateDayOff(dto);

	  
	        if (resultTimeInfo == 1 && resultIsPharmarcy == 1 && resultDayOff == 1) {
	        
	            req.getSession().setAttribute("updateSuccess", "true");
	            resp.sendRedirect("/apa/pharmacy/opening/view.do?pharmacyId=" + pharmacyId );
	        } else {
	           
	            PrintWriter writer = resp.getWriter();
	            writer.print("<script>alert('failed');history.back();</script>");
	            writer.close();
	        }
	    }    
}