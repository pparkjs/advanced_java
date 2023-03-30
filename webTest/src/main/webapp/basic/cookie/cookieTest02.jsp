<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>

</script>
</head>
<body>
	<a href="<%=request.getContextPath()%>/cookieCountServlet.do" id="upCnt">Cookie Count 증가하기</a>
	<a href="<%=request.getContextPath()%>/cookieCountDelServlet.do" id="delCnt">Cookie Count 초기화하기</a>
</body>
</html>

