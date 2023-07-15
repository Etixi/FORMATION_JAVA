package com.infotel.eshop.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Address {
	
	@Id
	private String id;
	private String street;
	private String postcode;
	private String city;
	
	@OneToOne 
	@JoinColumn(name="id")
	@MapsId
	private Customer customer;
	
	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", postcode=" + postcode + ", city=" + city + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
	
}
