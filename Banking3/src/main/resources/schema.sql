DROP TABLE IF EXISTS bank_Roles;
DROP TABLE IF EXISTS bank_Users;
DROP TABLE IF EXISTS customers;

DROP TABLE IF EXISTS bank_transactions;
DROP TABLE IF EXISTS saving_Accounts;
DROP TABLE IF EXISTS bank_Accounts;
DROP TABLE IF EXISTS account_Type;

create table bank_Users(
  bank_user_id int not null AUTO_INCREMENT,
  username varchar(100) null,
  password varchar(100) null,
  email_address varchar(100) null,
  active tinyint NOT NULL,
  creation_date_time datetime(6) null,
  PRIMARY KEY ( bank_user_id ),
  CONSTRAINT user_info_username UNIQUE (username),
  CONSTRAINT user_info_email_address UNIQUE (email_address)
);

create table bank_Roles(
  id int not null AUTO_INCREMENT,
  bank_user_id int null,
  role varchar(100) null,
  CONSTRAINT PK_bank_roles PRIMARY KEY ( id ),
  CONSTRAINT FK_bank_user_id FOREIGN KEY (bank_user_id) REFERENCES bank_Users(bank_user_id)
);

--ALTER TABLE bank_Users
--ADD CONSTRAINT user_info_username UNIQUE (username);

--ALTER TABLE bank_Users
--ADD CONSTRAINT user_info_password UNIQUE (password);

--ALTER TABLE bank_Users
--ADD CONSTRAINT user_info_email_Address UNIQUE (email_Address);

--ALTER TABLE BANK_ACCOUNTS
--ADD CONSTRAINT FK_some_column
--FOREIGN KEY (some_column) REFERENCES some_table(some_column);

create table customers(
  id int not null AUTO_INCREMENT,
  uuid varchar(100) null,
  staff_id_who_key_in varchar(100) null,
  company varchar(100) null,
  fund_source varchar(100) null,
  address varchar(500) null,
  city varchar(100) null,
  joined_date date null,
  nric varchar(100) null,
  first_name varchar(100) null,
  last_name varchar(100) null,
  sex varchar(100) null,
  email_address varchar(100) null,
  phone varchar(100) null,
  date_Of_birth date null,
  job_title varchar(100) null,
  creation_date_time datetime(6) null,
  CONSTRAINT PK_customers PRIMARY KEY ( id ),
  CONSTRAINT customer_info_nric UNIQUE (nric)
);

create table account_Type(
  id int not null AUTO_INCREMENT,
  account_description varchar(100) null,
  PRIMARY KEY ( id )
);

create table bank_Accounts(
  bank_account_id int not null AUTO_INCREMENT,
  uuid varchar(100) null,
  staff_id_who_key_in varchar(100) null,
  created_date date null,
  customer_nric varchar(100) null,
  balance double null,
  account_no varchar(100) null,
  account_type int null,
  sub_account_type_id int null,
  creation_date_time datetime(6) null,
  CONSTRAINT PK_bank_Accounts PRIMARY KEY ( bank_account_id),
  CONSTRAINT FK_account_type FOREIGN KEY (account_type) REFERENCES account_Type(id)
);

create table bank_transactions(
  id int not null AUTO_INCREMENT,
  uuid varchar(100) null,
  staff_id_who_key_in varchar(100) null,
  account_no varchar(100) null,
  amount double null,
  is_credit boolean null,
  remarks varchar(100) null,
  creation_date_time datetime(6) null,
  bank_account_id int null,
  CONSTRAINT PK_bank_transactions PRIMARY KEY ( id ),
  CONSTRAINT FK_bank_account_id FOREIGN KEY (bank_account_id) REFERENCES bank_Accounts(bank_account_id)
);

create table saving_Accounts(
  id int not null AUTO_INCREMENT,
  interest_rate double null,
  min_amount_to_cal_interest double null,
  CONSTRAINT PK_saving_Accounts PRIMARY KEY ( id )
);

--create table fixedDeposit_Accounts(
--
--);



