import java.io.*;
import java.util.Scanner;

public class Hw1_Bank_Accounts {

	public static void main(String[] args) throws IOException {
		// constant definitions
		final int MAX_NUM = 50;

		// variable declarations
		int[] acctNumArray = new int[MAX_NUM]; // array of account numbers
		double[] balanceArray = new double[MAX_NUM]; // array of balances
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
		numAccts = readAccts(acctNumArray, balanceArray, MAX_NUM);
		printAccts(acctNumArray, balanceArray, numAccts, outFile);

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
				printAccts(acctNumArray, balanceArray, numAccts, outFile);
				break;
			case 'b':
			case 'B':
				balance(acctNumArray, balanceArray, numAccts, outFile, kybd);
				break;
			case 'd':
			case 'D':
				deposit(acctNumArray, balanceArray, numAccts, outFile, kybd);
				break;
			case 'w':
			case 'W':
				withdrawal(acctNumArray, balanceArray, numAccts, outFile, kybd);
				break;
			case 'n':
			case 'N':
				numAccts = newAcct(acctNumArray, balanceArray, numAccts, outFile, kybd);
				break;
			case 'x':
			case 'X':
				numAccts = deleteAcct(acctNumArray, balanceArray, numAccts, outFile, kybd);
				break;
			default:
				outFile.println("Error: " + choice + " is an invalid selection -  try again");
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
	 * Method readAccts() Input: acctNumArray - reference to array of account
	 * numbers balanceArray - reference to array of account balances maxAccts -
	 * maximum number of active accounts allowed Process: Reads the initial database
	 * of accounts and balances Output: Fills in the initial account and balance
	 * arrays and returns the number of active accounts
	 */
	public static int readAccts(int[] acctNumArray, double[] balanceArray, int maxAccts) throws IOException {
		// open database input file
		// create File object
		File dbFile = new File("myinput.txt");

		// create Scanner object
		Scanner sc = new Scanner(dbFile);

		int count = 0; // account number counter

		while (sc.hasNext() && count < maxAccts) {
			acctNumArray[count] = sc.nextInt();
			balanceArray[count] = sc.nextDouble();
			count++;
		}

		// close the input file
		sc.close();

		// return the account number count
		return count;
	}

	/*
	 * Method printAccts: Input: acctNumArray - array of account numbers
	 * balanceArray - array of account balances numAccts - number of active accounts
	 * outFile - reference to the output file Process: Prints the database of
	 * accounts and balances Output: Prints the database of accounts and balances
	 */
	public static void printAccts(int[] acctNumArray, double[] balanceArray, int numAccts, PrintWriter outFile) {
		outFile.println();
		outFile.println("\t\tDatabase of Bank Accounts");
		outFile.println();
		outFile.println("Account   Balance");
		for (int index = 0; index < numAccts; index++) {
			outFile.printf("%7d  $%7.2f", acctNumArray[index], balanceArray[index]);
			outFile.println();
		}
		outFile.println();

		// flush the output file
		outFile.flush();
	}

	/*
	 * Method menu() Input: none Process: Prints the menu of transaction choices
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
		System.out.println("\t     X -- Delete Account");
		System.out.println("\t     Q -- Quit");
		System.out.println();
		System.out.print("\tEnter your selection: ");
	}

	/*
	 * Method findAcct: Input: acctNumArray - array of account numbers numAccts -
	 * number of active accounts requestedAccount - requested account
	 * requested_number Process: Performs a linear search on the acctNunArray for
	 * the requested account Output: If found, the index of the requested account is
	 * returned Otherwise, returns -1
	 */
	public static int findAcct(int[] acctNumArray, int numAccts, int requestedAccount) {
		for (int index = 0; index < numAccts; index++)
			if (acctNumArray[index] == requestedAccount)
				return index;
		return -1;
	}

	/*
	 * Method balance: Input: acctNumArray - array of account numbers balanceArray -
	 * array of account balances numAccts - number of active accounts outFile -
	 * reference to output file kybd - reference to the "test cases" input file
	 * Process: Prompts for the requested account Calls findAcct() to see if the
	 * account exists If the account exists, the balance is printed Otherwise, an
	 * error message is printed Output: If the account exists, the balance is
	 * printed Otherwise, an error message is printed
	 */
	public static void balance(int[] acctNumArray, double[] balanceArray, int numAccts, PrintWriter outFile,
			Scanner kybd) {
		int requestedAccount;
		int index;

		System.out.println();
		System.out.print("Enter the account number: "); // prompt for account number
		requestedAccount = kybd.nextInt(); // read-in the account number

		// call findAcct to search if requestedAccount exists
		index = findAcct(acctNumArray, numAccts, requestedAccount);

		if (index == -1) // invalid account
		{
			outFile.println("Transaction Requested: Balance Inquiry");
			outFile.println("Error: Account number " + requestedAccount + " does not exist");
		} else // valid zccount
		{
			outFile.println("Transaction Requested: Balance Inquiry");
			outFile.println("Account Number: " + requestedAccount);
			outFile.printf("Current Balance: $%.2f", balanceArray[index]);
			outFile.println();
		}
		outFile.println();

		outFile.flush(); // flush the output buffer
	}

	/*
	 * Method deposit: Input: acctNumArray - array of account numbers balanceArray -
	 * array of account balances numAccts - number of active accounts outFile -
	 * reference to the output file kybd - reference to the "test cases" input file
	 * Process: Prompts for the requested account Calls findacct() to see if the
	 * account exists If the account exists, prompts for the amount to deposit If
	 * the amount is valid, it makes the deposit and prints the new balance
	 * Otherwise, an error message is printed Output: For a valid deposit, the
	 * deposit transaction is printed Otherwise, an error message is printed
	 */
	public static void deposit(int[] acctNumArray, double[] balanceArray, int numAccts, PrintWriter outFile,
			Scanner kybd) {
		int requestedAccount;
		int index;
		double amountToDeposit;

		System.out.println();
		System.out.print("Enter the account number: "); // prompt for account number
		requestedAccount = kybd.nextInt(); // read-in the account number

		// call findAcct to search if requestedAccount exists
		index = findAcct(acctNumArray, numAccts, requestedAccount);

		if (index == -1) // invalid account
		{
			outFile.println("Transaction Requested: Deposit");
			outFile.println("Error: Account number " + requestedAccount + " does not exist");
		} else // valid zccount
		{
			System.out.print("Enter amount to deposit: "); // prompt for amount to deposit
			amountToDeposit = kybd.nextDouble(); // read-in the amount to deposit

			if (amountToDeposit <= 0.00) {
				// invalid amount to deposit
				outFile.println("Transaction Requested: Deposit");
				outFile.println("Account Number: " + requestedAccount);
				outFile.println("Amount to Deposit: $" + amountToDeposit);
				outFile.printf("Error: $%.2f is an invalid amount", amountToDeposit);
				outFile.println();
			} else {
				outFile.println("Transaction Requested: Deposit");
				outFile.println("Account Number: " + requestedAccount);
				outFile.printf("Old Balance: $%.2f", balanceArray[index]);
				outFile.println();
				outFile.println("Amount to Deposit: $" + amountToDeposit);
				balanceArray[index] += amountToDeposit; // make the deposit
				outFile.printf("New Balance: $%.2f", balanceArray[index]);
				outFile.println();
			}
		}
		outFile.println();

		outFile.flush(); // flush the output buffer
	}

	/*
	 * Method withdrawal: Input: acctNumArray - array of account numbers balanceArray -
	 * array of account balances numAccts - number of active accounts outFile -
	 * reference to the output file kybd - reference to the "test cases" input file
	 * Process: Prompts for the requested account Calls findacct() to see if the
	 * account exists If the account exists, prompts for the amount to withdraw If
	 * the amount is valid, it makes the withdraw and prints the new balance
	 * Otherwise, an error message is printed for either insufficient funds or invalid withdraw amount Output: For a valid withdraw, the
	 * withdraw transaction is printed Otherwise, an error message is printed
	 */
	public static void withdrawal(int[] acctNumArray, double[] balanceArray, int numAccts, PrintWriter outFile,
			Scanner kybd) {
		int requestedAccount;
		int index;
		double amountToWithdraw;

		System.out.println();
		System.out.print("Enter the account number: "); // prompt for account number
		requestedAccount = kybd.nextInt(); // read-in the account number

		// call findAcct to search if requestedAccount exists
		index = findAcct(acctNumArray, numAccts, requestedAccount);

		if (index == -1) // invalid account
		{
			outFile.println("Transaction Requested: Withdrawal");
			outFile.println("Error: Account number " + requestedAccount + " does not exist");
		} else // valid account
		{
			System.out.print("Enter amount to Withdraw: "); // prompt for amount to Withdraw
			amountToWithdraw = kybd.nextDouble(); // read-in the amount to Withdraw

			if (amountToWithdraw <= 0.00) {
				// invalid amount to Withdraw
				outFile.println("Transaction Requested: Withdrawal");
				outFile.println("Account Number: " + requestedAccount);
				outFile.println("Amount to Withdraw: $" + amountToWithdraw);
				outFile.printf("Error: $%.2f is an invalid amount", amountToWithdraw);
				outFile.println();
			} else {

				if (amountToWithdraw > 0.00 && amountToWithdraw > balanceArray[index]) {
					// insuffiecent funds error
					outFile.println("Transaction Requested: Withdrawal");
					outFile.println("Account Number: " + requestedAccount);
					outFile.println("Amount to Withdraw: $" + amountToWithdraw);
					outFile.printf("Error: Insuffiecnt funds", balanceArray[index]);
					outFile.println();
				}

				else {
					//Working Withdrawl
					outFile.println("Transaction Requested: Withdrawal");
					outFile.println("Account Number: " + requestedAccount);
					outFile.printf("Old Balance: $%.2f", balanceArray[index]);
					outFile.println();
					outFile.println("Amount to Withdraw: $" + amountToWithdraw);
					balanceArray[index] -= amountToWithdraw; // make the Withdraw
					outFile.printf("New Balance: $%.2f", balanceArray[index]);
					outFile.println();
				}
			}
		}
		outFile.println();

		outFile.flush(); // flush the output buffer
	}
	/*
	 * Method newAcct: Input: acctNumArray - array of account numbers balanceArray -
	 * array of account balances numAccts - number of active accounts outFile -
	 * reference to the output file kybd - reference to the "test cases" input file
	 * Process: Prompts for the requested account number Calls findacct() to see if the
	 * account exists If the account doesn't exist, the new account number is stored in the array 
	 * and has a balance set to 0, if it does exist an error is printed. Output: For a valid new account, the
	 * new account info is printed and num accts is returned.
	 */
	public static int newAcct(int[] acctNumArray, double[] balanceArray, int numAccts, PrintWriter outFile,
			Scanner kybd) {
		int requestedAccount;
		int index;

		System.out.println();
		System.out.print("Enter the new account number: "); // prompt for the new account number
		requestedAccount = kybd.nextInt(); // read-in the account number

		index = findAcct(acctNumArray, numAccts, requestedAccount);

		if (index > -1) { //already existing account error finder
			outFile.println("Transaction Requested: New Account");
			outFile.println("Error: Account number " + requestedAccount + " already exists");
		} else { //Creation of new account
			acctNumArray[numAccts] = requestedAccount;
			balanceArray[numAccts] = 0.0;
			outFile.println("Transaction Requested: New Account");
			outFile.println("New Account Created");
			outFile.println("New Account Number: " + requestedAccount);
			numAccts++;
		}
		outFile.println();
		outFile.flush(); // flush the output buffer

		return numAccts;

	}

	/*
	 * Method deleteAcct: Input: acctNumArray - array of account numbers balanceArray -
	 * array of account balances numAccts - number of active accounts outFile -
	 * reference to the output file kybd - reference to the "test cases" input file
	 * Process:Prompts for the requested account Calls findacct() to see if the
	 * account exists If the account doesn't exist an error is printed, otherwise the balance of the 
	 * requested account is then checked if the balance is not 0 an error is printed, otherwise
	 * the account is deleted as well as the elements in the array are moved to close the gap.
	 *  Output: a prompt the account is deleted and the num of accts is returned
	 */
	public static int deleteAcct(int[] acctNumArray, double[] balanceArray, int numAccts, PrintWriter outFile,
			Scanner kybd) {
		int requestedAccount;
		int index;

		System.out.println();
		System.out.print("Enter the Account wanting to be Deleted: ");// prompt for future deleted account
		requestedAccount = kybd.nextInt(); // read-in the account to be deleted

		index = findAcct(acctNumArray, numAccts, requestedAccount);

		if (index == -1) { // Account DNE error
			outFile.println("Transaction Requested: Delete Account");
			outFile.println("Error: Account " + requestedAccount + " does not exist");
		} else {
			if (balanceArray[index] > 0.0) {//Account exists but non-zero balance error
				outFile.println("Transaction Requested: Delete Account");
				outFile.println("Error: Account " + requestedAccount + " has a non-zero balance");
			} else {//actual deletion of account
				int deleteAccount = index;
				for (int i = deleteAccount; i < acctNumArray.length - 1; i++) {//closes gap in array 
					acctNumArray[i] = acctNumArray[i + 1];
					balanceArray[i] = balanceArray[i + 1];
				}
				outFile.println("Transaction Requested: Delete Account");
				outFile.println("Account Deleted: " + requestedAccount);
				numAccts--;
			}
		}
		outFile.println();
		outFile.flush(); // flush the output buffer
		return numAccts;
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