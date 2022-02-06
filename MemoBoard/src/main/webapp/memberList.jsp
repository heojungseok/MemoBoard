<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>멤버 목록</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
	<form action="member_list" method="get">
	<input type="hidden" name="mode" value="0">
	<table border="1" >
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>이름</th>
			<th>나이</th>
			<th>이메일</th>
			<th width='250'>주소</th>
			<th>상태</th>
			<th>가입일</th>			
			<th>블랙</th>
			<th>탈퇴</th>
		</tr>
		<c:forEach var="mem" items="${memberList}" varStatus="vs">
		<tr>
			<td>		
				${mem.midx}
			</td>
			<td>		
				${mem.userid}
			</td>
			<td>		
				${mem.username}
			</td>
			<td>		
				${mem.age}
			</td>
			<td>		
				${mem.email}
			</td>
			<td>		
				${mem.address}
			</td>
			<td>		
				${mem.stat}
			</td>
			<td>		
				<fmt:formatDate pattern="yyyy-MM-dd" value="${mem.wdate}"/>
			</td>
			<td>		
				<input type="checkbox" name="blacks" value="${mem.midx}">
			</td>
			<td>		
				<input type="checkbox" name="bans" value="${mem.midx}">
			</td>
		</tr>
		</c:forEach>		
	</table>
	<input type="submit" name="modeBtn" value="블랙등록">
	<input type="submit" name="modeBtn" value="탈퇴등록">
	</form>
	<c:set var="num" value="${param.p == null ? 1: param.p}"/>		
		<c:set var="sp" value="${num - (num % 5)}"/>		
		<div class="w-50">				
			<ul class="nav d-flex justify-content-center">				
				<c:if test="${sp > 4}">			
			 		<li class="ml-2"><a href="?p=${num-5}">◀</a></li>
			 	</c:if>
				<c:forEach var="i" begin="0" end="4">
				
					<li class="ml-2"><a href="?p=${sp+i}">${sp+i+1}</a></li>
										
				</c:forEach>							
				<c:if test="${(sp+5)*10 <= total}">
					<li class="ml-2"><a href="?p=${sp+5}">▶</a></li>
				</c:if>
			</ul>
		</div>

</body>
</html>