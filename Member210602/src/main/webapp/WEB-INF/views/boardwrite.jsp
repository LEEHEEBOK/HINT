<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Board Write</h1>
로그인 아이디: ${sessionScope.loginMember} <br>
<!-- 글쓰기 -->
<form action="boardwrite" method="post" enctype="multipart/form-data">
	글 제목 : <input type="text" name="btitle"> <br>
	작성자  : <input type="text" name="bwriter" value="${sessionScope.loginMember}" readonly> <br>
	내용 : <textarea rows="5" cols="5" name="bcontents"></textarea> <br>
	파일 : <input type="file" name="bfile"> <br>
			<input type="submit" value="글 쓰기">		
</form>

 <br> <a href="board_home">보드 홈으로</a>
</body>
</html>