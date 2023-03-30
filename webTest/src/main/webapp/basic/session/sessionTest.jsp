<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
// JSP문서에서는 Session정보가 'session'이라는 이름으로 자동으로 저장되어 있다.
// 그래서 HttpSession session = request.getSession(); 이 명령을 사용할 필요가 없다.
// request 와 reponse 객체가 저장되어있는 거랑 같음
%>
<h3>이름 : <%=session.getAttribute("userName") %></h3>
<a href="<%=request.getContextPath() %>/sessionAdd.do">Session 정보 저장하기</a><br><br>
<a href="<%=request.getContextPath() %>/sessionRead.do">Session 정보 확인하기</a><br><br>
<a href="<%=request.getContextPath() %>/sessionDel.do">Session 정보 삭제하기</a><br><br>

</body>
</html>