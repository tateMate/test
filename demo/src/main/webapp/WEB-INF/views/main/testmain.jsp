<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main page for test</title>
<style>
</style>
</head>
<body>
	<h1>this is main page for test</h1>
	<a href="http://localhost:8080/login">로그인 test</a><br>
	<a href="http://localhost:8080/join">회원가입 test</a><br>
	<a href="http://localhost:8080/userinfo?user_id=1">회원정보</a><br>
	<hr>
		<h1 style="font-size:150px;">TATEMATE</h1>
		<h6>따떼마떼</h6>
	<hr>
<!-- recommend room-mate zone -->
	<c:choose>
		<c:when test="${empty sessionScope.user.user_id}">
			<p>로그인을 하거나 회원가입을 해주세요. 제발</p>
		</c:when>
		<c:otherwise>
			<c:forEach var="rcmd" items="${rcmd}">
				<h1>${rcmd.user_id}</h1>
				<h1>nickname: ${rcmd.user_nickname}</h1>
				<h1>user_gender:${rcmd.user_gender}</h1>
				<h1>pw: ${rcmd.user_pw}</h1>
				<br>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	
</body>
</html>