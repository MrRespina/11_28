<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login.jsp</title>
</head>
<body>

	<table class="loginTable" border="1" align="center">
		<form action="loginController" method="post" name="loginform" onsubmit="return loginCheck();">
			<tr class="loginTr">
				<td>ID</td>
				<td><input class="loginInput" name="l_id" placeholder="id 입력" value="${cookie.lastLoginId.value}"></td>
			</tr>
			<tr class="loginTr">
				<td>PW</td>
				<td><input class="loginInput" name="l_pw" placeholder="pw 입력"
					type="password"></td>
			</tr>
			<tr align="center">
				<td colspan="1"><button class="loginButton">로그인</button></td>
			</form>
				<td colspan="1"><button class="loginButton" onclick="goSignUp();">회원가입</button></td>
			</tr>
	</table>

</body>
</html>