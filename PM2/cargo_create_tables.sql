# Group 5 - CarGo
# Bingfan Tian, Bo Chen, 
# Jianqing Ma, Kaiwen Zhou, 
# Qihui Liu, Sen Yan
# CS5200 - PM2
# 2/24/2022

CREATE SCHEMA IF NOT EXISTS CarGoApplication;
USE CarGoApplication;

DROP TABLE IF EXISTS Messages;
DROP TABLE IF EXISTS Reviews;
DROP TABLE IF EXISTS Saves;
DROP TABLE IF EXISTS Cars;
DROP TABLE IF EXISTS Searches;
DROP TABLE IF EXISTS Sellers;
DROP TABLE IF EXISTS Buyers;
DROP TABLE IF EXISTS Admins;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Companies;


CREATE TABLE Companies (
 CompanyId 		INT 	AUTO_INCREMENT,
 CompanyName 	VARCHAR(255),
 Description 	VARCHAR(1023),
 CONSTRAINT pk_Companies_CompanyId PRIMARY KEY (CompanyId)
);

CREATE TABLE Users (
 UserId 		INT 	AUTO_INCREMENT,
 FirstName 		VARCHAR(255) 	NOT NULL,
 LastName 		VARCHAR(255)	NOT NULL,
 Email 			VARCHAR(255) 	NOT NULL,
 Password VARCHAR(255) NOT NULL,
 CONSTRAINT pk_Users_UserId PRIMARY KEY (UserId),
 CONSTRAINT uq_Users_Email UNIQUE(Email)
);

CREATE TABLE Admins (
 UserId 	INT,
 CONSTRAINT pk_Admins_UserId PRIMARY KEY (UserId),
 CONSTRAINT fk_Admins_UserId FOREIGN KEY (UserId)
  REFERENCES Users(UserId)
  ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Buyers (
 UserId 		INT,
 DOB 			DATE 	NOT NULL,
 ZIP 			INT 	NOT NULL,
 CONSTRAINT pk_Buyers_UserId PRIMARY KEY (UserId),
 CONSTRAINT fk_Buyers_UserId FOREIGN KEY (UserId)
  REFERENCES Users(UserId)
  ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Sellers (
 UserId 		INT,
 CompanyId 		INT,
 ZIP 			INT 			NOT NULL,
 Address 		VARCHAR(255) 	NOT NULL,
 Introduction 	VARCHAR(1023),
 CONSTRAINT pk_Sellers_UserId PRIMARY KEY (UserId),
 CONSTRAINT fk_Sellers_UserId FOREIGN KEY (UserId)
  REFERENCES Users(UserId)
  ON UPDATE CASCADE ON DELETE CASCADE,
 CONSTRAINT fk_Sellers_CompanyId FOREIGN KEY (CompanyId)
  REFERENCES Companies(CompanyId)
  ON UPDATE CASCADE ON DELETE SET NULL
);


create table Searches(
    SearchId 		int 	auto_increment,
    Year 			int,
    Make 			varchar(255),
    Model 			varchar(255),
    Trim 			varchar(255),
    Body 			varchar(255),
    Transmission 	varchar(255),
    State 			varchar(255),
    Odometer 		int,
    CarCondition 	double,
    Color 			varchar(255),
    Interior 		varchar(255),
    UserId 			int,
    constraint pk_Search_SearchId primary key (SearchId),
    constraint fk_Search_UserId foreign key (UserId) references Users(UserId)
);

create table Cars(
    Vin 			varchar(255)	Not null,
    Year 			int,
    Make 			varchar(255),
    Model 			varchar(255),
    Trim 			varchar(255),
    Body 			varchar(255),
    Transmission 	varchar(255),
    State 			varchar(255),
    Odometer 		int,
    CarCondition 	double,
    Color 			varchar(255),
    Interior 		varchar(255),
    Mmr 			int,
    SellingPrice	int,
    UserId 		int,
    constraint pk_Search_Vin primary key (Vin),
    constraint fk_Search_SellerId foreign key (UserId) references Users(UserId)
);

create table Reviews(
    ReviewId 		int 	auto_increment,
    Date 			timestamp,
    ReviewContent 	varchar(255),
    Rating 			double,
    BuyerId 		int,
    SellerId 		int,
    constraint pk_Review_ReviewId PRIMARY KEY (ReviewId),
    constraint fk_Review_BuyerId foreign key (BuyerId) references Users(UserId),
    constraint fk_Review_SellerId foreign key (SellerId) references Users(UserId)
);

CREATE TABLE Messages (
	MessageId 		INT 			AUTO_INCREMENT,
	SentTime 		TIMESTAMP 		DEFAULT CURRENT_TIMESTAMP 	NOT NULL,
	Content 		VARCHAR(1023) 	NOT NULL,
	FromId 			INT,
	ToId 			INT,
	CONSTRAINT pk_Messages_MessageId PRIMARY KEY(MessageId),
	CONSTRAINT fk_Messages_FromId FOREIGN KEY(FromId) REFERENCES Users(UserId)
	ON UPDATE CASCADE ON DELETE SET NULL,
	CONSTRAINT fk_Messages_ToId FOREIGN KEY(ToId) REFERENCES Users(UserId)
	ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE Saves(
	SaveId 		INT 	AUTO_INCREMENT,
	Vin 		VARCHAR(255),
	UserId 		INT,
	CONSTRAINT pk_Saves_SaveId PRIMARY KEY(SaveId),
	CONSTRAINT fk_Saves_Vin FOREIGN KEY(Vin) REFERENCES Cars(Vin)
	ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_Saves_UserId FOREIGN KEY(UserId) REFERENCES Users(UserId)
	ON UPDATE CASCADE ON DELETE CASCADE
);
