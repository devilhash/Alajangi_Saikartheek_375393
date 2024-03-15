package com.kartheek.java.clipayments;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
				 ops.currentUser();
			}else if(optStr.equalsIgnoreCase("6")) {
				   addMoney();
			}
			else if(optStr.equalsIgnoreCase("7")) {

				System.out.println(ops.checkWalletBalance());
			}else if(optStr.equalsIgnoreCase("9")) {
				  logout();
			}
			else if(optStr.equalsIgnoreCase("8")) {
				printUserBAnkAcctsList();
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
		String dob = opt.next();
		System.out.println("Address:");
		String addr = opt.next();
		System.out.println("Password:");
		String password = opt.next();
				UserOperations ops = new UserOperations();
				User u = ops.doUserRegistration(fName, lName, password, phNo, dob, addr);
				
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


			UserOperations ops = new UserOperations();

			ops.userLogIn ( userId, password);
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
		bankAccount.setAcctBalance(1200);
		List<BankAccount> temp = new ArrayList<>();
		for(User u : userList) {
			if(u.getUserId()==currentUserId) {
				u.getBankList().add(bankAccount);	
			}
		}
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
			UserOperations ops = new UserOperations();
			ops.addMoneyToWallet(amount);
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
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
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
				 boolean result;

				 int transferType = sc.nextInt();
				 System.out.println("Enter Transaction Amount : ");
				 double tAmount = sc.nextDouble();
				 transaction.setTransactionAmount(tAmount);
				 transaction.setTransactionDate(formatter.format(date));
				 transaction.setTransactionId(date.getTime());
				 transaction.setUserId();

				 switch(transferType) {
				 case 1 :   Wallet source = walletList.get(currentUserId);
				 transaction.setSourceWallet(source);
				 System.out.println("enter receiver userId : ");
				 int receiver = sc.nextInt();
				 Wallet destination = walletList.get(receiver);
				 transaction.setDestinationWallet(destination);
				    result =ops.doTransaction(source, destination, transaction.getTransactionType(),tAmount);
				    if(result) {
						 System.out.println("transaction successful");
					 }
					 else {
						 System.out.println("Transaction failed");
					 }

				 break;

				 case 2 :  System.out.println("Enter sender bankaccount number : ");
				 String senderAcctNum = sc.next();
				 BankAccount source2 = null;
				 List<BankAccount> userAccountList = new ArrayList<BankAccount>();
				 Map<User,List<BankAccount>> mapItems = ops.getBankAccountList();
					for(User u:mapItems.keySet()) {
						if(u.getUserId()==currentUserId) {
							userAccountList =  mapItems.get(u);
						}
					}
					for(BankAccount b : userAccountList) {
						if(b.getAcctNumber().equals(senderAcctNum)) {
							source2 = b;
						}
					}
					if(source2 != null) {
						 System.out.println("Enter receiver bankaccount number : ");
						 String recieverAcctNum = sc.next();
						 BankAccount destination2 = null;
						 for(BankAccount b : acctList) {
							 if(b.getAcctNumber().equals(recieverAcctNum)) {
								 destination2 = b;
							 }
						 }

						 transaction.setSourceAcct(source2);
						 transaction.setDestinationAcct(destination2);
						  result =ops.doTransaction(source2, destination2, transaction.getTransactionType(),tAmount);
						  if(result) {
								 System.out.println("transaction successful");
							 }
							 else {
								 System.out.println("Transaction failed");
							 }
				           }
					break;
				 case 3 :  System.out.println("Enter sender bankaccount number : ");
				 String senderAcctNumBankToWallet = sc.next();
				 BankAccount sourceBankToWallet = null;

				 List<BankAccount> userAccountListBankToWallet = new ArrayList<BankAccount>();
				 Map<User,List<BankAccount>> mapItemsBankToWallet = ops.getBankAccountList();
					for(User u:mapItemsBankToWallet.keySet()) {
						if(u.getUserId()==currentUserId) {
							userAccountListBankToWallet =  mapItemsBankToWallet.get(u);
						}
					}
					for(BankAccount b : userAccountListBankToWallet) {
						if(b.getAcctNumber().equals(senderAcctNumBankToWallet)) {
							  sourceBankToWallet = b;
							transaction.setSourceAcct(sourceBankToWallet);
						}
					}

					 System.out.println("enter receiver userId : ");
					 int receiverId = sc.nextInt();
					 Wallet destinationWallet = walletList.get(receiverId);
					 transaction.setDestinationWallet(destinationWallet);
					 result =ops.doTransaction(sourceBankToWallet, destinationWallet, transaction.getTransactionType(),tAmount);
					 if(result) {
//						 Transaction recipientTransaction = new Transaction();
//						 recipientTransaction.setTransactionAmount(tAmount);
//						 transaction.setTransactionDate(formatter.format(date));
//						 recipientTransaction.setTransactionId(date.getTime());
//						 recipientTransaction.setUserId( destinationWallet.getUserId());
//						 recipientTransaction.setSourceAcct(sourceBankToWallet);
						 System.out.println("Transaction successful");
						 }
					 else {
						 System.out.println("Transaction failed");
					 }
					break;
				 case 4 :  Wallet sourceWallet = walletList.get(currentUserId);
				           transaction.setSourceWallet(sourceWallet);

				           System.out.println("Enter receiver bankaccount number : ");
							String recieverAcctNum = sc.next();
							 BankAccount destinationAccount = null;
							 for(BankAccount b : acctList) {
								 if(b.getAcctNumber().equals(recieverAcctNum)) {
									 destinationAccount = b;
								 }

							 }
							 transaction.setDestinationAcct(destinationAccount);
							 result =ops.doTransaction(sourceWallet, destinationAccount, transaction.getTransactionType(),tAmount);
							 if(result) {
								 System.out.println("transaction successful");
							 }
							 else {
								 System.out.println("Transaction failed");
							 }
							 break;

						default : System.out.println("please enter correct option");
			 }


			 }


			 else if(option==2) {
				 transaction.setTransactionType(TransactionType.CREDIT);
				 transaction.setTransactionDate(formatter.format(date));
				 transaction.setTransactionId(date.getTime());
				 transaction.setUserId();
				 System.out.println("Enter Account Number : ");
				 String targetAcctNum = sc.next ();
				 System.out.println("Enter Transaction Amount : ");
				 double tAmount = sc.nextDouble();
				 BankAccount source = null;
				 List<BankAccount> userAccountList = new ArrayList<BankAccount>();
				 Map<User,List<BankAccount>> mapItems = ops.getBankAccountList();
					for(User u:mapItems.keySet()) {
						if(u.getUserId()==currentUserId) {
							userAccountList =  mapItems.get(u);
						}
					}
					for(BankAccount b : userAccountList) {
						if(b.getAcctNumber().equals(targetAcctNum)) {
							source = b;
						}
					}
					transaction.setSourceAcct(source);
					ops.creditAmountToAccount(source, tAmount);
			 }
			 
			  try {
				  SqlDAO sqlDao = new SqlDAO();
				  sqlDao.addTransactionDetailsToDataBase(transaction);
			  }catch(ClassNotFoundException e) {
				  e.printStackTrace();
			  }
			  catch(SQLException e) {
				  e.printStackTrace();
			  }
			  catch(Exception e) {
				   e.printStackTrace();
			  }






	}
	}
	public static void checkAcctBalance() {
		System.out.println("Enter account number");
		 Scanner sc = new Scanner(System.in);
		 String acctNum = sc.next();
		 for(BankAccount b : acctList) {
			 if(b.getAcctNumber().equals(acctNum)) {
				 System.out.println( b.getAcctBalance());
			 }
		 }

	}
	 



	}
