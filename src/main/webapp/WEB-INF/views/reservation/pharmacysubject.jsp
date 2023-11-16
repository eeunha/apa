<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>
<link href='https://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
<style>
	body {
		-webkit-user-select: none;
 		-moz-user-select: none;
 		-ms-use-select: none;
		background-color: #f8f8f8;
	}
	.hospital-info {
		position: relative;
	    flex-direction: column;
	    min-width: 0;
	    word-wrap: break-word;
	    background-color: #fff;
	    background-clip: border-box;
	    border: 1px solid #f8f8f8;
	    border-radius: 0.35rem;
	    padding: 50px 100px;
	    margin-top: 100;
	    text-align: center;
	}
	.subject-choice{	
		display: flex;
		justify-content: center;
		padding-bottom: 10px;
	}
	
	.hospital-info-dept {
		text-align: center;
		width: 100px;
		height: 35px;
		border: 1px solid #fff;
		border-radius: 0.6rem;
		background-color: #f0f0f0;
		padding-top: 5px;
		margin-right: 5px;
		margin-top: 7.5px;
		opacity: 0.6;
	}
	.hospital-info-dept:hover {
		background-color: #999999;
		opacity: 0.9;
	}
	
	#calendar{
	  margin-left: auto;
	  margin-right: auto;
	  width: 320px;
	  font-family: 'Lato', sans-serif;
	  margin-bottom: 800px;
	}
	#calendar_weekdays div{
	  display:inline-block;
	  vertical-align:top;
	}
	#calendar_content, #calendar_weekdays, #calendar_header{
	  position: inherit;
	  width: 320px;
	  overflow: hidden;
	  float: left;
	  z-index: 10;
	}
	#calendar_weekdays div, #calendar_content div{
	  width:40px;
	  height: 40px;
	  overflow: hidden;
	  text-align: center;
	  background-color: #F5F1E9;
	  color: #787878;
	}
	#calendar_content{
	  -webkit-border-radius: 0px 0px 12px 12px;
	  -moz-border-radius: 0px 0px 12px 12px; 
	  border-radius: 0px 0px 12px 12px;
	}
	#calendar_content div{
	  float: left;
	}
	#calendar_content div.other:hover{
	  background-color: #5bc1ac;
	  opacity:0.7;
	  filter: alpha(opacity=70);
	  color: #FFFFFF;
	}
	#calendar_content div.blank{
	  background-color: #d9d7d2;
	}
	#calendar_header, #calendar_content div.today{
	  background-color: #5bc1ac;
	  filter: alpha(opacity=70);
	  opacity: 1;
	}
	#calendar_content div.today{
	  color: #FFFFFF;
	}
	#calendar_header{
	  width: 100%;
	  height: 37px;
	  text-align: center;
	  background-color: #FF6860;
	  padding: 18px 0;
	  -webkit-border-radius: 12px 12px 0px 0px;
	  -moz-border-radius: 12px 12px 0px 0px; 
	  border-radius: 12px 12px 0px 0px;
	}
	#calendar_header h1{
	  font-size: 2em;
	  color: #FFFFFF;
	  float:left;
	}
	i[class^=icon-chevron]{
	  color: #FFFFFF;
	  float: left;
	  width:15%;
	  border-radius: 50%;
	}
	#calendar_header {
		background-color: #5bc1ac;
		padding-left: 180px;
	}
	.reservation-button {
		text-align: center;
		width: 480px;
		height: 50px;
		border: 1px solid #fff;
		border-radius: 0.6rem;
		background-color: #f0f0f0;
		margin-top: 50px;
	}
	.doctor-choice {
		display: flex;
		justify-content: center;
	}
	.doctor-choice > div{
		margin: 0px 30px;
	}
	.docter-images {
		width: 150px;
		height: 150px;
		border: 1px solid #FFFFFF;
		border-radius: 50%;
		margin-bottom: 10px;
		opacity: 0.6;
	}
	.docter-images:hover {
		opacity: 0.9;
	}
	.hospital-doc-name {
		font-size: 20px;
	}
	.hospital-info > h1 {
		padding-bottom: 20px;
		padding-top: 20px;
	}
	.timepicker {
		margin-top: 30px;
	}
