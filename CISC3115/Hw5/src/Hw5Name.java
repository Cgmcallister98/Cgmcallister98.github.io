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
	
	public Name(Name myName) {//copy constructor
		first = myName.first;
		last = myName.last;
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
	
	public Name getName() {
		Name name = new Name(first, last);
		return name;
	}
	
	public String toString() {
		String str = String.format("%s %s", first, last);
		return str;
	}
	
	public boolean equals(Name myName) {
		if(first.equals(myName.getFirst())&& last.equals(myName.getLast()))
				return true;
		else return false;
	}
}