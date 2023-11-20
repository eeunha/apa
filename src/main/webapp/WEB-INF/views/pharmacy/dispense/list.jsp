<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/assetmypage.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">


</head>
<style>

  .display {
            border-collapse: collapse;
            width: 100%;
        }
        .display th, .display td {
            border: 1px solid #dddddd;
            text-align: center;
            padding: 8px;
        }
        .display th {
            background-color: #f2f2f2;
        }
		
		.button-class:hover {
		    border: 5px solid #5BC1AC; 
		}
		
		.pagebar{
			text-align: center;
		}
		#pagebar {
		    text-align: center;
		}
</style>
<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">
		<%@ include file="/WEB-INF/views/inc/pharmacyslidebar.jsp"%>

		<!-- Divider -->
		<hr class="sidebar-divider d-none d-md-block">

		</ul>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

					<!-- Sidebar Toggle (Topbar) -->
					<button id="sidebarToggleTop"
						class="btn btn-link d-md-none rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>

					<!-- Page Heading -->
					<div class="col-auto">
						<i class="fas fa-pills fa-2x text-navy-300"></i>
					</div>
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800 hansans"
							style="padding-top: 28px;">약 제조 관리</h1>
					</div>

					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">

						<div class="topbar-divider d-none d-sm-block"></div>
						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <span
								class="mr-2 d-none d-lg-inline text-gray-600 small">${name}(${id})님</span>
								<img class="img-profile rounded-circle"
								src="/apa/asset/images/undraw_profile.svg">
						</a> <!-- Dropdown - User Information -->
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="userDropdown">
								<a class="dropdown-item" href="#"> <i
									class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> 내정보
								</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#" data-toggle="modal"
									data-target="#logoutModal"> <i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
									로그아웃
								</a>
							</div></li>

					</ul>

				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Content Row -->
					<div class="row">

						<!-- 목록 -->
						<div class="col-xl-3 col-md-6 mb-4 button-class" id="waiting">
							<div class="card border-left-primary shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-info text-uppercase mb-1">제조 대기</div>
											<div class="row no-gutters align-items-center">
												<div class="col-auto">
													<div class="h5 mb-0 mr-3 font-weight-bold text-gray-800" id="waitingCount">${cntDto.watingCnt} 건 </div>
												</div>
												<div class="col">
													<div class="progress progress-sm mr-2">
														<div class="progress-bar bg-info" role="progressbar"
															style="width: 40%" aria-valuenow="50" aria-valuemin="0"
															aria-valuemax="100"></div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-calendar fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- Earnings (Monthly) Card Example -->
						<div class="col-xl-3 col-md-6 mb-4 button-class" id="dispensing">
							<div class="card border-left-success shadow h-100 py-2"
								id="dispensing">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-info text-uppercase mb-1">제조중
											</div>
											<div class="row no-gutters align-items-center">
												<div class="col-auto">
													<div class="h5 mb-0 mr-3 font-weight-bold text-gray-800" id="dispensingCount">${cntDto.jejoCnt} 건</div>
												</div>
												<div class="col">
													<div class="progress progress-sm mr-2">
														<div class="progress-bar bg-info" role="progressbar"
															style="width: 60%" aria-valuenow="50" aria-valuemin="0"
															aria-valuemax="100"></div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-prescription-bottle-alt fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- Earnings (Monthly) Card Example -->
						<div class="col-xl-3 col-md-6 mb-4 button-class" id="complete">
							<div class="card border-left-info shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-info text-uppercase mb-1 onclick="addBorderEffect(this, 'waiting')">제조
												완료</div>
											<div class="row no-gutters align-items-center">
												<div class="col-auto">
													<div class="h5 mb-0 mr-3 font-weight-bold text-gray-800" id="completeCount">${cntDto.jejoFinCnt} 건</div>
												</div>
												<div class="col">
													<div class="progress progress-sm mr-2">
														<div class="progress-bar bg-info" role="progressbar"
															style="width: 75%" aria-valuenow="50" aria-valuemin="0"
															aria-valuemax="100"></div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- Pending Requests Card Example -->
						<div class="col-xl-3 col-md-6 mb-4 button-class" id="pickup">
							<div class="card border-left-warning shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-info text-uppercase mb-1">수령 완료</div>
											<div class="row no-gutters align-items-center">
												<div class="col-auto">
													<div class="h5 mb-0 mr-3 font-weight-bold text-gray-800" id="pickUpCount">${cntDto.surungFinCnt} 건</div>
												</div>
												<div class="col">
													<div class="progress progress-sm mr-2">
														<div class="progress-bar bg-info" role="progressbar"
															style="width: 100%" aria-valuenow="50" aria-valuemin="0"
															aria-valuemax="100"></div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-shopping-basket fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>


					<!-- Content Row -->

					<div class="row">

						<!-- Area Chart -->
						<div class="col-xl-12">
							<div class="card shadow mb-4">
								<!-- Card Header - Dropdown -->
								<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
									<h6 class="m-0 font-weight-bold text-primary">목록</h6>
									<h6 class="m-0 font-weight-bold text-primary">총 ${cntDto.totalCnt}건</h6>
								</div>
								<table id="dispense" class="display" style="width: 100%">
							    <thead>
							        <tr>
							            <th>제조번호</th>
							            <th>병원이름</th>
							            <th>회원이름</th>
							            <th>연락처</th>
							            <th>요청일자</th>
							            <th>수령일자</th>
							            <th>수령방법</th>
							            <th>진행상태</th>
							        </tr>
							    </thead>
							    <tbody>
							        <c:forEach items="${dtoList}" var="dto">
							            <tr>
							                <td>${dto.dispenseSeq}</td>                                                
							                <td>${dto.hospitalName}</td>
							                <td>${dto.userName}</td>
							                <td>${dto.userTel}</td>
							                <td>${dto.treatmentDate}</td>
							                <td>${dto.regdate}</td>
							                <td>${dto.pickupWay}</td>
							                <td>${dto.dispenseStatus}</td>
							            </tr>       
							        </c:forEach>        
							    </tbody>
							</table>
							
							<div id="pagebar">${pagebar}</div>
							
							</div>
							<!-- Card Body -->
							<div class="card-body">
								<div class="chart-area">
									<canvas id="myAreaChart"></canvas>
								</div>
							</div>
						</div>
					</div>

					<!-- Pie Chart -->

					<!-- Card Body -->
					<div class="card-body">
						<div class="chart-pie pt-4 pb-2">
							<canvas id="myPieChart"></canvas>
						</div>
						<div class="mt-4 text-center small"></div>
					</div>
				</div>
			</div>
		</div>

		<!-- Content Row -->
		<div class="row">

			<!-- Content Column -->
			<div class="col-lg-6 mb-4"></div>
		</div>
		<!-- End of Main Content -->
	</div>
	<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Footer -->
	<%@ include file="/WEB-INF/views/inc/pharmacyfooter.jsp"%>

	<!-- Logout Modal-->
	<%@ include file="/WEB-INF/views/inc/pharmacylogouttop.jsp"%>


	
	
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

	<script>
	$(document).ready(function() {
	    // 제조 대기 클릭 시
	    $("#waiting").click(function() {
	        location.href = "/apa/pharmacy/dispense/waiting.do?pharmacyId=${pharmacyId}";
	    });

	    // 제조 중 클릭 시
	    $("#dispensing").click(function() {
	        location.href = "/apa/pharmacy/dispense/dispensing.do?pharmacyId=${pharmacyId}";
	    });

	    // 제조 완료 클릭 시
	    $("#complete").click(function() {
	        location.href = "/apa/pharmacy/dispense/complete.do?pharmacyId=${pharmacyId}";
	    });

	    // 수령 완료 클릭 시
	    $("#pickup").click(function() {
	        location.href = "/apa/pharmacy/dispense/pickup.do?pharmacyId=${pharmacyId}";
	    });
	});
	</script>


</body>
</html>