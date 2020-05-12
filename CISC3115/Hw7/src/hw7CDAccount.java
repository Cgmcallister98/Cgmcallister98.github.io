
public class CDAccount extends Account {

	private DateInfo CDdate;
	
	public CDAccount() {
		super();
		CDdate = new DateInfo();
	}
	
	public CDAccount(int acctNum, String acctType, boolean acctStatus, double acctBalance, Depositor depositor, DateInfo date) {//parameter constructor
		super(acctNum, acctType, acctStatus, acctBalance, depositor);
		CDdate = date;
	}
	
	public String makeDeposit(double depositAmount, int newDate) {
		DateInfo today = new DateInfo();
		Transaction trans = new Transaction();
		String str = "Deposit";
		String result;
		if(getStatus() == true) {
			if(today.compareDate(CDdate) == 0) {
				trans.setTransType(str);//maturity date not reached
				trans.setIndicator(false);
				trans.setTransDate(today);
				trans.setFailure(1);
				addTransaction(trans);
				return result = "Transaction Requested: Deposit \n" + getTransactions
						(getTransactionSize()-1).getFailure() + " \n Maturity Date: " + 
						getDateInfo().getMonth() +"/" +
						getDateInfo().getDayOfMonth() + "/" + getDateInfo().getYear();
			}else if(today.compareDate(CDdate) == 1 && depositAmount <= 0) {
				trans.setTransType(str);
				trans.setIndicator(false);
				trans.setTransDate(today);
				trans.setFailure(2);
				addTransaction(trans);
					// invalid amount to deposit
				return result = "Transaction Requested: Deposit \n" + 
					getTransactions(getTransactionSize()-1).getFailure() +
						depositAmount;
			}else if(today.compareDate(CDdate) == 1 && depositAmount > 0) {
				if(newDate == 6) {
					DateInfo date = new DateInfo();
						
					CDdate.setMonth(CDdate.getMonth()+6);					
					setDateInfo(date);
					setBalance(getBalance() + depositAmount);
					Bank.addCD(depositAmount);
					
					trans.setTransType(str);
					trans.setIndicator(true);
					trans.setTransDate(today);
					trans.setTransAmount(depositAmount);
					addTransaction(trans);
					
					return result = "Transaction Requested: Deposit \n Account Number: " + 
					getAcctNum() + "\n Balance: " + (getBalance()-depositAmount) + " \n Amount to Deposit: $" + 
					depositAmount + " \n New Balance: " + getBalance() + " \n New Maturity Date: " + getDateInfo().getMonth() +"/" +
					getDateInfo().getDayOfMonth() + "/" + getDateInfo().getYear();
				}
				if(newDate == 12) {
					DateInfo date = new DateInfo();
						
					date.setYear(date.getYear()+1);
					setDateInfo(date);
					setBalance(getBalance() + depositAmount);
					Bank.addCD(depositAmount);
						
					trans.setTransType(str);
					trans.setIndicator(true);
					trans.setTransDate(today);
					trans.setTransAmount(depositAmount);
					addTransaction(trans);
					return result = "Transaction Requested: Deposit \n Account Number: " + 
							getAcctNum() + "\n Balance: " + (getBalance()-depositAmount) + " \n Amount to Deposit: $" + 
							depositAmount + " \n New Balance: " + getBalance() + " \n New Maturity Date: " + getDateInfo().getMonth() +"/" +
							getDateInfo().getDayOfMonth() + "/" + getDateInfo().getYear();
					}
				if(newDate == 18) {
					DateInfo date = new DateInfo();
					
					date.setMonth(date.getMonth()+6);
					date.setYear(date.getYear()+1);
					setDateInfo(date);
					setBalance(getBalance() + depositAmount);
					Bank.addCD(depositAmount);
					
					trans.setTransType(str);
					trans.setIndicator(true);
					trans.setTransDate(today);
					trans.setTransAmount(depositAmount);
					addTransaction(trans);
					return result = "Transaction Requested: Deposit \n Account Number: " + 
							getAcctNum() + "\n Balance: " + (getBalance()-depositAmount) + " \n Amount to Deposit: $" + 
							depositAmount + " \n New Balance: " + getBalance() + " \n New Maturity Date: " + getDateInfo().getMonth() +"/" +
							getDateInfo().getDayOfMonth() + "/" + getDateInfo().getYear();
				}
				if(newDate == 24) {
					DateInfo date = new DateInfo();
					
					date.setYear(date.getYear()+2);
					setDateInfo(date);
					setBalance(getBalance() + depositAmount);
					Bank.addCD(depositAmount);
						
					trans.setTransType(str);
					trans.setIndicator(true);
					trans.setTransDate(today);
					trans.setTransAmount(depositAmount);
					addTransaction(trans);
					return result = "Transaction Requested: Deposit \n Account Number: " + 
							getAcctNum() + "\n Balance: " + (getBalance()-depositAmount) + " \n Amount to Deposit: $" + 
							depositAmount + " \n New Balance: " + getBalance() + " \n New Maturity Date: " + getDateInfo().getMonth() +"/" +
							getDateInfo().getDayOfMonth() + "/" + getDateInfo().getYear();					
				}
			}
		}
		trans.setTransType(str);//closed account
		trans.setIndicator(false);
		trans.setTransDate(today);
		trans.setFailure(0);
		addTransaction(trans);
		return result = "Transaction Requested: Deposit \n" + getTransactions(getTransactionSize()-1).getFailure();
	}
	
