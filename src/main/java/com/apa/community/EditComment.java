package com.apa.community;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * @author 최진희
 * 커뮤니티 댓글 수정을 처리하는 서블릿 클래스입니다.
 * @WebServlet 어노테이션을 사용하여 "/community/editcomment.do" 경로로 서블릿을 매핑합니다.
 * 
 */
@WebServlet("/community/editcomment.do")
public class EditComment extends HttpServlet {

    /**
     * HTTP POST 요청을 처리하고 커뮤니티 댓글을 수정합니다.
     *
     * @param req  클라이언트로부터의 HTTP POST 요청 객체
     * @param resp 서블릿이 클라이언트로 응답을 보낼 때 사용하는 HTTP 응답 객체
     * @throws ServletException 서블릿에서 발생한 일반적인 예외
     * @throws IOException      입출력 작업 중 발생한 예외
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        // 클라이언트로부터 댓글 내용과 댓글 번호 가져오기
		String content = req.getParameter("content");
		String seq = req.getParameter("seq");

        // 데이터베이스 연동을 위한 DAO 객체 생성
		communityDAO dao = new communityDAO();
		// 댓글 DTO 생성 및 내용과 번호 설정
		CommentDTO dto = new CommentDTO();
		dto.setCommentContent(content);
		dto.setCommentSeq(seq);
		// 댓글 수정 수행
		int result = dao.editComment(dto);
		
		 // 응답 타입 및 인코딩 설정
		resp.setContentType("application/json");
        
		// JSON 객체 생성 및 결과 설정
		JSONObject obj = new JSONObject();
		obj.put("result", result);
		
		PrintWriter writer = resp.getWriter();
		writer.write(obj.toString());
		writer.close();		
		
		
	}

}
