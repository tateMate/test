<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main page for test</title>
<style>
div{
	border:1px solid black;
	width:30%;
	overflow: hidden;
	display: inline-block;
	background-color:aliceblue; 
}
div:hover{
	background-color:red;
	cursor: pointer;
}
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
			<p><button onclick="location.href='login'">로그인</button>을 하거나 <button onclick="location.href='join'">회원가입</button>을 해주세요. 제발</p>
		</c:when>
		<c:otherwise>
			<c:forEach var="rcmd" items="${rcmd}">
				<div onclick="location.href='userinfo?user_id='+${rcmd.user_id}" >
					<h1>닉네임: ${rcmd.user_nickname}</h1>
					<h1>성별:${rcmd.user_gender}</h1>
					<h1>나이:${rcmd.user_age}</h1>
					<h1>pw: ${rcmd.user_pw}</h1>
				</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>

<script>
</script>
</body>
</html>