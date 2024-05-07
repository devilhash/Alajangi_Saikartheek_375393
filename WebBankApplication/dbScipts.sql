 use bank;
 
 create table user_info(
 user_id int not null,
 first_name varchar(50) not null,
 last_name varchar(50) not null,
 address varchar(50) not null,
 phNo varchar(50) NOT NULL UNIQUE,
 date_of_birth date not null,
 wallet_balance double default 0.00,
 password varchar(50) not null,
 primary key(user_id));
 
ALTER TABLE user_info MODIFY user_id INT NOT NULL AUTO_INCREMENT;
select * from user_info;

create table bank_account_types(
	bank_acct_typeid int,
    bank_acct_type_code varchar(3),
    bank_acct_type_desc varchar(50),
    primary key(bank_acct_typeid)
 );
 
 insert into bank_account_types values(1,'SA','SAVINGS ACCOUNT');
insert into bank_account_types values(2,'CA','CURRENT ACCOUNT');

CREATE TABLE bank_accounts (
   bank_acct_no varchar(16) NOT NULL,
   bank_name VARCHAR(50),
   acct_type_id int,
   bank_ifsc VARCHAR(10),
   acct_pin INT,
   user_id INT,
   acct_balance double(12,2),
    FOREIGN KEY (user_id) REFERENCES user_info (user_id),
    FOREIGN KEY (acct_type_id) REFERENCES bank_account_types (bank_acct_typeid),
    PRIMARY KEY (bank_acct_no)
);
select * from bank_accounts;