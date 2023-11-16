<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="/WEB-INF/views/inc/asset.jsp" %>
    <style>
        /*input {*/
        /*    border: 1px solid #c6c6c6;*/
        /*    text-align: center;*/
        /*    border-radius: 5px;*/
        /*    outline-color: #1cc88a;*/
        /*}*/

        /*#main {*/
        /*    font-size: 12px;*/
        /*    line-height: 2.1;*/
        /*}*/

        /*#address {*/
        /*    width: 25em;*/
        /*}*/

        /*#my-info {*/
        /*    display: flex;*/
        /*    align-items: center;*/
        /*    margin-bottom: 30px;*/
        /*    justify-content: flex-start;*/
        /*}*/

        /*#my-info-input {*/
        /*    height: 400px;*/
        /*    display: grid;*/
        /*    align-items: center;*/
        /*}*/

        /*#info-register-btn {*/
        /*    text-align: center;*/
        /*}*/

        /*#register-btn, #add-btn {*/
        /*    border: 0;*/
        /*    width: 7em;*/
        /*    height: 2em;*/
        /*    color: white;*/
        /*    font-size: 1em;*/
        /*    border-radius: 0.35rem;*/
        /*    background-color: #5BC1AC;*/
        /*}*/

        /*#normal {*/
        /*    color: #5bc1ac;*/
        /*}*/

        /*.col-xl-12 {*/
        /*    padding-left: 100px;*/
        /*    padding-right: 100px;*/
        /*    padding-top: 100px;*/
        /*}*/

        /*.card-body {*/
        /*    overflow: auto;*/
        /*}*/

        h1 {
            text-align: center;
            font-size: 2.5em;
            color: #5bc1ac;
        }

        .my-info-input-child {
            margin-bottom: 10px;
        }
        .my-info-input-child label {
            display: inline-block;
            width: 100px;
            text-align: right;
            margin-right: 10px;
        }
        .my-info-input-child input {
            width: 140px; /* 상자 크기를 30% 줄입니다 */
            height: 30px;
            border-radius: 5px;
            border: 1px solid #ccc;
            padding: 5px;
        }
        #info-add-btn{

            margin-top: 20px;
        }

        #info-register-btn {
            text-align: center;
            margin-top: 20px;
        }
        #add-btn {
            width: 110px;
            height: 30px;
            font-size: 16px;
            border-radius: 10px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            background-color: #5bc1ac;
            color: white;
            border: none;
            cursor: pointer;
            margin-left: 15px;
        }

        #register-btn {
            width: 300px;
            height: 50px;
            font-size: 20px;
            border-radius: 10px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            background-color: #5bc1ac;
            color: white;
            border: none;
            cursor: pointer;
        }
        #add-btn:hover, #register-btn:hover {
            background-color: white;
            color: #5bc1ac;
        }

        .col-xl-12{
            width: 50%;
            margin-left: 30%;
            margin-top: 5%;
            margin-bottom: 5%;
        }
    </style>
</head>
<body>
<!-- /user/register.jsp -->
<%@ include file="/WEB-INF/views/inc/header.jsp" %>

