<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<%@ include file="/WEB-INF/views/inc/asset.jsp"%>

<style>
body {
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-use-select: none;
	background-color: #f8f8f8;
}
body > main {
	
	margin-bottom: 50px;
}

tbody {
	max-width: 1320px;
	margin-left: auto;
	margin-right: auto;
}

#main-list {
	margin-top: 100px;
	/* padding: 100px 100px; */
	max-width: 1320px;
	margin-left: auto;
	margin-right: auto;
}

.hospital-info {
	width: auto;
	position: relative;
	flex-direction: column;
	word-wrap: break-word;
	background-color: #fff;
	background-clip: border-box;
	border: 1px solid #e3e6f0;
	border-radius: 0.35rem;
	padding: 10px 20px;
	margin-top: 100;
	max-width: 1320px;
	margin-left: auto;
	margin-right: auto;
}

#tags {
	display: grid;
	grid-template-columns: repeat(8, 1fr);
	text-align: center;
}

.tag {
	position: relative;
	flex-direction: column;
	min-width: 0;
	word-wrap: break-word;
	background-color: #fff;
	background-clip: border-box;
	border: 1px solid #e3e6f0;
	border-radius: 0.35rem;
	padding: 5px 0;
}

.tag:hover {
	cursor: pointer;
}

.info-text {
	margin-left: 30px;
	margin-bottom: 10px;
}

.collapse-header {
	margin-left: 20px;
	margin-top: 10px;
}

.clicked {
	background-color: #f0f0f0;
}

.hospital-info-contentlist {
	margin-left: 10px;
}

.hospital-info-content {
	text-align: center;
	width: 100px;
	height: 35px;
	border: 1px solid #fff;
	border-radius: 0.6rem;
	background-color: #f0f0f0;
	padding: 3px 10px;
	margin-left: 5px;
	margin-top: 7.5px;
}

.hospital-info-content-color {
	background-color: #beefe5;
}

.reservation-button {
	width: 150px;
	height: 50px;
	border: 1px solid #fff;
	border-radius: 0.6rem;
	background-color: #f0f0f0;
}

.symtomlist-div {
	margin-top: 10px;
	width: auto;
	text-align: center;
}

.symtomlist-button {
	width: 200px;
	height: 40px;
	background-color: #fff;
	background-clip: border-box;
	border: 1px solid #e3e6f0;
	border-radius: 0.35rem;
	padding: 5px 0;
}

.symtomlist-hide {
	display: none;
}

.hospital-list {
	width: auto;
}

.hospital-info-contentlist div {
	display: inline-flex;
}

.hospital-info-contentlist a:nth-child(2) {
	display: flex;
	justify-content: space-between;
	float: right;
}

