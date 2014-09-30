import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.math.BigInteger;

public class Test{
	
		public static void main(String args[]){
			try{
				String theItem = "MO1#5";
				int myS = theItem.indexOf("#");
				String myItem = theItem.substring(0, myS);
				int myQ = Integer.parseInt(theItem.substring(myS + 1, theItem.length()));
				
				System.out.println(myItem);
				
				System.out.println(myQ);
			}
			catch(Exception e){
			}
		}		
}



/* Scanner Input1 = new Scanner(System.in);
			Scanner Input2 = new Scanner(System.in);
			String newName;
			String newAdd;
			long newCN;
			System.out.println(\n\n"NOTE: IF YOU DO NOT WISH TO CHANGE A CERTAIN DATA, ENTER 0\n\n");
			
			System.out.print("Update Name To : ");
			newName = Input.next();
			System.out.println();
			
			System.out.print("Update Address To : ");
			newAdd = Input1.next();
			System.out.println();
			
			System.out.print("Update Contact To : ");
			newCN = Input2.nextLong();
			
*/