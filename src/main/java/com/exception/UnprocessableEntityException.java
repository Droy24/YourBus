package com.exception;

/**
 * Unprocessable entity request exception handler class.
 * 
 * @author Devasheesh
 * @version 1.0
 */
public class UnprocessableEntityException extends RuntimeException {

	private static final long serialVersionUID = -4648015318860275959L;

	public UnprocessableEntityException() {
		super();
	}

	public UnprocessableEntityException(String message) {
		super(message);
	}
}