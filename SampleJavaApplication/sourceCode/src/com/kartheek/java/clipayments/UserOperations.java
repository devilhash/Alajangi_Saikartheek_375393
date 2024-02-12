package com.kartheek.java.clipayments;
import com.kartheek.java.clipayments.entity.*;

public class UserOperations {
	User [] list = UserObject.usersList;
	public User doUserRegistration(String fName, String lName, String password, long phNum, String dob,String addr) {
		User u = new User();
		u.setFirstName(fName);
		u.setLastName(lName);
		u.setPhoneNum(phNum);
		u.setDateOfBirth(dob);
		u.setCommunicationAddr(addr);
		u.setPassword(password);
		
		u.setUserId();
		return u;
	}
	
	public void printUserList( ){
		for(int i=0;i<  list.length;i++) {
			if(list != null) {
				System.out.println("User Details of "+ list[i].getFirstName());
				System.out.println(list[i]);
			}
			
		}
	}

	public void currentUser() {
		 
		
	}

	public void userLogIn(int userId, String password) {
		 
		
	}

 

}
