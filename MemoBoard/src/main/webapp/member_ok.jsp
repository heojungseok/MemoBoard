<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>

<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%-- <%
request.setCharacterEncoding("utf-8");
HttpSession mSession = request.getSession();
String aaa = String.valueOf(mSession.getAttribute("aaa"));
System.out.println("fghjkl:" +aaa);

String userid = request.getParameter("userid");
String passwd = request.getParameter("passwd");
String email = request.getParameter("email");
String username = request.getParameter("username");
String address = request.getParameter("address");
String SYSDATE = request.getParameter("wdate");

System.out.println("id: "+userid);
System.out.println("pw: "+passwd);
System.out.println("mail: "+email);
System.out.println("username: "+username);
System.out.println("adrress: "+address);
System.out.println("wdate: "+SYSDATE);

String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
String sql = "";
sql = "INSERT INTO MEMO_MEMBER (userid, passwd, email, username, address, wdate) VALUES (" 
+"'"+userid+"'"+",'"+passwd+"'"+",'"+email+"'"+",'"+username+"'"+",'"+address+"',SYSDATE)";    
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection con = DriverManager.getConnection(url, "MEGA", "1234");
Statement st = con.createStatement();
//ResultSet rs = st.executeQuery(sql);
int result = st.executeUpdate(sql);
System.out.println(result+"-member_ok"); 
/* 
  while (rs.next()) {
	System.out.println("이름 : " + rs.getString("username"));
		
}   */  
 
request.setAttribute("bbb", "456");
//pageContext.forward("member.jsp");

con.close();
st.close();
//rs.close();
%>
 --%>
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
<title>member_ok</title>
<style>
body{
 margin: auto;
 width: 1200px
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
	<form action="login_ok.jsp" method="post">
	<span class="display-3 d-flex justify-content-start">LOGIN</span>
	<hr><hr>
	<table>
	
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
	</form>
	</div>
	
	<div>
	<a href="member.jsp"><input name="join" type="button" value="join"></a>
	</div>
	
	</div>
</body>

</html>