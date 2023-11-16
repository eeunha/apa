<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
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

	#pharmacyId {
		cursor: not-allowed;
		background-color: #d3d3d3;
	}

	#pharmacySSNs {
		cursor: not-allowed;
		background-color: #d3d3d3;
	}
	
	#pharmacySSNm{
		cursor: not-allowed;	
		background-color: #d3d3d3;
	}
	#pharmacySSNe{
		cursor: not-allowed;	
		background-color: #d3d3d3;
	}

	#address {
		width: 25em;
	}

	#my-info {
		display: flex;
		align-items: center;
    	justify-content: flex-start;
    	margin-bottom: 30px;
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
	<%@ include file="/WEB-INF/views/inc/pharmacyslidebar.jsp" %>
      
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
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

					<!-- Sidebar Toggle (Topbar) -->
					<button id="sidebarToggleTop"
						class="btn btn-link d-md-none rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>

					<!-- Page Heading -->
					<div class="col-auto">
						<i class="fas fa-hospital-user fa-2x text-navy-300"></i>
					</div>
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800 hansans"
							style="padding-top: 28px;">내 정보</h1>
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
					<div class="row"></div>

					<!-- Content Row -->

					<div class="row">

						<!-- Area Chart -->
						<div class="col-xl-12">
							<div class="card shadow mb-5">
								<!-- Card Header - Dropdown -->
								<div
									class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
									<h6 class="m-0 font-weight-bold text-primary">내 정보</h6>
								</div>
								<!-- Card Body -->
								<div class="card-body">
									<form method="POST" action="/apa/pharmacy/info/edit.do">
										<div id="my-info">
											<div id="my-info-name">
												<div class="my-info-name-child">이름</div>
												<div class="my-info-name-child">아이디</div>
												<div class="my-info-name-child">비밀번호</div>
												<div class="my-info-name-child">비밀번호 확인</div>
												<div class="my-info-name-child">사업자등록번호</div>
												<div class="my-info-name-child">연락처</div>
												<div class="my-info-name-child">이메일</div>
												<div class="my-info-name-child">주소</div>
												<div class="my-info-name-child">현재 비밀번호</div>
											</div>
											<div id="my-info-input">
												<div class="my-info-input-child">
													<input type="text" name="name" id="name"
														value="${dto.pharmacyName}">
												</div>
												<div class="my-info-input-child">
													<input type="text" name="id" value="${dto.pharmacyId}"
														id="id" disabled>
												</div>
												<div class="my-info-input-child">
													<input type="password" name="pw" value="${dto.pharmacyPw}" id="pw">
												</div>
												<div class="my-info-input-child"><input type="password" name="pw-confirm" id="pw-confirm"><div id="message" disabled></div></div>  
												<div class="my-info-input-child">
													<input type="text" value="${dto.pharmacySSNs}" size="3" id="pharmacySSNs" disabled> -
													<input type="text" value="${dto.pharmacySSNm}" size="2" id="pharmacySSNm" disabled> -
													<input type="text" value="${maskingSSN}"size="9" id="pharmacySSNe" disabled >
														 
												</div>
												<div class="my-info-input-child">
													<input type="text" name="tel1" value="${dto.pharmacyTels}" size="4"> -
													<input type="text" name="tel2" value="${dto.pharmacyTelm}" size="4"> -
													<input type="text" name="tel3" value="${dto.pharmacyTele}" size="4">
												</div>
												<div class="my-info-input-child">
													<input type="text" name="email"
														value="${dto.pharmacyEmail}" required>
												</div>
												<div class="my-info-input-child">
													<input type="text" name="address" id="address"
														value="${dto.pharmacyAddress}" required>
												</div>
												<div class="my-info-input-child">
													<input type="password" name="inputCurrentPw"
														id="inputCurrentPw" required>
												</div>
											</div>
										</div>
										<input type="hidden" name="id" value="${dto.pharmacyId}" id="id">
										<input type="hidden" name="currentPw" value="${dto.pharmacyPw}" id="currentPw">
										<input type="hidden" name="ssn1" value="${dto.pharmacySSNs}" size="3" id="ssn1">
										<input type="hidden" name="ssn2" value="${dto.pharmacySSNm}" size="2" id="ssn2">
										<input type="hidden" name="ssn3" value="${maskingSSN}" size="9"	id="ssn3">
									
										<div id="info-update-btn">
											<button id="update-btn">수정하기</button>
										</div>
									</form>
									<form method="GET" action="/apa/pharmacy/info/edit.do" id="delete-form">
										<button id="delete-user" type="button">회원 탈퇴</button>
										<input type="hidden" name="id" value="${dto.pharmacyId}">
									</form>
								</div>
							</div>
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

                <!-- /.container-fluid -->

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
		
		const pw = document.getElementById('pw');
		const pwConfirm = document.getElementById('pw-confirm');
		const message = document.getElementById('message');
		const btn = document.getElementById('update-btn');
		const currentPw = document.getElementById('currentPw');

		
		 function checkPassword() {
			
        if (pwConfirm.value.length == 0) {
        	console.log('비밀번호 확인이 비어 있음');
            message.innerText = '';
            btn.type = "submit";
        } else if (pw.value != pwConfirm.value) {
        	console.log('비밀번호가 일치하지 않음');
            message.innerText = '비밀번호가 일치하지 않습니다.';
            message.style.color = "tomato";
            btn.type = "button";
        } else {
        	console.log('비밀번호가 일치함');
            message.innerText = '비밀번호가 일치합니다.';
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

		
		pwConfirm.addEventListener('keyup', checkPassword);
		pw.addEventListener('keyup', changePassword);
		
	
		document.getElementById('delete-user').addEventListener('click', function() {
		    if (confirm('정말로 탈퇴하시겠습니까?')) {
		      
		        document.getElementById('delete-form').submit();
		    } else {
		       
		    }
		});
	</script>
</body>
</html>