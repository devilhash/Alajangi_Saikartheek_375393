package com.kartheek.java.clipayments;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kartheek.java.clipayments.entity.*;

public class UserOperations {
	 List<User>list = RunPaymentsApplication.userList;
	 Map<Integer,Wallet> walletList = RunPaymentsApplication.walletList;
	 List<BankAccount> baList = RunPaymentsApplication.acctList;
    public User doUserRegistration(String fName, String lName, String password, long phNum, String dob,String addr) throws Exception{
		User u = new User();
		u.setFirstName(fName);
		u.setLastName(lName);
		u.setPhoneNum(phNum);
		u.setDateOfBirth(dob);
		u.setCommunicationAddr(addr);
		u.setPassword(password);
		
		u.setUserId();
		
//		FileOps fileOps = new FileOps();
//		fileOps.userToFile(u);
		return u;
	}
	
	public void printUserList( ){
	 for(User u : list) {
		 System.out.println(u);
	 }
	}

	public void currentUser() {
		if(RunPaymentsApplication.currentUserId==-1) {
			System.out.println("No user logged in");
		}
		else {
			 for(User u : list) {
				 if(RunPaymentsApplication.currentUserId == u.getUserId()) {
					 System.out.println(u.getFirstName()+" "+u.getLastName());
				 }
			 }
			 
		 }
		
	}

	public void userLogIn(int userId, String password) {
		for(User u : list) {
			if(u.getUserId()==userId&&u.getPassword( ).equals(password)) {
				System.out.println("welcome Mr."+u.getFirstName()+" "+u.getLastName());
			    RunPaymentsApplication.currentUserId = userId;
			}
			}
		}
	public void addMoneyToWallet(double amount) {
		if(walletList.containsKey(RunPaymentsApplication.currentUserId )) {
	        walletList.get(RunPaymentsApplication.currentUserId ).setBalance(walletList.get(RunPaymentsApplication.currentUserId ).getBalance()+amount);
	        System.out.println(walletList.get(RunPaymentsApplication.currentUserId ).getBalance());
			 
		}
	}
	public double checkWalletBalance(){
		
		return walletList.get(RunPaymentsApplication.currentUserId ).getBalance();
	}
	public  Map<User, List<BankAccount>> getBankAccountList(){
		  
Map<User,List<BankAccount>> userBankAcctMap = new HashMap<User,List<BankAccount>>();
		
		for(User u:list) {
			if(list != null) {
				userBankAcctMap.put(u, u.getBankList());
			}
		}
		return userBankAcctMap;
		
	}
	public boolean doTransaction(Wallet sender , Wallet receiver , TransactionType tType,double amount) {
		if(sender.getBalance()>amount) {
			receiver.setBalance(receiver.getBalance()+amount);
			sender.setBalance(sender.getBalance()-amount);
			return true;
		}
		return false;
		
		 
	}
		
 
		
 
 

}
