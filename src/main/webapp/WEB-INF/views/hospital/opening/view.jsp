<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/assetmypage.jsp" %>
<style>
	body {
		-webkit-user-select: none;
 		-moz-user-select: none;
 		-ms-use-select: none;
		background-color: #f8f8f8;
	}
	.hospitalpage-info-op{
		background-color: #beefe5;
	}
	.hospitalpage-info-list > div, .hospitalpage-body-day > div, .hospitalpage-body-time > div {
		display: inline-flex;
	}
	
	.hospitalpage-info-list > div:nth-child(2) {
		display: none;
	}
	.hospitalpage-info-list > div div{
		text-align: center;
		padding-top: 5px;
		width:100px;
		height:40px;
		border: 1px solid #999999;
		border-radius: 10px;
		margin-right: 10px;
	}
	.hospitalpage-button > button, .hospitalpage-button-time > button, .hospitalpage-button-day > button{
		margin-left : auto;
		margin-right: auto;
		text-align: center;
		background-color:#ffffff;
		border:none;
	}
	.hospitalpage-button, .hospitalpage-button-time, .hospitalpage-button-day{
		display: flex;
  		justify-content: space-between;
  		float: right;
		width:100px;
		height:40px;
		border: 1px solid #999999;
		border-radius: 10px;
		margin-right: 10px;
	}
	
	
