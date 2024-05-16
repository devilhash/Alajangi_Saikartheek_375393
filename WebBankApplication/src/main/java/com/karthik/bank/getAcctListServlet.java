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
import java.util.List;

import com.karthik.bank.dao.BankAccountDAO;
import com.karthik.bank.dto.BankAccount;
import com.karthik.bank.dto.User;

/**
 * Servlet implementation class getAcctListServlet
 */
public class getAcctListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getAcctListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 
		
		 HttpSession session = request.getSession();
		 User u =  (User) session.getAttribute("user_details");
		 try {
			BankAccountDAO bankDao = new BankAccountDAO();
			List<BankAccount> list = bankDao.getBankAcctList(u.getUserId());
			session.setAttribute("bankList", list);
//			RequestDispatcher rd = request.getRequestDispatcher("/dashboard.jsp");
//			rd.forward(request, response);
			RequestDispatcher rdacct = request.getRequestDispatcher("/dashboard.jsp");
			rdacct.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
     
}
