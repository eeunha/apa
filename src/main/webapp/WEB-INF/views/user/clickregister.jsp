<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="/WEB-INF/views/inc/asset.jsp" %>


    <style>

        input[type=button] {
            width: 300px;
            height: 200px;
            border-radius: 10px;
            font-size: 24px;
            box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
            background-color: white;
            color: #5bc1ac;
            border: none;
            margin-top: 100px;
            margin-bottom: 250px;
            cursor: pointer;
        }

        input[type=button]:hover {
            background-color: #5bc1ac;
            color: white;
        }

        .h3, h3 {
            margin-top: 30px;
            font-size: 3rem;
            text-align: center;
        }



    </style>
</head>
<body>
<%@ include file="/WEB-INF/views/inc/header.jsp" %>
<h1 class="h3 mb-0 text-gray-800 hansans" style="padding-top: 20px;">일반 회원가입</h1>
<div style="width:80%;margin:0 auto;display:flex;justify-content: space-around;">
    <input type="button" id="select_user" value="일반회원" onclick="location.href='/apa/user/userregister.do'">
    <input type="button" id="select_hospital" value="병원회원" onclick="location.href='/apa/hospital/hospitalregister.do'">
    <input type="button" id="select_pharmacy" value="약국회원" onclick="location.href='/apa/pharmacy/pharmacyregister.do'">
</div>


<%@ include file="/WEB-INF/views/inc/footer.jsp" %>
<script>

</script>
</body>
</html>
