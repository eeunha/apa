<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="/WEB-INF/views/inc/asset.jsp" %>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
        }
        
        /* Header styles */
        header {
            background-color: #333;
            color: #fff;
            padding: 10px;
            text-align: center;
        }

        /* Main styles */
        main {
            width: 80%;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        /* Subtitle styles */
        .sub {
            font-size: 24px;
            margin-bottom: 10px;
        }

        /* Table styles */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        /* Form styles */
        form {
            margin-bottom: 20px;
        }

        select, input[type="text"], input[type="submit"] {
            margin-right: 10px;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
        }

        /* Button styles */
        .add.primary {
            background-color: #28a745;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <!-- Header Section -->
    <%@ include file="/WEB-INF/views/inc/header.jsp" %>
    
    <main id="main">
        <!-- Subtitle -->
        <h1 class="sub">
            질병 백과
            <small>
                <c:if test="${map.search == 'y'}">검색결과</c:if>
            </small>
        </h1>
        
        <!-- Magazine List Table -->
        <table id="list">
            <thead>
                <tr>
                    <th>번호</th>
                    <th>질병명</th>
                    <th>질병설명</th>
                    <th>조회수</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${list}" var="dto">
                    <tr>
                        <td>${dto.wikiSeq}</td>
                        <td>
                            <!-- Title and Link -->
                            <a href="/apa/wikilist/view.do?wikiSeq=${dto.wikiSeq}&search=${map.search}&column=${map.column}&word=${map.word}">
                                ${dto.diseaseName}
                            </a>
                            <!-- Conditional Element -->
                            <c:if test="${dto.isnew == 1}">
                                <span class='is-new'>new</span>
                            </c:if>
                        </td>
                        <td>${dto.diseaseExplanation}</td>
                        <td>${dto.wikiReadcount}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Pagination Bar -->
        <div id="pagebar">${pagebar}</div>
        
        <!-- Search Form -->
        <form id="search-form" action="/apa/magazine/list.do" method="GET">
            <select name="column">
                <option value="diseaseName">질병명</option>
                <!-- Add more options as needed -->
            </select>
            <input type="text" name="word" class="long" required>
            <input type="submit" value="검색하기">
        </form>
        
    </main>
    
    <!-- Script Section -->
    <script>
        <!-- Search-related Script -->
        <c:if test="${map.search == 'y'}">
            $('select[name=column]').val('${map.column}');
            $('input[name=word]').val('${map.word}');
        </c:if>
        
        <!-- Page Selection Script -->
        $('#selPage').val(${nowPage});
    </script>
</body>
</html>