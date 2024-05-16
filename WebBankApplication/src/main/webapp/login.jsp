<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel = "stylesheet" href = "login.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>login page</title>
</head>
<body>
  <%@ page import = "com.karthik.bank.dao.UserDAO" %>
  <%--
    Cookie[] cookies = request.getCookies();
    UserDAO dao = new UserDAO();
    for(Cookie c : cookies){
    	if(c.getName().equals("userId")){
    		if(dao.isUserexist(c.getValue())){
    			 request.getRequestDispatcher("getAcctListServlet").forward(request, response);
    		}
    	}
    }
  
 --%>
    
    <div class ="row" id = "main">
         
         <div class ="col-sm-6" id = "login-img"> 
            <img src="  banklogo.jpg"  />
         </div>
         <div class ="col-sm-6" id = "login-form" >
           <div style="margin-top: 90px;">
            <h3>LogIn</h3>
            <% String msg =null;
            String rmsg = null;
            msg =(String) session.getAttribute("msg");
            rmsg =(String) session.getAttribute("regmsg");
            if(msg!=null){%>
           <center><h6 style="color: red;"><%=msg %></h6></center>  <%} 
            if(rmsg!=null){%>
            <center> <h6 style="color: green;"><%=rmsg %></h6></center>   <%} %>
             
          
            
            
            <form  action="http://localhost:8080/WebBankApplication/login"  method = "POST">
                <div class = "row" >
                    <input type="text" placeholder="phonenumber" name="phonenumber" class ="details" >
                </div>
                <div class = "row"  >
                    <input type="password" placeholder="password"  name="password" class ="details">
                </div>
                <div class = "row" id ="submit" >
                    <input type="submit" class ="details" >
                </div>
            </form>
           </div>
            
         </div>

    </div>
</body>
</html>