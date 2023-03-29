<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>request 연습</h3>
<!-- 
	- 아래의 <form>태그에서 구성한 데이터를 이용하여 계산된 결과를 출력하는 웹프로그램을 작성하시오.
		(숫자 2개 입력, 연산자 선택 후 전송한다.)
		(처리할 서블릿의 URL패턴 : /requestTest02.do )
		
	- 숫자 입력은 정수형으로 입력한다.
	
	- 결과 예시
	10 / 4 = 2.5
	 
 -->
<hr>
<form action="<%=request.getContextPath()%>/requestTest02.do">
	<table>
		<tr>
			<td><input type="text" size="10" name="num1"></td>
			<td>
				<select name="cal">
					<option value="+"> + </option>
					<option value="-"> - </option>
					<option value="*"> * </option>
					<option value="/"> / </option>
					<option value="%"> % </option>
				</select>
			</td>
			<td><input type="text" size="10" name="num2"></td>
			<td><input type="submit" value="계산하기"></td>
		</tr>
	
	</table>
</form>
</body>
</html>