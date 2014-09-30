// IT 214 - Database Management Systems
// DBMS Project - Store Management, Client Side Code
// Group B207
// Arnav Goyal - 200901063
// Kumar Sumeet - 200901069
// Abhishek Anand - 200901115
// Angi Adani - 200901080
// Prakhar Sharma - 200901083

import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.math.BigInteger;

public class DBMSProject{
	
	public static boolean mainVar = true;
	public static String db_url;
	public static String db_user;
	public static String db_pwd;
	public static Connection myConnection;
	public static Statement myStatement;
	public static ArrayList myCart;
	public static ArrayList qCart;
	protected static String myHash = "8bc7ddb977b7c0c09687da8a8b8124f7";
	
	public static void main(String args[]){
		
		try{		
			Scanner Input = new Scanner(System.in);	
			String myPass;
			String myPassHash;
			System.out.println("\n####################\n\nWelcome To Our Store\n\n####################\n\n");
			
			myCart = new ArrayList();
			qCart = new ArrayList();
			Class.forName("org.postgresql.Driver");
			db_url = "jdbc:postgresql://10.100.71.21:5432/200901063";
			db_user = "200901063";
			db_pwd = "200901063";
			myConnection =  DriverManager.getConnection(db_url, db_user, db_pwd);
			myStatement = myConnection.createStatement();
			boolean mySchema = myStatement.execute("SET SEARCH_PATH TO Group_B207");
								
			while(mainVar){
				System.out.println("\n1.  Enter Shop");
				System.out.println("2.  Get Customer Details");
				System.out.println("\n9.  Leave");
				System.out.println("\n0.  Advanced Users");
				
				System.out.print("\nPlease Enter Your Choice: ");
				int myAns = Input.nextInt();
				System.out.println("\n");
				
				switch (myAns){
					
					case 1: EnterShop();
							break;
					
					case 2: getDetails();
							break;
					
					case 9: Leave();
							break;
					
					case 0: System.out.print("Enter Administrator Password: ");
							myPass = Input.next();
							myPassHash = hashPassword(myPass);
							if(myPassHash.equals(myHash)){
								dbAdmin();
								continue;
							}else{
								System.out.println("\n#####\nERROR\n#####");
								System.out.println("\nIncorrect Keyphrase. Access Denied. Contact Group_B207\n\n");
								continue;
							}
					
					default: System.out.println("Wrong Input Entered, Try Again");
							 continue;
				} // End Switch
			} // End While	
		}
		catch(Exception e){
			System.out.println("An Error Occured");
			System.out.println("Reason : " + e.getMessage());
		}			
	} // End Main

	public static void EnterShop(){
		// Gives the List of Options when you Enter The Store
		
		boolean varLoop = true;
		Scanner Input = new Scanner(System.in);
		
		while(varLoop){
			
			System.out.println("\nWhat Do You Want To Purchase?\n");
			
			System.out.println("1.  Movies");
			System.out.println("2.  Games");
			System.out.println("3.  Audio");			
			System.out.println("\n8.  Go Back");
			System.out.println("9.  Leave");
			
			System.out.print("\nPlease Enter Your Choice: ");
			int myAns = Input.nextInt();
			System.out.println("\n");
			
			switch (myAns){
				
				case 1: doMovies();
						break;
				
				case 2: doGames();
						break;
				
				case 3: doAudio();
						break;
						
				case 8: return;
				
				case 9: Leave();
						break;
				
				default: System.out.println("Wrong Input Entered, Try Again");
						 continue;
			} // End Switch
		} // End While
	} // End EnterShop
	
	public static void getDetails(){
		// Asks for the Customer ID to retrieve details
		
		boolean varLoop = true;
		Scanner Input = new Scanner(System.in);
		Scanner iD = new Scanner(System.in);
		
		while(varLoop){
		
			System.out.println("\n1.  Enter Your Customer ID");
			System.out.println("\n8.  Go Back");
			System.out.println("9.  Leave");
			
			System.out.print("\nEnter Your Choice : ");
			int myAns = Input.nextInt();
			
			switch (myAns){
				
				case 1: System.out.print("\nPlease Enter Customer ID : ");
						String myID = iD.next();
						getCustomerDetails(myID);
						break;
				
				case 8: return;
				
				case 9: Leave();
						break;
				
				default: System.out.println("Wrong Input Entered, Try Again");
						 continue;
			} // End Switch
		} // End While
	} // End getDetails
	
	public static void getCustomerDetails(String myID){
		// Retrieves the Customer Details for a Customer
		
		try{
			Boolean myA = false;
			Scanner Input = new Scanner(System.in);
			ResultSet mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Customers WHERE Customer_ID = '" + myID + "'");
			
			myA = mySet.next();
			if(!myA){
				System.out.println("\n#####\nERROR\n#####\n");
				System.out.println("The Customer With Customer ID '" + myID + "' Doesn't Exist.\n\n");
				return;
			}else{
				String Customer_ID = mySet.getString("Customer_ID");
				String Customer_Name = mySet.getString("Customer_Name");
				String Address = mySet.getString("Address");
				long Contact = mySet.getLong("Contact");
				double Total_Amount = mySet.getDouble("Total_Amount_$");
				double Total_Entity = mySet.getDouble("Total_Entity");
				double Discount_Entitled = mySet.getDouble("Discount_Entitled");
				String Credit = mySet.getString("Credit");
				double Credit_Limit = mySet.getDouble("Credit_Limit");
				
				System.out.println("\nCustomer ID : " + Customer_ID);
				System.out.println("Customer Name : " + Customer_Name);
				System.out.println("Address : " + Address);
				System.out.println("Contact Number : " + Contact);
				System.out.println("Total Amount Purchased : " + Total_Amount);
				System.out.println("Total Entity Purchased : " + Total_Entity);
				System.out.println("Discount Entitled : " + Discount_Entitled);
				System.out.println("Credit (Y or N) : " + Credit);
				System.out.println("Credit Limit : " + Credit_Limit);
				System.out.println("\n");
			}
			
			System.out.print("Happy With The Data? 1. Yes, 2. No, I Want To Change Them : ");
			int myAns = Input.nextInt();
			if(myAns == 1){
				return;
			}else if(myAns == 2){
				updateCust(myID);
			}else{
				System.out.println("Wrong Input Entered, Try Again");
			}
		}
		catch(Exception e){
			System.out.println("An Error Occured");
			System.out.println("Reason : " + e.getMessage());
		}
	} // End getCustomerDetails
	
