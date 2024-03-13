package com.kartheek.java.clipayments.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {

	public static void main(String[] args) {
		 try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clipayments","root","");
			Statement st = con.createStatement();
//			st.executeQuery( "select * from ")
			
			 
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 

}
