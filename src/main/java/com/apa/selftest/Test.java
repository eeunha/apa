package com.apa.selftest;

import com.apa.model.TestDTO;
import com.apa.repository.TestDAO;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 자가진단 문제를 처리하는 서블릿 클래스입니다.
 * 이 서블릿은 자가진단 문제 화면을 표시하고, 사용자가 선택한 답변을 처리하여 결과 페이지로 이동하는 기능을 제공합니다.
 * 사용자가 "/selftest/test.do" 주소로 접근하면 자가진단 문제 페이지를 표시하고, HTTP POST 요청을 통해 사용자의 답변을 처리합니다.
 * 해당 서블릿은 HTTP GET 요청에 응답하여 자가진단 문제 화면을 표시하고, HTTP POST 요청에 응답하여 사용자의 답변을 처리합니다.
 * 사용자의 답변은 각 문제별로 총점이 계산되고, 해당 총점에 따라 결과 페이지로 이동합니다.
 *
 * @author 신수정
 * @version 1.0
 * @since 2023-11-20
 */
@WebServlet("/selftest/test.do")
public class Test extends HttpServlet {

	/**
	 * 자가진단 문제 화면을 표시하는 HTTP GET 요청을 처리합니다.
	 *
	 * @param req  클라이언트의 요청을 포함하는 HttpServletRequest 객체입니다.
	 * @param resp 클라이언트에게 응답을 보내기 위한 HttpServletResponse 객체입니다.
	 * @throws ServletException 서블릿의 정상 작동을 방해하는 예외가 발생할 경우입니다.
	 * @throws IOException      입출력 예외가 발생할 경우입니다.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 문제 번호를 받아와서 해당 번호의 자가진단 문제 리스트를 가져옴
		String seq = req.getParameter("seq");
		TestDAO dao = new TestDAO();
		ArrayList<TestDTO> list = dao.questionList(seq);

		// 요청 속성으로 문제 번호와 문제 리스트 저장
		req.setAttribute("seq", seq);
		req.setAttribute("list", list);

		// 문제 페이지로 포워딩
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/self-test/test.jsp");
		dispatcher.forward(req, resp);
	}

	/**
	 * 사용자의 자가진단 답변을 처리하는 HTTP POST 요청을 처리합니다.
	 *
	 * @param req  클라이언트의 요청을 포함하는 HttpServletRequest 객체입니다.
	 * @param resp 클라이언트에게 응답을 보내기 위한 HttpServletResponse 객체입니다.
	 * @throws ServletException 서블릿의 정상 작동을 방해하는 예외가 발생할 경우입니다.
	 * @throws IOException      입출력 예외가 발생할 경우입니다.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 인코딩 설정 및 콘텐츠 타입 설정
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=utf-8");
		// 총점을 저장할 변수 초기화
		int sum = 0;
		// 문제 번호 받아오기
		String seq = req.getParameter("seq");
		// 각 문제별로 사용자의 답변을 처리하여 총점 계산
		if("1".equals(req.getParameter("seq"))){
			for (int i = 1; i<=10; i++) {
				sum += Integer.parseInt(req.getParameter("q" + i));
			}
			if (sum >= 0 && sum <=13){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			} else if(sum > 13 && sum <= 18){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 18 && sum <= 40){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}



		}else if("2".equals(req.getParameter("seq"))){
			for (int i = 1; i<=7; i++) {
				sum += Integer.parseInt(req.getParameter("q"+i));
			}
			if(sum >= 0 && sum <= 4){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 4 && sum <= 9){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 9  && sum <= 14){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 14 && sum <= 21){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}

		}else if("3".equals(req.getParameter("seq"))){
			for (int i = 1; i<=20; i++) {
				sum += Integer.parseInt(req.getParameter("q"+i));
			}
			if(sum >= 0 && sum <= 19){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 19 && sum <= 31){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 31 && sum <= 40){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 40 && sum <= 60) {
				resp.sendRedirect("/apa/selftest/result.do?sum=" + sum + "&seq=" + seq);
			}

		}else if("4".equals(req.getParameter("seq"))){
			for (int i = 1; i<=13; i++) {
				sum += Integer.parseInt(req.getParameter("q"+i));
			}

			if(sum >= 0 && sum <= 4){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 4 && sum <= 13){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}

		}else if("5".equals(req.getParameter("seq"))){
			for (int i = 1; i<=10; i++) {
				sum += Integer.parseInt(req.getParameter("q"+i));
			}
			if(sum >= 0 && sum <= 7){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 7 && sum <= 14){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 14 && sum <= 20){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}
		}else if("6".equals(req.getParameter("seq"))){
			for (int i = 1; i<=7; i++) {
				sum += Integer.parseInt(req.getParameter("q"+i));
			}
			if(sum >= 0 && sum <= 7){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 7 && sum <= 14){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 14 && sum <= 20){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}
		}else if("7".equals(req.getParameter("seq"))){
			for (int i = 1; i<=8; i++) {
				sum += Integer.parseInt(req.getParameter("q"+i));
			}
			if(sum >= 0 && sum <= 7){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 7 && sum <= 14){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 14 && sum <= 20){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}
		}else if("8".equals(req.getParameter("seq"))){
			for (int i = 1; i<=10; i++) {
				sum += Integer.parseInt(req.getParameter("q"+i));
			}
			if(sum >= 0 && sum <= 7){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 7 && sum <= 14){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}else if(sum > 14 && sum <= 20){
				resp.sendRedirect("/apa/selftest/result.do?sum="+sum + "&seq=" + seq);
			}
		}


	}
}
