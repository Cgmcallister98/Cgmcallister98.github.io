/* Cassidy McAllister 
 * Homework 4 Array Lists
 */
import java.io.*;
import java.util.Scanner;

public class Hw5 {

	public static void main(String[] args) throws IOException {
		
		char choice; // menu item selected
		boolean notDone = true; // loop control flag

		Bank bank = new Bank();
		
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
		readAccts(bank);
		printAccts(bank, outFile);

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
				printAccts(bank, outFile);
				break;
			case 'b':
			case 'B':
				balance(bank, outFile, kybd);
				break;
			case 'd':
			case 'D':
				deposit(bank, outFile, kybd);
				break;
			case 'c':
			case 'C':
				clearCheck(bank, outFile, kybd);
				break;
			case 'w':
			case 'W':
				withdrawal(bank, outFile, kybd);
				break;
			case 'n':
			case 'N':
				newAcct(bank, outFile, kybd);
				break;
			case 'i':
			case 'I': 
				acctInfo(bank, outFile, kybd);
				break;
			case 'h':
			case 'H':
				acctHistory(bank, outFile, kybd);
				break;
			case 's':
			case 'S':
				closeAcct(bank, outFile, kybd);
				break;
			case 'r':
			case 'R':
				reopenAcct(bank, outFile, kybd);
				break;
			case 'x':
			case 'X':
				deleteAcct(bank, outFile, kybd);
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
		System.out.println("\t     C -- Clear Check");
		System.out.println("\t     N -- New Account");
		System.out.println("\t     B -- Balance Inquiry");
		System.out.println("\t     I -- Account Info");
		System.out.println("\t     H -- Acount Info and Transaction History");
		System.out.println("\t     S -- Close Account");
		System.out.println("\t     R -- Reopen an Account");
		System.out.println("\t     X -- Delete Account");
		System.out.println("\t     Q -- Quit");
		System.out.println();
		System.out.print("\tEnter your selection: ");
	}

	/*Method: readAccts
	 *Input: bank- reference to bank object
	 *Process: reads in input file then tokenizes the input file assigning it
	 *to the data members references then adds the account to the array list
	 *Output: null 
	 */
	public static void readAccts(Bank bank) throws IOException {
		int year, month, dayOfMonth;
		
		// open database input file
		// create File object
		File dbFile = new File("myinput.txt");

		// create Scanner object
		Scanner sc = new Scanner(dbFile);
		
		String line;
		
		while (sc.hasNext()) {
			
			line = sc.nextLine();
			String[] tokens = line.split(" ");
			//tokenize input and asign accordingly
			Name myName = new Name(tokens[0], tokens[1]);
			Depositor myDepositor = new Depositor(tokens[2], myName);
			month = Integer.parseInt(tokens[6]);
			dayOfMonth = Integer.parseInt(tokens[7]);
			year = Integer.parseInt(tokens[8]);
			DateInfo maturityDate = new DateInfo(month,dayOfMonth,year);
			Account myAccount = new Account(Integer.parseInt(tokens[3]), tokens[4],
					Boolean.parseBoolean(tokens[9]), Double.parseDouble(tokens[5]), 
					myDepositor, maturityDate);
			bank.addAccount(myAccount); //add element
			if(myAccount.getAcctType().equals("Savings"))
				Bank.addSavings(myAccount.getBalance());
			else if(myAccount.getAcctType().equals("Checking"))
				Bank.addChecking(myAccount.getBalance());
			else Bank.addCD(myAccount.getBalance());
		}
		sc.close(); //closes io
	}
	
