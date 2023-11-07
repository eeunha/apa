<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header id="header">
	<h1>
		<c:if test="${empty id}">
			<span>아파!</span>
		</c:if>
		
		<c:if test="${ not empty id }">
			<span>
				<span class="material-symbols-outlined">Toys</span>
				.. 아파!
			</span>
		</c:if>
		<span>어디가?</span>
	</h1>
	<nav>
		<a href="/apa/main.do">Home</a>
		
		<c:if test="${empty id}">
			<a href="/apa/user/login.do">로그인</a>
			<a href="/apa/user/register.do">회원가입</a>
		</c:if>
		<c:if test="${not empty id}">
			<a href="/apa/user/logout.do">로그아웃</a>
		</c:if>
		
		<a href="/apa/community/list.do">게시판</a>
	</nav>
</header>


