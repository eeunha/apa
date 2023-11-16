<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/assetmypage.jsp" %>
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
        
        #dispense-list-btn {
		text-align: center;
		}
		
		#list-btn {
			border: 0;
			background-color: #5BC1AC;
			color: white;
			width: 7em;
			height: 2em;
			border-radius: 0.35rem;
			font-size: 1em;
		}
		#rejectBtn{
			border: 0;
			background-color: #5BC1AC;
			color: white;
			width: 4em;
			height: 2em;
			border-radius: 0.35rem;
			font-size: 1em;
		}
		#approveBtn{
			border: 0;
			background-color: #5BC1AC;
			color: white;
			width: 4em;
			height: 2em;
			border-radius: 0.35rem;
			font-size: 1em;
		}
		
		
		.button-class:hover {
		    border: 5px solid #5BC1AC; 
		}
	
		#pagebar {
		    text-align: center;
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
		
</style>
<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

     <%@ include file="/WEB-INF/views/inc/pharmacyslidebar.jsp"%>
       

         

            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">

            <!-- Sidebar Toggler (Sidebar) -->
           <!--  <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div> -->

            <!-- Sidebar Message -->
            <!-- <div class="sidebar-card d-none d-lg-flex">
                <img class="sidebar-card-illustration mb-2" src="/apa/asset/images/undraw_rocket.svg" alt="...">
                <p class="text-center mb-2"><strong>SB Admin Pro</strong> is packed with premium features, components, and more!</p>
                <a class="btn btn-success btn-sm" href="https://startbootstrap.com/theme/sb-admin-pro">Upgrade to Pro!</a>
            </div> -->

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
					<div class="col-auto">
                         <i class="fas fa-clinic-medical fa-2x text-navy-300"></i>
                     </div>
	                <div class="d-sm-flex align-items-center justify-content-between mb-4">
	                	<h1 class="h3 mb-0 text-gray-800 hansans" style="padding-top: 28px;">입점 관리</h1>
	                </div>
	                    
                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">


	                    
                        <div class="topbar-divider d-none d-sm-block"></div>
                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${dto.pharmacyName}(${dto.pharmacyId})님</span>
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

                        <!-- 진료대기 -->
                        <div class="col-xl-4 col-md-6 mb-4">
                            <div class="card border-left-primary shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                진행 현황</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">${dto.status}</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-clinic-medical fa-2x text-gray-300"></i>
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
								<div
									class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
									<h6 class="m-0 font-weight-bold text-primary">업점신청목록</h6>
									
								</div>
								<table id="dispenseWaiting" class="display" style="width: 100%">
							
							        <tr>
							            <th>입점신청번호</th>
							            <th>입점신청서</th>
							            <th>입점신청날짜</th>
							            <th>진행상태</th>							         
							        </tr>
							 						     
							            <tr>
							                <td>1</td>                                                
							                <td><button type="button" class="btn" onclick="location.href='/apa/pharmacy/entry/apply.do?pharmacyId=${dto.pharmacyId}';">입점신청서</button></td>
							                <td>${dto.regdate}</td>
							                <td>${dto.status}</td>
							            </tr>       							           
							   
							</table>						
							
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
                                    <div class="mt-4 text-center small">
                                      
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Content Row -->
                    <div class="row">

                        <!-- Content Column -->
                        <div class="col-lg-6 mb-4">

                  

                        </div>


            </div>
            <!-- End of Main Content -->

       
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

	<!-- Footer -->
	<%@ include file="/WEB-INF/views/inc/pharmacyfooter.jsp"%>

	<!-- Logout Modal-->
	<%@ include file="/WEB-INF/views/inc/pharmacylogouttop.jsp"%>
	<script>

	
	function viewDetail(pharmacyId) {
	    var winHeight = 1000; // 새 창의 높이
	    var winWidth = 800; // 새 창의 너비
	    var winTop = (screen.height / 2) - (winHeight / 2); // 새 창이 화면의 중앙에 위치하도록 설정
	    var winLeft = (screen.width / 2) - (winWidth / 2); // 새 창이 화면의 중앙에 위치하도록 설정

	    // 새 창 열기
	    window.open('/apa/pharmacy/entry/apply.do?pharmacyId=' + pharmacyId, '입점신청서', 'height=' + winHeight + ',width=' + winWidth + ',top=' + winTop + ',left=' + winLeft);
	}
	
	
	
	</script>

</body>
</html>