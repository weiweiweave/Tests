create table bank_Users(
  id int not null AUTO_INCREMENT UNIQUE,
  username varchar(100) null,
  password varchar(100) null,
  first_Name varchar(100) null,
  last_Name varchar(100) null,
  address varchar(500) null,
  email_Address varchar(100) null,
  nric varchar(100) null,
  fund_Source varchar(100) null,
  job_Title varchar(100) null,
  job_Description varchar(100) null,
  date_Of_Birth date null,
  creation_Date_Time datetime(6) null,
  PRIMARY KEY ( id )
);