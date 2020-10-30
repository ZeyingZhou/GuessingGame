//Zeying Zhou 20116670
package com.example.guessmaster;

public class Person extends Entity{
	private String gender;
	
	//constructor method accept four arguements to initialize the Person
	public Person(String name, Date born, String gender, double difficulty) {
		super(name, born, difficulty);
		this.gender = gender;
	}
	
	//copy constructor
	public Person(Person person) {
		super(person);
		this.gender = person.gender;
	}
	
	//clone method for person
	public Person clone() {
		return new Person(this);
	}
	
	//toString method 
	public String toString() {
		return super.toString() + "Gender: " + gender + "\n";
	}
	
	//entityType method for person
	public String entityType() {
		return "This entity is a Person!";
	}
	
}
