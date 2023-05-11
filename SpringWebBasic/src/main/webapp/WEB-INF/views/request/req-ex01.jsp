<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>req-ex01.jsp 파일로 들어오기 성공!</h2>
	<p>우와</p>

	<form action="/basic/request/basic01">
		<input type="submit" value="GET 요청!">		
	</form>

	<form action="/basic/request/basic01" method="post">
		<input type="submit" value="POST 요청!">		
	</form>


</body>
</html>