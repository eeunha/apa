<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/assetmypage.jsp"%>
<style>
main {
	font-size: 18px;
}

#adminCounsel {
	text-align: center;
}

#view {
	width: 90%;
}

#pagebar {
	margin-top: 30px;
	text-align: center;
}

#questionTitle {
	text-align: left;
}

#doctorview {
	width: 90%;
}

#DoctorName {
	padding-right: 20px;
}
.back{
	border-radius: 4px;
	border: 1px solid #D0E5E1;
	background-color: #D0E5E1;
}
.del{
	border-radius: 4px;
	border: 1px solid #D0E5E1;
	background-color: #D0E5E1;
}
</style>
<body id="page-top">
	<main>


		<!-- Page Wrapper -->
		<div id="wrapper">

			<!-- Sidebar -->
			<ul
				class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
				id="accordionSidebar">

				<!-- Sidebar - Brand -->
				<a
					class="sidebar-brand d-flex align-items-center justify-content-center"
					href="/apa/main.do">
					<div class="sidebar-brand-icon rotate-n-15">
						<i class="fas fa-laugh-wink"></i>
					</div>
					<div class="sidebar-brand-text mx-3 hansans">아파! 어디가?</div>
				</a>

				<!-- Divider -->
				<hr class="sidebar-divider my-0">

				<!-- Nav Item - Dashboard -->
				<li class="nav-item active"><a class="nav-link"
					href="/apa/admin/info/view.do?seq=${dto.userSeq}" style=""> <i
						class="fas fa-fw fa-tachometer-alt"></i> <span>마이페이지</span></a></li>

				<!-- Divider -->
				<hr class="sidebar-divider">

				<!-- Nav Item - Pages Collapse Menu -->
				<li class="nav-item"><a class="nav-link collapsed"
					href="/apa/admin/info/view.do"> <i class="fas fa-fw fa-user"></i>
						<span>내 정보</span>
				</a></li>

				<!-- Nav Item - Utilities Collapse Menu -->
				<li class="nav-item active"><a class="nav-link collapsed"
					data-toggle="collapse" data-target="#collapseTwo"
					aria-expanded="true" aria-controls="collapseTwo"> <i
						class="fas fa-fw fa-file-alt"></i> <span>회원 관리</span>
				</a>
					<div id="collapseTwo" class="collapse show"
						aria-labelledby="headingTwo" data-parent="#accordionSidebar">
						<div class="bg-white py-2 collapse-inner rounded">
							<a class="collapse-item active"
								href="/apa/admin/user/general/generalmain.do">일반 회원</a> <a
								class="collapse-item" href="/apa/admin/user/hospital/list.do">병원
								회원</a> <a class="collapse-item"
								href="/apa/admin/user/pharmacy/list.do">약국 회원</a>
						</div>
					</div></li>

				<!-- Nav Item - Pages Collapse Menu -->
				<li class="nav-item"><a class="nav-link collapsed" href="#"
					data-toggle="collapse" data-target="#collapseUtilities"
					aria-expanded="true" aria-controls="collapseUtilities"> <i
						class="fas fa-fw fa-file-alt"></i> <span>입점 관리</span>
				</a>
					<div id="collapseUtilities" class="collapse"
						aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
						<div class="bg-white py-2 collapse-inner rounded">
							<a class="collapse-item"
								href="/apa/admin/company/entry/hospitallist.do">병원 회원</a> <a
								class="collapse-item"
								href="/apa/admin/company/entry/pharmacy.do">약국 회원</a>
						</div>
					</div></li>

				<!-- Nav Item - Charts -->
				<li class="nav-item"><a class="nav-link"
					href="/apa/admin/company/after/list.do"> <i
						class="fas fa-fw fa-hospital-alt"></i> <span>병원 사후관리</span></a></li>

				<!-- Nav Item - Utilities Collapse Menu -->
				<li class="nav-item active"><a class="nav-link collapsed"
					data-toggle="collapse" data-target="#collapseTwo"
					aria-expanded="true" aria-controls="collapseTwo"> <i
						class="fas fa-fw fa-file-alt"></i> <span>게시판 관리</span>
				</a>
					<div id="collapseTwo" class="collapse show"
						aria-labelledby="headingTwo" data-parent="#accordionSidebar">
						<div class="bg-white py-2 collapse-inner rounded">
							<a class="collapse-item" href="/apa/admin/advice/list.do">의학상담</a>
							<a class="collapse-item" href="/apa/admin/community/list.do">커뮤니티</a>
						</div>
					</div></li>

				<!-- Divider -->
				<hr class="sidebar-divider d-none d-md-block">

			</ul>
			<!-- End of Sidebar -->





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
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800 hansans"
							style="padding-top: 28px;">의학상담 관리</h1>
					</div>

					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">
						<div class="topbar-divider d-none d-sm-block"></div>
						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <span
								class="mr-2 d-none d-lg-inline text-gray-600 small"></span> <img
								class="img-profile rounded-circle"
								src="/apa/asset/images/undraw_profile.svg">
						</a></li>
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
									<h5 class="m-0 font-weight-bold text-primary">의학상담 질문</h5>
								</div>
								<!-- Card Body -->
								<div class="card-body">
									<main id="main">
										<main id="main">
											<h1>
												게시판 <small>삭제하기</small>
											</h1>
											<form method="POST" action="/apa/admin/advice/del.do">
												<div>
													<button type="button" class="back"
														onclick="location.href='/apa/admin/advice/view.do?seq=${seq}';">돌아가기</button>
													<button type="submit" class="del">삭제하기</button>
												</div>
												<input type="hidden" name="seq" value="${seq}">
											</form>

										</main>
									</main>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
</body>
<script>
	$('#selPage').val(${nowPage});
</script>

</html>