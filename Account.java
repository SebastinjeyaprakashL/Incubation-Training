package myPackage;
import java.util.*;

class Bank {
	private long accountnumber;
	private String name, username, password;
	private float balance;
	public static final String bankName () {
		String bankname = "BANK OF XYZ";
		return bankname;
	}
	protected void setAccDetails (long accNum, String name, String username, String pwd) {
		this.accountnumber = accNum;
		this.name = name;
		this.username = username;
		this.password = pwd;
	}
	protected long getAccNum () {
		return this.accountnumber;
	}
	protected String getName () {
		return this.name;
	}
	protected String getUserName () {
		return this.username;
	}
	protected String getPassword () {
		return this.password;
	}
	protected void setBalance (float bal) {
		this.balance = bal;
	}
	protected float getBalance () {
		return this.balance;
	}
}

class Customer1 extends Bank {
	Customer1 (long myaccNum, String myName, String myUser, String myPwd) {
		setAccDetails (myaccNum, myName, myUser, myPwd);	
		setBalance (1000);
	}
	boolean verifyUsername (String usrname) {
		String dbUsername = getUserName ();
		if (dbUsername.equals (usrname))
			return true;
		else
			return false;
	}
	boolean verifyPwd (String pwd) {
		String dbpwd = getPassword ();
		if (dbpwd.equals (pwd))
			return true;
		else
			return false;
	}
	boolean checkForDebit (float amount) {
		float availAmount = getBalance ();
		if (availAmount > amount) {
			float updatedBalance = availAmount - amount;
			setBalance (updatedBalance);
			return true;
		}
		else
			return false;
	}
	void updateAccBal (float balAmount) {
		float availAmount = getBalance ();
		float updatedBalance = availAmount - balAmount;
		setBalance (updatedBalance);
	}
	float checkbalance () {
		float availAmount = getBalance ();
		return availAmount;
	}
}

public class Account {
	public static void main (String args[]) {
		boolean loginFlag = true, flag = true;
		Scanner sc = new Scanner (System.in);
		String username, pwd;
		int option, loginAction;
		float debitamount;
		Customer1 a1 = new Customer1 (1234, "Test1", "user1", "Test#@!");	// db
		String bankName = Bank.bankName();
		System.out.println ("Welcome to "+bankName);
		do {
			System.out.println("Please enter the action to proceed: \n1.Login \n2.Exit");
			loginAction = sc.nextInt();
			sc.nextLine();
			if (loginAction == 1) {
				System.out.println("Please enter your Username and Password to Login \nUsername : ");
				username = sc.nextLine ();
				if (username == "0") {
					loginFlag = false;
				}
				else {
					boolean isUserExist = a1.verifyUsername (username);
						if (isUserExist) {
							System.out.println ("\nPassword :\n");
							pwd = sc.nextLine ();
							if (pwd == "0") {
								loginFlag = false;
							}
							boolean verifypwd = a1.verifyPwd (pwd);
							if (verifypwd) {
								System.out.println ("Login Successfull !");
								while (loginFlag == true) {
									System.out.println(" Enter the option to perform : \n 1.CHECK BAL \n 2.TRANSFER \n 3.Exit");
									option = sc.nextInt ();
									sc.nextLine ();
									if (option == 1) {
										float balamount = a1.checkbalance ();
										System.out.println ("Account Balance : " + balamount);		
									}
									else if (option == 2) {
										System.out.println ("Enter the amount to transfer: ");
										debitamount = sc.nextFloat();
										sc.nextLine ();
										boolean checkBalForTrans = a1.checkForDebit (debitamount);
										if (!checkBalForTrans) {
											System.out.println("Debit amount exceeds current balance. Please eneter valid amount !\n");
										}
										else {
											System.out.println ("Dear customer Rs." + debitamount +" has been debited from your account.");
										}
									}
									else {  
										sc.close();
										System.out.println("Successfully logged out !");
										loginFlag = false;
									}
								}
							}
							else { 
								System.out.println("Password is incorrect !");
							}
						}
						else {  
							System.out.println("Username doesn't exist !  ");
						}
				}
			}
			else {
				sc.close();
				System.out.println("Exited");
				flag = false;
			}
		}while(flag == true);
	}
}

