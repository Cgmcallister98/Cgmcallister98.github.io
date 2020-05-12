import java.util.ArrayList;

public class Account {
	private int acctNum;
	private String acctType;
	private boolean acctStatus;
	private double acctBalance;
	private ArrayList<Transaction> transaction;
	private Depositor depositor;


	
	public Account() {//no-arg constructor
		acctNum = 0;
		acctType = "";
		acctStatus = false;
		acctBalance = 0.0;
		transaction = new ArrayList<>();
		depositor = new Depositor();
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
	
	public Account(Account acct) {//COPY constructor for CD
		acctNum = acct.acctNum;
		acctType = acct.acctType;
		acctStatus = acct.acctStatus;
		acctBalance = acct.acctBalance;
		depositor = new Depositor(acct.depositor);
		transaction = acct.transaction;
	}
	
	
	
	/*
	 * Method: makeDeposit
	 * Input: depositAmount- amount to deposit into Acc
	 * Process: checks if depositAmount is valid, then if its not returns error
	 * identifier else returns success identifier and makes deposit. 
	 * Output: success or failure Identifier
	 */
	public String makeDeposit(double depositAmount, int newDate) {
	  return "not working";
	}
	
	public String makeDeposit(double depositAmount) {
		return "not working 2";
	}
	
	/*
	 * Method: makeWithdrawal
	 * Input: amountToWithdraw- amount to withdraw from Acc
	 * Process: checks if amountToWithdraw is valid, then if valid in context
	 * to the account, if its not returns error identifier else returns 
	 * success identifier and makes withdrawal. 
	 * Output: success or failure Identifier
	 */
	public String makeWithdrawal(double amountToWithdraw, int newDate) {
		return "not working";
	}
	
	public String makeWithdrawal(double amountToWithdraw) {
		return "not working2";
	}
	/*
	 * Method: clearCheck
	 * Input: checkToClear- amount to clear from Acc
	 * Process: checks if checkToClear is valid, then if valid in context
	 * to the account, if its not returns error identifier and deducts error 
	 * amount else returns success identifier and makes withdrawal.
	 * Output: success or failure Identifier 
	 */
	public String clearCheck(double checkToClear, DateInfo dateToClear) { 
		return "not working";
	}
	
	/*Method: closeAcct
	 * Input: status- reference to status of account
	 * Process: checks to see if status is true if so sets it to false then 
	 * returns true to signify closed account else returns false;
	 * Output: true or false depending on if account was closed or not
	 */
	public String closeAcct() {
		Transaction trans = new Transaction();
		DateInfo today = new DateInfo();
		
		if(acctStatus == true) {
			acctStatus = false;
			trans.setTransType("Close Account");
			trans.setTransDate(today);
			trans.setIndicator(true);
			addTransaction(trans);
			return "Transaction Requested: Close Account \n Account Number: " 
			+ getAcctNum() + "\n Account Closed";
		} else {
			trans.setTransType("Close Account");
			trans.setTransDate(today);
			trans.setIndicator(false);
			trans.setFailure(7);
			addTransaction(trans);
			return "Transaction Requested: Close Account \n" + getTransactions
					(getTransactionSize()-1).getFailure();
		}
	}
	
	/*Method: reOpenAcct
	 * Input: status- reference to status of account
	 * Process: checks to see if status is false if so sets it to true then 
	 * returns true to signify closed account else returns false;
	 * Output: true or false depending on if account was creOpened or not
	 */
	public String reopenAcct() {
		Transaction trans = new Transaction();
		DateInfo today = new DateInfo();
		if(acctStatus == false) {
			acctStatus = true;
			trans.setTransType("Reopen Account");
			trans.setTransDate(today);
			trans.setIndicator(true);
			addTransaction(trans);
			return "Transaction Requested: Reopen Account \n Account Number: " 
					+ getAcctNum() + "\n Account Reopened";
		} else {
			trans.setTransType("Reopen Account");
			trans.setTransDate(today);
			trans.setIndicator(false);
			trans.setFailure(8);
			addTransaction(trans);
			return "Transaction Requested: Reopen Account \n" + getTransactions
					(getTransactionSize()-1).getFailure();
		}
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
		return new Depositor(depositor);
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
	
	public String toString() {
		String accStatus;
		String str;
		if(acctStatus == true)
			accStatus = "Open";
		else accStatus = "Closed";
			str = String.format("%-12s %-12s %9s %8d %10s %9.2f %8s", 
					depositor.getName().getFirst(),
					depositor.getName().getLast(),
					depositor.getSSNumber(),
					acctNum,
					acctType, 
					acctBalance, 
					accStatus); 
		return str;
	}
	
	public String toStringAcctInfo() {
		String str;
		
		String accStatus;
		if(acctStatus == true)
			accStatus = "Open";
		else accStatus = "Closed";
		
			str = String.format("Account Holder: %s %s \n"
					+ "Account Holder SSID: %s \n"
					+ "Account Number: %d \n"
					+ "Account Type: %s \n"
					+ "Account Balance: %.2f \n"
					+ "Account Status: %s \n",
					depositor.getName().getFirst(),
					depositor.getName().getLast(),
					depositor.getSSNumber(),
					acctNum,
					acctType,
					acctBalance,
					accStatus);
		return str;
	}
	 
}
