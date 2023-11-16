<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<style>
#main{
	padding: 105px 300px 200px;	
	
}	

#contentSector{
	border-top: 6px solid #EEF5F3;
}

#view {
/* 	width:1300px;
 */	/* background-color: green; */
 	height: 400px;
 	border: 1px solid #DCF0EC;
 	background-color: #DCF0EC;
 	font-size: 1.1rem;
 	margin-top: 35px;
}

#view th:nth-child(1) {
	width: 80px; 
	text-align: center;
	/* border-bottom: 6px solid #EEF5F3; */
	border-right: 6px solid #EEF5F3;
	font-size: 1.1rem;
}

#num{
	width:5px;
	height: 50px;
	padding-left: 10px;
	font-size: 1.1rem;
	border-bottom: 6px solid #EEF5F3;
}
#id {
	border-right: 6px solid #EEF5F3;
	width:80px;
	text-align: center;
	font-size: 1.1rem;
	border-bottom: 6px solid #EEF5F3;
}
#subject {
	width:300px;
	height: 80px;
	padding-left: 10px;
	font-size: 1.1rem;
}
#content{
	width: 1100px;
	height: auto;
	border-top: 6px solid #EEF5F3;
	padding-left: 15px;
	padding-right: 10px;
	font-size: 1.1rem;
}
#date{
	width: 70px;
	border-right: 6px solid #EEF5F3;
	text-align: center;
	font-size: 1.1rem;
}
#getId{
	width: 120px;
	padding-left: 10px;
	font-size: 1.1rem;
}
#getDate{
	width: 220px; 
	padding-left: 10px;
	font-size: 1.1rem;
}
#subjectSector{
	border-top: 6px solid #EEF5F3;
	font-size: 1.1rem;
}
#list-comment {width: 90%;}
#list-comment td:nth-child(1) { width: auto; }
#list-comment td:nth-child(2) { width: 160px; text-align: center; }
	
#list-comment td:nth-child(1) > div { 
	display: flex;		
	justify-content: space-between; 
	/* background-color: gold; */
	height: 40px;
	padding-top: 5px;
	margin-top: 4px;
	
}
	
#list-comment td:nth-child(1) > div > div:nth-child(2) {
	font-size: 12px;
	color: #777;
	padding-top: 5px;
	
}

.btn5 {
	display: inline-block;
}

#comment{
	width: 1080px;
	height: 200px;
	border-radius: 6px;
	border: 1px solid grey;
}
.bottomBtn{
	display: flex;
	padding-bottom: 10px;
	padding-top: 10px;
	
}
.comment{
	background-color: #DCF0EC;
	height:200px;
	width: 80px;
	border-radius: 6px;
	border: 1px solid grey;

}
.edit, .del, .back, .cancel{
	border-radius: 4px;
	border: 1px solid #D0E5E1;
	background-color: #D0E5E1;
}

