<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/assetmypage.jsp" %>
<style>

	#favorite {
		display: flex;
		flex-flow: row wrap;
		justify-content: space-between;
	}

	.cardHospital {
		border: 0;
		border-radius: 10px;
		background-color: #cefff5;
		width: 47%;
		position: relative;
		padding: 20px;
		cursor: pointer;
	}

	.cardHospital:nth-child(even) {
		margin-top: 20px;
		margin-bottom: 20px;
		margin-right: 20px;
	}

	.cardHospital:nth-child(odd) {
		margin-top: 20px;
		margin-bottom: 20px;
		margin-left: 20px;
	}
	
	#hName {
		font-size: 1.3rem;
		color: #15ad84;
	}
	
	#dName {
		color: #999;	
	}
	
	#check {
		color: tomato;
	}
	
	#delete {
		position: absolute;
	    left: 94%;
	    top: 8%;
	    cursor: pointer;
	}
	
	
	
</style>
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
                <a class="nav-link" href="/apa/user/info/view.do?seq=${dto.userSeq}" style="">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>마이페이지</span></a>
            </li>			
			
            <!-- Divider -->
            <hr class="sidebar-divider">
            
            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="/apa/user/info/view.do?seq=${dto.userSeq}">
                    <i class="fas fa-fw fa-user"></i>
                    <span>내 정보</span>
                </a>
            </li>

            <!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="/apa/user/children/view.do?seq=${dto.userSeq}">
                    <i class="fas fa-fw fa-baby"></i>
                    <span>자녀 관리</span>
                </a>
            </li>
            
            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="/apa/user/treatment/info.do?seq=${dto.userSeq}">
                    <i class="fas fa-fw fa-stethoscope"></i>
                    <span>진료 정보</span>
                </a>
            </li>

            <!-- Nav Item - Charts -->
            <li class="nav-item active">
                <a class="nav-link" href="/apa/user/favorite/list.do?seq=${dto.userSeq}">
                    <i class="fas fa-fw fa-hospital-alt"></i>
                    <span>즐겨찾기 병원</span></a>
            </li>

            <!-- Nav Item - Tables -->
            <li class="nav-item">
                <a class="nav-link" href="/apa/user/storage/list.do?seq=${dto.userSeq}">
                    <i class="fas fa-fw fa-inbox"></i>
                    <span>보관함</span></a>
            </li>

            <li class="nav-item">
                <a class="nav-link collapsed" href="/apa/user/review/list.do?seq=${dto.userSeq}">
                    <i class="fas fa-fw fa-thumbs-up"></i>
                    <span>리뷰 관리</span>
                </a>
            </li>			

            <li class="nav-item">
                <a class="nav-link collapsed" href="#">
                    <i class="fas fa-fw fa-file-alt"></i>
                    <span>문진표 작성</span>
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
	                	<h1 class="h3 mb-0 text-gray-800 hansans" style="padding-top: 28px;">즐겨찾기 병원</h1>
	                </div>
	                    
                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">


	                    
                        <div class="topbar-divider d-none d-sm-block"></div>
                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${dto.userName}(${dto.userId})님</span>
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
                                    <h5 class="m-0 font-weight-bold text-primary">즐겨찾기 병원</h5>
                                </div>
                                <!-- Card Body -->
                                <div class="card-body" id="favorite">
									<c:forEach items="${list}" var="fdto">
										
										<div class="cardHospital" onclick="">
										<a href="/apa/find/view.do?id=${fdto.hospitalId}">
											<i class="fas fa-fw fa-times" id="delete" onclick="deleteFavor(${fdto.bookmarkSeq});"></i>
											<div id="box1">
												<font id="hName">
													${fdto.hospitalName}
												</font>
												<font id="dName">
													${fdto.departmentName}
												</font>
											</div>

											<div id="box2">
												<font id="check">
													<c:choose>
														<c:when test="${fdto.openCheck eq '0'}">진료 전&nbsp</c:when>
														<c:when test="${fdto.openCheck eq '1'}">진료 중&nbsp</c:when>
														<c:when test="${fdto.openCheck eq '2'}">진료 종료&nbsp</c:when>
													</c:choose>
												</font>
																							
												${fdto.openTime} ~ ${fdto.closeTime}
											</div>	
											
											<div id="box3">
												${fdto.hospitalAddress}
											</div>
										</a>
										</div>
									</c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
            	</div>
            </div>
            
            <!-- End of Main Content -->
			<%@ include file="/WEB-INF/views/inc/endofmaincontent.jsp" %>
		
		
		<script>
			
			function deleteFavor(seq) {

				if (confirm('정말로 삭제하시겠습니까?')) {
					
					$.ajax({
						type:'POST',
						url: '/apa/user/favorite/list.do',
						data: 'seq=' + seq,
						dataType: 'json',
						success: function(result) {
							
							if (result.result) {
								location.reload();
							} else {
								alert('failed')
							}
						},
						error: function(a,b,c) {
							console.log(a,b,c);
						}
					});
				}
			}
			
		</script>
</body>
</html>