	/*
	 * Method printAccts: 
	 * Input: bank - bank class containing array of accounts and their info 
	 * outFile - reference to the output file 
	 * Process: Prints the database of accounts 
	 * Output: Prints the database of accounts
	 */
	public static void printAccts(Bank bank, PrintWriter outFile) {
		
		Account myAccount;
		
		outFile.println();
		outFile.println("\t\tDatabase of Bank Accounts");
		outFile.println();
		outFile.println("First Name  Last Name \t    SSID      Account    Type     "
				+ " Balance  Status  Maturity Date");
		for (int index = 0; index < bank.getNumAccounts(); index++) {
			myAccount = bank.getAccount(index);
			outFile.println(myAccount);
		}
		outFile.println();
		outFile.printf("Total amount in Savings: %.2f", Bank.getAllSavings());
		outFile.println();
		outFile.printf("Total amount in Checking: %.2f", Bank.getAllChecking());
		outFile.println();
		outFile.printf("Total amount in CD: %.2f", Bank.getAllCD());
		outFile.println();
		outFile.printf("Total amount in the Bank: %.2f", Bank.getAll());
		outFile.println();
		outFile.println();
		for (int index = 0; index < bank.getNumAccounts(); index++) {
			outFile.println("Transaction History for " + bank.getAccount(index).getAcctNum());
			outFile.println();
			for (int i = 0; i < bank.getAccount(index).getTransactionSize();i++) {
				outFile.println(bank.getAccount(index).getTransactions(i).toString());
			}
			outFile.println();
		}
		
		outFile.println();	
		outFile.flush();
	}
	
	/*
	 * Method balance: 
	 * Input: bank - Bank class wih an array of accounts and their info 
	 * outFile - reference to output file 
	 * kybd - reference to the "test cases" input file
	 * Process: Prompts for the requested account Calls bank.findAcct() to see if the
	 * account exists If the account exists, the balance is printed 
	 * Otherwise, an error message is printed 
	 * Output: If the account exists, the balance is printed 
	 * Otherwise, an error message is printed
	 */
	public static void balance(Bank bank, PrintWriter outFile, Scanner kybd) {
		int requestedAccount;
		int index;
		
		System.out.println(); // prompt for account number
		System.out.print("Enter the account number: ");
		requestedAccount = kybd.nextInt(); // read-in the account number
		Transaction trans = new Transaction();
		
		index = bank.findAcct(requestedAccount);
		
		if (index == -1) // invalid account
		{
			outFile.println("Transaction Requested: Balance Inquiry");
			outFile.println(trans.getFailure());
		} else // valid account
		{
			outFile.println("Transaction Requested: Balance Inquiry");
			outFile.println("Account Number: " + requestedAccount);
			outFile.printf("Current Balance: $%.2f", 
					bank.getAccount(index).getBalance());
			outFile.println();
		}
		outFile.println();
		outFile.flush(); // flush the output buffer
	}

