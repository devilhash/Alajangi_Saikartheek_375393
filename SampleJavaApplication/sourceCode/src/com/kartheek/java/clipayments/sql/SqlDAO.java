package com.kartheek.java.clipayments.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.Date;

import com.kartheek.java.clipayments.entity.BankAccount;
import com.kartheek.java.clipayments.entity.Transaction;
import com.kartheek.java.clipayments.entity.TransactionType;
import com.kartheek.java.clipayments.entity.User;

public class SqlDAO {
	Connection con;
	 public SqlDAO() throws ClassNotFoundException, SQLException{
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 
		  con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/clipayments","root","root");
	 }
	 
	 public void addUserToDataBase(User u) throws SQLException {
		 Statement st = con.createStatement();
		 String query = "insert into User(UserId,FirstName,SecondName,PhoneNumber,DateOFBirth,Address,Passcode, WalletBalance) "
					+ "values('"+u.getUserId()+"','"+u.getFirstName()+"','"+u.getLastName()+"','"+u.getPhoneNum()+"','"+u.getDateOfBirth()+"','"+u.getCommunicationAddr()+"','"+u.getPassword()+"',"+0+")";
		 st.executeUpdate(query);
		 System.out.println(query);
		 con.close();
		 
	 }
	 public void addAccountToDataBase(BankAccount b) throws SQLException {
		 Statement st = con.createStatement();
		 String query = "insert into bankaccount "+"values('"+b.getUserid()+"','"+b.getAcctNumber()+"','"+b.getBankName()+"','"+b.getIFSC()+"','"+b.getAcctType()+"','"+b.getAcctBalance()+"')";
		 st.executeUpdate(query);
		 System.out.println(query);
		 con.close();
	 }
	 public void  addTransactionDetailsToDataBase(Transaction t) throws SQLException {
		 Statement st = con.createStatement();
		 if(t.getTransactionType()==TransactionType.DEBIT) {
		 String query = "insert into transactions(TransactionId,TransactionDate,TransactionType,TransactionAmount,UserId,to_account_number,to_wallet) "+"values('"+t.getTransactionId()+"','"+t.getTransactionDate()+"','"+t.getTransactionType()+"','"+t.getTransactionAmount()+"','"+t.getUserId()+"','"+t.getDestinationAcct().getAcctNumber()+"','"+t.getDestinationWallet().getUserId()+"')";
		 st.executeUpdate(query);
		 System.out.println(query);
		 String queryrecip = "insert into transactions(TransactionId,TransactionDate,TransactionType,TransactionAmount,UserId,from_account_number,from_wallet)  "+"values('"+t.getTransactionId()+"','"+t.getTransactionDate()+"','"+TransactionType.CREDIT+"','"+t.getTransactionAmount()+"','"+t.getUserId()+"','"+t.getSourceAcct().getAcctNumber()+"','"+t.getSourceWallet().getUserId()+"')";
		 st.executeUpdate(query);
		 System.out.println(query);
		 }else {
			 String query = "insert into transactions(TransactionId,TransactionDate,TransactionType,TransactionAmount,UserId,from_account_number,from_wallet)  "+"values('"+t.getTransactionId()+"','"+t.getTransactionDate()+"','"+TransactionType.CREDIT+"','"+t.getTransactionAmount()+"','"+t.getUserId()+"','"+t.getSourceAcct().getAcctNumber()+"','"+t.getSourceWallet().getUserId()+"')";
			 st.executeUpdate(query);
			 System.out.println(query);
		 }
		 con.close();
	 }
}