	public static void updateCust(String myID){
		// Updates Name, Address Or Contact of a Customer
		
		try{
			Scanner Input = new Scanner(System.in);
			Scanner Input1 = new Scanner(System.in);
			String newName;
			String newAdd;
			long newCN;
			String updateSQL;
			PreparedStatement myUpdate;
			int myA;
			System.out.println("\nWhat Do You Want To Edit?");
			System.out.println("\n1.  Name");
			System.out.println("2.  Address");
			System.out.println("3.  Contact");
			
			System.out.print("\nEnter Your Choice : ");
			int myAns = Input.nextInt();
			
			switch(myAns){
				
				case 1: System.out.print("\nUpdate Name To : ");
						newName = Input1.next();
						
						updateSQL = "UPDATE Group_B207.Customers SET Customer_Name = ? WHERE Customer_ID = ?";
						myUpdate = myConnection.prepareStatement(updateSQL);
						
						myUpdate.setString(1, newName);
						myUpdate.setString(2, myID);
						
						myA = myUpdate.executeUpdate();
						System.out.println("\nName Successfully Changed To : " + newName);
						break;
						
				case 2: System.out.print("\nUpdate Address To : ");
						newAdd = Input1.next();
						
						updateSQL = "UPDATE Group_B207.Customers SET Address = ? WHERE Customer_ID = ?";
						myUpdate = myConnection.prepareStatement(updateSQL);
						
						myUpdate.setString(1, newAdd);
						myUpdate.setString(2, myID);
						
						myA = myUpdate.executeUpdate();
						System.out.println("\nAddress Successfully Changed To : " + newAdd);
						break;
						
				case 3: System.out.print("\nUpdate Contact Number To : ");
						newCN = Input1.nextLong();
						
						updateSQL = "UPDATE Group_B207.Customers SET Contact = ? WHERE Customer_ID = ?";
						myUpdate = myConnection.prepareStatement(updateSQL);
						
						myUpdate.setLong(1, newCN);
						myUpdate.setString(2, myID);
						
						myA = myUpdate.executeUpdate();
						System.out.println("\nContact Number Successfully Changed To : " + newCN);
						break;
				
				default: System.out.println("Wrong Input Entered, Try Again");
						 break;
			} // End Switch
			
			
		}
		catch(Exception e){
			System.out.println("An Error Occured");
			System.out.println("Reason : " + e.getMessage());
		}		
	} // End updateCust
	
	public static void doMovies(){
		// Choices to Buy Movies
		
		boolean varLoop = true;
		Scanner Input = new Scanner(System.in);
		Scanner iD = new Scanner(System.in);
		
		while(varLoop){
			
			System.out.println("\n1.  Browse Movies");
			System.out.println("2.  Buy A Movie");
			System.out.println("\n8.  Go Back.");
			System.out.println("9.  Leave.");
			
			System.out.print("\nEnter Your Choice : ");
			int myAns = Input.nextInt();
			
			switch (myAns){
				
				case 1: browseMovies();
						break;
				
				case 2: buyMovie();
						break;
						
				case 8: return;
				
				case 9: Leave();
						break;
				
				default: System.out.println("Wrong Input Entered, Try Again");
						 continue;
			} // End Switch
		} // End While
	} // End doMovies
	
	public static void browseMovies(){
		// Lists all the Movies and Filter Options.
		
		boolean varLoop = true;
		Scanner Input = new Scanner(System.in);
		
		System.out.println("\n");
		
		while(varLoop){
			
			System.out.println("\n1.  View All Available Movies.");
			System.out.println("2.  Filter Movies.");
			System.out.println("\n8.  Go Back.");
			System.out.println("9.  Leave.");
			
			System.out.print("\nEnter Your Choice : ");
			int myAns = Input.nextInt();
			
			switch (myAns){
				
				case 1: viewMovies();
						break;
				
				case 2: filterMovies();
						break;
						
				case 8: return;
				
				case 9: Leave();
						break;
						
				default: System.out.println("Wrong Input Entered, Try Again");
						 continue;
			} // End Switch
		} // End While
	} // End browseMovies
	
	public static void buyMovie(){
		// Adds a Movie to your Cart
		
		try{
			boolean varLoop = true;
						
			while(varLoop){
				
				boolean myA = false;
				Scanner Input = new Scanner(System.in);
				Scanner myAns = new Scanner(System.in);
								
				System.out.print("\nEnter The ID Of The Movie You Wish To Purchase : ");
				String myID = Input.next();
				String qID;
				
				ResultSet mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Movies WHERE Movie_ID = '" + myID + "'");
				
				myA = mySet.next();
				if(!myA){
					System.out.println("\n#####\nERROR\n#####\n");
					System.out.println("The Movie With Movie ID '" + myID + "' Doesn't Exist.\n");
					System.out.print("\nContinue Shopping? 1. Yes, 2. No  :  ");
					int contShop = myAns.nextInt();
					
					if(contShop == 1){
						mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Movies JOIN Group_B207.Directors ON Group_B207.Movies.Director_ID = Group_B207.Directors.Director_ID");
						System.out.println("\nMovie ID \t\t\t Movie Name \t\t\t Director \t\t\t Rating \t\t\t Price");
									
						System.out.println("\n");
						while(mySet.next()){
						
							String Movie_ID = mySet.getString("Movie_ID");
							String Movie_Name = mySet.getString("Movie_Name");
							String Director_Name = mySet.getString("Director_Name");
							double Rating = mySet.getDouble("Rating");
							double Price = mySet.getDouble("Price_$");
							
							System.out.println(Movie_ID + "\t\t\t" + Movie_Name + "\t\t\t" + Director_Name + "\t\t\t" + Rating + "\t\t\t" + Price);
						} // End While
						continue;
					}else{
						System.out.println("\n");
						break;
					}
				}else{
					String Movie_Name = mySet.getString("Movie_Name");
										
					if(!myCart.contains(myID)){
						System.out.print("\nEnter Quantity : ");
						int myQ = Input.nextInt();
						qID = myID + "#" + myQ;
						myCart.add(myID);
						qCart.add(qID);
						System.out.println("\n" + Movie_Name + " succesfully added to your Cart\n");
					}else{
						System.out.println("\nItem Already Exists In Your Cart");
					}
					System.out.print("\nContinue Shopping? 1. Yes, 2. No  :  ");
					int contShop = myAns.nextInt();
					
					if(contShop == 1){
						mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Movies JOIN Group_B207.Directors ON Group_B207.Movies.Director_ID = Group_B207.Directors.Director_ID");
						System.out.println("\nMovie ID \t\t\t Movie Name \t\t\t Director \t\t\t Rating \t\t\t Price");
									
						System.out.println("\n");
						while(mySet.next()){
						
							String Movie_ID = mySet.getString("Movie_ID");
							Movie_Name = mySet.getString("Movie_Name");
							String Director_Name = mySet.getString("Director_Name");
							double Rating = mySet.getDouble("Rating");
							double Price = mySet.getDouble("Price_$");
							
							System.out.println(Movie_ID + "\t\t\t" + Movie_Name + "\t\t\t" + Director_Name + "\t\t\t" + Rating + "\t\t\t" + Price);
						} // End While
						continue;
					}else{
						break;
					}
				}	
			} // End While
		}
		catch(Exception e){
			System.out.println("An Error Occured");
			System.out.println("Reason : " + e.getMessage());
		}	
	} // End buyMovie
	
