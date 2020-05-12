public class Account {
	private int acctNum;
	private String acctType;
	private double balance;
	private Depositor depositor;
	private DateInfo maturityDate;
	private DateInfo today = new DateInfo();
	
	 public Account(){//constructor
		 acctNum = 0;
		 acctType = "";
		 balance = 0.0;
		 depositor = new Depositor();
		 maturityDate = new DateInfo();
	 }
	//constructor-with date
	public Account(int accNum, String accType, double accBalance, Depositor myDepositor, DateInfo maturityDate) {
		 acctNum = accNum;
		 acctType = accType;
		 balance = accBalance;
		 depositor = myDepositor;
		 this.maturityDate = maturityDate; 
	}
	//constructor-no date
	public Account(int requestedAccount, String acctType, double balance, Depositor dpst) {
		 acctNum = requestedAccount;
		 this.acctType = acctType;
		 this.balance = balance;
		 depositor = dpst;
		 maturityDate = new DateInfo(); 
	}

	//Setters
	public void setAcctNum(int num) {
		acctNum = num;
	}
	
	public void setAcctType(String str) {
		acctType = str;
	}
	
	public void setBalance(double num) {
		balance = num;
	}
	
	public void setDepositor (Depositor dpstr) {
		depositor = dpstr;
	}
	
	public void setDateInfo(DateInfo date) {
		maturityDate = date;
	}
	
	//Getters 
	public int getAcctNum() {
		return acctNum;
	}
	
	public String getAcctType() {
		return acctType;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public Depositor getDepositor() {
		return depositor;
	}
	
	public DateInfo getDateInfo() {
		return maturityDate;
	}
	/*
	 * Method: makeDeposit
	 * Input: depositAmount- amount to deposit into Acc
	 * Process: checks if depositAmount is valid, then if its not returns error
	 * identifier else returns success identifier and makes deposit. 
	 * Output: success or failure Identifier
	 */
	public int makeDeposit(double depositAmount, int newDate) {
		if(acctType.contentEquals("CD")) {
			if(today.compareDate(maturityDate) == 0) {
				return -1;
			}else if(today.compareDate(maturityDate) == 1 && depositAmount <= 0) {
				return 0;
			}else if(today.compareDate(maturityDate) == 1 && depositAmount > 0) {
				if(newDate == 6) {
					DateInfo date = new DateInfo();
					
					date.setMonth(date.getMonth()+6);					
					maturityDate=date;
					balance += depositAmount;
					return 1;
				}
				if(newDate == 12) {
					DateInfo date = new DateInfo();
					
					date.setYear(date.getYear()+1);
					maturityDate=date;
					balance += depositAmount;
					return 1;
				}
				if(newDate == 18) {
					DateInfo date = new DateInfo();
					
					date.setMonth(date.getMonth()+6);
					date.setYear(date.getYear()+1);
					maturityDate=date;
					balance += depositAmount;
					return 1;
				}
				if(newDate == 24) {
					DateInfo date = new DateInfo();
					
					date.setYear(date.getYear()+2);
					maturityDate=date;
					balance += depositAmount;
					return 1;
				}
			}
		} else {
			if(depositAmount <= 0)
				return 0;
			else {
				balance += depositAmount;
				return 2;
			}
		}
		return 0;
	}
	
	/*
	 * Method: makeWithdrawal
	 * Input: amountToWithdraw- amount to withdraw from Acc
	 * Process: checks if amountToWithdraw is valid, then if valid in context
	 * to the account, if its not returns error identifier else returns 
	 * success identifier and makes withdrawal. 
	 * Output: success or failure Identifier
	 */
	public int makeWithdrawal(double amountToWithdraw, int newDate) {
		if(acctType.contentEquals("CD")) {
			if(today.compareDate(maturityDate) == 0) {
				return -1;
			}else if(today.compareDate(maturityDate) == 1 && amountToWithdraw <= 0) {
				return 0;
			}else if(today.compareDate(maturityDate) == 1 && amountToWithdraw > 0.0 && amountToWithdraw > balance) {
				return -2;
			}else {
				if(newDate == 6) {
					DateInfo date = new DateInfo();
					
					date.setMonth(date.getMonth()+6);					
					maturityDate=date;
					balance -= amountToWithdraw;
					return 1;
				}
				if(newDate == 12) {
					DateInfo date = new DateInfo();
					
					date.setYear(date.getYear()+1);
					maturityDate=date;
					balance -= amountToWithdraw;
					return 1;
				}
				if(newDate == 18) {
					DateInfo date = new DateInfo();
					
					date.setMonth(date.getMonth()+6);
					date.setYear(date.getYear()+1);
					maturityDate=date;
					balance -= amountToWithdraw;
					return 1;
				}
				if(newDate == 24) {
					DateInfo date = new DateInfo();
					
					date.setYear(date.getYear()+2);
					maturityDate=date;
					balance -= amountToWithdraw;
					return 1;
				}
			}
		}else {
			if(amountToWithdraw <= 0.0)
				return 0;
			else if(amountToWithdraw > 0.0 && amountToWithdraw > balance)
				return -1;
			else {
				balance -= amountToWithdraw;
				return 2;
			}
		}
		return 0;
	}
	/*
	 * Method: clearCheck
	 * Input: checkToClear- amount to clear from Acc
	 * Process: checks if checkToClear is valid, then if valid in context
	 * to the account, if its not returns error identifier and deducts error 
	 * amount else returns success identifier and makes withdrawal.
	 * Output: success or failure Identifier 
	 */
	public int clearCheck(double checkToClear, DateInfo dateToClear) {
		if(today.clearCheckDate(dateToClear) == 1) {
			if(getBalance() < checkToClear && getBalance() < 2.5 ) {
				balance -= balance;
				return 0;
			}
			else if (getBalance() < checkToClear) {
				balance -= 2.5;
				return -1;
			}
			else {
				balance -= checkToClear;
				return 1;
			}
		}else return -2;
	}  
	 
}
