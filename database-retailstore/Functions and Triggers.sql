SET SEARCH_PATH TO Group_B207;

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
			Discounted_Price = Total_Price_$ - ((SELECT Discount_Entitled FROM Customers WHERE Customer_ID = NEW.Customer_ID)*(Total_Price_$/100))
		
		RETURN NULL;
		
	END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER Get_Price
	AFTER INSERT ON Temp
	FOR EACH ROW
	EXECUTE PROCEDURE Get_Price();
END;