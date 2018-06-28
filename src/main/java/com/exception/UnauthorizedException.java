package com.exception;

/**
 * Unauthorized request exception handler class.
 * 
 * @author Devasheesh
 * @version 1.0
 */
public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 1083620595565776251L;

	public UnauthorizedException() {
		super();
	}

	public UnauthorizedException(String message) {
		super(message);
	}
}
