<%@page import="jsp.model.BoardData"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%HttpSession mSession = request.getSession();
	String curUsername = String.valueOf(mSession.getAttribute("username"));
	
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
<title>detail</title>
<style>
body{
	width: 800px;
	margin: auto;
}
#titleTxt{
	font-family: serif;
	font-style: italic;
}
#fixTxt{
	font-family: sans-serif;
	font-size: x-large;
	font-weight: 700;
	
}
</style>
</head>
<body>
	<div class="d-flex justify-content-between" >
	<span class="display-2">Detail</span>
	<div>
	<a href="board">
	<input type="button" value="글 목록">
	</a>
	</div>
	</div>
	<hr>
	<span id="fixTxt">
	Board No. :${b.idx }<br>
	Writer ${b.writer} <br>
	Title : ${b.title} <br>
	Content : ${b.content}<br>
	
	Files : ${b.files }
	</span>
	<hr>
	
	<c:forEach var="c" items="${cmt }" varStatus="vs">
	<c:if test="${c.cmt != null }">
	${c.idx } ${c.writer } : ${c.cmt }
	<c:if test="${c.mine == true }">
		<form action="delete" method="get">
		<input type="hidden" name="cidxNbidx" value="${c.idx },${b.idx }">
		<input type="submit" name="delCmt" value="삭제">
		</form>
	</c:if>
	 <br>
	</c:if>
	
	</c:forEach>
	<hr>
	<div>
	댓글 작성자 :<%=curUsername %>                  
	<form action="detail" method="post">
	<input type="hidden" name="bidx" value="${b.idx }">
	<textarea name="cmt" rows="5" cols="100"></textarea>
	<input type="submit" name="addCmtBtn" value="댓글 달기">
	</form>
	</div>

</body>
</html>