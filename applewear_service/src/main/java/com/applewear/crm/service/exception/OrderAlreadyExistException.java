package com.applewear.crm.service.exception;

public class OrderAlreadyExistException extends Exception {

	private static final long serialVersionUID = -4268590147375098481L;

	public OrderAlreadyExistException(String message) {
		super(message);
	}

}
