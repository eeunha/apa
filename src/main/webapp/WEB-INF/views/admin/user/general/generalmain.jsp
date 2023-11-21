<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/assetmypage.jsp" %>
<style>

	#list {
		text-align: center;
	}
		
	#list > thead > tr > th {
	    color: white;
		font-size: 1.3rem;
	    background-color: #5BC1AC;
	    font-weight: lighter;
    }

	#list > thead > tr > th:nth-child(1) { width: 150px; }
	#list > thead > tr > th:nth-child(2) { width: 200px; }
	#list > thead > tr > th:nth-child(3) { width: 250px; }
	#list > thead > tr > th:nth-child(4) { width: 40px; }
	#list > thead > tr > th:nth-child(5) { width: 150px; }

	#list > tbody > tr > td {
		font-size: 1.2rem;
		color: #008e63;
	}

	#list > tbody > tr:nth-child(even) {
		background-color: #ecfffb;
	}
	
	#list > tbody > tr:nth-child(odd) {
		background-color: #d3ffeb;
	}

	#list > tbody > tr > td > input { text-align: center; }

	#list > tbody > tr > td > div > input { text-align: center; }

	#list > tbody > tr > td:nth-child(1) > input { width: 148px; }

	#list > tbody > tr > td:nth-child(2) > div > input { width: 80px; }

	#list > tbody > tr > td:nth-child(3) > div > input { width: 60px; }
	
	#list > tbody > tr > td:nth-child(4) > div {
		display: grid;
		justify-items: center;
	}
	
	#list > tbody > tr > td:nth-child(5) > div {
		display: flex;
		justify-content: space-evenly;
		margin-top: 5px;
	    margin-bottom: 5px;
	}

	#list > tbody > tr > td:nth-child(5) > div > i {
		font-size: 2rem;
		cursor: pointer;
	}
	
	#list tr th:first-child { border-top-left-radius: 10px; }
	#list tr th:last-child { border-top-right-radius: 10px; }
	#list tr:last-child td:first-child	{ border-bottom-left-radius: 10px; }
	#list tr:last-child td:last-child	{ border-bottom-right-radius: 10px; }
	
	.out, .in, .add {
		margin-top: 20px;
	    background-color: #5BC1AC;
	    color: white;
	    border: 0;
	    border-radius: 5px;
	    padding-left: 20px;
	    padding-right: 20px;
	    padding-top: 5px;
	    padding-bottom: 5px;
	    /* font-size: 1.2rem; */
	}
	
	#add, #btnAdd {
		display: none;
	}

	#add > tbody > tr > th {
		padding-right: 3rem;
		padding-top: 10px;
   		padding-bottom: 10px;	
	}
	
	input {
		border: 1px solid #c6c6c6;
	    text-align: center;
	    border-radius: 5px;
	    outline-color: #1cc88a;
	}
	
	
	#name { width: 13rem; }
	
	#ssn1 { width: 5.5rem; }

	#ssn2 {	width: 6.5rem; }
	
	#tel1 { width: 3rem; }
	#tel2 { width: 4rem; }
	#tel3 { width: 4rem; }
	
	.ustbl {
    	text-align: center; /* 테이블을 수평 가운데 정렬 */
    	margin: auto; /* 테이블을 수직 가운데 정렬 */
	}
	
	table {
		width: 1500px;
		margin: 15px 0;
		border: 0;
	}
	
	table th {
		font-weight: bold;
		background-color: #D0E5E1;
		color: #5D5D5D;
	}
	
	table, table th, table td {
		text-align: center;
		padding: 4px;
		border-collapse: collapse;
	}
	
	table th, table td {
		border: 1px solid #ffffff;
		border-width: 1px
	}
	
	table th {
		font-size: 1rem;
		border: 1px solid #e7f6fe;
		border-width: 1px 0 1px 0
	}
	
	table td {
		font-size: 0.9rem;
		border: 1px solid #eeeeee;
		border-width: 1px 0 1px 0
	}
	
	table tr {
		border: 1px solid #ffffff;
	}
	
	table tr:nth-child(odd) {
		background-color: #f7f7f7;
	}
	
	table tr:nth-child(even) {
		background-color: #ffffff;
	}
	
	div {
		text-align: right;
	}
	#btn {
		text-align: center;
		border: 1px solid green;
		border-radius: 5px;
	}
	#pagebar {
		text-align: center;
		margin-bottom: 15px;
	}
	#collapseTwo > div {
		text-align: left;
	}
	.collapse-item {
		text-align: left;
	}
	.semi-title {
		padding-top: 20px;
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
                <a class="nav-link" href="/apa/admin/info/view.do?id=${id}" style="">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>마이페이지</span></a>
            </li>			
			
            <!-- Divider -->
            <hr class="sidebar-divider">
            
            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="/apa/admin/info/view.do?id=${id}">
                    <i class="fas fa-fw fa-user"></i>
                    <span>내 정보</span>
                </a>
            </li>

            <!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item active">
                <a class="nav-link collapsed" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true"
                    aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-file-alt"></i>
                    <span>회원 관리</span>
                </a>
                <div id="collapseTwo" class="collapse show" aria-labelledby="headingTwo"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <a class="collapse-item active" href="/apa/admin/user/general/generalmain.do">일반 회원</a>
                        <a class="collapse-item" href="/apa/admin/user/hospital/list.do">병원 회원</a>
                        <a class="collapse-item" href="/apa/admin/user/pharmacy/list.do">약국 회원</a>
                    </div>
                </div>
            </li>
            
            <!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
                    aria-expanded="true" aria-controls="collapseUtilities">
                    <i class="fas fa-fw fa-file-alt"></i>
                    <span>입점 관리</span>
                </a>
                <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <a class="collapse-item" href="/apa/admin/company/entry/hospitallist.do">병원 회원</a>
                        <a class="collapse-item" href="/apa/admin/company/entry/pharmacylist.do">약국 회원</a>
                    </div>
                </div>
            </li>

            <!-- Nav Item - Charts -->
            <li class="nav-item">
                <a class="nav-link" href="/apa/admin/company/after/list.do">
                    <i class="fas fa-fw fa-hospital-alt"></i>
                    <span>병원 사후관리</span></a>
            </li>
            
            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages" aria-expanded="true"
                    aria-controls="collapsePages">
                    <i class="fas fa-fw fa-folder"></i>
                    <span>게시글 관리</span>
                </a>
                <div id="collapsePages" class="collapse" aria-labelledby="headingPages"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <a class="collapse-item" href="/apa/admin/advice/list.do">게시글</a>
                        <a class="collapse-item" href="/apa/admin/community/list.do">커뮤니티</a>
                    </div>
                </div>
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
	                	<h1 class="h3 mb-0 text-gray-800 hansans" style="padding-top: 28px;">회원 관리</h1>
	                </div>
	                    
                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">


	                    
                        <div class="topbar-divider d-none d-sm-block"></div>
                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">관리자(${dto.adminId})님</span>
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
                                    <h5 class="m-0 font-weight-bold text-primary">일반 회원</h5>
                                </div>
                                
			<!-- Card Body -->
	
	        <div class="ustbl">
			<h3 class="semi-title">회원 정보</h3>
			<div class="btncontainer"><button id="btn"><a href="/apa/admin/user/general/list.do">상세보기</a></button></div>
				<table class=user>
					<tr>
						<th id="num">번호</th>
						<th id="id">아이디</th>
						<th id="username">회원명</th>
						<th id="ssn">주민등록번호</th>
						<th id="tel">연락처</th>
						<th id="address">주소</th>
					</tr>
					<c:forEach items="${userList}" var="dto" varStatus="status">
						<tr>
							<td>${status.count}</td>	<!-- 순번 -->
							<td>${dto.userId}</td>		<!-- 회원 아이디 -->
							<td>${dto.userName}</td>	<!-- 회원명 -->
							<td>${dto.userSSN}</td>		<!-- 주민등록번호 -->
							<td>${dto.userTel}</td>		<!-- 연락처 -->
							<td>${dto.userAddress}</td>	<!-- 주소 -->
						</tr>
					</c:forEach>
				</table>
		
				<h3 class="semi-title">블랙리스트</h3>
				<div class="btncontainer"><button id="btn"><a href="/apa/admin/user/general/black/list.do">상세보기</a></button></div>
				<table class=black>
					<tr>
						<th id="num">번호</th>
						<th id="id">아이디</th>
						<th id="username">회원명</th>
						<th id="reason">사유</th>
						<th id="date">블랙리스트 등록 날짜</th>
					</tr>
					<c:forEach items="${blackList}" var="dto" varStatus="status">
						<tr>
							<td>${status.count}</td>		<!-- 순번 -->
							<td>${dto.userId}</td>			<!-- 회원아이디 -->
							<td>${dto.userName}</td>		<!-- 회원명 -->
							<td>${dto.blacklistReason}</td>	<!-- 사유 -->
							<td>${dto.blacklistDate}</td>	<!-- 블랙리스트 등록 날짜 -->
						</tr>
					</c:forEach>
				</table>
				
				<h3 class="semi-title">신고 현황</h3>
				<div class="btncontainer"><button id="btn"><a href="/apa/admin/user/general/report/list.do">상세보기</a></button></div>
				<table class=report>
					<tr>
						<th id="num">번호</th>
						<th id="id">아이디</th>
						<th id="username">회원명</th>
						<th id="regdate">신고일자</th>
						<th id="type">신고유형</th>
						<th id="state">신고현황</th>
					</tr>
					<c:forEach items="${reporList}" var="dto" varStatus="status">
						<tr>
							<td>${status.count}</td>		<!-- 순번 -->
							<td>${dto.userId}</td>			<!-- 아이디 -->
							<td>${dto.userName}</td>		<!-- 회원명 -->
							<td>${dto.regdate}</td>			<!-- 신고일자 -->
							<td>${dto.reportType}</td>		<!-- 신고유형 -->
							<td>${dto.isReportState}</td>	<!-- 신고현황 -->
						</tr>
					</c:forEach>
				</table>
			</div>			
            
            <!-- End of Main Content -->
			<%@ include file="/WEB-INF/views/inc/endofmaincontent.jsp" %>
	
	<script>
		
	</script>
</body>
</html>