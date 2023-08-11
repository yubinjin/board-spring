<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>removeForm</title>
</head>
<body>
<c:import url="/header"/>
<div align="center">
	<table>
	<tr><td>
		<form action="removeProc" method="post" >
			<input type="hidden" name="bn" value="${bn }" />
			<input type="hidden" name="fileName" value="${fileName }" />
			<input type="password" name="pw" placeholder="비밀번호"><br>
			<input type="password" name="confirm" placeholder="비밀번호 확인" ><br>
			<input type="submit" value="게시글 삭제"><br>
		</form>
	</td></tr>
	</table>
</div>
<c:import url="/footer"/>
</body>
</html>
