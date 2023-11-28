<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="UTF-8">
<title>signUp</title>
</head>
<body>
	<form action="SignUpController" name="signUpForm" onsubmit="return checkSignUp();" method="post" enctype="multipart/form-data">
		<table class="loginTable" border="1" align="center">
			<tr>
				<td colspan="2">회원가입</td>
			</tr>
			<tr class="loginTr">
				<td>ID</td>
				<td><input class="signUpInput" name="m_id" placeholder="아이디 입력"
					maxlength="10"></td>
			</tr>
			<tr class="loginTr">
				<td>PW</td>
				<td><input class="signUpInput" name="m_pw"
					placeholder="패스워드 입력" type="password" maxlength="12"></td>
			</tr>
			<tr class="loginTr">
				<td>PW 확인</td>
				<td><input class="signUpInput" name="m_pw_check"
					placeholder="패스워드 확인" type="password" maxlength="12"></td>
			</tr>
			<tr class="loginTr">
				<td>이름</td>
				<td><input class="signUpInput" name="m_name"
					placeholder="이름 입력" maxlength="10"></td>
			</tr>
			<tr class="loginTr">
				<td>전화번호</td>
				<td><input class="signUpInput" name="m_phone"
					placeholder="???-????-????" maxlength="13"></td>
			</tr>
			<tr class="loginTr">
				<td>생년월일</td>
				<td><select name="m_y" class="select">
						<c:forEach var="y" begin="${cy - 100 }" end="${cy }">
							<option>${y }</option>
						</c:forEach>
				</select>년&nbsp;&nbsp; <select name="m_m" class="select">
						<c:forEach var="m" begin="1" end="12">
							<option>${m }</option>
						</c:forEach>
				</select>월&nbsp;&nbsp; <select name="m_d" class="select">
						<c:forEach var="d" begin="1" end="31">
							<option>${d }</option>
						</c:forEach>
				</select>일</td>
			</tr>
			<tr class="loginTr">
				<td>사진</td>
				<td><input name="m_photo" type="file" class="signUpInputButton"></td>
			</tr>
			<tr align="center">
				<td colspan="2"><button class="loginButton">회원가입</button>
			</tr>
		</table>
	</form>
</body>
</html>