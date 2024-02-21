package com.kartheek.java.clipayments;
import java.util.List;

import com.kartheek.java.clipayments.entity.*;

public class UserOperations {
	 List<User>list = RunPaymentsApplication.userList;
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
		 for(User u : list) {
			 if(RunPaymentsApplication.currentUserId == u.getUserId()) {
				 System.out.println(u.getFirstName()+" "+u.getLastName());
			 }
			 else {
				 System.out.println("please enter valid credentials");
			 }
		 }
		
	}

	public void userLogIn(int userId, String password) {
		for(User u : list) {
			if(u.getUserId()==userId&&u.getPassword( ).equals(password))
				System.out.println("welcome Mr."+u.getFirstName()+" "+u.getLastName());
			   RunPaymentsApplication.currentUserId = userId;
			}
		}
		 
		
 
 

}
