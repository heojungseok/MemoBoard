<%@page import="java.util.ArrayList"%>
<%@page import="jsp.Book" %>
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
ArrayList<Book> bArr = (ArrayList<Book>)request.getAttribute("bArr");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memo</title>
<style>
</style>
</head>
<body>
	<form action="memo" method="get">
		<div>
			작성자 <br> <input name="writer" type="text" value="">
		</div>
		<div>
			내용 <br>
			<textarea name="content" rows="20" cols="60"></textarea>
		</div>
		<div>
			<input type="submit" name="done" value="OK">
		</div>
		<% for(int i = 0; i < bArr.size(); i++){
			Book b = bArr.get(i);%>
		<div>
			<table border="1">
				<tr>
					<td><input type="submit" disabled="disabled" value="작성자">
						<%=b.getWriter() %></td>
				</tr>
				<tr>
					<td><input type="submit" disabled="disabled" value="내용">
						<%=b.getContent() %></td>
				</tr>
			</table>
		</div>
		<%} %>
	</form>
</body>
</html>