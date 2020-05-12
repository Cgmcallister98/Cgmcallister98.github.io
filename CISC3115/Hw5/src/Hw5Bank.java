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
		return new Account(account.get(index));
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
	public int openNewAcct(int requestedAccount, String first, String last, 
		String ssId, String acctType, boolean status) {
		int index;
		Transaction trans = new Transaction();
		DateInfo today = new DateInfo();
		String str = "New Account";
		index = findAcct(requestedAccount);
		
		if(index > -1) {
			return -1;
		} else {
			if (acctType.equals("CD")){
			
			Name name = new Name(first, last);
			Depositor dpst = new Depositor(ssId, name);
			DateInfo date = new DateInfo();
			date.setMonth(date.getMonth()+6);
			Account newAcc = new Account(requestedAccount, acctType, status, 0.0, 
					dpst, date);
			addAccount(newAcc);
			
			trans.setTransType(str);
			trans.setIndicator(true);
			trans.setTransDate(today);
			getAccount(getNumAccounts()-1).addTransaction(trans);
			return 1;
			}	else {
			Name name = new Name(first, last);
			Depositor dpst = new Depositor(ssId, name);
			Account newAcc = new Account(requestedAccount, acctType, status, 0.0,
					dpst);
			addAccount(newAcc);
			trans.setTransType(str);
			trans.setIndicator(true);
			getAccount(getNumAccounts()-1).addTransaction(trans);
			return 1;
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
	public int deleteAcct(int requestedAccount) {
		int index;
		Transaction trans = new Transaction();
		String str = "New Account";
		
		index = findAcct(requestedAccount);
		
		if(index == -1)
			return -1;
		else if(getAccount(index).getBalance() > 0.0 ) {
			trans.setTransType(str);
			trans.setFailure(10);
			trans.setIndicator(false);
			getAccount(index).addTransaction(trans);
			return 0;
		}else {
		getAccount(index).setAcctNum(getAccount(account.size()-1).getAcctNum());
		getAccount(index).setAcctType(getAccount(account.size()-1).getAcctType());
		getAccount(index).setBalance(getAccount(account.size()-1).getBalance());
		getAccount(index).setDepositor(getAccount(account.size()-1).getDepositor());
		getAccount(index).setDateInfo(getAccount(account.size()-1).getDateInfo());
		getAccount(index).setStatus(getAccount(account.size()-1).getStatus());
		account.remove(account.size()-1);//might need to remove this
			return 1;	 
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
