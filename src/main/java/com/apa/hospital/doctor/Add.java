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

/**
 * @author 이재용
 * 병원의 의사를 추가하는 서블릿 클래스입니다.
 */
@WebServlet("/hospital/doctor/add.do")
public class Add extends HttpServlet {
	
	/**
	 * 의사 추가 페이지로 이동하는 GET 요청 처리 메서드입니다.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/hospital/doctor/add.jsp");
		dispatcher.forward(req, resp);
	}

	/**
	 * 의사 정보를 받아와 추가하는 POST 요청 처리 메서드입니다.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		try {
			// 파일 업로드를 위한 MultipartRequest 객체 생성
			MultipartRequest multi = new MultipartRequest(
					req,
					req.getRealPath("asset/images/doc"),
					1024 * 1024 * 10,
					"UTF-8",
					new DefaultFileRenamePolicy());
			
			// 요청 파라미터 수집
			String name = multi.getParameter("name");
			String id = multi.getParameter("id");
			String dept = multi.getParameter("dept");
			String img = multi.getParameter("img");
			
			// HospitalMyPageDAO 객체 생성
			HospitalMyPageDAO dao = new HospitalMyPageDAO();
			
			// HospitalDoctorDTO 객체 생성 및 값 설정
			HospitalDoctorDTO dto = new HospitalDoctorDTO();
			dto.setSeq(id);
			dto.setName(name);
			dto.setDeptname(dept);		
			if (img != null && !img.equals("")) {
				dto.setImg(img);
			} else {
				dto.setImg("pic.png");
			}
			
			// 의사 추가 메서드 호출
			int result = dao.doctorInsert(dto);
			
			// 응답을 위한 JSON 객체 생성
			JSONObject obj = new JSONObject();
			obj.put("result", result);
			
			// 결과에 따라 페이지 이동
			if (result == 1) {
				resp.sendRedirect("/apa/hospital/doctor/list.do?id=" + id);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/hospital/doctor/list.do?id=" + id);
				dispatcher.forward(req, resp);
			}
			
		} catch (Exception e) {
			System.out.println("Add.doPost()");
			e.printStackTrace();
		}

		// 실패 시 경고창 출력 후 이전 페이지로 이동
		PrintWriter writer = resp.getWriter();
		writer.print("<script>alert('failed');history.back();</script>");
		writer.close();
	}
}
