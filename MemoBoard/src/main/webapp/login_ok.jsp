<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
request.setCharacterEncoding("utf-8");
String userid = request.getParameter("userid");
String passwd = request.getParameter("passwd");
System.out.println(userid);
System.out.println(passwd);

String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
String sql = "";
sql = "SELECT * FROM MEMO_MEMBER WHERE userid = " + "'" + userid + "'";
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection con = DriverManager.getConnection(url, "MEGA", "1234");
Statement st = con.createStatement();
ResultSet rs = st.executeQuery(sql);

boolean isLogin = false;
while (rs.next()) {
if(passwd.equals(rs.getString("passwd"))){
isLogin = true;
int midx = rs.getInt("midx");
String username = rs.getString("username");
HttpSession mySession = request.getSession();
mySession.setAttribute("username", username);
mySession.setAttribute("midx", midx);

}
	System.out.println(rs.getString("userid")+","+rs.getString("passwd")+","+rs.getInt("midx"));

}
if(isLogin){
	System.out.println("로그인");
	response.sendRedirect("board");
}else{
	System.out.println("로그인 실패");
	response.sendRedirect("member_ok.jsp");
}


con.close();
st.close();
rs.close();


request.setAttribute("bbb", "456");
//pageContext.forward("member.jsp");


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
<title>write</title>
<style>
</style>
</head>
<body>

</body>
</html>