</style>
</head>
<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/apa/main.do">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-laugh-wink"></i>
                </div>
                <div class="sidebar-brand-text mx-3 hansans"> 아파! 어디가? </div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0" >
			
            <!-- Nav Item - Dashboard -->
            <li class="nav-item active">
                <a class="nav-link" href="/apa/hospital/info/view.do?id=${dto.hospitalId}" style="">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>마이페이지</span></a>
            </li>			
			
            <!-- Divider -->
            <hr class="sidebar-divider">
            
            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item ">
                <a class="nav-link collapsed" href="/apa/hospital/info/view.do?id=${dto.hospitalId}">
                    <i class="fas fa-fw fa-user"></i>
                    <span>내 정보</span>
                </a>
            </li>
            
            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item active">
                <a class="nav-link collapsed" href="/apa/hospital/treatment/info.do?id=${dto.hospitalId}">
                    <i class="fas fa-fw fa-table"></i>
                    <span>운영 관리</span>
                </a>
            </li>

            <!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item ">
                <a class="nav-link collapsed" href="/apa/hospital/doctor/list.do?id=${dto.hospitalId}">
                    <i class="fas fa-fw fa-stethoscope"></i>
                    <span>의사 관리</span>
                </a>
            </li>
            
            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">

        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

					<!-- Page Heading -->
	                <div class="d-sm-flex align-items-center justify-content-between mb-4">
	                	<h1 class="h3 mb-0 text-gray-800 hansans" style="padding-top: 28px;">운영 관리</h1>
	                </div>
	                    
                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">


	                    
                        <div class="topbar-divider d-none d-sm-block"></div>
                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${dto.hospitalName}(${dto.hospitalId})님</span>
                                <img class="img-profile rounded-circle"
                                    src="/apa/asset/images/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    내정보
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    로그아웃
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Content Row -->

                    <div class="row">

                        <!-- Area Chart -->
                        <div class="col-xl-12">
                            <div class="card shadow mb-5">
                                <!-- Card Header - Dropdown -->
                                <div
                                    class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                    <h5 class="m-0 font-weight-bold text-primary">운영 관리</h5>
                                </div>
                                <!-- Card Body -->
                                <div class="card-body">
	                                <h5>주간 휴무일</h5>
                                	<div class="hospitalpage-body-day">
                                		<div>
		                                	<div>${dayoff}</div>
                                		</div>
	                                	<div class="hospitalpage-button-day">
			                                <button type="button">수정</button>
		                                </div>
                                	</div>
		                            <br>
                                	<hr>
	                                <h5>운영시간</h5>
                                	<div class="hospitalpage-body-time">
                                		<div>
		                                	<div>${opendto.openTime} ~ ${opendto.closeTime}</div>
                                		</div>
	                                	<div class="hospitalpage-button-time">
			                                <button type="button">수정</button>
		                                </div>
                                	</div>
	                                <br>
                                	<hr>
                                	<h5>운영정보</h5>
                                	<div class="hospitalpage-info-list">
	                                	<div>
                                		<c:if test="${infodto.face == 'y' || infodto.face == 'Y'}">
	                                		<div id="y" class="hospitalpage-info-op" value='y'>대면</div>
                                		</c:if>
                                		<c:if test="${infodto.face == 'n' || infodto.face == 'N'}">
	                                		<div value='n'>대면</div>
                                		</c:if>
                                		<c:if test="${infodto.unface == 'y' || infodto.unface == 'Y'}">
	                                		<div id="y" class="hospitalpage-info-op" value='y'>비대면</div>
                                		</c:if>
                                		<c:if test="${infodto.unface == 'n' || infodto.unface == 'N'}">
	                                		<div value='n'>비대면</div>
                                		</c:if>
                                		<c:if test="${infodto.call == 'y' || infodto.call == 'Y'}">
	                                		<div class="hospitalpage-info-op" value='y'>왕진</div>
                                		</c:if>
                                		<c:if test="${infodto.call == 'n' || infodto.call == 'N'}">
	                                		<div value='n'>왕진</div>
                                		</c:if>
                                		<c:if test="${infodto.check == 'y' || infodto.check == 'Y'}">
	                                		<div class="hospitalpage-info-op" value='y'>건강검진</div>
                                		</c:if>
                                		<c:if test="${infodto.check == 'n' || infodto.check == 'N'}">
	                                		<div value='n'>건강검진</div>
                                		</c:if>
                                		<c:if test="${infodto.vaccin == 'y' || infodto.vaccin == 'Y'}">
	                                		<div class="hospitalpage-info-op" value='y'>예방접종</div>
                                		</c:if>
                                		<c:if test="${infodto.vaccin == 'n' || infodto.vaccin == 'N'}">
	                                		<div value='n'>예방접종</div>
                                		</c:if>
                                		<c:if test="${infodto.night == 'y' || infodto.night == 'Y'}">
	                                		<div class="hospitalpage-info-op" value='y'>야간운영</div>
                                		</c:if>
                                		<c:if test="${infodto.night == 'n' || infodto.night == 'N'}">
	                                		<div value='n'>야간운영</div>
                                		</c:if>
                                		<c:if test="${infodto.holiday == 'y' || infodto.holiday == 'Y'}">
	                                		<div class="hospitalpage-info-op" value='y'>주말운영</div>
                                		</c:if>
                                		<c:if test="${infodto.holiday == 'n' || infodto.holiday == 'N'}">
	                                		<div value='n'>주말운영</div>
                                		</c:if>
		                                </div>
		                                <div class="hospitalpage-button-text" style="boder=none; color: tomato;">클릭을 하여 on/off 가능</div>
	                                	<div class="hospitalpage-button">
		                                	<button type="button">수정</button>
	                                	</div>
                                	</div>
                                	
                                </div>
                            </div>
                        </div>
                    </div>
            	</div>
            </div>
            
            <!-- End of Main Content -->
			<%@ include file="/WEB-INF/views/inc/endofmaincontent.jsp" %>
		
		
	<script>
	load();
	function load() {
		$('.hospitalpage-info-list > div > div').val('n');
		$('.hospitalpage-info-op').val('y');		
	}
	$('.hospitalpage-button-day').click(function() {
		if ($('.hospitalpage-button-day > button').text() == '수정'){
			$('.hospitalpage-body-day > div:nth-child(1)').html('');
			$('.hospitalpage-body-day > div:nth-child(1)').append(`
					<select id="hospitalinfo-day-select" >
						<option selected>선택</option>
			               <option value="1">월</option>
			               <option value="2">화</option>
			               <option value="3">수</option>
			               <option value="4">목</option>
			               <option value="5">금</option>
			               <option value="6">토</option>
			               <option value="7">일</option>
					</select>
					`);
			$('.hospitalpage-button-day > button').text('완료')
		}else {
			
			$('.hospitalpage-button-day > button').text('수정')			
		}
	});
	/* $('#hospitalinfo-day-select').change(function() {
		
	}); */
	
	$('.hospitalpage-button').click(function() {
		if ($('.hospitalpage-button > button').text() == '수정'){
			$('.hospitalpage-button > button').text('완료')
			$('.hospitalpage-button-text').css('display', 'inline');
		} else {
			$('.hospitalpage-button > button').text('수정')
			$('.hospitalpage-button-text').css('display', '');
			var info1 = $('.hospitalpage-info-list > div > div:nth-child(1)').val();
			var info2 = $('.hospitalpage-info-list > div > div:nth-child(2)').val();
			var info3 = $('.hospitalpage-info-list > div > div:nth-child(3)').val();
			var info4 = $('.hospitalpage-info-list > div > div:nth-child(4)').val();
			var info5 = $('.hospitalpage-info-list > div > div:nth-child(5)').val();
			var info6 = $('.hospitalpage-info-list > div > div:nth-child(6)').val();
			var info7 = $('.hospitalpage-info-list > div > div:nth-child(7)').val();
			var info8 = "${id}";
			$.ajax({
				type: 'POST',
				url: '/apa/hospital/opening/view.do',
				data: {
					info1,
					info2,
					info3,
					info4,
					info5,
					info6,
					info7,
					info8			
				},
				dataType: 'json',
				success: function(result){
					if(result.result == 1){
						$('.hospitalpage-button-text').text('정상적으로 수정되었습니다.');
					}
				},
				error: function(a,b,c) {
					console.log(a,b,c);
				}
			});
		}
	});

	$('.hospitalpage-info-list > div > div').click(function(){
		var target = $(event.target);
		if ($('.hospitalpage-button > button').text() == '완료'){
			if ($(event.target).css('background-color') == 'rgb(190, 239, 229)'){
				$(event.target).css('background-color', '#ffffff');		
				$(event.target).val('n');
			} else {				
				$(event.target).css('background-color', '#BEEFE5');				
				$(event.target).val('y');				
			}
		}
	});
	</script>
</body>
</html>