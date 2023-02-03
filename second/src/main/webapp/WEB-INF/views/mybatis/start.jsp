<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>나의 회원관리 프로그램</h1>
<h3>
	<ul>
		<li><a href="login">로그인</a></li><!-- httpSession 브라우저 정보 지속 -->
		<li><a href="logout" >로그아웃</a></li>
		<li><a href="<%=request.getContextPath() %>/mybatisMemberList">회원리스트(로그인x)</a></li>
		<li><a href="memberInsert">회원가입(로그인x)</a></li>
		<li><a href="memberInform">내 정보</a></li>
		<li><a href="memberDelete">회원탈퇴</a></li>
		<li><a href="boardList">게시판</a></li>
	</ul>
</h3>
<% if(session.getAttribute("loginId")!= null){
	out.println("<h1>" + session.getAttribute("loginId")+" 회원님 환영합니다. </h1>");
} else{
	out.println("<h1> 로그인 해주세요. </h1>");
} %>

<h3>${!empty updateResult? updateResult : ""}</h3>
</body>
</html>