<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h1> ${otherInform.name } ���� �����Դϴ�. (�����ڸ� ���� �� �� �ְ� ������ ��)</h1>
<h3>���̵� : ${otherInform.id } </h3>
<h3>�̸��� : ${otherInform.email } </h3>
<h3>����ȣ : ${otherInform.phone } </h3>
<h3>�ּ� : ${otherInform.address } </h3>
<h3>���� : <img src ="/upload/${otherInform.image }"> </h3>

<script>
alert('${updateResult }');
</script>
</body>
</html>