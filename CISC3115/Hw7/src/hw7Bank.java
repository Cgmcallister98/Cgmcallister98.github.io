/*Cassidy McAllister
 * HW4 Array Lists
 */
import java.util.ArrayList;

public class Bank { //MISSING READ IN AMOUNTS
	
	private static double totalAmountinSavingsAccts=0;
	private static double totalAmountinCheckingAccts=0;
	private static double totalAmountinCDAccts=0;
	private static double totalAmountinAllAccts=0;
	private ArrayList<Account> account;
	
	//no-arg constructor
	public Bank() {
		account = new ArrayList<>();
	}
	/*Method: addAccount
	 * Input: newAcc-new account object
	 *Process: adds account to array list
	 *Output: null
	 */
	public void addAccount(Account newAcc) {
		account.add(newAcc);
	}
	//getters
	public Account getAccount(int index) {
		return account.get(index);//Check this
	}
	
	public int getNumAccounts() {
		return account.size();
	}
	
	/*
	 * Method findAcct: 
	 * Input: requestedAccount -account to find
	 * Process: Performs a linear search on the account array in the bank for
	 * the requested account 
	 * Output: If found, the index of the requested account is
	 * returned Otherwise, returns -1
	 */
	public int findAcct(int requestedAccount) { 
		for(int index=0; index < account.size(); index++)
			if(getAccount(index).getAcctNum() == requestedAccount)
				return index;
		return -1;				
	}
	
	
	/* Method: openNewAcct
	 * Input: 
	 * requestedAccount- new account number
	 * first- first name
	 * last- last name
	 * ssId- SSID number
	 * acctType- Account type
	 * Process: checks if account exists if so returns failure idenifier, if 
	 * DNE then checks if account is of Type CD then assigns variables and 
	 * creates new maturity date else creates non Cd account, both return 
	 * success indicator
	 * OutPut: success or failure Identifier
	 */
	public String openNewAcct(int requestedAccount, String first, String last, 
		String ssId, String acctType, boolean status) {
		int index;
		Transaction trans = new Transaction();
		DateInfo today = new DateInfo();
		String str = "New Account";
		index = findAcct(requestedAccount);
		
		if(index > -1) {
			return "Transaction Requested: New Account \n " + trans.getFailure();
		}else{
			Name name = new Name(first, last);
			Depositor dpst = new Depositor(ssId, name);
			DateInfo date = new DateInfo();
			date.setMonth(date.getMonth()+6);
			if (acctType.equals("CD")){
			CDAccount newAcc = new CDAccount(requestedAccount, acctType, status, 0.0, 
					dpst, date);
			addAccount(newAcc);
			
			trans.setTransType(str);
			trans.setIndicator(true);
			trans.setTransDate(today);
			getAccount(getNumAccounts()-1).addTransaction(trans);
			return "Transaction Requested: New Account \n New Account Created \n New Account Holder: "
			+ getAccount(getNumAccounts()-1).getDepositor().getName().getFirst() + " " + 
			getAccount(getNumAccounts()-1).getDepositor().getName().getLast() + 
			"\n New Account Holder SSID: " + getAccount(getNumAccounts()-1).getDepositor().getSSNumber() + 
			"\n New Account Number: " + requestedAccount + "\n New Account Type: " + 
			getAccount(getNumAccounts()-1).getAcctType() + 
			"New Account Status: Open \n Maturity Date: " + ((CDAccount) getAccount(getNumAccounts()-1)).getDateInfo().getMonth() +"/" +
			((CDAccount) getAccount(getNumAccounts()-1)).getDateInfo().getDayOfMonth() + "/" + 
			((CDAccount) getAccount(getNumAccounts()-1)).getDateInfo().getYear();
			}else if(acctType.equals("Savings")) {
			SavingsAccount newAcc = new SavingsAccount(requestedAccount, acctType, 
					status, 0.0, dpst);
			addAccount(newAcc);
			trans.setTransType(str);
			trans.setIndicator(true);
			getAccount(getNumAccounts()-1).addTransaction(trans);
			return "Transaction Requested: New Account \n New Account Created \n New Account Holder: "
					+ getAccount(getNumAccounts()-1).getDepositor().getName().getFirst() + " " + 
					getAccount(getNumAccounts()-1).getDepositor().getName().getLast() + 
					"\n New Account Holder SSID: " + getAccount(getNumAccounts()-1).getDepositor().getSSNumber() + 
					"\n New Account Number: " + requestedAccount + "\n New Account Type: " + 
					getAccount(getNumAccounts()-1).getAcctType() + 
					"New Account Status: Open";
			}else{
			CheckingAccount newAcc = new CheckingAccount(requestedAccount, acctType, 
					status, 0.0, dpst);
			addAccount(newAcc);
			trans.setTransType(str);
			trans.setIndicator(true);
			getAccount(getNumAccounts()-1).addTransaction(trans);
			return "Transaction Requested: New Account \n New Account Created \n New Account Holder: "
					+ getAccount(getNumAccounts()-1).getDepositor().getName().getFirst() + " " + 
					getAccount(getNumAccounts()-1).getDepositor().getName().getLast() + 
					"\n New Account Holder SSID: " + getAccount(getNumAccounts()-1).getDepositor().getSSNumber() + 
					"\n New Account Number: " + requestedAccount + "\n New Account Type: " + 
					getAccount(getNumAccounts()-1).getAcctType() + 
					"New Account Status: Open";
			}
		}
	}
	
