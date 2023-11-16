<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
<style>
	.detail-userinfo-button{
		text-align:center;
		padding-right:10px;
		width: 80px;
		height: 35px;
		border: 1px solid #ffffff;
		border-radius: 0.6rem;
		background-color: #f0f0f0;
	}
	form {
		padding: 0px auto;
	}
	.detail-userinfo-list{
		position: relative;
	    flex-direction: column;
	    min-width: 0;
	    word-wrap: break-word;
	    background-clip: border-box;
	    margin: 50px 350px;
	    margin-top: 100;
	    text-align: left;
	    max-width: 1320px;
	    margin-left: auto;
	    margin-right: auto;
	}
	.userinfo-list{
	}
	.detail-userinfo{
		display: block;
		padding-left: 50px;
		padding-bottom: 5px;
	}
	.detail-userinfo info{
		border: 1px solid #999999;
		border-radius: 0.35rem;
	}
	.userinfo-list-h1 {
		padding: 30px 30px;
		text-align: center;
	}
	.detail-userinfo-table th{
		padding-right: 50px;
		padding-bottom: 10px;
		padding-top: 10px;
		border: 1px solid #999999;
		border-radius: 0.35rem;
		padding: 10px 20px;
	}
	.detail-userinfo-table td {
		border: 1px solid #999999;
		border-radius: 0.35rem;
		padding: 10px 10px;
	}
	.detail-userinfo-table input{
		padding-left: 10px;
		margin: 0 5px;
	}
	.detail-userinfo-detail{
		width: 550px;
		height: 100px;
		margin: 0 5px;
	}
	.oneclickservice > div {
		display: flex;
	}
	.help-tip{
		text-align: center;
		cursor: default;	
		margin: 5px 5px;
	}
	
	.help-tip:hover p{	
		display:block;	
		transform-origin: 100% 0%;
		-webkit-animation: fadeIn 0.3s ease-in-out;	
		animation: fadeIn 0.3s ease-in-out;	
	}
	.help-tip p{	
		display: none;	
		text-align: left;		
		background-color: #f1f1f1;	
		padding: 20px;	
		width: 300px;
		position: absolute;	
		border-radius: 3px;	
		box-shadow: 1px 1px 1px rgba(0, 0, 0, 0.2);	
		color: #000000;	
		font-size: 13px;	
		line-height: 1.4;
		bottom: -12px;
	}
	.help-tip p:before{	
		position: absolute;	
		content: '';	
		width:0;	
		height: 0;
		border:6px solid transparent;	
		border-bottom-color:#f1f1f1;
		top:-12px;	
	}
	.help-tip p:after{	
		width:100%;	
		height:40px;	
		content:'';
		position: absolute;	
		top:-40px;	
		left:0;	
	}
	.oneclickhide{
		display:none;
	}
	.rezuser-choice-hide{
		display:none;
	}
	.reservation-button{
		display:flex;
		align-items:center;
		justify-content:center;
		width: 480px;
		height: 50px;
		border: 1px solid #fff;
		border-radius: 0.6rem;
		background-color: #f0f0f0;
		margin-top: 50px;
		margin-left: auto;
		margin-right: auto;
	}
	.rez-body-top {
		display:flex;
		align-items:center;
		justify-content:center;
		width:1320px;
		max-width: 1320px;
		margin-left: auto;
		margin-right: auto;
	}
