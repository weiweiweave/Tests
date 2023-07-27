create table bank_Users(
  id int not null AUTO_INCREMENT,
  username varchar(100) null,
  password varchar(100) null,
  email_address varchar(100) null,
  creation_date_time datetime(6) null,
  PRIMARY KEY ( id ),
  CONSTRAINT user_info_username UNIQUE (username),
  CONSTRAINT user_info_email_address UNIQUE (email_address)
);

--ALTER TABLE bank_Users
--ADD CONSTRAINT user_info_username UNIQUE (username);

--ALTER TABLE bank_Users
--ADD CONSTRAINT user_info_password UNIQUE (password);

--ALTER TABLE bank_Users
--ADD CONSTRAINT user_info_email_Address UNIQUE (email_Address);

create table customers(
  id int not null AUTO_INCREMENT UNIQUE,
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
  PRIMARY KEY ( id ),
  CONSTRAINT customer_info_nric UNIQUE (nric)
);

create table account_Type(
  id int not null AUTO_INCREMENT,
  account_description varchar(100) null,
  PRIMARY KEY ( id )
);

create table bank_Accounts(
  id int not null AUTO_INCREMENT,
  uuid varchar(100) null,
  staff_id_who_key_in varchar(100) null,
  created_date date null,
  customer_nric varchar(100) null,
  balance double null,
  account_no varchar(100) null,
  account_type int null,
  account_type_id int null,
  creation_date_time datetime(6) null,
  PRIMARY KEY ( id ),
  CONSTRAINT FK_account_type FOREIGN KEY (account_type) REFERENCES account_Type(id),
  CONSTRAINT bank_account_info_account_no UNIQUE (account_no)
);

create table saving_Accounts(
  id int not null AUTO_INCREMENT,
  interest_rate double null,
  min_amount_to_cal_interest double null,
  PRIMARY KEY ( id )
);

create table fixedDeposit_Accounts(

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
  PRIMARY KEY ( id )
);