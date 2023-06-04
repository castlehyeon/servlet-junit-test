<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="nav-container">
	<a href="/main.signup" class="nav-link">Main</a>&nbsp;&nbsp;&nbsp;
	<a href="/login.signup" class="nav-link">Login</a>&nbsp;&nbsp;&nbsp;
	<a href="/register.signup" class="nav-link">Register</a>&nbsp;&nbsp;&nbsp;
	<a href="#" class="nav-link">Intro</a>&nbsp;&nbsp;&nbsp;
	<a href="#" class="nav-link">Intro</a>&nbsp;&nbsp;&nbsp;

	<c:choose>
		<c:when test="${not empty sessionScope.userid}">
			<div class="text-nowrap bg-body-secondary border" style="width: 20rem;">
				<b>[${sessionScope.userid}]</b>로그인 상태
			</div>
			<a class="btn btn-danger" href='/logout.signup'>로그아웃</a>
		</c:when>
		<c:otherwise>
			<div class="text-nowrap bg-body-secondary border" style="width: 20rem;">
				<b>[로그인 하지 않으셨네요]</b>
			</div>
			<a class="btn btn-danger" href='/login.signup'>로그인</a>
		</c:otherwise>
	</c:choose>
</div>
