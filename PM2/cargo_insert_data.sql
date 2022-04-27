# Group 5 - CarGo
# Bingfan Tian, Bo Chen, 
# Jianqing Ma, Kaiwen Zhou, 
# Qihui Liu, Sen Yan
# CS5200 - PM2
# 2/24/2022

USE CarGoApplication;
DROP TABLE IF EXISTS Imports;

# Create a temp Imports table to hold raw data
create table Imports(
	ImportId 		int 	AUTO_INCREMENT,
    Year 			int,
    Make 			varchar(255),
    Model 			varchar(255),
    Trim 			varchar(255),
    Body 			varchar(255),
    Transmission 	varchar(255),
    Vin 			varchar(255),
    State 			varchar(255),
    Odometer 		int,
    CarCondition 	double,
    Color 			varchar(255),
    Interior 		varchar(255),
    Seller 			varchar(255),
    Mmr 			int,
    SellingPrice 	double,
    SaleDate 		varchar(255),
    constraint pk_RecordsId primary key (ImportId)
);

# Import raw data into Imports table
LOAD DATA INFILE '/usr/local/tmp/car_prices.csv' INTO TABLE Imports
  FIELDS TERMINATED BY ','
  OPTIONALLY ENCLOSED BY '"'
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES
  (@Year, @Make, @Model, @Trim, @Body, @Transmission, @Vin, @State, @CarCondition, @Odometer, @Color, @Interior, @Seller,
	@Mmr, @SellingPrice, @SaleDate)
	SET
	Year = nullif(@Year,''),
	Make = nullif(@Make,''),
	Model = nullif(@Model,''),
	Trim = nullif(@Trim,''),
    Body = nullif(@Body,''),
	Transmission = nullif(@Transmission,''),
	Vin = nullif(@Vin,''),
	State = nullif(@State,''),
    Odometer = nullif(@Odometer,''),
	CarCondition = nullif(@CarCondition,''),
	Color = nullif(@Color,''),
	Interior = nullif(@Interior,''),
	Seller = nullif(@Seller,''),
	Mmr = nullif(@Mmr,''),
	SellingPrice = nullif(@SellingPrice,''),
	SaleDate = nullif(@SaleDate,'')
;

# Since the original data is a transaction record, 
# there is a case that a vehicle is traded multiple times. 
# It is necessary to clean up the raw data and 
# remove the repeated vehicle vin numbers.
DROP TABLE IF EXISTS Imports_temp;

create table Imports_temp like Imports;

insert into Imports_temp
select *
from Imports
where Vin not in (
      SELECT Vin
		FROM Imports
		GROUP BY Vin
		HAVING COUNT(Vin) > 1
     );

drop table Imports;

alter table Imports_temp rename to Imports;

# remove the all vehicle before 2010.
DROP TABLE IF EXISTS Imports_temp;

create table Imports_temp like Imports;

insert into Imports_temp
select *
from Imports
where Year > 2010;

drop table Imports;

alter table Imports_temp rename to Imports;

# insert data into Cars tables after cleaning the data
insert into Cars(
	Vin, Year, Make, Model, 
    Trim, Body, Transmission, 
    State, Odometer, CarCondition, 
    Color, Interior, Mmr, SellingPrice)
select Vin, Year, Make, Model, 
		Trim, Body, Transmission, 
        State, Odometer, CarCondition, 
        Color, Interior, Mmr, SellingPrice
from Imports;

# insert data into dealer companies
DROP TABLE IF EXISTS Imports_temp;
create table Imports_temp like Imports;

insert into Imports_temp
select *
from Imports
where Seller not in (
      SELECT Seller
		FROM Imports
		GROUP BY Seller
		HAVING COUNT(Seller) > 1
     );

drop table Imports;

alter table Imports_temp rename to Imports;

# insert data into Companies
insert into Companies(
	CompanyName, Description)
select Seller, Seller
from Imports;

drop table Imports;

INSERT INTO Users(FirstName, LastName, Email, Password)
	VALUES('Tony', 'Bruce', 'tony.bruce@gmail.com', 'passwords');
INSERT INTO Users(FirstName, LastName, Email, Password)
	VALUES('Bingfan', 'Tian', 'tian.bin@gmail.com', 'passwords');
INSERT INTO Users(FirstName, LastName, Email, Password)
	VALUES('Bo', 'Chen', 'chen.bo@gmail.edu', 'passwords');
INSERT INTO Users(FirstName, LastName, Email, Password)
	VALUES('Sen', 'Yan', 'yan.sen@gmail.com', 'passwords');
