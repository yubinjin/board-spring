<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ex2</title>
<script>
	var req;
	function send(){
		var sendData = document.getElementById('sendData')
		req = new XMLHttpRequest();
		req.onreadystatechange = change;
		req.open('post', 'ex2');
		req.send(sendData.value);
	}
	function change(){
		if(req.readyState == 4 && req.status == 200){
			var print = document.getElementById('print')
			print.innerHTML = req.responseText;
		}
	}
</script>
</head>
<body>
	<input type="text" 
	id="sendData" onchange="send()"/>
<!-- 	<button type="button" onclick="send()">
		서버로 요청
	</button> -->
	<br>
	<span id="print"></span>
</body>
</html>


