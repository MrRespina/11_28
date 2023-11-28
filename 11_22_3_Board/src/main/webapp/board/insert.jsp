<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="BoardWriteController" name="insertForm" onsubmit=""
		method="post">
		<table class="memberInsertTable" border="1" align="center">
			<tr>
				<td><input type="hidden" name="b_no" value="${b_no}"></input></td>
			</tr>
			<tr>
				<td style="font-size: 60px">글쓰기</td>
			</tr>
			<tr>
				<td><textarea id="boardTextarea" name="b_text" placeholder="내용"
						autofocus="autofocus" maxlength="200"></textarea></td>
			</tr>
			<tr>
				<td><button class="BoardInputButton">&nbsp;&nbsp;확인&nbsp;&nbsp;</button></td>
			</tr>
		</table>
	</form>
	<table align="center">
		<tr>
			<td><button class="BoardInputButton" onclick="goBoard();">&nbsp;&nbsp;돌아가기&nbsp;&nbsp;</button></td>
		</tr>
	</table>

</body>
</html>