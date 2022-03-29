package myPackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Market {
	public static Scanner sc = new Scanner (System.in);
	public static HashMap <Integer,String> inventoryList = new HashMap <Integer,String>();
	public static HashMap <Integer,Float> unitPrice = new HashMap <Integer,Float>();
	public static HashMap <Integer,Float> inventory = new HashMap <Integer, Float>();
	public static int newProductId = 1;
	public static final String employeeDirectoryFilePath = "EMPLOYEEDIRECTORY.txt", 
								productListFilePath = "PRODUCTLIST.txt", 
								productPriceListFilePath = "PRICELIST.txt",
								inventoryListDataFilePath = "INVENTORYDATA.txt",
								billFilePath = "BILL.txt";
	
	public Market () {
		updatedProductList ();
		updatedUnitPriceData ();
		updatedInventoryData();
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
	
	public void updateInventory () {
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
	
	public void updateInventoryData () {
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
	
	public void updateProductPriceData () {
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
	
	public HashMap getEmployeeDirectory (){
		try{
			File file = new File (employeeDirectoryFilePath);
			if (!file.exists()){
				file.createNewFile();
			}
			int employeeIdKey;
			HashMap <Integer,ArrayList> employeeDirectory = new HashMap<Integer,ArrayList>();
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
				employeeDirectory.put(employeeIdKey, employeeDetails);
			}
			return employeeDirectory;
			
		}
		catch (Exception e){
			System.out.println(e);
			return null;
		}
	}
	public void updateEmployeeDirectory (HashMap<Integer,ArrayList> updatedEmployeeDirectory)
	{
		try {
			File file = new File (employeeDirectoryFilePath);
			BufferedWriter writerForEmployeeDeirectory = new BufferedWriter( new FileWriter(file) );
			for (Map.Entry<Integer, ArrayList> entry : updatedEmployeeDirectory.entrySet()){
				writerForEmployeeDeirectory.write(entry.getKey() + ":" + entry.getValue());
				writerForEmployeeDeirectory.newLine();
			}
			writerForEmployeeDeirectory.flush();
			writerForEmployeeDeirectory.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		
	}
	
	public void addEmployee () {
		try {
			ArrayList  employeeDetails = new ArrayList (getUserEmployeeDetials ());
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
			System.out.println("Employee added successfully");
		}
		catch (Exception e) {
			System.out.println("OOPS something went wrong : " + e);
		}
	}
	
	public void removeEmployee () {
		try {
			System.out.println("Please enter the employee ID , you need to remove : ");
			int employeeIdToRemove = getIntUserInput();
			HashMap <Integer,ArrayList> employeeDetails = new HashMap<Integer,ArrayList>(getEmployeeDirectory());
			if (employeeDetails.containsKey(employeeIdToRemove)) {
				System.out.println("Please confirm to remove the following employee from the organization : \n" );
				ArrayList employeeDetailList = new ArrayList (employeeDetails.get(employeeIdToRemove));
				System.out.println("NAME : "+employeeDetailList.get(0) 
								+"\nMOBILE : " + employeeDetailList.get(1) 
								+ "EMAIL : "+employeeDetailList.get(2));
				System.out.println ("Please enter 1 to DELETE (OR) 0 to CANCEl");
				int deleteConfirmation = getIntUserInput();
				if (deleteConfirmation == 1) {
					System.out.println("Removed : "+employeeDetails.remove(employeeIdToRemove));
					updateEmployeeDirectory(employeeDetails);
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
				if (re_enterOption == 1)
				{
					removeEmployee();
				}
			}
		}
		catch (Exception e) {
			System.out.println(e);
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
			for (Map.Entry<Integer, String> entry: inventoryList.entrySet())
	        {
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
				if (updateOrAddProductOption == 1)
				{
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
	
	public void purchase () {
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
			generateBill(purchaseOrder);
				
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void generateBill (HashMap <Integer,Float> purchaseList) {
		try {
			File file = new File (billFilePath);
			BufferedWriter billWriter = new BufferedWriter (new FileWriter(file,true));
			billWriter.write("PRODUCT NAME \t UNIT PRICE \t QUANTITY \t AMOUNT");
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

	
	public static void main (String args []) {
		
		boolean staySignedInFlag  ;
		Market market = new Market ();
		System.out.println("Please enter any one of the option "
				+ "\n1 - Login "
				+ "\n0 - Exit");
		int loginAs = getIntUserInput();
		if (loginAs == 1) {
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
				int adminAction = getIntUserInput ();
				switch (adminAction) {
					case 0 : continue;
					case 1: market.addEmployee ();
					break;
					case 2: market.removeEmployee();
					break;
					case 3: market.addNewProduct();
					break;
					case 4: market.updateUnitPrice(0);
					break;
					case 5: market.viewInventory();
					break;
					case 6: market.refillProducts(0);
					break;
					case 7: market.purchase();
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
			System.out.println("END");
		}
	}
}