table {
	width: 1320px;
	max-width: 1320px;
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>
<body id="section_1">
	<%@ include file="/WEB-INF/views/inc/header.jsp"%>
	<main>
		<div id="main-list">
			<div id="tags">
				<c:forEach items="${symtomlist}" var="dto">
					<c:if test="${dto.seq+0 >= 25}">
						<div id="tag${dto.seq}" class="tag symtomlist-hide"
							" onclick="selSystom(${dto.seq})">${dto.name}</div>
					</c:if>
					<c:if test="${dto.seq+0 <= 24}">
						<div id="tag${dto.seq}" class="tag"
							onclick="selSystom(${dto.seq})">${dto.name}</div>
					</c:if>
				</c:forEach>
			</div>
			<div class="symtomlist-div">
				<input type="button" class="symtomlist-button" value="증상 더보기">
			</div>
			<hr>
			<table id="hospital-list">
				<tbody>
					<c:forEach items="${hosdto}" var="hosdto">
						<tr>
							<td><a href="/apa/find/view.do?id=${hosdto.id}">
									<div class="hospital-info">
										<h6 class="collapse-header">${hosdto.name}</h6>
										<p class="info-text">${hosdto.deptname}</p>
										<p class="info-text">${hosdto.address}</p>
							</a>
								<div class="hospital-info-contentlist">
									<a href="/apa/find/view.do?id=${hosdto.id}">
										<div>
											<c:if test="${hosdto.face == 'y' or hosdto.face == 'Y'}">
												<p class="hospital-info-content hospital-info-content-color">대면</p>
											</c:if>
											<c:if test="${hosdto.face == 'n' or hosdto.face == 'N'}">
												<p class="hospital-info-content">대면</p>
											</c:if>
											<c:if test="${hosdto.unface == 'y' or hosdto.unface == 'Y'}">
												<p class="hospital-info-content hospital-info-content-color">비대면</p>
											</c:if>
											<c:if test="${hosdto.unface == 'n' or hosdto.unface == 'N'}">
												<p class="hospital-info-content">비대면</p>
											</c:if>
											<c:if test="${hosdto.call == 'y' or hosdto.call == 'Y'}">
												<p class="hospital-info-content hospital-info-content-color">왕진</p>
											</c:if>
											<c:if test="${hosdto.call == 'n' or hosdto.call == 'N'}">
												<p class="hospital-info-content">왕진</p>
											</c:if>
											<c:if test="${hosdto.check == 'y' or hosdto.check == 'Y'}">
												<p class="hospital-info-content hospital-info-content-color">건강검진</p>
											</c:if>
											<c:if test="${hosdto.check == 'n' or hosdto.check == 'N'}">
												<p class="hospital-info-content">건강검진</p>
											</c:if>
											<c:if test="${hosdto.vaccin == 'y' or hosdto.vaccin == 'Y'}">
												<p class="hospital-info-content hospital-info-content-color">예방접종</p>
											</c:if>
											<c:if test="${hosdto.vaccin == 'n' or hosdto.vaccin == 'N'}">
												<p class="hospital-info-content">예방접종</p>
											</c:if>
											<c:if test="${hosdto.night == 'y' or hosdto.night == 'Y'}">
												<p class="hospital-info-content hospital-info-content-color">야간진료</p>
											</c:if>
											<c:if test="${hosdto.night == 'n' or hosdto.night == 'N'}">
												<p class="hospital-info-content">야간진료</p>
											</c:if>
											<c:if
												test="${hosdto.holiday == 'y' or hosdto.holiday == 'Y'}">
												<p class="hospital-info-content hospital-info-content-color">주말진료</p>
											</c:if>
											<c:if
												test="${hosdto.holiday == 'n' or hosdto.holiday == 'N'}">
												<p class="hospital-info-content">주말진료</p>
											</c:if>
										</div>
									</a> 
									<c:if test="${lv == '1'}">
														<a href="/apa/reservation/select.do?id=${hosdto.id}">
								    							<button class="reservation-button"> 예약하기 </button>
						    							</a>
									</c:if>
									<c:if test="${lv == '' || lv == null}">
						    							<a href="/apa/user/login.do">
								    							<button class="reservation-button"> 예약하기 </button>
						    							</a>
									</c:if>
								</div>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</main>

	<%@ include file="/WEB-INF/views/inc/footer.jsp"%>
	<script>
			var lists = [];
			var seqlist = [1,0,0,0,0,0,0,0];
			var count = 0;
			var onoff = false;
			/* load(seqlist); */
        	function selSystom(seq) {
        		var tag = document.getElementsByClassName("tag");
        		if (event.target.classList[1] === "clicked" || event.target.classList[2] === "clicked") {
        	          event.target.classList.remove("clicked");
        	          count--;
        	    } else {
        	    	if (count >= 8) {
	        	        	count = 0;
	        	        	lists[count].classList.remove("clicked");
        	    			lists[count] = event.target;
        	    			seqlist[count] = seq;
	        	        	lists[count].classList.add("clicked");
	        	        	onoff = true;
        	    	} else {
        	    		if (onoff) {          	    			
	        	        	lists[count].classList.remove("clicked");      	    			
	        	        	lists[count] = event.target;
        	    			seqlist[count] = seq;
		        	    	lists[count].classList.add("clicked");
        	    		} else {
        	    			lists[count] = event.target;
        	    			seqlist[count] = seq;
		        	    	lists[count].classList.add("clicked");
        	    		}
        	    	}
        	    }
    	    	load(seqlist);
        		count++;
			}
        	function load(seqlist) {
        		$.ajax({
    				type: 'GET',
    				url: '/apa/find/find.do',
    				data: {
    					seq1 : seqlist[0],
    					seq2 : seqlist[1],
    					seq3 : seqlist[2],
    					seq4 : seqlist[3],
    					seq5 : seqlist[4],
    					seq6 : seqlist[5],
    					seq7 : seqlist[6],
    					seq8 : seqlist[7]
    				},
    				dataType: 'json',
    				success: function(result) {
    					$('#hospital-list tbody').html('');
    					$(result).each((index, item) => {
    						let temp = `
    								<tr>
    									<td>
		    								<a href="/apa/find/view.do?id=\${item.id}">
		    								<div class="hospital-info">
			    								<h6 class="collapse-header">\${item.name}</h6>
			    				                <p class="info-text">\${item.deptname}</p>
			    				                <p class="info-text">\${item.address}</p>
						    				</a>
			    				                <div class="hospital-info-contentlist">		
				    								<a href="/apa/find/view.do?id=\${item.id}">
				    				                	<div>
				    				                	`;
				    				                	if (item.face == 'y' || item.face == 'Y'){
				    				                		temp+=`
								    							<p class="hospital-info-content hospital-info-content-color">대면</p>
								    							`;
				    				                	}
				    				                	if (item.face == 'n' || item.face == 'N'){
				    				                		temp+=`
								    							<p class="hospital-info-content">대면</p>
								    							`;
				    				                	}
				    				                	if (item.unface == 'y' || item.unface == 'Y'){
				    				                		temp+=`
								    							<p class="hospital-info-content hospital-info-content-color">비대면</p>
								    							`;
				    				                	}
				    				                	if (item.unface == 'n' || item.unface == 'N'){
				    				                		temp+=`
								    							<p class="hospital-info-content">비대면</p>
								    							`;
				    				                	}
				    				                	if (item.call == 'y' || item.call == 'Y'){
				    				                		temp+=`
								    							<p class="hospital-info-content hospital-info-content-color">왕진</p>
								    							`;
				    				                	}
				    				                	if (item.call == 'n' || item.call == 'N'){
				    				                		temp+=`
								    							<p class="hospital-info-content">왕진</p>
								    							`;
				    				                	}
				    				                	if (item.check == 'y' || item.check == 'Y'){
				    				                		temp+=`
								    							<p class="hospital-info-content hospital-info-content-color">건강검진</p>
								    							`;
				    				                	}
				    				                	if (item.check == 'n' || item.check == 'N'){
				    				                		temp+=`
								    							<p class="hospital-info-content">건강검진</p>
								    							`;
				    				                	}
				    				                	if (item.vaccin == 'y' || item.vaccin == 'Y'){
				    				                		temp+=`
								    							<p class="hospital-info-content hospital-info-content-color">예방접종</p>
								    							`;
				    				                	}
				    				                	if (item.vaccin == 'n' || item.vaccin == 'N'){
				    				                		temp+=`
								    							<p class="hospital-info-content">예방접종</p>
								    							`;
				    				                	}
				    				                	if (item.night == 'y' || item.night == 'Y'){
				    				                		temp+=`
								    							<p class="hospital-info-content hospital-info-content-color">야간진료</p>
								    							`;
				    				                	}
				    				                	if (item.night == 'n' || item.night == 'N'){
				    				                		temp+=`
								    							<p class="hospital-info-content">야간진료</p>
								    							`;
				    				                	}
				    				                	if (item.holiday == 'y' || item.holiday == 'Y'){
				    				                		temp+=`
								    							<p class="hospital-info-content hospital-info-content-color">휴일진료</p>
								    							`;
				    				                	}
				    				                	if (item.holiday == 'n' || item.holiday == 'N'){
				    				                		temp+=`
								    							<p class="hospital-info-content">휴일진료</p>
								    							`;
				    				                	}
						    							temp+=`
						    							</div>
						    						</a>
						    						`;
						    						if ('${lv}' == '1'){
						    						temp += `
						    							<a href="/apa/reservation/select.do?id=\${item.id}">
								    							<button class="reservation-button"> 예약하기 </button>
						    							</a>	
						    							`;
						    						} else if ('${lv}' == '' || '${lv}' == null){
						    						temp += `
						    							<a href="/apa/user/login.do">
								    							<button class="reservation-button"> 예약하기 </button>
						    							</a>
						    							`;
						    						}
						    							temp+= `
			    				                </div>
			    							</div>
    									</td>
    								</tr>
    								`;
						    	    $('#hospital-list tbody').append(temp);							
    					});
    				},
    				error: function(a,b,c) {
    					console.log(a,b,c);
    				}
    			});
			}
        	
        	$(".symtomlist-button").click(function() {
    			if ($(".symtomlist-hide").is(":visible")){
    				$(".symtomlist-hide").slideUp();
    				$(".symtomlist-button").val("증상 더보기");    				
    			} else {
    				$(".symtomlist-hide").slideDown();
    				$(".symtomlist-button").val("증상 숨기기");    				
    				$(".tag").css('display','inline');
    			}
    		});
        </script>

</body>
</html>