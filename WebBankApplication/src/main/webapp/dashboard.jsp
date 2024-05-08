<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel = "stylesheet"  href = "dashboard.css"></link>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
     <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
 
<title>Insert title here</title>
</head>
<body>
  <div class = "navbar">
        <h4 >BankingApplication</h4>
        <button>log out</button>

    </div>
    <%
    String userName = (String) session.getAttribute("name");
    %>
    <h2 style="padding: 10px;">Welcome <%= userName %></h2>
    
     
   <%@ page import="java.util.ArrayList" %>
   <%@ page import="com.karthik.bank.dto.BankAccount" %>
   <%  ArrayList<BankAccount> list = (ArrayList<BankAccount>) session.getAttribute("bankList"); 
   if(list.size()==0){%>
    <div class="account_details">
           <form action = "http://localhost:8080/WebBankApplication/addbank.jsp">
           <input type = "submit" value="addBank" class = "addaccount"/>
           </form>
    </div>
   
   
   <%} else{%>
       <div   id = "list">
            <ul>
            <li><h4>PrimaryBankAccount : <%=list.get(0).getAcctNumber() %></h4></li>
            <li><h4>BankAccountBalance : <%=list.get(0).getAcctBalance()%></h4></li>
            </ul>
    </div>
   <%} %>
   
   <div class = "bankList" > 
   <%if(list.size()>0){
   for(int i = 0 ; i < list.size() ; i++){ %>
       <div class = "acct" >
        <ul>
            <li><h6> <%=list.get(i).getBankName()%></h6></li>
            <li><h6>AccountNumber : <%=list.get(i).getAcctNumber()%></h6></li>
            <li><h6>Balance       : <%=list.get(i).getAcctBalance()%></h6></li>
            <li><h6>IFSC          : <%=list.get(i).getIfsc()%></h6></li>
            </ul>
       
       </div>
          
   <%} %>
     <form action = "http://localhost:8080/WebBankApplication/addbank.jsp">
           <input type = "submit" value="addBank" class = "addaccount"/>
           </form>
   <%} %>
   </div>
   
    
         
     
</body>
</html>