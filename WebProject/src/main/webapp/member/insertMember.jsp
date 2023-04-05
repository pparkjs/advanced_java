<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.6.4.min.js"></script>
<script src="../js/jquery.serializejson.min.js"></script>

<style>
	.end{
		border: none;
		display : flex;
		justify-content: center;
		align-items: center;
	}
</style>
<script>
$(function(){
	
	idCnt = 0; // 중복 확인용 변수 선언
	pwCnt = 0; // 비밀번호 확인용 변수 선언
	
	// 회원목록으로 돌아가는 함수
	$('.memList').on("click", function(){
		location.href = "<%=request.getContextPath()%>/memberList.do";
	})
	
	// id 중복 체크하는 함수
	$('#checkId').on("click", function(){
		
		idVal = $('#memId').val().trim();
		
		$.ajax({
			url : '<%=request.getContextPath()%>/memberIdCheck.do',
			type : 'get',
			data : {'mem_id' : idVal},
			success : function(res){
				
				if(res.flag == "사용 가능한 ID"){
					idCnt = 1;
					$('.idState').html(res.flag).css("color", "blue");
				}else{
					idCnt = 0;
					$('.idState').html(res.flag).css("color", "red");
				}
			},
			error : function(xhr){
				alert("상태 :" + xhr.status);
			},
			dataType : 'json'
		})
	})
	
	// 비밀번호 확인하는 함수
	$('#checkPw').on('keyup', function(){
		pw = $('#memPw').val();
		chPw = $('#checkPw').val();
		
		if(pw != chPw){
			pwCnt = 0;
			$('.pwState').html("비밀번호가 다릅니다.").css("color", "red");
		}else{
			pwCnt = 1;
			$('.pwState').html("비밀번호 확인 완료").css("color", "blue");
		}
	})
	
	
	// 저장 버튼 누를시 DB에 저장되는 함수
	$('.save').on("click", function(){
		
		id = $('#memId').val();
		pw = $('#memPw').val();
		name = $('#memName').val();
		tel = $('#memTel').val();
		addr = $('#memAddr').val();
		photo = $('#memimage').val();
		
		if(idCnt == 1 && pwCnt == 1 && id != null &&
			pw != null && name != null && tel != null){
			$('form').submit();
		}else {
			alert("정보를 다시 입력하세요.")
		}
		
	})
	
})
</script>
</head>
<body>

<h3>회원 정보 입력 폼</h3>
<form action="<%=request.getContextPath()%>/insertMember.do" method="post"
		enctype="multipart/form-data">
	<table border="1">
		<tr>
			<td>회원ID</td>
			<td>
				<input type="text" name="mem_id" id="memId">
				<input type="button" id="checkId" value="중복확인">
				<span class="idState"></span>			
			</td>
		</tr>
		
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="mem_pass" id="memPw"></td>
		</tr>
		
		<tr>
			<td>비밀번호 확인</td>
			<td>
				<input type="password" id="checkPw">
				<span class="pwState"></span>	
			</td>
		</tr>
		
		<tr>
			<td>회원이름</td>
			<td><input type="text" name="mem_name" id="memName"></td>
		</tr>
		
		<tr>
			<td>전화번호</td>
			<td><input type="text" name="mem_tel" id="memTel"></td>
		</tr>
		
		<tr>
			<td>회원주소</td>
			<td><input type="text" name="mem_addr" id="memAddr"></td>
		</tr>
		
		<tr>
			<td>프로필 사진</td>
			<td><input type="file" name="mem_photo" id="memimage"></td>
		</tr>
		
		<tr>
			<td class="end" colspan="2">
				<div class="endBtn">
					<input type="button" class="save" value="저장">
					<input type="reset" value="취소">
					<input type="button" class="memList" value="회원목록">
				</div>
			</td>
		</tr>
	
	</table>
</form>
</body>
</html>