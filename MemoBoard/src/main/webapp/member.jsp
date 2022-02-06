
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>

<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	/* HttpSession mSession = request.getSession();
	mSession.setAttribute("aaa", "1234");
	
	String bbb = String.valueOf(request.getAttribute("bbb"));
	System.out.println("test: "+bbb); */
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
<title>member</title>
<style>
body {
	margin: auto;
	width: 800px
}
#fixStr{
	font-family : monospace;
	font-weight : bolder;
	text-align : center;
	width: 200px;
	
}
</style>
</head>
<body>
	<form action="memberInfo" method="post">
		<span class="display-3 d-flex justify-content-start">SIGN UP</span>
		<hr>
		<table>
			<tr>
				<td id="fixStr">아이디</td>
				<td><input name="userid" type="text" value=""></td>
			</tr>
			<tr>
				<td id="fixStr">비밀번호</td>
				<td><input name="passwd" type="password" value=""></td>
			</tr>
			<tr>
				<td id="fixStr">비밀번호 확인</td>
				<td><input name="passwd1" type="password" value=""></td>
			</tr>
			<tr>
				<td id="fixStr">e-mail</td>
				<td><input name="email" type="text" value=""></td>
			</tr>
			<tr>
				<td id="fixStr">이름</td>
				<td><input name="username" type="text" value=""></td>
			</tr>
			<tr>
				<td id="fixStr">주소</td>
				<td><input name="address" type="text" value=""></td>
			</tr>
		</table>
		<hr>
		<div>
			<input name="confirm" type="submit" value="OK">
		</div>

	</form>

</body>
</html>