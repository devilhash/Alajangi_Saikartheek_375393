package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAO {
	Connection con;
	String userSQL ="root";
	String passwordSQL = "Alajangisaikartheek123";
	public  DAO() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank",userSQL ,passwordSQL);
	}
	
	public  void createDetails() throws SQLException{
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i=2;i<=100;i++) {
			 String query = "insert into sample(id) values('"+i+"')";
			  Statement st = con.createStatement();
			st.executeUpdate(query);
		}
	}
	public ArrayList<Integer> getDetails() throws SQLException{
		ArrayList<Integer> list = new ArrayList<Integer>();
		String query = "select * from sample";
		  Statement st = con.createStatement();
		  ResultSet rs = st.executeQuery(query);
		  while(rs.next()) {
			  list.add(rs.getInt("id"));
		  }
		  return list;
		
	}
}
