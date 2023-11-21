<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<style>
	#popup {
	    display: flex;
	    align-items: center;
	    justify-content: center;
	    position: fixed;
	    top: 0;
	    left: 0;
	    width: 100%;
	    height: 100%;
	}
	.popup-content {
	    background: #fff;
	    border-radius: 15px;
	    text-align: center;
	    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	    position: relative;
	    max-width: 400px;
	    width: 80%;
	    margin-bottom: 20px;
	}
	.popup-header {
	    background: #5BC1AC;
	    padding: 10px;
	    border-top-left-radius: 15px;
	    border-top-right-radius: 15px;
	    display: flex;
	    justify-content: flex-end;
	    align-items: center;
	}
	.close-btn {
	    background: none;
	    border: none;
	    color: #fff;
	    font-size: 18px;
	    cursor: pointer;
	}
	.close-btn:hover {
	    color: #f0f0f0;
	}
	.popup-btn {
	    margin-top: 20px;
	    padding: 10px 20px;
	    cursor: pointer;
	    border-radius: 5px;
	    font-size: 16px;
	    font-weight: bold;
	    outline: none;
	    margin-bottom: 30px;
	}
	.popup-btn.confirm, .popup-btn.cancel {
	    background: #5BC1AC;
	    color: #fff;
	    border: 1px solid #5BC1AC;
	    transition: background 0.3s;
	}
	.popup-btn.confirm:hover, .popup-btn.cancel:hover {
	    background: #4DA094;
	}
	.popup-btn.cancel {
	    margin-left: 10px;
	}
</style>
</head>
<body>
	<!-- /admin/user/general/black/del.jsp -->
	
        <form id="deleteForm" method="POST" action="/apa/admin/user/general/black/del.do">
	        <div class="popup-overlay" id="popup">
	            <div class="popup-content">
	                <div class="popup-header">
	                    <button class="close-btn" onclick="closePopup()">&times;</button>
	                </div>
	                <p>블랙리스트에서 취소하시겠습니까?</p>
	                <button type="button" class="popup-btn confirm" onclick="confirmDelete()">확인</button>
	                <button class="popup-btn cancel" onclick="cancle();">취소</button>
	            </div>
	            <input type="hidden" name="userSeq" value="${dto.userSeq}">
	        </div>
        </form>

    <script>
	    function confirmDelete() {
	        if (confirm('취소하시겠습니까?')) {
	            // 확인을 눌렀을 때 서버로 데이터 전송
	            document.getElementById('deleteForm').submit();
	            // 서버로의 전송이 완료되면 아래의 메시지를 보여줄 수 있습니다.
	            alert('완료되었습니다.');
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