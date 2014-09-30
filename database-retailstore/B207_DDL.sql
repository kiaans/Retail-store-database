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
Date_Of_Birth DATE NOT NULL,
Country varchar(30) NOT NULL,
Limelighted_By varchar(6) REFERENCES Movies,
Number_Of_Movies numeric (3,0) NOT NULL,
About varchar(500));

CREATE TABLE Directors(
Director_ID varchar(6) PRIMARY KEY,
Director_Name varchar(50) NOT NULL,
Date_Of_Birth DATE NOT NULL,
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

CREATE TABLE Genres(
Genre_ID varchar(6) PRIMARY KEY,
Genre_Name varchar(50) NOT NULL,
About varchar(500) NOT NULL);

CREATE TABLE Games(
Game_ID varchar(6) PRIMARY KEY,
Game_Name varchar(50) NOT NULL,
Developer_ID varchar(6),
Game_Platform varchar(30) NOT NULL,
Game_Genre_ID varchar(6) REFERENCES Genres,
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

CREATE TABLE Audio(
Audio_ID varchar(6) PRIMARY KEY,
Audio_Name varchar(50) NOT NULL,
Artist_ID varchar(6),
Audio_Genre_ID varchar(6) REFERENCES Genres,
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
Total_Amount_$ numeric (8,2) NOT NULL DEFAULT 0,
Total_Entity numeric (5,0) NOT NULL DEFAULT 0,
Discount_Entitled numeric (4,2) NOT NULL DEFAULT 0,
Credit varchar(1) DEFAULT 'N',
Credit_Limit numeric (6,0) DEFAULT 0);

CREATE TABLE Items(
Item_ID varchar(6) PRIMARY KEY,
Item_Price numeric(6,2));

CREATE SEQUENCE Bill_Number_Seq;
CREATE SEQUENCE Order_Number;

CREATE TABLE Purchases(
Customer_ID varchar(6) REFERENCES Customers,
Item_ID varchar(6) REFERENCES Items,
Bill_Number numeric (6,0) DEFAULT nextval('Bill_Number_Seq'),
Date_Purchased DATE DEFAULT CURRENT_DATE NOT NULL,
Quantity numeric (4,0) NOT NULL,
Total_Price_$ numeric (6,2) DEFAULT 0,
Discounted_Price numeric (6,2) CHECK (Discounted_Price >= 0) DEFAULT 0,
PRIMARY KEY (Customer_ID, Item_ID, Bill_Number));

CREATE TABLE Suppliers(
Supplier_ID varchar(6) PRIMARY KEY,
Supplier_Name varchar(30) NOT NULL,
Address varchar(100) NOT NULL,
Contact numeric (14,0) NOT NULL,
Supplier_Type varchar(6) NOT NULL);

CREATE TABLE Orders(
Order_ID varchar(6) PRIMARY KEY DEFAULT nextval('Order_Number'),
Item_ID varchar(6) REFERENCES Items NOT NULL,
Quantity numeric (3,0) NOT NULL,
Supplier_ID varchar(6) REFERENCES Suppliers NOT NULL,
Date_Ordered DATE DEFAULT CURRENT_DATE NOT NULL);

CREATE TABLE Temp(
Customer_ID varchar(6),
Item_ID varchar(6),
Bill_Number numeric (6,0));


ALTER TABLE Movies ADD FOREIGN KEY (Director_ID) REFERENCES Directors;
ALTER TABLE Movies ADD FOREIGN KEY (Prod_House_ID) REFERENCES Production_House;

ALTER TABLE Games ADD FOREIGN KEY (Developer_ID) REFERENCES Developers;
ALTER TABLE Games ADD FOREIGN KEY (Game_Genre_ID) REFERENCES Genres;

ALTER TABLE Audio ADD FOREIGN KEY (Artist_ID) REFERENCES Artists;
ALTER TABLE Audio ADD FOREIGN KEY (Audio_Genre_ID) REFERENCES Genres;

CREATE OR REPLACE FUNCTION Insert_Items_Movies()
	RETURNS TRIGGER AS $$
	
	BEGIN
	
		IF(TG_OP = 'INSERT') THEN
			INSERT INTO Items VALUES(NEW.Movie_ID, NEW.Price_$);
		END IF;
		
		RETURN NULL;
	END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER Insert_Items_Movies
	AFTER INSERT ON Movies
	FOR EACH ROW
	EXECUTE PROCEDURE Insert_Items_Movies();
END;

CREATE OR REPLACE FUNCTION Insert_Items_Games()
	RETURNS TRIGGER AS $$
	
	BEGIN
	
		IF(TG_OP = 'INSERT') THEN
			INSERT INTO Items VALUES(NEW.Game_ID, NEW.Price_$);
		END IF;
		
		RETURN NULL;
	END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER Insert_Items_Games
	AFTER INSERT ON Games
	FOR EACH ROW
	EXECUTE PROCEDURE Insert_Items_Games();
END;

CREATE OR REPLACE FUNCTION Insert_Items_Audio()
	RETURNS TRIGGER AS $$
	
	BEGIN
	
		IF(TG_OP = 'INSERT') THEN
			INSERT INTO Items VALUES(NEW.Audio_ID, NEW.Price_$);
		END IF;
		
		RETURN NULL;
	END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER Insert_Items_Audio
	AFTER INSERT ON Audio
	FOR EACH ROW
	EXECUTE PROCEDURE Insert_Items_Audio();
END;


CREATE OR REPLACE FUNCTION Update_Stock()
	RETURNS TRIGGER AS $$

	BEGIN

		IF(TG_OP = 'INSERT') THEN
			UPDATE Movies
			SET Stock = Stock - NEW.Quantity, 
			Copies_Sold = Copies_Sold + NEW.Quantity 
			WHERE Movie_ID = NEW.Item_ID;
			
			UPDATE Games
			SET Stock = Stock - NEW.Quantity, 
			Copies_Sold = Copies_Sold + NEW.Quantity
			WHERE Game_ID = NEW.Item_ID;
			
			UPDATE Audio
			SET Stock = Stock - NEW.Quantity,
			Copies_Sold = Copies_Sold + NEW.Quantity
			WHERE Audio_ID = NEW.Item_ID;
		END IF;

		RETURN NULL; 
	END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER Update_Stock
	AFTER INSERT ON Purchases
	FOR EACH ROW
	EXECUTE PROCEDURE Update_Stock();
END;


CREATE OR REPLACE FUNCTION After_Purchase()
	RETURNS TRIGGER AS $$
	
	BEGIN
	
		IF(TG_OP = 'INSERT') THEN
			INSERT INTO Temp VALUES(NEW.Customer_ID, NEW.Item_ID, NEW.Bill_Number);
		END IF;
		
		RETURN NULL;
	END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER After_Purchase
	AFTER INSERT ON Purchases
	FOR EACH ROW	
	EXECUTE PROCEDURE After_Purchase();
END;


CREATE OR REPLACE FUNCTION Get_Price()
	RETURNS TRIGGER AS $$
	
	BEGIN
	
		IF(TG_OP = 'INSERT') THEN
		UPDATE Purchases
			SET Total_Price_$ = (Quantity * (SELECT Item_Price FROM Items WHERE Item_ID = NEW.Item_ID))
			WHERE Customer_ID = NEW.Customer_ID AND Item_ID = NEW.Item_ID AND Bill_Number = NEW.Bill_Number;
		END IF;
		
		UPDATE Purchases
			SET Discounted_Price = Total_Price_$ - ((SELECT Discount_Entitled FROM Customers WHERE Customer_ID = NEW.Customer_ID)*(Total_Price_$/100))
			WHERE Customer_ID = NEW.Customer_ID AND Item_ID = NEW.Item_ID AND Bill_Number = NEW.Bill_Number;
			
		UPDATE Customers
			SET Total_Entity = Total_Entity + (SELECT Quantity FROM Purchases WHERE Bill_Number = (SELECT Last_Value FROM Bill_Number_Seq) AND Item_ID = NEW.Item_ID),
			Total_Amount_$ = Total_Amount_$ + (SELECT Discounted_Price FROM Purchases WHERE Bill_Number = (SELECT Last_Value FROM Bill_Number_Seq) AND Item_ID = NEW.Item_ID)
			WHERE Customer_ID = NEW.Customer_ID;
		
		RETURN NULL;
	END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER Get_Price
	AFTER INSERT ON Temp
	FOR EACH ROW
	EXECUTE PROCEDURE Get_Price();
END;


CREATE OR REPLACE FUNCTION Order_Movies()
	RETURNS TRIGGER AS $$
	
	DECLARE
		New_Quantity INTEGER;
		
	BEGIN
		
		New_Quantity = ((10 - NEW.Stock)*10) + NEW.Demand;
		IF(TG_OP = 'UPDATE') THEN
			IF(NEW.Stock < 10) THEN
				INSERT INTO Orders(Item_ID, Quantity, Supplier_ID) VALUES(NEW.Movie_ID, New_Quantity, 'SP1');
			END IF;
		END IF;
		
		RETURN NULL;
	END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER Order_Movies
	AFTER UPDATE ON Movies
	FOR EACH ROW
	EXECUTE PROCEDURE Order_Movies();
END;


CREATE OR REPLACE FUNCTION Order_Games()
	RETURNS TRIGGER AS $$
	
	DECLARE
		New_Quantity INTEGER;
		
	BEGIN
		
		New_Quantity = ((10 - NEW.Stock)*10) + NEW.Demand;
		IF(TG_OP = 'UPDATE') THEN
			IF(NEW.Stock < 10) THEN
				INSERT INTO Orders(Item_ID, Quantity, Supplier_ID) VALUES(NEW.Game_ID, New_Quantity, 'SP2');
			END IF;
		END IF;
		
		RETURN NULL;
	END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER Order_Games
	AFTER UPDATE ON Games
	FOR EACH ROW
	EXECUTE PROCEDURE Order_Games();
END;


CREATE OR REPLACE FUNCTION Order_Audio()
	RETURNS TRIGGER AS $$
	
	DECLARE
		New_Quantity INTEGER;
		
	BEGIN
		
		New_Quantity = ((10 - NEW.Stock)*10) + NEW.Demand;
		IF(TG_OP = 'UPDATE') THEN
			IF(NEW.Stock < 10) THEN
				INSERT INTO Orders(Item_ID, Quantity, Supplier_ID) VALUES(NEW.Audio_ID, New_Quantity, 'SP3');
			END IF;
		END IF;
		
		RETURN NULL;
	END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER Order_Audio
	AFTER UPDATE ON Audio
	FOR EACH ROW
	EXECUTE PROCEDURE Order_Audio();
END;