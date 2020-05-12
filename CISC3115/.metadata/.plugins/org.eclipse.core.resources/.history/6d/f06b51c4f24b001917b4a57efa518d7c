import java.util.ArrayList;

public class Account {
	private int acctNum;
	private String acctType;
	private boolean acctStatus;
	private double acctBalance;
	private ArrayList<Transaction> transaction;
	private Depositor depositor;
	private DateInfo date;
	
	public Account() {//no-arg constructor
		acctNum = 0;
		acctType = "";
		acctStatus = false;
		acctBalance = 0.0;
		transaction = new ArrayList<>();
		depositor = new Depositor();
		date = new DateInfo();
	}
	
	//Arg Constructor w/ date
	public Account(int accNum, String accType, boolean accStatus, double balance, 
			Depositor dpst, DateInfo date ) {
		acctNum = accNum;
		acctType = accType;
		acctStatus = accStatus;
		acctBalance = balance;
		depositor = dpst;
		this.date = date;
		transaction = new ArrayList<>();
	}
	
	//Arg Constructor w/ no date
	public Account(int accNum, String accType, boolean accStatus, double balance,
			Depositor dpst) {
		acctNum = accNum;
		acctType = accType;
		acctStatus = accStatus;
		acctBalance = balance;
		depositor = dpst;
		transaction = new ArrayList<>();
	}
	
	/*
	 * Method: makeDeposit
	 * Input: depositAmount- amount to deposit into Acc
	 * Process: checks if depositAmount is valid, then if its not returns error
	 * identifier else returns success identifier and makes deposit. 
	 * Output: success or failure Identifier
	 */
	public int makeDeposit(double depositAmount) {
	  if(acctStatus == true) {
		if(depositAmount <= 0)
			return 0;
		else {
			acctBalance += depositAmount;
			return 1;
		}
	  } else return -1;
	}
	
	/*
	 * Method: makeWithdrawal
	 * Input: amountToWithdraw- amount to withdraw from Acc
	 * Process: checks if amountToWithdraw is valid, then if valid in context
	 * to the account, if its not returns error identifier else returns 
	 * success identifier and makes withdrawal. 
	 * Output: success or failure Identifier
	 */
	public int makeWithdrawal(double amountToWithdraw) {
	  if(acctStatus == true) {
		if(amountToWithdraw <= 0.0)
			return 0;
		else if(amountToWithdraw > 0.0 && amountToWithdraw > acctBalance)
			return -1;
		else {
			acctBalance -= amountToWithdraw;
			return 1;
		}
	  } else return -2;
	}
	
	/*
	 * Method: clearCheck
	 * Input: checkToClear- amount to clear from Acc
	 * Process: checks if checkToClear is valid, then if valid in context
	 * to the account, if its not returns error identifier and deducts error 
	 * amount else returns success identifier and makes withdrawal.
	 * Output: success or failure Identifier 
	 */
	public int clearCheck(double checkToClear) {
	  if(acctStatus == true) {
		if(getBalance() < checkToClear && getBalance() < 2.5 ) {
			acctBalance -= acctBalance;
			return 0;
		}
		else if (getBalance() < checkToClear) {
			acctBalance -= 2.5;
			return -1;
		}
		else {
			acctBalance -= checkToClear;
			return 1;
		}
	  }else return -2;
	}
	
	/*Method: closeAcct
	 * Input: status- reference to status of account
	 * Process: checks to see if status is true if so sets it to false then 
	 * returns true to signify closed account else returns false;
	 * Output: true or false depending on if account was closed or not
	 */
	public boolean closeAcct() {
		if(acctStatus == true) {
			acctStatus = false;
			return true;
		} else return false;
	}
	
	/*Method: reOpenAcct
	 * Input: status- reference to status of account
	 * Process: checks to see if status is false if so sets it to true then 
	 * returns true to signify closed account else returns false;
	 * Output: true or false depending on if account was creOpened or not
	 */
	public boolean reopenAcct() {
		if(acctStatus == false) {
			acctStatus = true;
			return true;
		} else return false;
	}
	
	//Setters
	public void setAcctNum(int num) {
		acctNum = num;
	}
		
	public void setAcctType(String str) {
		acctType = str;
	}
		
	public void setBalance(double num) {
		acctBalance = num;
	}
		
	public void setDepositor (Depositor dpstr) {
		depositor = dpstr;
	}
		
	public void setDateInfo(DateInfo date) {
		this.date = date;
	}
	
	public void addTransaction(Transaction newTrans) {
		transaction.add(newTrans);
	}
	
	public void setStatus(boolean status) {
		acctStatus = status;
	}
		
		//Getters 
	public int getAcctNum() {
		return acctNum;
	}
		
	public String getAcctType() {
		return acctType;
	}
		
	public double getBalance() {
		return acctBalance;
	}
		
	public Depositor getDepositor() {
		return depositor;
	}
		
	public DateInfo getDateInfo() {
		return date;
	}
	
	public Transaction getTransactions(int index){
		return transaction.get(index);
	}
	
	public boolean getStatus() {
		return acctStatus;
	}
	
	public int getTransactionSize() {
		return transaction.size();
	}
}
