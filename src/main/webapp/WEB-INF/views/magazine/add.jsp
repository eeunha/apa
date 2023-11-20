<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<style>
	
	    body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
        }

        header {
            background-color: #333;
            color: #fff;
            padding: 10px;
            text-align: center;
        }

        h1 {
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
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }

        .full {
            width: 100%;
            box-sizing: border-box;
        }

        button {
            padding: 10px;
            cursor: pointer;
            border: none;
            border-radius: 4px;
            color: #fff;
            font-size: 16px;
            margin-right: 10px;
        }

        .back {
            background-color: #666;
        }

        .add {
            background-color: #4caf50;
        }

        textarea {
            width: 100%;
            height: 150px;
            resize: vertical;
        }
	
</style>
</head>
<body>
	<!-- /magazine/add.jsp -->
	<%@ include file="/WEB-INF/views/inc/header.jsp" %>
	
	<h1 class="add">게시물 추가</h1>
	
		
	<form method="POST" action="/apa/magazine/add.do">
		<table class="vertical">
			<tr>
				<th>제목</th>
				<td><input type="text" name="magazineTitle" id="magazineTitle" required class="full" autofocus></td>
			</tr>
			<tr>
				<th>부제</th>
				<td><input type="text" name="magazineSubTitle" id="magazineSubTitle" required class="full" autofocus></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="magazineContent" id="magazineContent" required class="full"></textarea></td>
			</tr>
		</table>
		<div>
			<button type="button" class="back" onclick="location.href='/apa/magazine/list.do';">돌아가기</button>
			<button type="submit" class="add primary">글쓰기</button>
		</div>
		</form>
	
	<script>
		
	</script>
</body>
</html>