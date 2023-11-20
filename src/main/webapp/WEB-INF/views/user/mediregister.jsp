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

    #main > h1{
      color: #5bc1ac;
      font-size: 30px;
    }

    #main > tr{
      line-hight: 17px;
    }

  </style>
</head>
<body>
<!-- /user/register.jsp -->
<%@ include file="/WEB-INF/views/inc/header.jsp" %>

<main id="main">
  <h1 title="sub" class>병원/약국 회원 <small>가입하기</small></h1>

  <form method="POST" action="/user/mediregister.do" enctype="multipart/form-data">
    <table class="vertical">
      <tr>
        <th>상호명</th>
        <td><input type="radio" name="username" id="username" required class="short"></td>
      </tr>
      <tr>
        <th>이름</th>
        <td><input type="text" name="username" id="username" required class="short"></td>
      </tr>
      <tr>
        <th>주민등록번호</th>
        <td><input type="text" name="userssn" id="userssn" required class="long"></td>
      </tr>
      <tr>
        <th>아이디</th>
        <td><input type="text" name="userid" id="userid" required class="short"></td>
      </tr>
      <tr>
        <th>비밀번호</th>
        <td><input type="password" name="userpw" id="userpw" required class="short"></td>
      </tr>
      <tr>
        <th>비밀번호 확인</th>
        <td><input type="password" name="userpwcheck" id="userpwcheck" required class="short"></td>
      </tr>
      <tr>
        <th>전화 번호</th>
        <td><input type="text" name="usertel" id="usertel" required class="long"></td>
      </tr>
      <tr>
        <th>이메일</th>
        <td><input type="text" name="useremail" id="useremail" required class="long"></td>
      </tr>
      <tr>
        <th>자택주소</th>
        <td><textarea name="useraddress" id="useraddress" class="long" required></textarea></td>
      </tr>
      <tr>
        <th>자녀</th>
        <td>
          <input type="button" value="추가하기" onclick="location.href=자녀등록페이지">
        </td>
      </tr>
      <tr>
        <th>개인정보수집</th>
        <br>
        <td>
          동의<input type="checkbox" id="">
        </td>
      </tr>
    </table>
    <div>
      <button type="button" class="back" onclick="location.href='/user/clickregister.do';">돌아가기</button>
      <button type="submit" class="add primary">가입하기</button>
    </div>
  </form>
</main>
<%@ include file="/WEB-INF/views/inc/footer.jsp" %>


<script>

</script>
</body>
</html>