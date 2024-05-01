package com.karthik.bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {
	Connection con;
	public UserDAO() throws ClassNotFoundException, SQLException{
		  
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","Alajangisaikartheek123");
	}
	
	public  int addUserInfo(String userId,String password) throws SQLException {
		Statement st = con.createStatement();
		String query = "insert into user_info (user_id,password)"+ "values('"+userId+"','"+password+"')";
		return st.executeUpdate(query);
	}
	 
	public boolean userAuth(String userId,String password) throws SQLException {
		Statement st = con.createStatement();
		String query = "select user_id , password from user_info";
		ResultSet rs = st.executeQuery(query);
		while(rs.next()) {
			if(rs.getString(1).equals(userId)&&rs.getString(2).equals(password)) {
				return true;
			}
		}
		return false;
	}

}
