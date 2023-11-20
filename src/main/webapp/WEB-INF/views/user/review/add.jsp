<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<style>
	
	h1 {
		width: 80%;
		margin: 0 auto;
		color: #0db391;
		margin-top: 30px;
		text-align: center;
	}

	hr {
		border: 0;
		background: #0db391;
		height: 2px;
		width: 80%;
		margin: 0 auto;
	}
	
	#box { height: 20px;}

	#text-box {
		width: 500px;
		height: 220px;
		margin: 0 auto;
		display: grid;
		justify-content: center;
	}
	
	#q-box {
		display: grid;
	}
	
	#qContent {
		width: 500px;
		height: 200px;
		resize: none;
		background-color: #d3ffeb;
		border-color: white;
		border-left-width: 0px;
	    border-right-width: 0px;
   		border-top-width: 2px;
   		border-bottom-width: 0px;
   		border-bottom-left-radius: 10px;
		border-bottom-right-radius: 10px;		
   		border-top-left-radius: 10px;
		border-top-right-radius: 10px;		
   		box-shadow: 1px;
   		padding-left: 10px;
   		outline-color: #09734d;
	}
	
	#list {
		text-align: center;
		margin: 0 auto;
		width: 500px;
	}
	
	#list tr td:nth-child(1) { width: 150px; }
	#list tr td:nth-child(2) { width: 100px; }
	#list tr td:nth-child(3) { width: 100px; }
	#list tr td:nth-child(4) { width: 150px; }
	
	#list tr:nth-child(1) td {
		background-color: #46bb94;
		color: white;
	}

	#list tr:nth-child(3) td {
		background-color: #46bb94;
		color: white;
	}

	#list tr:nth-child(2) td { background-color: #d3ffeb; }	
	#list tr:nth-child(4) td { background-color: #d3ffeb; }

	#list tr:nth-child(1) td:nth-child(1) { border-top-left-radius: 10px; }
	#list tr:nth-child(1) td:nth-child(4) { border-top-right-radius: 10px; }

	#list tr:nth-child(2) td:nth-child(1) { border-bottom-left-radius: 10px; }
	#list tr:nth-child(2) td:nth-child(4) { border-bottom-right-radius: 10px; }

	#txt-box {
		width: 500px;
		margin: 0 auto;
		color: #0db391;
		font-size: 1.2rem;
	}
	
	#txt-hr {
		border: 0;
		background: #0db391;
		height: 2px;
		width: 500px;
		margin: 0 auto;	
	}

	#comment {
		display: flex;
		flex-flow: row wrap;
		margin-bottom: 10px;
		justify-content: center;
	}

	#comment div:nth-child(1) {
		width: 100px;
		background-color: #2bc194;
		color: white;
		display: flex;
		align-items: center;
		padding-left: 5px;
		padding-top: 3px;
		padding-bottom: 3px;
		border-top-left-radius: 10px;
		border-bottom-left-radius: 10px;
	}
	#comment div:nth-child(2) {
		background-color: #d3ffeb;
		width: 250px;
		height: 100%;
		display: flex;
		align-items: center;
		padding-left: 5px;
		padding-top: 3px;
		padding-bottom: 3px;
	}
	#comment div:nth-child(3) {
		background-color: #ecfffb;
		width: 150px;
		display: flex;
		align-items: center;
		padding-left: 9px;
		padding-top: 3px;
		padding-bottom: 3px;
		border-top-right-radius: 10px;
		border-bottom-right-radius: 10px;
	}

	.btn {
		color: white;
		width: 100px;
		height: 40px;
    	margin: 0 auto;
    	cursor: pointer;
		display: flex;
		font-size: 1.2rem;
		text-align: center;
		line-height: 50px;
    	align-items: center;
    	border-radius: 10px;
    	border: 0;
		justify-content: center;
		background-color: #2bc194;
		transition: all 0.5s;
	}
	
	.btn:hover {
		background-color: #2bc194;
	}
	
	#btn-box {
	    width: 250px;
	    margin: 0 auto;
	    display: flex;
	}
	
	.good, .bad {
		display: none;
	}
	
	#good-box {
		width: 500px;
		margin: 0 auto;
		display: flex;
		flex-flow: row wrap;
	    justify-content: space-between;
	}
	
	#bad-box {
		width: 500px;
		margin: 0 auto;
		display: flex;
		flex-flow: row wrap;
	    justify-content: space-between;
	}
	
	#good-box label {
		border-radius: 10px;
		width: 150px;
		text-align: center;
		margin-bottom: 10px;
		border: 1px solid #2bc194;
		transition: all 0.3s;
	}
	
	#bad-box label {
		border-radius: 10px;
		width: 150px;
		text-align: center;
		margin-bottom: 10px;
		border: 1px solid tomato;
		transition: all 0.3s;
	}
	
	small { color: #7c988e; }
	
</style>
</head>
<body>
	
		<h1>리뷰 작성</h1>
		
		<div id="box"></div>

		<hr>
		
		<div id="box"></div>
		<form method="POST" action="/apa/user/review/add.do">
		<div id="text-box">
			<div id="q-box">
				<textarea id="qContent" name="content" required></textarea>
			</div>
		</div>
	
		<table id="list">
			<tr>
				<td>병원명</td>
				<td>진료 의사</td>
				<td>진료 방식</td>
				<td>방문 날짜</td>
			</tr>
			<tr>
				<td>${dto.hospitalName}</td>
				<td>${dto.doctorName}</td>
				<td>${dto.mediWay}</td>
				<td>${dto.treatmentDate}</td>
			</tr>
		</table>

		<div id="box"></div>
		<div id="txt-box">태그 선택 <small> 5개 까지 선택 가능합니다.</small></div>
		<hr id="txt-hr">
		<div id="box"></div>

		<div id="good-box">
			<input type="checkbox" name='tag' value='1' id="cb1" class="good">
			<label for="cb1">진료가 꼼꼼해요</label>
			
			<input type="checkbox" name='tag' value='2' id="cb2" class="good">
			<label for="cb2">시설이 깨끗해요</label>
			
			<input type="checkbox" name='tag' value='3' id="cb3" class="good">
			<label for="cb3">친절해요</label>
			
			<input type="checkbox" name='tag' value='4' id="cb4" class="good">
			<label for="cb4">주차하기 편해요</label>
			
			<input type="checkbox" name='tag' value='5' id="cb5" class="good">
			<label for="cb5">대기시간이 적어요</label>
			
			<input type="checkbox" name='tag' value='6' id="cb6" class="good">
			<label for="cb6">진료경과가 좋아요</label>
			
			<input type="checkbox" name='tag' value='7' id="cb7" class="good">
			<label for="cb7">설명이 자세해요</label>		
		</div>

		<div id="bad-box">
			<input type="checkbox" name='tag' value='8' id="cb8" class="bad">
			<label for="cb8">불친절해요</label>
			
			<input type="checkbox" name='tag' value='9' id="cb9" class="bad">
			<label for="cb9">과잉진료</label>
			
			<input type="checkbox" name='tag' value='10' id="cb10" class="bad">
			<label for="cb10">대기시간이 길어요</label>
			
			<input type="checkbox" name='tag' value='11' id="cb11" class="bad">
			<label for="cb11">진료시간이 짧아요</label>
			
			<input type="checkbox" name='tag' value='12' id="cb12" class="bad">
			<label for="cb12">주차가 불편해요</label>
			
			<input type="checkbox" name='tag' value='13' id="cb13" class="bad">
			<label for="cb13">비위생적이에요</label>		
		</div>

		<div id="box"></div>
		<div id="btn-box">
			<div class="btn" onclick="window.close()">창 닫기</div>
			<button class="btn">완료</button>
		</div>
		<input type="hidden" value="${seq}" name="seq">
		<input type="hidden" name="tag-input">
		</form>
		
		<div id="box"></div>
		<div id="box"></div>

	<script>
		
		$(document).ready(function() {
			var maxChecked = 5;
		  
			$('.good, .bad').change(function() {
		    	var checkedCount = $('.good:checked, .bad:checked').length;
		    
		   		if (checkedCount > maxChecked) {
		      		this.checked = false;
		      		return;
		    	}
		    
		    	var checkboxId = $(this).attr('id');
		    	var labelFor = $('label[for="' + checkboxId + '"]');
		    
		    	if ($(this).is(':checked')) {
		      		if ($(this).hasClass('good')) {
		        		labelFor.css('background-color', '#2bc194');
		      		} else if ($(this).hasClass('bad')) {
		        		labelFor.css('background-color', 'tomato');
		      		}
		    	} else {
		      		labelFor.css('background-color', 'white');
		    	}
		  	});
		});
		
		$(document).ready(function() {
			var maxChecked = 5;
			var checkedValues = [];

			$('.good, .bad').change(function() {
				var value = $(this).val();

			    if ($(this).is(':checked')) {
			    	if (checkedValues.indexOf(value) === -1) {
			        	checkedValues.push(value);
			      	}
			    } else {
			   		
			    	var index = checkedValues.indexOf(value);
			    	
			   		if (index !== -1) {
			        	checkedValues.splice(index, 1);
			      	}
			    }

			    if (checkedValues.length > maxChecked) {
			    	checkedValues.splice(maxChecked);
			    }

			    updateTagInputValue();
			});

			function updateTagInputValue() {
				var tagInput = $('input[name="tag-input"]');
				tagInput.val(checkedValues.join(','));
			}
		});		
	</script>
</body>
</html>