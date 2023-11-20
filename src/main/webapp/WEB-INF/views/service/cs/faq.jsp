<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
    	<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">
    	
        <%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<style>

	#box-50 {
		height: 50px;
	}

	#box-10 {
		height: 10px;
	}

	h1 {
		width: 80%;
		margin: 0 auto;
	}

	hr {
		width: 80%;
		margin: 0 auto;
		height: 2px;
		background-color: #666;
		border: 0;
	}

	.question-box {
		width: 80%;
		margin: 0 auto;
		display: flex;
		align-items: center;
		cursor: pointer;
	}

	.fa-q {
		background-color: #5bc1ac;
		color: white;
		padding: 10px;
		border: 0;
		border-radius: 100px;
		font-size: 1.5rem;		
	}
	
	.question {
		width: 100%;
		font-size: 1.5rem;
		margin-left: 10px;
		margin-right: auto;
	}
	
	.question-box:hover .question{
		background-color: #CCC;
	}
	
	.answer-box {
		width: 80%;
		height: 150px;
		margin: 0 auto;
		background-color: #abebde;
     	opacity: 0;
	    max-height: 0;
	    overflow: hidden;
	    transition: opacity 0.5s, max-height 0.3s;		
	}
	
	.answer-box.open {
		opacity: 1;
		max-height: 1000px;
	}	
	
	textarea {
		background-color: #d6fff6;
		padding: 15px;
		border: 0;
		resize: none;
		font-size: 1.3rem;
		width: 100%;
		height: 100%;
	}
	
</style>
    </head>
<body id="section_1">
    <%@ include file="/WEB-INF/views/inc/header.jsp" %>

 	<div id="box-50"></div>
    <h1 class ="hansans" style="color: #5bc1ac;">FAQ</h1>
    <hr>
    <div id="box-50"></div>

	<div class="question-box">
		<div><i class="fa-solid fa-q"></i></div>
		<div class="question">병원 예약 후 취소할 수 있나요?</div>
	</div>
	
	<div id="box-10"></div>
	<hr>
	<div id="box-10"></div>

	<div class="answer-box">
		<textarea disabled>취소할 수 있지만 30분 내외로 취소하여야 합니다. 
그러지 못할 경우에 회원님의 계정에 신고와 경고 횟수가 누적될 수 있으므로 이점 유의하시길 바랍니다.</textarea>
	</div>

    <div id="box-50"></div>

	<div class="question-box">
		<div><i class="fa-solid fa-q"></i></div>
		<div class="question">로그인이 되지 않아요.</div>
	</div>
	
	<div id="box-10"></div>
	<hr>
	<div id="box-10"></div>

	<div class="answer-box">
		<textarea disabled>올바른 아이디와 비밀번호를 입력했는지 다시 한번 확인해주세요.
그래도 로그인이 되지 않는다면 아이디 찾기 혹은 비밀번호 찾기를 이용해주시고 그래도 안된다면 고객센터로 문의해 주시길 바랍니다.</textarea>
	</div>

    <div id="box-50"></div>
   
	<div class="question-box">
		<div><i class="fa-solid fa-q"></i></div>
		<div class="question">제증명 서류는 어디서 신청하나요?</div>
	</div>
	
	<div id="box-10"></div>
	<hr>
	<div id="box-10"></div>

	<div class="answer-box">
		<textarea disabled>제증명 서류는 마이페이지의 진료 관리의 진료 내역 탭에서 신청하실 수 있습니다.
제증명 서류는 진료가 완료된 회원을 기준으로 신청하실 수 있습니다.
대기중 이거나 진료 중인 경우에는 서류를 신청하실 수 없습니다.</textarea>
	</div>

    <div id="box-50"></div>
   
	<div class="question-box">
		<div><i class="fa-solid fa-q"></i></div>
		<div class="question">즐겨찾기한 병원은 어디서 볼 수 있나요?</div>
	</div>
	
	<div id="box-10"></div>
	<hr>
	<div id="box-10"></div>

	<div class="answer-box">
		<textarea disabled>즐겨찾기한 병원은 마이페이지 즐겨찾기 병원 탭에서 확인하실 수 있습니다.
즐겨찾기한 병원을 클릭하면 해당 병원의 페이지로 이동합니다.</textarea>
	</div>

    <div id="box-50"></div>
   
	<div class="question-box">
		<div><i class="fa-solid fa-q"></i></div>
		<div class="question">리뷰 작성은 어디서 하나요?</div>
	</div>
	
	<div id="box-10"></div>
	<hr>
	<div id="box-10"></div>

	<div class="answer-box">
		<textarea disabled>리뷰 작성은 마이페이지의 진료 관리의 진료 내역 탭에서 리뷰 작성을 하실 수 있습니다.
작성한 리뷰는 리뷰 관리에서 상세 보기와 삭제를 하실 수 있습니다.</textarea>
	</div>

    <div id="box-50"></div>
   
    <%@ include file="/WEB-INF/views/inc/footer.jsp" %>
    <script src="https://kit.fontawesome.com/6358fe2c65.js" crossorigin="anonymous"></script>
    <script>
    window.addEventListener('DOMContentLoaded', function() {
    	  var elements = document.querySelectorAll('i.fa-solid.fa-q');

    	  elements.forEach(function(element) {
    	    element.addEventListener('mouseover', function() {
    	      this.classList.add('fa-bounce');
    	    });

    	    element.addEventListener('mouseout', function() {
    	      this.classList.remove('fa-bounce');
    	    });
    	  });

    	  var questionBoxes = document.querySelectorAll('.question-box');
    	  var answerBoxes = document.querySelectorAll('.answer-box');

    	  for (var i = 0; i < questionBoxes.length; i++) {
    	    questionBoxes[i].addEventListener('click', function() {
    	      var answerBox = this.nextElementSibling.nextElementSibling.nextElementSibling.nextElementSibling;

    	      // 선택한 답변이 이미 열려있는 경우 닫는다
    	      if (answerBox.classList.contains('open')) {
    	        answerBox.classList.remove('open');
    	      } else {
    	        // 그 외의 경우, 모든 답변을 닫고 선택한 답변만 열린 상태로 만든다
    	        for (var j = 0; j < answerBoxes.length; j++) {
    	          answerBoxes[j].classList.remove('open');
    	        }
    	        answerBox.classList.add('open');
    	      }
    	    });
    	  }
    	});
    </script>
</body>
</html>