<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
request.setCharacterEncoding("utf-8");
String title = request.getParameter("title");
String content = request.getParameter("content");

HttpSession mySession = request.getSession();
String username = String.valueOf(mySession.getAttribute("username"));
int midx = Integer.parseInt(String.valueOf(mySession.getAttribute("midx")));

System.out.println(username);
System.out.println(midx);

for(int i = 0; i < 100; i++){
String sql ="INSERT INTO MEMO_BOARD (midx, title, content ,wdate)"+
"VALUES(?,?,?,SYSDATE)";

String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection con = DriverManager.getConnection(url, "MEGA", "1234");
PreparedStatement pst = con.prepareStatement(sql);
pst.setInt(1, midx+i);
pst.setString(2, title + i);
pst.setString(3, content + i);

int result = pst.executeUpdate();
System.out.println(result);
System.out.println(title);
System.out.println(content);

con.close();
pst.close();

}
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
<title>write_ok</title>
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
	<form action="write.jsp" method="post">
		<span id=titleTxt class="display-2">Write Here!</span>
		<hr>
		<div class="d-flex justify-content-between">
		<span id=fixTxt class="pe-4">Writer</span><%=username %>
		<a href="board"><input type="button" value="글 목록"></a> 
		</div>
		<br>
		<div class="d-flex flex-column">
			<span id=fixTxt>Title</span>
			<input name="title" type="text">
			<br>
			<span id=fixTxt>Contents</span>
			<textarea name="content" rows="20" cols="60"></textarea>
		</div>
		<hr>
		<div class="d-flex justify-content-between">
			<input type="submit" name="done" value="작성완료">
			<input type="submit" name="done" value="취소">
			
		</div>
	</form>
</body>
</html>