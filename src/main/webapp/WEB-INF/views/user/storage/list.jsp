<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/assetmypage.jsp" %>
<style>

	.list {
		text-align: center;
	}
	
	.list tr th:first-child { border-top-left-radius: 10px; }
	.list tr th:last-child { border-top-right-radius: 10px; }
	.list tr:last-child td:first-child	{ border-bottom-left-radius: 10px; }
	.list tr:last-child td:last-child	{ border-bottom-right-radius: 10px; }
		
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

	#mlist > tbody > tr > th:nth-child(1) { width: 150px; }
	#mlist > tbody > tr > th:nth-child(2) { width: 250px; }
	#mlist > tbody > tr > th:nth-child(3) { width: 130px; }
	
	#qlist > tbody > tr > th:nth-child(1) { width: 150px; }
	#qlist > tbody > tr > th:nth-child(2) { width: 300px; }
	#qlist > tbody > tr > th:nth-child(3) { width: 200px; }
	#qlist > tbody > tr > th:nth-child(4) { width: 100px; }
	#qlist > tbody > tr > th:nth-child(5) { width: 100px; }

	#clist > tbody > tr > th:nth-child(1) { width: 250px; }
	#clist > tbody > tr > th:nth-child(2) { width: 200px; }
	#clist > tbody > tr > th:nth-child(3) { width: 100px; }
	#clist > tbody > tr > th:nth-child(4) { width: 100px; }
	#clist > tbody > tr > th:nth-child(5) { width: 100px; }
	#clist > tbody > tr > th:nth-child(6) { width: 100px; }
	#clist > tbody > tr > th:nth-child(7) { width: 70px; }
	
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
            <li class="nav-item">
                <a class="nav-link" href="/apa/user/favorite/list.do?seq=${dto.userSeq}">
                    <i class="fas fa-fw fa-hospital-alt"></i>
                    <span>즐겨찾기 병원</span></a>
            </li>

            <!-- Nav Item - Tables -->
            <li class="nav-item active">
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
	                	<h1 class="h3 mb-0 text-gray-800 hansans" style="padding-top: 28px;">보관함</h1>
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
                                    <h5 class="m-0 font-weight-bold text-primary">의학테스트 결과 보관함</h5>
                                </div>
                                <!-- Card Body -->
                                <div class="card-body">
									
									<table id="mlist" class="list">
										<tr>
											<th>테스트명</th>
											<th>테스트 날짜</th>
											<th>결과</th>
										</tr>
										<c:forEach items="${mlist}" var="mdto">
										<tr>
											<td>${mdto.mediTestName}</td>
											<td>${mdto.testTime}</td>
											<td><button onclick="viewDetail(${mdto.mediTestSaveSeq})" class="btn">결과 확인</button></td>
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
                                    <h5 class="m-0 font-weight-bold text-primary">의학 상담 보관함</h5>
                                </div>
                                <!-- Card Body -->
                                <div class="card-body">
                                <h5 class="m-0 font-weight-bold text-primary" style="padding-bottom: 20px;">내 글 보관함</h5>
									<table id="qlist" class="list">
										<tr>
											<th>진료과목</th>
											<th>제목</th>
											<th>등록 날짜</th>
											<th>상세 보기</th>
											<th>삭제</th>
										</tr>
										<c:forEach items="${qlist}" var="qdto">
										<tr>
											<td>${qdto.departmentName}</td>
											<td>${qdto.counselTitle}</td>
											<td>${qdto.regdate}</td>
											<td><button onclick="viewDetailQ(${qdto.mediCounselQuestionSeq})" class="btn">상세 보기</button></td>
											<td><button onclick="delQ(${qdto.mediCounselQuestionSeq},${qdto.mediCounselAnswerSeq})" class="btn">삭제</button></td>
										</tr>
										</c:forEach>
									</table>								
    	                            <h5 class="m-0 font-weight-bold text-primary" style="padding-top: 20px;padding-bottom: 20px;">타인 글 보관함</h5>
									<table id="qlist" class="list">
										<tr>
											<th>진료과목</th>
											<th>제목</th>
											<th>등록 날짜</th>
											<th>상세 보기</th>
											<th>삭제</th>
										</tr>
										<c:forEach items="${blist}" var="bdto">
										<tr>
											<td>${bdto.departmentName}</td>
											<td>${bdto.counselTitle}</td>
											<td>${bdto.regdate}</td>
											<td><button onclick="viewDetailQ(${bdto.mediCounselQuestionSeq})" class="btn">상세 보기</button></td>
											<td><button onclick="delB(${bdto.mediCounselBoxSeq})" class="btn">삭제</button></td>
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
                                    <h5 class="m-0 font-weight-bold text-primary">커뮤니티 보관함</h5>
                                </div>
                                <!-- Card Body -->
                                <div class="card-body">
									<table id="clist" class="list">
										<tr>
											<th>제목</th>
											<th>날짜</th>
											<th>댓글 수</th>
											<th>좋아요 수</th>
											<th>상세 보기</th>
											<th>삭제</th>
										</tr>
										<c:forEach items="${clist}" var="cdto">
										<tr>
											<td>${cdto.communityTitle}</td>
											<td>${cdto.communityDate}</td>
											<td>${cdto.communityCommentCount}</td>
											<td>${cdto.communityLikeCount}</td>
											<td><button onclick="viewDetailC(${cdto.communitySeq})" class="btn">상세 보기</button></td>
											<td><button onclick="delC(${cdto.communitySeq})" class="btn">삭제</button></td>
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

			function viewDetail(seq) {
		        var winHeight = 1000;
		        var winWidth = 800;
		        var winTop = (screen.height / 2) - (winHeight / 2);
		        var winLeft = (screen.width / 2) - (winWidth / 2);
		        window.open('/apa/user/storage/selftest/view.do?seq=' + seq, '테스트 결과', 'height=' + winHeight + ',width=' + winWidth + ',top=' + winTop + ',left=' + winLeft);
			}

			function viewDetailQ(seq) {
		        var winHeight = 1000;
		        var winWidth = 800;
		        var winTop = (screen.height / 2) - (winHeight / 2);
		        var winLeft = (screen.width / 2) - (winWidth / 2);
		        window.open('/apa/user/storage/advice/view.do?seq=' + seq, '의학 상담', 'height=' + winHeight + ',width=' + winWidth + ',top=' + winTop + ',left=' + winLeft);
			}

			function viewDetailC(seq) {
		        var winHeight = 1000;
		        var winWidth = 800;
		        var winTop = (screen.height / 2) - (winHeight / 2);
		        var winLeft = (screen.width / 2) - (winWidth / 2);
		        var newWin = window.open('/apa/user/storage/community/view.do?seq=' + seq, '커뮤니티', 'height=' + winHeight + ',width=' + winWidth + ',top=' + winTop + ',left=' + winLeft);

		        var timer = setInterval(function() { 
		            if(newWin.closed) {
		                clearInterval(timer);
		                location.reload();   // 부모 창 새로고침
		            }
		        }, 500);
			}
			
			function delQ(qseq,aseq) {
				
				if (confirm('정말로 삭제하시겠습니까?')) {
					
					$.ajax({
						type:'POST',
						url: '/apa/user/storage/advice/del.do',
						data: {
							qseq: qseq,
							aseq: aseq
						},
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
			
			function delB(bseq) {
				
				if (confirm('정말로 삭제하시겠습니까?')) {
					
					$.ajax({
						type:'POST',
						url: '/apa/user/storage/advice/del.do',
						data: {
							bseq: bseq
						},
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

			function delC(cseq) {
				
				if (confirm('정말로 삭제하시겠습니까?')) {
					
					$.ajax({
						type:'POST',
						url: '/apa/user/storage/community/del.do',
						data: {
							cseq: cseq
						},
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