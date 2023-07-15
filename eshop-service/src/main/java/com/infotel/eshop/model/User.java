package com.infotel.eshop.model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity  @Table(name="user_data")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type",discriminatorType = DiscriminatorType.STRING)
public abstract class User {
	@Id
	protected String username;
	protected String password;
	
	
	
	public User() {
		super();
		
	}
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
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
