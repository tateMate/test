<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main page for test</title>
</head>
<body>
	<h1>this is main page for test</h1>
	<hr>
	<a href="http://localhost:8080/login">로그인 test</a><br>
	<a href="http://localhost:8080/join">회원가입 test</a><br>
	<a href="http://localhost:8080/userinfo?user_id=1">회원정보</a><br>
	
	<h1>${sessionScope.user.user_nickname}</h1>
	<h1>${sessionScope.userCharacter.mbti}</h1>
	
</body>
</html>