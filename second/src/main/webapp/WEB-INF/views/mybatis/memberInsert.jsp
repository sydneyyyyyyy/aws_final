<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- html form (파라미터 이름) == MeberDTO(프로퍼티이름)== Member테이블(컬럼이름) 같은이름으로 저장 -->

<form action="<%=request.getContextPath() %>/memberInsert" method="post" enctype="multipart/form-data">
아이디<input type=text name="id" value=""><br>
암호<input type=password name="pw"><br>
이름<input type=text name="name"><br>
폰<input type=text name="phone" pattern="010[0-9]{4}[0-9]{4}"><br>
이메일<input type=email name="email"><br>
주소<input type=text name="address"><br>
사진 업로드<input type=file id="imageFile" name="imageFile"><br>
<input type=submit value="회원가입">

</form>
</body>
</html>