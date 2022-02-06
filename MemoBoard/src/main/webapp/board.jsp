<%@page import="jsp.model.BoardData"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
ArrayList<BoardData> boardArr = (ArrayList<BoardData>)request.getAttribute("board");
HttpSession mSession = request.getSession();
String username = String.valueOf(request.getAttribute("username"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<title>board</title>
<style>
body {
	width: 50%;
	margin: auto;
}

#titleTxt {
	font-family: serif;
	font-style: italic;
	text-align: right;
	
}

table {
	width: 100%;
	text-align: center;
}
</style>
</head>
<body>
	<div class="display-5">
	현재 접속중 :<c:out value="${curUser }"/>
	</div><br>
	<a href="member_list"><input type="button" value="멤버 관리"></a>
	<span id=titleTxt class="display-2 d-flex justify-content-end">Board</span>
	<hr>
	<hr>
	<table>
		<tr>
			<th class="border border-dark border-3">번호</th>
			<th width='250' class="border border-dark border-3">제목</th>
			<th class="border border-dark border-3">작성자</th>
			<th class="border border-dark border-3">작성일</th>
		</tr>
		<!--
		varStatus 속성 
		index: 데이터의 위치값 , count: 반복횟수 ,first: 첫번째 데이터, last: 마지막 데이터 current: 현재 들어있는 데이터
		varStatus 옵션 
		begin: 데이터 시작 인덱스, end:데이터 끝 인덱스, step: 증감
		,${vs.index},${vs.count}, ${vs.first }, ${vs.last } -->
		<c:forEach var="b" items="${board}" varStatus="vs" >
		
		<tr>
			<td class="border border-secondary">${b.idx }</td>
			<td class="border border-secondary">
				
				<a href="detail?bidx=${b.idx }">${b.title }</a> 
				[<c:out value="${b.count }"/>]
				<!--else if문을 사용할 때  -->
				<c:choose>
					
					<c:when test="${b.idx < 90}">
						<!-- 90미만 -->
					</c:when>
					
					<c:when test="${b.idx < 120}">
						<!-- 120미만 -->
					</c:when>
					
					<c:otherwise>
						<!-- 큰 숫자 -->
					</c:otherwise>
				</c:choose>
			</td>
			<td class="border border-secondary">${b.writer }</td>
			<td class="border border-secondary">
			<fmt:formatDate pattern="yyyy-MM-dd" value="${b.wdate}"/>
                 <%--  <fmt:formatNumber pattern="#,####" value="10000000"/> --%>
             </td>
             <td>
				<c:set var="page" value="${param.p == null ? 0 : param.p}"/>
             <c:if test="${b.mine == true }">
				<a href="edit?bidx=${b.idx }">
				<input type="button" name="editBtn" value="수정" >
				</a>
				<form action="delete" method="get">
				<input type="hidden" name="idx" value="${page },${b.idx}">
				<input type="submit" name="delBtn" value="삭제" >
				</form>
				</c:if>
             </td>       
		</tr>
		</c:forEach>
	</table>
	</div>
	<br>
	<div>
	<a href="write.jsp"><input type="button" value="글쓰기"></a>
	</div>
	<!-- 페이지 처리 -->
	<c:set var="total" value="${total }"/>
	<c:set var="num" value="${param.p == null ? 1 : param.p}"/>
	파라미터 페이지 숫자 :${num }
	실제 페이지 숫자  :${num+1 }
	<c:set var="sp" value="${num - (num % 5)}"/>
	sp 조건식 값:${sp }
	total 값 :${total }
	total 조건식 값 :${(sp+5)*10 }
	<div class="w-100">
		<ul class="nav d-flex justify-content-center">
		<!--첫 페이지 화살표 안뜨게 하기  -->
		<c:if test="${sp>4 }">
			<li class="ms-2"><a href="?p=${num-1 }">◀</a></li>
		</c:if>
		<c:forEach var="i" begin="0" end="4">
				<li class="ms-2"><a href="?p=${i+sp}">${i+sp+1}</a></li>
		</c:forEach>
		<!--마지막 페이지 화살표 안뜨게, 다음 페이지가 안보이려면 총 데이터가 몇개인지 알아야함 -->
		<c:if test="${(sp + 5)*10 < total }">
		<a href="?p=${num+1 }"><li class="ms-2">▶</li></a>
		</c:if>
		</ul>
	</div>
</body>
</html>