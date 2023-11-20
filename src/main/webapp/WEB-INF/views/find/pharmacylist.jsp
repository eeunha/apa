<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<%@ include file="/WEB-INF/views/inc/asset.jsp"%>

<style>
	body {
		-webkit-user-select: none;
		-moz-user-select: none;
		-ms-use-select: none;
		background-color: #f8f8f8;
	}
	
	tbody {
		max-width: 1320px;
		margin-left: auto;
		margin-right: auto;
	}
	
	#main-list {
		margin-top: 100px;
		max-width: 1320px;
		margin-left: auto;
		margin-right: auto;
	}
	
	.hospital-info {
		position: relative;
		flex-direction: column;
		word-wrap: break-word;
		background-color: #fff;
		background-clip: border-box;
		border: 1px solid #e3e6f0;
		border-radius: 0.35rem;
		padding: 10px 20px;
		margin-top: 100;
		max-width: 1320px;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 2px;
	}
	
	.info-text {
		margin-left: 30px;
		margin-bottom: 10px;
	}
	
	.collapse-header {
		margin-left: 20px;
		margin-top: 10px;
	}
	
	.hospital-info-contentlist {
		margin-left: 10px;
	}
	
	.hospital-info-content {
		text-align: center;
		width: 100px;
		height: 35px;
		border: 1px solid #fff;
		border-radius: 0.6rem;
		background-color: #f0f0f0;
		padding: 3px 10px;
		margin-left: 5px;
		margin-top: 7.5px;
	}
	
	.reservation-button {
		width: 150px;
		height: 50px;
		border: 1px solid #fff;
		border-radius: 0.6rem;
		background-color: #f0f0f0;
	}
	.hospital-info-contentlist div{
		display: inline-flex;
	}
	.hospital-info-contentlist a:nth-child(2){
		display: flex;
	  justify-content: space-between;
	  float: right;
	}
	.hospital-info-content-color{
		background-color:#beefe5;
	}
</style>
</head>
<body id="section_1">
	<%@ include file="/WEB-INF/views/inc/header.jsp"%>
	<main>
		<div id="main-list">
			<table id="hospital-list">
				<tbody>
					<c:forEach items="${list}" var="list">
						<a href="/apa/find/pharmacyview.do?id=${list.id}">
							<div class="hospital-info">
								<h6 class="collapse-header">${list.name}</h6>
								<p class="info-text">약국</p>
								<p class="info-text">${list.address}</p>
						</a>
						<div class="hospital-info-contentlist">
							<a href="/apa/find/pharmacyview.do?id=${list.id}">
								<div>
								<c:if test="${list.night == 'y' or list.night == 'Y'}">
									<p class="hospital-info-content hospital-info-content-color">야간진료</p>
								</c:if>
								<c:if test="${list.night == 'n' or list.night == 'N'}">
									<p class="hospital-info-content">야간진료</p>
								</c:if>
								<c:if test="${list.holiday == 'y' or list.holiday == 'Y'}">
									<p class="hospital-info-content hospital-info-content-color">주말진료</p>
								</c:if>
								<c:if test="${list.holiday == 'n' or list.holiday == 'N'}">
									<p class="hospital-info-content">주말진료</p>
								</c:if>
								</div>
							</a>
							<c:if test="${lv == '1'}">
								<a href="/apa/reservation/pharmacysubject.do?id=${list.id}">
										<button class="reservation-button">예약하기</button>
								</a>
							</c:if>
							<c:if test="${lv == '' || lv == null}">
								<a href="/apa/user/login.do">
									<button class="reservation-button"> 예약하기 </button>
								</a>
							</c:if>
								
						</div>
						</div>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</main>

	<%@ include file="/WEB-INF/views/inc/footer.jsp"%>
	<script>
		
	</script>

</body>
</html>