	public static void viewMovies(){
		// List all the Movies
		
		try{
			ResultSet mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Movies JOIN Group_B207.Directors ON Group_B207.Movies.Director_ID = Group_B207.Directors.Director_ID");
			
			System.out.println("\nMovie ID \t\t\t Movie Name \t\t\t Director \t\t\t Rating \t\t\t Price");
						
			System.out.println("\n");
			while(mySet.next()){
				
				String Movie_ID = mySet.getString("Movie_ID");
				String Movie_Name = mySet.getString("Movie_Name");
				String Director_Name = mySet.getString("Director_Name");
				double Rating = mySet.getDouble("Rating");
				double Price = mySet.getDouble("Price_$");
				
				System.out.println(Movie_ID + "\t\t\t" + Movie_Name + "\t\t\t" + Director_Name + "\t\t\t" + Rating + "\t\t\t" + Price);
			} // End While
		}
		catch(Exception e){
			System.out.println("An Error Occured");
			System.out.println("Reason : " + e.getMessage());
		}
	} // End viewMovies
	
	public static void filterMovies(){
		// Gives Filtering Options.
		
		boolean varLoop = true;
		Scanner Input = new Scanner(System.in);
		while(varLoop){
			
			System.out.println("\n1.  Search By Actor");
			System.out.println("2.  Search By Director");
			System.out.println("\n8.  Go Back");
			System.out.println("9.  Leave");
			
			System.out.print("\nPlease Enter Your Choice: ");
			int myAns = Input.nextInt();
			System.out.println("\n");
			
			switch (myAns){
				
				case 1: searchActor();
						break;
				
				case 2: searchDir();
						break;
				
				case 8: return;
				
				case 9: Leave();
						break;
				
				default: System.out.println("Wrong Input Entered, Try Again");
						 continue;
			} // End Switch
		} // End While
	} // End filterMovies
	
	public static void searchActor(){
		// Search By a Specific Actor
		try{
			boolean varLoop = true;
			
			ResultSet mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Actors");
			System.out.println("\nActor ID \t\t Actor Name\n");
				
			while(mySet.next()){
					
				String Actor_ID = mySet.getString("Actor_ID");
				String Actor_Name = mySet.getString("Actor_Name");
				
				System.out.println(Actor_ID + "\t\t" + Actor_Name);		
			} // End While
						
			while(varLoop){
				
				boolean myA = false;
				Scanner Input = new Scanner(System.in);
				Scanner myAns = new Scanner(System.in);
							
				System.out.print("\nEnter The ID Of The Actor For Filtering : ");
				String myID = Input.next();
				
				mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Movies WHERE Movie_ID IN (SELECT Movie_ID FROM Group_B207.Acted_In WHERE Actor_ID = '" + myID + "')");
				
				myA = mySet.next();
				if(!myA){
					System.out.println("\n#####\nERROR\n#####\n");
					System.out.println("The Actor With Actor ID '" + myID + "' Doesn't Exist.\n");
					System.out.print("\nView The List Again? 1. Yes, 2. No  :  ");
					int vAgain = myAns.nextInt();
					
					if(vAgain == 1){
						mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Actors");
						System.out.println("\nActor ID \t\t Actor Name\n");
				
						while(mySet.next()){					
							String Actor_ID = mySet.getString("Actor_ID");
							String Actor_Name = mySet.getString("Actor_Name");				
							System.out.println(Actor_ID + "\t\t" + Actor_Name);		
						} // End While						
						continue;
					}else{
						System.out.println("\n");
						break;
					}
				}else{
					String Movie_ID = mySet.getString("Movie_ID");
					String Movie_Name = mySet.getString("Movie_Name");
					double Rating = mySet.getDouble("Rating");
					double Price = mySet.getDouble("Price_$");
				
					System.out.println("\n" + Movie_ID + "\t\t\t" + Movie_Name + "\t\t\t" + Rating + "\t\t\t" + Price);
				}
				
				while(mySet.next()){
					
					String Movie_ID = mySet.getString("Movie_ID");
					String Movie_Name = mySet.getString("Movie_Name");
					double Rating = mySet.getDouble("Rating");
					double Price = mySet.getDouble("Price_$");
				
					System.out.println(Movie_ID + "\t\t\t" + Movie_Name + "\t\t\t" + Rating + "\t\t\t" + Price);
				} // End While
				
				System.out.print("\nContinue Filtring? 1. Yes, 2. No  :  ");
				int vFilt = myAns.nextInt();
				
				if(vFilt != 1){
					break;
				}
				
				mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Actors");
				System.out.println("\nActor ID \t\t Actor Name\n");
				
				while(mySet.next()){					
					String Actor_ID = mySet.getString("Actor_ID");
					String Actor_Name = mySet.getString("Actor_Name");				
					System.out.println(Actor_ID + "\t\t" + Actor_Name);		
				} // End While	
			} // End While
		}
		catch(Exception e){
			System.out.println("An Error Occured");
			System.out.println("Reason : " + e.getMessage());
		}
	} // End searchActor
	
	public static void searchDir(){
		// Search by a Specific Director
		
		try{
			boolean varLoop = true;
			
			ResultSet mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Directors");
			System.out.println("\nDirector ID \t\t Director Name\n");
				
			while(mySet.next()){
					
				String Director_ID = mySet.getString("Director_ID");
				String Director_Name = mySet.getString("Director_Name");
				
				System.out.println(Director_ID + "\t\t\t\t\t" + Director_Name);		
			} // End While
						
			while(varLoop){
				
				boolean myA = false;
				Scanner Input = new Scanner(System.in);
				Scanner myAns = new Scanner(System.in);
							
				System.out.print("\nEnter The ID Of The Director For Filtering : ");
				String myID = Input.next();
				
				mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Movies WHERE Director_ID = '" + myID + "'");
				
				myA = mySet.next();
				if(!myA){
					System.out.println("\n#####\nERROR\n#####\n");
					System.out.println("The Director With Director ID '" + myID + "' Doesn't Exist.\n");
					System.out.print("\nView The List Again? 1. Yes, 2. No  :  ");
					int vAgain = myAns.nextInt();
					
					if(vAgain == 1){
						mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Directors");
						System.out.println("\nDirector ID \t\t Director Name\n");
				
						while(mySet.next()){					
							String Director_ID = mySet.getString("Director_ID");
							String Director_Name = mySet.getString("Director_Name");				
							System.out.println(Director_ID + "\t\t\t\t\t" + Director_Name);		
						} // End While				
						continue;
					}else{
						System.out.println("\n");
						break;
					}
				}else{
					String Movie_ID = mySet.getString("Movie_ID");
					String Movie_Name = mySet.getString("Movie_Name");
					double Rating = mySet.getDouble("Rating");
					double Price = mySet.getDouble("Price_$");
				
					System.out.println("\n" + Movie_ID + "\t\t\t" + Movie_Name + "\t\t\t" + Rating + "\t\t\t" + Price);
				}
				
				while(mySet.next()){
					
					String Movie_ID = mySet.getString("Movie_ID");
					String Movie_Name = mySet.getString("Movie_Name");
					double Rating = mySet.getDouble("Rating");
					double Price = mySet.getDouble("Price_$");
				
					System.out.println(Movie_ID + "\t\t\t" + Movie_Name + "\t\t\t" + Rating + "\t\t\t" + Price);
				} // End While
				
				System.out.print("\nContinue Filtring? 1. Yes, 2. No  :  ");
				int vFilt = myAns.nextInt();
				
				if(vFilt != 1){
					break;
				}
				
				mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Directors");
				System.out.println("\nDirector ID \t\t Director Name\n");
				
				while(mySet.next()){					
					String Director_ID = mySet.getString("Director_ID");
					String Director_Name = mySet.getString("Director_Name");				
					System.out.println(Director_ID + "\t\t\t\t\t" + Director_Name);		
				} // End While	
			} // End While
		}
		catch(Exception e){
			System.out.println("An Error Occured");
			System.out.println("Reason : " + e.getMessage());
		}
		
	} // End searchDir
	
