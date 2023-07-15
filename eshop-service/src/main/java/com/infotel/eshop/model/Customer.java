package com.infotel.eshop.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("CU")
public class Customer extends Person{
	
	@OneToOne(mappedBy="customer")
	private Address address; 
	public Customer() {
		super();
	
	}



	public Customer(
			String username,
			String password,
			String firstName,
			String lastName) 
	{this(username, password,firstName, lastName, null, null);
	}



	private String title;
	private String phone;

	public Customer(
			String username,
			String password,
			String firstName,
			String lastName,
			String title,
			String phone) {
		super(username, password,firstName, lastName);
		this.title = title;
		this.phone = phone;
	}



	@Override
	public String toString() {
		return "Customer [title=" + title + ", phone=" + phone + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", username=" + username + ", password=" + password + "]";
	}




	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}



	public Address getAddress() {
		return address;
	}



	public void setAddress(Address address) {
		this.address = address;
	}
	


}
