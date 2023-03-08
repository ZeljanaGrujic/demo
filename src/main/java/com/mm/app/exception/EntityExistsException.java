package com.mm.app.exception;

public class EntityExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -24653081154735358L;

	public EntityExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityExistsException(String message) {
		super(message);
	}

	public EntityExistsException(Throwable cause) {
		super(cause);
	}

}
