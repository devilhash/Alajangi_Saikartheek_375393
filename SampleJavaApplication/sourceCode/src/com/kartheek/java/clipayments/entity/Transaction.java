package com.kartheek.java.clipayments.entity;

//import java.time.LocalDate;
import java.util.Date;

public class Transaction {
	TransactionType transactionType;
	Double transactionAmount;
	public Double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
    public BankAccount getSourceAcct() {
		return sourceAcct;
	}
	public void setSourceAcct(BankAccount sourceAcct) {
		this.sourceAcct = sourceAcct;
	}
	public BankAccount getDestinationAcct() {
		return destinationAcct;
	}
	public void setDestinationAcct(BankAccount destinationAcct) {
		this.destinationAcct = destinationAcct;
	}
	Date transactionDate;
    long transactionId;
	Wallet sourceWallet;
	Wallet destinationWallet;
	BankAccount sourceAcct;
	BankAccount destinationAcct;
	
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	 
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
 
	public Wallet getSourceWallet() {
		return sourceWallet;
	}
	public void setSourceWallet(Wallet sourceWallet) {
		this.sourceWallet = sourceWallet;
	}
	public Wallet getDestinationWallet() {
		return destinationWallet;
	}
	public void setDestinationWallet(Wallet destinationWallet) {
		this.destinationWallet = destinationWallet;
	}
	
	@Override
	public String toString() {
		return  "Transaction Id : " + this.transactionId + "\n" + "\n Time : "+this.transactionDate +"\n"+ this.transactionType + "\n Amount : " + this.transactionAmount  ;}
	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

}
