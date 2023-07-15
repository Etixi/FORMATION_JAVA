package com.infotel.eshop.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerDto {
	
	@XmlElement(name="Username")
	private String username;
	@XmlElement(name="FirstName")
	private String firstName;
	@XmlElement(name="Lastname")
	private String lastName;
	@XmlElement(name="Title")
	private String title;
	@XmlElement(name="Phone")
	private String phone;
	@XmlElement(name="Address")
	private AddressDto address;
	
	

	@Override
	public String toString() {
		return "CustomerDto [username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", title="
				+ title + ", phone=" + phone + ", address=" + address + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}
	
	
	
}
