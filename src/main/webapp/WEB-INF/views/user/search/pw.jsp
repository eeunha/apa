<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
            width: 70%;
            background-color: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.1);

        }

        #main > h1 {
            color: #5bc1ac;
            font-size: 30px;
            text-align: center;
        }

        input[type=text], input[type=email] input[type=password]{

            width: 100%;
            padding: 12px 20px;
            margin: 10px 10px;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        button[type=submit] {
            width: 100%;
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


        #pw_search_box {
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

        .teltext {
            display: flex;
            margin: 10px 0px;
        }

        .teltext > span {
            margin: 10px 10px;
        }

        input[type=text], input[type=email] input[type=password] {
            width: 100%;
            padding: 12px 20px;
            margin: 10px 0px;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="password" i] {
            width: 100%;
            padding: 12px 20px;
            margin: 10px 0px;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
    </style>
</head>
<body>
<!-- /user/search/id.jsp -->
<%@ include file="/WEB-INF/views/inc/header.jsp" %>

<main id="main">
    <h1 class="h3 mb-0 text-gray-800 hansans" style="padding-top: 20px;">비밀번호 찾기</h1>
    <form method="POST" action="/apa/user/search/pw.do">
        <div id="pw_search_box">
            <label for="select_user" class="checked-label">일반회원</label>
            <input type="checkbox" id="select_user" name="checked" value="1" onclick="clickCheck(this)" class="cb"
                   checked>

            <label for="select_hospital">병원</label>
            <input type="checkbox" id="select_hospital" name="checked" value="2" onclick="clickCheck(this)" class="cb">

            <label for="select_pharmacy">약국</label>
            <input type="checkbox" id="select_pharmacy" name="checked" value="3" onclick="clickCheck(this)" class="cb">
        </div>
        <br><br>
        <table class="vertical">
            <tr>
                <th>아이디</th>
                <td><input type="text" name="id" id="id" required class="short"></td>
            </tr>
            <tr>
                <th>이름</th>
                <td><input type="text" name="name" id="name" required class="short"></td>
            </tr>
            <tr>
                <th>전화번호</th>
                <td>
                    <div class="teltext">
                        <input type="text" name="tel1" size="4">&nbsp;-&nbsp;
                        <input type="text" name="tel2" size="4">&nbsp;-&nbsp;
                        <input type="text" name="tel3" size="4">
                    </div>
                </td>
            </tr>
            <tr>
                <th>새 비밀번호</th>
                <td>
                    <input type="password" name="pw" id="pw" required>
                </td>
            </tr>
            <tr>
                <th>비밀번호확인</th>
                <td>
                    <input type="password" name="pwchecked" id="pwchecked" required>
                </td>
            </tr>
        </table>
        <br>
        <div id="btn-box">
            <button type="submit" id="login_button">확인</button>
        </div>
        <%--				<button type="button" class="back" onclick="location.href='/main.do';">돌아가기</button>--%>
    </form>
</main>

<script>
    function clickCheck(target) {
        document.querySelectorAll(`input[type=checkbox]`)
            .forEach(el => el.checked = false);

        target.checked = true;

    }

    function checkAll() {
        if (!checkUserId(form.id.value)) {
            return false;
        } else if (!checkPassword(form.id.value, form.pw.value, form.pwchecked.value)) {
            return false;
        } else if (!checkName(form.name.value)) {
            return false;
        }
        return true;
    }


    function checkExistData(value, dataName) {
        if (value == "") {
            alert(dataName + "입력해주세요!");
            return false;
        }
        return true;
    }
    function checkUserId(id) {
        if (!checkExistData(id, "아이디를"))
            return false;

        var idRegExp = /^(?=.*?[a-z])(?=.*?[0-9]).{1,10}$/;
        if (!idRegExp.test(id)) {
            alert("아이디는 영문 소문자와 숫자 4~12자리로 입력해야합니다!");
            form.id.value = "";
            form.id.focus();
            return false;
        }
        return true;
    }

    function checkPassword(id, pw, pwchecked) {
        if (!checkExistData(pw, "비밀번호를"))
            return false;
        if (!checkExistData(pwchecked, "비밀번호 확인을"))
            return false;
        var pwRegExp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{4,16}$/;
        if (!pwRegExp.test(pw)) {
            alert("비밀번호는 영문 대소문자와 특수문자, 숫자 4~12자리로 입력해야합니다!");
            form.pw.value = "";
            form.pw.focus();
            return false;
        }

        if (pw != pwchecked) {

            alert("두 비밀번호가 맞지 않습니다.");
            form.pw.value = "";
            form.pwchecked.value = "";
            form.pwchecked.focus();
            return false;
        }

        if (id == pw) {
            alert("아이디와 비밀번호는 같을 수 없습니다!");
            form.pw.value = "";
            form.pwchecked.value = "";
            form.pwchecked.focus();
            return false;
        }
        return true;
    }
    function checkName(name) {
        if (!checkExistData(name, "이름을"))
            return false;

        var nameRegExp = /^[가-힣]{2,15}$/;
        if (!nameRegExp.test(name)) {
            alert("이름이 올바르지 않습니다.");
            return false;
        }
        return true;
    }

    function clickCheck(element) {
        var label = document.querySelector('label[for=' + element.id + ']');

        if (element.checked) {
            label.classList.add('checked-label');
        } else {
            label.classList.remove('checked-label');
        }

        // Ensure only one checkbox is checked at a time
        var checkboxes = document.querySelectorAll('#pw_search_box input[type="checkbox"]');
        checkboxes.forEach(function (checkbox) {
            if (checkbox !== element) {
                checkbox.checked = false;
                document.querySelector('label[for=' + checkbox.id + ']').classList.remove('checked-label');
            }
        });
    }
</script>
</body>
</html>