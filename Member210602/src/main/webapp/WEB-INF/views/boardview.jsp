<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>
	//삭제
	function boardDelete() {
		var bdelete = confirm('글을 삭제 하시겠습니다?');
		if (bdelete) {
			console.log('삭제 할 글 번호' + ${board.bnumber});
			location.href='boarddelete?bnumber='+${board.bnumber};
			alert('삭제 되었습니다');
			} else {
			alert('삭제 취소');
	}
	}
		
	// 댓글 등록
	$(document).ready(function(){
		$("#cwrite-btn").click(function(){
			var cwriter = document.getElementById('cwriter').value;
			var ccontents = document.getElementById('ccontents').value;
			var cbnumber = '${board.bnumber}';
			console.log(cwriter);
			console.log(ccontents);
			console.log(cbnumber);
	//댓글 내용
			$.ajax({
				type: 'post',
				url: 'comment/commentwrite',
				data:{
					'cwriter': cwriter,
					'ccontents': ccontents,
					'cbnumber': cbnumber},
				dataType: 'json',
				success: function(list){
					console.log(list);
					var output = "<table border='1'>";
					output += "<tr><th>작성자</th>";
					output += "<th>내용</th></tr>";
					for(var i in list){
						output += "<tr>";
						output += "<td>"+list[i].cwriter+"</td>";
						output += "<td>"+list[i].ccontents+"</td>";
						output += "</tr>";
					}
					output += "</table>";
					document.getElementById('comment-list').innerHTML = output;
					document.getElementById('cwriter').value='';
					document.getElementById('ccontents').value='';
				},
				error: function(){
					console.log('문제있음.');
				}
			});
		});
	});

	// 댓글 삭제
	$("#commentDelete").click(function() {
		console.log('댓글삭제.');
		if(confirm('댓글을 삭제 하시겠습니까?')){
			$.ajax({
				type: 'delete',
				url: 'commentDelete',
				success: function(list){
					console.log('삭제 성공.');
					alert('삭제 되었습니다');
				},
				error: function(){
					console.log('삭제 실패.');
					alert('삭제하지 못했습니다.');
				}		
			});
		}
	});
	
</script>
</head>
<body>
	<h1>Board View</h1>
	로그인 아이디: ${sessionScope.loginMember}
	<br> 글 번호 : ${board.bnumber}
	<br> 글 제목 : ${board.btitle}
	<br> 작성자 : ${board.bwriter}
	<br> 내용 : ${board.bcontents}
	<br> 이미지 이름 : ${board.bfilename}
	<br> 이미지 :
	<img src="resources/upload/${board.bfilename}" height="200" width="200">
	<br>

	<!-- 작성자만 수정가능 -->
	<c:if test="${sessionScope.loginMember == board.bwriter}">
		아이디가 ${sessionScope.loginMember} 일 때만 실행 가능
		<a href="boardupdate?bnumber=${board.bnumber}">수정</a>
	</c:if>
	<br>
	<!-- 작성자와 관리자만 삭제 가능 -->
	<c:if test="${sessionScope.loginMember eq admin == board.bwriter}">
		관리자와 아이디가 ${sessionScope.loginMember} 일 때만 실행 가능
		<button onclick="boardDelete(${board.bnumber})">삭제</button>
		<br>
	</c:if>
	<br>

	<!-- 댓글 -->
	<div id="comment-write">
		작성자 : <input type="text" id="cwriter"
			value="${sessionScope.loginMember}" readonly> <br> 내용 :
		<input type="text" id="ccontents"> <br>
		<button id="cwrite-btn">댓글 등록</button>
	</div>

	<!-- 댓글 목록출력 -->
	<div id="comment-list">
		<table border="1">
			<tr>
				<th>작성자</th>
				<th>내용</th>
				<th>삭제</th>
			</tr>
			<c:forEach var="comment" items="${commentList}">
				<tr>
					<td>${comment.cwriter}</td>
					<td>${comment.ccontents}</td>
					<td><button id="commentDelete">삭제</button></td>
				</tr>
			</c:forEach>
		</table>
	</div>



	<a href="paging?page=${page}">페이징 목록</a>
	<br>
	<a href="board_home">보드 홈으로</a>
</body>
</html>