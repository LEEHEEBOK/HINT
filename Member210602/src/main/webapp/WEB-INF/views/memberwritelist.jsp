<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
		table, tr, td, th {
		border: 1px black solid;
		border-collapse: collapse;
		}
	</style>
</head>
<body>
<h1>Member Write List</h1>
로그인 아이디: ${sessionScope.loginMember} <br>
	<table>
		<tr>
			<th>글 번호</th>
			<th>글 제목</th>
			<th>글 작성자</th>
		</tr>
		<c:forEach var="board" items="${boardList}">
		<tr>
			<td>${board.bnumber}</td>
			<td> <a href="boardview?bnumber=${board.bnumber}">${board.btitle }</a> </td>
			<td>${board.bwriter}</td>
		</tr>
		</c:forEach>
	</table>
	<a href="mypage?mid=${member.mid}">회원 메인 페이지</a>
	<a href="./">홈으로</a>
</body>
</html>