	/*
	 * Method deposit: 
	 * Input: bank - Bank class with an array of accounts and their info
	 * outFile - reference to the output file 
	 * kybd - reference to the "test cases" input file
	 * Process: Prompts for the requested account Calls findacct() to see if the
	 * account exists If the account exists, if it exists it checks if the 
	 * account is a CD or not, if a CD it then calls compareDate() to check the 
	 * maturity date and if it is on or after it prompts the user for a new 
	 * maturity date period and the amount to deposit, it then calls 
	 * makeDeposit() to check if the amount to deposit is valid,
	 * if not it prints an error, otherwise makes the deposit. 
	 * If not a CD then it asks for amount to deposit and then calls 
	 * makeDeposit() to check if amount is valid, if valid makes deposit if not
	 * prints an error. 
	 * Output: For a valid deposit, the deposit transaction is printed 
	 * Otherwise, an error message is printed
	 */
	public static void deposit(Bank bank, PrintWriter outFile, Scanner kybd) {
		int requestedAccount; 
		int index;
		double amountToDeposit;
		int newDate;
		DateInfo today = new DateInfo();
		DateInfo date = new DateInfo();
		Transaction trans = new Transaction();
		String str = "deposit";
			
		System.out.println(); // prompt for account number
		System.out.println("Enter the account number: ");
		requestedAccount = kybd.nextInt(); // read-in the account number
		System.out.println("Enter Ammount to Deposit: ");
		amountToDeposit = kybd.nextInt();
		System.out.println("Enter new Maturity Date period: ");
		newDate = kybd.nextInt();
		
		index = bank.findAcct(requestedAccount);
		
		
		
		if (index == -1) // invalid account
		{
			outFile.println("Transaction Requested: Deposit");
			outFile.println(trans.getFailure());
			outFile.println();
		}else {
			int dCheck = bank.getAccount(index).makeDeposit(amountToDeposit, newDate);
	
			if(dCheck == -2) {
			trans.setTransType(str);//closed account
			trans.setIndicator(false);
			trans.setTransDate(today);
			trans.setFailure(0);
			bank.getAccount(index).addTransaction(trans);
			outFile.println("Transaction Requested: Deposit");
			outFile.println(trans.getFailure());
		}else if(dCheck == -1) {
			trans.setTransType(str);//maturity date not reached
			trans.setIndicator(false);
			trans.setTransDate(today);
			trans.setFailure(1);
			bank.getAccount(index).addTransaction(trans);
			outFile.println("Transaction Requested: Deposit");
			outFile.println(trans.getFailure());
			outFile.printf("Maturity Date: %2d, %-2d, %-4d", 
					bank.getAccount(index).getDateInfo().getMonth()+1, 
					bank.getAccount(index).getDateInfo().getDayOfMonth(), 
					bank.getAccount(index).getDateInfo().getYear());
			outFile.println();
		}else if(dCheck == 0) {
			trans.setTransType(str);
			trans.setIndicator(false);
			trans.setTransDate(today);
			trans.setFailure(2);
			bank.getAccount(index).addTransaction(trans);
			// invalid amount to deposit
			outFile.println("Transaction Requested: Deposit");
			outFile.printf(trans.getFailure(), amountToDeposit);
			outFile.println();	
		}else if(dCheck == 1) {
			bank.getAccount(index).setDateInfo(date);
			trans.setTransType(str);
			trans.setIndicator(true);
			trans.setTransDate(today);
			trans.setTransAmount(amountToDeposit);
			bank.getAccount(index).addTransaction(trans);
			outFile.println();
			outFile.println("Transaction Requested: Deposit");
			outFile.println("Account Number: " + requestedAccount);
			outFile.printf("Balance: $%.2f", 
					bank.getAccount(index).getBalance()-amountToDeposit);
			outFile.println();
			outFile.println("Amount to Deposit: $" + amountToDeposit);
			outFile.printf("New Balance: $%.2f", 
					bank.getAccount(index).getBalance());
			outFile.println();
			outFile.printf("New Maturity Date: %2d, %-2d, %-4d", 
					bank.getAccount(index).getDateInfo().getMonth()+1,
					bank.getAccount(index).getDateInfo().getDayOfMonth(), 
					bank.getAccount(index).getDateInfo().getYear());
			outFile.println();
		}else if(dCheck == 2) {
			trans.setTransType(str);
			trans.setIndicator(true);
			trans.setTransDate(today);
			trans.setTransAmount(amountToDeposit);
			bank.getAccount(index).addTransaction(trans);
			outFile.println();
			outFile.println("Transaction Requested: Deposit");
			outFile.println("Account Number: " + requestedAccount);
			outFile.printf("Balance: $%.2f", 
					bank.getAccount(index).getBalance()-amountToDeposit);
			outFile.println();
			outFile.println("Amount to Deposit: $" + amountToDeposit);
			outFile.printf("New Balance: $%.2f", 
					bank.getAccount(index).getBalance());
			outFile.println();
		}
		}
		outFile.println();
		outFile.flush();
	}
	
