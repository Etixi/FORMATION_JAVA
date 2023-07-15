package com.infotel.eshop.exception;

public class EShopException extends RuntimeException{

	public EShopException(String message, Throwable cause) {
		super(message, cause);
	}


	public EShopException(String message) {
		super(message);

	}


	public EShopException(Throwable cause) {
		super(cause);

	}

}
