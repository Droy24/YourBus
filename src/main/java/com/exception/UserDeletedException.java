package com.exception;

/**
 * Forbidden Exception Handler class.
 * 
 * @author Devasheesh
 * @version 1.0
 */
public class UserDeletedException extends RuntimeException {

	private static final long serialVersionUID = 3082972573961285436L;

	public UserDeletedException() {
		super();
	}

	public UserDeletedException(String message) {
		super(message);
	}
}
