<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board UpDate</title>

<script>
	function update() {
	var update = confirm('수정 하시겠습니다?');
	if (update) {
		console.log('수정할 번호' + ${board.bnumber})
		location.href='boardupdateprocess?bnumber='+${board.bnumber};
		alert('수정 되었습니다');
		} else {
		alert('수정 취소');
		}
	}

</script>


</head>
<body>
<h1>Board UpDate</h1>

	<form action="boardupdateprocess" method="post" enctype="multipart/form-data">
		수정 하는 번호 : <input type="text" name="bnumber" value="${board.bnumber}" readonly> <br>
		글 제목 : <input type="text" name="btitle"> <br>
		작성자  : <input type="text" name="bwriter" value="${sessionScope.loginMember}" readonly> <br>
		내용 : <textarea rows="5" cols="5" name="bcontents"></textarea> <br>
		파일 : <input type="file" name="bfile"> <br>
				<input type="button" value="수정" onclick="update()">		
	</form>

<br><a href="paging?page=${page}">페이징 목록</a>
<br><a href="board_home">보드 홈으로</a>
</body>
</html>