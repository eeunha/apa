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
		width: 900px;
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
	
	#list > tbody > tr > td:nth-child(3) > div {
		display: grid;
		justify-items: center;
	}
	
	#list > tbody > tr > td:nth-child(4) > div {
		display: flex;
		justify-content: space-evenly;
		margin-top: 5px;
	    margin-bottom: 5px;
	}

	#list > tbody > tr > td:nth-child(4) > div > i {
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
	.mypage-doctor-img {
		width: 100px;
		height: 100px;
	}
	.card-body {
		max-width: 1320px;
		margin-left: auto;
		margin-right: auto;
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
            <li class="nav-item ">
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
            <li class="nav-item active">
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
	                	<h1 class="h3 mb-0 text-gray-800 hansans" style="padding-top: 28px;">의사 정보</h1>
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
                                    <h5 class="m-0 font-weight-bold text-primary">의사 목록</h5>
                                </div>
                                <!-- Card Body -->
                                <div class="card-body">
									<table id="list">
										<thead>
											<tr>
												<th>이름</th>
												<th>진료과</th>
												<th>이미지</th>
												<th></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${list}" var="list">
											<tr>
												<td>${list.name}</td>
												<td>
													${list.deptname}
												</td>
												<td>
													<img src="/apa/asset/images/doc/${list.img}" class="mypage-doctor-img">
												</td>
												<td>
													<div>
														<i class="fas fa-fw fa-times hospital-hover-icon" onclick="del(${list.seq});"></i>
													</div>
												</td>
											</tr>
											</c:forEach>
										</tbody>
									</table>
									
									<div><button class="out" id="toggleAdd" type="button">의사 등록</button></div>
									
									<form id="addForm" method="POST" action="/apa/hospital/doctor/add.do" enctype="multipart/form-data">
									<table id="add">
										<tr>
											<th>이름</th>
											<td><input type="text" name="name" id="name"></td>
										</tr>
										<tr>
											<th>진료과</th>
											<td>
												<select name="dept" id="dept-select" >
													<option selected>선택</option>
										               <option value="1">산부인과</option>
										               <option value="2">이비인후과</option>
										               <option value="3">정형외과</option>
										               <option value="4">피부과</option>
										               <option value="5">내과</option>
										               <option value="6">안과</option>
										               <option value="7">치과</option>
										               <option value="8">한의원</option>
										               <option value="9">비뇨기과</option>
										               <option value="10">신경외과</option>
										               <option value="11">가정의학과</option>
										               <option value="12">외과</option>
										               <option value="13">정신의학과</option>
										               <option value="14">소아과</option>
										               <option value="15">신경과</option>
										               <option value="16">성형외과</option>
										               <option value="17">재활의학과</option>
										               <option value="18">마취통증의학과</option>
										               <option value="19">영상의학과</option>
										               <option value="20">응급의학과</option>
										               <option value="21">기타</option>
												</select>
											</td>
										</tr>
										<tr>
											<th>이미지 등록</th>
											<td>
												<input type="file" name="img">
											</td>
										</tr>
										<input type="hidden" name="id" value="${dto.hospitalId}">
									</table>
									<div id="btnAdd">
										<button class="add" type="submit">등록하기</button>
									</div>
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
			$('#toggleAdd').click(function() {
				
				//alert($('#add').css('display'));
				
				 
				if ($('#add').css('display') == 'none') {
					$('#add').css('display', 'table');
					$(this).removeClass('out');
					$(this).addClass('in');
					$(this).text('접기');
					$('#btnAdd').css('display', 'block');
					$("#name").focus();
				} else {
					$('#add').css('display', 'none');
					$(this).removeClass('in');
					$(this).addClass('out');
					$(this).text('자녀 등록');
					$('#btnAdd').css('display', 'none');
				} 
						
				
			});
			
			/* $('#btnAdd > button').click(function() {
				
				//alert($('#addForm').serialize()); //직렬화
				
				//ajax > 입력 데이터 전송 > insert
				$.ajax({
					type: 'POST',
					url: '/apa/hospital/doctor/add.do',
					data: $('#addForm').serialize(),
					dataType: 'json',
					success: function(result) {
						
						//alert(result.result);
						
						if (result.result == 1) {
							
							//목록 새로 고침
							location.reload();
							
							//입력 폼 초기화
							$("#name").val('');
							$("#ssn1").val('');
							$("#ssn2").val('');
							$("#tel1").val('');
							$("#tel2").val('');
							$("#tel3").val('');
							
						} else {
							alert('failed');
						}
						
					},
					error: function(a,b,c) {
						console.log(a,b,c);
					}
				});
				
			}); */
			
			
			
			function edit(id) {
				//클릭 > 수정 버튼(<span>) 
				
				let name = $(event.target).parents('tr').children().eq(0).text();
				let ssn = $(event.target).parents('tr').children().eq(1).text();
				let tel = $(event.target).parents('tr').children().eq(2).text();
				
				let ssnArr = ssn.split("-");
				ssnArr[0] = ssnArr[0].replace(/\s/g, "");
				ssnArr[1] = ssnArr[1].replace(/\s/g, "");

				let telArr = tel.split("-");
				telArr[0] = telArr[0].replace(/\s/g, "");
				telArr[1] = telArr[1].replace(/\s/g, "");
				telArr[2] = telArr[2].replace(/\s/g, "");
								
				
				$(event.target).parents('tr').children().eq(0).append(`<input type="text" value="\${name}">`);
				
				$(event.target).parents('tr').children().eq(1).children().append(`<input type="text" value="\${ssnArr[0]}"> - <input type="text" value="\${ssnArr[1]}">`);
				
				$(event.target).parents('tr').children().eq(2).children().append(`<input type="text" value="\${telArr[0]}"> - <input type="text" value="\${telArr[1]}"> - <input type="text" value="\${telArr[2]}">`);				
				
				$(event.target).parents('tr').children().eq(4).children().html(`<i class="fas fa-fw fa-edit" onclick="editOk(\${id});"></i>`);
			}
		
			
			function editOk(seq) {
				
				let name = $(event.target).parents('tr').children().children().eq(0).val();
				
				let ssn1 = $(event.target).parents('tr').children().children().eq(1).children().val();
				let ssn2 = $(event.target).parents('tr').children().children().eq(1).children().eq(1).val();
				let ssn = ssn1 + "-" + ssn2;
				
				let tel1 = $(event.target).parents('tr').children().children().eq(2).children().val();
				let tel2 = $(event.target).parents('tr').children().children().eq(2).children().eq(1).val();
				let tel3 = $(event.target).parents('tr').children().children().eq(2).children().eq(2).val();
				let tel = tel1 + "-" + tel2 + "-" + tel3;
				
				const temp = $(event.target);
				
				$.ajax({
					type: 'POST',
					url: '/apa/hospital/doctor/edit.do',
					data: {
						name: name,
						ssn: ssn,
						tel: tel,
						childSeq: seq,
						id: ${dto.hospitalId}
					},
					dataType: 'json',
					success: function(result) {
						
						if (result.result == 1) {

							location.reload();
						
						} else {
							alert('failed');
						}
						
					},
					error: function(a,b,c) {
						console.log(a,b,c);
					}
				});
				
			}
			
			function del(id) {
				
				if (confirm('정말로 삭제하시겠습니까?')) {
					
					$.ajax({
						type:'POST',
						url: '/apa/hospital/doctor/del.do',
						data: 'id=' + id,
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