	/*
	 * Method withdrawal: 
	 * Input: bank - Bank class with an array of accounts and their info
	 * outFile - reference to the output file 
	 * kybd - reference to the "test cases" input file
	 * Process: Prompts for the requested account Calls findacct() to see if the
	 * account exists If the account exists, if it exists it checks if the 
	 * account is a CD or not, if a CD it then calls compareDate() to check the 
	 * maturity date and if it is on or after it prompts the user for a new 
	 * maturity date period and the amount to withdraw, it then calls 
	 * makeWithdrawal() to check if the amount to withdraw is valid,
	 * if not it prints an error, otherwise makes the withdraw. 
	 * If not a CD then it asks for amount to withdraw and then calls 
	 * makeWithdrawal() to check if amount is valid, if valid makes the 
	 * withdrawal if not prints an error. 
	 * Output: For a valid withdrawal, the withdrawal transaction is printed 
	 * Otherwise, an error message is printed
	 */
	public static void withdrawal(Bank bank, PrintWriter outFile, Scanner kybd) {
		int requestedAccount;
		int index;
		double amountToWithdraw;
		int newDate;
		DateInfo today = new DateInfo();
		Transaction trans = new Transaction();
		String str = "Withdrawal";
		DateInfo date = new DateInfo();
		
		System.out.println();// prompt for account number
		System.out.print("Enter the account number: "); 
		requestedAccount = kybd.nextInt(); // read-in the account number
		System.out.println("Enter Ammount to Withdraw: ");
		amountToWithdraw = kybd.nextDouble();
		System.out.println("Enter new Maturity Date period: ");
		newDate = kybd.nextInt();

		// call findAcct to search if requestedAccount exists
		index = bank.findAcct(requestedAccount);
		

		
		if (index == -1) // invalid account
		{
			outFile.println();
			outFile.println("Transaction Requested: Withdrawal");
			outFile.println(trans.getFailure());
			outFile.println();
		}else {
			int wCheck = bank.getAccount(index).makeWithdrawal(amountToWithdraw, newDate);
			if(wCheck == -3) {
			trans.setTransType(str);//closed account
			trans.setIndicator(false);
			trans.setTransDate(today);
			trans.setFailure(0);
			bank.getAccount(index).addTransaction(trans);
			outFile.println("Transaction Requested: Withdraw");
			outFile.println(trans.getFailure());
		}else if(wCheck == -1) {
			trans.setTransType(str);//maturity date not reached
			trans.setIndicator(false);
			trans.setTransDate(today);
			trans.setFailure(1);
			bank.getAccount(index).addTransaction(trans);
			outFile.println("Transaction Requested: Withdraw");
			outFile.println(trans.getFailure());
			outFile.printf("Maturity Date: %2d, %-2d, %-4d", 
					bank.getAccount(index).getDateInfo().getMonth()+1, 
					bank.getAccount(index).getDateInfo().getDayOfMonth(), 
					bank.getAccount(index).getDateInfo().getYear());
			outFile.println();	
		}else if(wCheck == 0) {
			trans.setTransType(str);
			trans.setIndicator(false);
			trans.setTransDate(today);
			trans.setFailure(2);
			bank.getAccount(index).addTransaction(trans);
			// invalid amount to deposit
			outFile.println("Transaction Requested: Withdraw");
			outFile.println("Account Number: " + requestedAccount);
			outFile.printf("Balance: $%.2f",
					bank.getAccount(index).getBalance());
			outFile.println();
			outFile.println("Amount to Withdraw: $" + amountToWithdraw);
			outFile.printf(trans.getFailure(), amountToWithdraw);
			outFile.println();	
			outFile.println();
		}else if(wCheck == -2) {
			trans.setTransType(str);//insufficient funds
			trans.setIndicator(false);
			trans.setTransDate(today);
			trans.setFailure(3);
			bank.getAccount(index).addTransaction(trans);
			outFile.println("Transaction Requested: Withdraw");
			outFile.println("Account Number: " + requestedAccount);
			outFile.printf("Balance: $%.2f",
					bank.getAccount(index).getBalance());
			outFile.println();
			outFile.println("Amount to Withdraw: $" + amountToWithdraw);
			outFile.println(trans.getFailure());
	    }else if(wCheck == 1) {
	    	bank.getAccount(index).setDateInfo(date);
			trans.setTransType(str);
			trans.setIndicator(true);
			trans.setTransDate(today);
			trans.setTransAmount(amountToWithdraw);
			bank.getAccount(index).addTransaction(trans);
	    	outFile.println();
			outFile.println("Transaction Requested: Withdraw");
			outFile.println("Account Number: " + requestedAccount);
			outFile.printf("Balance: $%.2f", bank.getAccount(index).getBalance()+amountToWithdraw);
			outFile.println();
			outFile.println("Amount to Withdraw: $" + amountToWithdraw);
			outFile.printf("New Balance: $%.2f", 
					bank.getAccount(index).getBalance());
			outFile.println();
			outFile.printf("New Maturity Date: %2d, %-2d, %-4d",  
					bank.getAccount(index).getDateInfo().getMonth()+1,
					bank.getAccount(index).getDateInfo().getDayOfMonth(),
					bank.getAccount(index).getDateInfo().getYear());
			outFile.println();
		}else if(wCheck == 2) {
			trans.setTransType(str);
			trans.setIndicator(true);
			trans.setTransDate(today);
			trans.setTransAmount(amountToWithdraw);
			bank.getAccount(index).addTransaction(trans);
			outFile.println();
			outFile.println("Transaction Requested: Withdrawal");
			outFile.println("Account Number: " + requestedAccount);
			outFile.printf("Balance: $%.2f", bank.getAccount(index).getBalance()+amountToWithdraw);
			outFile.println();
			outFile.println("Amount to Withdraw: $" + amountToWithdraw);
			outFile.printf("New Balance: $%.2f", 
					bank.getAccount(index).getBalance());
			outFile.println();
		}
		}
		outFile.println();
		outFile.flush();
	}
	
