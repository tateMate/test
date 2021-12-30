<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user info page</title>
<style type="text/css">
body {
	background-color: gray;
}

.this {
	display: flex;
	width: 500px;
	justify-content: space-between;
}

h1 {
	display: inline-block;
}

#inputcocomment {
	display: none;
}
</style>
</head>
<body>
<!-- 헤더 -->
	<h1>${user.user_nickname}의info page</h1>
	<c:choose>
		<c:when  test="${empty sessionScope.user.user_id}">
			<button onclick="location.href='http://localhost:8080/login'">로그인</button>
		</c:when>
		<c:otherwise>
			<button onclick="location.href='http://localhost:8080/userinfo?user_id='+${sessionScope.user.user_id}">마이페이지</button>
			<p style="display: inline-block">${sessionScope.user.user_nickname}(id:${sessionScope.user.user_id})로 로그인중</p>
		</c:otherwise>
	</c:choose>
	<hr>
<!-- user info zone -->
	<div class="this">
		<div>
			<h3>아이디 :${user.user_email}</h3>
			<h3>닉네임 :${user.user_nickname}</h3>
			<h3>성별 :${user.user_gender}</h3>
			<h3>국적 :${user.user_nationality}</h3>
			<h3>나이 :${user.user_age}</h3>
			<h3>백신 :${user.user_vaccine}</h3>
			<h3>흡연 :${user.user_smoking}</h3>
			<h3>방 :${user.user_room}</h3>
			<h3>애완동물 :${user.user_pet}</h3>
		</div>
		<div>
			<p>${user.user_nickname}의character</p>
			<h3>청결도 :${character.cleanliness}</h3>
			<h3>기상시간 :${character.wakeup_time}</h3>
			<h3>취침시간 :${character.sleep_time}</h3>
			<h3>요리빈도 :${character.cooking_frequency}</h3>
			<h3>수다력 :${character.chatter}</h3>
			<h3>잠버릇 :${character.snoring}</h3>
			<h3>mbti :${character.mbti}</h3>
		</div>
	</div>
	<c:if test="${sessionScope.user.user_id eq param.user_id}">
		<button onclick="window.location.href='/userinfo/modify?user_id=${user.user_id}'">회원정보 수정</button>
	</c:if>
	<hr>
	<div>
		<h1>comment zone</h1>
		<div>
			<table>
				<c:forEach var="comment" items="${comment}" varStatus="status">
<!-- 댓글 zone -->
					<c:choose>
						<c:when test="${comment.comment_status==2}">
							<tr>
								<td colspan="3">삭제된 글입니다.</td>
							</tr>
						</c:when>
						<c:when test="${comment.comment_status==0}">
							<c:choose>
								<c:when test="${comment.comment_access==0 or sessionScope.user.user_id==comment.comment_id_to or sessionScope.user.user_id==comment.comment_id_from}">
									<tr>
										<td>${comment.comment_id_from}</td>
										<td onclick="setcocommentname(${comment.comment_id})"
											id="contents_modify_zone${comment.comment_id}">${comment.comment_contents}</td>
										<td><fmt:formatDate pattern="yyyy-MM-dd HH시 mm분" value="${comment.comment_time}" /></td>
										<td>
											<form action="delco" method="post">
												<input type="hidden" name="comment_id"
													value="${comment.comment_id}">
												<button type="submit">del</button>
											</form>
										</td>
										<td><button
												onclick="modco('${comment.comment_contents}',${comment.comment_id})">mod</button></td>
									</tr>
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="3">비밀글입니다.</td>
									</tr>
								</c:otherwise>
								
							</c:choose>
						</c:when>
					</c:choose>
<!-- 대댓글 zone -->
					<c:forEach var="cocomment" items="${cocomment[status.index]}">
						<c:choose>
							<c:when test="${cocomment.cocomment_status==2}">
								<tr>
									<td colspan=3>ㄴ삭제된 글입니다.</td>
								</tr>
							</c:when>
							<c:when test="${cocomment.cocomment_status==0}">
								<c:choose>
									<c:when ${comment.comment_access==0 or sessionScope.user.user_id==comment.comment_id_to or sessionScope.user.user_id==comment.comment_id_from}>
										<tr>
											<td colspan="2" id="cocomment_modify_zone${cocomment.cocomment_id}">ㄴ ${cocomment.cocomment_id_from} <em>${cocomment.cocomment_contents}</em>
											</td><td>	
												<fmt:formatDate pattern="yyyy-MM-dd HH시 mm분" value="${cocomment.cocomment_time}"/>
												<form action="delcoco" method="post"
													style="display: inline-block;">
													<input type="hidden" name="cocomment_id"
														value="${cocomment.cocomment_id}">
													<button style="background-color: red;" type="submit">del</button>
												</form>
												<button style="background-color: red;"
												onclick="modcoco('${cocomment.cocomment_contents}',${cocomment.cocomment_id})">mod</button>
											</td>
										</tr>
									</c:when>
									<c:otherwise>
										<tr>
											<td colspan="3">비밀글입니다.</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</c:when>
						</c:choose>
					</c:forEach>
				</c:forEach>
			</table>
		</div>
		<form id="inputcocomment" role="form" action="cocomment" method="post">
			<input id="commentid" type="hidden" name=comment_id
				value="${cocoment.coment_id}"> <input type="hidden"
				name=cocomment_id_from value="${sessionScope.user.user_id}">
			<input type="text" name=cocomment_contents placeholder="대댓글내용 입력">
			<input type="submit" value="대댓글입력">
		</form>
		<form role="form" action="comment" method="post">
			<input type="hidden" name=comment_id_to value="${user.user_id}">
			<input type="hidden" name=comment_id_from
				value="${sessionScope.user.user_id}"> <input type="text"
				name=comment_contents placeholder="댓글내용 입력"> <label>비밀글</label>
			<input type="checkbox" value="1" name=comment_access> <input
				type="submit" value="댓글입력">
		</form>
	</div>

	<a href="http://localhost:8080/main">main으로</a>
</body>
<script>
	console.log
	function setcocommentname(commentid){
		document.getElementById("inputcocomment").style.cssText = "display:inline-block;"
		document.getElementById("commentid").value=commentid
	}
	function modco(contents, id){
		var str="<form action='modico' method='post'><input type='text' value='"+contents+"' name='comment_contents'><input type='hidden' name='comment_id' value='"+id+"'><button type='submit'>확인</button></form>";
		document.getElementById("contents_modify_zone"+id).innerHTML=str;
	}
	function modcoco(contents, id){
		var str="<form action='modicoco' method='post'><input type='text' value='"+contents+"' name='cocomment_contents'><input type='hidden' name='cocomment_id' value='"+id+"'><button type='submit'>확인</button></form>";
		document.getElementById("cocomment_modify_zone"+id).innerHTML=str;
	}
/*
	function test(time){
		alert(time.getClass);
		console.log(time.getClass);
	}
 	function modco(id){
 		document.getElementById("contents_modify_zone").innerHTML="<input type="text"></input>"		
 	}
*/
</script>

</html>