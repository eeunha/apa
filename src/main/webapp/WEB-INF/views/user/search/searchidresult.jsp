<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <%@ include file="/WEB-INF/views/inc/asset.jsp" %>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f4f4f4;
    }
    #main {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      height: 80vh;
      margin: auto;
      background-color: #fff;
      padding: 20px;
    }
    table {
      width: 100%;
      margin: 20px 0;
      border-collapse: collapse;
      text-align: center;
    }
    th {
      color: #5bc1ac;
    }
    tr:nth-child(even) {background-color: #f2f2f2;}
    .nav-item {
      display: inherit;
      justify-content: center;
      position: absolute;
      bottom: 30%;
      width: 100%;
    }
    #btn1, #btn2 {
      background-color: #5bc1ac;
      border: none;
      color: white;
      padding: 15px 32px;
      text-align: center;
      text-decoration: none;
      display: inline-block;
      font-size: 16px;
      margin: 4px 2px;
      cursor: pointer;
    }

    #main > h1 {
      color: #5bc1ac;
      font-size: 30px;
      text-align: center;
    }

    #btn-box {
        margin-top: 20px;
    }



  </style>
</head>
<body>
<!-- /user/search/pw.jsp -->
<%@ include file="/WEB-INF/views/inc/header.jsp" %>


<main id="main">
 <h1 class="h3 mb-0 text-gray-800 hansans" style="padding-top: 20px;">"아이디 찾기 성공!"<br>
     "회원님의 아이디는<strong class="h3 mb-0 text-gray-800 hansans" style="color: #2a96a5"> "<%=request.getSession().getAttribute("id")%>" </strong>입니다."</h1>

  <div id="btn-box">
    <button type="submit" id="btn1" onclick=location.href="/apa/user/login.do">확인</button>
    <button type="submit" id="btn2"  onclick=location.href="/apa/user/search/pw.do">비밀번호 찾기</button>
  </div>
</main>
<%@ include file="/WEB-INF/views/inc/footer.jsp" %>
<script>

</script>
</body>
</html>