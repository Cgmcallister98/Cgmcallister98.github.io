public class Name {
	private String first;
	private String last;
	
	public Name() {//constructor
		first = "";
		last = "";		
	}
	
	public Name(String firstName, String lastName) {
		first = firstName;
		last = lastName;
	}

	//Setters	
	public void setFirst(String fName) {
		first = fName;
	}
	
	public void setLast(String lName) {
		last = lName;
	}
	
	//Getters
	public String getFirst() {
		return first;
	}
	
	public String getLast() {
		return last;
	}
}
