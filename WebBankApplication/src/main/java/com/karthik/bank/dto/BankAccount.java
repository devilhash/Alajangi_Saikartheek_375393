package com.karthik.bank.dto;

public class BankAccount {
int userId;
String acctNumber;
String bankName;
String ifsc;
int pin;
int accTypeId;
double acctBalance;
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public String getAcctNumber() {
	return acctNumber;
}
public void setAcctNumber(String acctNumber) {
	this.acctNumber = acctNumber;
}
public String getBankName() {
	return bankName;
}
public void setBankName(String bankName) {
	this.bankName = bankName;
}
public String getIfsc() {
	return ifsc;
}
public void setIfsc(String ifsc) {
	this.ifsc = ifsc;
}
public int getPin() {
	return pin;
}
public void setPin(int pin) {
	this.pin = pin;
}
public int getAccTypeId() {
	return accTypeId;
}
public void setAccTypeId(int accTypeId) {
	this.accTypeId = accTypeId;
}
public double getAcctBalance() {
	return acctBalance;
}
public void setAcctBalance(double acctBalance) {
	this.acctBalance = acctBalance;
}


}
