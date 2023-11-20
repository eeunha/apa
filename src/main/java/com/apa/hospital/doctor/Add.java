package com.apa.hospital.doctor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.apa.model.HospitalDoctorDTO;
import com.apa.repository.HospitalMyPageDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/hospital/doctor/add.do")
public class Add extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/hospital/doctor/add.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
req.setCharacterEncoding("UTF-8");
		try {
			MultipartRequest multi = new MultipartRequest(
					req,
					req.getRealPath("asset/images/doc"),
					1024 * 1024 * 10,
					"UTF-8",
					new DefaultFileRenamePolicy());
			String name = multi.getParameter("name");
			String id = multi.getParameter("id");
			String dept = multi.getParameter("dept");
			String img = multi.getParameter("img");
			
			//2.
			HospitalMyPageDAO dao = new HospitalMyPageDAO();
			
			HospitalDoctorDTO dto = new HospitalDoctorDTO();
			dto.setSeq(id);
			dto.setName(name);
			dto.setDeptname(dept);		
			if (img != null && !img.equals("")) {
				dto.setImg(img);
			} else {
				dto.setImg("pic.png");
			}
			
			
			int result = dao.doctorInsert(dto);
			
			//3.
			JSONObject obj = new JSONObject();
			obj.put("result", result);
			
			if (result == 1) {
				resp.sendRedirect("/apa/hospital/doctor/list.do?id="+id);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/hospital/doctor/list.do?id="+id);
				dispatcher.forward(req, resp);
			}
			
		} catch (Exception e) {
			System.out.println("Add.doPost()");
			e.printStackTrace();
		}

		PrintWriter writer = resp.getWriter();
		writer.print("<script>alert('failed');history.back();</script>");
		writer.close();
	}
}
