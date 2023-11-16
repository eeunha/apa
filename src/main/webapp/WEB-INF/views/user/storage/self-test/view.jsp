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
		width: 25%;
	}

	tr td:nth-child(2) {
		font-size: 1.2rem;
		color: #008e63;
	}

	tr > td:last-child > textarea {
		background-color: #ecfffb;
		border: 0;
		resize: none;
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
	}


</style>
</head>
<body>
	<!-- /user/treatment/view.jsp -->
	
		<h1>테스트 결과</h1>
		
		<div></div>

		<hr>
		
		<div></div>
		
		<table>
			<tr>
				<td>테스트명</td>
				<td>${dto.mediTestName}</td>
			</tr>
			<tr>
				<td>테스트 날짜</td>
				<td>${dto.testDate}</td>
			</tr>
			<tr>
				<td>회원명</td>
				<td>${dto.userName}</td>
			</tr>
			<tr>
				<td>점수</td>
				<td>${dto.mediTestTotalScore}점</td>				
			</tr>
			<tr>
				<td>결과</td>
				<td>
					<textarea cols="30" disabled>${dto.mediTestResultContent}</textarea>
				</td>
			</tr>
		</table>
		<div id="box"></div>
		<div id="btn" onclick="window.close()">창 닫기</div>
	
	<script>
		
	</script>
</body>
</html>