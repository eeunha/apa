<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/assetmypage.jsp" %>
<style>

	#clist tr th:first-child { border-top-left-radius: 10px; }
	#clist tr th:last-child { border-top-right-radius: 10px; }
	#clist tr:last-child td:first-child	{ border-bottom-left-radius: 10px; }
	#clist tr:last-child td:last-child	{ border-bottom-right-radius: 10px; }	

	.list tr th:first-child { border-top-left-radius: 10px; }
	.list tr th:last-child { border-top-right-radius: 10px; }
	.list tr:last-child td:first-child	{ border-bottom-left-radius: 10px; }
	.list tr:last-child td:last-child	{ border-bottom-right-radius: 10px; }

	.list {
		text-align: center;
	}
		
	.list > tbody > tr > th {
	    color: white;
		font-size: 1.3rem;
	    background-color: #5BC1AC;
	    font-weight: lighter;
    }

	.list > tbody > tr > td {
		font-size: 1.2rem;
		color: #008e63;
		padding-top: 8px;
		padding-bottom: 8px;
	}

	.list > tbody > tr:nth-child(even) {
		background-color: #d3ffeb;
	}
	
	.list > tbody > tr:nth-child(odd) {
		background-color: #ecfffb;
	}

	.list > tbody > tr > th:nth-child(1) { width: 50px; }
	.list > tbody > tr > th:nth-child(2) { width: 200px; }
	.list > tbody > tr > th:nth-child(3) { width: 100px; }
	.list > tbody > tr > th:nth-child(4) { width: 200px; }
	.list > tbody > tr > th:nth-child(5) { width: 200px; }
	.list > tbody > tr > th:nth-child(6) { width: 130px; }
	.list > tbody > tr > th:nth-child(7) { width: 130px; }
	.list > tbody > tr > th:nth-child(8) { width: 130px; }

	.list > tbody > tr > td {
		font-size: 1.2rem;
	}
	
	#clist {
		text-align: center;
	}
	
	#clist > tbody > tr > th {
	    color: white;
		font-size: 1.3rem;
	    background-color: #5BC1AC;
	    font-weight: lighter;
    }

	#clist > tbody > tr > td {
		font-size: 1.2rem;
		color: #008e63;
		padding-top: 8px;
		padding-bottom: 8px;
	}

	#clist > tbody > tr:nth-child(even) {
		background-color: #d3ffeb;
	}
	
	#clist > tbody > tr:nth-child(odd) {
		background-color: #ecfffb;
	}

	#clist > tbody > tr > th:nth-child(1) { width: 50px; }
	#clist > tbody > tr > th:nth-child(2) { width: 350px; }
	#clist > tbody > tr > th:nth-child(3) { width: 250px; }
	#clist > tbody > tr > th:nth-child(4) { width: 200px; }

	#clist > tbody > tr > td {
		font-size: 1.2rem;
	}
	
	.btn {
	    background-color: #5BC1AC;
	    color: white;
	    border: 0;
	    border-radius: 5px;
	    padding-left: 10px;
	    padding-right: 10px;
	    padding-top: 5px;
	    padding-bottom: 5px;
	}
	
	#btn-complete:hover {
		cursor: default;
		color: white;
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
            <li class="nav-item active">
                <a class="nav-link collapsed" href="/apa/user/treatment/info.do?seq=${dto.userSeq}">
                    <i class="fas fa-fw fa-stethoscope"></i>
                    <span>진료 정보</span>
                </a>
            </li>

            <!-- Nav Item - Charts -->
            <li class="nav-item">
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
	                	<h1 class="h3 mb-0 text-gray-800 hansans" style="padding-top: 28px;">진료 정보</h1>
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
                                    <h5 class="m-0 font-weight-bold text-primary">예약 진료</h5>
                                </div>
                                <!-- Card Body -->
                                <div class="card-body">
									<table class="list">
										<tr>
											<th>번호</th>
											<th>병원명</th>
											<th>담당 의사</th>
											<th>방문 날짜</th>
											<th>상세 증상</th>
											<th>진료 방식</th>
										</tr>
										<c:forEach items="${rlist}" var="rdto" varStatus="status">
										<tr>
											<td>${status.index + 1}</td>
											<td>${rdto.hospitalName}</td>
											<td>${rdto.doctorName}</td>
											<td>${rdto.treatmentDate}</td>
											<td>${rdto.symptom}</td>
											<td>${rdto.mediWay}</td>
										</tr>
										</c:forEach>
									</table>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-12">
                            <div class="card shadow mb-5">
                                <!-- Card Header - Dropdown -->
                                <div
                                    class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                    <h5 class="m-0 font-weight-bold text-primary">진료 내역</h5>
                                </div>
                                <!-- Card Body -->
                                <div class="card-body">
									<table class="list">
										<tr>
											<th>번호</th>
											<th>병원명</th>
											<th>담당 의사</th>
											<th>방문 날짜</th>
											<th>진단 내용</th>
											<th>상세 내용</th>
											<th>리뷰</th>
											<th>제증명</th>
										</tr>
										<c:forEach items="${hlist}" var="hdto" varStatus="status">
										<tr>
											<td>${status.index + 1}</td>
											<td>${hdto.hospitalName}</td>
											<td>${hdto.doctorName}</td>
											<td>${hdto.treatmentDate}</td>
											<td>${hdto.mediName}</td>
											<td><button type="button" class="btn" onclick="viewDetail(${hdto.mediHistorySeq},${hdto.childSeq})">상세보기</button></td>
											<c:choose>
												<c:when test="${hdto.reviewSeq eq null}">
													<td><button type="button" class="btn" onclick="insertReview(${hdto.mediHistorySeq})">리뷰작성</button></td>
												</c:when>
												<c:otherwise>
													<td><button type="button" class="btn" id="btn-complete">작성완료</button></td>
												</c:otherwise>
											</c:choose>
											<c:choose>
												<c:when test="${hdto.reqDocumentSeq eq null}">
													<td><button type="button" class="btn" onclick="insertRequestDocument(${hdto.mediHistorySeq})">신청하기</button></td>
												</c:when>
												<c:otherwise>
													<td><button type="button" class="btn" id="btn-complete">신청완료</button></td>
												</c:otherwise>
											</c:choose>											
										</tr>
										</c:forEach>
									</table>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-12">
                            <div class="card shadow mb-5">
                                <!-- Card Header - Dropdown -->
                                <div
                                    class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                    <h5 class="m-0 font-weight-bold text-primary">건강 검진 내역</h5>
                                </div>
                                <!-- Card Body -->
                                <div class="card-body">
									<table id="clist">
										<tr>
											<th>번호</th>
											<th>병원명</th>
											<th>방문 날짜</th>
											<th>진행 상태</th>
										</tr>
										<c:forEach items="${clist}" var="cdto" varStatus="status">
										<tr>
											<td>${status.index + 1}</td>
											<td>${cdto.hospitalName}</td>
											<td>${cdto.reservationDate}</td>
											<td>${cdto.isCheckup}</td>
										</tr>
										</c:forEach>
									</table>
                                </div>
                            </div>
                        </div>
                    </div>
            	</div>
            </div>
            
            <!-- End of Main Content -->
			<%@ include file="/WEB-INF/views/inc/endofmaincontent.jsp" %>
		
		
		<script>
			
			function viewDetail(seq,cseq) {
				
		        var winHeight = 1000;
		        var winWidth = 800;
		        var winTop = (screen.height / 2) - (winHeight / 2);
		        var winLeft = (screen.width / 2) - (winWidth / 2);
		        window.open('/apa/user/treatment/view.do?seq=' + seq + '&cseq=' + cseq, '진료 내역', 'height=' + winHeight + ',width=' + winWidth + ',top=' + winTop + ',left=' + winLeft);
			
			}
			
			function insertReview(seq) {

				var winHeight = 1000;
		        var winWidth = 800;
		        var winTop = (screen.height / 2) - (winHeight / 2);
		        var winLeft = (screen.width / 2) - (winWidth / 2);
		        var newWin = window.open('/apa/user/review/add.do?seq=' + seq, '리뷰 작성', 'height=' + winHeight + ',width=' + winWidth + ',top=' + winTop + ',left=' + winLeft);
		        
		        var timer = setInterval(function() { 
		            if(newWin.closed) {
		                clearInterval(timer);
		                location.reload();   // 부모 창 새로고침
		            }
		        }, 500);
		        
			}
			
			function insertRequestDocument(seq) {

				var winHeight = 1000;
		        var winWidth = 800;
		        var winTop = (screen.height / 2) - (winHeight / 2);
		        var winLeft = (screen.width / 2) - (winWidth / 2);
		        var newWin = window.open('/apa/user/treatment/request.do?seq=' + seq, '서류 신청', 'height=' + winHeight + ',width=' + winWidth + ',top=' + winTop + ',left=' + winLeft);
		        
		        var timer = setInterval(function() { 
		            if(newWin.closed) {
		                clearInterval(timer);
		                location.reload();   // 부모 창 새로고침
		            }
		        }, 500);
		        
			}
			
		</script>
</body>
</html>