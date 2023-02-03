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
���� : <input type="text" id="nickname" value="${param.id }">
<input type="button" id="enterBtn" value="����">
<input type="button" id="exitBtn" value="����">
<h2>ä�ÿ���</h2>
<div id="chatArea">ä�� ���� ǥ��<br></div>
������ �޽��� : <input type="text" id="message">
<input type="button" id="sendBtn" value="����">

<script src="js/jquery-3.6.1.min.js"></script>
<script>
//java���� static method ����ϴ� ��ó�� �������ڸ��� ����ǵ��� �ۼ�
$(function (){
	let webSocket;
	$("#enterBtn").on('click',function(){
		webSocket = new WebSocket("ws://localhost:8085/ws");
		webSocket.onopen = open;
		webSocket.onclose = close;
		webSocket.onmessage = message;//������ ������ �������� ������ ����(������ ������ ������ �޾ƿ�)
	});

	$("#sendBtn").on('click',function(){
		let nickname = $("#nickname").val();
		let sendMsg = $("#message").val();
		webSocket.send(nickname + " : " + sendMsg);
	});//������ �۽�(����)
});

function open(){console.log("������ ���� ����");}
function close(){console.log("������ ���� ����");}
function message(event){
	console.log("�����κ��� �޼��� ���� ����");
	$("#chatArea").append(event.data + "<br>");
	
	}

</script>

</body>
</html>