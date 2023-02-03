<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.6.1.min.js"></script><!-- src/main/resources/static/ 생략 -->
<script>
$(document).ready(function(){
	$("a").on('click',function(event){
		//기본 동작 이벤트 처리 내장 태그
		event.preventDefault();
		//alert($(this).attr("id"));
		$.ajax({
			url:'/otherMemberInform',
			data:{'id':$(this).attr("id")},
			type: 'get',
			dataType: 'json',
			success: function(response){
				$("#inform").html(response.id + "<br>");
				$("#inform").append(response.name + "<br>");
				$("#inform").append(response.address + "<br>");
				//if() null 처리하기
				$("#inform").append("<img src ='/upload/" + response.image + "'><br>");
			}
		});
		
	}); 
});
</script>
</head>
<body>
	<h3>전체 회원정보 조회</h3>
	<c:forEach items="${memberList}" var="member">
		${member.id } :${member.pw }:<a id="${member.id }" href="otherMemberInform?id=${member.id }">${member.name }</a>:${member.address }<br>
	</c:forEach>
	
	<h1 id="inform"></h1>
</body>
</html>