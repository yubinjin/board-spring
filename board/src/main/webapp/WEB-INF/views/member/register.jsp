<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>register</title>
<c:url var="root" value="/" />
<script src="${root }/resources/check.js"></script>
<script>
	var req;
	function send(){
		var sendData = document.getElementById('id')
		req = new XMLHttpRequest();
		req.onreadystatechange = change;
		req.open('post', 'overlap');
		req.send(sendData.value);
	}
	function change(){
		if(req.readyState == 4 && req.status == 200){
			var print = document.getElementById('msg')
			print.innerHTML = req.responseText;
		}
	}
</script>
</head>
<body>
	<c:import url="/header" />
	<div align="center">
		<h1>회원 등록</h1>
		<table>
		<tr><td>
			<form action="register" method="post" id="f">
				<input type="text" name="id" placeholder="아이디" id="id"> 
				<button type="button" onclick="send()">중복 확인</button>
				 <br>
				<input type="password" name="pw" placeholder="비밀번호" id="pw"><br>
				<input type="password" name="confirm" placeholder="비밀번호 확인 " id="confirm" onchange="pwCheck()">
				<label id="label">(*필수 체크)</label><br>
				<input type="text" name="username" id="username" placeholder="이름" ><br>
				<input type="text" name="address" placeholder="주소" ><br>
				<input type="text" name="mobile" placeholder="전화번호" ><br>
				<input type="button" value="회원가입" onclick="allCheck()"><br>
			</form>
		</td></tr>
		</table>
	</div>
	<c:import url="/footer" />
</body>
</html>