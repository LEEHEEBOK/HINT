<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member manager</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<style>
		table, tr, td, th {
		border: 1px black solid;
		border-collapse: collapse;
		}
	</style>
	
	<script>
		//삭제
		function mdelete(id) {
			console.log('삭제할아이디'+id);
			location.href="memberdelete?mid="+id;
		}
		
		//ajax 상세 조회
		function memberViewAjax(mid) {
			console.log(mid);
			$.ajax({
				type : 'post',
				url : 'memberviewajax',
				data : {'mid' : mid},
				dataType : 'json',
				success : function (result) {
					console.log(result);
					var output = "<table>";
					output += "<tr><th>ID</th> <th>PASSWORD</th> <th>NAME</th> <th>PHONE</th>";
					output += "<th>EMAIL</th> <th>FILENAME</th></tr>";
					output += "<tr>";
					output += "<td>"+result.mid+"</td>";
					output += "<td>"+result.mpassword+"</td>";
					output += "<td>"+result.mname+"</td>";
					output += "<td>"+result.mphon+"</td>";
					output += "<td>"+result.memail+"</td>";
					output += "<td>"+result.mfilename+"</td>";
					output += "</tr>";
					output += "</table>";
					
					document.getElementById('memberviewajaxdiv').innerHTML = output; 
					
				},
				error: function () {
					console.log('문제발생');
				}
			});	
		}
		
		//로그아웃
		function logout() {
			location.href="logout";
		}
		
		// 회원 정보 수정 요청
		function memberUpdate(){
			location.href="memberupdate";
		}
	</script>
	
</head>
<body>
	<h1>Member Manager</h1>
	로그인 아이디: ${sessionScope.loginMember}
	<br>
	<table>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>조회</th>
			<th>조회(Ajax)</th>
			<th>삭제</th>
		</tr>
		<c:forEach var ="member" items="${memberList}">
		 	<tr>
		 		<td>${member.mid}</td>
		 		<td>${member.mname}</td>
		 		<td><a href="memberview?mid=${member.mid}">조회</a></td>
		 		<td><button onclick="memberViewAjax('${member.mid}')">조회(ajax)</button></td>
		 		<td><button onclick="mdelete('${member.mid}')">삭제</button></td>
			</tr>
		</c:forEach>
	</table>
	
	<!--관리자 수정 -->
		<button onclick="memberUpdate()">관리자 회원 수정</button>
	
	
	<!-- 로그아웃 -->
		<button onclick="logout()">로그아웃</button> <br>
	
	<a href="paging?page=${page}">페이징 목록</a>
	<h6> 아래 ajax 상세 조회 출력</h6>
		<div id="memberviewajaxdiv"></div>
	
	<a href="./">홈으로</a>
</body>
</html>