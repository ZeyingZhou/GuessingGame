//Zeying Zhou 20116670
package com.example.guessmaster;

public class Country extends Entity{
	private String capital; //captical attribute
	
	//constructor method accept four arguments to initialize the Country
	public Country(String name, Date born, String capital, double difficulty) {
		super(name, born, difficulty); //initialize the instance variables from the base class
	    this.capital = capital;
	}
	
	//copy constructor 
	public Country(Country country) {
		super(country);
		this.capital = country.capital;
	}
	
	//clone method country
	public Country clone() {
		return new Country(this);
	}
	
	//toString method
	public String toString() {
		return super.toString() + "Capitial: " + capital;
	}
	
	//entityType method for country
	public String entityType() {
		return "This entity is a country!";
	}
	
}
