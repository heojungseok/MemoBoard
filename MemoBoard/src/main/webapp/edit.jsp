<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
HttpSession mySession = request.getSession();
String username = String.valueOf(mySession.getAttribute("username"));

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
<title>edit</title>
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
	font-weight: 500;
	font-style: italic;
}
</style>
</head>
<body>
<!--encoding 방법을 바꿀 때 설정 (파일을 보낼 수 있게) -->
	<form action="edit" method="post" enctype="multipart/form-data">
		<span id=titleTxt class="display-2">Write Here!</span>
		<hr>
		<div class="d-flex justify-content-between">
		<span id=fixTxt class="pe-4">Writer</span><%=username%>
		<a href="board"><input type="button" value="글 목록"></a> 
		</div>
		<br>
		<div class="d-flex flex-column">
			<span id=fixTxt>Board No. ${b.idx }</span>
			<input type="hidden" name="bidx" value="${b.idx }">
			<span id=fixTxt>Title</span>
			<input name="title" type="text" value="${b.title }">
			<br>
			<span id=fixTxt>Contents</span>
			<textarea name="content" rows="20" cols="60">${b.content }</textarea><br>
			<span id=fixTxt>Files : ${b.files }</span>
		</div>
		<hr>
		<div>
		<span id=fixTxt>File 1</span>
		<input type="file" name="myFile" value="파일"><br>
		<span id=fixTxt>File 2</span>
		<input type="file" name="myFile" value="파일"><br>
		<span id=fixTxt>File 3</span>
		<input type="file" name="myFile" value="파일">
		</div>
		<hr>
		<div class="d-flex justify-content-between">
			<input type="submit" name="done" value="작성완료">
		</form>
			<input type="submit" name="done" value="취소">
		</div>
</body>
</html>