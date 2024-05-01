<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String user = (String)session.getAttribute("user");
//session.removeAttribute("user");
//session.invalidate();
String user2 = (String)session.getAttribute("user");
%>
<h1>Welcome <%=user %>!</h1>
<h1>Welcome <%=user2 %>!</h1>

</body>
</html>