INSERT INTO Users(FirstName, LastName, Email, Password)
	VALUES('Kaiwen', 'Zhou', 'zhou.kai@gmail.com', 'passwords');
INSERT INTO Users(FirstName, LastName, Email, Password)
	VALUES('Qihui', 'Liu', 'liu.qi@gmail.com', 'passwords');
INSERT INTO Users(FirstName, LastName, Email, Password)
	VALUES('Jianqing', 'Ma', 'ma.jian@gmail.com', 'passwords');
    
INSERT INTO Admins(UserId)
	VALUES(1);
    
INSERT INTO Buyers(UserId, DOB, ZIP)
	VALUES(2, '1996.10.19', 98103);
INSERT INTO Buyers(UserId, DOB, ZIP)
	VALUES(3, '1999.08.21', 98115);
INSERT INTO Buyers(UserId, DOB, ZIP)
	VALUES(4, '1997.02.02', 98104);
    
INSERT INTO Sellers(UserId, CompanyId, ZIP, Address, Introduction)
	VALUES(5, 1, 98103, 'Address', 'Introduction');
INSERT INTO Sellers(UserId, CompanyId, ZIP, Address, Introduction)
	VALUES(6, 1, 98103, 'Address', 'Introduction');
INSERT INTO Sellers(UserId, CompanyId, ZIP, Address, Introduction)
	VALUES(7, 2, 98115, 'Address', 'Introduction');
    
INSERT INTO Saves(UserId, Vin)
	VALUES(2, '1c3ccceg5fn565081');
INSERT INTO Saves(UserId, Vin)
	VALUES(2, '1c3cdfbb0fd227486');
INSERT INTO Saves(UserId, Vin)
	VALUES(2, '1c3cccag8fn516513');
INSERT INTO Saves(UserId, Vin)
	VALUES(2, '1fm5k7f86fga20478');
INSERT INTO Saves(UserId, Vin)
	VALUES(3, '1fm5k8f82fga20581');
INSERT INTO Saves(UserId, Vin)
	VALUES(3, '1c3ccceg5fn565081');
INSERT INTO Saves(UserId, Vin)
	VALUES(3, '1g11c5sl8ff191035');
INSERT INTO Saves(UserId, Vin)
	VALUES(3, '1gc1kue87ff109372');
INSERT INTO Saves(UserId, Vin)
	VALUES(3, '1gnkrgkd2fj106435');
INSERT INTO Saves(UserId, Vin)
	VALUES(3, '1gkkrrkd7fj120243');

INSERT INTO Messages(Content, FromId, ToId)
	VALUES('This is a message', 2, 5);
INSERT INTO Messages(Content, FromId, ToId)
	VALUES('This is a message', 3, 5);
INSERT INTO Messages(Content, FromId, ToId)
	VALUES('This is a message', 3, 6);
INSERT INTO Messages(Content, FromId, ToId)
	VALUES('This is a message', 3, 7);
INSERT INTO Messages(Content, FromId, ToId)
	VALUES('This is a message', 4, 5);
INSERT INTO Messages(Content, FromId, ToId)
	VALUES('This is a message', 4, 6);
    
INSERT INTO Reviews(ReviewContent, Rating, BuyerId, SellerId)
	VALUES('This is a content', 5.0, 2, 5);
INSERT INTO Reviews(ReviewContent, Rating, BuyerId, SellerId)
	VALUES('This is a content', 4.0, 2, 6);
INSERT INTO Reviews(ReviewContent, Rating, BuyerId, SellerId)
	VALUES('This is a content', 4.5, 3, 5);
INSERT INTO Reviews(ReviewContent, Rating, BuyerId, SellerId)
	VALUES('This is a content', 3.0, 4, 7);
    
insert into Cars(
	Vin, Year, Make, Model, 
    Trim, Body, Transmission, 
    State, Odometer, CarCondition, 
    Color, Interior, Mmr, SellingPrice, UserId)
    VALUES('1C4RJFAG6JC499308', 2018, 'Jeep', 'Grand Cherokee', 'Limited', 'SUV', 'automatic', 
		'WA', 43990, 4.8, 'Black', 'Black', 29000, 35000, 3);

insert into Cars(
	Vin, Year, Make, Model, 
    Trim, Body, Transmission, 
    State, Odometer, CarCondition, 
    Color, Interior, Mmr, SellingPrice, UserId)
    VALUES('1C4RJFAG6JC491234', 2017, 'BMW', 'X6', 'sport', 'SUV', 'automatic', 
    'WA', 43990, 4.8, 'Blue', 'Black', 29000, 35000, 3);