</style>
</head>
<body>
	<!-- /reservation/select.jsp -->
	<%@ include file="/WEB-INF/views/inc/header.jsp" %>
	
	<div class="rez-body-top">
		<form action="/apa/reservation/complete.do" method="POST">
		<div class="detail-userinfo-list">
			<div class="userinfo-list">
				<h1 class="userinfo-list-h1 hansans">상세정보 입력</h1>
				<table class = "detail-userinfo-table">
					<tr>
						<th>진료 대상</th>
						<td colspan="2">
							<input type="button" class="rezuser-choice-user" value="본인">
							<input type="button" class="rezuser-choice-child" value="자녀">
						</td>
					</tr>
					<tr class="rezuser-choice-hide">
						<th>자녀 선택</th>
						<td colspan="2">
							<c:forEach items="${childdto}" var="list">
								${list.childName}<input type="radio" name="rezchildseq" value="${list.childSeq}" disabled="disabled">
							</c:forEach>
						</td>
					</tr>
					<tr class="rezuser-choice-hide">
						<th>자녀 전화번호</th>
						<td colspan="2">
							<input type="text" class="detail-child-info" value="010" style="width: 45px" disabled="disabled"> -
							<input type="text" class="detail-child-info" value="" style="width: 60px" disabled="disabled"> -
							<input type="text" class="detail-child-info" value="" style="width: 60px" disabled="disabled">
						</td>
					</tr>
					<tr class="rezuser-choice-hide">
						<th>자녀 주민등록번호</th>
						<td colspan="2">
							<input type="text" class="detail-child-info" value="" style="width: 90px" disabled="disabled">- 
							<input type="text" class="detail-child-info" value="" style="width: 90px" disabled="disabled">
						</td>
					</tr>
					<tr>
						<th>본인 이름</th>
						<td colspan="2"><input type="text" value="${dto.userName}" style="width: 80px" required></td>
					</tr>
					<tr>
						<th>주민등록번호</th>
						<td colspan="2">
							<input type="text" value="${firstssn}" style="width: 90px" required>- 
							<input type="text" value="${lastssn}" style="width: 90px" required>
						</td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td colspan="2">
							<input type="text" value="${firsttel}" style="width: 45px" required> -
							<input type="text" value="${middeltel}" style="width: 60px" required> -
							<input type="text" value="${lastltel}" style="width: 60px" required>
						</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td colspan="2">
							<input type="text" value="${emailid}"  style="width: 120px" required>@
							<input type="text" value="${emailaddress}"  style="width: 120px" required>
						</td>
					</tr>
					<tr>
						<th>주소</th>
						<td colspan="2"><input type="text" value="${dto.userAddress}"  style="width: 400px" required></td>
						
					</tr>					
					<tr>
						<th>상세사항</th>
						<td colspan="2"><textarea class="detail-userinfo-detail" name="reztext" cols="80" rows="8" ></textarea></td>

					</tr>
					<c:if test="${choicetype ne '건강검진' and choicetype ne 'drug'}">
						<tr>
							<th rowspan="3">처방 약 사후처리</th>
							<td class="oneclickservice" colspan="2">
								<div>
									<input type="button" class="detail-userinfo-button" value="원클릭">
									<div class="help-tip">
										원클릭 서비스가 뭔가요?
										<p>버튼하나로 병원과 약국 예약을 동시에!!</p>
									</div>
								</div>
							</td>
						</tr>
						<tr class="oneclickhide">
							<th style = "width: 100px;">수령방법</th>
							<td>
								방문수령<input type="radio" name="rezdrugtype" value="방문수령" checked="checked" disabled >
								퀵<input type="radio" name="rezdrugtype" value="퀵" disabled >
							</td>
						</tr>
						<tr class="oneclickhide">
							<td colspan="2">
								<input type="text" class="timepicker" name="rezdrugtime" id="timepicker" readonly disabled>
							</td>
						</tr>
					</c:if>
				</table>
				<input type="hidden" name="rezhospitaid" value="${id}">
				<input type="hidden" name="reztype" value="${choicetype}">
				<input type="hidden" name="rezdocseq" value="${choicedoc}">
				<input type="hidden" name="rezgotime" value="${choicetime}">
				<input type="hidden" name="rezregtime" value="${nowtime}">
				<button type="submit" class="reservation-button"> 예약하기 </button>
			</div>			
		</div>
		</form>
	</div>

	<%@ include file="/WEB-INF/views/inc/footer.jsp" %>
	<script>
		$('.timepicker').timepicker({
		    timeFormat: 'HH:mm',
		    interval: 10,
		    minTime: '7:00',
		    maxTime: '17:00',
		    defaultTime: '',
		    startTime: '7:00',
		    dynamic: false,
		    dropdown: true,
		    scrollbar: true
		});
		$(".rezuser-choice-user").click(function() {
			$(".rezuser-choice-hide").slideUp();
			$('.rezuser-choice-hide input').attr("disabled", true);
			$('.rezuser-choice-hide input').attr("required", false);
		});
		$(".detail-userinfo-button").click(function() {
			if ($(".oneclickhide").is(":visible")){
				$(".oneclickhide").slideUp();
				$(".oneclickhide input").attr("disabled", true);
				$(".oneclickhide input").attr("required", false);
			} else {
				$(".oneclickhide").slideDown();
				$(".oneclickhide input").attr("disabled", false);
				$(".oneclickhide input").attr("required", true);
			}
		});
		$(".rezuser-choice-child").click(function() {
			if ($(".rezuser-choice-hide").is(":visible")){
				$(".rezuser-choice-hide").slideUp()
				$('.rezuser-choice-hide input').attr("disabled", true);
				$('.rezuser-choice-hide input').attr("required", false);
			} else {
				$(".rezuser-choice-hide").slideDown();
				$('.rezuser-choice-hide input').attr("disabled", false);
				$('.rezuser-choice-hide input').attr("required", true);
			}
		});
	</script>
</body>
</html>