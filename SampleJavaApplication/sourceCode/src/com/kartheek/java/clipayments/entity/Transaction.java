package com.kartheek.java.clipayments.entity;

import java.time.LocalDate;

public class Transaction {
	TransactionType transactionType;
	Double transactionAmount;
	public Double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	LocalDate transactionDate;
	String transactionId;
	Wallet sourceWallet;
	Wallet destinationWallet;
	
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	 
	public LocalDate getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
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

}
