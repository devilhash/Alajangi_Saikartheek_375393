package com.karthik.bank;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import com.karthik.bank.dao.UserDAO;
import com.karthik.bank.dto.User;

 
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		String userName = request.getParameter("phonenumber");
		String password = request.getParameter("password");
		User u = null;
		String fullName = null;
		try {
		    UserDAO dao = new UserDAO();
			u =   dao.userAuth(userName, password);

			if(u!= null) {
				HttpSession session = request.getSession();
				session.setAttribute("user_details",u);
				
				Cookie co = new Cookie("userId",String.valueOf(u.getUserId()));
				response.addCookie(co);
				RequestDispatcher acctListrd = request.getRequestDispatcher("getAcctListServlet");
				acctListrd.forward(request, response);
			}
					 
  
			
			else {
				HttpSession session = request.getSession();
				session.setAttribute("msg",  "log in failed please enter valid credentials");
				RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
				rd.forward(request, response);
			}
		} catch (ClassNotFoundException e) {
			 
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	 
	}

}
