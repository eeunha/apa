<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<style>
	
	h1 {
		width: 80%;
		margin: 0 auto;
		color: #0db391;
		margin-top: 30px;
		text-align: center;
	}

	hr {
		border: 0;
		background: #0db391;
		height: 2px;
		width: 80%;
		margin: 0 auto;
	}
	
	div { height: 30px; }
	
	table {
		height: 200px;
		width: 80%;
		text-align: center;
		margin: 0 auto;
		border: 2px solid #5BC1AC;
	}
	
	tr td:nth-child(1) {
		padding: 3%;
		background-color: #5BC1AC;
		color: white;
		font-size: 1.2rem;
	}

	tr td:nth-child(2) {
		font-size: 1.2rem;
		color: #008e63;
	}

	tr:nth-child(even) td:nth-child(2) { background-color: #d3ffeb; }
	tr:nth-child(odd) td:nth-child(2) { background-color: #ecfffb; }

	#box { height: 20px;}

	#btn {
		color: white;
		width: 100px;
		height: 40px;
    	margin: 0 auto;
    	cursor: pointer;
		display: flex;
		font-size: 1.2rem;
		text-align: center;
		line-height: 50px;
    	align-items: center;
    	border-radius: 10px;
		justify-content: center;
		background-color: #2bc194;
		border: 0;
	}
	
	#content {
		width: 100%;
	    background-color: #ecfffb;
	    padding-left: 10px;
	    border: 0;
	    outline: none;
	}
	
</style>
</head>
<body>
	<!-- /user/treatment/view.jsp -->
	
		<h1>제증명 서류 요청</h1>
		
		<div></div>

		<hr>
		
		<div></div>
		
		<form method="POST"	action="/apa/user/treatment/request.do">
		
		<table>
			<tr>
				<td>신청 서류명</td>
				<td><input type="text" name="contentDocument" placeholder="신청할 서류명을 입력해주세요." id="content"></td>
			</tr>
		</table>
		<div id="box"></div>
		<button id="btn">신청하기</button>
		<input type="hidden" value="${seq}" name="seq">
		</form>
	
	<script>
		
	</script>
</body>
</html>