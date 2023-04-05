<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>File Upload 연습</h2>
<!-- 
	파일 업로드용 <form>태그는 method가 반드시 post이어야 한다
	<form>태그에 enctype속성을 'multipart/form-data'라고 설정해야한다
 -->
<form action="<%=request.getContextPath()%>/fileUpload.do" method="post"
		enctype="multipart/form-data">
	작성자 이름 : <input type="text" name="username"><br><br>
	한 개의 파일 선택 : <input type="file" name="upFile1"><br><br>
	여러개의 파일 선택 : <input type="file" name="upFile2" multiple><br><br>
	
	<input type="submit" value="전 송">
</form>
<br><hr><br>


<a href="<%=request.getContextPath()%>/fileList.do">전체 파일 목록 보기</a>


</body>
</html>