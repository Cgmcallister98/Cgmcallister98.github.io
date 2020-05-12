/* Cassidy McAllister */

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BankAccountswClasses {

	public static void main(String[] args) throws IOException {

		// constant definitions
		final int MAX_NUM = 50;

		// variable declarations
		BankAccount[] accounts = new BankAccount[MAX_NUM];
		//crates Bank accounts array reference 
		int numAccts; // number of accounts
		char choice; // menu item selected
		boolean notDone = true; // loop control flag

		// open input test cases file
		File testCases = new File("mytestcases.txt");

		// create Scanner object
		Scanner kybd = new Scanner(testCases);
		//Scanner kybd = new Scanner(System.in);

		// open the output file
		PrintWriter outFile = new PrintWriter("myoutput.txt");
		//PrintWriter outFile = new PrintWriter(System.out);

		/* first part */
		/* fill and print initial database */
		numAccts = readAccts(accounts, MAX_NUM);
		printAccts(accounts, numAccts, outFile);

		/* second part */
		/* prompts for a transaction and then */
		/* call functions to process the requested transaction */
		do {
			menu();
			choice = kybd.next().charAt(0);
			switch (choice) {
			case 'q':
			case 'Q':
				notDone = false;
				printAccts(accounts, numAccts, outFile);
				break;
			case 'b':
			case 'B':
				balance(accounts, numAccts, outFile, kybd);
				break;
			case 'd':
			case 'D':
				deposit(accounts, numAccts, outFile, kybd);
				break;
			case 'c':
			case 'C':
				clearCheck();
			case 'w':
			case 'W':
				withdrawal(accounts, numAccts, outFile, kybd);
				break;
			case 'n':
			case 'N':
				numAccts = newAcct(accounts, numAccts, outFile, kybd);
				break;
			case 'i':
			case 'I': 
				accountInfo(accounts, numAccts, outFile, kybd);
				break;
			case 'x':
			case 'X':
				numAccts = deleteAcct(accounts, numAccts, outFile, kybd);
				break;
			default:
				outFile.println("Error: " + choice + " is an invalid selection"
								+ " - try again");
				outFile.println();
				outFile.flush();
				break;
			}
			// give user a chance to look at output before printing menu
			// pause(kybd);
		} while (notDone);
		// close the output file
		outFile.close();

		// close the test cases input file
		kybd.close();

		System.out.println();
		System.out.println("The program is terminating");
	}
	
	public static int readAccts(BankAccount[] account, int maxAccts) 
		throws IOException {
		// open database input file
		// create File object
		File dbFile = new File("myinput.txt");

		// create Scanner object
		Scanner sc = new Scanner(dbFile);

		int count = 0; // account number counter
		
		//local variables
		String line;
		String tempStr;

		while (sc.hasNext() && count < maxAccts) {
			BankAccount myAccount = new BankAccount();
			
			line = sc.nextLine();
			StringTokenizer myLine = new StringTokenizer(line);
			
			myAccount.setFirst(myLine.nextToken());
			myAccount.setLast(myLine.nextToken());
			myAccount.setSSNumber(myLine.nextToken());
			tempStr = myLine.nextToken();
			myAccount.setAccNumber(Integer.parseInt(tempStr)); 
			myAccount.setAccType(myLine.nextToken());
			tempStr = myLine.nextToken();
			myAccount.setAccBalance(Double.parseDouble(tempStr));
			account[count]= myAccount; //set array element
			count++; //increment the account count
		}

		// close the input file
		sc.close();

		// return the account number count
		return count;
	}

	/*
	 * Method printAccts: 
	 * Input: account - array of accounts and their info 
	 * numAccts - number of active accounts
	 * outFile - reference to the output file 
	 * Process: Prints the database of accounts and balances 
	 * Output: Prints the database of accounts and balances
	 */
	public static void printAccts(BankAccount[] account, int numAccts, 
					   PrintWriter outFile) {
		outFile.println();
		outFile.println("\t\tDatabase of Bank Accounts");
		outFile.println();
		outFile.println("First Name   Last Name \t    SSID    Account  Type"
						+"     Balance");
		for (int index = 0; index < numAccts; index++) {
			outFile.printf("%-12s %-12s %-9s %-7d %-8s $%7.2f", 
			account[index].getFirst(), account[index].getLast(), 
			account[index].getSSNumber(), account[index].getAccNumber(), 
			account[index].getAccType(), account[index].getAccBalance());
			
			outFile.println();
			
		}
		outFile.println();

		// flush the output file
		outFile.flush();
	}

	/*
	 * Method menu() Input: none 
	 * Process: Prints the menu of transaction choices
	 * Output: Prints the menu of transaction choices
	 */
	public static void menu() {
		System.out.println();
		System.out.println("Select one of the following transactions:");
		System.out.println("\t****************************");
		System.out.println("\t    List of Choices         ");
		System.out.println("\t****************************");
		System.out.println("\t     W -- Withdrawal");
		System.out.println("\t     D -- Deposit");
		System.out.println("\t     N -- New Account");
		System.out.println("\t     B -- Balance Inquiry");
		System.out.println("\t     I -- Account Info");
		System.out.println("\t     X -- Delete Account");
		System.out.println("\t     Q -- Quit");
		System.out.println();
		System.out.print("\tEnter your selection: ");
	}

	/*
	 * Method findAcct: 
	 * Input: account - array of accounts and their info 
	 * numAccts - number of active accounts 
	 * requestedAccount - requested account 
	 * Process: Performs a linear search on the account object array for
	 * the requested account 
	 * Output: If found, the index of the requested account is
	 * returned Otherwise, returns -1
	 */
	public static int findAcct(BankAccount[] account, int numAccts, 
								int requestedAccount) {
		for (int index = 0; index < numAccts; index++)
			if (account[index].getAccNumber() == requestedAccount)
				return index;
		return -1;
	}

	/*
	 * Method balance: 
	 * Input: account - array of accounts and their info 
	 * numAccts - number of active accounts 
	 * outFile - reference to output file 
	 * kybd - reference to the "test cases" input file
	 * Process: Prompts for the requested account Calls findAcct() to see if the
	 * account exists If the account exists, the balance is printed 
	 * Otherwise, an error message is printed 
	 * Output: If the account exists, the balance is printed 
	 * Otherwise, an error message is printed
	 */
	public static void balance(BankAccount[] account, int numAccts, 
								PrintWriter outFile, Scanner kybd) {
		int requestedAccount;
		int index;

		System.out.println(); // prompt for account number
		System.out.print("Enter the account number: ");
		requestedAccount = kybd.nextInt(); // read-in the account number

		// call findAcct to search if requestedAccount exists
		index = findAcct(account, numAccts, requestedAccount);

		if (index == -1) // invalid account
		{
			outFile.println("Transaction Requested: Balance Inquiry");
			outFile.println("Error: Account number " + requestedAccount + 
							" does not exist");
		} else // valid account
		{
			outFile.println("Transaction Requested: Balance Inquiry");
			outFile.println("Account Number: " + requestedAccount);
			outFile.printf("Current Balance: $%.2f",
						   account[index].getAccBalance());
			outFile.println();
		} 
		outFile.println();

		outFile.flush(); // flush the output buffer
	}

	/*
	 * Method deposit: 
	 * Input: account - array of accounts and their info
	 * numAccts - number of active accounts 
	 * outFile - reference to the output file 
	 * kybd - reference to the "test cases" input file
	 * Process: Prompts for the requested account Calls findacct() to see if the
	 * account exists If the account exists, prompts for the amount to deposit 
	 * If the amount is valid, it makes the deposit and prints the new balance
	 * Otherwise, an error message is printed 
	 * Output: For a valid deposit, the deposit transaction is printed 
	 * Otherwise, an error message is printed
	 */
	public static void deposit(BankAccount[] account, int numAccts, 
							   PrintWriter outFile, Scanner kybd) {
		int requestedAccount;
		int index;
		double amountToDeposit;

		System.out.println();// prompt for account number
		System.out.print("Enter the account number: "); 
		requestedAccount = kybd.nextInt(); // read-in the account number

		// call findAcct to search if requestedAccount exists
		index = findAcct(account, numAccts, requestedAccount);

		if (index == -1) // invalid account
		{
			outFile.println("Transaction Requested: Deposit");
			outFile.println("Error: Account number " + requestedAccount + 
							" does not exist");
		} else // valid account
		{     // prompt for amount to deposit
			System.out.print("Enter amount to deposit: ");
			amountToDeposit = kybd.nextDouble(); 
			// read-in the amount to deposit

			if (amountToDeposit <= 0.00) {
				// invalid amount to deposit
				outFile.println("Transaction Requested: Deposit");
				outFile.println("Account Number: " + requestedAccount);
				outFile.println("Amount to Deposit: $" + amountToDeposit);
				outFile.printf("Error: $%.2f is an invalid amount", 
								amountToDeposit);
				outFile.println();
			} else {// make the deposit
				outFile.println("Transaction Requested: Deposit");
				outFile.println("Account Number: " + requestedAccount);
				outFile.printf("Old Balance: $%.2f",
							   account[index].getAccBalance());
				outFile.println();
				outFile.println("Amount to Deposit: $" + amountToDeposit);
				account[index].setAccBalance(amountToDeposit + 
						                     account[index].getAccBalance()); 
				outFile.printf("New Balance: $%.2f", 
						       account[index].getAccBalance());
				outFile.println();
			}
		}
		outFile.println();

		outFile.flush(); // flush the output buffer
	}

	/*
	 * Method withdrawal: 
	 * Input: account - array of accounts and their info 
	 * numAccts - number of active accounts 
	 * outFile - reference to the output file 
	 * kybd - reference to the "test cases" input file
	 * Process: Prompts for the requested account Calls findacct() to see if the
	 * account exists If the account exists, prompts for the amount to withdraw 
	 * If the amount is valid, it makes the withdraw and prints the new balance
	 * Otherwise, an error message is printed for either insufficient funds or 
	 * invalid withdraw amount 
	 * Output: For a valid withdraw, the withdraw transaction is printed 
	 * Otherwise, an error message is printed
	 */
	public static void withdrawal(BankAccount[] account, int numAccts, 
								  PrintWriter outFile, Scanner kybd) {
		int requestedAccount;
		int index;
		double amountToWithdraw;

		System.out.println();// prompt for account number
		System.out.print("Enter the account number: "); 
		requestedAccount = kybd.nextInt(); // read-in the account number

		// call findAcct to search if requestedAccount exists
		index = findAcct(account, numAccts, requestedAccount);

		if (index == -1) // invalid account
		{
			outFile.println("Transaction Requested: Withdrawal");
			outFile.println("Error: Account number " + requestedAccount + 
						    " does not exist");
		} else // valid account
		{// prompt for amount to Withdraw
			System.out.print("Enter amount to Withdraw: "); 
			amountToWithdraw = kybd.nextDouble(); 
			// read-in the amount to Withdraw

			if (amountToWithdraw <= 0.00) {
				// invalid amount to Withdraw
				outFile.println("Transaction Requested: Withdrawal");
				outFile.println("Account Number: " + requestedAccount);
				outFile.println("Amount to Withdraw: $" + amountToWithdraw);
				outFile.printf("Error: $%.2f is an invalid amount", 
							   amountToWithdraw);
				outFile.println();
			} else {

				if (amountToWithdraw > 0.00 && amountToWithdraw > 
					account[index].getAccBalance()) {
					// insuffiecent funds error
					outFile.println("Transaction Requested: Withdrawal");
					outFile.println("Account Number: " + requestedAccount);
					outFile.println("Amount to Withdraw: $" + amountToWithdraw);
					outFile.println("Error: Insuffiecnt funds");
					outFile.println("Account Balance: " + 
								    account[index].getAccBalance());
					outFile.println();
				}

				else {
					//Working Withdrawl
					outFile.println("Transaction Requested: Withdrawal");
					outFile.println("Account Number: " + requestedAccount);
					outFile.printf("Old Balance: $%.2f", 
							       account[index].getAccBalance());
					outFile.println();
					outFile.println("Amount to Withdraw: $" + amountToWithdraw);
					account[index].setAccBalance(account[index].getAccBalance() 
									  - amountToWithdraw); // make the Withdraw
					outFile.printf("New Balance: $%.2f", 
							       account[index].getAccBalance());
					outFile.println();
				}
			}
		}
		outFile.println();

		outFile.flush(); // flush the output buffer
	}
	/*
	 * Method newAcct: 
	 * Input: account - array of accounts and their info
	 * numAccts - number of active accounts 
	 * outFile - reference to the output file 
	 * kybd - reference to the "test cases" input file
	 * Process: Prompts for the requested account number Calls findacct() 
	 * to see if the account exists If the account doesn't exist, 
	 * the new account number is stored in the array 
	 * and has a balance set to 0, if it does exist an error is printed. 
	 * Output: For a valid new account, the new account info is printed 
	 * and num accts is returned.
	 */
	public static int newAcct(BankAccount[] account, int numAccts, 
			PrintWriter outFile, Scanner kybd) {
		int requestedAccount;
		int index; 
		 
		System.out.println();// prompt for the new account number
		System.out.print("Enter the new account number: "); 
		requestedAccount = kybd.nextInt(); // read-in the account info
		
		index = findAcct(account, numAccts, requestedAccount);

		if (index > -1) { //already existing account error finder
			outFile.println("Transaction Requested: New Account");
			outFile.println("Error: Account number " + requestedAccount + 
							" already exists");
		} else { //Creation of new account
			BankAccount newAccount = new BankAccount();
			System.out.print("Enter the First Name of new account holder: ");
			newAccount.setFirst(kybd.next());
			System.out.print("Enter the last Name of the new account holder: ");
			newAccount.setLast(kybd.next());
			System.out.print("Enter the Social Security Number of" 
					+ "new account holder: ");
			newAccount.setSSNumber(kybd.next());
			System.out.print("Enter the account type for the new account: ");
			newAccount.setAccType(kybd.next());
			newAccount.setAccNumber(requestedAccount);
			account[numAccts]= newAccount;
			outFile.println("Transaction Requested: New Account");
			outFile.println("New Account Created");
			outFile.println("New Account Holder: " + 
							account[numAccts].getFirst() + " " + 
							account[numAccts].getLast());
			outFile.println("New Account Holder SSID: " + 
							account[numAccts].getSSNumber());
			outFile.println("New Account Number: " + requestedAccount);
			outFile.println("New Account Type: " + 
							account[numAccts].getAccType());
			outFile.println();
			
			numAccts++;
		}
		outFile.println();
		outFile.flush(); // flush the output buffer

		return numAccts;

	}

	/*
	 * Method deleteAcct: 
	 * Input: account - array of accounts and their info 
	 * numAccts - number of active accounts 
	 * outFile - reference to the output file 
	 * kybd - reference to the "test cases" input file
	 * Process:Prompts for the requested account Calls findacct() to see if the
	 * account exists If the account doesn't exist an error is printed,
	 *  otherwise the balance of the requested account is then checked 
	 *  if the balance is not 0 an error is printed, otherwise the account is 
	 *  deleted as well as the last element in the array is moved to close the
	 *   gap.
	 * Output: a prompt the account is deleted and the num of accts is returned
	 */
	public static int deleteAcct(BankAccount[] account, int numAccts, 
								 PrintWriter outFile, Scanner kybd) {
		int requestedAccount;
		int index;

		System.out.println();// prompt for future deleted account
		System.out.print("Enter the Account wanting to be Deleted: ");
		requestedAccount = kybd.nextInt(); // read-in the account to be deleted

		index = findAcct(account, numAccts, requestedAccount);

		if (index == -1) { // Account DNE error
			outFile.println("Transaction Requested: Delete Account");
			outFile.println("Error: Account " + requestedAccount + 
							" does not exist");
		} else {//Account exists but non-zero balance error
			if (account[index].getAccBalance() > 0.0) {
				outFile.println("Transaction Requested: Delete Account");
				outFile.println("Error: Account " + requestedAccount + 
								" has a non-zero balance");
				outFile.println("Account Balance: " + 
								account[index].getAccBalance());
			} else {//actual deletion of account
				account[index] = account[numAccts-1];
				outFile.println("Transaction Requested: Delete Account");
				outFile.println("Account Deleted: " + requestedAccount);
				numAccts--;
			}
		}
		outFile.println();
		outFile.flush(); // flush the output buffer
		return numAccts;
	}
	
	/*
	 * Method accountInfo: 
	 * Input: account - array of accounts and their info 
	 * numAccts - number of active accounts 
	 * requestedAccount - requested account 
	 * Process: Performs a linear search on the account object for
	 * the requested account SSID. If it exists it prints out all instances of 
	 * that SSID with the accompanying info
	 * Output: If found, the info of the requested account is
	 * printed otherwise and error is printed.
	 */
	public static void accountInfo(BankAccount[] account, int numAccts, 
								   PrintWriter outFile, Scanner kybd ) {
		
		String requestedAccount;
		int count=0;
		
		System.out.println("Enter SSID of Account Holder: ");
		requestedAccount = kybd.next();
			
		for (int index = 0; index < numAccts; index++)
			if (account[index].getSSNumber().equals(requestedAccount)) {
		
			outFile.println("Transaction Requested: Account Info");
			outFile.println("Account Holder: " + account[index].getFirst() + " " 
							+ account[index].getLast());
			outFile.println("Account Holder SSID: " + 
							account[index].getSSNumber());
			outFile.println("Account Number: " + account[index].getAccNumber());
			outFile.println("Account Type: " + account[index].getAccType());
			outFile.println("Account Balance: " + 
							account[index].getAccBalance());
			outFile.println();
			count++;
		} 
		if(count == 0) {
			outFile.println("Account Holder SSID: " + requestedAccount);
			outFile.println("Error: No Account With this SSID");
			outFile.println();
		}
		
		outFile.println();
		outFile.flush(); // flush the output buffer
	}
	
	/* Method pause() */
	public static void pause(Scanner keyboard) {
		String tempstr;
		System.out.println();
		System.out.print("press ENTER to continue");
		tempstr = keyboard.nextLine(); // flush previous ENTER
		tempstr = keyboard.nextLine(); // wait for ENTER
	}
}