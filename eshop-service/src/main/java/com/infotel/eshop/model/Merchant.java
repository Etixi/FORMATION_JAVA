package com.infotel.eshop.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity @DiscriminatorValue("ME")
public class Merchant extends Person{
	public Merchant(String username, String password, String firstName, String lastName) {
		super(username,password, firstName, lastName);
	}

	
	public Merchant() {
		super();
		
	}


	@Override
	public String toString() {
		return "Merchant [firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	
	}


