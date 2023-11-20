<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<style>
	.hospital-info {
		text-align:center;
		max-width: 1320px;
		margin: 100px auto;
			
	}
	.hospital-info > div{
		text-align:center;
		display: grid;
		grid-template-columns: repeat(3, 1fr);
		grid-gap: 50px;
		align-items:center;
		justify-content:center;
		margin-left: 70px;
	
	}
	.type-button {
		background-color: #ffffff;
		border: 1px solid #ffffff;
		width: 300px;
		height: 150px;
		margin: 10px 10px;
		font-size: 50px;
	}
	.type-button-div {
		width: 300px;
		height: 150px;
		border: 1px solid #f5f5f5;
		border-radius: 20px;
	}

	.reservation-button {
		text-align: center;
		width: 480px;
		height: 50px;
		border: 1px solid #fff;
		border-radius: 0.6rem;
		background-color: #f0f0f0;
		margin-top: 50px;
	}
</style>
</head>
<body>
	<!-- /reservation/select.jsp -->
	<%@ include file="/WEB-INF/views/inc/header.jsp" %>
	<form action="/apa/reservation/subject.do" method="POST">
		<div class="hospital-info">
			<div>
				<c:if test="${hospitalinfo.face == 'y' || hospitalinfo.face == 'Y'}">
					<div class="col-lg-3 col-md-6 col-12 mb-4 mb-lg-0 type-button-div">
	                   <div class="featured-block d-flex justify-content-center align-items-center type-button-div">
							<button type="button" class="type-button lineseedkr" onclick="typechoice()" value="${hospitalinfo.face}">대면</button>
	                	</div>
					</div>			
				</c:if>
				<c:if test="${hospitalinfo.unface == 'y' || hospitalinfo.unface == 'Y'}">
					<div class="col-lg-3 col-md-6 col-12 mb-4 mb-lg-0 type-button-div">
	                   	<div class="featured-block d-flex justify-content-center align-items-center type-button-div">			
							<button type="button" class="type-button lineseedkr" onclick="typechoice()" value="${hospitalinfo.unface}">비대면</button>
							</div>
					</div>
				</c:if>
				<c:if test="${hospitalinfo.call == 'y' || hospitalinfo.call == 'Y'}">
					<div class="col-lg-3 col-md-6 col-12 mb-4 mb-lg-0 type-button-div">
	                	<div class="featured-block d-flex justify-content-center align-items-center type-button-div">		
							<button type="button" class="type-button lineseedkr" onclick="typechoice()" value="${hospitalinfo.call}">왕진</button>
							</div>
					</div>
				</c:if>
				<c:if test="${hospitalinfo.check == 'y' || hospitalinfo.check == 'Y'}">		
					<div class="col-lg-3 col-md-6 col-12 mb-4 mb-lg-0 type-button-div">
	                   	<div class="featured-block d-flex justify-content-center align-items-center type-button-div">
							<button type="button" class="type-button lineseedkr" onclick="typechoice()" value="${hospitalinfo.check}">건강검진</button>
							</div>
					</div>
				</c:if>
				<c:if test="${hospitalinfo.vaccin == 'y' || hospitalinfo.vaccin == 'Y'}">			
					<div class="col-lg-3 col-md-6 col-12 mb-4 mb-lg-0 type-button-div">
	                   	<div class="featured-block d-flex justify-content-center align-items-center type-button-div">
							<button type="button" class="type-button lineseedkr" onclick="typechoice()" value="${hospitalinfo.vaccin}">예방접종</button>
							</div>
					</div>
				</c:if>
			</div>
			<div id="choice-type"></div>
			<button type="submit" class="reservation-button" disabled="disabled"> 다음으로 </button>
			<input type="hidden" name="id" value="${id}">
		</div>
	</form>
	<%@ include file="/WEB-INF/views/inc/footer.jsp" %>
	<script>
	function typechoice() {
		$('.type-button').css('opacity', '0.4');
		$(event.target).css('opacity', '1');
		$('#choice-type').html('');
		$('#choice-type').append('<input type="hidden" id="choicetype" name="choicetype" value= "">');
		$('#choicetype').val($(event.target).text());
		$('.reservation-button').attr("disabled", false);
	}
	</script>
</body>
</html>