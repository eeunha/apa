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

        table.vertical {
            width: 100%;
            margin-bottom: 20px;
        }

        table.vertical th,
        table.vertical td {
            padding: 10px;
            text-align: left;
        }

        input.full,
        textarea.full {
            width: 100%;
            box-sizing: border-box;
        }

        div.buttons {
            margin-top: 20px;
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

        button.edit.primary {
            background-color: #4CAF50;
            color: #fff; 
        }

        /* 추가된 부분: 텍스트 에어리어 크기 조정 */
        textarea.full {
            height: 200px; /* 조절할 높이 값 */
            resize: vertical; /* 사용자가 크기 조절할 수 있도록 설정 */
        }
    </style>
</head>
<body>
    <!-- /magazine/edit.jsp -->
    <%@ include file="/WEB-INF/views/inc/header.jsp" %>
    <main id="main">
        <h1>게시물 수정</h1>

        <form method="POST" action="/apa/magazine/edit.do">
            <table class="vertical">
                <tr>
                    <th>제목</th>
                    <td><input type="text" name="magazineTitle" id="magazineTitle" required class="full" value="${dto.magazineTitle}"></td>
                </tr>
                <tr>
                    <th>부제</th>
                    <td><input type="text" name="magazineSubTitle" id="magazineSubTitle" required class="full" value="${dto.magazineSubTitle}"></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td><textarea name="magazineContent" id="magazineContent" required class="full">${dto.magazineContent}</textarea></td>
                </tr>
            </table>
            <div>
                <button type="button" class="back" onclick="location.href='/apa/magazine/view.do?magazineSeq=${dto.magazineSeq}';">돌아가기</button>
                <button type="submit" class="edit primary">수정하기</button>
            </div>
            <input type="hidden" name="magazineSeq" value="${dto.magazineSeq}">
        </form>

    </main>

    <script>

    </script>
</body>
</html>