	/*Method: clearCheck
	 * Input: bank- Bank object with array of accounts
	 * outFle- reference to output 
	 * kybd- reference to input 
	 * process: Asks for Acc #, if doesnt exists prints error and if its not a 
	 * checking account prints an error, if it does then asks for check amount 
	 * and date, if amount is less than balance then check bounces and fee is 
	 * deducted from account, if not check is cleared. Before clearing Account
	 *  also checks date of check, if 6 months have past since date on check 
	 *  error is printed, else check is cleared.
	 *  Output: Prints if check is cleared, if not prints error
	 */
	public static void clearCheck(Bank bank, PrintWriter outFile, Scanner kybd) {
		int requestedAccount;
		int index;
		double checkToClear;
		int month;
		int day;
		int year;
		DateInfo dateToClear;
		DateInfo today = new DateInfo();
		Transaction trans = new Transaction();
		String str = "Clear Check";
		
		System.out.println();// prompt for account number
		System.out.print("Enter the account number: "); 
		requestedAccount = kybd.nextInt(); // read-in the account number
		System.out.print("Enter Amount on Check: ");
		checkToClear = kybd.nextDouble();
		System.out.print("Enter Date on Check: ");
		month = kybd.nextInt();
		day = kybd.nextInt();
		year = kybd.nextInt();
		dateToClear = new DateInfo(month, day, year);

		// call findAcct to search if requestedAccount exists
		index = bank.findAcct(requestedAccount);
		

		
		if (index == -1) // invalid account
		{
			outFile.println();
			outFile.println("Transaction Requested: Clear Check");
			outFile.println(trans.getFailure());
			outFile.println();
		}else { 
			int cCheck = bank.getAccount(index).clearCheck(checkToClear, dateToClear);
			if(cCheck == -3) {
			trans.setTransType(str);
			trans.setIndicator(false);
			trans.setTransDate(today);
			trans.setFailure(6);
			bank.getAccount(index).addTransaction(trans);
			outFile.println("Transaction Requested: Clear Check");
			outFile.println(trans.getFailure());
		}else if(cCheck == -2) {
			trans.setTransType(str);
			trans.setIndicator(false);
			trans.setTransDate(today);
			trans.setFailure(0);
			bank.getAccount(index).addTransaction(trans);
			outFile.println("Transaction Requested: Clear Check");
			outFile.println(trans.getFailure());
		}else if(cCheck == 0) {
			trans.setTransType(str);
			trans.setIndicator(false);
			trans.setTransDate(today);
			trans.setFailure(4);
			bank.getAccount(index).addTransaction(trans);
			outFile.println();
			outFile.println("Transaction Requested: Clear Check");
			outFile.println("Account Number: " + requestedAccount);
			outFile.println("Check Amount: " + checkToClear);
			outFile.println(trans.getFailure());
			outFile.println("Remaining Account Balance Deducted");
			outFile.printf("Balance: $%.2f", 
					bank.getAccount(index).getBalance());
			outFile.println();
		}else if(cCheck == -1) {
			trans.setTransType(str);
			trans.setIndicator(false);
			trans.setTransDate(today);
			trans.setFailure(4);
			bank.getAccount(index).addTransaction(trans);
			outFile.println("Transaction Requested: Clear Check");
			outFile.println("Account Number: " + requestedAccount);
			outFile.println("Check Amount: " + checkToClear);
			outFile.println(trans.getFailure());
			outFile.println("$2.50 has been deducted from Account");
			outFile.printf("New Balance: $%.2f", bank.getAccount(index).getBalance());
			outFile.println();
		} else if(cCheck == 1){
			trans.setTransType(str);
			trans.setIndicator(true);
			trans.setTransDate(today);
			trans.setTransAmount(checkToClear);
			bank.getAccount(index).addTransaction(trans);
			outFile.println("Transaction Requested: Clear Check");
			outFile.println("Account Number: " + requestedAccount);
			outFile.println("Check Amount: " + checkToClear);
			outFile.println("Check Cleared");
			outFile.printf("New Balance: $%.2f", bank.getAccount(index).getBalance());
			outFile.println();
		}else if(cCheck == -2) {
			trans.setTransType(str);//check past 6 month period
			trans.setIndicator(false);
			trans.setTransDate(today);
			trans.setFailure(5);
			bank.getAccount(index).addTransaction(trans);
			outFile.println("Transaction Requested: Clear Check");
			outFile.println(trans.getFailure());
		}
		}
		outFile.println();
		outFile.flush(); // flush the output buffer
	}
		
