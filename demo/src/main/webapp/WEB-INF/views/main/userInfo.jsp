<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user info page</title>
<style type="text/css">
	body{
		background-color: gray;
	}
	.this{
		display:flex;
		width:500px;
		justify-content: space-between;
	}
	h1{ display: inline-block;}
</style>
</head>
<body>
	<h1>${user.user_nickname}의 info page</h1>
	<button onclick="location.href='http://localhost:8080/login'">로그인</button>
	<hr>
	<div class="this">
		<div>
			<h3>아이디	:${user.user_email}</h3>
			<h3>닉네임	:${user.user_nickname}</h3>
			<h3>성별		:${user.user_gender}</h3>
			<h3>국적		:${user.user_nationality}</h3>
			<h3>나이		:${user.user_age}</h3>
			<h3>백신		:${user.user_vaccine}</h3>
			<h3>흡연		:${user.user_smoking}</h3>
			<h3>방		:${user.user_room}</h3>
			<h3>애완동물	:${user.user_pet}</h3>
		</div>
		<div>
			<p>${user.user_nickname}의 character</p>
			<h3>청결도	:${character.cleanliness}</h3>
			<h3>기상시간	:${character.wakeup_time}</h3>
			<h3>취침시간	:${character.sleep_time}</h3>
			<h3>요리빈도	:${character.cooking_frequency}</h3>
			<h3>수다력	:${character.chatter}</h3>
			<h3>잠버릇	:${character.snoring}</h3>
			<h3>mbti	:${character.mbti}</h3>
		</div>
	</div>
	<hr>
		<div>
		<h1>comment zone</h1>
		<div>
			<table>
				<c:forEach var="comment" items="${comment}">
			<tr>
				<td>${comment.comment_id_from}</td>
				<td>${comment.comment_contents}</td>
				<td>${comment.comment_time}</td>
			</tr>
				</c:forEach>
			</table>
		</div>
		<form role="form" action="comment" method="post">
			<input type="hidden" name=comment_id_to value="${user.user_id}">
			<input type="hidden" name=comment_id_from value="${sessionScope.user.user_id}">
			<input type="text" name=comment_contents placeholder="댓글내용 입력">
			<label>비밀글</label>
			<input type="checkbox" value="1" name=comment_access><br>
			<br>
			<input type="submit" value="댓글입력">
		</form>
		</div>
	<a href="http://localhost:8080/main">main으로</a>
</body>
</html>