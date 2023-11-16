<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
    	<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">
    	
        <%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<style>

.icon {
	max-width: 70%;
}

#middle-content-textbox1 {
	height: 550px;
	overflow: hidden;
}

#middle-content-textbox2 {
	height: 320px;
}

#middle-content-textbox3 {
	height: 320px;
}

.middle-content {
	width: auto;
}

</style>
    </head>
    <body id="section_1">
        <%@ include file="/WEB-INF/views/inc/header.jsp" %>
        <main>

            <section class="hero-section hero-section-full-height">
                <div class="container-fluid">
                    <div class="row">

                        <div class="col-lg-12 col-12 p-0">
                            <div id="hero-slide" class="carousel carousel-fade slide" data-bs-ride="carousel">
                                <div class="carousel-inner">
                                    <div class="carousel-item active">
                                        <img src="/apa/asset/images/slide/main-image1.jpg" class="carousel-image img-fluid" alt="...">
                                        
                                        <div class="carousel-caption d-flex flex-column justify-content-end">
                                            <h1 class="hansans">병원/약국 찾기</h1>
                                            
                                            <p>최적의 병원을 예약해보세요.</p>
                                        </div>
                                    </div>

                                    <div class="carousel-item">
                                        <img src="/apa/asset/images/slide/main-image2.jpg" class="carousel-image img-fluid" alt="...">
                                        
                                        <div class="carousel-caption d-flex flex-column justify-content-end">
                                            <h1 class="hansans">의학 상담</h1>
                                            
                                            <p>의학 상담 바로가기 ></p>
                                        </div>
                                    </div>

                                    <div class="carousel-item">
                                        <img src="/apa/asset/images/slide/main-image3.jpg" class="carousel-image img-fluid" alt="...">
                                        
                                        <div class="carousel-caption d-flex flex-column justify-content-end">
                                            <h1 class="hansans">의학 정보</h1>
                                            
                                            <p>각종 질병이 궁금하다면?</p>
                                        </div>
                                    </div>
                                </div>

                                <button class="carousel-control-prev" type="button" data-bs-target="#hero-slide" data-bs-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Previous</span>
                                </button>

                                <button class="carousel-control-next" type="button" data-bs-target="#hero-slide" data-bs-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Next</span>
                                </button>
                            </div>
                        </div>

                    </div>
                </div>
            </section>


            <section class="section-padding">
                <div class="container">
                    <div class="row">


                        <div class="col-lg-3 col-md-6 col-12 mb-4 mb-lg-0">
                            <div class="featured-block d-flex justify-content-center align-items-center">
                                <a href="/apa/find/list.do" class="d-block">
                                    <img src="/apa/asset/images/icons/hospital.png" class="featured-block-image img-fluid icon" alt="">

                                    <p class="featured-block-text"><strong class="hansans">병원/약국 찾기</strong></p>
                                </a>
                            </div>
                        </div>

                        <div class="col-lg-3 col-md-6 col-12 mb-4 mb-lg-0 mb-md-4">
                            <div class="featured-block d-flex justify-content-center align-items-center">
                                <a href="/apa/advice/list.do" class="d-block">
                                    <img src="/apa/asset/images/icons/doctor.png" class="featured-block-image img-fluid icon" alt="">

                                    <p class="featured-block-text"><strong class="hansans">의학 상담</strong></p>
                                </a>
                            </div>
                        </div>

                        <div class="col-lg-3 col-md-6 col-12 mb-4 mb-lg-0 mb-md-4">
                            <div class="featured-block d-flex justify-content-center align-items-center">
                                <a href="/apa/magazine/list.do" class="d-block">
                                    <img src="/apa/asset/images/icons/knowledge.png" class="featured-block-image img-fluid icon" alt="">

                                    <p class="featured-block-text"><strong class="hansans">의학 정보</strong></p>
                                </a>
                            </div>
                        </div>

                        <div class="col-lg-3 col-md-6 col-12 mb-4 mb-lg-0">
                            <div class="featured-block d-flex justify-content-center align-items-center">
                                <a href="/apa/community/list.do" class="d-block">
                                    <img src="/apa/asset/images/icons/community.png" class="featured-block-image img-fluid icon" alt="">

                                    <p class="featured-block-text"><strong class="hansans">커뮤니티</strong></p>
                                </a>
                            </div>
                        </div>

                    </div>
                </div>
            </section>

            <section class="section-padding section-bg" id="section_2">
                <div class="container">
                    <div class="row">

                        <div class="col-lg-6 col-12 mb-5 mb-lg-0">
                            <img src="/apa/asset/images/middle-image.png" class="custom-text-box-image img-fluid" alt="">
                        </div>

                        <div class="col-lg-6 col-12">
                            <div class="custom-text-box" id="middle-content-textbox1">
                                <h2 class="mb-2, hansans">독감 예방접종 기피하나요?</h2>

                                <h5 class="mb-3">아동·청소년 중심 인플루엔자 본격 유행…예방접종 서둘러야</h5>

                                <p class="mb-0">11일 질병관리청에 따르면 호흡기감염병 의원급 표본감시 결과<br> 10월 4주차(10월 22일~28일) 외래환자 1000명당 인플루엔자 의사환자(38℃ 이상 갑작스런 발열과 기침 또는 인후통이 있는 경우) 분율은 32.6명으로 집계됐다.<br> 이는 10월 3주차(15일~21일) 18.8명 대비 73% 증가한 수치다.<br><br> 특히 인플루엔자 유행기준인 외래환자 1000명당 의사환자 6.5명보다 훨씬 높은 수치다.0

