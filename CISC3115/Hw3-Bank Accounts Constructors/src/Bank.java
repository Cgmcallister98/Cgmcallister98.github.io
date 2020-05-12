public class Bank {
	final int MAX_NUM = 50;
	
	
	private int numAccts;
	private Account[] account; 
	
	public Bank() { //No-arg constructor
		numAccts=0;
		account = new Account[MAX_NUM];
	}
	
	public Bank(int accts) {//1-arg Constructor
		numAccts = accts;
		account = new Account[MAX_NUM];
	}
	
	//Setters
	public void setNumAccts(int num) {
		numAccts = num;
	}
	
	public void addAccount (Account acc) {
		account[numAccts] = acc;
		numAccts++;//FIX THIS 
	}
	
	//Getters
	public int getNumAccts() {
		return numAccts;
	}
	
	public Account getAccount(int index) {
		return account[index];
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
	public int openNewAcct(int requestedAccount, String first, String last, String ssId, String acctType) {
		int index;
		index = findAcct(requestedAccount);
		
		if(index > -1) {
			return -1;
		} else {
			if (acctType.equals("CD")){
			Name name = new Name(first, last);
			Depositor dpst = new Depositor(ssId, name);
			DateInfo date = new DateInfo();
			date.setMonth(date.getMonth()+6);
			Account newAcc = new Account(requestedAccount, acctType, 0.0, dpst, date);
			addAccount(newAcc);
			return 1;
			}	else {
			Name name = new Name(first, last);
			Depositor dpst = new Depositor(ssId, name);
			Account newAcc = new Account(requestedAccount, acctType, 0.0, dpst);
			addAccount(newAcc);
			return 1;
		}
		}
	}
	
	/*Method: deleteAcct
	 * Input: requestedAccount - account number to delete
	 * Process: checks to see if account exists, if it doesnt exist returns 
	 * error identifier, then checks if account has non zero balance if so
	 * returns error identifier, else deletes account and returns success 
	 * identifier.
	 * Output: success or failure Identifier
	 */
	public int deleteAcct(int requestedAccount) {
		int index;
		
		index = findAcct(requestedAccount);
		
		if(index == -1)
			return -1;
		else if( getAccount(index).getBalance() > 0.0 )
			return 0;
		else {
			 getAccount(index).setAcctNum(getAccount(numAccts-1).getAcctNum());
			 getAccount(index).setAcctType(getAccount(numAccts-1).getAcctType());
			 getAccount(index).setBalance(getAccount(numAccts-1).getBalance());
			 getAccount(index).setDepositor(getAccount(numAccts-1).getDepositor());
			 getAccount(index).setDateInfo(getAccount(numAccts-1).getDateInfo());
			return 1;	 
		}
		
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
		for(int index=0; index < numAccts; index++)
			if(getAccount(index).getAcctNum() == requestedAccount)
				return index;
		return -1;				
	}
	 
}
