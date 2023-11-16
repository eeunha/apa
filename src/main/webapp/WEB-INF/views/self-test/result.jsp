<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="/WEB-INF/views/inc/asset.jsp" %>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        #main {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            margin: auto;
            background-color: #fff;
            padding: 20px;
        }

        #result_test{

        }
        #main > h1 {
            color: #1c294e;
            font-size: 30px;
            text-align: center;
        }
		
        #go_test {
            text-align: center;
            border: 0;
        	display: flex;
        	margin: 0 auto;
            font-size: 1.2rem;
            background: #5bc1ac;
            color: white;
            padding: 10px;
            cursor: pointer;
            border-radius: 10px;
            width: 100px;
            justify-content: center
        }
        
        #btn-box {
        	width: 80%;
        	margin: 0 auto;
        	height: 50px;
        }		
        
        #box-20 {
        	height: 20px;
        }	
        
        .icon {
			margin-top: 50px;
		    width: 25%;        
        }
        
        .hansans {
        	padding-top: 30px;
        }	
    </style>
</head>
<body>
<%@ include file="/WEB-INF/views/inc/header.jsp" %>
<!-- /selftest/test.jsp -->
<main id="main">
        <c:if test="${seq eq 1}">
        <c:choose>
        <c:when test="${sum >= 0 && sum <= 13}">
        		<img src="/apa/asset/images/icons/happy.png" class="featured-block-image img-fluid icon" alt="">
                <h1 class="h3 mb-0 text-gray-800 hansans">"정상적인 스트레스 상태입니다."</h1>
            </c:when>
            <c:when test="${sum > 13 && sum <= 18}">
        		<img src="/apa/asset/images/icons/soso.png" class="featured-block-image img-fluid icon" alt="">

                <h1 class="h3 mb-0 text-gray-800 hansans">"경도의 스트레스 상태입니다. 스트레스의 영향을 조금 받고 있는 상태입니다. 스트레스를 줄이고 긍정적인 경험을
                    하는 등 스트레스 관리가 필요합니다."</h1>
            </c:when>
            <c:when test="${sum > 18 && sum <= 40}">
            	<img src="/apa/asset/images/icons/sad.png" class="featured-block-image img-fluid icon" alt="">
                <h1 class="h3 mb-0 text-gray-800 hansans">"심한 스트레스 상태입니다. 적극적인 스트레스 관리가 필요하며 혼자서 해결하기 어려울 수 있으므로 전문가의
                    도움을 적극적으로 받으시기 바랍니다."</h1>
            </c:when>
            <c:otherwise>
            	<img src="/apa/asset/images/icons/question.png" class="featured-block-image img-fluid icon" alt="">
                <h1 class="h3 mb-0 text-gray-800 hansans">"점수 범위를 벗어남"</h1>
            </c:otherwise>
            </c:choose>
            </c:if>

            <c:if test="${seq eq 2}">
            <c:choose>
            <c:when test="${sum >= 0 && sum <= 4}">
         	    <img src="/apa/asset/images/icons/happy.png" class="featured-block-image img-fluid icon" alt="">
                <h1 class="h3 mb-0 text-gray-800 hansans">"정상적인 수준의 불안을 경험하고 있습니다."</h1>
            </c:when>
            <c:when test="${sum > 4 && sum <= 9}">
            	<img src="/apa/asset/images/icons/soso.png" class="featured-block-image img-fluid icon" alt="">
                <h1 class="h3 mb-0 text-gray-800 hansans">"가벼운 정도의 불안을 경험하고 있습니다. 심각한 문제는 아니지만 좀 더 안정을 취할 방법을 강구해
                    보십시오."</h1>
            </c:when>
            <c:when test="${sum > 9 && sum <= 14}">
				<img src="/apa/asset/images/icons/sad.png" class="featured-block-image img-fluid icon" alt="">
         	    <h1 class="h3 mb-0 text-gray-800 hansans">"상당한 정도의 불안을 경험하고 있습니다. 정서적인 안정감이 부족하고 일상생활에도 어려움을 겪고 있을 수 있으므로
                전문가의 도움이 필요합니다."</h1>

            </c:when>
            <c:when test="${sum > 14 && sum <= 21}">
				<img src="/apa/asset/images/icons/verysad.png" class="featured-block-image img-fluid icon" alt="">
            	<h1 class="h3 mb-0 text-gray-800 hansans">"심한 불안을 경험하고 있습니다. 거의 항상 불안하여 정상적으로 일상생활을 해나가는 데 어려움을 경험할 것
                "이미 도움을 요청했을 수도 있지만 그렇지 않다면 가능한 한 조속한 시일 내에 전문가를 통해 상담 받을 것을
                권합니다."</h1>

            </c:when>
            <c:otherwise>
				<img src="/apa/asset/images/icons/question.png" class="featured-block-image img-fluid icon" alt="">
            	<h1 class="h3 mb-0 text-gray-800 hansans">"점수 범위를 벗어남"</h1>
            </c:otherwise>
            </c:choose>
            </c:if>

            <c:if test="${seq eq 3}">
            <c:choose>
            <c:when test="${sum >= 0 && sum <= 19}">
				<img src="/apa/asset/images/icons/happy.png" class="featured-block-image img-fluid icon" alt="">
       	    	<h1 class="h3 mb-0 text-gray-800 hansans">"일반적인 정상 수준의 범위에 해당하므로 우울증에 대해 크게 걱정하지 않아도 됩니다."</h1>

            </c:when>
            <c:when test="${sum > 19 && sum <= 31}">
				<img src="/apa/asset/images/icons/soso.png" class="featured-block-image img-fluid icon" alt="">
            	<h1 class="h3 mb-0 text-gray-800 hansans">"경미한 수준의 우울증 증세가 있어 보입니다. 자신과 주변에 대해 긍정적인 시각을 갖기 위한 노력을 하시고 관심이 있는
                활동을 통해 기분 전환의 기회를 가져보세요. 다만 혼자서 극복하기가 어렵다고 생각되시면 전문가의 도움을 받아보시기 바랍니다."</h1>

            </c:when>
            <c:when test="${sum > 31 && sum <= 40}">
				<img src="/apa/asset/images/icons/sad.png" class="featured-block-image img-fluid icon" alt="">
            	<h1 class="h3 mb-0 text-gray-800 hansans">"중증도의 우울증 증세를 보여 일상생활에 어려움 또는 불편을 겪고 계실 것으로 보여집니다. 우울증은 마음의 감기라고
                하듯 누구나 걸릴 수 있고 초기에 극복하려는 의지를 가져야 치료 효과가 높습니다. 전문가의 도움을 받아보시길 권해드립니다."</h1>

            </c:when>
            <c:when test="${sum > 40 && sum <= 60}">
				<img src="/apa/asset/images/icons/verysad.png" class="featured-block-image img-fluid icon" alt="">
            	<h1 class="h3 mb-0 text-gray-800 hansans">"우울증 증상이 심한 수준으로 보여집니다. 전문가와의 상담을 통해 약물치료를 포함한 즉각적인 치료가 필요해보입니다. 이
                정도의 결과의 경우 가능한 병원 치료를 권합니다."</h1>
            </c:when>
            <c:otherwise>
				<img src="/apa/asset/images/icons/question.png" class="featured-block-image img-fluid icon" alt="">
            	<h1 class="h3 mb-0 text-gray-800 hansans">"점수 범위를 벗어남"</h1>
            </c:otherwise>
            </c:choose>
            </c:if>

            <c:if test="${seq eq 4}">
            <c:choose>
            <c:when test="${sum >= 0 && sum <= 4}">
				<img src="/apa/asset/images/icons/happy.png" class="featured-block-image img-fluid icon" alt="">
            	<h1 class="h3 mb-0 text-gray-800 hansans">"정상 수치에 해당합니다. 만약 동일 증상이 반복된다면 다른 원인에 의한 것은 아닌지 검진이 필요할 수도 있겠으나
                심리적인 요인이 크다고 느껴진다면 상담 전문가와 상의를 해보실 것을 권해드립니다."</h1>
            </c:when>
            <c:when test="${sum > 4 && sum <= 13}">
				<img src="/apa/asset/images/icons/sad.png" class="featured-block-image img-fluid icon" alt="">
            	<h1 class="h3 mb-0 text-gray-800 hansans">"공황장애가 의심되니 전문가의 상담을 받아보시기 바랍니다. 심각한 스트레스를 받은 직후이거나 불편한 장소 긴장의
                상태에서 중증 정도의 증상을 빈번하게 겪고 계시다면 빠른 시일 내에 전문가를 찾아주시기 바랍니다."</h1>
            </c:when>
            <c:otherwise>
				<img src="/apa/asset/images/icons/question.png" class="featured-block-image img-fluid icon" alt="">
            	<h1 class="h3 mb-0 text-gray-800 hansans">"점수 범위를 벗어남"</h1>
            </c:otherwise>
            </c:choose>
            </c:if>


            <c:if test="${seq eq 5 || seq eq 6 || seq eq 7 || seq eq 8}">
            <c:choose>
            <c:when test="${sum >= 0 && sum <= 7}">
				<img src="/apa/asset/images/icons/happy.png" class="featured-block-image img-fluid icon" alt="">
	            <h1 class="h3 mb-0 text-gray-800 hansans">"정상"</h1>

            </c:when>
            <c:when test="${sum > 7 && sum <= 14}">
				<img src="/apa/asset/images/icons/soso.png" class="featured-block-image img-fluid icon" alt="">
            	<h1 class="h3 mb-0 text-gray-800 hansans">"중증"</h1>

            </c:when>
            <c:when test="${sum > 14 && sum <= 20}">
				<img src="/apa/asset/images/icons/sad.png" class="featured-block-image img-fluid icon" alt="">
            	<h1 class="h3 mb-0 text-gray-800 hansans">"심각"</h1>

            </c:when>
            <c:otherwise>
				<img src="/apa/asset/images/icons/question.png" class="featured-block-image img-fluid icon" alt="">
            	<h1 class="h3 mb-0 text-gray-800 hansans">"점수 범위를 벗어남"</h1>
            </c:otherwise>
            </c:choose>
            </c:if>
	
	<div id="box-20"></div>
	<div id="box-20"></div>
	<div id="btn-box"><input id="go_test" type="button" value="확인" onclick="location.href='/apa/selftest/list.do'"></div>
    
</main>

<%@ include file="/WEB-INF/views/inc/footer.jsp" %>
<!-- 추가 질문들... -->
<%--seq가 1일때(스트레스 테스트) 받아온 점수가 9점이면 결과가 어떻게 나올까요--%>


<script>


</script>
</body>
</html>
