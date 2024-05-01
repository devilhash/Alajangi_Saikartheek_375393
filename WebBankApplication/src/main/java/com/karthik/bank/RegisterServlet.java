package com.karthik.bank;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.karthik.bank.dao.UserDAO;

 
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public RegisterServlet() {
        super();
     }
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("user");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirm");
		 if(password.equals(confirmPassword)) {
			 try {
				UserDAO dao = new UserDAO();
				dao.addUserInfo(userId, confirmPassword);
				RequestDispatcher rd = request.getRequestDispatcher("/welcome.html");
				rd.forward(request, response);
			} catch (ClassNotFoundException e) {
 				e.printStackTrace();
			} catch (SQLException e) {
 				e.printStackTrace();
			}
		 }
		 else {
			 response.setContentType("text/html");
			 PrintWriter out = response.getWriter();
			 out.print("password mismatch");
			 RequestDispatcher rd = request.getRequestDispatcher("/welcome.html");
			 rd.include(request, response);
		 }
	}

}