연령별로 아동·청소년에서 인플루엔자 증가세가 뚜렷하다.<br>10월 4주차 기준 인플루엔자 의사환자 발생은 7∼12세(86.9명), 13∼18세(67.5명), 19∼49세(30.3명) 순으로 나타났다. 또 인플루엔자 입원환자와 중증급성호흡기감염증 입원환자 중에서도 큰 증가세를 보이고 있어 주의가 필요한 상황이다.<br><br>

특히 질병청에 따르면 어린이(6개월~13세)의 경우 백신 접종률이 11월 2일 기준 47.5%로, 이는 전년 같은 기간 51.8% 대비 접종률이 낮다.<br> 지영미 질병관리청장은 “현재 인플루엔자 유행은 과거 겨울철 유행의 증가세가 지금 시작되고 있는 양상으로 9월말부터 시행중인 인플루엔자 국가예방접종에 적극적으로 참여해 줄 것”을 당부했다.</p>
                            </div>
                            
                            <div class="row">
                                <div class="col-lg-6 col-md-6 col-12">
                                    <div class="custom-text-box mb-lg-0" id="middle-content-textbox2">
                                        <h5 class="mb-3">국가 검진 정보</h5>

                                        <p>2023년 국가건강검진 대상자는
                                        <br>홀수년도 출생자입니다.</p>

                                        <ul class="custom-list mt-2">
                                            <li class="custom-list-item d-flex">
                                                <i class="bi-check custom-text-box-icon me-2"></i>
                                                지역가입자
                                            </li>

                                            <li class="custom-list-item d-flex">
                                                <i class="bi-check custom-text-box-icon me-2"></i>
                                                직장피부양자
                                            </li>

                                            <li class="custom-list-item d-flex">
                                                <i class="bi-check custom-text-box-icon me-2"></i>
                                                직장가입자
                                            </li>

                                            <li class="custom-list-item d-flex">
                                                <i class="bi-check custom-text-box-icon me-2"></i>
                                                의료급여수급권자
                                            </li>
                                        </ul>
                                    </div>
                                </div>

                                <div class="col-lg-6 col-md-6 col-12">
                                    <div class="custom-text-box d-flex flex-wrap d-lg-block mb-lg-0" id="middle-content-textbox3">
										<h5 class="mb-3">2023년 예방 접종 통계</h5>
										<div id="barchart_material"></div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </section>

            <section class="section-padding" id="section_5">
                <div class="container">
                    <div class="row">

                        <div class="col-lg-12 col-12 text-center mb-4">
                            <h2 class="hansans">의학상담</h2>
                        </div>

                        <div class="col-lg-4 col-md-6 col-12 mb-4 mb-lg-0">
                            <div class="custom-block-wrap">
                                <div class="custom-block">
                                    <div class="custom-block-body middle-content">
                                        <h5 class="mb-3">움카민시럽과 코푸시럽의 차이...</h5>

                                        <p>움카민시럽과 코푸시럽 차이가 뭔가요? 기침과 가래가 있는 증상에 둘 다 효능이...</p>
										<hr>
                                        <div class="d-flex align-items-center my-2">
                                            <p class="mb-0">
                                                <strong>작성일자: </strong>
                                                2023-11-03
                                            </p>

                                            <p class="ms-auto mb-0">
                                                <strong>답변 유무: </strong>
                                                답변완료
                                            </p>
                                        </div>
                                    </div>

                                    <a href="donate.html" class="custom-btn btn">Click</a>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-4 col-md-6 col-12 mb-4 mb-lg-0">
                            <div class="custom-block-wrap">
                                <div class="custom-block">
                                    <div class="custom-block-body middle-content">
                                        <h5 class="mb-3">갑작스럽게 손가락과 팔꿈치에...</h5>

                                        <p>토요일아침에 일어났는데 갑자기 약지손가락이랑 새끼손가락이 쥐나면서 팔꿈치...</p>
										<hr>
                                        <div class="d-flex align-items-center my-2">
                                            <p class="mb-0">
                                                <strong>작성일자: </strong>
                                                2023-11-04
                                            </p>

                                            <p class="ms-auto mb-0">
                                                <strong>답변 유무: </strong>
                                                답변완료
                                            </p>
                                        </div>
                                    </div>

                                    <a href="donate.html" class="custom-btn btn">Click</a>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-4 col-md-6 col-12">
                            <div class="custom-block-wrap">
                                <div class="custom-block">
                                    <div class="custom-block-body middle-content">
                                        <h5 class="mb-3">맨발로 이물질을 밟아 발뒤꿈치가...</h5>

                                        <p>한시간전에 맨발로 이물질을 밟아서 발뒤꿈치가 아픈데 족저근막염으로 발전하...</p>
                                        <div>                                     
											<hr>
	                                        <div class="d-flex align-items-center my-2">
	                                            <p class="mb-0">
	                                                <strong>작성일자: </strong>
	                                                2023-11-05
	                                            </p>
	
	                                            <p class="ms-auto mb-0">
	                                                <strong>답변 유무: </strong>
	                                                답변완료
	                                            </p>
	                                        </div>
                                        </div>
                                    </div>

                                    <a href="donate.html" class="custom-btn btn">Click</a>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </section>

            <section class="news-section section-padding" id="section_3">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12 col-12 mb-5">
                            <h2 class="hansans">의학정보</h2>
                        </div>
                        <div class="col-lg-7 col-12">
                            <div class="news-block">
                                <div class="news-block-top">
                                    <a href="news-detail.html">
                                        <img src="/apa/asset/images/news/under-image.jpg" class="news-image img-fluid" alt="">
                                    </a>

                                    <div class="news-category-block">
                                        <a href="#" class="category-block-link">
                                            Reserve Your Health
                                        </a>
                                    </div>
                                </div>

                            </div>
                        </div>

                        <div class="col-lg-4 col-12 mx-auto">
                            <form class="custom-form search-form" action="#" method="get" role="form">
                                <input name="search" type="search" class="form-control" id="search" placeholder="Search" aria-label="Search">

                                <button type="submit" class="form-control">
                                    <i class="bi-search"></i>
                                </button>
                            </form>

                            <h5 class="mt-5 mb-3 hansans">News</h5>

                            <div class="news-block news-block-two-col d-flex mt-4">
                                <div class="news-block-two-col-image-wrap">
                                    <a href="news-detail.html">
                                        <img src="/apa/asset/images/news/news-image1.jpg" class="news-image img-fluid" alt="">
                                    </a>
                                </div>

                                <div class="news-block-two-col-info">
                                    <div class="news-block-title mb-2"><!-- megazineSeq = 58 -->
                                        <h6><a href="news-detail.html" class="news-block-title-link">체중 변화 심한 당뇨환...</a></h6>
                                    </div>

                                    <div class="news-block-date">
                                        <p>
                                            <i class="bi-calendar4 custom-icon me-1"></i>
                                            November 11, 2023
                                        </p>
                                    </div>
                                </div>
                            </div>

                            <div class="news-block news-block-two-col d-flex mt-4">
                                <div class="news-block-two-col-image-wrap">
                                    <a href="news-detail.html">
                                        <img src="/apa/asset/images/news/news-image2.jpg" class="news-image img-fluid" alt="">
                                    </a>
                                </div>

                                <div class="news-block-two-col-info">
                                    <div class="news-block-title mb-2"><!-- megazineSeq = 2 -->
                                        <h6><a href="news-detail.html" class="news-block-title-link">미세먼지 장기간 노출...</a></h6>
                                    </div>

                                    <div class="news-block-date">
                                        <p>
                                            <i class="bi-calendar4 custom-icon me-1"></i>
                                            October 24, 2023
                                        </p>
                                    </div>
                                </div>
                            </div>

                            <div class="category-block d-flex flex-column">
                                <h5 class="mb-3 hansans">Categories</h5>
								<a href="#" class="category-block-link">
                                    의학 상담
                                </a>
                                <a href="#" class="category-block-link">
                                    의학 매거진
                                </a>

                                <a href="#" class="category-block-link">
                                    질병 백과
                                </a>
                                <a href="#" class="category-block-link">
                                    커뮤니티
                                </a>
                            </div>
                        </div>

                    </div>
                </div>
            </section>
        </main>

        <%@ include file="/WEB-INF/views/inc/footer.jsp" %>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script>
        google.charts.load('current', {'packages':['bar']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {
          var data = google.visualization.arrayToDataTable([
            ['', '청년', '중장년', '노년'],
            ['2023', 200, 500, 800],
          ]);

          var options = {
            bars: 'vertical' // Required for Material Bar Charts.
          };

          var chart = new google.charts.Bar(document.getElementById('barchart_material'));

          chart.draw(data, google.charts.Bar.convertOptions(options));
        }
        </script>
    </body>
</html>