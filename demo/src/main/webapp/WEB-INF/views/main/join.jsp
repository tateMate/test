<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>join</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
	<h1>회원가입</h1>
	<hr>
	<form role="form" action="join" method="post" enctype="multipart/form-data">
		<table>
		<tr><td>EMAIL:</td><td><input type="text" name="user_email" id="user_email"><br></td></tr>
		<tr><td></td><td><input type="button" value="중복체크" onclick="chkid()"></input></td></tr>
		<tr><td>PASSWORD:</td><td><input type="password" name=user_pw></td></tr>
		<tr><td>nickname:</td><td><input type="text" name=user_nickname></td></tr>
		<tr><td>gender:</td><td><input type="radio" value="0" name=user_gender>남
			<input type="radio" value="1" name=user_gender>여</td></tr>
		<tr><td>nationality:</td><td><input type="text" name=user_nationality></td></tr>
		<tr><td>age:</td><td><input type="number" name=user_age></td></tr>
		<tr><td>smoking:</td><td><input type="radio" value="0" name=user_smoking>비흡연
			<input type="radio" value="1" name=user_smoking>흡연</td></tr>
		<tr><td>vaccine:</td><td><input type="number" name=user_vaccine></td></tr>
		<tr><td>room:</td><td><input type="radio" value="0" name=user_room>없음
			<input type="radio" value="1" name=user_room>있음</td></tr>
		<tr><td>matching:</td><td><input type="radio" value="0" name=user_matching>원하지 않음
			<input type="radio" value="1" name=user_matching>원함</td></tr>
		<tr><td colspan="2"><input type="file" name="file"></td></tr>
		<tr><td><input type="submit" value="가입"></td><td><input type="reset" value="다시쓰자"></td></tr>
		</table>
	</form>
	<br>
	<hr>
<script>
	function chkid(){
		$.ajax({
			url:"emailChk",
			type:"post",
			data:{user_email:user_email.value},
  			dataType:"json",
			success:function(d){
				
				console.dir(d);
				if(d) alert("사용가능")
				else alert("중복된 이메일입니다")
			}
			
		});
	}
</script>
</body>
</html>