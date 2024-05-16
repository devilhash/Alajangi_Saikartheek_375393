<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 
<%@ page import = "java.util.ArrayList" %>
 <%
 ArrayList<Integer> list = new ArrayList<Integer>();
 	  list =( ArrayList<Integer>) session.getAttribute("sortedList");
 	 session.setAttribute("pg",0);
 	  if(list != null){
 	  for(int i = 0 ; i <10 ; i++){
 %>
   <h4><%= i %></h4>
   <%} }else{%>
   <h2>list is empty</h2>
   <% } %>
   
 
   
<form action = "NextPage">
<input type = "submit" value = "nextpage"/>
</form>
</body>
</html>