	public static void doGames(){
		// Choices to Buy Games
		
		boolean varLoop = true;
		Scanner Input = new Scanner(System.in);
		Scanner iD = new Scanner(System.in);
		
		while(varLoop){
			
			System.out.println("\n1.  Browse Games");
			System.out.println("2.  Buy A Game");
			System.out.println("\n8.  Go Back.");
			System.out.println("9.  Leave.");
			
			System.out.print("\nEnter Your Choice : ");
			int myAns = Input.nextInt();
			
			switch (myAns){
				
				case 1: browseGames();
						break;
				
				case 2: buyGame();
						break;
						
				case 8: return;
				
				case 9: Leave();
						break;
				
				default: System.out.println("Wrong Input Entered, Try Again");
						 continue;
			} // End Switch
		} // End While
	} // End doGames
	
	public static void browseGames(){
		// Lists all the Games and Filter Options.
		
		boolean varLoop = true;
		Scanner Input = new Scanner(System.in);
		
		System.out.println("\n");
		
		while(varLoop){
			
			System.out.println("\n1.  View All Available Games.");
			System.out.println("2.  Filter Games.");
			System.out.println("\n8.  Go Back.");
			System.out.println("9.  Leave.");
			
			System.out.print("\nEnter Your Choice : ");
			int myAns = Input.nextInt();
			
			switch (myAns){
				
				case 1: viewGames();
						break;
				
				case 2: filterGames();
						break;
						
				case 8: return;
				
				case 9: Leave();
						break;
				
				default: System.out.println("Wrong Input Entered, Try Again");
						 continue;
			} // End Switch
		} // End While
	} // End browseGames
	
	public static void viewGames(){
		// List all the Games
		
		try{
			ResultSet mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Games");
			
			System.out.println("\nGame ID \t\t\t Game Name \t\t\t Platform \t\t\t Rating \t\t\t Price");
						
			System.out.println("\n");
			while(mySet.next()){
				
				String Game_ID = mySet.getString("Game_ID");
				String Game_Name = mySet.getString("Game_Name");
				String Platform = mySet.getString("Game_Platform");
				double Rating = mySet.getDouble("Rating");
				double Price = mySet.getDouble("Price_$");
				
				System.out.println(Game_ID + "\t\t\t" + Game_Name + "\t\t\t" + Platform + "\t\t\t" + Rating + "\t\t\t" + Price);
			} // End While
		}
		catch(Exception e){
			System.out.println("An Error Occured");
			System.out.println("Reason : " + e.getMessage());
		}
	} // End viewGames
	
	public static void filterGames(){
		// Gives Filtering Options.
		
		boolean varLoop = true;
		Scanner Input = new Scanner(System.in);
		while(varLoop){
			
			System.out.println("\n1.  Search By Developer");
			System.out.println("2.  Search By Genre");
			System.out.println("\n8.  Go Back");
			System.out.println("9.  Leave");
			
			System.out.print("\nPlease Enter Your Choice: ");
			int myAns = Input.nextInt();
			System.out.println("\n");
			
			switch (myAns){
				
				case 1: searchDeveloper();
						break;
				
				case 2: searchGenreG();
						break;
				
				case 8: return;
				
				case 9: Leave();
						break;
				
				default: System.out.println("Wrong Input Entered, Try Again");
						 continue;
			} // End Switch
		} // End While
	} // End filterGames
	
	public static void searchDeveloper(){
		// Filter by Developer
		
		try{
			boolean varLoop = true;
			
			ResultSet mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Developers");
			System.out.println("\nDeveloper ID \t\t Developer Name\n");
				
			while(mySet.next()){
					
				String Developer_ID = mySet.getString("Developer_ID");
				String Developer_Name = mySet.getString("Developer_Name");
				
				System.out.println(Developer_ID + "\t\t" + Developer_Name);		
			} // End While
						
			while(varLoop){
				
				boolean myA = false;
				Scanner Input = new Scanner(System.in);
				Scanner myAns = new Scanner(System.in);
							
				System.out.print("\nEnter The ID Of The Developer For Filtering : ");
				String myID = Input.next();
				
				mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Games WHERE Developer_ID = '" + myID + "'");
				
				myA = mySet.next();
				if(!myA){
					System.out.println("\n#####\nERROR\n#####\n");
					System.out.println("The Developer With Developer ID '" + myID + "' Doesn't Exist.\n");
					System.out.print("\nView The List Again? 1. Yes, 2. No  :  ");
					int vAgain = myAns.nextInt();
					
					if(vAgain == 1){
						mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Developers");
						System.out.println("\nDeveloper ID \t\t Developer Name\n");
				
						while(mySet.next()){					
							String Developer_ID = mySet.getString("Developer_ID");
							String Developer_Name = mySet.getString("Developer_Name");				
							System.out.println(Developer_ID + "\t\t" + Developer_Name);		
						} // End While						
						continue;
					}else{
						System.out.println("\n");
						break;
					}
				}else{
					String Game_ID = mySet.getString("Game_ID");
					String Game_Name = mySet.getString("Game_Name");
					String Platform = mySet.getString("Game_Platform");
					double Rating = mySet.getDouble("Rating");
					double Price = mySet.getDouble("Price_$");
				
					System.out.println(Game_ID + "\t\t\t" + Game_Name + "\t\t\t" + Platform + "\t\t\t" + Rating + "\t\t\t" + Price);
				}
				
				while(mySet.next()){
					
					String Game_ID = mySet.getString("Game_ID");
					String Game_Name = mySet.getString("Game_Name");
					String Platform = mySet.getString("Game_Platform");
					double Rating = mySet.getDouble("Rating");
					double Price = mySet.getDouble("Price_$");
				
					System.out.println(Game_ID + "\t\t\t" + Game_Name + "\t\t\t" + Platform + "\t\t\t" + Rating + "\t\t\t" + Price);
				} // End While
				
				System.out.print("\nContinue Filtring? 1. Yes, 2. No  :  ");
				int vFilt = myAns.nextInt();
				
				if(vFilt != 1){
					break;
				}
				
				mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Developers");
				System.out.println("\nDeveloper ID \t\t Developer Name\n");
				
				while(mySet.next()){					
					String Developer_ID = mySet.getString("Developer_ID");
					String Developer_Name = mySet.getString("Developer_Name");				
					System.out.println(Developer_ID + "\t\t" + Developer_Name);		
				} // End While	
			} // End While
		}
		catch(Exception e){
			System.out.println("An Error Occured");
			System.out.println("Reason : " + e.getMessage());
		}
	} // End searchDeveloper
	
