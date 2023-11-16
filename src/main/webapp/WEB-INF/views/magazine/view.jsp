<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<style>

	 body {
            font-family: 'Arial', sans-serif;
            margin: 50px;
            padding: 50px;
            background-color: #f5f5f5;
        }

        header {
            background-color: #333;
            color: #fff;
            padding: 10px;
            text-align: center;
        }

        h1.sub {
            text-align: center;
            margin-top: 20px;
            color: #333;
        }

        form {
            max-width: 600px;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        table {
            width: 100%;
            margin-bottom: 20px;
            border-collapse: collapse;
            border: 1px solid gray;
        }
        
        table th {
        	width: 20%;
        	border: 1px solid gray; 
        }
        
        table td {
        	width: 80%;
        	border: 1px solid gray;
        }
        
        

        .full {
            width: 100%;
            box-sizing: border-box;
        }

        textarea {
            width: 100%;
            height: 150px;
            resize: vertical;
        }

        .back,
        .edit,
        .del {
            margin: 10px;
            padding: 8px 16px;
            font-size: 16px;
            cursor: pointer;
        }

        .back {
            background-color: #ccc;
        }

        .edit {
            background-color: #4caf50;
            color: #fff;
        }

        .del {
            background-color: #f44336;
            color: #fff;
        }

</style>
</head>
<body>
	<!-- /magazine/view.jsp -->
	<%@ include file="/WEB-INF/views/inc/header.jsp" %>
	<h1 class="sub">상세보기</h1>
		<table class="vertical" id="view">
			<tr>
				<th>번호</th>
				<td>${dto.magazineSeq}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${dto.magazineTitle}</td>
			</tr>
			<tr>
				<th>부제목</th>
				<td>${dto.magazineSubTitle}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${dto.magazineContent}</td>
			</tr>
			<tr>
				<th>날짜</th>
				<td>${dto.magazineDate}</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${dto.magazineReadcount}</td>
			</tr>
		</table>

		<div>
			<button type="button" class="back" onclick="location.href='/apa/magazine/list.do';">뒤로가기</button>

			<button type="button" class="edit" onclick="location.href='/apa/magazine/edit.do?magazineSeq=${dto.magazineSeq}';">수정하기</button>
			<button type="button" class="del" onclick="location.href='/apa/magazine/del.do?magazineSeq=${dto.magazineSeq}';">삭제하기</button>
		
		</div>
	
	<script>
	
		
	</script>
</body>
</html>
