	/*Method: deleteAcct
	 * Input: requestedAccount - account number to delete
	 * Process: checks to see if account exists, if it doesn't exist returns 
	 * error identifier, then checks if account has non zero balance if so
	 * returns error identifier, else deletes account and returns success 
	 * identifier.
	 * Output: success or failure Identifier
	 */
	public String deleteAcct(int requestedAccount) {
		int index;
		Transaction trans = new Transaction();
		String str = "Delete Account";
		
		index = findAcct(requestedAccount);
		
		if(index == -1)
			return "Transaction Requested: Delete Account \n Error: Account Does not Exist";
		else if(getAccount(index).getBalance() > 0.0 ) {
			trans.setTransType(str);
			trans.setFailure(10);
			trans.setIndicator(false);
			getAccount(index).addTransaction(trans);
			return "Transaction Requested: Delete Account" + getAccount(index).getTransactions
					(getAccount(index).getTransactionSize()-1).getFailure() + "\n Account Balance: " 
					+ getAccount(index).getBalance();
		}else {
		account.set(index, account.get(account.size()-1)) ;
		account.remove(account.size()-1);
			return "Transaction Requested: Delete Account \n Account Deleted: " + requestedAccount;	 
		}
	}
	
	public static void addSavings(double savings) {
		totalAmountinSavingsAccts += savings;
		totalAmountinAllAccts += totalAmountinSavingsAccts;
	}
	
	public static void subtractSavings(double savings) {
		totalAmountinSavingsAccts -= savings;
		totalAmountinAllAccts -= totalAmountinSavingsAccts;
	}
	
	public static void addChecking(double checking) {
		totalAmountinCheckingAccts += checking;
		totalAmountinAllAccts += totalAmountinCheckingAccts;
	}
	
	public static void subtractChecking(double checking) {
		totalAmountinCheckingAccts -= checking;
		totalAmountinAllAccts -= totalAmountinCheckingAccts;
	}
	
	public static void addCD(double CD) {
		totalAmountinCDAccts += CD;
		totalAmountinAllAccts += totalAmountinCDAccts;
	}
	
	public static void subtractCD(double CD) {
		totalAmountinCDAccts -= CD;
		totalAmountinAllAccts -= totalAmountinCDAccts;
	}
	
	public static double getAllSavings() {
		return totalAmountinSavingsAccts;
	}
	
	public static double getAllChecking() {
		return totalAmountinCheckingAccts;
	}
	
	public static double getAllCD() {
		return totalAmountinCDAccts;
	}
	
	public static double getAll() {
		return totalAmountinAllAccts;
	}
	
}