	public static void searchGenreG(){
		// Filter By Genre
		
		try{
			boolean varLoop = true;
			
			ResultSet mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Genres");
			System.out.println("\nGenre ID \t\t Genre Name\n");
				
			while(mySet.next()){
					
				String Genre_ID = mySet.getString("Genre_ID");
				String Genre_Name = mySet.getString("Genre_Name");
				
				System.out.println(Genre_ID + "\t\t" + Genre_Name);		
			} // End While
						
			while(varLoop){
				
				boolean myA = false;
				Scanner Input = new Scanner(System.in);
				Scanner myAns = new Scanner(System.in);
							
				System.out.print("\nEnter The ID Of The Genre For Filtering : ");
				String myID = Input.next();
				
				mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Games WHERE Game_Genre_ID = '" + myID + "'");
				
				myA = mySet.next();
				if(!myA){
					System.out.println("\n#####\nERROR\n#####\n");
					System.out.println("The Game With Genre ID '" + myID + "' Doesn't Exist.\n");
					System.out.print("\nView The List Again? 1. Yes, 2. No  :  ");
					int vAgain = myAns.nextInt();
					
					if(vAgain == 1){
						mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Genres");
						System.out.println("\nGenre ID \t\t Genre Name\n");
				
						while(mySet.next()){					
							String Genre_ID = mySet.getString("Genre_ID");
							String Genre_Name = mySet.getString("Genre_Name");				
							System.out.println(Genre_ID + "\t\t" + Genre_Name);		
						} // End While						
						continue;
					}else{
						System.out.println("\n");
						break;
					}
				}else{
					String Game_ID = mySet.getString("Game_ID");
					String Game_Name = mySet.getString("Game_Name");
					String Platform = mySet.getString("Game_Platform");
					double Rating = mySet.getDouble("Rating");
					double Price = mySet.getDouble("Price_$");
				
					System.out.println(Game_ID + "\t\t\t" + Game_Name + "\t\t\t" + Platform + "\t\t\t" + Rating + "\t\t\t" + Price);
				}
				
				while(mySet.next()){
					
					String Game_ID = mySet.getString("Game_ID");
					String Game_Name = mySet.getString("Game_Name");
					String Platform = mySet.getString("Game_Platform");
					double Rating = mySet.getDouble("Rating");
					double Price = mySet.getDouble("Price_$");
				
					System.out.println(Game_ID + "\t\t\t" + Game_Name + "\t\t\t" + Platform + "\t\t\t" + Rating + "\t\t\t" + Price);
				} // End While
				
				System.out.print("\nContinue Filtring? 1. Yes, 2. No  :  ");
				int vFilt = myAns.nextInt();
				
				if(vFilt != 1){
					break;
				}
				
				mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Genres");
				System.out.println("\nGenre ID \t\t Genre Name\n");
				
				while(mySet.next()){					
					String Genre_ID = mySet.getString("Genre_ID");
					String Genre_Name = mySet.getString("Genre_Name");				
					System.out.println(Genre_ID + "\t\t" + Genre_Name);		
				} // End While	
			} // End While
		}
		catch(Exception e){
			System.out.println("An Error Occured");
			System.out.println("Reason : " + e.getMessage());
		}
	} // End searchGenreG
		
	public static void buyGame(){
		// Adds a Game to your Cart
		
		try{
			boolean varLoop = true;
						
			while(varLoop){
				
				boolean myA = false;
				Scanner Input = new Scanner(System.in);
				Scanner myAns = new Scanner(System.in);
								
				System.out.print("\nEnter The ID Of The Game You Wish To Purchase : ");
				String myID = Input.next();
				String qID;
				
				ResultSet mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Games WHERE Game_ID = '" + myID + "'");
				
				myA = mySet.next();
				if(!myA){
					System.out.println("\n#####\nERROR\n#####\n");
					System.out.println("The Game With Game ID '" + myID + "' Doesn't Exist.\n");
					System.out.print("\nContinue Shopping? 1. Yes, 2. No  :  ");
					int contShop = myAns.nextInt();
					
					if(contShop == 1){
						mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Games");
						System.out.println("\nGame ID \t\t\t Game Name \t\t\t Platform \t\t\t Rating \t\t\t Price");
									
						System.out.println("\n");
						while(mySet.next()){
						
							String Game_ID = mySet.getString("Game_ID");
							String Game_Name = mySet.getString("Game_Name");
							String Platform = mySet.getString("Game_Platform");
							double Rating = mySet.getDouble("Rating");
							double Price = mySet.getDouble("Price_$");
							
							System.out.println(Game_ID + "\t\t\t" + Game_Name + "\t\t\t" + Platform + "\t\t\t" + Rating + "\t\t\t" + Price);
						} // End While
						continue;
					}else{
						System.out.println("\n");
						break;
					}
				}else{
					String Game_Name = mySet.getString("Game_Name");
					
					
					if(!myCart.contains(myID)){
						System.out.print("\nEnter Quantity : ");
						int myQ = Input.nextInt();
						qID = myID + "#" + myQ;
						myCart.add(myID);
						qCart.add(qID);
						System.out.println("\n" + Game_Name + " succesfully added to your Cart\n");
					}else{
						System.out.println("\nItem Already Exists In Your Cart");
					}
					System.out.print("\nContinue Shopping? 1. Yes, 2. No  :  ");
					int contShop = myAns.nextInt();
					
					if(contShop == 1){
						mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Games");
						System.out.println("\nGame ID \t\t\t Game Name \t\t\t Platform \t\t\t Rating \t\t\t Price");
									
						System.out.println("\n");
						while(mySet.next()){
						
							String Game_ID = mySet.getString("Game_ID");
							Game_Name = mySet.getString("Game_Name");
							String Platform = mySet.getString("Game_Platform");
							double Rating = mySet.getDouble("Rating");
							double Price = mySet.getDouble("Price_$");
							
							System.out.println(Game_ID + "\t\t\t" + Game_Name + "\t\t\t" + Platform + "\t\t\t" + Rating + "\t\t\t" + Price);
						} // End While
						continue;
					}else{
						break;
					}
				}	
			} // End While
		}
		catch(Exception e){
			System.out.println("An Error Occured");
			System.out.println("Reason : " + e.getMessage());
		}		
	} // End buyGame
	
