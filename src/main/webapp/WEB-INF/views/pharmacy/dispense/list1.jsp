<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/assetmypage.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>jQuery Grid Example</title>
<!-- jQuery 라이브러리 로드 -->
<link rel="stylesheet" href="/apa/asset/jqwidgets/jqwidgets/styles/jqx.base.css"/>

<!-- DataTables 라이브러리 로드 -->
<!-- 
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script> -->
</head>

<!-- <script type="text/javascript" src="/js/common/jquery/jquery-3.2.1.min.js"></script>
<script src="/jqGrid/js/i18n/grid.locale-kr.js" type="text/javascript"></script>
<script src="/jqGrid/js/minified/jquery.jqGrid.min.js" type="text/javascript"></script>  -->
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
								class="mr-2 d-none d-lg-inline text-gray-600 small">-----님</span>
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

						<!-- 제조대기 -->
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card border-left-primary shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-info text-uppercase mb-1"
												onclick="">제조 대기</div>
											<div class="row no-gutters align-items-center">
												<div class="col-auto">
													<div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">값넣기</div>
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
						<div class="col-xl-3 col-md-6 mb-4">
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
													<div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">값넣기</div>
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
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card border-left-info shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-info text-uppercase mb-1">제조
												완료</div>
											<div class="row no-gutters align-items-center">
												<div class="col-auto">
													<div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">값넣기</div>
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
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card border-left-warning shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-xs font-weight-bold text-info text-uppercase mb-1">수령
												완료</div>
											<div class="row no-gutters align-items-center">
												<div class="col-auto">
													<div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">값넣기</div>
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
								<div
									class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
									<h6 class="m-0 font-weight-bold text-primary">제조 대기</h6>

								</div>
								<table id="example" class="display" style="width: 100%">
									<div class="dropdown no-arrow">
										<thead>
											<tr>
												<th>ID</th>
												<th>Name</th>
												<!-- 다른 열들을 추가할 수 있습니다. -->
											</tr>
										</thead>
										<tbody>
											<!-- 데이터 행들을 추가할 수 있습니다. -->
											<div id="grid"></div>
										</tbody>
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


	<script src="/apa/asset/jqwidgets/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="/apa/asset/jqwidgets/jqwidgets/jqxcore.js"></script>
    <script type="text/javascript" src="/apa/asset/jqwidgets/jqwidgets/jqxdata.js"></script> 
    <script type="text/javascript" src="/apa/asset/jqwidgets/jqwidgets/jqxbuttons.js"></script>
    <script type="text/javascript" src="/apa/asset/jqwidgets/jqwidgets/jqxscrollbar.js"></script>
    <script type="text/javascript" src="/apa/asset/jqwidgets/jqwidgets/jqxmenu.js"></script>
    <script type="text/javascript" src="/apa/asset/jqwidgets/jqwidgets/jqxcheckbox.js"></script>
    <script type="text/javascript" src="/apa/asset/jqwidgets/jqwidgets/jqxlistbox.js"></script>
    <script type="text/javascript" src="/apa/asset/jqwidgets/jqwidgets/jqxdropdownlist.js"></script>
    <script type="text/javascript" src="/apa/asset/jqwidgets/jqwidgets/jqxgrid.js"></script>
    <script type="text/javascript" src="/apa/asset/jqwidgets/jqwidgets/jqxgrid.sort.js"></script> 
    <script type="text/javascript" src="/apa/asset/jqwidgets/jqwidgets/jqxgrid.pager.js"></script> 
    <script type="text/javascript" src="/apa/asset/jqwidgets/jqwidgets/jqxgrid.selection.js"></script> 
    <script type="text/javascript" src="/apa/asset/jqwidgets/jqwidgets/jqxgrid.edit.js"></script> 
    <script type="text/javascript" src="/apa/asset/jqwidgets/jqwidgets/jqxgrid.filter.js"></script>
    <script type="text/javascript" src="/apa/asset/jqwidgets/scripts/demos.js"></script>
	<script>
		$(document).ready(function () {
	        //var url = "/apa/products.xml";
	        //var url = "ajax 반환 서블릿 주소";
	        var url = "/apa/list.do";
	        // prepare the data
	        var source =
	        {
	            datatype: "json",
	            datafields: [
	                { name: 'ProductName', type: 'string' },
	                { name: 'price', type: 'float' }
	                /* ,
	                { name: 'QuantityPerUnit', type: 'int' },
	                { name: 'UnitPrice', type: 'float' },
	                { name: 'UnitsInStock', type: 'float' },
	                { name: 'Discontinued', type: 'bool' } */
	            ],
	            url: url
	        };
	        var cellsrenderer = function (row, columnfield, value, defaulthtml, columnproperties, rowdata) {
	            if (value < 20) {
	                return '<span style="margin: 4px; margin-top:8px; float: ' + columnproperties.cellsalign + '; color: #ff0000;">' + value + '</span>';
	            }
	            else {
	                return '<span style="margin: 4px; margin-top:8px; float: ' + columnproperties.cellsalign + '; color: #008000;">' + value + '</span>';
	            }
	        }
	        var dataAdapter = new $.jqx.dataAdapter(source, {
	            downloadComplete: function (data, status, xhr) { },
	            loadComplete: function (data) { },
	            loadError: function (xhr, status, error) { }
	        });
	        // initialize jqxGrid
	        $("#grid").jqxGrid(
	        {
	            width: getWidth('Grid'),
	            source: dataAdapter,                
	            pageable: true,
	            autoheight: true,
	            sortable: true,
	            altrows: true,
	            enabletooltips: true,
	            editable: true,
				filterable: true,
	            selectionmode: 'multiplecellsadvanced',
	            columns: [
	              { text: 'Product Name', columngroup: 'ProductDetails', datafield: 'ProductName', width: 250 },
	              { text: 'Price', columngroup: 'ProductDetails', datafield: 'price', align: 'right', cellsalign: 'right', cellsformat: 'c2', width: 200 }
	            
	            ],
	            columngroups: [
	                { text: 'Product Details', align: 'center', name: 'ProductDetails' }
	            ]
	        });
	    });
	</script>

</body>
</html>