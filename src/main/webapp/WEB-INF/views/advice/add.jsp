<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
    	<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">
    	
        <%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<style>
main{
	padding: 105px 300px 200px;	
}

.counselingWrite {
	/* border: 1px solid grey; */
	width: 1150px;
	height: 400px;
	background-color: #DCF0EC;
}
input[type="text"]{
    width: 100%;
    padding: 10px;
   	border: 1px solid white;
    border-radius: 4px;
    background-color: white;
    /* box-sizing: border-box; */
}
textarea{
	width: 100%;
	height: 300px;
	padding: 10px;
	margin-left: 10px;
	border: 1px solid white;
	border-radius: 4px;
}
.counselingWrite th:nth-child(1){
	/* background-color: grey; */
	text-align: center;
	font-size: 20px;
	 
}
#subjectLine{
	border-bottom: 12px solid #EEF5F3;
	border-right: 12px solid #EEF5F3;
	padding: 10px;
}

#adviceTitle{
	border-right: 12px solid #EEF5F3;
	border-bottom: 12px solid #EEF5F3;
	width: 100px;
	height: 40px;
}

#adviceContent{
	border-right: 12px solid #EEF5F3;
	width: 100px;
}
#departmentChoice {
	border-bottom: 12px solid #EEF5F3;
	width: 70px;
	text-align: center;
	font-size: 18px;
}

#departmentSector {
	border-bottom: 12px solid #EEF5F3;

}
.buttons{
	width: 1100px;
	margin-top: 30px;
	text-align: right;
}
.back {
	border: 1px solid grey;
	border-radius: 4px;
	background-color: #98d6c9;
}
.questionAdd{
	border: 1px solid grey;
	border-radius: 4px;
	background-color: #98d6c9;
}
</style>
    </head>
    <body id="section_1">
        <%@ include file="/WEB-INF/views/inc/header.jsp" %>
        <main>
			<h1 class ="hansans">의학상담 <small>글쓰기</small></h1>
		
		<form method="POST" action="/apa/advice/add.do">
		<table class="counselingWrite">
			<tr>
				<th id="adviceTitle">제목</th>
					<td id="subjectLine"><input type="text" name="subject" id="subject" required class="full" autofocus></td>
				<th id="departmentChoice">진료과</th>
					<td id="departmentSector">
					<select name="department" id="department">
					<option selected>선택</option>
					<option value="1">산부인과</option>
					<option value="2">이비인후과</option>
					<option value="3">정형외과</option>
					<option value="4">피부과</option>
					<option value="5">내과</option>
					<option value="6">안과</option>
					<option value="7">치과</option>
					<option value="8">한의원</option>
					<option value="9">비뇨기과</option>
					<option value="10">신경외과</option>
					<option value="11">가정의학과</option>
					<option value="12">외과</option>
					<option value="13">정신의학과</option>
					<option value="14">소아과</option>
					<option value="15">신경과</option>
					<option value="16">성형외과</option>
					<option value="17">재활의학과</option>
					<option value="18">마취통증의학과</option>
					<option value="19">영상의학과</option>
					<option value="20">응급의학과</option>
					<option value="21">기타</option>
					</select>
					</td>
			</tr>
			<tr>
				<th id="adviceContent">내용</th>
				<td><textarea name="content" id="content" required class="full"></textarea></td>
			</tr>
		</table>
		<div class="buttons">
			<button type="button" class="back" onclick="location.href='/apa/advice/list.do';">돌아가기</button>
			<button type="submit" class="questionAdd">등록</button>
		</div>
		</form>
		
        </main>

        <%@ include file="/WEB-INF/views/inc/footer.jsp" %>
        
    </body>
</html>