<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
table, tr, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}
</style>


</head>
<body>
	<h1>Board Page</h1>
	로그인 아이디: ${sessionScope.loginMember}
	<br>
	<table>
		<tr>
			<th>글번호</th>
			<th>작성자</th>
			<th>제목</th>
		</tr>
		<c:forEach var="board" items="${boardList}">
			<tr>
				<td>${board.bnumber}</td>
				<td>${board.bwriter}</td>
				<td><a
					href="boardview?bnumber=${board.bnumber}&page=${paging.page}">${board.btitle}</a></td>
			</tr>
		</c:forEach>
	</table>


	<!-- if else -->
	<c:choose>
		<c:when test="${paging.page<=1}">
			[이전]&nbsp;
		</c:when>
		<c:otherwise>
			<a href="paging?page=${paging.page-1}">[이전]</a>&nbsp;
		</c:otherwise>
	</c:choose>

	<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i"
		step="1">
		<c:choose>
			<c:when test="${i eq paging.page}">
				${i}
			</c:when>
			<c:otherwise>
				<a href="paging?page=${i}">${i}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>

	<c:choose>
		<c:when test="${paging.page>=paging.maxPage}">
			[다음]
		</c:when>
		<c:otherwise>
			<a href="paging?page=${paging.page+1}">[다음]</a>
		</c:otherwise>
	</c:choose>
	<br>

	<!-- 검색기능 -->
	<form action="search" method="get">
		<select name="searchtype">
			<option value="btitle">제목</option>
			<option value="bwriter">작성자</option>
		</select>
			<input type="text" name="keyword" placeholder="검색어입력">
			<input type="submit" value="검색">
	</form>
	
	<a href="boardWritePage">글쓰기화면</a>
	<br>
	<a href="board_home">보드 홈으로</a>
</body>
</html>