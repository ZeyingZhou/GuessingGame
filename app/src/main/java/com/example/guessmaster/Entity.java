//Zeying Zhou 20116670
package com.example.guessmaster;

public abstract class Entity {
	private String name;
	private Date born;
	private double difficulty; //new attribute difficulty
	
	//constructor method accept three arguments to initialize the Entity
	public Entity(String name, Date birthDate, double difficulty) {
		this.name = name;
		this.born = new Date(birthDate); //no privacy leak
		this.difficulty = difficulty;
	}
	
	//copy constructor
	public Entity(Entity entity) {
		this.name = entity.name;
		this.born = new Date(entity.born); //no privacy leak
		this.difficulty = entity.difficulty;
	}

	public String getName() {
		return name;
	}

	public Date getBorn() {
		return new Date(born);
	}
	
	//public method getAwardedTicketNumber
	public int getAwardedTicketNumber() {
		return (int)(difficulty*100);
	}
	
	//toString method
	public String toString() {
		return "Name: "+name+"\n"+"Born at: "+born.toString()+"\n";
	}
	
	//abstract method entityType
	public abstract String entityType();
	
	//abstract method clone
	public abstract Entity clone();
	
	//welcomeMessage method
	public String welcomeMessage() {
		return "Welcome! Let's start the game! This entity is a " + this.entityType();
	}
	
	//closingMessage method
	public String closingMessage() {
		return "Congratulations! The detailed information of the entity you guess is:\n" + this.toString();
	}
}
