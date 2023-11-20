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
	}


</style>
</head>
<body>
	<!-- /user/treatment/view.jsp -->
	
		<h1>진료 내역</h1>
		
		<div></div>

		<hr>
		
		<div></div>
		
		<table>
			<tr>
				<td>진료 번호</td>
				<td>${dto.mediHistorySeq}</td>
			</tr>
			<tr>
				<td>진료 날짜</td>
				<td>${dto.treatmentDate}</td>
			</tr>
			<tr>
				<td>예약자명</td>
				<td>${dto.userName}</td>
			</tr>
			<tr>
				<td>환자명</td>
				<c:choose>
					<c:when test="${name eq 'X'}">
						<td>${dto.userName}</td>				
					</c:when>
					<c:otherwise>
						<td>${name}</td>				
					</c:otherwise>
				</c:choose>
			</tr>
			<tr>
				<td>진단명</td>
				<td>${dto.mediName}</td>
			</tr>
			<tr>
				<td>질병 코드</td>
				<td>${dto.mediCode}</td>
			</tr>
			<tr>
				<td>병원명</td>
				<td>${dto.hospitalName}</td>
			</tr>
		</table>
		<div id="box"></div>
		<div id="btn" onclick="window.close()">창 닫기</div>
	
	<script>
		
	</script>
</body>
</html>