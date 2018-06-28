package com.exception;

/**
 * Bad Request Exception Handler class.
 * 
 * @author Devasheesh 
 * @version 1.0
 */
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -209952997370629714L;

	public BadRequestException() {
		super();
	}

	public BadRequestException(String message) {
		super(message);
	}
}