	public static void doAudio(){
		// Choices to Buy Audio
		
		boolean varLoop = true;
		Scanner Input = new Scanner(System.in);
		Scanner iD = new Scanner(System.in);
		
		while(varLoop){
			
			System.out.println("\n1.  Browse Albums");
			System.out.println("2.  Buy An Album");
			System.out.println("\n8.  Go Back.");
			System.out.println("9.  Leave.");
			
			System.out.print("\nEnter Your Choice : ");
			int myAns = Input.nextInt();
			
			switch (myAns){
				
				case 1: browseAudio();
						break;
				
				case 2: buyAudio();
						break;
						
				case 8: return;
				
				case 9: Leave();
						break;
				
				default: System.out.println("Wrong Input Entered, Try Again");
						 continue;
			} // End Switch
		} // End While
	} // End doAudio
	
	public static void browseAudio(){
		// Lists all the Games and Filter Options.
		
		boolean varLoop = true;
		Scanner Input = new Scanner(System.in);
		
		System.out.println("\n");
		
		while(varLoop){
			
			System.out.println("\n1.  View All Available Albums.");
			System.out.println("2.  Filter Albums.");
			System.out.println("\n8.  Go Back.");
			System.out.println("9.  Leave.");
			
			System.out.print("\nEnter Your Choice : ");
			int myAns = Input.nextInt();
			
			switch (myAns){
				
				case 1: viewAlbums();
						break;
				
				case 2: filterAlbums();
						break;
						
				case 8: return;
				
				case 9: Leave();
						break;
				
				default: System.out.println("Wrong Input Entered, Try Again");
						 continue;
			} // End Switch
		} // End While
	} // End browseAudio
	
	public static void viewAlbums(){
		// List all the Audio
		
		try{
			ResultSet mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Audio JOIN Group_B207.Artists ON Group_B207.Audio.Artist_ID = Group_B207.Artists.Artist_ID");
			
			System.out.println("\nAudio ID \t\t\t Audio Name \t\t\t Artist \t\t\t Price");
						
			System.out.println("\n");
			while(mySet.next()){
				
				String Audio_ID = mySet.getString("Audio_ID");
				String Audio_Name = mySet.getString("Audio_Name");
				String Artist_Name = mySet.getString("Artist_Name");
				double Price = mySet.getDouble("Price_$");
				
				System.out.println(Audio_ID + "\t\t\t" + Audio_Name + "\t\t\t" + Artist_Name + "\t\t\t" + Price);
			} // End While
		}
		catch(Exception e){
			System.out.println("An Error Occured");
			System.out.println("Reason : " + e.getMessage());
		}
	} // End viewAlbums
	
	public static void filterAlbums(){
		// Gives Filtering Options.
		
		boolean varLoop = true;
		Scanner Input = new Scanner(System.in);
		while(varLoop){
			
			System.out.println("\n1.  Search By Artist");
			System.out.println("2.  Search By Genre");
			System.out.println("\n8.  Go Back");
			System.out.println("9.  Leave");
			
			System.out.print("\nPlease Enter Your Choice: ");
			int myAns = Input.nextInt();
			System.out.println("\n");
			
			switch (myAns){
				
				case 1: searchArtist();
						break;
				
				case 2: searchGenreA();
						break;
				
				case 8: return;
				
				case 9: Leave();
						break;
				
				default: System.out.println("Wrong Input Entered, Try Again");
						 continue;
			} // End Switch
		} // End While
	} // End filterAlbums
	
	public static void searchArtist(){
		// Filter by Artist
		
		try{
			boolean varLoop = true;
			
			ResultSet mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Artists");
			System.out.println("\nArtist ID \t\t Artist Name\n");
				
			while(mySet.next()){
					
				String Artist_ID = mySet.getString("Artist_ID");
				String Artist_Name = mySet.getString("Artist_Name");
				
				System.out.println(Artist_ID + "\t\t" + Artist_Name);		
			} // End While
						
			while(varLoop){
				
				boolean myA = false;
				Scanner Input = new Scanner(System.in);
				Scanner myAns = new Scanner(System.in);
							
				System.out.print("\nEnter The ID Of The Artist For Filtering : ");
				String myID = Input.next();
				
				mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Audio WHERE Artist_ID = '" + myID + "'");
				
				myA = mySet.next();
				if(!myA){
					System.out.println("\n#####\nERROR\n#####\n");
					System.out.println("The Artist With Artist ID '" + myID + "' Doesn't Exist.\n");
					System.out.print("\nView The List Again? 1. Yes, 2. No  :  ");
					int vAgain = myAns.nextInt();
					
					if(vAgain == 1){
						mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Artists");
						System.out.println("\nArtist ID \t\t Artist Name\n");
				
						while(mySet.next()){					
							String Artist_ID = mySet.getString("Artist_ID");
							String Artist_Name = mySet.getString("Artist_Name");				
							System.out.println(Artist_ID + "\t\t" + Artist_Name);		
						} // End While						
						continue;
					}else{
						System.out.println("\n");
						break;
					}
				}else{
					String Audio_ID = mySet.getString("Audio_ID");
					String Audio_Name = mySet.getString("Audio_Name");
					String Artist_Name = mySet.getString("Artist_Name");
					double Price = mySet.getDouble("Price_$");
				
					System.out.println(Audio_ID + "\t\t\t" + Audio_Name + "\t\t\t" + Artist_Name + "\t\t\t" + Price);
				}
				
				while(mySet.next()){
					
					String Audio_ID = mySet.getString("Audio_ID");
					String Audio_Name = mySet.getString("Audio_Name");
					String Artist_Name = mySet.getString("Artist_Name");
					double Price = mySet.getDouble("Price_$");
				
					System.out.println(Audio_ID + "\t\t\t" + Audio_Name + "\t\t\t" + Artist_Name + "\t\t\t" + Price);
				} // End While
				
				System.out.print("\nContinue Filtring? 1. Yes, 2. No  :  ");
				int vFilt = myAns.nextInt();
				
				if(vFilt != 1){
					break;
				}
				
				mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Artists");
				System.out.println("\nArtist ID \t\t Artist Name\n");
				
				while(mySet.next()){					
					String Artist_ID = mySet.getString("Artist_ID");
					String Artist_Name = mySet.getString("Artist_Name");				
					System.out.println(Artist_ID + "\t\t" + Artist_Name);		
				} // End While	
			} // End While
		}
		catch(Exception e){
			System.out.println("An Error Occured");
			System.out.println("Reason : " + e.getMessage());
		}	
	} // End searchArtist
	
