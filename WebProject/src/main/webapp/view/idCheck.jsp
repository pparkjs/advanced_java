<%@page import="memberVO.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	int res = (Integer)request.getAttribute("result");
	
	if(res == 0){
%>
		{ 
			"flag" : "사용 가능한 ID"
		} 
<%		
	}else{
%>
		{ 
			"flag" : "중복된 ID"
		} 
<%		
	}
%>

