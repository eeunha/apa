<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/assetmypage.jsp" %>
<style>

	input {
		border: 1px solid #c6c6c6;
	    text-align: center;
	    border-radius: 5px;
	    outline-color: #1cc88a;
	}

	#message {
		width: 250px;
		border: 0;
		text-align: left;
		font-weight: bold;
		margin-left: 10px;
		background-color: white;
	}

	#id {
		cursor: not-allowed;
		background-color: #d3d3d3;
	}

	#ssn1 {
		cursor: not-allowed;
		background-color: #d3d3d3;
	}
	
	#ssn2 {
		cursor: not-allowed;	
		background-color: #d3d3d3;
	}
	#ssn3 {
		cursor: not-allowed;	
		background-color: #d3d3d3;
	}

	#address {
		width: 25em;
	}

	#my-info {
		display: flex;
		align-items: center;
    	margin-bottom: 30px;
    	justify-content: flex-start;
	}

	#my-info-name {
    	color: #666666;
		height: 400px;
		display: grid;
		font-weight: bold;
		align-items: center;
		margin-right: 100px;
	}

	.my-info-name-child {
		height: 20px;
	}

	.my-info-name-child:last-child {
		margin-bottom: 0px;
	}
	
	#my-info-input {
		height: 400px;
		display: grid;
		align-items: center;
	}

	.my-info-input-child {
	}

	#info-update-btn {
		text-align: center;
	}
	
	#update-btn {
		border: 0;
		width: 7em;
		height: 2em;
		color: white;
		font-size: 1em;
		border-radius: 0.35rem;
		background-color: #5BC1AC;
	}

	#delete-user {
		border: 0;
		color: #CCC;
		float: right;
		outline: none;
		font-size: small;
		background-color: white;
	}

	#delete-user:hover {
		text-decoration: none;
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
                <a class="nav-link" href="/apa/hospital/info/view.do?id=${dto.hospitalId}" style="">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>마이페이지</span></a>
            </li>			
			
            <!-- Divider -->
            <hr class="sidebar-divider">
            
            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item active">
                <a class="nav-link collapsed" href="/apa/hospital/info/view.do?id=${dto.hospitalId}">
                    <i class="fas fa-fw fa-user"></i>
                    <span>내 정보</span>
                </a>
            </li>
            
            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="/apa/hospital/opening/view.do?id=${dto.hospitalId}">
                    <i class="fas fa-fw fa-table"></i>
                    <span>운영 관리</span>
                </a>
            </li>

            <!-- Nav Item - Utilities Collapse Menu -->
            <li class="nav-item">
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
	                	<h1 class="h3 mb-0 text-gray-800 hansans" style="padding-top: 28px;">내 정보 관리</h1>
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
                                    <h5 class="m-0 font-weight-bold text-primary">내 정보</h5>
                                </div>
                                <!-- Card Body -->
                                <div class="card-body">
                                	<form method="POST" action="/apa/hospital/info/edit.do">
									<div id="my-info">
	                                    <div id="my-info-name">
	                              	    	<div class="my-info-name-child">이름</div>                              	   		
	                              	    	<div class="my-info-name-child">아이디</div>                              	   		
	                              	    	<div class="my-info-name-child">비밀번호</div>                              	   		
	                              	    	<div class="my-info-name-child">비밀번호 확인</div>                              	   		
	                              	    	<div class="my-info-name-child">사업자 등록 번호</div>                              	   		
	                              	    	<div class="my-info-name-child">연락처</div>                              	   		
	                              	    	<div class="my-info-name-child">이메일</div>                              	   		
	                              	    	<div class="my-info-name-child">주소</div>                              	   		
	                              	    	<div class="my-info-name-child">현재 비밀번호</div>                              	   		
	                                    </div>
	                                    <div id="my-info-input">
	                              	    	<div class="my-info-input-child"><input type="text" name="name" value="${dto.hospitalName}" id="name"></div>                              	   		
	                              	    	<div class="my-info-input-child"><input type="text" value="${dto.hospitalId}" id="id" disabled ></div>                              	   		
	                              	    	<div class="my-info-input-child"><input type="password" name="pw" value="${dto.hospitalPw}" id="pw"></div>                              	   		
	                              	    	<div class="my-info-input-child"><input type="password" name="pw-confirm" id="pw-confirm"><input type="text" id="message" disabled></div>                              	   		
	                              	    	<div class="my-info-input-child">
	                              	    		<input type="text" value="${dto.hospitalSSNs}" size="3" id="ssn1" disabled> -
	                              	    		<input type="text" value="${dto.hospitalSSNm}" size="2" id="ssn2" disabled> -
	                              	    		<input type="text" value="${maskingSSN}" size="6" id="ssn3" disabled>
	                              	    	</div>                              	   		
	                              	    	<div class="my-info-input-child">
	                              	    		<input type="text" name="tel1" value="${dto.hospitalTels}" size="4"> - 
	                              	    		<input type="text" name="tel2" value="${dto.hospitalTelm}" size="4"> - 
	                              	    		<input type="text" name="tel3" value="${dto.hospitalTele}" size="4">
	                              	    	</div>                              	   		
	                              	    	<div class="my-info-input-child"><input type="text" name="email" value="${dto.hospitalEmail}" required></div>                              	   		
	                              	    	<div class="my-info-input-child"><input type="text" name="address" value="${dto.hospitalAddress}" id="address" required></div>                              	   		
	                              	    	<div class="my-info-input-child"><input type="password" name="inputCurrentPw" id="inputCurrentPw" required></div>                              	   		
	                                    </div>
									</div>
									<input type="hidden" name="id" value="${dto.hospitalId}" id="id">
									<input type="hidden" name="currentPw" value="${dto.hospitalPw}" id="currentPw">
									<input type="hidden" name="ssn1" value="${dto.hospitalSSNs}" size="7" id="ssn1">
									<input type="hidden" name="ssn2" value="${dto.hospitalSSNm}" size="7" id="ssn2">
	                             	<input type="hidden" name="ssn3" value="${maskingSSN}" size="9" id="ssn3">
									<input type="hidden" name="seq" value="${dto.hospitalId}">
	                                <div id="info-update-btn">
	                                	<button id="update-btn">수정하기</button>
	                                </div>
                                	</form>
	                                
	                                <form method="GET" action="/apa/hospital/info/edit.do" id="delete-form">
	                                	<button id="delete-user" type="button">회원 탈퇴</button>
										<input type="hidden" name="id" value="${dto.hospitalId}">
	                                </form>
                                </div>
                            </div>
                        </div>
                    </div>
            	</div>
            </div>
            
            <!-- End of Main Content -->
			<%@ include file="/WEB-INF/views/inc/endofmaincontent.jsp" %>
		
		
		<script>
			// HTML 요소를 찾아 변수에 저장합니다.
			const pw = document.getElementById('pw');
			const pwConfirm = document.getElementById('pw-confirm');
			const message = document.getElementById('message');
			const btn = document.getElementById('update-btn');
			const currentPw = document.getElementById('currentPw');
			
			// 비밀번호와 비밀번호 확인이 일치하는지 검사하는 함수를 만듭니다.
			function checkPassword() {
				
				if (pwConfirm.value.length == 0) {
			        message.value = '';
			        btn.type = "submit";			    	
			    } else if (pw.value != pwConfirm.value) {
			        message.value = '비밀번호가 일치하지 않습니다.';
			        message.style.color = "tomato";
			        btn.type = "button";
			    } else {
			        message.value = '비밀번호가 일치합니다.';
			        message.style.color = "#5BC1AC";
			        btn.type = "submit";
			    }
			}
	
			function changePassword() {
				
				if (pw.value != currentPw.value) {
						
					pwConfirm.setAttribute('required', '');
					
				} else if (pw.value == currentPw.value) {
					
					pwConfirm.removeAttribute('required');					
					
				}
				
			}
			
			// 이벤트 리스너를 추가하여 'pw-confirm'의 값이 변경될 때마다 함수를 실행합니다.
			pwConfirm.addEventListener('keyup', checkPassword);
			pw.addEventListener('keyup', changePassword);
			
			
			document.getElementById('delete-user').addEventListener('click', function() {
			    if(confirm('정말로 탈퇴하시겠습니까?')) {
			        // 사용자가 확인을 선택한 경우, 폼을 제출합니다.
			        document.getElementById('delete-form').submit();
			    } else {
			        // 사용자가 취소를 선택한 경우, 이전 페이지로 돌아갑니다.
			    }
			});
		</script>
</body>
</html>