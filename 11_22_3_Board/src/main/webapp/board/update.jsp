<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="BoardUpdateController" name="insertForm" method="post">
		<table class="updateForm" border="1" align="center">
			<tr>
				<td style="font-size: 60px"><input class="UpdateInput" name="b_no" value="${b_no}" readonly="readonly"></input>번 게시글 수정하기</td>
			</tr>
			<tr>
				<td><textarea id="updateTextarea" name="b_text" placeholder="내용" autofocus="autofocus" maxlength="200">${b_text}</textarea></td>
			</tr>
			<tr>
				<td><button class="BoardUpdateButton">&nbsp;&nbsp;수정&nbsp;&nbsp;</button></td>
			</tr>
		</table>
		<table align="center">
			<tr>
				<td><button class="BoardUpdateButton" onclick="goBoard();">&nbsp;&nbsp;돌아가기&nbsp;&nbsp;</button></td>
			</tr>
		</table>
	</form>
</body>
</html>