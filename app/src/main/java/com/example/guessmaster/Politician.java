//Zeying Zhou 20116670
package com.example.guessmaster;

public class Politician extends Person{
	private String party;
	
	//constructor method accept five arguments to initialize the Person
	public Politician(String name, Date born, String gender, String party, double difficulty) {
		super(name, born, gender, difficulty);
		this.party = party;
	}
	
	//copy constructor
	public Politician(Politician politician) {
		super(politician);
		this.party = politician.party;
	}
	
	//clone method for politician
	public Politician clone() {
		return new Politician(this);
	}
	
	//toString method
	public String toString() {
		return super.toString() + "Party: " + party;
	}
	
	//entityType method for politician
	public String entityType() {
		return "This entity is a politician!";
	}
}