	/* Method: accountInfo
	 * Input: bank- Bank class with an array of accounts
	 * outFile - printWriter reference 
	 * kybd- reference to "test cases"
	 * Process: Asks for an SSID number then does a linear search to find all 
	 * accounts with such SSID then prints them, if no accounts match the SSid 
	 * an error is printed
	 * Output: Prints the account info for the SSID requested, otherwise prints 
	 * an error.
	 */
	public static void acctInfo(Bank bank, PrintWriter outFile, Scanner kybd) {
		String requestedAccount;
		int count = 0;
		
		System.out.println("Enter SSID of Account Holder: ");
		requestedAccount = kybd.next();
		//CD account first
		for (int index = 0; index < bank.getNumAccounts(); index++)
		if (bank.getAccount(index).getDepositor().getSSNumber()
				.equals(requestedAccount)) {
			outFile.println("Transaction Requested: Account Info");
			outFile.println(bank.getAccount(index).toStringAcctInfo());
			count++;
		}
		if(count == 0) {//no accounts 
			outFile.println("Account Holder SSID: " + requestedAccount);
			outFile.println("Error: No Account With this SSID");
		}
		
		outFile.println();
		outFile.flush(); // flush the output buffer
	}
	/* Method: accountHistory
	 * Input: bank- Bank class with an array of accounts
	 * outFile - PrintWriter reference 
	 * kybd- reference to "test cases"
	 * Process: Asks for an SSID number then does a linear search to find all 
	 * accounts with such SSID then prints them, if no accounts match the SSid 
	 * an error is printed then prints out all transactios on the account valid or 
	 * not.
	 * Output:Prints the account info for the SSID requested and transaction history
	 * , otherwise prints an error.
	 */
	public static void acctHistory(Bank bank, PrintWriter outFile, Scanner kybd) {
		String requestedAccount;
		int count = 0;
		
		System.out.println("Enter SSID of Account Holder: ");
		requestedAccount = kybd.next();
		//CD account first
		for (int index = 0; index < bank.getNumAccounts(); index++) {
			if (bank.getAccount(index).getDepositor().getSSNumber().
				equals(requestedAccount)) {
				count++;
				outFile.println("Transaction Requested: Account Info with History");
				bank.getAccount(index).toStringAcctInfo();
				outFile.println("Transaction History: ");
			
			for (int i = 0; i < bank.getAccount(index).getTransactionSize();i++) {
						outFile.println();
						outFile.println(bank.getAccount(index).getTransactions(i).toString());
			}
			}
		}
		if(count == 0) {//no accounts found
			outFile.println("Account Holder SSID: " + requestedAccount);
			outFile.println("Error: No Account With this SSID");
		}
		
		outFile.println();
		outFile.flush(); // flush the output buffer
	}
	
