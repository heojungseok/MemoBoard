<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
int p = Integer.parseInt(String.valueOf(request.getAttribute("page")));
/* int bidx = Integer.parseInt(String.valueOf(request.getAttribute("bidx"))); */

System.out.println("curPage"+p);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
<title>delete</title>

</head>
<body>
<%if(p != -1){ %>
<script type="text/javascript">
alert("삭제 완료")
location.href= "board?p=<%=p%>";
</script>
<%} %>
<%-- <%}else if(bidx != -1) {%>
<script type="text/javascript">
alert("삭제 완료")
location.href= "detail?bidx=<%=bidx%>";
</script>
<%} %> --%>
</body>
</html>