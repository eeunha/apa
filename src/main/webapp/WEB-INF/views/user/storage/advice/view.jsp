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

	#text-box {
		width: 600px;
		height: 350px;
		margin: 0 auto;
		display: grid;
		justify-content: center;
	}
	
	#q-box {
		display: grid;
	}
	
	#title {
		background-color: #46bb94;
	    outline: none;
	    color: white;
		border: 0;
		border-top-left-radius: 10px;
		border-top-right-radius: 10px;
		padding-left: 10px;
	}
	
	#qContent {
		width: 500px;
		height: 150px;
		resize: none;
		background-color: #d3ffeb;
		border-color: white;
		border-left-width: 0px;
	    border-right-width: 0px;
   		border-top-width: 2px;
   		border-bottom-width: 0px;
   		box-shadow: 1px;
   		padding-left: 10px;
	}
	
	#aContent {
		width: 500px;
		height: 150px;
		resize: none;
		background-color: #ecfffb;
		border-color: white;
		border-left-width: 0px;
	    border-right-width: 0px;
   		border-top-width: 2px;
   		border-bottom-width: 0px;
   		padding-left: 10px;
  		border-bottom-left-radius: 10px;
		border-bottom-right-radius: 10px;
	}
	
	#aContentNull {
		width: 500px;
		height: 150px;
		resize: none;
		background-color: #ecfffb;
		border-color: white;
		border-left-width: 0px;
	    border-right-width: 0px;
   		border-top-width: 2px;
   		border-bottom-width: 0px;
   		padding-left: 10px;
  		border-bottom-left-radius: 10px;
		border-bottom-right-radius: 10px;
		text-align: center;
		line-height: 150px;
		overflow: hidden;
	}
	
	#list {
		text-align: center;
		margin: 0 auto;
		width: 500px;
	}
	
	#list tr:nth-child(1) td {
		background-color: #46bb94;
		color: white;
	}

	#list tr:nth-child(3) td {
		background-color: #46bb94;
		color: white;
	}

	#list tr:nth-child(2) td { background-color: #d3ffeb; }	
	#list tr:nth-child(4) td { background-color: #d3ffeb; }

	#list tr:nth-child(1) td:nth-child(1) { border-top-left-radius: 10px; }
	#list tr:nth-child(1) td:nth-child(3) { border-top-right-radius: 10px; }

	#list tr:nth-child(4) td:nth-child(1) { border-bottom-left-radius: 10px; }
	#list tr:nth-child(4) td:nth-child(3) { border-bottom-right-radius: 10px; }

	
</style>
</head>
<body>
	
		<h1>의학상담 상세 보기</h1>
		
		<div></div>

		<hr>
		
		<div></div>

		<div id="text-box">
			<div id="q-box">
				<input type="text" disabled value="${dto.counselTitle}" id="title">
				<textarea disabled id="qContent">${dto.counselContent}</textarea>
			</div>
			<div id="a-box">
				<c:choose>
					<c:when test="${dto.counselAnswerContent eq null}">
						<textarea disabled id="aContentNull">답변을 기다려 주세요!</textarea>
						</c:when>
					<c:otherwise>
						<textarea disabled id="aContent">${dto.counselAnswerContent}</textarea>
					</c:otherwise>
				</c:choose>
			</div>
		</div>

		<table id="list">
			<tr>
				<td>회원 ID</td>
				<td>진료과목</td>
				<td>질문 날짜</td>
			</tr>
			<tr>
				<td>${dto.userId}</td>
				<td>${dto.departmentName}</td>
				<td>${dto.regdate}</td>
			</tr>
			<tr>
				<td>답변 의사이름</td>
				<td>병원명</td>
				<td>답변 날짜</td>
			</tr>
			<tr>
				<c:choose>
					<c:when test="${dto.counselAnswerContent eq null}">
						<td>-</td>
						<td>-</td>
						<td>-</td>
					</c:when>
					<c:otherwise>
						<td>${dto.doctorName}</td>
						<td>${dto.hospitalName}</td>
						<td>${dto.answerTime}</td>
					</c:otherwise>	
				</c:choose>
			</tr>
		</table>

		<div id="box"></div>
		<div id="btn" onclick="window.close()">창 닫기</div>
	
	<script>
		
	</script>
</body>
</html>