package com.infotel.eshop.exception;

public class UserAlreadyExistsException extends RegisterException{

	public UserAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
		
	}
	
	
	public UserAlreadyExistsException(String message) {
		super(message);
		
	}


	

}
