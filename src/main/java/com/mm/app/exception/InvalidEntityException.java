package com.mm.app.exception;

public class InvalidEntityException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7083514865346018735L;

	public InvalidEntityException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidEntityException(String message) {
		super(message);
	}

	public InvalidEntityException(Throwable cause) {
		super(cause);
	}

}
