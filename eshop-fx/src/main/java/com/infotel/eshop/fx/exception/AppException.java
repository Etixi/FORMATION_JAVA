package com.infotel.eshop.fx.exception;

public class AppException extends RuntimeException {

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppException(String message) {
		super(message);
	}

	public AppException(Throwable cause) {
		super(cause);
	}

	
}
