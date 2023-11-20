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
	
	#box { height: 20px;}

	#text-box {
		width: 500px;
		height: 220px;
		margin: 0 auto;
		display: grid;
		justify-content: center;
	}
	
	#q-box {
		display: grid;
	}
	
	#qContent {
		width: 500px;
		height: 200px;
		resize: none;
		background-color: #d3ffeb;
		border-color: white;
		border-left-width: 0px;
	    border-right-width: 0px;
   		border-top-width: 2px;
   		border-bottom-width: 0px;
   		border-bottom-left-radius: 10px;
		border-bottom-right-radius: 10px;		
   		border-top-left-radius: 10px;
		border-top-right-radius: 10px;		
   		box-shadow: 1px;
   		padding-left: 10px;
   		outline-color: #09734d;
	}
	
	#list {
		text-align: center;
		margin: 0 auto;
		width: 500px;
	}
	
	#list tr td:nth-child(1) { width: 150px; }
	#list tr td:nth-child(2) { width: 100px; }
	#list tr td:nth-child(3) { width: 100px; }
	#list tr td:nth-child(4) { width: 150px; }
	
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
	#list tr:nth-child(1) td:nth-child(4) { border-top-right-radius: 10px; }

	#list tr:nth-child(2) td:nth-child(1) { border-bottom-left-radius: 10px; }
	#list tr:nth-child(2) td:nth-child(4) { border-bottom-right-radius: 10px; }

	#txt-box {
		width: 500px;
		margin: 0 auto;
		color: #0db391;
		font-size: 1.2rem;
	}
	
	#txt-hr {
		border: 0;
		background: #0db391;
		height: 2px;
		width: 500px;
		margin: 0 auto;	
	}

	#comment {
		display: flex;
		flex-flow: row wrap;
		margin-bottom: 10px;
		justify-content: center;
	}

	#comment div:nth-child(1) {
		width: 100px;
		background-color: #2bc194;
		color: white;
		display: flex;
		align-items: center;
		padding-left: 5px;
		padding-top: 3px;
		padding-bottom: 3px;
		border-top-left-radius: 10px;
		border-bottom-left-radius: 10px;
	}
	#comment div:nth-child(2) {
		background-color: #d3ffeb;
		width: 250px;
		height: 100%;
		display: flex;
		align-items: center;
		padding-left: 5px;
		padding-top: 3px;
		padding-bottom: 3px;
	}
	#comment div:nth-child(3) {
		background-color: #ecfffb;
		width: 150px;
		display: flex;
		align-items: center;
		padding-left: 9px;
		padding-top: 3px;
		padding-bottom: 3px;
		border-top-right-radius: 10px;
		border-bottom-right-radius: 10px;
	}

	.btn {
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
    	border: 0;
		justify-content: center;
		background-color: #2bc194;
		transition: all 0.5s;
	}
	
	.btn:hover {
		background-color: #2bc194;
	}
	
	#btn-box {
	    width: 250px;
	    margin: 0 auto;
	    display: flex;
	}
	
	#tag-box {
		width: 500px;
		margin: 0 auto;
		display: flex;
		flex-flow: row wrap;
	    justify-content: space-evenly;
	}
	
	.tag {
		border-radius: 10px;
		width: 150px;
		text-align: center;
		margin-bottom: 10px;
		color: white;	
	}
	
	#goodTag { background-color: #2bc194; }
	
	#badTag { background-color: tomato; }
	
</style>
</head>
<body>
	
		<h1>리뷰 작성</h1>
		
		<div id="box"></div>

		<hr>
		
		<div id="box"></div>
		<div id="text-box">
			<div id="q-box">
				<textarea id="qContent" name="content" disabled>${dto.reviewContent}</textarea>
			</div>
		</div>
	
		<table id="list">
			<tr>
				<td>병원명</td>
				<td>진료 의사</td>
				<td>진료 방식</td>
				<td>방문 날짜</td>
			</tr>
			<tr>
				<td>${dto.hospitalName}</td>
				<td>${dto.doctorName}</td>
				<td>${dto.mediWay}</td>
				<td>${dto.treatmentDate}</td>
			</tr>
		</table>

		<div id="box"></div>
		<div id="txt-box">태그</div>
		<hr id="txt-hr">
		<div id="box"></div>

		<div id="tag-box">
			<c:forEach items="${tlist}" var="tdto">
				<c:choose>
					<c:when test="${tdto.tagType eq '긍정'}">
						<div id="goodTag" class="tag">${tdto.tag}</div>
					</c:when>
					<c:otherwise>
						<div id="badTag" class="tag">${tdto.tag}</div>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>

		<div id="box"></div>
		
		<div class="btn" onclick="window.close()">창 닫기</div>
		
		<div id="box"></div>
		<div id="box"></div>

	<script>

	</script>
</body>
</html>