	public static void searchGenreA(){
		// Filter by Genre
		
		try{
			boolean varLoop = true;
			
			ResultSet mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Genres");
			System.out.println("\nGenre ID \t\t Genre Name\n");
				
			while(mySet.next()){
					
				String Genre_ID = mySet.getString("Genre_ID");
				String Genre_Name = mySet.getString("Genre_Name");
				
				System.out.println(Genre_ID + "\t\t" + Genre_Name);		
			} // End While
						
			while(varLoop){
				
				boolean myA = false;
				Scanner Input = new Scanner(System.in);
				Scanner myAns = new Scanner(System.in);
							
				System.out.print("\nEnter The ID Of The Genre For Filtering : ");
				String myID = Input.next();
				
				mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Audio JOIN Group_B207.Artists ON Group_B207.Audio.Artist_ID = Group_B207.Artists.Artist_ID WHERE Audio_Genre_ID = '" + myID + "'");
				
				myA = mySet.next();
				if(!myA){
					System.out.println("\n#####\nERROR\n#####\n");
					System.out.println("The Album With Genre ID '" + myID + "' Doesn't Exist.\n");
					System.out.print("\nView The List Again? 1. Yes, 2. No  :  ");
					int vAgain = myAns.nextInt();
					
					if(vAgain == 1){
						mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Genres");
						System.out.println("\nGenre ID \t\t Genre Name\n");
				
						while(mySet.next()){					
							String Genre_ID = mySet.getString("Genre_ID");
							String Genre_Name = mySet.getString("Genre_Name");				
							System.out.println(Genre_ID + "\t\t" + Genre_Name);		
						} // End While						
						continue;
					}else{
						System.out.println("\n");
						break;
					}
				}else{
					String Audio_ID = mySet.getString("Audio_ID");
					String Audio_Name = mySet.getString("Audio_Name");
					String Artist_Name = mySet.getString("Artist_Name");
					double Price = mySet.getDouble("Price_$");
				
					System.out.println(Audio_ID + "\t\t\t" + Audio_Name + "\t\t\t" + Artist_Name + "\t\t\t" + Price);
				}
				
				while(mySet.next()){
					
					String Audio_ID = mySet.getString("Audio_ID");
					String Audio_Name = mySet.getString("Audio_Name");
					String Artist_Name = mySet.getString("Artist_Name");
					double Price = mySet.getDouble("Price_$");
				
					System.out.println(Audio_ID + "\t\t\t" + Audio_Name + "\t\t\t" + Artist_Name + "\t\t\t" + Price);
				} // End While
				
				System.out.print("\nContinue Filtring? 1. Yes, 2. No  :  ");
				int vFilt = myAns.nextInt();
				
				if(vFilt != 1){
					break;
				}
				
				mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Genres");
				System.out.println("\nGenre ID \t\t Genre Name\n");
				
				while(mySet.next()){					
					String Genre_ID = mySet.getString("Genre_ID");
					String Genre_Name = mySet.getString("Genre_Name");				
					System.out.println(Genre_ID + "\t\t" + Genre_Name);		
				} // End While	
			} // End While
		}
		catch(Exception e){
			System.out.println("An Error Occured");
			System.out.println("Reason : " + e.getMessage());
		}
	} // End searchGenreA
	
	public static void buyAudio(){
		//Adds A Audio to your Cart
		
		try{
			boolean varLoop = true;
						
			while(varLoop){
				
				boolean myA = false;
				Scanner Input = new Scanner(System.in);
				Scanner myAns = new Scanner(System.in);
								
				System.out.print("\nEnter The ID Of The Album You Wish To Purchase : ");
				String myID = Input.next();
				String qID;
				
				ResultSet mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Audio WHERE Audio_ID = '" + myID + "'");
				
				myA = mySet.next();
				if(!myA){
					System.out.println("\n#####\nERROR\n#####\n");
					System.out.println("The Album With Audio ID '" + myID + "' Doesn't Exist.\n");
					System.out.print("\nContinue Shopping? 1. Yes, 2. No  :  ");
					int contShop = myAns.nextInt();
					
					if(contShop == 1){
						mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Audio JOIN Group_B207.Artists ON Group_B207.Audio.Artist_ID = Group_B207.Artists.Artist_ID");
			
						System.out.println("\nAudio ID \t\t\t Audio Name \t\t\t Artist \t\t\t Price");
									
						System.out.println("\n");
						while(mySet.next()){
						
							String Audio_ID = mySet.getString("Audio_ID");
							String Audio_Name = mySet.getString("Audio_Name");
							String Artist_Name = mySet.getString("Artist_Name");
							double Price = mySet.getDouble("Price_$");
							
							System.out.println(Audio_ID + "\t\t\t" + Audio_Name + "\t\t\t" + Artist_Name + "\t\t\t" + Price);
						} // End While
						continue;
					}else{
						System.out.println("\n");
						break;
					}
				}else{
					String Audio_Name = mySet.getString("Audio_Name");			
					
					if(!myCart.contains(myID)){
						System.out.print("\nEnter Quantity : ");
						int myQ = Input.nextInt();
						qID = myID + "#" + myQ;
						myCart.add(myID);
						qCart.add(qID);
						System.out.println("\n" + Audio_Name + " succesfully added to your Cart\n");
					}else{
						System.out.println("\nItem Already Exists In Your Cart");
					}
					System.out.print("\nContinue Shopping? 1. Yes, 2. No  :  ");
					int contShop = myAns.nextInt();
					
					if(contShop == 1){
						mySet = myStatement.executeQuery("SELECT * FROM Group_B207.Audio JOIN Group_B207.Artists ON Group_B207.Audio.Artist_ID = Group_B207.Artists.Artist_ID");
			
						System.out.println("\nAudio ID \t\t\t Audio Name \t\t\t Artist \t\t\t Price");
									
						System.out.println("\n");
						while(mySet.next()){
						
							String Audio_ID = mySet.getString("Audio_ID");
							Audio_Name = mySet.getString("Audio_Name");
							String Artist_Name = mySet.getString("Artist_Name");
							double Price = mySet.getDouble("Price_$");
							
							System.out.println(Audio_ID + "\t\t\t" + Audio_Name + "\t\t\t" + Artist_Name + "\t\t\t" + Price);
						} // End While
						continue;
					}else{
						break;
					}
				}	
			} // End While
		}
		catch(Exception e){
			System.out.println("An Error Occured");
			System.out.println("Reason : " + e.getMessage());
		}		
	} // End buyAudio
	
