<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table align="center">
		<tr>
			<td colspan="1">
				<img class="welcomeImg" src="image/${sessionScope.loginMember.m_photo }">
			</td>
			<td align="center" colspan="1">
				${sessionScope.loginMember.m_name }님<br>
				어서오세요.<br>
				<button class="loginButton" onClick="myInfo();">내정보</button>
				<button class="loginButton" onClick="logOut();">로그아웃</button><br>

			</td>
		</tr>
	</table>
</body>
</html>