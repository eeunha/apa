<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<style>
#main{
	padding: 105px 300px 200px;	
	
}	





#subject {
	width: 600px;
	height: 50px;
	padding-left: 10px;
	font-size: 1.1rem;
	margin-left: 20px;
	margin-top: 20px;
	border: 0px solid #D0E5E1;
	border-radius: 4px;
}
#content{
	width: 1100px;
	height: 200px;
	/* border-top: 6px solid #EEF5F3; */
	padding-left: 15px;
	padding-right: 10px;
	font-size: 1.1rem;
	margin: 20px 20px 20px 20px;
	border: 0px solid #D0E5E1;
	border-radius: 4px;
}



.edit, .back{
	border-radius: 4px;
	border: 1px solid #D0E5E1;
	background-color: #D0E5E1;
}


#main > form > table > tbody > tr:nth-child(1) > th{
	width: 10px;
}
#main > form > table > tbody{
	display: inline-block;
}
th:nth-child(1){
	width: 50px;
}
#subjectTitle{
	font-weight: bold;
}
#contentTitle{
	font-weight: bold;
	text-align: center;
}

.vertical{
	background-color: #D0E5E1;
	border-radius: 6px;
}
#first, #second{
	padding-left: 20px;
	font-size: 15px;
}

#btn2{

	margin-top: 20px;
	text-align: right;
	margin-right: 120px;
}
</style>
</head>
<body>
	<!-- /community/edit.jsp -->
	<%@ include file="/WEB-INF/views/inc/header.jsp" %>
		<main id="main">
		<h1 class="hansans">Community <small>수정</small></h1>
		
		<form method="POST" action="/apa/community/edit.do">
		<table class="vertical">
			<tr>
				<th id="first">제목</th>
				<td><input type="text" name="subject" id="subject" required class="full" value="${dto.subject}"></td>
			</tr>
			<tr>
				<th id="second">내용</th>
				<td>
				<textarea name="content" id="content" required class="full">${dto.content}</textarea></td>
			</tr>
		</table>
		<div id="btn2">
			<button type="button" class="back" onclick="location.href='/apa/community/view.do?seq=${dto.communitySeq}';">돌아가기</button>
			<button type="submit" class="edit primary">수정하기</button>
		</div>
		<input type="hidden" name="seq" value="${dto.communitySeq}">
		</form>
		
		
		
	</main>
		
	   <%@ include file="/WEB-INF/views/inc/footer.jsp" %>
	<script>
		
	</script>
</body>
</html>