	public static void Leave(){
		// Gives Leaving Options. Asks if you want to Buy the Selected Items.
		
		Scanner Input = new Scanner(System.in);
		int myAns;
		if(myCart.isEmpty()){
			System.out.println("\n\nThank You For Your Visit. Do Come Again.\n");
			System.exit(0);
		}else{
			System.out.println("\n\nYour Cart Contains " + myCart.size() + " Items");
			System.out.print("\nDo You Want To Buy Them?  1. Yes, 2. No : ");
			myAns = Input.nextInt();
			
			if(myAns == 2){
				System.out.println("\n\nThank You For Your Visit. Do Come Again.\n");
				System.exit(0);
			}else if(myAns == 1){
				custIdentify();
			}else{
				System.out.println("Bad Input");
				Leave();
			}
		}	
	} // End Leave
	
	public static void custIdentify(){
		// Customer Login Options. If you're not Registered, Redirects to Registration Portal
		
		Scanner Input = new Scanner(System.in);
		
		System.out.print("\nExisting Customer?\n\n1.  Enter Customer ID\n\n2.  Register And Continue\n\nEnter Your Choice :  ");
		int myAns = Input.nextInt();
		System.out.println();
		
		if(myAns == 1){
			doPurchase();
		}else if(myAns == 2){
			doRegister();
		}else{
			System.out.println("\nBad Input\n");
			custIdentify();
		}
	} // End custIdentify
	
	public static void doRegister(){
		// Registers a Customer
		
		try{
			Scanner Input = new Scanner(System.in);
			Scanner Input1 = new Scanner(System.in);
			Scanner Input2 = new Scanner(System.in);
			String newID;
			ResultSet mySet = myStatement.executeQuery("SELECT Max(Customer_ID) FROM Group_B207.Customers");
			mySet.next();
			String lastID = mySet.getString("Max");
			if(lastID != null){
				int lastDigit = Integer.parseInt(lastID.substring(2, lastID.length()));
				lastDigit++;
				newID = "CS" + lastDigit;
			}else{
				newID = "CS1";
			}
			
			System.out.print("\nEnter Your Name : ");
			String custName = Input.nextLine();
			System.out.println();
			
			System.out.print("Enter Address : ");
			String custAddress = Input2.nextLine();
			System.out.println();
					
			System.out.print("Enter Contact Number : ");
			long custNumber = Input.nextLong();
			System.out.println();
			
			String custSQL = "INSERT INTO Group_B207.Customers VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement inCust = myConnection.prepareStatement(custSQL);
			inCust.setString(1, newID);
			inCust.setString(2, custName);
			inCust.setString(3, custAddress);
			inCust.setLong(4, custNumber);
			inCust.setDouble(5, 0.00);
			inCust.setInt(6, 0);
			inCust.setDouble(7, 0.00);
			inCust.setString(8, "N");
			inCust.setDouble(9, 0.00);
			
			inCust.executeUpdate();
			System.out.println("Customer Detail Successfully Created. Your Customer ID is : " + newID + "\n");
			doPurchase();
		}
		catch(Exception e){
			System.out.println("An Error Occured");
			System.out.println("Reason : " + e.getMessage());
		}
	} // End doRegister
	
	public static void doPurchase(){
		// Adds Item to Purchases and Generates the Total Amount
		
		try{
			int nItems = myCart.size();
			Scanner Input = new Scanner(System.in);
			System.out.print("\nEnter Your Customer ID : ");
			String myID = Input.next();
			
			ResultSet mySet = myStatement.executeQuery("SELECT Customer_ID FROM Group_B207.Customers WHERE Customer_ID = '" + myID + "'");
			boolean myA = mySet.next();
			if(!myA){
				System.out.println("\nCustomer With Customer ID " + myID + " Doesn't Exist");
				doPurchase();
			}else{			
				for(int i = 0; i < nItems; i++){			
					String theItem = (String)qCart.get(i);
					int myS = theItem.indexOf("#");
					String myItem = theItem.substring(0, myS);
					int myQ = Integer.parseInt(theItem.substring(myS + 1, theItem.length()));
					if(i == 0){
						String insertSQL = "INSERT INTO Group_B207.Purchases (Customer_ID, Item_ID, Quantity) VALUES(?, ?, ?)";
			
						PreparedStatement inPur = myConnection.prepareStatement(insertSQL);
						inPur.setString(1, myID);
						inPur.setString(2, myItem);
						inPur.setInt(3, myQ);	
						int myVar = inPur.executeUpdate();
					}else{
						String insertSQL = "INSERT INTO Group_B207.Purchases (Bill_Number, Customer_ID, Item_ID, Quantity) VALUES(CURRVAL('Bill_Number_Seq'), ?, ?, ?)";
			
						PreparedStatement inPur = myConnection.prepareStatement(insertSQL);
						inPur.setString(1, myID);
						inPur.setString(2, myItem);
						inPur.setInt(3, myQ);	
						int myVar = inPur.executeUpdate();						
					}
				} // End For
				myCart.clear();
				qCart.clear();
					
				mySet = myStatement.executeQuery("SELECT Sum(Discounted_Price) From Group_B207.Purchases WHERE Customer_ID = '" + myID + "' AND Bill_Number = (Select Last_Value FROM Bill_Number_Seq)");
				myA = mySet.next();
					
				double TotalPrice = mySet.getDouble("Sum");
				System.out.println("\n\nTotal Amount Payable = $" + TotalPrice);
				System.exit(0);
			}
		}
		catch(Exception e){
			System.out.println("An Error Occured");
			System.out.println("Reason : " + e.getMessage());
		}		
	} // End doPurchase
	
	public static void dbAdmin(){
		// Gives Admin Access and Universal Query Executor.
		// You can Execute and SELECT Query by Entering this Function. Requires Passphrase
	
		try{
			Scanner Input = new Scanner(System.in);
			System.out.println("\n\t\tPlease Enter A Valid PostGreSQL Query\n");
			String myQuery = Input.nextLine();
				
			ResultSet mySet = myStatement.executeQuery(myQuery);
			ResultSetMetaData myData = mySet.getMetaData();
			int myColumns = myData.getColumnCount();
			int formatCount = 1;
			
			while(mySet.next()){
				if(formatCount == 1){
					System.out.println("\n");
				}
				for(int i = 1; i <= myColumns; i++){
					System.out.print(mySet.getObject(i) + "\t\t\t");
				} // End For
				formatCount++;
				System.out.println();
			} // End While
			
			System.out.print("\nContinue Admin Mode? 1. Yes, 2. No :  ");
			int myAns = Input.nextInt();
			
			if(myAns == 1){
				dbAdmin();
			}
		}
		catch(Exception e){
			System.out.println("An Error Occured");
			System.out.println("Reason : " + e.getMessage());
		}
	} // End dbAdmin
	
	public static String hashPassword(String password){
		// Generates a MD5 HashPassword.
	
		String hashword = null;
		try{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(password.getBytes());
			BigInteger hash = new BigInteger(1, md5.digest());
			hashword = hash.toString(16);
		}
		catch(Exception e){
			System.out.println(e);
		}	
		return hashword;
	} // End hashPassword
}