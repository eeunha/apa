<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="/WEB-INF/views/inc/asset.jsp" %>
    <style>

        #main {
            margin-right: 600px;
            margin-left: 600px;
            margin-top: 130px;
            margin-bottom: 130px;

        }

        #main {
            color: #5bc1ac;
            font-size: 30px;
        }

        #main > tr{
            line-hight: 17px;
        }

    </style>
</head>
<body>
<!-- /user/login.jsp -->
<%@ include file="/WEB-INF/views/inc/header.jsp" %>

<main id="main">
    "가입을 축하드립니다."
    <div>
        <li class="nav-item ms-3">
            <a class="nav-link custom-btn custom-border-btn btn" href="/apa/main.do">메인으로</a>
        </li>
        <li class="nav-item ms-3">
            <a class="nav-link custom-btn custom-border-btn btn" href="/apa/pharmacy/pharmacist/add.do">대표약사 추가하기</a>
        </li>
    </div>
</main>
<%@ include file="/WEB-INF/views/inc/footer.jsp" %>

</body>
</html>