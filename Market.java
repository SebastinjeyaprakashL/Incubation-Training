package myPackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Market {
	public static Scanner sc = new Scanner (System.in);
	public static final String employeeDirectoryFilePath = "EMPLOYEEDIRECTORY.txt";
	public static int getIntUserInput () {
		try {
			int input = sc.nextInt();
			sc.nextLine();
			return input;
		}
		catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		
	}
	public static float getFLoatUserInput () {
		try {
			float input = sc.nextFloat();
			sc.nextLine();
			return input;
		}
		catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		
	}
	public static String getStringUserInput ()
	{
		try {
			String input = sc.nextLine().toUpperCase();
			return input;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	public static long getLongUserInput ()
	{
		try {
			long input = sc.nextLong();
			sc.nextLine();
			return input;
		}
		catch (Exception e) {
			e.printStackTrace();
			return 0;
		}	
	}
	
	public ArrayList getEmployeeDetils () {
		try {
			String employeeName, employeeEmail ;
			long employeeMobileNumber;
			ArrayList  employeeDetails = new ArrayList ();
			System.out.println("Enter the Employee details : \nEMPLOYEE NAME :");
			employeeName = getStringUserInput ();
			System.out.println("MOBILE :");
			employeeMobileNumber = getLongUserInput ();
			System.out.println ("EMAIL :");
			employeeEmail = getStringUserInput ();
			employeeDetails.add(employeeName);
			employeeDetails.add(employeeMobileNumber);
			employeeDetails.add(employeeEmail);
			return employeeDetails;
			
			
		}
		catch (Exception e) {
			System.out.println("Invalid Input" + e);
			return null;
		}
		
		
	}
	
	public void addEmployee () {
		try {
			ArrayList  employeeDetails = new ArrayList (getEmployeeDetils ());
			HashMap <Integer,ArrayList> employeeDetailsWithId = new HashMap <Integer,ArrayList>();
			 
			File file = new File (employeeDirectoryFilePath);
			if(!file.exists()) {
	            file.createNewFile();
	         }
			BufferedReader readerForEmployeeDirectory = new BufferedReader(new FileReader(file));
			String lastEntry = "", fileReaderByLine;
			int lastId = 1;
			while ((fileReaderByLine = readerForEmployeeDirectory.readLine()) != null) { 
			lastEntry = fileReaderByLine;
			}
			System.out.println(lastEntry);
			if (lastEntry  != "") {
				String lastLineArray [] = lastEntry.split(":");
				lastId = Integer.parseInt(lastLineArray[0]);
				lastId ++ ;
			}
			employeeDetailsWithId.put (lastId,employeeDetails);
			BufferedWriter writerForEmployeeDeirectory = new BufferedWriter( new FileWriter(file, true) );
			for (Map.Entry<Integer, ArrayList> entry : employeeDetailsWithId.entrySet()){
				writerForEmployeeDeirectory.write(entry.getKey() + ":" + entry.getValue());
				writerForEmployeeDeirectory.newLine();
			}
			writerForEmployeeDeirectory.flush(); 
		}
		catch (Exception e) {
			System.out.println("OOPS something went wrong : " + e);
		}
		
		
	}
	
	public static void main (String args []) {
		
		boolean staySignedInFlag  ;
		Market market = new Market ();
		System.out.println("Please enter any one of the option \n1 - Admin Login \n2.Employee Login \n3.Exit");
		int loginAs = getIntUserInput();
		if (loginAs == 1) {
			staySignedInFlag = true;
			while (staySignedInFlag ) {
				System.out.println("Please enter the operation to perform \n1 - ADD EMPLOYEE \n2 - ADD NEW PRODUCT TO INVENTORY \n3- UPDATE EXISTING PRODUCT UNIT PRICE \n4 - REFILL INVENTORY PRODUCTS \n5 - MAKE A SALE");
				int adminAction = getIntUserInput ();
				switch (adminAction) {
				case 1: market.addEmployee ();
						break;
				default : staySignedInFlag = false;
				break;
				}
			}
		}
		else if(loginAs == 2) {
			staySignedInFlag = true;
			while (staySignedInFlag) {
				
			}
		}
		else {
			System.out.println("End");
		}
	}
}
