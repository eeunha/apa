<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<style>
	.main-body {
		margin: 100px 25%;
	 	text-align: center;
	}
	button {
		width: 100px;
		height: 50px;
		border: 1px solid #999999;
		border-radius: 5%;
		margin: 20px 20px;
	}
	body {
		-webkit-user-select: none;
 		-moz-user-select: none;
 		-ms-use-select: none;
		background-color: #f8f8f8;
	}
</style>
</head>
<body>
	<!-- /reservation/complete.jsp -->
	<%@ include file="/WEB-INF/views/inc/header.jsp" %>
	
	<div class="main-body">
		<h4>여기는 왕진 페이지
		</h4>
		<div>
			<a href="/apa/main.do">
				<button>바로가기</button>
			</a>
			<a href="/apa/main.do">
				<button>메인페이지</button>
			</a>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/views/inc/footer.jsp" %>
	<script>
		
	</script>
</body>
</html>