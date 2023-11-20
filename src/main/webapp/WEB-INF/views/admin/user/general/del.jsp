<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="/WEB-INF/views/inc/asset.jsp" %>
    <style>
        /* 추가적인 스타일은 여기에 작성 */
    </style>
</head>
<body>
    <!-- /admin/user/general/del.jsp -->
    <%@ include file="/WEB-INF/views/inc/header.jsp" %>
    
    <main id="main">
        <h1>회원 정보 <small>삭제하기</small></h1>
        <form id="deleteForm" method="POST" action="/apa/admin/user/general/del.do">
            <div>
                <button type="button" class="del primary" onclick="confirmDelete();">삭제하기</button>
                <button type="button" class="back" onclick="cancle();">돌아가기</button>
                
            </div>
            <input type="hidden" name="userSeq" value="${userSeq}">
        </form>
    </main>

    <script>

        function confirmDelete() {
            if (confirm('정말로 삭제하시겠습니까?')) {
                // 확인을 눌렀을 때 서버로 데이터 전송
                document.getElementById('deleteForm').submit();
                // 서버로의 전송이 완료되면 아래의 메시지를 보여줄 수 있습니다.
                alert('삭제가 완료되었습니다.');
            } else {
                alert('삭제가 취소되었습니다.');
				window.close();
            }
        }
        
        function cancle() {
			window.close();
        }
    </script>
</body>
</html>