	/*
	 * Method newAcct: 
	 * Input: bank - Bank class with an array of accounts and their info
	 * outFile - reference to the output file 
	 * kybd - reference to the "test cases" input file
	 * Process: Prompts for the requested account number Calls findacct() 
	 * to see if the account exists If the account doesn't exist, 
	 * the new account number is stored in the bank object's account array 
	 * and has a balance set to 0 as well as the other info, 
	 * if it does exist an error is printed. 
	 * Output: For a valid new account, the new account info is printed
	 * otherwise an error is printed.
	 */
	public static void newAcct(Bank bank, PrintWriter outFile, Scanner kybd) {
		int requestedAccount;
		String first;
		String last;
		String ssId;
		String acctType;
		boolean status = true;
		int index;
		String str = "New Account";
		DateInfo date = new DateInfo();
		Transaction trans = new Transaction();
		trans.setFailure(9);
		
		
		
		System.out.println();// prompt for the new account number
		System.out.print("Enter the new account number: "); 
		requestedAccount = kybd.nextInt();// read-in the account info
		System.out.print("Enter the First Name of new account holder: ");
		first = kybd.next();
		System.out.print("Enter the last Name of the new account holder: ");
		last = kybd.next();
	   System.out.print("Enter the Social Security Number of new account holder: ");
		ssId = kybd.next();
		System.out.print("Enter the account type for the new account: ");
		acctType = kybd.next();
				
		index = bank.openNewAcct(requestedAccount,first,last,ssId,acctType,status);
		
		if (index == -1) {//account already exists
			outFile.println("Transaction Requested: New Account");
			outFile.println(trans.getFailure());
			
		} else {//creating CD 
			if(bank.getAccount(bank.getNumAccounts()-1).getAcctType().equals("CD")){
			trans.setTransType(str);
			trans.setIndicator(true);
			trans.setTransDate(date);
			bank.getAccount(bank.getNumAccounts()-1).addTransaction(trans);
			outFile.println("Transaction Requested: New Account");
			outFile.println("New Account Created");
			outFile.println("New Account Holder: " + 
			bank.getAccount(bank.getNumAccounts()-1).getDepositor()
			.getName().getFirst() + " " + 
			bank.getAccount(bank.getNumAccounts()-1).getDepositor().getName().
			getLast());
			outFile.println("New Account Holder SSID: " + 
			bank.getAccount(bank.getNumAccounts()-1).getDepositor().getSSNumber());
			outFile.println("New Account Number: " + requestedAccount);
			outFile.println("New Account Type: " + 
			bank.getAccount(bank.getNumAccounts()-1).getAcctType());
			outFile.println("New Account Status: Open");
			outFile.printf("Maturity Date: %2d, %-2d, %-4d", 
			bank.getAccount(bank.getNumAccounts()-1).getDateInfo().getMonth()+1,
			bank.getAccount(bank.getNumAccounts()-1).getDateInfo().getDayOfMonth(), 
			bank.getAccount(bank.getNumAccounts()-1).getDateInfo().getYear());
			outFile.println();
			}else {//creat other accounts
			trans.setTransType(str);
			trans.setIndicator(true);
			trans.setTransDate(date);
			bank.getAccount(bank.getNumAccounts()-1).addTransaction(trans);
			outFile.println("Transaction Requested: New Account");
			outFile.println("New Account Created");
			outFile.println("New Account Holder: " + 
			bank.getAccount(bank.getNumAccounts()-1).getDepositor().getName()
			.getFirst()
			+ " " +bank.getAccount(bank.getNumAccounts()-1).getDepositor().getName()
			.getLast());
			outFile.println("New Account Holder SSID: " + 
			bank.getAccount(bank.getNumAccounts()-1).getDepositor().getSSNumber());
			outFile.println("New Account Number: " + requestedAccount);
			outFile.println("New Account Type: " + 
			bank.getAccount(bank.getNumAccounts()-1).getAcctType());
			outFile.println("New Account Status: Open");
				}
		}
		
		outFile.println();
		outFile.flush();			
	}
	
