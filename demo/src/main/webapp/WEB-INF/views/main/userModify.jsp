<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>join</title>
</head>
<body>
	<form role="form" action="modify" method="post" enctype="multipart/form-data">
		<input type="hidden" value="${sessionScope.user.user_id}" name="user_id">
		<label>EMAIL:</label>${sessionScope.user.user_email}<br>
		<label>nickname:</label>
		<input value="${sessionScope.user.user_nickname}" type="text" name=user_nickname><br>
		<label>nationality:</label> <input type="text" name=user_nationality><br>
		<label>age:</label> <input type="number" name=user_age><br>
		<label>smoking:</label>
		<input type="radio" value="0" name=user_smoking>비흡연
		<input type="radio" value="1" name=user_smoking>흡연<br>
		<label>vaccine:</label> <input type="number" name=user_vaccine><br>
		<label>room:</label>
		<input type="radio" value="0" name=user_room>없음
		<input type="radio" value="1" name=user_room>있음<br>
		<label>matching:</label>
		<input type="radio" value="0" name=user_matching>원하지 않음
		<input type="radio" value="1" name=user_matching>원함<br>
		<input type="file" name="file">
		<br>
		<input type="submit" value="수정">
	</form>
	<br>
	<hr>

</body>
</html>