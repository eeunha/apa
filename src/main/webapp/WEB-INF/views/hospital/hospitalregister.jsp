<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="/WEB-INF/views/inc/asset.jsp" %>
    <style>

        input {
            border: 1px solid #c6c6c6;
            text-align: center;
            border-radius: 5px;
            outline-color: #1cc88a;
        }

        #address {
            width: 25em;
        }

        #my-info {
            display: flex;
            align-items: center;
            margin-bottom: 30px;
            justify-content: flex-start;
        }

        #my-info-name {
            color: #666666;
            height: 400px;
            display: grid;
            font-weight: bold;
            align-items: center;
            margin-right: 100px;
            padding-left: 20px;
        }

        .my-info-name-child {
            height: 20px;
        }

        .my-info-name-child:last-child {
            margin-bottom: 0px;
        }

        #my-info-input {
            height: 400px;
            display: grid;
            align-items: center;
        }

        .my-info-input-child {
        }

        #info-register-btn {
            text-align: center;
        }

        #register-btn {
            border: 0;
            width: 7em;
            height: 2em;
            color: white;
            font-size: 1em;
            border-radius: 0.35rem;
            background-color: #5BC1AC;
        }

        .mb-5{
            margin-bottom: 3rem!important;
            margin-top: 3rem!important;
        }

        .h3, h1, h2, h3, h4, h5, h6 {
            margin-top: 0;
            margin-bottom: 0.5rem;
            font-weight: 500;
            line-height: 1.2;
            padding-top: 40px;
        }
        @media (min-width: 1200px)
        .h3, h3 {
            font-size: 2rem;
        }
    </style>
</head>
<body id="page-top">
<!-- /user/register.jsp -->
<%@ include file="/WEB-INF/views/inc/header.jsp" %>
<div id="wrapper">
    <main id="main">
        <div class="container-fluid">

            <!-- Content Row -->
            <div class="row">
                <!-- Area Chart -->
                <div class="col-xl-12">
                    <div class="card shadow mb-5" style="margin: 0 auto; width: 80%;">
                        <!-- Card Header - Dropdown -->
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-800 hansans" style="margin-left: 40px;">병원 회원가입</h1>
                        </div>
                        <!-- Card Body -->
                        <div class="card-body">
                            <form name="form" method="POST" action="/apa/hospital/hospitalregister.do"
                                  onsubmit="return checkAll()">
                                <div id="my-info">
                                    <div id="my-info-name">
                                        <div class="my-info-name-child">상호명</div>
                                        <div class="my-info-name-child">진료과목</div>
                                        <div class="my-info-name-child">사업자등록번호</div>
                                        <div class="my-info-name-child">아이디</div>
                                        <div class="my-info-name-child">비밀번호</div>
                                        <div class="my-info-name-child">비밀번호 확인</div>
                                        <div class="my-info-name-child">연락처</div>
                                        <div class="my-info-name-child">이메일</div>
                                        <div class="my-info-name-child">주소</div>
                                        <div class="my-info-name-child">개인정보 이용동의</div>
                                    </div>
                                    <div id="my-info-input">
                                        <div class="my-info-input-child"><input type="text" name="name"></div>
                                        <div class="my-info-input-child">
                                            <div class="dropdown">
                                                <button class="btn btn-secondary dropdown-toggle" id="btn1" type="button"
                                                        data-bs-toggle="dropdown" aria-expanded="false" size="">
                                                    선택
                                                </button>
                                                <input type="hidden" name="department" id="department">
                                                <ul class="dropdown-menu" id="selectedValue">
                                                    <li><a class="dropdown-item" href="#" value="1">산부인과</a></li>
                                                    <li><a class="dropdown-item" href="#" value="2">이비인후과</a></li>
                                                    <li><a class="dropdown-item" href="#" value="3">정형외과</a></li>
                                                    <li><a class="dropdown-item" href="#" value="4">피부과</a></li>
                                                    <li><a class="dropdown-item" href="#" value="5">내과</a></li>
                                                    <li><a class="dropdown-item" href="#" value="6">안과</a></li>
                                                    <li><a class="dropdown-item" href="#" value="7">치과</a></li>
                                                    <li><a class="dropdown-item" href="#" value="8">한의원</a></li>
                                                    <li><a class="dropdown-item" href="#" value="9">비뇨기과</a></li>
                                                    <li><a class="dropdown-item" href="#" value="10">신경외과</a></li>
                                                    <li><a class="dropdown-item" href="#" value="11">가정의학과</a></li>
                                                    <li><a class="dropdown-item" href="#" value="12">외과</a></li>
                                                    <li><a class="dropdown-item" href="#" value="13">정신의학과</a></li>
                                                    <li><a class="dropdown-item" href="#" value="14">소아과</a></li>
                                                    <li><a class="dropdown-item" href="#" value="15">신경과</a></li>
                                                    <li><a class="dropdown-item" href="#" value="16">성형외과</a></li>
                                                    <li><a class="dropdown-item" href="#" value="17">재활의학과</a></li>
                                                    <li><a class="dropdown-item" href="#" value="18">마취통증의학과</a></li>
                                                    <li><a class="dropdown-item" href="#" value="19">영상의학과</a></li>
                                                    <li><a class="dropdown-item" href="#" value="20">응급의학과</a></li>
                                                    <li><a class="dropdown-item" href="#" value="21">기타</a></li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="my-info-input-child">
                                            <input type="text" size="4" id="ssn1" name="ssn1"> - 
                                            <input type="text" size="3" id="ssn2" name="ssn2"> - 
                                            <input type="text" size="5" id="ssn3" name="ssn3">
                                        </div>
                                        <div class="my-info-input-child"><input type="text" name="id" maxlength="12"></div>
                                        <div class="my-info-input-child"><input type="password" name="pw" maxlength="16"></div>
                                        <div class="my-info-input-child"><input type="password" name="pwchecked" maxlength="16"></div>
                                        <div class="my-info-input-child">
                                            <input type="text" name="tel1" size="4" maxlength="4"> -
                                            <input type="text" name="tel2" size="4" maxlength="4"> -
                                            <input type="text" name="tel3" size="4" maxlength="4">
                                        </div>
                                        <div class="my-info-input-child"><input type="email" name="email" required>
                                        </div>
                                        <div class="my-info-input-child"><input type="text" name="address" id="address" required></div>
                                        <div class="my-info-input-child"><input type="checkbox" name="info"
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


            document.querySelectorAll('.dropdown-menu a').forEach(function (element) {
                element.addEventListener('click', function (e) {
                    var selectedValue = e.target.getAttribute('value');
                    var dropdownButton = e.target.closest('.dropdown').querySelector('button');
                    dropdownButton.textContent = e.target.textContent;
                    document.getElementById('department').value = selectedValue;
                });
            });

            function checkAll() {
                if (!checkUserId(form.id.value)) {
                    return false;
                } else if (!checkPassword(form.id.value, form.pw.value, form.pwchecked.value)) {
                    return false;
                } else if (!checkMail(form.email.value)) {
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
                    alert("비밀번호는 영문 대소문자와 특수문자, 숫자 4~16자리로 입력해야합니다!");
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

            function checkMail(email) {
                if (!checkExistData(email, "이메일을"))
                    return false;
                var emailRegExp = /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/;
                if (!emailRegExp.test(email)) {
                    alert("이메일 형식이 올바르지 않습니다!");
                    form.email.value = "";
                    form.email.focus();
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


        </script>
</body>
</html>