<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>삭제할 학생의 학번을 입력해주세요.</h2>

	<form action="<c:url value='/score/deleteOne'/>">
		학번 입력: <input type="text" name="stuId">
		<input type="submit" value="확인">
	</form>

</body>
</html>