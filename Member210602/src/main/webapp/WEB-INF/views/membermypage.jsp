	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member My Page</title>
<script>

//로그아웃
	function logout() {
		location.href="logout";
	}
	
// 회원 정보 수정 요청
	function update(){
		console.log('회원수정');
		location.href="memberupdate";
	}
	
</script>
</head>
<body>
<h1>Member My Page</h1>
로그인 아이디: ${sessionScope.loginMember} <br>
<!-- 상세 조회 -->
	<a href="memberview?mid=${sessionScope.loginMember}">상세보기</a> <br>
<!-- 회원 정보 수정 요청 -->	
	<button onclick="update()">수정</button> <br>
	
<!-- 로그아웃 -->
	<button onclick="logout()">로그아웃</button> <br>

<!-- 내가 작성한 글 보기 -->
	<a href="memberwritelist?mid=${sessionScope.loginMember}">내가 작성한 글 보기</a> <br>
<br>
	<a href="board_home">보드 홈으로</a> <br>
<a href="./">홈으로</a>

</body>
</html>