//Zeying Zhou 20116670
package com.example.guessmaster;

public class Singer extends Person{
	private String debutAlbum;
	private Date debutAlbumReleaseDate;
	
	//constructor method accept six arguments to initialize the Person
	public Singer(String name, Date born, String gender, String debutAlbum, Date debutAlbumReleaseDate, double difficulty) {
		super(name, born, gender, difficulty);
		this.debutAlbum = debutAlbum;
		this.debutAlbumReleaseDate = debutAlbumReleaseDate;
	}
	
	//copy constructor
	public Singer(Singer singer) {
		super(singer);
		this.debutAlbum = singer.debutAlbum;
		this.debutAlbumReleaseDate = new Date(singer.debutAlbumReleaseDate);
	}
	
	//clone method for singer
	public Singer clone() {
		return new Singer(this);
	}
	
	//toString method
	public String toString() {
		return super.toString() + "Debut Album: " + debutAlbum + "\n" + "Release Date: " + debutAlbumReleaseDate.toString();
	}
	
	//entityType method for singer
	public String entityType() {
		return "This entity is a singer!";
	}
}
