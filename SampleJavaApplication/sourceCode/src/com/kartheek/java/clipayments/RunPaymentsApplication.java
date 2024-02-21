package com.kartheek.java.clipayments;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kartheek.java.clipayments.entity.AcctType;
import com.kartheek.java.clipayments.entity.BankAccount;
import com.kartheek.java.clipayments.entity.User;
import com.kartheek.java.clipayments.entity.Wallet;

public class RunPaymentsApplication {

	static int x=10;
   static List<User> userList = new ArrayList<User> ();
   static int currentUserId = -1;
   static Wallet wallet;
	 
    

	public static void main(String[] args) {
		
		int selectedOption=0;		
		Scanner opt = new Scanner(System.in);
				
		while(true) {
			System.out.println("Payments App Actions:");
			System.out.println("1. Register New User");
			System.out.println("2. Login");
			System.out.println("3. ADD Bank Account");
			System.out.println("6.Add money to Wallet");
			System.out.println("4. List of Users");
			System.out.println("-1. Quit/ Logout");
			System.out.println("5. Current User");
			System.out.println("Choose an Option:");
			
			String optStr = opt.next();
			try {
				selectedOption = Integer.parseInt(optStr);
				
			}catch(NumberFormatException e) {
				e.printStackTrace();
				e.getMessage();
				System.out.println("Number format Error Occured Please choose another option.");
				
			}catch(ArithmeticException e) {
				e.printStackTrace();
				e.getMessage();
				System.out.println("arithmetic Error Occured Please choose another option.");
				
			}catch(Exception e) {
				e.printStackTrace();
				e.getMessage();
				System.out.println("Some Error Occured Please choose another option.");
			}finally{
				System.out.println();
			}
			
			System.out.println("User selected "+selectedOption);
			UserOperations ops = new UserOperations();
			if(optStr.equalsIgnoreCase("1")) {
			   register();
			   
			}else if(optStr.equalsIgnoreCase("2")) {
				if(currentUserId == -1) {
				System.out.println("enter User credentials to login ");
				System.out.println();
				System.out.println("Enter UserId : ");
				int userId = opt.nextInt();
				System.out.println("Enter password : ");
				String password = opt.next();
				
				ops.userLogIn ( userId, password);
				}
				else {
					System.out.println("To log in to another account you must log out the current user");
				}
				
				
			}else if(optStr.equalsIgnoreCase("3")) {
				addBankAccount();
				
			}else if(optStr.equalsIgnoreCase("4")) {
				ops.printUserList( );
			}else if(optStr.equalsIgnoreCase("-1")) {
				break;
			}else if(optStr.equalsIgnoreCase("5")) {
				 ops.currentUser();
			}else if(optStr.equalsIgnoreCase("6")) {
				   addMoney();
			}
			else {
				
			}
		}
		opt.close();
	}
	
	
	
	
	private static void register() {
		 try {
		Scanner opt = new Scanner(System.in);
		System.out.println("Please do provide user details as asked:");
		System.out.println("First Name:");
		String fName = opt.next();
		System.out.println("Last Name:");
		String lName = opt.next();
		System.out.println("Phone Number:");
		long phNo = Long.parseLong(opt.next());
		System.out.println("Date Of Birth:");
		String dob = opt.next();
		System.out.println("Address:");
		String addr = opt.next();
		System.out.println("Password:");
		String password = opt.next();
				UserOperations ops = new UserOperations();
				User u = ops.doUserRegistration(fName, lName, password, phNo, dob, addr);
				wallet = new Wallet();
				 
			     userList.add(u);
			     
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		
	 
	     
	}
	
	
	private static void logIn() {
		Scanner opt = new Scanner(System.in);
		if(currentUserId == -1) {
			System.out.println("enter User credentials to login ");
			System.out.println();
			System.out.println("Enter UserId : ");
			int userId = opt.nextInt();
			System.out.println("Enter password : ");
			String password = opt.next();
			
			UserOperations ops = new UserOperations();
			
			ops.userLogIn ( userId, password);
			}
			else {
				System.out.println("To log in to another account you must log out the current user");
			}
	}
	private static void addBankAccount() {
		BankAccount bankAccount = new BankAccount();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Bank Name : ");
		String bankName = sc.next();
		System.out.println("Enter Bank Account Number : ");
		String acctNumber = sc.next();
		System.out.println("Enter IFSC Code : ");
		String ifsc = sc.next();
		System.out.println("Select Your Account type from the following : ");
		for(AcctType type : AcctType.values()) {
			System.out.println("      "+type);
		}
		String actType = sc.next();
		try {
			AcctType acctType = AcctType.valueOf(actType);
			bankAccount.setAcctType(acctType);
		}
		catch(IllegalArgumentException e) {
			System.out.println("User should enter only one of the following values");
			for(AcctType type : AcctType.values()) {
				System.out.println("      "+type);
			}
		}
	 
		
		System.out.println("Enter Account Pin");
		String pin = sc.next();
		
		 
		bankAccount.setBankName(bankName);
		bankAccount.setAcctNumber(acctNumber);
		bankAccount.setIFSC(ifsc);
		bankAccount.setAcctPin(pin);
		bankAccount.setUserid(currentUserId);
		 
		
		
	}
	private static void addMoney() {
		
		if(currentUserId!=-1) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter amount : ");
		double amount = sc.nextDouble();
		sc.close();
		if(amount <=10000.00) {
			wallet.setBalance(wallet.getBalance()+amount);
			System.out.println("your current balance is "+wallet.getBalance());
		}
		else {
			System.out.println("Maximum deposit is 10,000 ");
		}
	 
		 
	}
		else {
			System.out.println("user must log in to add money to wallet");
		}
	}

}
