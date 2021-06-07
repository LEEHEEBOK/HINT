<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberview</title>
</head>
<body>
<h1>Member View</h1>
로그인 아이디: ${sessionScope.loginMember} <br>
아이디 : ${member.mid} <br>
비밀번호 : ${member.mpassword} <br>
이름 : ${member.mname} <br>
전화번호 : ${member.mphon} <br>
이메일 : ${member.memail} <br>
프로필 이름 : ${member.mfilename} <br>
이미지: <img src="resources/upload/${member.mfilename}" height="200" width="200"> <br>

<a href="./">홈으로</a>
</body>
</html>