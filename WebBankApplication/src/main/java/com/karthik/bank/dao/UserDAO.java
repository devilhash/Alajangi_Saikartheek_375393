package com.karthik.bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.karthik.bank.dto.User;

public class UserDAO {
	Connection con;
	String userSQL ="root";
	String passwordSQL = "Alajangisaikartheek123";
	public UserDAO() throws ClassNotFoundException, SQLException{
	 
		  
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank",userSQL ,passwordSQL);
	}
	
	public  int addUserInfo(User u) throws SQLException {
		Statement st = con.createStatement();
		String query = "insert into user_info ( password,first_name,last_name,address,phNo,date_of_birth)"+ "values('"+u.getPassword()+"','"+u.getFirstName()+"','"+u.getLastName()+"','"+u.getAddress()+"','"+u.getPhNo()+"','"+u.getDob()+"')";
		return st.executeUpdate(query);
	}
	 
	public int userAuth(String userId,String password) throws SQLException {
		Statement st = con.createStatement();
		String query = "select phNo,password,user_id from user_info";
		ResultSet rs = st.executeQuery(query);
		while(rs.next()) {
			if(rs.getString(1).equals(userId)&&rs.getString(2).equals(password)) {
				 return rs.getInt(3);
			}
		}
		return 0;
	}
	public String userDetails(int userId) throws SQLException {
		PreparedStatement st = con.prepareStatement("select first_name,last_name from user_info where user_id = ?");
		st.setInt(1,userId);
		ResultSet rs = st.executeQuery() ;
		while(rs.next()) {
			return rs.getString(1)+" "+rs.getString(2);
		}
		return null;
		
		 
	}
	public boolean isUserexist(String userId) throws SQLException {
		int id = Integer.parseInt(userId);
		PreparedStatement st = con.prepareStatement("select user_id from user_info where user_id = ?");
		st.setInt(1,id);
		ResultSet rs = st.executeQuery() ;
		 if(rs.next()) {
			 return true;
		 }
		return false;
		
		 
	}

}
