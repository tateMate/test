<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>tatemate login</title>
<style>
	a{font-size: 10px;
</style>
</head>
<body>
	<form action="login" method="post">
		<label>ID:</label>
		<input type="text" name="user_email"><br>
		<label>PASSWORD:</label>
		<input type="password" name=user_pw><br>
		<br>
		<input type="submit" value="로그인" style="margin-right:10px;"><a href='http://localhost:8080/forgotPw'>비밀번호 찾기</a>
	</form>
	
	<br>
	<hr>
<script>
</script>

</body>
</html>