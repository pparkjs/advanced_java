<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>forward, sendRedirect 연습</h2>

<form method="post" action="<%=request.getContextPath() %>/responseTest01.do">
	forward 연습 : <input type="text" name="username">
	<input type="submit" value="확인"> 
</form>

<br><hr><br>

<form method="post" action="<%=request.getContextPath() %>/responseTest02.do">
	Redirect 연습 : <input type="text" name="username">
	<input type="submit" value="확인"> 
</form>
</body>
</html>