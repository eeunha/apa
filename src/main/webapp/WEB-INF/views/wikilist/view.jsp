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
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
            color: #333333;
        }

        /* Subtitle styles */
        .sub {
            text-align: center;
            margin-top: 20px;
        }

        /* Table styles */
        .vertical {
            width: 60%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 10px;
            border-bottom: 1px solid #dddddd;
        }

        th {
            text-align: right;
            font-weight: bold;
            width: 30%;
        }

        /* Button styles */
        button {
            padding: 8px 15px;
            border-radius: 4px;
            border: none;
            cursor: pointer;
            margin: 10px 5px;
            font-size: 14px;
        }

        .back {
            background-color: #6c757d;
            color: white;
        }

        .edit {
            background-color: #ffc107;
            color: #333333;
        }

        .del {
            background-color: #dc3545;
            color: white;
        }
</style>
</head>
<body>
	<!-- /wikilist/view.jsp -->
	<%@ include file="/WEB-INF/views/inc/header.jsp" %>
	<h1 class="sub">상세보기</h1>
		<table class="vertical" id="view">
			<tr>
				<th>번호</th>
				<td>${dto.wikiSeq}</td>
			</tr>
			<tr>
				<th>질병명</th>
				<td>${dto.diseaseName}</td>
			</tr>
			<tr>
				<th>질병설명</th>
				<td>${dto.diseaseExplanation}</td>
			</tr>
			<tr>
				<th>원인</th>
				<td>${dto.diseaseCause}</td>
			</tr>
			<tr>
				<th>증상</th>
				<td>${dto.wikiSymptom}</td>
			</tr>
			<tr>
				<th>진단</th>
				<td>${dto.diagnosis}</td>
			</tr>
			<tr>
				<th>치료</th>
				<td>${dto.care}</td>
			</tr>
		</table>

		<div>
			<button type="button" class="back" onclick="location.href='/apa/wikilist/list.do';">뒤로가기</button>
		</div>
	
	<script>
	
		
	</script>
</body>
</html>
















