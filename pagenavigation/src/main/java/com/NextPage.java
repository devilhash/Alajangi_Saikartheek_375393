package com;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class NextPage
 */
public class NextPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NextPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Integer> list = new ArrayList<Integer>();
		HttpSession se = request.getSession();
		ArrayList<Integer> fullList =  (ArrayList<Integer>)se.getAttribute("list");
		System.out.println(fullList);
		
		int pg =(int)se.getAttribute("pg");
		int start = pg*10;
		int end = (pg+1)*10;
		  
		 
		  
		 for(int i = start ; i < end ; i++) {
			 list.add(fullList.get(i) );
		 }
		 se.setAttribute("pg",  pg+1);
		 se.setAttribute("sortedList", list);
		 int p =(int)se.getAttribute("pg");
			RequestDispatcher rd = request.getRequestDispatcher("/show.jsp");
			rd.forward(request, response);
			
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
