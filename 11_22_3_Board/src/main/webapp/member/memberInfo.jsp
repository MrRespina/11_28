<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="loginTable" align="center">
	<form action="MemberInfoController" name="updateForm" method="post" enctype="multipart/form-data" onsubmit="return checkUpdate();">
		<tr class="loginTr">
			<td>ID</td><td align="center"><input class="signUpInput"  name="m_id" readonly="readonly" value="${sessionScope.loginMember.m_id }"></td>
		</tr>
		<tr class="loginTr">
			<td>PW</td><td align="center"><input  class="signUpInput" name="m_pw" placeholder="PassWord" 
			maxlength="12" type="password" value="${sessionScope.loginMember.m_pw }"></td>
		</tr>
		<tr class="loginTr">
			<td>PW 확인</td><td align="center"><input class="signUpInput" name="m_pw_check" placeholder="PassWordCheck" 
			maxlength="12" type="password" value="${sessionScope.loginMember.m_pw }"></td>
		</tr>
		<tr class="loginTr">
			<td>이름</td><td align="center"><input class="signUpInput" name="m_name"
					placeholder="이름 입력" maxlength="10" value="${sessionScope.loginMember.m_name }"></td>
		</tr>
		<tr class="loginTr">
			<td>전화번호</td><td align="center"><input name="m_phone" class="signUpInput" placeholder="???-????-????" maxlength="13" value="${sessionScope.loginMember.m_phone }"></td>
		</tr>
		<tr class="loginTr">
				<td>생년월일</td>
				<td><select name="m_y" class="select">
				<option>
					<fmt:formatDate value="${sessionScope.loginMember.m_birth}" pattern="yyyy"/>
				</option>
						<c:forEach var="y" begin="${cy - 100 }" end="${cy }">
							<option>${y }</option>
						</c:forEach>
				</select>
				년&nbsp;&nbsp; <select name="m_m" class="select">
				<option>
					<fmt:formatDate value="${sessionScope.loginMember.m_birth}" pattern="M"/>
				</option>
						<c:forEach var="m" begin="1" end="12">
							<option>${m }</option>
						</c:forEach>
				</select>월&nbsp;&nbsp; <select name="m_d" class="select">
				<option>
					<fmt:formatDate value="${sessionScope.loginMember.m_birth}" pattern="d"/>
				</option>
						<c:forEach var="d" begin="1" end="31">
							<option>${d }</option>
						</c:forEach>
				</select>일</td>
			</tr>
			<tr class="loginTr">
				<td align="center" colspan="2"><img src="image/${sessionScope.loginMember.m_photo}"></td>
			</tr>
			
			<tr class="loginTr">
				<td colspan="2"><input name="m_photo" type="file" class="signUpInputButton"></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><button class="loginButton">수정</button></td>
			</tr>
			</form>
			<tr class="loginTr">
				<td align="center" colspan="2">
				<button class="loginButton" onClick="infoDelete();">회원탈퇴</button>
				<button class="loginButton" onClick="goHome();">돌아가기</button></td></tr>
	</table>
</body>
</html>