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
	<!-- /admin/user/general/black/add.jsp -->
	<%@ include file="/WEB-INF/views/inc/header.jsp" %>
	
		<form method="POST" action="/apa/admin/user/general/black/add.do">
			<div>
				<%-- <button id="btn"><a href="/apa/admin/user/general/view.do?userSeq=${dto.userSeq}">수정완료</a></button> --%>
				<button>수정완료</button>
			</div>
				<table class=info>
					<tr>
						<th>아이디</th>
						<td><input type="text" name="seq" id="seq" required class="full" autofocus></td>
					</tr>
					<tr>
						<th>사유</th>
						<td><input type="text" name="content" id="content" required class="full"></td>
					</tr>
				</table>
	
	<script>
		
	</script>
</body>
</html>