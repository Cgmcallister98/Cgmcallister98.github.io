public class Transaction {
	private String transType;
	private double transAmount;
	private DateInfo transDate;
	private boolean indicator;
	private  String failure;
	
	public Transaction() {//no arg constructor
		transType = "";
		transAmount = 0.0;
		transDate = new DateInfo();
		indicator = false;
		failure = "Error: Account number does not exist";
	}
	//arg constructor
	public Transaction(String type, double amount, DateInfo date, boolean indicator,
			String fail) {
		transType = type;
		transAmount = amount;
		transDate = date;
		this.indicator = indicator;
		failure = fail;
	}
	
	public Transaction(Transaction trans) {//copy constructor
		transType = trans.transType;
		transAmount = trans.transAmount;
		transDate = new DateInfo(trans.transDate);
		indicator = trans.indicator;
		failure = trans.failure;	
	}
	
	//setters
	public void setTransType(String str) {
		transType = str;
	}
	
	public void setTransAmount (double amount) {
		transAmount = amount;
	}
	
	public void setTransDate(DateInfo date) {
		transDate = date;
	}
	
	public void setIndicator(boolean set) {
		indicator = set;
	}
	
	public void setFailure(int fail) {
		if(fail == 0) {
			failure = "Error: Account is closed";
		}else if(fail == 1) {
			failure = "Error: Maturity Date has not been reached";
		}else if(fail == 2) {
			failure = "Error: Invalid amount";
		}else if(fail == 3) {
			failure = "Error: Insufficient funds";
		}else if(fail == 4) {
			failure = "Error: Check Bounced";
		}else if(fail == 5) {
			failure = "Error: Check has past 6 Month clear Period";
		}else if(fail == 6) {
			failure = "Error: Not a Checking Account";
		}else if(fail == 7) {
			failure = "Error: Account already closed";
		}else if(fail == 8) {
			failure = "Error: Account already open";
		}else if(fail == 9) {
			failure = "Account already exists";
		}else if(fail == 10) {
			failure = "Error: Account has a non-zero balance";
		}
	}
		
	//getters
	public String getTransType() {
		return transType;
	}
	
	public double getTransAmount () {
		return transAmount;
	}
	
	public DateInfo getTransDate() {
		return transDate;
	}
	
	public boolean getIndicator() {
		return indicator;
	}
	
	public String getFailure(){
		return failure;
	}
	
	public String toString() {
		String str;
		if(indicator == false) {
			str = failure;
		}else if(transType.contentEquals("New Account")) {
		str = String.format("Transaction Type: %s \n"
				+ "Transaction Date: %s", transType, getTransDate());
		} else {
			str = String.format("Transaction Type: %s \n"
					+ "Transaction Amount: %.2f \n"
					+ "Transaction Date: %s", 
					transType,
					transAmount,
					getTransDate());
		}
		return str;
	}
}
