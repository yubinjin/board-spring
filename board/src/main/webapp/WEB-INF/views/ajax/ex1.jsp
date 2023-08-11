<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ex1</title>
<script>
	var req;
	function send(){
		req = new XMLHttpRequest();
		req.onreadystatechange = change;
		req.open('post', 'ex1');
		req.send(null);
	}
	function change(){
		//console.log('상태가 변했다.')
		if(req.readyState == 4 && req.status == 200){
			console.log('상태가 4번이다.')
			var print = document.getElementById('print')
			print.innerHTML = req.responseText;
		}
	}
</script>
</head>
<body>
	<div id="print">여기에 응답 데이터를 출력할거야</div>
	<button type="button" onclick="send()">
		서버에게 요청하기
	</button>
</body>
</html>


