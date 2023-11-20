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
	<!-- /admin/user/general/report/approval.jsp -->
	<%@ include file="/WEB-INF/views/inc/header.jsp" %>
	
		<form method="POST" action="/apa/admin/user/general/report/decline.do">
			<div>
				<button>수정완료</button>
			</div>
			<input type="hidden" name="userSeq" value="${dto.userSeq}">
			</form>
	
	<script>
		
	</script>
</body>
</html>