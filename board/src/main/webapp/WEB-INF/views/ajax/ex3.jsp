<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ex3</title>
<script>
	var req;
	function send(){
		var i = document.getElementById('id').value
		var p = document.getElementById('pw').value
		var c = document.getElementById('confirm').value
		var sendData = {id:i, pw:p, confirm:c} //JSON 자료형
		console.log(sendData)
		// JSON 자료형의 데이터를 문자열로 형반환 해서 네트워크로 전달.
		sendData = JSON.stringify(sendData);
		req = new XMLHttpRequest();
		req.onreadystatechange = change;
		req.open('post', 'ex3');
		req.setRequestHeader('Content-Type', 'application/json;charset=UTF-8')
		req.send(sendData);
	}
	function change(){
		if(req.readyState == 4 && req.status == 200){
			var printId = document.getElementById('printId')
			var printPw = document.getElementById('printPw')
			var printConfirm = document.getElementById('printConfirm')
			//JSON 형태의 문자열 자료형 데이터를 JSON자료형으로 형변환
			var resData = JSON.parse(req.responseText);
			printId.innerHTML = resData.id
			printPw.innerHTML = resData.pw
			printConfirm.innerHTML = resData.confirm
		}
	}
</script>
</head>
<body>
	<input type="text" id="id"/><br>
	<span id="printId"></span><br>
	
	<input type="password" id="pw"/><br>
	<span id="printPw"></span><br>
	
	<input type="password" id="confirm"/><br>
	<span id="printConfirm"></span><br>
	
 	<button type="button" onclick="send()">
		로그인
	</button> 
	<br>
</body>
</html>


