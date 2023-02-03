<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>다운로드 목록</h3>
<%
String[] fileList = (String[])request.getAttribute("fileList");
for(String fileName : fileList){
	out.println("<h5><a href='fileDownload?oneFile=" + fileName +"'>" + fileName + "</a></h5>");
}
%>
<!-- 
a태그 클릭하면 다운로드 클릭되도록 만들기
<a href='fileDownload?파라미터명=파일명'>
 -->
</body>
</html>