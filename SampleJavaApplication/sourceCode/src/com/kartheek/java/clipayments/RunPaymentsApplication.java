package com.kartheek.java.clipayments;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.kartheek.java.clipayments.entity.AcctType;
import com.kartheek.java.clipayments.entity.BankAccount;
import com.kartheek.java.clipayments.entity.Transaction;
import com.kartheek.java.clipayments.entity.TransactionType;
import com.kartheek.java.clipayments.entity.User;
import com.kartheek.java.clipayments.entity.Wallet;
import com.kartheek.java.clipayments.sql.SqlDAO;

public class RunPaymentsApplication {

	static int x=10;
   static List<User> userList = new ArrayList<User> ();
   static List<BankAccount> acctList = new ArrayList<BankAccount> ();


   public static int currentUserId = -1;

    public static Map<Integer, Wallet> walletList = new HashMap<Integer,Wallet>();




	public static void main(String[] args) {
//		FileOps fileOps = new FileOps();
//		 try {
//			List<User> userData = fileOps.fileToUser();
//			for(User u : userData) {
//				System.out.println(u.getUserId()+" "+u.getFirstName()+" "+u.getLastName()+" "+u.getPhoneNum()+" "+u.getDateOfBirth()+" "+u.getCommunicationAddr());
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//	}
//		

		int selectedOption=0;		
		Scanner opt = new Scanner(System.in);

		while(true) {
			System.out.println("Payments App Actions:");
			System.out.println("1. Register New User");
			System.out.println("2. Login");
			System.out.println("3. ADD Bank Account");
			System.out.println("4. List of Users");
			System.out.println("5. Current User");
			System.out.println("6.Add money to Wallet");
			System.out.println("7.Check Wallet Balance");
			System.out.println("8.Get Account List     ");
			System.out.println("9.log out");
			System.out.println("10.Transaction");
			System.out.println("11.check Account Balalance");
			System.out.println("-1. Quit ");


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
				 logIn();


			}else if(optStr.equalsIgnoreCase("3")) {
				if(currentUserId!=-1) {
					addBankAccount();
				}
				else {
					System.out.println("User must logIn to add bankAccount");
				}
				 

			}else if(optStr.equalsIgnoreCase("4")) {
				ops.printUserList( );
			}else if(optStr.equalsIgnoreCase("-1")) {
				break;
			}else if(optStr.equalsIgnoreCase("5")) {
				  try {
					SqlDAO dao = new SqlDAO();
					 System.out.println(dao.getCurrentUser());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(optStr.equalsIgnoreCase("6")) {
				   addMoney();
			}
			else if(optStr.equalsIgnoreCase("7")) {
				try {
					SqlDAO dao = new SqlDAO();
					System.out.println("WalletBalance :"+dao.showWalletBalance());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}else if(optStr.equalsIgnoreCase("9")) {
				  logout();
			}
			else if(optStr.equalsIgnoreCase("8")) {
				try {
					SqlDAO dao = new SqlDAO();
					dao.showBankAcounts();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(optStr.equalsIgnoreCase("10")) {
				transaction() ;
			}else if(optStr.equalsIgnoreCase("11")) {
				checkAcctBalance() ;
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
		String date = opt.next();
		DateTimeFormatter dateFormat =  DateTimeFormatter.ofPattern("dd-MM-yyyy");
		 LocalDate dob = LocalDate.parse(date,dateFormat);
		System.out.println(dob);
		System.out.println("Address:");
		String addr = opt.next();
		System.out.println("Password:");
		String password = opt.next();
	 
		
				UserOperations ops = new UserOperations();
				User u = ops.doUserRegistration(fName, lName, password, phNo,  dob, addr);
				
				try {
					SqlDAO sqlDao = new SqlDAO();
					sqlDao.addUserToDataBase(u);
				}catch(Exception e){
					e.printStackTrace();
				}

			     userList.add(u);
			   Wallet wallet = new Wallet();
			   int usrId = u.getUserId();
			   walletList.put(usrId, wallet);


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


			 try {
				SqlDAO dao = new SqlDAO();
				if(dao.verifyUser(userId, password)) {
					System.out.println("User successfully logged in ");
				}
				else {
					System.out.println("user not found");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) { 
				e.printStackTrace();
			} catch ( Exception e) {
				e.printStackTrace();
			}
			}
			else {
				System.out.println("To log in to another account you must log out the current user");
			}
	}
	private static void addBankAccount() {
		BankAccount bankAccount = new BankAccount();
		UserOperations ops = new UserOperations();
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
			e.printStackTrace();

		}


		System.out.println("Enter Account Pin");
		String pin = sc.next();


		bankAccount.setBankName(bankName);
		bankAccount.setAcctNumber(acctNumber);
		bankAccount.setIFSC(ifsc);
		bankAccount.setAcctPin(pin);
		bankAccount.setUserid(currentUserId);
		bankAccount.setAcctBalance(500);
		List<BankAccount> temp = new ArrayList<>();
//		for(User u : userList) {
//			if(u.getUserId()==currentUserId) {
//				u.getBankList().add(bankAccount);	
//			}
//		}
		acctList.add(bankAccount);
		try {
			SqlDAO sqlDao = new SqlDAO();
			sqlDao.addAccountToDataBase(bankAccount);
			
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		









	}
	private static void addMoney() {

		if(currentUserId!=-1) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter amount : ");
		double amount = sc.nextDouble();
		if(amount <=10000.00) {
			 try {
				SqlDAO dao = new SqlDAO();
				System.out.println(dao.addMoneyToWallet(amount));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Maximum deposit is 10,000 ");
		}


	}
		else {
			System.out.println("user must log in to add money to wallet");
		}
	}
	private static void logout() {
		currentUserId = -1;
	}
//	private static String accountTypeList() {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Select Your Account type from the following : ");
//		for(AcctType type : AcctType.values()) {
//			System.out.println("      "+type);
//		}
//		String actType = sc.next();
//		sc.close();
//		return actType;
//		
//	}
	/**
	 * 
	 */
	public static void printUserBAnkAcctsList() {
		UserOperations ops = new UserOperations();
		Map<User,List<BankAccount>> mapItems = ops.getBankAccountList();

		for(User u:mapItems.keySet()) {
			if(u.getUserId()==currentUserId) {
			List<BankAccount> //baList = new ArrayList<BankAccount>();
					baList = mapItems.get(u);
			System.out.println(u);
			if(baList != null) {
				for(BankAccount ba: baList) {
					System.out.println("--"+ba.getAcctNumber());
				}
			}
			}

		}
	}
	public static void transaction() {
		 if(currentUserId!= -1) {
			    Scanner sc = new Scanner(System.in);
			    Transaction transaction = new Transaction();
			      Date date = new Date();
			     UserOperations ops = new UserOperations();
			     int i = 1;
				 for(TransactionType transactionType : TransactionType.values()) {
					 System.out.println(i+" "+transactionType);
					 i++;
				 }
				 System.out.println("select an option to perform : ");
				 int option = sc.nextInt();
				 if(option==1) {
					 transaction.setTransactionType(TransactionType.DEBIT);
					 System.out.println("Select which type of transfer you want to perform");
					 System.out.println("1.Wallet to Wallet");
					 System.out.println("2.Bank to Bank");
					 System.out.println("3.Bank to Wallet");
					 System.out.println("4.Wallet to Bank");
					 switch(option) {
					 case 1 :  try {
							walletToWallet();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					 }
					 
				 
 
 
			 
				 }
		 }




 
	}
	public static void checkAcctBalance() {
		System.out.println("Enter account number");
		 Scanner sc = new Scanner(System.in);
		 String acctNum = sc.next();
		  
		  try {
			SqlDAO dao = new SqlDAO();
			 System.out.println("balance : "+dao.showAccountBalance(acctNum));;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void walletToWallet() throws ClassNotFoundException, SQLException {
		 Scanner sc = new Scanner(System.in);
		System.out.println("Enter amount :");
		double amount = sc.nextDouble();
		System.out.println("Enter receiver's UserId :");
		int receiver = sc.nextInt();
		SqlDAO dao = new SqlDAO();
		if(dao.transactionWalletToWallet(amount, receiver)) {
			System.out.println("transaction successfull");
		}
		else {
			System.out.println("transation failed");
		}
		
	}
	 



	}
