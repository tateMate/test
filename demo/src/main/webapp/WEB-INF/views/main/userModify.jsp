<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>join</title>
<style>
	div{
		display: inline-block;
	}
</style>
</head>
<body>
	<form role="form" action="modify" method="post" enctype="multipart/form-data">
<!-- basic user info zone -->
		<div>
			<input type="hidden" value="${sessionScope.user.user_id}" name="user_id">
			<label>EMAIL:</label>${sessionScope.user.user_email}<br>
			<label>nickname:</label>
			<input value="${sessionScope.user.user_nickname}" type="text" name=user_nickname><br>
			<label>nationality:</label> <input value="${sessionScope.user.user_nationality}" type="text" name=user_nationality><br>
			<label>age:</label> <input value="${sessionScope.user.user_age}" type="number" name=user_age><br>
			<label>smoking:</label>
			<input type="radio" value="0" name=user_smoking ${sessionScope.user.user_smoking eq '0' ? 'checked':''}>비흡연
			<input type="radio" value="1" name=user_smoking ${sessionScope.user.user_smoking eq '1' ? 'checked':''}>흡연<br>
			<label>vaccine:</label> <input value="${sessionScope.user.user_vaccine}" type="number" name=user_vaccine><br>
			<label>room:</label>
			<input type="radio" value="0" name=user_room ${sessionScope.user.user_room eq '0' ? 'checked':''}>없음
			<input type="radio" value="1" name=user_room ${sessionScope.user.user_room eq '1' ? 'checked':''}>있음<br>
			<label>matching:</label>
			<input type="radio" value="0" name=user_matching ${sessionScope.user.user_matching eq '0' ? 'checked':''}>원하지 않음
			<input type="radio" value="1" name=user_matching ${sessionScope.user.user_matching eq '1' ? 'checked':''}>원함<br>
			<img src="../../../IMG/${sessionScope.user.user_profile}" height="100"><br>
			<input type="file" name="file">
			<br>
		</div>
