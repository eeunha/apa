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

div {
	height: 30px;
}

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

tr:nth-child(even) td:nth-child(2) {
	background-color: #d3ffeb;
}

tr:nth-child(odd) td:nth-child(2) {
	background-color: #ecfffb;
}

#box {
	height: 20px;
}

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

	<h1>입점 신청서</h1>

	<div></div>

	<hr>

	<div></div>

	<table>
		<tr>
			<td>아이디</td>
			<td>${dto.pharmacyId}</td>
		</tr>
		<tr>
			<td>약국명</td>
			<td>${dto.pharmacyName}</td>
		</tr>
		<tr>
			<td>주소</td>
			<td>${dto.pharmacyAddress}</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>${dto.pharmacyEmail}</td>
		</tr>
		<tr>
			<td>연락처</td>
			<td>${dto.pharmacyTel}</td>
		</tr>
		<tr>
			<td>약사 이름</td>
			<td>${dto.pharmacistName}</td>
		</tr>
		<tr>
			<td>약 제조 여부</td>
			<td>${dto.isDispense}</td>
		</tr>
		<tr>
			<td>운영시간</td>
			<td>${dto.openTime}~${dto.closeTime}</td>
		</tr>
		<tr>
			<td>운영 여부</td>
			<td>
				야간: ${dto.isPharmarcyNightCare}
				휴일:${dto.isPharmarcyHoliday}
			</td>
		
			
		</tr>
		<tr>
			<td>휴무일</td>
			<td>${dto.pharmacyDayOff}요일</td>
		</tr>
		</table>
		<div id="box"></div>
	<%-- <form action="/apa/pharmacy/entry/apply.do?pharmacyId=${dto.pharmacyId}" method="POST">		
		<button type="submit" id="btn">제출하기</button>
	</form> --%>
	<div id="btn" onclick="location.href='/apa/pharmacy/entry/list.do?pharmacyId=${dto.pharmacyId}';">확인</div>
	
	<script>
	
	document.getElementById('confirmButton').addEventListener('click', function() {
	    
	    $.ajax({
	        type: 'POST',
	        url: '/apa/pharmacy/entry/apply.do',
	        success: function(response) {
	            if (response === 'Success') {
	                alert('입점신청서가 제출되었습니다.');
	                location.href = '/apa/pharmacy/entry/list.do'; // 성공 시 이동할 페이지 URL
	            } else {
	                alert('제출에 실패하였습니다.');
	            }
	        },
	        error: function() {
	            alert('요청 처리 중 오류가 발생했습니다.');
	        }
	    });
	});
	</script>
</body>
</html>