<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="/WEB-INF/views/inc/asset.jsp" %>
    <style>
        /* 전체 페이지 스타일 */
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
        }

        /* 헤더 스타일 */
        header {
            background-color: #333;
            color: #fff;
            padding: 10px;
            text-align: center;
        }

        /* 메인 컨텐츠 스타일 */
        main {
            max-width: 1000px;
            margin: 20px auto;
        }

        /* 서브 제목 스타일 */
        h1.sub {
            text-align: center;
            color: #333;
        }

        /* 리스트 테이블 스타일 */
        table#list {
            border: 1px solid gray;
            width: 100%;
            margin: 0 auto;
            border-collapse: collapse;
            margin-top: 20px;
        }

        #list th,
        #list td {
            border: 1px solid gray;
            padding: 10px;
            text-align: center;
        }

        /* 특정 셀 스타일 */
        #list th:nth-child(1),
        #list th:nth-child(2),
        #list th:nth-child(3),
        #list th:nth-child(4) {
            width: auto;
        }

        #list th:nth-child(1) { width: 50px; }
        #list th:nth-child(3) { width: 500px; }
        #list th:nth-child(4) { width: 200px; }

        #list td {
            text-align: center;
        }

        #list td:nth-child(2),
        #list td:nth-child(3) {
            text-align: left;
        }

        /* 페이지바 스타일 */
        #pagebar {
            margin-top: 20px;
        }

        /* 검색 폼 스타일 */
        #search-form {
            margin-top: 20px;
            display: flex;
            align-items: center;
        }

        #search-form select,
        #search-form input[type="text"] {
            padding: 8px;
            margin-right: 10px;
        }

        #search-form input[type="submit"] {
            padding: 8px 16px;
            background-color: #4caf50;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        /* 버튼 스타일 */
        .list {
            padding: 8px 16px;
            background-color: #333;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <!-- 헤더 영역 -->
    <%@ include file="/WEB-INF/views/inc/header.jsp" %>
    
    <main id="main">
        <!-- 서브 제목 -->
        <h1 class="sub">의학 매거진
            <small>
                <c:if test="${map.search == 'y'}">검색결과</c:if>
            </small>
        </h1>
        
        <!-- 매거진 리스트 테이블 -->
        <table id="list">
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>부제</th>
                <th>날짜</th>
                <th>조회수</th>
            </tr>
            <c:forEach items="${list}" var="dto">
                <tr>
                    <td>${dto.magazineSeq}</td>
                    <td>
                        <!-- 제목 및 링크 -->
                        <a href="/apa/magazine/view.do?magazineSeq=${dto.magazineSeq}&search=${map.search}&column=${map.column}&word=${map.word}">
                            ${dto.magazineTitle}
                        </a>
                        <!-- 조건부 요소 -->
                        <c:if test="${dto.ccnt > 0}"></c:if>
                        <c:if test="${dto.isnew == 1}">
                            <span class='is-new'>new</span>
                        </c:if>
                    </td>
                    <td>${dto.magazineSubTitle}</td>
                    <td>${dto.magazineDate}</td>
                    <td>${dto.magazineReadcount}</td>
                </tr>
            </c:forEach>
        </table>

        <!-- 페이지바 -->
        <div id="pagebar">${pagebar}</div>
        
        <!-- 검색 폼 -->
        <form id="search-form" action="/apa/magazine/list.do" method="GET">
            <select name="column">
                <option value="magazineTitle">제목</option>
                <option value="magazineSubTitle">부제</option>
                <option value="magazineContent">내용</option>
            </select>
            <input type="text" name="word" class="long" required>
            <input type="submit" value="검색하기">
        </form>
        
        <!-- 버튼 영역 -->
        <div>
            <button type="button" class="add primary" onclick="location.href='/apa/magazine/add.do';">글쓰기</button>
        </div>
    </main>
    <!-- 스크립트 영역 -->
    <script>
        <!-- 검색 관련 스크립트 -->
        <c:if test="${map.search == 'y'}">
            $('select[name=column]').val('${map.column}');
            $('input[name=word]').val('${map.word}');
        </c:if>
        
        <!-- 페이지 선택 스크립트 -->
        $('#selPage').val(${nowPage});
    </script>
</body>
</html>