<!-- user character info zone -->
		<div><table>
			<tr><td colspan='5'>청결도</td></tr><tr><td>
				<input type="radio" value="1" name="cleanliness" ${sessionScope.userCharacter.cleanliness eq '1' ? 'checked':''}>매우 더러움
			</td><td>
				<input type="radio" value="2" name="cleanliness" ${sessionScope.userCharacter.cleanliness eq '2' ? 'checked':''}>더러움
			</td><td>
				<input type="radio" value="3" name="cleanliness" ${sessionScope.userCharacter.cleanliness eq '3' ? 'checked':''}>보통
			</td><td>
				<input type="radio" value="4" name="cleanliness" ${sessionScope.userCharacter.cleanliness eq '4' ? 'checked':''}>깨끗함
			</td><td>
				<input type="radio" value="5" name="cleanliness" ${sessionScope.userCharacter.cleanliness eq '5' ? 'checked':''}>매우 깨끗함
			</td></tr>
			<tr><td colspan='5'>기상시간</td></tr><tr><td>
				<input type="radio" value="1" name="wakeup_time" ${sessionScope.userCharacter.wakeup_time eq '1' ? 'checked':''}>매우 이름
			</td><td>
				<input type="radio" value="2" name="wakeup_time" ${sessionScope.userCharacter.wakeup_time eq '2' ? 'checked':''}>이름
			</td><td>
				<input type="radio" value="3" name="wakeup_time" ${sessionScope.userCharacter.wakeup_time eq '3' ? 'checked':''}>보통
			</td><td>
				<input type="radio" value="4" name="wakeup_time" ${sessionScope.userCharacter.wakeup_time eq '4' ? 'checked':''}>늦음
			</td><td>
				<input type="radio" value="5" name="wakeup_time" ${sessionScope.userCharacter.wakeup_time eq '5' ? 'checked':''}>매우 늦음
			</td></tr>
			<tr><td colspan='5'>취침시간</td></tr><tr><td>
			<input type="radio" value="1" name="sleep_time" ${sessionScope.userCharacter.sleep_time eq '1' ? 'checked':''}>매우 이름
			</td><td>
			<input type="radio" value="2" name="sleep_time" ${sessionScope.userCharacter.sleep_time eq '2' ? 'checked':''}>이름
			</td><td>
			<input type="radio" value="3" name="sleep_time" ${sessionScope.userCharacter.sleep_time eq '3' ? 'checked':''}>보통
			</td><td>
			<input type="radio" value="4" name="sleep_time" ${sessionScope.userCharacter.sleep_time eq '4' ? 'checked':''}>늦음
			</td><td>
			<input type="radio" value="5" name="sleep_time" ${sessionScope.userCharacter.sleep_time eq '5' ? 'checked':''}>매우 늦음
			</td></tr>
			<tr><td colspan='5'>요리빈도</td></tr><tr><td>
				<input type="radio" value="1" name="cooking_frequency" ${sessionScope.userCharacter.cooking_frequency eq '1' ? 'checked':''}>매우 드묾
			</td><td>
				<input type="radio" value="2" name="cooking_frequency" ${sessionScope.userCharacter.cooking_frequency eq '2' ? 'checked':''}>드묾
			</td><td>
				<input type="radio" value="3" name="cooking_frequency" ${sessionScope.userCharacter.cooking_frequency eq '3' ? 'checked':''}>보통
			</td><td>
				<input type="radio" value="4" name="cooking_frequency" ${sessionScope.userCharacter.cooking_frequency eq '4' ? 'checked':''}>빈번함
			</td><td>
				<input type="radio" value="5" name="cooking_frequency" ${sessionScope.userCharacter.cooking_frequency eq '5' ? 'checked':''}>매우 빈번함
			</td></tr>
			<tr><td colspan='5'>수다력</td></tr><tr><td>
				<input type="radio" value="1" name="chatter" ${sessionScope.userCharacter.chatter eq '1' ? 'checked':''}>매우 과묵함
			</td><td>
				<input type="radio" value="2" name="chatter" ${sessionScope.userCharacter.chatter eq '2' ? 'checked':''}>과묵함
			</td><td>
				<input type="radio" value="3" name="chatter" ${sessionScope.userCharacter.chatter eq '3' ? 'checked':''}>보통
			</td><td>
				<input type="radio" value="4" name="chatter" ${sessionScope.userCharacter.chatter eq '4' ? 'checked':''}>수다스러움
			</td><td>
				<input type="radio" value="5" name="chatter" ${sessionScope.userCharacter.chatter eq '5' ? 'checked':''}>매우 수다스러움
			</td></tr>
			<tr><td colspan='5'>잠버릇(코골이, 이갈이, 몽유병 등)</td></tr><tr><td>
			<input type="radio" value="1" name="snoring" ${sessionScope.userCharacter.snoring eq '1' ? 'checked':''}>매우 얌전함
			</td><td>
			<input type="radio" value="2" name="snoring" ${sessionScope.userCharacter.snoring eq '2' ? 'checked':''}>암전함
			</td><td>
			<input type="radio" value="3" name="snoring" ${sessionScope.userCharacter.snoring eq '3' ? 'checked':''}>보통
			</td><td>
			<input type="radio" value="4" name="snoring" ${sessionScope.userCharacter.snoring eq '4' ? 'checked':''}>심함
			</td><td>
			<input type="radio" value="5" name="snoring" ${sessionScope.userCharacter.snoring eq '5' ? 'checked':''}>매우 심함
			</td></tr>
			<tr><td colspan='5'>mbti</td></tr><tr><td>
				<input type="text" value="${sessionScope.userCharacter.mbti}" name="mbti">
			</td></tr>
		</table></div>
		<input type="submit" value="수정" style="display:block;">
	</form>
	<br>
	<hr>

</body>
</html>