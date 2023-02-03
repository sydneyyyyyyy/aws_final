<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h1> ${otherInform.name } 님의 정보입니다. (관리자만 열람 할 수 있게 변경할 것)</h1>
<h3>아이디 : ${otherInform.id } </h3>
<h3>이메일 : ${otherInform.email } </h3>
<h3>폰번호 : ${otherInform.phone } </h3>
<h3>주소 : ${otherInform.address } </h3>
<h3>사진 : <img src ="/upload/${otherInform.image }"> </h3>

<script>
alert('${updateResult }');
</script>
</body>
</html>