<main id="main">
    <div class="container-fluid">

        <!-- Content Row -->

        <div class="row">
            <!-- Area Chart -->
            <div class="col-xl-12">
                <div class="card shadow mb-5">
                    <!-- Card Header - Dropdown -->
                    <div id="register_title"
                         class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                        <h5 id="normal">일반 <small>회원가입</small></h5>
                    </div>
                    <!-- Card Body -->
                    <div class="card-body">
                        <form name="form" method="POST" action="/apa/user/userregister.do" onsubmit="return checkAll()">
                            <div id="my-info">
                                <div id="my-info-input">
                                    <div class="my-info-input-child">이름 <input type="text" name="name"></div>
                                    <div class="my-info-input-child">아이디 <input type="text" name="id"></div>
                                    <div class="my-info-input-child">비밀번호 <input type="password" name="pw"></div>
                                    <div class="my-info-input-child">비밀번호 확인 <input type="password" name="pwchecked">
                                    </div>
                                    <div class="my-info-input-child"> 주민등록번호
                                        <input type="text" size="7" id="ssn1" name="ssn1"> -
                                        <input type="password" size="9" id="ssn2" name="ssn2">
                                    </div>
                                    <div class="my-info-input-child">연락처
                                        <input type="text" name="tel1" size="4"> -
                                        <input type="text" name="tel2" size="4"> -
                                        <input type="text" name="tel3" size="4">
                                    </div>
                                    <div class="my-info-input-child">이메일 <input type="email" name="email" required>
                                    </div>
                                    <div class="my-info-input-child">주소 <input type="text" name="address" id="address"
                                                                               required></div>
                                    <div id="info-add-btn">
                                        <input type="button" id="add-btn" value="자녀등록">
                                    </div>
                                    <div class="my-info-input-child">개인정보 이용동의 <input type="checkbox" name="info"
                                                                                     id="info" required></div>

                                </div>
                            </div>
                            <div id="info-register-btn">
                                <input type="submit" id="register-btn" value="가입하기">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <%@ include file="/WEB-INF/views/inc/footer.jsp" %>

    <script>
        function clickCheck(target) {
            document.querySelectorAll(`input[type=checkbox]`)
                .forEach(el => el.checked = false);

            target.checked = true;

        }

        function checkAll(){
            if(!checkUserId(form.id.value)){
                return false;
            } else if(!checkPassword(form.id.value, form.pw.value,form.pwchecked.value)){
                return false;
            } else if(!checkMail(form.email.value)){
                return false;
            } else if(!checkName(form.name.value)){
                return false;
            } else if(!checkBirth(form.ssn1.value, form.ssn2.value)){
                return false;
            }
            return true;
        }

        function checkExistData(value, dataName){
            if(value == ""){
                alert(dataName + "입력해주세요!");
                return false;
            }
            return true;
        }

        function checkUserId(id){
            if(!checkExistData(id,"아이디를"))
                return false;

            var idRegExp = /^(?=.*?[a-z])(?=.*?[0-9]).{1,10}$/;
            if(!idRegExp.test(id)){
                alert("아이디는 영문 소문자와 숫자 4~12자리로 입력해야합니다!");
                form.id.value ="";
                form.id.focus();
                return false;
            }
            return true;
        }

        function checkPassword(id, pw, pwchecked) {
            if(!checkExistData(pw, "비밀번호를"))
                return false;
            if(!checkExistData(pwchecked, "비밀번호 확인을"))
                return false;
            var pwRegExp = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{4,16}$/;
            if(!pwRegExp.test(pw)){
                alert("비밀번호는 영문 대소문자와 특수문자, 숫자 4~12자리로 입력해야합니다!");
                form.pw.value = "";
                form.pw.focus();
                return false;
            }

            if(pw != pwchecked){

                alert("두 비밀번호가 맞지 않습니다.");
                form.pw.value ="";
                form.pwchecked.value ="";
                form.pwchecked.focus();
                return false;
            }

            if(id == pw){
                alert("아이디와 비밀번호는 같을 수 없습니다!");
                form.pw.value = "";
                form.pwchecked.value = "";
                form.pwchecked.focus();
                return false;
            }
            return true;
        }

        function checkMail(email){
            if(!checkExistData(email, "이메일을"))
                return false;
            var emailRegExp = /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/;
            if(!emailRegExp.test(email)){
                alert("이메일 형식이 올바르지 않습니다!");
                form.email.value = "";
                form.email.focus();
                return false;
            }
            return true;

        }

        function checkName(name){
            if(!checkExistData(name, "이름을"))
                return false;

            var nameRegExp = /^[가-힣]{2,15}$/;
            if(!nameRegExp.test(name)){
                alert("이름이 올바르지 않습니다.");
                return false;
            }
            return true;
        }

        function checkBirth(ssn1, ssn2){
            if(!checkExistData(ssn1, "주민등록번호를")
                    || !checkExistData(ssn2, "주민등록번호를"))
                return false;

            var totalSsn = "" + ssn1 + ssn2;

            var ssnArr = new Array();
            var sum = 0;
            var plus = 2;

            for (var i = 0; i < 12; i++){
                ssnArr[i] = totalSsn.charAt(i);
                if(i >= 0 && i <=7){
                    sum += totalSsn[i] * plus;
                    plus++;
                    if(i == 7)
                        plus = 2;
                } else {
                    sum += totalSsn[i] * plus;
                    plus++;
                }
            }

            if(ssnArr.length < 12){
                alert("주민등록번호는 13자리 입니다.");
                form.ssn1.value = "";
                form.ssn2.value = "";
                form.ssn1.focus();
                return false;
            }

            var result = 11 - (sum % 11) % 10
            if(result != totalSsn.charAt(12)){
                alert("유효하지않은 주민번호입니다.");
                form.ssn1.value = "";
                form.ssn2.value = "";
                form.ssn1.focus();
                return false;
            }
            return true;

        }


    </script>
</body>
</html>