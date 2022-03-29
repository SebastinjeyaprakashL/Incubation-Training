package incubation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Inventory {
	public static Scanner sc = new Scanner (System.in);
	public static HashMap <Integer,String> inventoryList = new HashMap <Integer,String>();
	public static HashMap <Integer,Float> unitPrice = new HashMap <Integer,Float>();
	public static HashMap <Integer,Float> inventory = new HashMap <Integer, Float>();
	public static int newProductId = 1;
	public static final String 	productListFilePath = "PRODUCTLIST.txt", 
								productPriceListFilePath = "PRICELIST.txt",
								inventoryListDataFilePath = "INVENTORYDATA.txt",
								billFilePath = "BILL.txt";
	
	public Inventory () {
		updatedProductList ();
		updatedUnitPriceData ();
		updatedInventoryData();
	}
	
	public void updatedInventoryData () {
		try {
			File file = new File (inventoryListDataFilePath);
			if(!file.exists()) {
	            file.createNewFile();
	         }
			BufferedReader readerForInventoryData = new BufferedReader(new FileReader(file));
			String lastEntry = "", fileReaderByLine;
			int productId ;
			float productQuantity;
			while ((fileReaderByLine = readerForInventoryData.readLine()) != null) { 
			lastEntry = fileReaderByLine;
				if (lastEntry  != "") {
					String lastLineArray [] = lastEntry.split(":");
					productId = Integer.parseInt(lastLineArray[0]);
					productQuantity = Float.parseFloat(lastLineArray[1]);
					inventory.put (productId,productQuantity);
				}
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void updatedProductList () {
		try {
			File file = new File (productListFilePath);
			if(!file.exists()) {
	            file.createNewFile();
	         }
			BufferedReader readerForInventory = new BufferedReader(new FileReader(file));
			String lastEntry = "", fileReaderByLine;
			int lastId = 0;
			String productName = "";
			while ((fileReaderByLine = readerForInventory.readLine()) != null) { 
				//newProductId ++ ;
			lastEntry = fileReaderByLine;
				if (lastEntry  != "") {
					String lastLineArray [] = lastEntry.split(":");
					lastId = Integer.parseInt(lastLineArray[0]);
					productName = lastLineArray[1];
					inventoryList.put (lastId,productName);
				}	
			}
			newProductId = lastId + 1 ;
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public void updatedUnitPriceData () {
		try {
			File file = new File (productPriceListFilePath);
			if(!file.exists()) {
	            file.createNewFile();
	         }
			BufferedReader readerForUnitPrice = new BufferedReader(new FileReader(file));
			String lastEntry = "", fileReaderByLine;
			int productId;
			float productPrice;
			while ((fileReaderByLine = readerForUnitPrice.readLine()) != null) { 
			lastEntry = fileReaderByLine;
				if (lastEntry  != "") {
					String lastLineArray [] = lastEntry.split(":");
					productId = Integer.parseInt(lastLineArray[0]);
					productPrice = Float.parseFloat(lastLineArray[1]);
					unitPrice.put (productId,productPrice);
				}
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	protected void updateInventory () {
		try {
			File file = new File (productListFilePath);
			BufferedWriter writerForInventory = new BufferedWriter( new FileWriter(file) );
			for (Map.Entry<Integer, String> entry : inventoryList.entrySet()){
				writerForInventory.write(entry.getKey() + ":" + entry.getValue());
				writerForInventory.newLine();
			}
			writerForInventory.flush();
			writerForInventory.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	protected void updateInventoryData () {
		try {
			File file = new File (inventoryListDataFilePath);
			BufferedWriter writerForInventory = new BufferedWriter( new FileWriter(file) );
			for (Map.Entry<Integer, Float> entry : inventory.entrySet()){
				writerForInventory.write(entry.getKey() + ":" + entry.getValue());
				writerForInventory.newLine();
			}
			writerForInventory.flush();
			writerForInventory.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	protected void updateProductPriceData () {
		try {
			File file = new File (productPriceListFilePath);
			BufferedWriter writerForInventory = new BufferedWriter( new FileWriter(file) );
			for (Map.Entry<Integer, Float> entry : unitPrice.entrySet()){
				writerForInventory.write(entry.getKey() + ":" + entry.getValue());
				writerForInventory.newLine();
			}
			writerForInventory.flush();
			writerForInventory.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static int getIntUserInput () {
		try {
			int input = sc.nextInt();
			return input;
		}
		catch (Exception e) {
			System.out.println("Invalid input for the current action ");
			return 0;
		}
		finally {
			sc.nextLine();
		}
		
		
	}
	public static float getFloatUserInput () {
		try {
			float input = sc.nextFloat();
			return input;
		}
		catch (Exception e) {
			System.out.println("Invalid input for the current action ");
			return 0;
		}
		finally {
			sc.nextLine();
		}
		
		
	}
	public static String getStringUserInput ()
	{
		try {
			String input = sc.nextLine().toUpperCase();
			return input;
		}
		catch (Exception e) {
			System.out.println("Invalid input for the current action ");;
			return null;
		}
	}
	
	public static long getLongUserInput ()
	{
		try {
			long input = sc.nextLong();
			return input;
		}
		catch (Exception e) {
			System.out.println("Invalid input for the current action ");
			return 0;
		}	
		finally {
			sc.nextLine();
		}
	}
	
	public void addNewProduct () {
		try {
			System.out.println ("Enter the product name to add in the inventory list");
			String newProductName = getStringUserInput();
			if (inventoryList.containsValue(newProductName)) {
				System.out.println("Product already exists! Do you need to update price ? Please enter 1 - yes (or) 0 - Exit");
				int updateProductUnitPriceOption = getIntUserInput();
				if (updateProductUnitPriceOption == 1) {
					updateUnitPrice(0);
				}
			}
			else {
				inventoryList.put(newProductId,newProductName);
				System.out.println("Product added successfully");
				updateUnitPrice(newProductId);
				refillProducts(newProductId);
				newProductId ++ ;
				updateInventory();
				
				updatedInventoryData();
				
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public int getProductIdByProductName (String productName) {
		int productId = 0;
		try {
			for (Map.Entry<Integer, String> entry: inventoryList.entrySet()) {
	            if (productName.equals(entry.getValue())) {
	                productId = entry.getKey();
	            }
	        }		
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return productId;
	}
	
	public void viewProductList () {
		for(Integer id : inventoryList.keySet() ) {
			System.out.println ("Product Id :" +id + " => Product Name : " + inventoryList.get(id));
		}
	}
	
	public void updateUnitPrice (int productId) {
		if (productId == 0) {
			System.out.println("Enter the product name to update price : ");
			String productNameTOUpdatePrice = getStringUserInput();
			productId = getProductIdByProductName(productNameTOUpdatePrice);
		}
		if (productId != 0) {
			String productName = inventoryList.get(productId);
			if (unitPrice.containsKey(productId)) {
				float unitPriceOfEnteredProduct = unitPrice.get(productId) ;
				System.out.println("Old price of "+ productName + " : "+ unitPriceOfEnteredProduct); 
			}
			System.out.println("\nEnter the  unit price of "+ productName +" to update :");
			float newUnitPrice = getFloatUserInput();
			unitPrice.put(productId,newUnitPrice);
			updateProductPriceData();
			System.out.println("New price added for "+ productName + " : " + newUnitPrice);
		}
		else {
			System.out.println("No such product in the inventory! Please make sure all letters spelled correctly. "
					+ "If you need to view productlist enter 1 (or) exit - 0");
			int viewInventoryListOption = getIntUserInput();
			if (viewInventoryListOption == 1) {
				viewProductList();
				System.out.println("Do you need to update price or need to add new product : "
						+ "\n1 - UPDATE PRICE "
						+ "\n2 - ADD NEW PRODUCT"
						+ "\n0 - Exit");
				int updateOrAddProductOption = getIntUserInput();
				if (updateOrAddProductOption == 1){
					updateUnitPrice (0);
				}
				else if (updateOrAddProductOption == 2) {
					addNewProduct();
				}
			}
		}
		
	}
	
	public void viewInventory () {
		if (!inventory.isEmpty()) {
			for(Integer id : inventory.keySet() ) {
				System.out.println ("Product Name : " + inventoryList.get(id) + " Quantity :"+ inventory.get(id));
			}
		}
		else {
			System.out.println("Inventory is empty! Please add update inventory.");
		}
	}
	
	public void refillProducts (int productId) {
		try {
			String productNameToRefillInventory ;
			if(productId != 0) {
				productNameToRefillInventory = inventoryList.get(productId);
			}
			else {
				viewProductList();
				System.out.println("Enter the product name to refill : ");
				productNameToRefillInventory = getStringUserInput();
				productId = getProductIdByProductName(productNameToRefillInventory);
			}
			
			float existingquantityOfEnteredProduct = 0 ;
			if (inventory.containsKey(productId)) {
				existingquantityOfEnteredProduct = inventory.get(productId) ;
				System.out.println("Old price of "+ productNameToRefillInventory + " : "+ existingquantityOfEnteredProduct); 
			}
			System.out.println("\nEnter the  quantity of "+ productNameToRefillInventory +" to update :");
			float quantityToUpdate = getFloatUserInput();
			float updatedQuantity = existingquantityOfEnteredProduct + quantityToUpdate ;
			inventory.put(productId,updatedQuantity);
			updateInventoryData();
			System.out.println("Quantity Updated !");
			viewInventory();
			
			
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void purchase (String currentUser) {
		try {
			boolean keepPurchasingFlag = true ;
			int keepPurchasingOption = 0 ;
			float productQuantityToPurchase, existingQuantityOfProduct, updatedQuantityOfProduct;
			HashMap <Integer,Float> purchaseOrder = new HashMap <Integer,Float>(); 
			while(keepPurchasingFlag == true) {
				viewProductList();
				System.out.println("Enter the product name :");
				String productNameToPurchase = getStringUserInput();
				int productId = getProductIdByProductName (productNameToPurchase);
				if(productId != 0) {
					System.out.println("Enter the quantity :");
					productQuantityToPurchase = getFloatUserInput();
					if(inventory.containsKey(productId)) {
						existingQuantityOfProduct = inventory.get(productId);
						updatedQuantityOfProduct = existingQuantityOfProduct - productQuantityToPurchase;
						if(updatedQuantityOfProduct > 0) {
							purchaseOrder.put(productId,productQuantityToPurchase);
							System.out.println("Do you wish to purchase another product : yes (1) or No (0)" );
							keepPurchasingOption = getIntUserInput();
							if (keepPurchasingOption == 1) {
								continue;
							}
							else {
								keepPurchasingFlag = false;
								break;
							}
						}
					}
					else {
						System.out.println("OUT OF STOCK!");
					}
					
				}
				else {
					System.out.println("No such product avaialble!");
					continue;
				}
			}
			generateBill(purchaseOrder,currentUser);
				
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void generateBill (HashMap <Integer,Float> purchaseList, String currentUser) {
		try {
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			LocalDateTime now = LocalDateTime.now();  
			File file = new File (billFilePath);
			BufferedWriter billWriter = new BufferedWriter (new FileWriter(file,true));
			billWriter.write("Bill No : \t \t \t \t Date: " + dateFormat.format(now)
							+ "\nBiller Name : "+ currentUser);
			billWriter.write("PRODUCT NAME \t UNIT PRICE \t QUANTITY \t AMOUNT");
			System.out.println("Bill No : \t \t \t \t Date: " + dateFormat.format(now)
			+ "\nBiller Name : "+ currentUser);
			System.out.println("PRODUCT NAME \t UNIT PRICE \t QUANTITY \t AMOUNT");
			billWriter.newLine();
			String productName;
			int generateBillOption ;
			Float quantity, unitPriceOfProduct, productPrice, totalPrice = 0F;
			for(Integer id : purchaseList.keySet()) {
				productName = inventoryList.get(id);
				quantity = purchaseList.get(id);
				unitPriceOfProduct = unitPrice.get(id);
				productPrice = quantity * unitPriceOfProduct;
				totalPrice += productPrice;
				billWriter.write(productName + "\t \t \t " + unitPriceOfProduct + "\t \t \t " + quantity + "\t \t \t " + productPrice );
				System.out.println(productName + "\t \t " + unitPriceOfProduct + "\t \t " + quantity + "\t \t " + productPrice);
				billWriter.newLine();
			}
			billWriter.newLine();
			billWriter.write("\t\t\t\t\t  TOTAL : " + totalPrice);
			billWriter.newLine();
			System.out.println("\t\t\t\t\t TOTAL : " + totalPrice);
			System.out.println("Please confirm to generate bill : YES (1) or No (0)");
			generateBillOption = getIntUserInput();
			if (generateBillOption == 1) {
				billWriter.flush();
				billWriter.close();
				System.out.println("Purchase Completed! \nThank You ! Welcome !");
				makePurchaseInInventory(purchaseList);
			}
			else {
				billWriter.close();
				System.out.println("OOPS previous purchase cancelled!");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void makePurchaseInInventory (HashMap <Integer,Float> orderList) {
		float productQuantityPurchased, existingQuantityOfProduct, updatedQuantityOfProduct;
		for (Integer id : orderList.keySet()) {
			productQuantityPurchased = orderList.get(id);
			existingQuantityOfProduct = inventory.get(id);
			updatedQuantityOfProduct = existingQuantityOfProduct - productQuantityPurchased;
			inventory.put(id,updatedQuantityOfProduct );
			updateInventoryData();
		}
	}

}

class Employee extends Inventory {
	public static final String employeeDirectoryFilePath = "EMPLOYEEDIRECTORY.txt" , 
							   userCredentialsFilePath = "CREDENTIALS.txt",
							   defaultUserPassword = "WELCOME@123";
	public static HashMap <Integer,ArrayList> employeeData = new HashMap <Integer,ArrayList> ();
	public static HashMap <String,String> userCredentials = new HashMap <String,String>();
	public static int newEmployeeId = 1;
	
	public Employee () {
		updatedEmployeeListWithDetails ();
		updatedUsernamePassword ();
		
	}
	
	public void updatedEmployeeListWithDetails () {
		try{
			File file = new File (employeeDirectoryFilePath);
			if (!file.exists()){
				file.createNewFile();
			}
			int employeeIdKey = 0;
			BufferedReader readerForEmployeeDirectory = new BufferedReader(new FileReader(file));
			String fileReaderByLine, employeeDetailsValueString;
			String [] fileLineArray, employeeDetailsValueArray;
			while ((fileReaderByLine = readerForEmployeeDirectory.readLine()) != null){
				fileLineArray = fileReaderByLine.split(":");
				employeeIdKey = Integer.parseInt(fileLineArray[0]);
				employeeDetailsValueString = fileLineArray[1].replaceAll("\\p{P}", "");
				employeeDetailsValueArray = employeeDetailsValueString.split(" ");
				ArrayList employeeDetails = new ArrayList (); 
				Collections.addAll(employeeDetails, employeeDetailsValueArray);
				employeeData.put(employeeIdKey, employeeDetails);
			}
			newEmployeeId = employeeIdKey + 1 ;
			
			
		}
		catch (Exception e){
			System.out.println(e);
		}
	}
	
	public void updatedUsernamePassword () {
		try{
			File file = new File (userCredentialsFilePath);
			if (!file.exists()){
				file.createNewFile();
			}
			BufferedReader readerForEmployeeDirectory = new BufferedReader(new FileReader(file));
			String fileReaderByLine, username, password;
			String [] fileLineArray;
			while ((fileReaderByLine = readerForEmployeeDirectory.readLine()) != null){
				fileLineArray = fileReaderByLine.split(":");
				username = fileLineArray[0];
				password = fileLineArray[1];
				userCredentials.put(username, password);
			}			
		}
		catch (Exception e){
			System.out.println(e);
		}
	}
	
	public void updateEmployeeDetailsInDb () {
		try {
			File file = new File (employeeDirectoryFilePath);
			BufferedWriter writerForEmployeeDeirectory = new BufferedWriter( new FileWriter(file) );
			for (Map.Entry<Integer, ArrayList> entry : employeeData.entrySet()){
				writerForEmployeeDeirectory.write(entry.getKey() + ":" + entry.getValue());
				writerForEmployeeDeirectory.newLine();
			}
			writerForEmployeeDeirectory.flush();
			writerForEmployeeDeirectory.close();
			
		}
		catch (Exception e) {
			
		}
	}
	
	public void updateCredentials () {
		try {
			File file = new File (userCredentialsFilePath);
			BufferedWriter writerForUserCred = new BufferedWriter( new FileWriter(file) );
			for (Map.Entry<String, String> entry : userCredentials.entrySet()) {
				writerForUserCred.write(entry.getKey() +":"+ entry.getValue());
				writerForUserCred.newLine();
			}
			writerForUserCred.flush();
			writerForUserCred.close();
			
		}
		catch (Exception e) {
			
		}
	}
	
	public ArrayList getUserEmployeeDetials () {
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
			ArrayList  employeeDetails = new ArrayList (getUserEmployeeDetials ());
			System.out.println("new emoloyee id :" + newEmployeeId);
			employeeData.put (newEmployeeId,employeeDetails);
			updateEmployeeDetailsInDb();
			String autogeneratedUsername = employeeDetails.get(0) + "_"+newEmployeeId;
			userCredentials.put(autogeneratedUsername, defaultUserPassword);
			updateCredentials();
			System.out.println("Employee added successfully \nThe username is : "+ autogeneratedUsername + " and password :" + defaultUserPassword);
			newEmployeeId ++;
		}
		catch (Exception e) {
			System.out.println("OOPS something went wrong : " + e);
		}
	}
	
	public void removeEmployee () {
		try {
			System.out.println("Please enter the employee ID , you need to remove : ");
			int employeeIdToRemove = getIntUserInput();		
			if (employeeData.containsKey(employeeIdToRemove)) {
				System.out.println("Please confirm to remove the following employee from the organization : \n" );
				ArrayList employeeDetailList = new ArrayList (employeeData.get(employeeIdToRemove));
				System.out.println("NAME : "+employeeDetailList.get(0) 
								+"\nMOBILE : " + employeeDetailList.get(1) 
								+ "EMAIL : "+employeeDetailList.get(2));
				System.out.println ("Please enter 1 to DELETE (OR) 0 to CANCEl");
				int deleteConfirmation = getIntUserInput();
				if (deleteConfirmation == 1) {
					System.out.println("Removed : "+employeeData.remove(employeeIdToRemove));
					updateEmployeeDetailsInDb();
					System.out.println("Do you need to remove another employee ?  "
							+ "1 - YES (OR) 0 - NO ");
					int removeAnotherEmp = getIntUserInput();
					if (removeAnotherEmp == 1) {
						removeEmployee();
					}
				}
			}
			else {
				System.out.println("No such employee ID present ! "
						+ "Do you need to re-enter the employee Id : "
						+ "press 1 or 0 for exit");
				int re_enterOption = getIntUserInput();
				if (re_enterOption == 1){
					removeEmployee();
				}
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	public void resetPassword (String userName , String newPassword) {
		userCredentials.put(userName, newPassword);
		updateCredentials();
		System.out.println("Password Updated Successfully! Login Again using new credentials.");
		}
	
}

public class SuperMarketTool {

	public static void main(String[] args) {
		Inventory inventory =new Inventory ();
		Employee employee = new Employee ();
		boolean staySignedInFlag  ;
		System.out.println("Please enter any one of the option "
				+ "\n1 - Admin Login "
				+ "\n2 - Employee Login"
				+ "\n0 - Exit");
		int loginAs = Inventory.getIntUserInput();
		if (loginAs == 1) {
			System.out.println("Admin username : ADMIN");
			System.out.println("Enter the admin password :");
			String adminPassword = Inventory.getStringUserInput();
			if(adminPassword.equals("ADMIN")) {
				staySignedInFlag = true;
				while (staySignedInFlag ) {
					System.out.println("Please enter the operation to perform : "
							+ "\n1 - ADD EMPLOYEE "
							+ "\n2 - REMOVE EMPLOYEE "
							+ "\n3 - ADD NEW PRODUCT TO INVENTORY "
							+ "\n4 - UPDATE EXISTING PRODUCT UNIT PRICE "
							+ "\n5 - VIEW INVENTORY"
							+ "\n6 - REFILL INVENTORY PRODUCTS "
							+ "\n7 - MAKE A SALE"
							+ "\n8 - EXIT");
					int adminAction = Inventory.getIntUserInput ();
					switch (adminAction) {
						case 0 : continue;
						case 1: employee.addEmployee ();
						break;
						case 2: employee.removeEmployee();
						break;
						case 3: employee.addNewProduct();
						break;
						case 4: employee.updateUnitPrice(0);
						break;
						case 5: employee.viewInventory();
						break;
						case 6: employee.refillProducts(0);
						break;
						case 7: employee.purchase("ADMIN");
						break;
						case 8: staySignedInFlag = false;
								System.out.println("Logged Out Successfully");
						break;
						default : System.out.println("No such option");
						break;
					}
				}
			}
			else {
				System.out.println("Invalid Password !");
			}
	
		}
		else if (loginAs == 2) {
			staySignedInFlag = true;
			
			while (staySignedInFlag == true) {
				System.out.println("Username :");
				String username = Inventory.getStringUserInput();
				if (Employee.userCredentials.containsKey(username)) {
					System.out.println("Password :");
					String password = Inventory.getStringUserInput();
					String existingPassword =Employee.userCredentials.get(username);
					if(existingPassword.equals(password)) {
						if (password.equals(employee.defaultUserPassword)) {
							System.out.println("You need to reset your password !"
									+ "\n New Password :");
							String newpassword = Inventory.getStringUserInput();
							System.out.println("Re-enter Password :");
							String reEnterPassword = Inventory.getStringUserInput();
							if(newpassword.equals(reEnterPassword)) {
								employee.resetPassword(username,newpassword);
								continue;
								}
							}
						else {
							String [] employeeName = username.split("_");
							while(staySignedInFlag == true) {
								System.out.println("Ënter the option : "
										+ "\n1 - MAKE A SALE"
										+ "\n2 - VIEW INVENTORY"
										+ "\n3 - EXIT");
								int employeeAction = Inventory.getIntUserInput();
								switch(employeeAction) {
								case 1: employee.purchase(employeeName[0]);
								break;
								case 2: employee.viewInventory();
								break;
								case 3: staySignedInFlag = false;
								System.out.println("Logged Out Successfully");
								break;
								default: System.out.println("No such option");
								break;
								}
							}
						}
					}
					else {
						System.out.println("Invalid Password! Re-enter username and password !");
						continue;
					}
				}
				else {
					System.out.println("Username doesn't exist! hint(USERNAME ==> Your Name followed by a underscore followed by your Id [empname_empId])"
							+ "Enter 1 to re-enter username 0 - exit");
					int reEnterUsername = Inventory.getIntUserInput();
					if (reEnterUsername == 1) {
						continue;
					}
					else {
						staySignedInFlag = false;
						break;
					}
					
				}
			}
		}
		else {
			System.out.println("END");
		}

	}

}
