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
	body {
		-webkit-user-select: none;
 		-moz-user-select: none;
 		-ms-use-select: none;
		background-color: #f8f8f8;
	}
	.hospital-info {
		position: relative;
	    flex-direction: column;
	    min-width: 0;
	    word-wrap: break-word;
	    background-color: #fff;
	    background-clip: border-box;
	    border: 1px solid #f8f8f8;
	    border-radius: 0.35rem;
	    padding: 50px 150px;
	    margin-top: 100;
	}
	
	
	.hosptal-info-two {
		display: flex;
	}
	.hospital-info-contentlist {
		display: flex;
	}
	.hospital-info-dept {
		text-align: center;
		width: 100px;
		height: 35px;
		border: 1px solid #fff;
		border-radius: 0.6rem;
		background-color: #f0f0f0;
		padding-top: 5px;
		margin-right: 5px;
		margin-top: 7.5px;
	}
	.hospital-info-content {
		text-align: center;
		width: 100px;
		height: 35px;
		border: 1px solid #fff;
		border-radius: 0.6rem;
		background-color: #f0f0f0;
		padding-top: 5px;
		margin-right: 5px;
		margin-top: 7.5px;
	}
	.collapse-header {
		font-size: 5rem;
	}
	.hospital-info-deptlist {
		display: flex;
	}
	main div{
		padding-left: 50px;
		padding-right: 50px;
	}
	.hosptal-top-info {
		padding-left: 0px;
	}
	.reservation-button {
		text-align: center;
		width: 500px;
		height: 50px;
		border: 1px solid #fff;
		border-radius: 0.6rem;
		background-color: #f0f0f0;
		/* position: absolute;
		left: 50%;
		transform: translate(-50%, -50%); */
		
		margin-left: 30%;
		margin-right: 30%;
	}
	#map {
		margin-bottom: 80px;
	}
	.hospital-info-content-color{
		background-color:#beefe5;
	}
</style>
    </head>
    <body id="section_1">
        <%@ include file="/WEB-INF/views/inc/header.jsp" %>
        <main>
			<div class="hospital-info">
				<div class="hosptal-top-info">
					<h6 class="collapse-header hansans">${dto.name}</h6>
					<div class="hospital-info-contentlist">
						<c:if test="${dto.night == 'y' or dto.night == 'Y'}">
							<p class="hospital-info-content hospital-info-content-color">야간진료</p>
						</c:if>
						<c:if test="${dto.night == 'n' or dto.night == 'N'}">
							<p class="hospital-info-content">야간진료</p>
						</c:if>
						<c:if test="${dto.holiday == 'y' or dto.holiday == 'Y'}">
							<p class="hospital-info-content hospital-info-content-color">주말진료</p>
						</c:if>
						<c:if test="${dto.holiday == 'n' or dto.holiday == 'N'}">
							<p class="hospital-info-content">주말진료</p>
						</c:if>
					</div>
				</div>
				<hr>
				<div class="hosptal-info-one">
					<p class="hospital-info-time">영업시간: ${dto.opentime} ~ ${dto.closetime}</p>
				</div>	
				<hr>
				<div>
					<p class="hospital-info-info">${dto.email}</p>
					<p class="hospital-info-info">${dto.tel}</p>
				</div>
				<hr>
				<div>
					<p class="hospital-info-info">${dto.address}</p>	
					<div id="map" style="width:auto;height:400px;"></div> <!-- 지도를 담을 영역 만들기 -->			
				</div>
					<c:if test="${lv == '1'}">
						<div style"width=100%">
							<a href="/apa/reservation/pharmacysubject.do?id=${dto.id}">
								<button class="reservation-button"> 예약하기 </button>
							</a>
						</div>
					</c:if>
					<c:if test="${lv == '' || lv == null}">
						<div style"width=100%">
							<a href="/apa/user/login.do">
								<button class="reservation-button"> 예약하기 </button>
							</a>
						</div>
					</c:if>
					
			    </div>
			</div>
        </main>
		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1e36c68ad00f345a585d2e60e5bfa6ac&libraries=services"></script>
        <%@ include file="/WEB-INF/views/inc/footer.jsp" %>
        <script>
	        /* const container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
			const options = { //지도를 생성할 때 필요한 기본 옵션
				center: new kakao.maps.LatLng(37.49934, 127.0333), //지도의 중심좌표.
				level: 3 //지도의 레벨(확대, 축소 정도)
			};
			const map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴 */

			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
				mapOption = {
					center: new kakao.maps.LatLng(37.49934, 127.0333), // 지도의 중심좌표
					level: 3 // 지도의 확대 레벨
				};
		
			// 지도를 생성합니다    
			var map = new kakao.maps.Map(mapContainer, mapOption); 
	
			// 주소-좌표 변환 객체를 생성합니다
			var geocoder = new kakao.maps.services.Geocoder();
	
			// 주소로 좌표를 검색합니다
			geocoder.addressSearch('${dto.address}', function(result, status) {
	
			    // 정상적으로 검색이 완료됐으면 
			     if (status === kakao.maps.services.Status.OK) {
	
			        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	
			        // 결과값으로 받은 위치를 마커로 표시합니다
			        var marker = new kakao.maps.Marker({
			            map: map,
			            position: coords
			        });
	
			        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			        map.setCenter(coords);
			    } 
			}); 
        </script>
    </body>
</html>