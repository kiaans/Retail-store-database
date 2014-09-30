CREATE SCHEMA Group_B207;

SET SEARCH_PATH TO Group_B207;

CREATE TABLE Movies(
Movie_ID varchar(6) PRIMARY KEY,
Movie_Name varchar(100) NOT NULL,
Director_ID varchar(6),
Prod_House_ID varchar(6),
Year_Of_Release numeric(4,0) NOT NULL,
Budget_Mil_$ numeric(6,2) NOT NULL,
Rating numeric (3,1) NOT NULL,
Stock numeric (3,0) NOT NULL,
Demand numeric (3,0) NOT NULL DEFAULT 0,
Price_$ numeric (4,2) NOT NULL CHECK (Price_$ > 0),
Copies_Sold numeric (4,0) NOT NULL DEFAULT 0,
About varchar(500));

CREATE TABLE Actors(
Actor_ID varchar(6) PRIMARY KEY,
Actor_Name varchar(50) NOT NULL,
Date_Of_Birth date NOT NULL,
Country varchar(30) NOT NULL,
Limelighted_By varchar(6) REFERENCES Movies,
Number_Of_Movies numeric (3,0) NOT NULL,
About varchar(500));

CREATE TABLE Directors(
Director_ID varchar(6) PRIMARY KEY,
Director_Name varchar(50) NOT NULL,
Date_Of_Birth date NOT NULL,
Country varchar(30) NOT NULL,
Limelighted_By varchar(6) REFERENCES Movies,
Number_Of_Movies numeric (3,0) NOT NULL,
About varchar(500));

CREATE TABLE Production_House(
Prod_ID varchar(6) PRIMARY KEY,
Prod_Name varchar(50) NOT NULL,
Year_Formed numeric (4,0) NOT NULL,
Country varchar(30) NOT NULL,
Number_Of_Movies numeric (5,0) NOT NULL);

CREATE TABLE Acted_In(
Actor_ID varchar(6) NOT NULL REFERENCES Actors,
Movie_ID varchar(6) NOT NULL REFERENCES Movies,
Role varchar(50) NOT NULL,
PRIMARY KEY (Actor_ID, Movie_ID));

CREATE TABLE Games(
Game_ID varchar(6) PRIMARY KEY,
Game_Name varchar(50) NOT NULL,
Developer_ID varchar(6),
Game_Platform varchar(30) NOT NULL,
Game_Genre_ID varchar(6) NOT NULL,
Year_Released numeric (4,0) NOT NULL,
Rating numeric (3,1) NOT NULL,
Stock numeric (3,0) NOT NULL,
Demand numeric (3,0) NOT NULL DEFAULT 0,
Price_$ numeric (4,2) NOT NULL CHECK (Price_$ > 0),
Copies_Sold numeric (4,0) NOT NULL DEFAULT 0);

CREATE TABLE Developers(
Developer_ID varchar(6) PRIMARY KEY,
Developer_Name varchar(50) NOT NULL,
Year_Formed numeric(4,0) NOT NULL,
Games_Developed numeric(4,0) NOT NULL,
Limelighted_By varchar(6) REFERENCES Games);

CREATE TABLE Genres(
Genre_ID varchar(6) PRIMARY KEY,
Genre_Name varchar(50) NOT NULL,
About varchar(500) NOT NULL);

CREATE TABLE Audio(
Audio_ID varchar(6) PRIMARY KEY,
Audio_Name varchar(50) NOT NULL,
Artist_ID varchar(6),
Audio_Genre_ID varchar(6) NOT NULL,
Year_Of_Release numeric (4,0) NOT NULL,
Stock numeric (3,0) NOT NULL,
Demand numeric (3,0) NOT NULL DEFAULT 0,
Price_$ numeric (4,2) NOT NULL CHECK (Price_$ > 0),
Copies_Sold numeric (4,0) NOT NULL DEFAULT 0);

CREATE TABLE Artists(
Artist_ID varchar(6) PRIMARY KEY,
Artist_Name varchar(50) NOT NULL,
Country varchar(50) NOT NULL,
Limelighted_By varchar(6) REFERENCES Audio,
About varchar(500));

CREATE TABLE Customers(
Customer_ID varchar(6) PRIMARY KEY,
Customer_Name varchar(50) NOT NULL,
Address varchar(150) NOT NULL,
Contact numeric (14,0) NOT NULL,
Total_Amount_$ numeric (6,0) NOT NULL DEFAULT 0,
Total_Entity numeric (5,0) NOT NULL DEFAULT 0,
Discount_Entitled numeric (4,2) NOT NULL DEFAULT 0,
Credit varchar(1) DEFAULT 'N',
Credit_Limit numeric (6,0) DEFAULT 0);

CREATE TABLE Items(
Item_ID varchar(6) PRIMARY KEY);

CREATE SEQUENCE Bill_Number_Seq;

CREATE TABLE Purchases(
Customer_ID varchar(6) REFERENCES Customers,
Item_ID varchar(6) REFERENCES Items,
Bill_Number numeric (6,0) DEFAULT nextval('Bill_Number_Seq'),
Quantity numeric (4,0) NOT NULL,
Unit_Price_$ numeric (4,2),
Total_Price_$ numeric (6,2),
Discount numeric(4,2),
Discounted_Price numeric (6,2) CHECK (Discounted_Price > 0),
PRIMARY KEY (Customer_ID, Item_ID, Bill_Number));

CREATE TABLE Suppliers(
Supplier_ID varchar(6) PRIMARY KEY,
Supplier_Name varchar(30) NOT NULL,
Address varchar(100) NOT NULL,
Contact numeric (14,0) NOT NULL,
Supplier_Type varchar(6) NOT NULL);

CREATE TABLE Orders(
Item_ID varchar(6) REFERENCES Items,
Quantity numeric (3,0) NOT NULL,
Supplier_ID varchar(6) REFERENCES Suppliers,
PRIMARY KEY (Item_ID, Supplier_ID));


ALTER TABLE Movies ADD FOREIGN KEY (Director_ID) REFERENCES Directors;
ALTER TABLE Movies ADD FOREIGN KEY (Prod_House_ID) REFERENCES Production_House;

ALTER TABLE Games ADD FOREIGN KEY (Developer_ID) REFERENCES Developers;
ALTER TABLE Games ADD FOREIGN KEY (Game_Genre_ID) REFERENCES Genres;

ALTER TABLE Audio ADD FOREIGN KEY (Artist_ID) REFERENCES Artists;
ALTER TABLE Audio ADD FOREIGN KEY (Audio_Genre_ID) REFERENCES Genres;