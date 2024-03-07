create database cliPayments;

use cliPayments;

create table User(
UserId  int NOT NULL,
FirstName  varchar(20) NOT NULL,
SecondName varchar(20) NOT NULL,
PhoneNumber int NOT NULL,
DateOFBirth Date NOT NULL,
Address varchar(20) ,
Passcode char(15),
primary key(UserId));

alter table User
modify column Passcode char(15) not null;

describe User;

create table Wallet(
UserId int not null,
WalletBalance double default 0.00,
foreign key(UserId) references User(UserId));


describe   Wallet;

create table BankAccount(
UserId int not null,
AccountNumber varchar(20) not null,
BankName varchar(20) not null,
IFSC_Code varchar(20) not null,
AccountType enum('SAVINGS','CURRENT','LOAN','SALARY') NOT NULL,
AccountBalance double not null default 0.00,
primary key(AccountNumber),
foreign key(UserId) references User(UserId));

describe BankAccount;

alter table User
add column WalletBalance double not null default 0.00;

describe User;

create table Transactions(
TransactionId varchar(20) not null,
TransactionDate Date not null,
TransactionType enum('CREDIT','DEBIT'),
TransactionAmount double not null,
UserId int not null,
primary key(TransactionId),
foreign key(UserId) references User(UserId));

describe Transactions;

 


