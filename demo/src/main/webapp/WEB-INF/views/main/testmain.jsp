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
		<h1 style="font-size:150px;">대충 TATEMATE 로고 크게 박힘</h1>
	<hr>
<!-- recommend room-mate zone -->
	<c:choose>
		<c:when test="${empty sessionScope.user.user_id}">
			<p>로그인을 하거나 회원가입을 해주세요. 제발</p>
		</c:when>
		<c:otherwise>
			<c:forEach var="rcmd" items="${rcmd}">
				<h1>${rcmd.user_id}</h1>
				<br>
			</c:forEach>
			<p>로그인은 했으나 이걸 어째, 아직 서비스 구현을 못해보렸넹 힣</p>
		</c:otherwise>
	</c:choose>
	
</body>
</html>