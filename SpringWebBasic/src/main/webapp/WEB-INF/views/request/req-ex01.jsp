<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>req-ex01.jsp ���Ϸ� ������ ����!</h2>
	<p>���</p>

	<form action="/basic/request/basic01">
		<input type="submit" value="GET ��û!">		
	</form>

	<form action="/basic/request/basic01" method="post">
		<input type="submit" value="POST ��û!">		
	</form>


</body>
</html>