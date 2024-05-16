package com.kartheek.java.clipayments;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.kartheek.java.clipayments.entity.User;

public class FileOps  {
	
	public final String PATH = "C:\\Users\\Dell\\Desktop\\cli\\data.csv";
	File f = new File(PATH);
	
	public void userToFile(User u) throws Exception{
		 
		FileWriter fw = new FileWriter(f,true);
		fw.write(u.toFile());
		fw.close();
	}
	
	public List<User> fileToUser() throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(f));
		List<User> userObjectList = new ArrayList<User>();
		String line;
		while((line = br.readLine())!= null) {
			String[] usrDet = line.split(",");
			User user = new User();
			user.setUserId(Integer.parseInt(usrDet[0]));
			user.setFirstName(usrDet[1]);
			user.setLastName(usrDet[2]);
			user.setPhoneNum(Long.parseLong(usrDet[3]));
//			user.setDateOfBirth(usrDet[4]);
			user.setCommunicationAddr(usrDet[5]);
			
			userObjectList.add(user);
		}
		
		br.close();
		return userObjectList;
		
		
	}

}
