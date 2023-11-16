<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="/WEB-INF/views/inc/asset.jsp" %>
    <style>
		.icon {
			width: 70%;
		}
    </style>
</head>
<body>
<!-- /selftest/list.jsp -->
<%@ include file="/WEB-INF/views/inc/header.jsp" %>

<section class="section-padding">
    <div class="container">
        <div class="row">
            <div class="col-lg-10 col-12 text-center mx-auto">
                <h2 class="mb-5, hansans">의학 테스트</h2>
            </div>

            <div class="col-lg-3 col-md-6 col-12 mb-4 mb-lg-0">
                <div class="featured-block d-flex justify-content-center align-items-center">
                    <a href="/apa/selftest/test.do?seq=1" class="d-block">
                    	<img src="/apa/asset/images/icons/stress.png" class="featured-block-image img-fluid icon" alt="">
                    
                        <p class="featured-block-text"><strong class="hansans">스트레스 테스트</strong></p>
                    </a>
                </div>
            </div>

            <div class="col-lg-3 col-md-6 col-12 mb-4 mb-lg-0 mb-md-4">
                <div class="featured-block d-flex justify-content-center align-items-center">
                    <a href="/apa/selftest/test.do?seq=2" class="d-block">
                    	<img src="/apa/asset/images/icons/anxiety.png" class="featured-block-image img-fluid icon" alt="">

                        <p class="featured-block-text"><strong class="hansans">불안 테스트</strong></p>
                    </a>
                </div>
            </div>

            <div class="col-lg-3 col-md-6 col-12 mb-4 mb-lg-0 mb-md-4">
                <div class="featured-block d-flex justify-content-center align-items-center">
                    <a href="/apa/selftest/test.do?seq=3" class="d-block">
						<img src="/apa/asset/images/icons/depression.png" class="featured-block-image img-fluid icon" alt="">	
	
                        <p class="featured-block-text"><strong class="hansans">우울증 테스트</strong></p>
                    </a>
                </div>
            </div>

            <div class="col-lg-3 col-md-6 col-12 mb-4 mb-lg-0">
                <div class="featured-block d-flex justify-content-center align-items-center">
                    <a href="/apa/selftest/test.do?seq=4" class="d-block">
                    	<img src="/apa/asset/images/icons/panic.png" class="featured-block-image img-fluid icon" alt="">
                    
                        <p class="featured-block-text"><strong class="hansans">공황장애 테스트</strong></p>
                    </a>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 col-12 mb-4 mb-lg-0">
                <div class="featured-block d-flex justify-content-center align-items-center">
                    <a href="/apa/selftest/test.do?seq=5" class="d-block">
                    	<img src="/apa/asset/images/icons/adhd.png" class="featured-block-image img-fluid icon" alt="">
                    	
                        <p class="featured-block-text"><strong class="hansans">ADHD 테스트</strong></p>
                    </a>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 col-12 mb-4 mb-lg-0">
                <div class="featured-block d-flex justify-content-center align-items-center">
                    <a href="/apa/selftest/test.do?seq=6" class="d-block">
                    	<img src="/apa/asset/images/icons/anger.png" class="featured-block-image img-fluid icon" alt="">
                    
                        <p class="featured-block-text"><strong class="hansans">분노조절 테스트</strong></p>
                    </a>
                </div>
            </div>
			<div class="col-lg-3 col-md-6 col-12 mb-4 mb-lg-0">
				<div class="featured-block d-flex justify-content-center align-items-center">
					<a href="/apa/selftest/test.do?seq=7" class="d-block">
						<img src="/apa/asset/images/icons/ptsd.png" class="featured-block-image img-fluid icon" alt="">
					
						<p class="featured-block-text"><strong class="hansans">외상후스트레스 테스트</strong></p>
					</a>
				</div>
			</div>
			<div class="col-lg-3 col-md-6 col-12 mb-4 mb-lg-0">
				<div class="featured-block d-flex justify-content-center align-items-center">
					<a href="/apa/selftest/test.do?seq=8" class="d-block">
						<img src="/apa/asset/images/icons/obsessive.png" class="featured-block-image img-fluid icon" alt="">
					
						<p class="featured-block-text"><strong class="hansans">강박증 테스트</strong></p>
					</a>
				</div>
			</div>
        </div>
    </div>
</section>
<%@ include file="/WEB-INF/views/inc/footer.jsp" %>

<script>

</script>
</body>
</html>