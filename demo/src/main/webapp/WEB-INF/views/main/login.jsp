<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>login</title>
</head>
<body>
	<form action="login" method="post">
		<label>ID:</label>
		<input type="text" name="user_email"><br>
		<label>PASSWORD:</label>
		<input type="password" name=user_pw><br>
		<br>
		<input type="submit" value="로그인">
	</form>
	<br>
	<hr>

</body>
</html>