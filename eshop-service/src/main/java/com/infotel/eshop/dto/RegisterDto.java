package com.infotel.eshop.dto;

public class RegisterDto {
	
	
	private String username;
	private String password;
	private String title;
	private String firstName;
	private String lastName;
	
	

	public RegisterDto(String username, String password, String firstName, String lastName) {
		
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public RegisterDto() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "RegisterDto [username=" + username + ", password=" + password + ", title=" + title + ", firstName="
				+ firstName + ", lastName=" + lastName + "]";
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
