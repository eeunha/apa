<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<style>
	
</style>
</head>
<body>
	<!-- /advice/edit.jsp -->
	<%@ include file="/WEB-INF/views/inc/header.jsp" %>
		<h1>의학상담 <small>수정하기</small></h1>
		
		<form method="POST" action="/apa/advice/edit.do">
		<table class="vertical">
			<tr>
				<th>제목</th>
				<td><input type="text" name="subject" id="subject" required class="full" value="${dto.adviceTitle}"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content" id="content" required class="full">${dto.adviceContent}</textarea></td>
			</tr>
		</table>
		<div>
			<button type="button" class="back" onclick="location.href='/apa/advice/list.do?seq=${dto.adviceSeq}';">돌아가기</button>
			<button type="submit" class="edit primary">수정하기</button>
		</div>
		<input type="hidden" name="seq" value="${dto.adviceSeq}">
		</form>
		
	
	<script>
		
	</script>
</body>
</html>