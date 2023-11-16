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
            background-color: #f8f9fa;
            font-family: 'Roboto', sans-serif;
        }

        #main {
            margin: 130px auto;
            width: 60%;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
        }

        #main > h1 {
            color: #5bc1ac;
            font-size: 30px;
            text-align: center;
        }

        input[type=text], input[type=password] {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        button[type=submit] {
            width: 80%;
            background-color: #5bc1ac;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button[type=submit]:hover {
            background-color: #5bc1ac;
        }

        table.vertical {
            width: 80%;
            margin: 0 auto;
        }

        table.vertical input[type=text], table.vertical input[type=password] {
            width: 100%;
            display: block;
            margin: 0 auto;
        }

        #find {
            display: flex;
            justify-content: space-evenly;
            width: 50%;
            margin: 0 auto;
        }

        #login-box {
            height: 50px;
            display: flex;
            justify-content: space-around;
        }

        #btn-box {
            display: flex;
            justify-content: center;
        }

        .cb {
            display: none;
        }

        label {
            cursor: pointer;
            width: 100%;
            text-align: center;
            line-height: 50px;
            transition: all 0.5s;
        }

        .checked-label {
            color: white;
            background-color: #5bc1ac;
        }



    </style>
</head>
<body>
<!-- /user/login.jsp -->
<%@ include file="/WEB-INF/views/inc/header.jsp" %>

<main id="main">
    <form method="POST" action="/apa/user/login.do">
        <div id="login-box">
            <label for="select_user" class="checked-label">일반회원</label>
            <input type="checkbox" id="select_user" name="checked" value="1" onclick="clickCheck(this)" class="cb" checked>

            <label for="select_hospital">병원</label>
            <input type="checkbox" id="select_hospital" name="checked" value="2" onclick="clickCheck(this)" class="cb">

            <label for="select_pharmacy">약국</label>
            <input type="checkbox" id="select_pharmacy" name="checked" value="3" onclick="clickCheck(this)" class="cb">

<%--            <label for="select_admin" id="admin_click"></label>--%>
<%--            <input type="checkbox" id="select_admin" name="checked" value="4" onclick="clickCheck(this)" class="cb">--%>
        </div>

        <br><br>
        <table class="vertical">
            <tr>
                <th>아이디</th>
                <td><input type="text" name="id" id="id" required class="short"></td>
            </tr>
            <tr>
                <th>암호</th>
                <td><input type="password" name="pw" id="pw" required class="short"></td>
            </tr>
        </table>
        <br>
        <div id="find">
            <a href ="/apa/user/search/id.do">아이디 찾기</a>
            <a href ="/apa/user/search/pw.do">비밀번호 찾기</a>
        </div>
        <br>
        <div id="btn-box">
            <button type="submit" id="login_button">login</button>
        </div>
    </form>
</main>
<%@ include file="/WEB-INF/views/inc/footer.jsp" %>

<script>
    function clickCheck(target){
        document.querySelectorAll(`input[type=checkbox]`)
            .forEach(el => el.checked = false);

        target.checked = true;

    }

    function clickCheck(element) {
        var label = document.querySelector('label[for=' + element.id + ']');

        if (element.checked) {
            label.classList.add('checked-label');
        } else {
            label.classList.remove('checked-label');
        }

        // Ensure only one checkbox is checked at a time
        var checkboxes = document.querySelectorAll('#login-box input[type="checkbox"]');
        checkboxes.forEach(function(checkbox) {
            if (checkbox !== element) {
                checkbox.checked = false;
                document.querySelector('label[for=' + checkbox.id + ']').classList.remove('checked-label');
            }
        });
    }
</script>
</body>
</html>