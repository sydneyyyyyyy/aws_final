<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="">
	<table border="2">
	<tr><th>번호</th><td><input type="text" value="${oneBoard.seq }" readonly></td></tr>
	<tr><th>제목</th><td><input type="text" value="${oneBoard.title }"></td></tr>
	<tr><th>내용</th><td><textarea cols=50 rows=5 >${oneBoard.contents }</textarea></td></tr>
	<tr><th>작성자</th><td><input type="text" value="${oneBoard.writer }" readonly></td></tr>
	<tr><th>조회수</th><td><input type="text" value="${oneBoard.viewcount }" readonly></td></tr>
	<tr><th>작성시간</th><td><input type="text" value="${oneBoard.writingtime }" readonly></td></tr>
	
	<tr><td><input type="submit" value="수정"></td><td><input type="submit" value="삭제"></td></tr>
	</table>
</form>
</body>
</html>