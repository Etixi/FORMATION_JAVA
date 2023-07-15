package com.infotel.eshop.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Login")
@XmlAccessorType(XmlAccessType.FIELD)
public class LoginDto {
	@XmlElement(name="Username")
	private String username;
	@XmlElement(name="Password")
	private String password;
	
	
	
	@Override
	public String toString() {
		return "LoginDto [username=" + username + ", password=" + password + "]";
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
