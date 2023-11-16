<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<style>
#main{
	padding: 105px 700px 300px;	
	text-align: center;
}		

#small{
	font-size: 25px;
}
#infoMsg{
	margin-top:15px;
	font-size: 25px;
}
#infosmall{
	font-size: 17px;
	margin-bottom: 20px;
	
}
.back{
	border-radius: 4px;
	border: 1px solid #D0E5E1;
	background-color: #D0E5E1;
}
.del{
	border-radius: 4px;
	border: 1px solid #D0E5E1;
	background-color: #D0E5E1;
}
</style>
</head>
<body>
	<!-- /community/del.jsp -->
	<%@ include file="/WEB-INF/views/inc/header.jsp" %>
	<main id="main">
		<h1 class="hansans">커뮤니티 <small id="small">게시글 삭제</small></h1>
		<form method="POST" action="/apa/community/del.do">
		<div id="infoMsg">
			해당 게시글을 삭제하시겠습니까?
		</div>
		<div id="infosmall">
			삭제하면 게시글을 복구할 수 없습니다.
		</div>
		<div>
			<button type="button" class="back" onclick="location.href='/apa/community/view.do?seq=${seq}';">돌아가기</button>
			<button type="submit" class="del">삭제하기</button>
		</div>
		<input type="hidden" name="seq" value="${seq}">
		</form>
	</main>
		   <%@ include file="/WEB-INF/views/inc/footer.jsp" %>
	
	<script>
		
	</script>
</body>
</html>