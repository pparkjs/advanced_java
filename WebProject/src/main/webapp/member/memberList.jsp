<%@page import="memberVO.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.6.4.min.js"></script>

<script>

  $(function(){
	  $('#addMem').on('click', function(){
		  location.href = "<%=request.getContextPath()%>/member/insertMember.jsp";
	  })
  })
  
</script>
</head>
<body>

<% 
	List<MemberVO> list = (List<MemberVO>)request.getAttribute("listVal");
%>

	<h3>회원 목록 보기</h3>
	<br><hr><br>
<%-- <a href="<%=request.getContextPath()%>/fileUpload.do"></a> --%>
	<table border="1">
		<tr>
			<td colspan="5">
				<input type="button" id="addMem" value="회원추가">
			</td>
		</tr>
		<tr>
			<th>ID</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>전화</th>	
			<th>주소</th>
		</tr>
			
<%   
	if(list == null || list.size()==0){
%>
	<tr>
		<th colspan="5">저장된 파일 목록이 없습니다.</th>
	</tr>

<% 	
	}else{
		for(MemberVO mem : list){
%>
			<!-- 여따 입력!! -->
			<tr>
				<td> <a href="<%=request.getContextPath()%>/memberInfo.do?memId=<%=mem.getMem_id()%>"><%=mem.getMem_id() %></a></td>
				<td> <%=mem.getMem_pass() %></td>
				<td> <%=mem.getMem_name() %></td>
				<td> <%=mem.getMem_tel() %></td>
				<td> <%=mem.getMem_addr() %></td>
			</tr>
<%

		}
		
%>
	
<% 	
	}
%>
	</table>
</body>
</html>