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
#main-list{
	padding: 105px 300px 200px;	
}

.collapse-header{
	font-weight: bold;
}

.advice-info {
      position: relative;
       flex-direction: column;
       min-width: 0;
       word-wrap: break-word;
       background-color: #fff;
       background-clip: border-box;
       border: 1px solid #e3e6f0;
       border-radius: 0.5rem;
       margin-top: 20px;
       height: 100px;
	   display: flex;
	   justify-content: center; 
	   overflow: hidden;
	   cursor: pointer;
   }
.info-text {
	/* text-align: center; */
	font-size: 18px;
	margin-bottom: 0;
	margin-left: 10px;
	
}
.small-info {
	display: flex;
	margin-bottom: 8px;
	
}

.document{
	border: 1px solid grey;
	margin-left: 5px;
	border-radius: 0.35rem;
	padding: 3px 3px;
	font-size: 14px;
}

.answer-progress{
	border: 1px solid grey;
	margin-left: 5px;
	border-radius: 0.35rem;
	padding: 3px 3px;
	font-size: 14px;
}

#regist-btn{
	justify-content: center;
}

#counselregist {
	border: 1px;
	position: fixed;
	background-color: #98d6c9;
    bottom: 10px;
    height: 4rem;
    width: 700px;
    border-radius: 8px;
   	transform: translateX(50%);
   	font-weight: bold;
   	font-size: 25px;
   	box-shadow: 2px 2px 5px #0B6127; 
   	color: #0B3B17;
}
#pagebar {
	text-align: center;
	padding: 30px;
	font-size: 20px;

}

.advice_Answer {
	border: 1px solid #E6E6E6;
	background-color: #E6E6E6;
	padding: 15px;
	margin-top: 12px;
	border-radius: 0.35rem;
	display: none;
}
.advice_Answer.show {
    display: block;
}
.advice-AnswerInfo{
	font-weight: bold;
	font-size: 18px;
	margin-bottom: 5px;
}
</style>
    </head>
    <body id="section_1">
        <%@ include file="/WEB-INF/views/inc/header.jsp" %>
      <main id="main-list">
      
      
       <h1 class ="hansans">의학상담</h1>
       <h6>무엇이든 물어보세요. 전문의사가 답해드립니다!</h6>
       <!-- c:forEach삽입 -->
       <c:forEach items="${list}" var="dto" varStatus="loop">
       <hr>
      	<div class="small-info">
	      	<span class="document">${dto.departName}</span>
	      	<span class="answer-progress">${dto.isAnswer}</span>
      	</div>
            <h6 class="collapse-header">Q. ${dto.adviceTitle}</h6>
            <div class="advice-info" id="advice_${loop.index}">
           	<p class="info-text">${dto.adviceContent}</p>
          </div>
            <div class="advice_Answer" id="answer_${loop.index}">
            	<div class="advice-AnswerInfo">${dto.adviceHospitalName} ${dto.departName} ${dto.adviceDoctorName} 의사</div>
            	<div class="answer-content">${dto.adviceCounselAnswerContent}</div>
            </div>	
         </c:forEach>
         
         <div id="pagebar">${pagebar}</div>
     
      <div id="regist-btn">
      	<a href="/apa/advice/add.do"><button id = "counselregist" type="button">의학상담 등록하기</button></a>
      </div>
       
       
	</main>

	<%@ include file="/WEB-INF/views/inc/footer.jsp" %>
    
<script>
	
	$('#selPage').val(${nowPage});
	
	
	<c:forEach items="${list}" var="dto" varStatus="loop">
    	document.getElementById("advice_${loop.index}").addEventListener("click", function () {
	        var answerBox = document.getElementById("answer_${loop.index}");
	        //answerBox.style.display = "block";
	        answerBox.classList.toggle("show");
    	});
	</c:forEach>

	
</script>    
    
    </body>
    
</html>