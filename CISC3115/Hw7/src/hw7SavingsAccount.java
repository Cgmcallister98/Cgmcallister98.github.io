public class SavingsAccount extends Account {

	public SavingsAccount() {
		super();
	}
	
	public SavingsAccount(int acctNum, String acctType, boolean acctStatus, double acctBalance, Depositor depositor) {//parameter constructor
		super(acctNum, acctType, acctStatus, acctBalance, depositor);
	}
	
	public String makeDeposit(double depositAmount) {
		Transaction trans = new Transaction();
		String str = "Deposit";
		DateInfo today = new DateInfo();
		String result;
		if(getStatus() == true) {
			if(depositAmount <= 0.0) {//Invald amount
				trans.setTransType(str);
				trans.setIndicator(false);
				trans.setTransDate(today);
				trans.setFailure(2);
				addTransaction(trans);
				return result = "Transaction Requested: Deposit \n" + getTransactions(getTransactionSize()-1).getFailure() +
						depositAmount;
			}else {
				setBalance(getBalance() + depositAmount);
				Bank.addSavings(depositAmount);
				
				trans.setTransType(str);
				trans.setIndicator(true);
				trans.setTransDate(today);
				trans.setTransAmount(depositAmount);
				addTransaction(trans);
				return result = "Transaction Requested: Deposit \n Account Number: " + 
						getAcctNum() + "\n Balance: $" + (getBalance()-depositAmount) + " \n Amount to Deposit: $" + 
						depositAmount + " \n New Balance: $" + getBalance();
			}
		}else {
			trans.setTransType(str);//closed account
			trans.setIndicator(false);
			trans.setTransDate(today);
			trans.setFailure(0);
			addTransaction(trans);
			return result = "Transaction Requested: Deposit \n" + getTransactions(getTransactionSize()-1).getFailure();
		}
	}
	
	public String makeWithdrawal(double withdrawAmount) {
		DateInfo today = new DateInfo();
		Transaction trans = new Transaction();
		String str = "Withdrawal";
		String result;
		if(getStatus() == true) {
			if(withdrawAmount <= 0.0) {
				trans.setTransType(str);
				trans.setIndicator(false);
				trans.setTransDate(today);
				trans.setFailure(2);
				addTransaction(trans);
				// invalid amount to withdraw
				return result = "Transaction Requested: Withdrawal \n" + 
						getTransactions(getTransactionSize()-1).getFailure() +
							withdrawAmount;
			}else if(withdrawAmount > 0.0 && withdrawAmount > getBalance()) {
				trans.setTransType(str);//insufficient funds
				trans.setIndicator(false);
				trans.setTransDate(today);
				trans.setFailure(3);
				addTransaction(trans);
				return result = "Transaction Requested: Withdraw \n Account Number: " + getAcctNum() + "\n Balance: $" +
						getBalance() + "\n Amount to Withdraw: $" + withdrawAmount + getTransactions
						(getTransactionSize()-1).getFailure();
			}else {
				setBalance(getBalance() - withdrawAmount);
				Bank.subtractSavings(withdrawAmount);
				
				trans.setTransType(str);
				trans.setIndicator(true);
				trans.setTransDate(today);
				trans.setTransAmount(withdrawAmount);
				addTransaction(trans);
				return result = "Transaction Requested: Withdraw \n Account Number: " + 
						getAcctNum() + "\n Balance: $" + (getBalance()+withdrawAmount) + " \n Amount to Withdraw: $" + 
						withdrawAmount + " \n New Balance: $" + getBalance();
			}
		}
		trans.setTransType(str);//closed account
		trans.setIndicator(false);
		trans.setTransDate(today);
		trans.setFailure(0);
		addTransaction(trans);
		return result = "Transaction Requested: Withdrawal \n" + getTransactions(getTransactionSize()-1).getFailure();
	}
	
	public Account getAccount() {
		Account acct = new SavingsAccount(getAcctNum(), getAcctType(), getStatus(), 
				getBalance(), getDepositor());
		return acct;
	}
	
	public String toString() {
		String accStatus;
		String str;
		if(getStatus() == true)
			accStatus = "Open";
		else accStatus = "Closed";
			str = String.format("%-12s %-12s %9s %8d %10s %9.2f %8s", 
					getDepositor().getName().getFirst(),
					getDepositor().getName().getLast(),
					getDepositor().getSSNumber(),
					getAcctNum(),
					getAcctType(),
					getBalance(),
					accStatus);
		return str;
	}
	
	public String toStringAcctInfo() {
		String str;
			
		String accStatus;
		if(getStatus() == true)
			accStatus = "Open";
		else accStatus = "Closed";
			str = String.format("Account Holder: %s %s \n"
						+ "Account Holder SSID: %s \n"
						+ "Account Number: %d \n"
						+ "Account Type: %s \n"
						+ "Account Balance: %.2f \n"
						+ "Account Status: %s \n",
						getDepositor().getName().getFirst(),
						getDepositor().getName().getLast(),
						getDepositor().getSSNumber(),
						getAcctNum(),
						getAcctType(),
						getBalance(),
						accStatus);
			return str;
		}
}
