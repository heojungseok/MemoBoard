<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
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
String sql = "SELECT * FROM MEMO_MEMBER";
  sql = "INSERT INTO MEMO_MEMBER (userid, passwd, email, username, address) VALUES (" 
+"'"+userid+"'"+",'"+passwd+"'"+",'"+email+"'"+",'"+username+"'"+",'"+address+"')";   
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection con = DriverManager.getConnection(url, "MEGA", "1234");
Statement st = con.createStatement();
//ResultSet rs = st.executeQuery(sql);
int result = st.executeUpdate(sql);
System.out.println(result); 

  /* while (rs.next()) {
	//System.out.println("이름 : " + rs.getString("username"));
	System.out.println("실행");	
}   */ 
%>
<%

HttpSession sMember = request.getSession();

sMember.setAttribute("userid", userid);
sMember.setAttribute("passwd", passwd);

String userid_ok = request.getParameter("userid_ok");
String passwd_ok = request.getParameter("passwd_ok");


System.out.println("session[ id : "+sMember.getValue("userid")+", pw :"+sMember.getValue("passwd")+"]");

if(sMember.getValue("userid").equals(userid_ok)){
	System.out.println("일치");
}else{
	System.out.println("불일치");
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
<title>login_ok</title>
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
	
	<span class="display-3 d-flex justify-content-start">LOGIN</span>
	<hr><hr>
	<table>
	
	<tr><td id="fixStr"> 아이디 </td>
	<td><input name="userid_ok" type="text" value=""></td>
	</tr>
	
	<tr><td id="fixStr">비밀번호</td>
	<td><input name="passwd_ok" type="password" value=""></td>
	</tr>
	
	</table>
	<hr><hr>
	<div class="d-flex justify-content-between">
	<form action="#" method="post">
	<div>
	<input name="login" type="submit" value="Login">
	</form>
	</div>
	<form action="member.jsp" method="post">
	<div>
	<input name="join" type="submit" value="join">
	</div>
	</form>
	</div>
	<%
		con.close();
		st.close();
//		rs.close();  
	%>	
</body>
</html>