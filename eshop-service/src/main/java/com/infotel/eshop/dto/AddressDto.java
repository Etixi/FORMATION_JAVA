package com.infotel.eshop.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Address")
@XmlAccessorType(XmlAccessType.FIELD)
public class AddressDto {
	
	@XmlElement(name="Street")
	private String street;
	@XmlElement(name="Zip")
	private String zip;
	@XmlElement(name="City")
	private String city;
	
	@Override
	public String toString() {
		return "AddressDto [street=" + street + ", zip=" + zip + ", city=" + city + "]";
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
}
