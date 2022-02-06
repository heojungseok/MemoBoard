<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%  
		

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
<title>login</title>
<style>
body{
 margin: auto;
 width: 800px;
}
#fixStr{
	font-family : sans-serif;
	font-weight : bolder;
	text-align : center;
	width: 200px;
	
}

</style>
</head>
<body>
	
	<span class="display-3 d-flex justify-content-start">LOGIN</span>
	<hr><hr>
	<table>
	
	<form action="member_ok.jsp" method="get">
	<tr><td id="fixStr"> 아이디 </td>
	<td><input name="userid" type="text" value=""></td>
	</tr>
	
	<tr><td id="fixStr">비밀번호</td>
	<td><input name="passwd" type="password" value=""></td>
	</tr>
	
	</table>
	<hr><hr>
	<div class="d-flex justify-content-between">
	<div>
	<input name="login" type="submit" value="Login">
	</div>
	</form>
	
	<div>
	<a href="member.jsp"><input name="join" type="button" value="join"></a>
	</div>
	
	</div>
</body>
</html>