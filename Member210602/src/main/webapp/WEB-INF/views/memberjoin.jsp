<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MemberJoin</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<!-- 아이디 중복확인 -->
<script>
	function idoverlap() {
		var inputId = document.getElementById('mid').value;
		var checkResult = document.getElementById('checkresult');
		console.log(inputId);
		
		$.ajax({
			type : 'post',
			url : 'idcheck',
			data : {'mid' : inputId},
			dataType : 'text',
			success: function (result) {
				console.log('성공');
				console.log('리턴 값' + result);
				if(result =="ok"){
					checkResult.style.color = 'green';
					checkResult.innerHTML = '사용가능한 아이디 입니다.';
				}else {
					checkResult.style.color = 'red';
					checkResult.innerHTML = '이미 사용중인 아이디 입니다.';
				}
			},
			error : function () {
				console.log('실패');
			}
			
		});
		
	}
</script>


</head>
<body>
<h1>MemberJoin</h1>

<!-- 회원 가입 -->
	<form action="memberjoin" method="post" enctype="multipart/form-data"> <!-- 파일 올릴때는 enctype 사용 -->
		아이디 : <input type="text" name="mid" id ="mid" onkeyup="idoverlap()"> <br>
		<span id= "checkresult"></span> <br>
		비밀번호 : <input type="text" name="mpassword"> <br>
		이름 : <input type="text" name="mname"> <br>
		전화번호 : <input type="text" name="mphon"> <br>
		이메일 : <input type="text" name="memail"> <br>
		파일 : <input type="file" name="mfile"> <br>
			<input type="submit" value="회원가입">		
	</form>
	
	<a href="./">홈으로</a>
</body>
</html>