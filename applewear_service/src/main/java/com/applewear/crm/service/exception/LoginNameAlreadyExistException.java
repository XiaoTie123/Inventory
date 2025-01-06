package com.applewear.crm.service.exception;

public class LoginNameAlreadyExistException extends Exception{

	private static final long serialVersionUID = -3862251532223123451L;

	public LoginNameAlreadyExistException(String message) {
		super(message);
	}
}
