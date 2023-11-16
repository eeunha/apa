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
		<h4>${name}님 예약이 완료 됐습니다.
			<br>예약 확정까지는 최대 10분정도 걸릴 수 있으니 양해 부탁드립니다.
				
			<br>예약 현황은 마이페이지 내에서 확인하실 수 있습니다
			<br>확인하시겠습니까?
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