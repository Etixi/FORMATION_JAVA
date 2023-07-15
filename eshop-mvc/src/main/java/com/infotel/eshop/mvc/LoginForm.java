package com.infotel.eshop.mvc;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


public class LoginForm {
	
	@NotBlank(message="identifiant obligatoire")
	@Email(message="Veuillez saisir votre addresse mail")
	private String username;
	
	@NotBlank(message="Mot de passe obligatoire")
	private String password;
	
	
	
	@Override
	public String toString() {
		return "LoginForm [username=" + username + ", password=" + password + "]";
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
