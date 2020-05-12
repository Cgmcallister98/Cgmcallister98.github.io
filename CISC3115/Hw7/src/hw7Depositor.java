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
	
	public Depositor(Depositor dpst) {//copy constructor
		SSNumber = dpst.SSNumber;
		name = new Name(dpst.name);
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
		return new Name(name);
	}
	
	public Depositor getDepositor() {
		Depositor dpst = new Depositor(SSNumber, name);
		return dpst;
	}
	
	public String toString() {
		String str = String.format("%s %s %s", name.getFirst(), name.getLast(), SSNumber);//might need to readjust format
		return str;
	}
	
	public boolean equals(Depositor dpst) {
		if(name.equals(dpst.getName()) && SSNumber.equals(dpst.getSSNumber())) 
			return true;
		else return false;
	}
	
}


