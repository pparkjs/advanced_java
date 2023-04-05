<%@page import="vo.FileinfoVO"%>
<%@page import="java.util.List"%>
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
	// 컨트롤러(서블릿)에서 보내온 데이터 받기 
	List<FileinfoVO> fileList = (List<FileinfoVO>)request.getAttribute("fileList");

%>

<h3>전체 파일 목록</h3>
<br><hr><br>
<a href="<%=request.getContextPath()%>/fileUpload.do">파일 업로드</a>
<table border="1">
<thead>	
	<tr><th>번호</th><th>작성자</th><th>저장 파일명</th><th>원래 파일명</th>	
	<th>파일 크기</th><th>날 짜</th><th>비 고</th></tr>
</thead>

<tbody>
<%
	if(fileList == null || fileList.size()==0){
%>
	<tr><td colspan="7"> 저장된 파일 목록이 없습니다.</td></tr>
<%
	}else {
		for(FileinfoVO fvo : fileList){
%>

	<tr>
		<td><%= fvo.getFile_no()%></td>
		<td><%= fvo.getFile_writer()%></td>
		<td><%= fvo.getSave_file_name()%></td>
		<td><%= fvo.getOrigin_file_name()%></td>
		<td><%= fvo.getFile_size()%></td>
		<td><%= fvo.getFile_date()%></td>
		<td><a href="<%=request.getContextPath()%>/fileDownload.do?fileno=<%=fvo.getFile_no()%>">Download</a></td>
	</tr>
<%
		}
	}
%>	
</tbody>

</table>
</body>
</html>