#main > div:nth-child(4){
	text-align: right;
	margin-right: 150px;
	margin-bottom: 20px;
}
</style>
</head>
<body>
	<!-- /community/view.jsp -->
	<%@ include file="/WEB-INF/views/inc/header.jsp" %>
	
		<main id="main">
		<h1 class="sub hansans">커뮤니티 <small>상세보기</small></h1>
		<table class="vertical" id="view">
			<tr>
				<th>번호</th>
				<td id="num">${dto.communitySeq}</td>
				<td></td>
				<th id="id">아이디</th>
				<td id="getId">${dto.id}</td>
				<td></td>
				<th id="date">날짜</th>
				<td id="getDate">${dto.regdate}</td>
			</tr>
			<tr id="subjectSector">
				<th>제목</th>
				<td id="subject">${dto.subject}</td>
			</tr>
			<tr>
				<th id="contentSector">내용</th>
				<td colspan="8" id="content">${dto.content}</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
			</tr>
			<tr>
			</tr>
		</table>
			<div class="bottomBtn">
							</div>	
		<div>
			<button type="button" class="back" onclick="location.href='/apa/community/list.do';">뒤로가기</button>
			
			<c:if test="${not empty id && (dto.id==id || lv == 0)}">
			<button type="button" class="edit" onclick="location.href='/apa/community/edit.do?seq=${dto.communitySeq}';">수정하기</button>
			<button type="button" class="del" onclick="location.href='/apa/community/del.do?seq=${dto.communitySeq}';">삭제하기</button>
			</c:if>
		</div>
		
		
		<c:if test="${not empty id}">
		<table id="add-comment">
			<tr>
				<td><input type="text" name="comment" id="comment" class="full"></td>
				<td><button type="button" class="comment" id="btnComment">등록</button></td>
			</tr>
		</table>
		</c:if>
		
		<table id="list-comment">
			<tbody>
				
			</tbody>
		</table>
		
		</main>
	   <%@ include file="/WEB-INF/views/inc/footer.jsp" %>
	<script>
	
	//댓글쓰기
	$('#btnComment').click(function() {
		var content = $('#comment').val();
		$.ajax({
			type: 'POST',
			url: '/apa/community/comment.do',
			data: {
				content,
				seq: ${seq}
			},
				dataType: 'json',
				success: function(result) {
				
		
				if (result.result == 1) {
					load(); //목록 새로고침
			}
			
				$('#comment').val('');//초기화
				},
				error: function(a,b,c) {
					console.log(a,b,c);
				}
			});
			
		});
		
		$('#comment').keydown(function() {
			
			if (event.keyCode == 13) { //엔터(\r)
				$('#btnComment').click();	
			}
			
		});
		

	
	load();
	
	//댓글 목록 가져오기(ajax) > 화면에 출력
	function load() {
		
		$.ajax({
			type: 'GET',
			url: '/apa//community/comment.do',
			data: 'communitySeq=${dto.communitySeq}',
			dataType: 'json',
			success: function(result) {
				//result == 댓글 목록
				
				$('#list-comment tbody').html(''); //기존 내용 삭제
				
				$(result).each((index, item) => {
					
					//console.log(item);
					
					let temp = `
						<tr>
							<td>
								<div>
									<div>\${item.content}</div>
									<div>\${item.regdate}</div>
								</div>
							</td>
							<td>
							  	<div class="btn5">\${item.id}</div>
						`;
						
					if (item.id == '${id}') {
					temp += `
							  	<c:if test="${not empty id}">
								<div class="btn5">
									<button type="button" class="edit" onclick="editComment(\${item.seq});">수정</button>
									<button type="button" class="del" onclick="delComment(\${item.seq});">삭제</button>
								</div>					
								</c:if>
						`;
					}
						
					temp += `
							</td>
						</tr>								
						
					`;
					
					$('#list-comment tbody').append(temp);
					
					
				});
				
			},
			error: function(a,b,c) {
				console.log(a,b,c);
			}
		});
		
	}
	function delComment(seq) {
		
		if (confirm('댓글을 삭제하시겠습니까?')) {
			$.ajax({
				type: 'POST',
				url: '/apa/community/delcomment.do',
				data: 'seq=' + seq,
				dataType: 'json',
				success: function(result) {
					
			
					if (result.result == 1) {
						load(); //목록 새로고침
					}
					
				},
				error: function(a,b,c) {
					console.log(a,b,c);
				}
			});
		}
		
	}
	
	function editComment(seq) {
		
		//alert($(event.target).parent().parent().prev().children().eq(0).children().eq(0).text());
		
		let val = $(event.target).parent().parent().prev().children().eq(0).children().eq(0).text();
				
		$('.edit-comment').remove();
		
		let temp = `
		
			<tr class="edit-comment">
				<td><input type="text" name="ecomment" id="ecomment" class="long" value="\${val}"></td>
				<td>
					<button type="button" class="edit" onclick="editCommentOk(\${seq});">완료</button>
					<button type="button" class="cancel" onclick="$('.edit-comment').remove();">취소</button>
				</td>
			</tr>
		
		`;
		
		$(event.target).parent().parent().parent().after(temp);
		
	}
	
	
	function editCommentOk(seq) {
		
		//alert($('#ecomment').val());
		//alert(seq);
		
		$.ajax({
			type: 'POST',
			url: '/apa/community/editcomment.do',
			data: {
				content: $('#ecomment').val(),
				seq: seq
			},
			dataType: 'json',
			success: function(result) {
				
				if (result.result == 1) {
					load(); //새로 고침
				}
				
			},
			error: function(a,b,c) {
				console.log(a,b,c);
			}
		});
		
	}

	</script>
</body>
</html>