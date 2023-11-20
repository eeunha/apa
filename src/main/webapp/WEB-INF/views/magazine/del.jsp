<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        }

        header {
            background-color: #333;
            color: #fff;
            padding: 1em;
            text-align: center;
        }

        main {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #f4f4f4;
            border-radius: 5px;
        }

        
        button {
            padding: 10px;
            cursor: pointer;
            border: none;
            border-radius: 5px;
            margin-right: 10px;
        }

        button.back {
            background-color: #999;
            color: #fff;
        }

        button.del.primary {
            background-color: #ff0000; 
            color: #fff;
        }
    </style>
</head>
<body>
    <!-- /magazine/del.jsp -->
    <%@ include file="/WEB-INF/views/inc/header.jsp" %>
    <main id="main">
        <h1>삭제하기</h1>
        <form method="POST" action="/apa/magazine/del.do">
            <div>
                <button type="button" class="back" onclick="location.href='/apa/magazine/view.do?magazineSeq=${magazineSeq}';">돌아가기</button>
                <button type="submit" class="del primary">삭제하기</button>
            </div>
            <input type="hidden" name="magazineSeq" value="${magazineSeq}">
        </form>
    </main>

    <script>
        
    </script>
</body>
</html>