</style>
</head>
<body>
	<!-- /reservation/subject.jsp -->
	<%@ include file="/WEB-INF/views/inc/header.jsp" %>
	<form action="/apa/reservation/detail.do" method="POST">
		<div class="hospital-info">			
			<h1 class="lineseedkr">진료일 선택</h1>
			<div id="calendar">
			    <div id="calendar_header"><i class="icon-chevron-left"></i>          <h1></h1><i class="icon-chevron-right"></i>         </div>
			    <div id="calendar_weekdays"></div>
			    <div id="calendar_content"></div>
			    <div id="day-choice"></div>
				<div class="time-list"><p2 class="lineseedkr">시간선택: </p2>
					<input type="text" class="timepicker" name="choicetime" id="timepicker" readonly >
				</div>
				<div> 
					<button type="submit" class="reservation-button" disabled="disabled"> 다음으로 </button>
				</div>
			</div>
			<input type="hidden" name="id" value="${id}">
			<input type="hidden" name="choicetype" value="drug">
		</div>
	</form>
	<%@ include file="/WEB-INF/views/inc/footer.jsp" %>
	<script>
		$('.timepicker').timepicker({
		    timeFormat: 'HH:mm',
		    interval: 30,
		    minTime: '${timedto.openTime}',
		    maxTime: '${timedto.closeTime}',
		    defaultTime: '${nowtime}',
		    startTime: '${timedto.openTime}',
		    dynamic: false,
		    dropdown: true,
		    scrollbar: true
		});
		$(function(){
			function c(){
				p();
				var e=h();var r=0;var u=false;
				l.empty();
				while(!u){
					if(s[r]==e[0].weekday){
						u=true
					}else{
						l.append('<div class="blank"></div>');
						r++
					}
				}
				for(var c=0;c<42-r;c++){
					if(c>=e.length){
						l.append('<div class="blank"></div>')
					}else{
						var v=e[c].day;
						if (/* g(new Date(t,n-1,v)) */ gg()<=c+1) {
							m='<div class="other" name="choiceday"  id="other'+c+'" onclick = daychoice()>'
						} else {
							m='<div class="blank" name="day" id="other'+c+'">';
						}
						/* var m=g(new Date(t,n-1,v))?'<div class="blank" name="choiceday"  id="other'+c+'" onclick = daychoice()>':'<div class="other" name="day" id="other'+c+'" onclick = daychoice()>'; */
						l.append(m+""+v+"</div>")
					}
				}
				var y=o[n-1];
				a.css("background-color",y).find("h1").text(t+" . "+i[n-1]);
				f.find("div").css("color",y);
				l.find(".today").css("background-color",y);
				d();
				
			}
			function h(){
				var e=[];
				for(var r=1;r<v(t,n)+1;r++){
					e.push({day:r,weekday:s[m(t,n,r)]})
				}
				return e
			}
			function p(){
				f.empty();
				for(var e=0;e<7;e++){
					f.append("<div>"+s[e].substring(0,3)+"</div>")
				}
			}
			function d(){
				var t;var n=$("#calendar").css("width",e+"px");
				n.find(t="#calendar_weekdays, #calendar_content").css("width",e+"px").find("div").css({width:e/7+"px",height:e/7+"px","line-height":e/7+"px"});
				n.find("#calendar_header").css({height:e*(1/7)+"px"}).find('i[class^="icon-chevron"]').css("line-height",e*(1/7)+"px")
			}
			function v(e,t){
				return(new Date(e,t,0)).getDate()
			}
			function m(e,t,n){
				return(new Date(e,t-1,n)).getDay()
			}
			function g(e){
				return y(new Date)==y(e)
			}
			function gg() {
				return yy(new Date)
			}
			function yy(e){
				return e.getDate()
			}
			function y(e){
				return e.getFullYear()+"/"+(e.getMonth()+1)+"/"+e.getDate()
			}
			function b(){
				var e=new Date;t=e.getFullYear();n=e.getMonth()+1
			}
			var e=480;
			var t=2013;
			var n=9;
			var r=[];
			var i=["1","2","3","4","5","6","7","8","9","10","11","12"];
			var s=["일요일","월요일","화요일","수요일","목요일","금요일","토요일"];
			var o=["#5bc1ac","#5bc1ac","#5bc1ac","#5bc1ac","#5bc1ac","#5bc1ac","#5bc1ac","#e67e22","#2ecc71","#5bc1ac","#5bc1ac","#5bc1ac"];
			var u=$("#calendar");
			var a=u.find("#calendar_header");
			var f=u.find("#calendar_weekdays");
			var l=u.find("#calendar_content");
			b();
			c();
			a.find('i[class^="icon-chevron"]').on("click",function(){
				var e=$(this);
				var r=function(e){
					n=e=="next"?n+1:n-1;
					if(n<1){
						n=12;t--
					}else if(n>12){
						n=1;t++}c()
					};
					if(e.attr("class").indexOf("left")!=-1){
						r("previous")
					}else{
						r("next")
					}
			})
		})
		function daychoice() {
			$('.other').css('background-color', '#F5F1E9');
			$('.other').css('color', '#787878');
			$('.other').css('filter', 'alpha(opacity=70)');
			$('.other').css('opacity', '1');
			$(event.target).css('background-color', '#5bc1ac');
			$(event.target).css('color', '#FFFFFF');
			$(event.target).css('opacity', '1');
			$('#day-choice').html('');
			$('#day-choice').append('<input type="hidden" id="choiceday" name="choiceday" value= "">');
			$('#choiceday').val($(event.target).text());
			$('.reservation-button').attr("disabled", false);
			
		}
		function docchoice(seq) {
			$('.docter-images').css('opacity', '0.6');
			$(event.target).css('opacity', '1');
			$('#hidden-choice-doc').html('');
			$('#hidden-choice-doc').append('<input type="hidden" id="choicedoc" name="choicedoc" value= "">');
			$('#choicedoc').val(seq);
			$('.reservation-button').attr("disabled", false);
			
		}
		
	</script>
</body>
</html>