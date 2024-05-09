<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="DownloadServlet">
<%@ page import = "java.io.File" %>
 <%
	String contextPath = application.getRealPath( "/");
	File file = new File(contextPath+"/files");
	String filesList[] = file.list();
	for(String s: filesList){	 
 %>
	 <input type = "radio" value =<%=s %> name = "files"><%=s %>  </input>
    <%}%>
    <input type = "submit"  value = "submit"/>
 
</form>
</body>
</html>