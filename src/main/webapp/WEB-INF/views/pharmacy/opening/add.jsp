<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/assetmypage.jsp" %>
<style>
	table.open {
		border-collapse: collapse;
		text-align: left;
		line-height: 1.5;
		border-top: 1px solid #ccc;
		border-left: 5px solid #5BC1AC;
		margin: 20px 10px;
	}
	
	table.open th {		
		width: 147px;
		padding: 10px;
		font-weight: bold;
		vertical-align: top;
		color: #153d73;
		border-right: 1px solid #ccc;
		border-bottom: 1px solid #ccc;
	}
	
	table.open td {
		width: 349px;
		padding: 10px;
		vertical-align: top;
		border-right: 1px solid #ccc;
		border-bottom: 1px solid #ccc;
	}
	
	.info-container {
		display: flex;
		flex-wrap: wrap;
		align-items: center;
	}
	
	.info-item {
		margin-right: 20px;
		margin-bottom: 10px;
	}
	
	.checkbox-label {
		margin-left: 5px;
	}
	
	#open-update-btn {
		text-align: center;
	}
	
	#update-btn {
		border: 0;
		background-color: #5BC1AC;
		color: white;
		width: 7em;
		height: 2em;
		border-radius: 0.35rem;
		font-size: 1em;
	}
	
	.open-info table {
		font-weight: bold; /* 글씨체를 bold로 지정 */
		border-radius: 10px;
	}
	
	 select {
        border: 1px solid #ccc; /* 테두리 선 설정 */
        border-radius: 5px; /* 둥근 테두리 설정 */
        padding: 5px; /* 안쪽 여백 설정 */
        background-color: #f2f2f2; /* 배경 색상 설정 */
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
                         <i class="fas fa-laptop-medical fa-2x text-navy-300"></i>
                     </div>
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

                        <!-- 제조 -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-primary shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                운영시간</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">${dto.openTime} ~ ${dto.closeTime}</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-clock fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Earnings (Monthly) Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-success shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                야간 운영 여부</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">${dto.isPharmarcyNightCare}</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-moon fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Earnings (Monthly) Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-info shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1">휴무 요일
                                            </div>
                                            <div class="row no-gutters align-items-center">
                                                <div class="col-auto">
                                                    <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">${dto.pharmacyDayOff}요일</div>
                                                </div>
                                                <div class="col">
                                                    <!-- <div class="progress progress-sm mr-2">
                                                        <div class="progress-bar bg-info" role="progressbar"
                                                            style="width: 50%" aria-valuenow="50" aria-valuemin="0"
                                                            aria-valuemax="100"></div>
                                                    </div> -->
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-calendar-alt fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Pending Requests Card Example -->
                        <div class="col-xl-3 col-md-6 mb-4">
                            <div class="card border-left-warning shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                               휴일 운영 여부</div>
                                            <div class="h5 mb-0 font-weight-bold text-gray-800">${dto.isPharmarcyHoliday}</div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-calendar-check fa-2x text-gray-300"></i>
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
                                    <h6 class="m-0 font-weight-bold text-primary">운영 정보</h6>
                                    <div class="dropdown no-arrow">
              
                                       
                                    </div>
                                </div>
                                <!-- Card Body -->
								<div class="card-body">
									<div id="open-info">
										<form id="add-form" method="POST" action="/apa/pharmacy/opening/add.do?pharmacyId=${pharmacyId}">

											<table class="open" style="width: 50%">
												<tr>
													<th scope="row">운영시간</th>
													<td>
														<select name="openTime">
															<option value="00:00">00:00</option>
															<option value="01:00">01:00</option>
															<option value="02:00">02:00</option>
															<option value="03:00">03:00</option>
															<option value="04:00">04:00</option>
															<option value="05:00">05:00</option>
															<option value="06:00">06:00</option>
															<option value="07:00">07:00</option>
															<option value="08:00">08:00</option>
															<option value="09:00">09:00</option>
															<option value="10:00">10:00</option>
															<option value="11:00">11:00</option>
															<option value="12:00">12:00</option>
															<option value="13:00">13:00</option>
															<option value="14:00">14:00</option>
															<option value="15:00">15:00</option>
															<option value="16:00">16:00</option>
															<option value="17:00">17:00</option>
															<option value="18:00">18:00</option>
															<option value="19:00">19:00</option>
															<option value="20:00">20:00</option>
															<option value="21:00">21:00</option>
															<option value="22:00">22:00</option>
															<option value="23:00">23:00</option>
															
													</select> ~ 
													<select	name="closeTime">
															<option value="00:00">00:00</option>
															<option value="01:00">01:00</option>
															<option value="02:00">02:00</option>
															<option value="03:00">03:00</option>
															<option value="04:00">04:00</option>
															<option value="05:00">05:00</option>
															<option value="06:00">06:00</option>
															<option value="07:00">07:00</option>
															<option value="08:00">08:00</option>
															<option value="09:00">09:00</option>
															<option value="10:00">10:00</option>
															<option value="11:00">11:00</option>
															<option value="12:00">12:00</option>
															<option value="13:00">13:00</option>
															<option value="14:00">14:00</option>
															<option value="15:00">15:00</option>
															<option value="16:00">16:00</option>
															<option value="17:00">17:00</option>
															<option value="18:00">18:00</option>
															<option value="19:00">19:00</option>
															<option value="20:00">20:00</option>
															<option value="21:00">21:00</option>
															<option value="22:00">22:00</option>
															<option value="23:00">23:00</option>
													
													</select>
													</td>
												</tr>
												<tr>
													<th scope="row">운영여부</th>
													<td>야간
														<select name="isPharmarcyNightCare">
															<option value="Y">Y</option>
															<option value="N">N</option>
														</select> 휴일
														<select	name="isPharmarcyHoliday">
																<option value="Y">Y</option>
																<option value="N">N</option>
														</select> 
													</td>
												</tr>
												<tr>
													<th scope="row">휴무일</th>
													<td>
													<select	name="pharmacyDayOff">
															<option value="일">일</option>
															<option value="월">월</option>
															<option value="화">화</option>
															<option value="수">수</option>
															<option value="목">목</option>
															<option value="금">금</option>
															<option value="토">토</option>											
													</select> 요일
													</td>
												</tr>
											</table>
											<div id="open-update-btn">
												<button type="button" id="update-btn" onclick="confirmAndAdd()">등록완료</button>
												<button type="button" id="update-btn" onclick="location.href='/apa/pharmacy/opening/view.do';">돌아가기</button>
											</div>
										</form>
									</div>
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

          

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

                <!-- Footer -->
          <%@ include file="/WEB-INF/views/inc/pharmacyfooter.jsp" %>   
    
    <!-- Logout Modal-->
    <%@ include file="/WEB-INF/views/inc/pharmacylogouttop.jsp" %>  
    
   
    <script>
    function confirmAndAdd() {        
        var isConfirmed = confirm('등록하시겠습니까?');
        
        // 확인이 눌렸을 때
        if (isConfirmed) {         
            document.getElementById('add-form').submit();
        }
    }
    
    
	</script>

    
    
 
</body>
</html>