	public String makeWithdrawal(double withdrawAmount, int newDate) {
		DateInfo today = new DateInfo();
		Transaction trans = new Transaction();
		String str = "Withdrawal";
		String result;
		if(getStatus()==true) {
			if(today.compareDate(getDateInfo()) == 0) {
				trans.setTransType(str);//maturity date not reached
				trans.setIndicator(false);
				trans.setTransDate(today);
				trans.setFailure(1);
				addTransaction(trans);
				return result = "Transaction Requested: Withdrawal \n" + getTransactions
						(getTransactionSize()-1).getFailure() + " \n Maturity Date: " + 
						getDateInfo().getMonth() +"/" +
						getDateInfo().getDayOfMonth() + "/" + getDateInfo().getYear();
			}else if(today.compareDate(getDateInfo()) == 1 && withdrawAmount <= 0) {
				trans.setTransType(str);
				trans.setIndicator(false);
				trans.setTransDate(today);
				trans.setFailure(2);
				addTransaction(trans);
				// invalid amount to withdraw
				return result = "Transaction Requested: Withdrawal \n" + 
						getTransactions(getTransactionSize()-1).getFailure() +
							withdrawAmount;
			}else if(today.compareDate(getDateInfo()) == 1 && withdrawAmount > 0.0 
					&& withdrawAmount > getBalance()) {
				trans.setTransType(str);//insufficient funds
				trans.setIndicator(false);
				trans.setTransDate(today);
				trans.setFailure(3);
				addTransaction(trans);
				return result = "Transaction Requested: Withdraw \n Account Number: " + getAcctNum() + "\n Balance: " +
						getBalance() + "\n Amount to Withdraw: $" + withdrawAmount + getTransactions
						(getTransactionSize()-1).getFailure();
			}else {
				if(newDate == 6) {
					DateInfo date = new DateInfo();
					
					date.setMonth(date.getMonth()+6);					
					setDateInfo(date);
					setBalance(getBalance() - withdrawAmount);
					Bank.subtractCD(withdrawAmount);
					
					trans.setTransType(str);
					trans.setIndicator(true);
					trans.setTransDate(today);
					trans.setTransAmount(withdrawAmount);
					addTransaction(trans);
					return result = "Transaction Requested: Withdraw \n Account Number: " + 
							getAcctNum() + "\n Balance: " + (getBalance()+withdrawAmount) + " \n Amount to Withdraw: $" + 
							withdrawAmount + " \n New Balance: " + getBalance() + " \n New Maturity Date: " + getDateInfo().getMonth() +"/" +
							getDateInfo().getDayOfMonth() + "/" + getDateInfo().getYear();
				}
				if(newDate == 12) {
					DateInfo date = new DateInfo();
					
					date.setYear(date.getYear()+1);
					setDateInfo(date);
					setBalance(getBalance() - withdrawAmount);
					Bank.subtractCD(withdrawAmount);
					
					trans.setTransType(str);
					trans.setIndicator(true);
					trans.setTransDate(today);
					trans.setTransAmount(withdrawAmount);
					addTransaction(trans);
					return result = "Transaction Requested: Withdraw \n Account Number: " + 
							getAcctNum() + "\n Balance: " + (getBalance()+withdrawAmount) + " \n Amount to Withdraw: $" + 
							withdrawAmount + " \n New Balance: " + getBalance() + " \n New Maturity Date: " + getDateInfo().getMonth() +"/" +
							getDateInfo().getDayOfMonth() + "/" + getDateInfo().getYear();
				}
				if(newDate == 18) {
					DateInfo date = new DateInfo();
					
					date.setMonth(date.getMonth()+6);
					date.setYear(date.getYear()+1);
					setDateInfo(date);
					setBalance(getBalance() - withdrawAmount);
					Bank.subtractCD(withdrawAmount);
					
					trans.setTransType(str);
					trans.setIndicator(true);
					trans.setTransDate(today);
					trans.setTransAmount(withdrawAmount);
					addTransaction(trans);
					return result = "Transaction Requested: Withdraw \n Account Number: " + 
							getAcctNum() + "\n Balance: " + (getBalance()+withdrawAmount) + " \n Amount to Withdraw: $" + 
							withdrawAmount + " \n New Balance: " + getBalance() + " \n New Maturity Date: " + getDateInfo().getMonth() +"/" +
							getDateInfo().getDayOfMonth() + "/" + getDateInfo().getYear();
				}
				if(newDate == 24) {
					DateInfo date = new DateInfo();
					
					date.setYear(date.getYear()+2);
					setDateInfo(date);
					setBalance(getBalance() - withdrawAmount);
					Bank.subtractCD(withdrawAmount);
					
					trans.setTransType(str);
					trans.setIndicator(true);
					trans.setTransDate(today);
					trans.setTransAmount(withdrawAmount);
					addTransaction(trans);
					return result = "Transaction Requested: Withdraw \n Account Number: " + 
							getAcctNum() + "\n Balance: " + (getBalance()+withdrawAmount) + " \n Amount to Withdraw: $" + 
							withdrawAmount + " \n New Balance: " + getBalance() + " \n New Maturity Date: " + getDateInfo().getMonth() +"/" +
							getDateInfo().getDayOfMonth() + "/" + getDateInfo().getYear();
				}
			}
		}
		trans.setTransType(str);//closed account
		trans.setIndicator(false);
		trans.setTransDate(today);
		trans.setFailure(0);
		addTransaction(trans);
		return result = "Transaction Requested: Withdrawal \n" + getTransactions(getTransactionSize()-1).getFailure();
	}
	
