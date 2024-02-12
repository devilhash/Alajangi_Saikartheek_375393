package com.kartheek.java.clipayments;

import java.util.Scanner;

import com.kartheek.java.clipayments.entity.User;

public class RunPaymentsApplication {

	static int x=10;
    static User[] userList =  new User[UserObject.usersList.length];  
    userList = UserObject.usersList;

	public static void main(String[] args) {
		
		int selectedOption=0;		
		Scanner opt = new Scanner(System.in);
				
		while(true) {
			System.out.println("Payments App Actions:");
			System.out.println("1. Register New User");
			System.out.println("2. Login");
			System.out.println("3. ADD Bank Account");
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
				
				
				User u = ops.doUserRegistration(fName, lName, password, phNo, dob, addr);
				for(int i=0;i<userList.length;i++) {
					if( userList[i] != null) {
						 continue;
					}
					 userList[i] = u;
					break;
					 
				}
			}else if(optStr.equalsIgnoreCase("2")) {
				System.out.println("enter User credentials to login ");
				System.out.println();
				System.out.println("Enter UserId : ");
				int userId = opt.nextInt();
				System.out.println("Enter password : ");
				String password = opt.next();
				
				ops.userLogIn ( userId, password);
				
				
			}else if(optStr.equalsIgnoreCase("3")) {
				
			}else if(optStr.equalsIgnoreCase("4")) {
				ops.printUserList( );
			}else if(optStr.equalsIgnoreCase("-1")) {
				break;
			}else if(optStr.equalsIgnoreCase("5")) {
				 ops.currentUser();
			}
			else {
				
			}
		}
		opt.close();
	}

}
