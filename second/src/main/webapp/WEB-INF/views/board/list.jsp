<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>${param.page } 페이지의 게시물 출력</h2>
<table border="2">
<tr><th>번호</th><th>제목</th><th>작성자</th></tr>

<c:forEach items ="${list }" var ="board">
<tr><td>${board.seq }</td><td><a href="oneBoard?seq=${board.seq }">${board.title }</a></td><td>${board.writer }</td></tr>
</c:forEach>
</table>

<%int totalCount = (Integer)request.getAttribute("totalCount");
int totalPage=0;
if(totalCount % 3 == 0){
	totalPage = totalCount / 3;
}else{
	totalPage = totalCount / 3 + 1;
}
for(int i = 1;i <=totalPage; i++){
%>	
<a href="boardList?page=<%= i %>"><%= i %>페이지</a>

<% } %>
<input type="button" id="writeBtn" value="글쓰기">
<script src="js/jquery-3.6.1.min.js"></script>
<script>
$("#writeBtn").on('click',function(){
	location.href="insertBoard";//get 방식
});
</script>



</body>
</html>