<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/resources/js/jquery-3.6.1.min.js"></script>
<script>
$(document).ready(function(){
	$("#form").on('submit',function(e){
		if($("#pw").val() != $("#pw2").val()){
			$("#result").html("<h3>암호가 일치하지 않습니다.</h3>");
			e.preventDefault();
		}
	});
	
});
</script>
</head>
<body>
<h3>정보 조회</h3>
<form id="form" action="<%=request.getContextPath() %>/memberUpdate" method="post">
	아이디<input type=text name="id" value="${memberInfo.id}" readonly><br>
	암호<input type=password name="pw" id="pw" ><br>
	암호 확인<input type=password name="pw2" id="pw2"><br>
	이름<input type=text name="name" value="${memberInfo.name}"><br>
	폰<input type=text name="phone" pattern="010[0-9]{8}" value="${memberInfo.phone}"><br>
	주소<input type=text name="address" value="${memberInfo.address}"><br>
	이메일<input type=email name="email" value="${memberInfo.email}"><br>
	<input type=submit value="정보수정">
</form>
<div id="result"></div>

</body>
</html>