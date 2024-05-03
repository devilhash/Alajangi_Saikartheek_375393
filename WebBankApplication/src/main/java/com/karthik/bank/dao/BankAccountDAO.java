package com.karthik.bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.karthik.bank.dto.BankAccount;

public class BankAccountDAO {
	Connection con;
	String userSQL ="root";
	String passwordSQL = "Alajangisaikartheek123";
	public BankAccountDAO() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank",userSQL ,passwordSQL);
	}
	public List<BankAccount> getBankAcctList(int userId) throws SQLException{
		List<BankAccount> bankList = new ArrayList<BankAccount>();
		PreparedStatement st = con.prepareStatement("select bank_acct_no,bank_name ,bank_ifsc,acct_balance from bank_accounts where user_id = ?");
		st.setInt(1, userId);
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			BankAccount b = new BankAccount();
			b.setAcctNumber(rs.getString(1));
			b.setBankName(rs.getString(2));
			b.setIfsc(rs.getString(3));
			b.setAcctBalance(rs.getDouble(4));
			
			bankList.add(b);
		}
		
		return bankList;
	
}
	public int addAcct(BankAccount b) throws SQLException {
		Statement st = con.createStatement();
		String query = "insert into bank_accounts (bank_acct_no,bank_name,acct_type_id,bank_ifsc,acct_pin,user_id,acct_balance)"+ "values('"+b.getAcctNumber()+"','"+b.getBankName()+"','"+ b.getAccTypeId()+"','"+b.getIfsc()+"','"+b.getPin()+"','"+b.getUserId()+"','"+b.getAcctBalance()+"')";
		return st.executeUpdate(query);
		
		
		
	}
}
