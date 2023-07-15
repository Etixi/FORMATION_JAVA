package com.infotel.eshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity 
public abstract class Person extends User{
	@Column(name="first_name")
	protected String firstName;
	@Column(name="last_name")
	protected String lastName;
	
	
	
	public Person() {
		super();
		
	}


	public Person(String username,
			String password, 
			String firstName,
			String lastName) {
		super(username, password);
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
