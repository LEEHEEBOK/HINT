<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member update</title>
<script>
function update() {
	var passwordConfirm = document.getElementById('pwd').value; 
	var password = '${member.mpassword}'; 
	if(password == passwordConfirm){
		updateform.submit();
	}else{
		alert('비밀번호가 틀립니다!!!')
	}
}
</script>


</head>
<body>
<h1>Member Update</h1>

<form action="updateprocess" method="post" name ="updateform">
	아이디 : <input type="text" name = "mid" value="${member.mid}" readonly> 변경 불가 <br>
	비밀번호 : <input type="text" placeholder="${member.mpassword}" name="mpassword" id="pwd"> <br>
	이름 : <input type="text" name = "mname" value="${member.mname}" readonly> 변경 불가 <br>
	전화번호 : <input type="text" name = "mphon" placeholder="${member.mphon}"> <br>
	이메일 : <input type="text" name = "memail" placeholder="${member.memail}" > <br>
	<input type="button" value="수정" onclick="update()"> <br>
</form>

</body>
</html>