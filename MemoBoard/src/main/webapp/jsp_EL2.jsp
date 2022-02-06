<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>el</title>
<style>
</style>
</head>
<body>
	${str }<br>
	
	${arr[1] }<br>
	
	${map.name}
	${map.age} <br>
	
	결과 : ${map.age < 100 ? '100 작다' : '100 이상' }<br>
	이름 : ${not empty param.name ? param.name: '입력없음' }<br>
	??? : ${param }<br>
	${header.host }
</body>
</html>