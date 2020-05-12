public class Depositor {
	private String SSNumber;
	private Name name;
	
	public Depositor(){//constructor
		SSNumber="";
		name = new Name();
	}
	public Depositor(String SSNum, Name newName) {
		SSNumber = SSNum;
		name = newName;
	}
	
	//Setters
	public void setSSNumber(String str) {
		SSNumber = str;
	}
	
	public void setName(Name nme) {
		name = nme;
	}
	
	//Getters
	public String getSSNumber() {
		return SSNumber;
	}
	
	public Name getName() {
		return name;
	}
}

