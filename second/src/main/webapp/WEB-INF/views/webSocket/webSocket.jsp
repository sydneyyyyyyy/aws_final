<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
#chatArea{background-color: yellow; border: 2px solid black}
</style>
</head>
<body>
별명 : <input type="text" id="nickname" value="${param.id }">
<input type="button" id="enterBtn" value="입장">
<input type="button" id="exitBtn" value="퇴장">
<h2>채팅영역</h2>
<div id="chatArea">채팅 내용 표시<br></div>
전송할 메시지 : <input type="text" id="message">
<input type="button" id="sendBtn" value="전송">

<script src="js/jquery-3.6.1.min.js"></script>
<script>
//java에서 static method 사용하는 것처럼 시작하자마자 실행되도록 작성
$(function (){
	let webSocket;
	$("#enterBtn").on('click',function(){
		webSocket = new WebSocket("ws://localhost:8085/ws");
		webSocket.onopen = open;
		webSocket.onclose = close;
		webSocket.onmessage = message;//웹소켓 서버가 전송해준 데이터 수신(서버가 응답한 내용을 받아옴)
	});

	$("#sendBtn").on('click',function(){
		let nickname = $("#nickname").val();
		let sendMsg = $("#message").val();
		webSocket.send(nickname + " : " + sendMsg);
	});//서버로 송신(보냄)
});

function open(){console.log("웹소켓 연결 성공");}
function close(){console.log("웹소켓 해제 성공");}
function message(event){
	console.log("서버로부터 메세지 수신 성공");
	$("#chatArea").append(event.data + "<br>");
	
	}

</script>

</body>
</html>