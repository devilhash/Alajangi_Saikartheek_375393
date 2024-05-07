<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel = "stylesheet" href = "css/register.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>login page</title>
</head>
<body>
    
    <div class ="row" id = "main">
         
         <div class ="col-sm-6" id = "login-img"> 
            <img src=" resources/banklogo.jpg"  />
         </div>
         <div class ="col-sm-6" id = "login-form" >
           <div style="margin-top: 90px;">
            <h3>Register</h3>
            <form  action="http://localhost:8080/WebBankApplication/addbank" method = "POST">
               
                
                <div class = "row" >
                    <input type="text" placeholder="accountnumber" name="accountnumber" class ="details" >
                </div>
                <div class = "row"  >
                    <input type="text" placeholder="bankname"  name="bankname" class ="details">
                </div>
                <div class = "row"  >
                    <input type="text" placeholder="ifsccode"  name="ifsccode" class ="details">
                </div>
                <div class = "row"  >
                    <input type="password" placeholder="pin"  name="pin" class ="details">
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