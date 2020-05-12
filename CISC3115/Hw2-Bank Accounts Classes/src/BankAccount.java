
public class BankAccount{
	private String first;
	private String last;
	private String ssNumber;
	private int accNumber;
	private String accType;
	private double accBalance;

	//setters for all inputed data 
	public void setFirst(String str) {
		first = str;
	}

	public void setLast(String str) {
		last = str;
	}

	public void setSSNumber(String str) {
		ssNumber = str;
	}

	public void setAccNumber(int d) {
		accNumber = d;
	}

	public void setAccType(String str) {
		accType = str;
	}

	public void setAccBalance(double num) {
		accBalance = num;
	}
	
	//getters for all inputed data
	public String getFirst() {
		return first;
	}

	public String getLast() {
		return last;
	}

	public String getSSNumber() {
		return ssNumber;
	}

	public int getAccNumber() {
		return accNumber;
	}

	public String getAccType() {
		return accType;
	}

	public double getAccBalance() {
		return accBalance;
	}
}
