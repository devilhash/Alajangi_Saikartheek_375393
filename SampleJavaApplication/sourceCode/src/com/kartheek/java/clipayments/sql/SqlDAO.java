package com.kartheek.java.clipayments.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.Date;

import com.kartheek.java.clipayments.RunPaymentsApplication;
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
		 String query = "insert into User( FirstName,SecondName,PhoneNumber,DateOFBirth,Address,Passcode, WalletBalance) "
					+ "values( '"+u.getFirstName()+"','"+u.getLastName()+"','"+u.getPhoneNum()+"','"+u.getDateOfBirth()+"','"+u.getCommunicationAddr()+"','"+u.getPassword()+"',"+0+")";
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
		 String query = "insert into transactions "+"values('"+t.getTransactionId()+"','"+t.getTransactionDate()+"','"+t.getTransactionType()+"','"+t.getTransactionAmount()+"','"+t.getUserId()+"','"+t.getDestinationAcct().getAcctNumber()+"','"+t.getDestinationWallet().getUserId()+"')";
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
	 public boolean verifyUser(int uId , String password) throws SQLException {
		 Statement st = con.createStatement();
		 String query = "select userId,Passcode from user";
		 ResultSet rs = st.executeQuery(query);
		 while(rs.next()) {
			 if(uId == rs.getInt(1)&& password.equals(rs.getString(2))) {
				 RunPaymentsApplication.currentUserId=uId;
				 con.close();
				 return true;
			 }
		 }
		 con.close();
		 return false;
		 
	 }
	 public String getCurrentUser() throws SQLException {
		 String firstName =null;
		 String secondName=null;
		 if(RunPaymentsApplication.currentUserId==-1) {
			  return "no user logged in";
		 }
		 else { 
			 String query = "select userId,FirstName,SecondName from user ";
			 Statement st = con.createStatement();
			 ResultSet rs = st.executeQuery(query);
			   while(rs.next()) {
				 if(rs.getInt(1)==RunPaymentsApplication.currentUserId) {
					 firstName = rs.getString(2);
					 secondName = rs.getString(3);
 			 
				 }
			 }
			   con.close();
			 
			 return firstName+" "+secondName ;
		 }
	 
		 
	 }
	 
	 public boolean addMoneyToWallet(double amount) throws SQLException {
		 double balance =0;
		 Statement st = con.createStatement();
		 String query = "select userId,WalletBalance from user";
		 ResultSet rs = st.executeQuery(query);
		 
		 while(rs.next()) {
			 if(rs.getInt(1)==RunPaymentsApplication.currentUserId) {
				 balance = rs.getDouble(2);
			 
			 }
		 }
		 String q = "UPDATE user SET  WalletBalance= ?  WHERE userId = ?";
		 
		 PreparedStatement pst = con.prepareStatement(q);
		 pst.setDouble(1, balance+amount);
		 pst.setInt(2, RunPaymentsApplication.currentUserId);
		 
		 int flag = pst.executeUpdate();
		 if(flag>0)  
			 return true;
		 else
			 return false;
		 
		 
	 }
	 public double showWalletBalance() throws SQLException {
		 double bal=0;
		 String query = "select WalletBalance from user where userId = ("+ RunPaymentsApplication.currentUserId+")";
		 Statement st = con.createStatement();
		 
		 ResultSet rs = st.executeQuery(query);
		 while(rs.next()) {
			 bal = rs.getDouble(1);
		 }
		 con.close();
		 return bal;
	 }
	 public void showBankAcounts() throws SQLException {
		 Statement st = con.createStatement();
		 String query = "select AccountNumber,BankName from bankaccount where userId = ("+RunPaymentsApplication.currentUserId+")";
		 ResultSet rs = st.executeQuery(query);
		 while(rs.next()) {
			 System.out.println(rs.getString(1)+" "+rs.getString(2));
		 }
		 con.close();
		 
		 
	 }
	 public double showAccountBalance(String acctNo) throws SQLException {
		 double bal=0;
		 String query = "select AccountBalance from bankaccount where userId = ("+ RunPaymentsApplication.currentUserId+") and AccountNumber = ("+acctNo+")";
		 Statement st = con.createStatement();
		 
		 ResultSet rs = st.executeQuery(query);
		 while(rs.next()) {
			 bal = rs.getDouble(1);
		 }
		 con.close();
		 return bal;
	 }
	 public boolean transactionWalletToWallet(double amount , int receiverUserId) throws SQLException {
		 double senderBalance = 0;
		 double receiverBalance = 0;
		 String querySenderBalance = "select WalletBalance from user where userId = ("+ RunPaymentsApplication.currentUserId+")";
		 String queryReceiverBalance = "select WalletBalance from user where userId = ("+ receiverUserId+")";
		 Statement st = con.createStatement();
		 ResultSet senderRS = st.executeQuery(querySenderBalance);
		 while(senderRS.next()) {
			 senderBalance = senderRS.getDouble(1);
			 
		 } 
		 ResultSet receiverRS = st.executeQuery(queryReceiverBalance);
	 
		 while(receiverRS.next()) {
			 receiverBalance = receiverRS.getDouble(1);
		 }
		 String updateSenderBalance = "update user set walletbalance = ("+(senderBalance-amount)+") where userId = ("+RunPaymentsApplication.currentUserId+")";
		 String updateReceiverBalance = "update user set walletbalance = ("+(receiverBalance+amount)+") where userId = ("+receiverUserId+")";
		 
		 int  updateReceiverFlag = st.executeUpdate(updateReceiverBalance);
		 int updateSenderFlag = st.executeUpdate(updateSenderBalance);
		 if(updateReceiverFlag==1&&updateSenderFlag==1) {
			 return true;
		 }
		return false;
		 
	 }
}