	public void setDateInfo(DateInfo date) {
		CDdate = date;
	}
	
	public DateInfo getDateInfo() {
		return CDdate;
	}
	
	public Account getAccount() {
		Account acct = new CDAccount(getAcctNum(), getAcctType(), getStatus(), 
				getBalance(), getDepositor(), getDateInfo());
		return acct;
	}
	
	public String toString() {
		String accStatus;
		String str;
		if(getStatus() == true)
			accStatus = "Open";
		else accStatus = "Closed";
		 str = String.format("%-12s %-12s %9s %8d %10s %9.2f %8s %4d/%1d/%2d", 
				getDepositor().getName().getFirst(),
				getDepositor().getName().getLast(),
				getDepositor().getSSNumber(),
				getAcctNum(),
				getAcctType(), 
				getBalance(), 
				accStatus, 
				CDdate.getMonth(),
				CDdate.getDayOfMonth(),
				CDdate.getYear());
		return str;
	}
	
	public String toStringAcctInfo() {
		String str;
		
		String accStatus;
		if(getStatus() == true)
			accStatus = "Open";
		else accStatus = "Closed";
		str = String.format("Account Holder: %s %s \n"
					+ "Account Holder SSID: %s \n "
					+ "Account Number: %d \n"
					+ "Account Type: %s \n"
					+ "Account Balance: %.2f \n"
					+ "Account Status: %s \n"
					+ "Maturity Date: %d/%d/%d",
					getDepositor().getName().getFirst(),
					getDepositor().getName().getLast(),
					getDepositor().getSSNumber(),
					getAcctNum(),
					getAcctType(), 
					getBalance(), 
					accStatus, 
					CDdate.getMonth(),
					CDdate.getDayOfMonth(),
					CDdate.getYear());
			return str;
		}
	
}