	/*
	 * Method deleteAcct: 
	 * Input: bank - Bank class with an array of accounts and their info 
	 * outFile - reference to the output file 
	 * kybd - reference to the "test cases" input file
	 * Process:Prompts for the requested account Calls findacct() to see if the
	 * account exists then it calls deleteAcct() If the account doesn't exist or 
	 * has a non zero balance an error is printed, otherwise the account is
	 * deleted.
	 * Output: a prompt the account is deleted or an error is printed
	 */ 
	static void deleteAcct(Bank bank, PrintWriter outFile, Scanner kybd) {
		int requestedAccount;
		int index;
			
		System.out.println();// prompt for future deleted account
		System.out.print("Enter the Account wanting to be Deleted: ");
		requestedAccount = kybd.nextInt(); // read-in the account to be deleted
		Transaction trans = new Transaction();
		
		index = bank.findAcct(requestedAccount);
		
		int deleted = bank.deleteAcct(requestedAccount);
		
		 if(deleted == -1) {//invalid account
			 outFile.println("Transaction Requested: Delete Account");
			 outFile.println(trans.getFailure());
		 }else if(deleted == 0) {//account has non zero balance
			trans.setFailure(10);
			outFile.println("Transaction Requested: Delete Account");
			outFile.println(trans.getFailure());
			outFile.println("Account Balance: " + 
			bank.getAccount(index).getBalance());
		 } else if(deleted == 1) {//balid deletion
			outFile.println("Transaction Requested: Delete Account");
			outFile.println("Account Deleted: " + requestedAccount);
		 }
		 outFile.println();
		 outFile.flush();
	}
	
	public static void closeAcct(Bank bank, PrintWriter outFile, Scanner kybd) {
		int requestedAccount;
		int index;
		boolean accStatus;
		Transaction trans = new Transaction();
		DateInfo date = new DateInfo();
		
		System.out.println();// prompt for future deleted account
		System.out.print("Enter the Account wanting to be Closed: ");
		requestedAccount = kybd.nextInt(); // read-in the account to be closed
		
		index = bank.findAcct(requestedAccount);
		
		if(index == -1) {//invalid account
			outFile.println("Transaction Requested: Close Account");
			outFile.println(trans.getFailure());
		}else {
			accStatus = bank.getAccount(index).closeAcct();
			if(accStatus == true) {//valid close
				trans.setTransType("Close Account");
				trans.setTransDate(date);
				trans.setIndicator(true);
				bank.getAccount(index).addTransaction(trans);
				outFile.println("Transaction Requested: Close Account");
				outFile.println("Account Number: " + requestedAccount);
				outFile.println("Account Closed");
			}else if(accStatus == false) {//account already closed
				trans.setTransType("Close Account");
				trans.setTransDate(date);
				trans.setIndicator(false);
				trans.setFailure(7);
				bank.getAccount(index).addTransaction(trans);
				outFile.println("Transaction Requested: Close Account");
				outFile.println(trans.getFailure());
			}
		}
		outFile.println();
		outFile.flush();
	}
	
	public static void reopenAcct(Bank bank, PrintWriter outFile, Scanner kybd) {
		int requestedAccount;
		int index;
		boolean accStatus;
		Transaction trans = new Transaction();
		DateInfo date = new DateInfo();
	
		System.out.println();// prompt for future deleted account
		System.out.print("Enter the Account wanting to be Reopened: ");
		requestedAccount = kybd.nextInt(); // read-in the account to be closed
	
		index = bank.findAcct(requestedAccount);
	
		if(index == -1) {//invalid account
			outFile.println("Transaction Requested: Reopen Account");
			outFile.println(trans.getFailure());
		}else {
		accStatus = bank.getAccount(index).reopenAcct();
			if(accStatus == true) {//valid reopen
				trans.setTransType("Reopen Account");
				trans.setTransDate(date);
				trans.setIndicator(true);
				bank.getAccount(index).addTransaction(trans);
				outFile.println("Transaction Requested: Reopen Account");
				outFile.println("Account Number: " + requestedAccount);
				outFile.println("Account Reopened");
			}else if(accStatus == false){//account already open
				trans.setTransType("Reopen Account");
				trans.setTransDate(date);
				trans.setIndicator(false);
				trans.setFailure(8);
				bank.getAccount(index).addTransaction(trans);
				outFile.println("Transaction Requested: Reopen Account");
				outFile.println(trans.getFailure());
			}
		}
		outFile.println();
		outFile.flush();
	}
}

