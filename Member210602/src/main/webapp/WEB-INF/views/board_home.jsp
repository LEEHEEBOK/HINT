<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board Home</title>
</head>
<body>
	<h1>Board Home</h1>
	로그인 아이디: ${sessionScope.loginMember}
	<br>
	<a href="mypage?mid=${sessionScope.loginMember}">회원 메인 페이지</a>
	<br>
	<a href="boardWritePage">글쓰기</a>
	<br>
	<a href="paging?page=${page}">페이징 목록</a>
	<br>
	<a href="./">홈으로</a>
	<br>
</body>
</html>