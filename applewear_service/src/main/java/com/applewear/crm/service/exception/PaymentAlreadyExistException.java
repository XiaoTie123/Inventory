package com.applewear.crm.service.exception;

public class PaymentAlreadyExistException extends Exception {

	private static final long serialVersionUID = -3833561562286393868L;
	
	public PaymentAlreadyExistException(String message) {
		super(message);
	}

}
