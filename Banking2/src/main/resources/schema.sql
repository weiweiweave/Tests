create table bank_Users(
  id int not null AUTO_INCREMENT,
  username varchar(100) null,
  password varchar(100) null,
  email_Address varchar(100) null,
  creation_Date_Time datetime(6) null,
  PRIMARY KEY ( id ),
  CONSTRAINT user_info_username UNIQUE (username),
  CONSTRAINT user_info_email_Address UNIQUE (email_Address)
);

--ALTER TABLE bank_Users
--ADD CONSTRAINT user_info_username UNIQUE (username);

--ALTER TABLE bank_Users
--ADD CONSTRAINT user_info_password UNIQUE (password);

--ALTER TABLE bank_Users
--ADD CONSTRAINT user_info_email_Address UNIQUE (email_Address);

create table customers(
  id int not null AUTO_INCREMENT UNIQUE,
  staff_id_who_key_in varchar(100) null,
  company varchar(100) null,
  fund_Source varchar(100) null,
  address varchar(500) null,
  city varchar(100) null,
  joined_Date date null,
  nric varchar(100) null,
  first_Name varchar(100) null,
  last_Name varchar(100) null,
  sex varchar(100) null,
  email_Address varchar(100) null,
  phone varchar(100) null,
  date_Of_Birth date null,
  job_Title varchar(100) null,
  creation_Date_Time datetime(6) null,
  PRIMARY KEY ( id ),
  CONSTRAINT customer_info_nric UNIQUE (nric)
);