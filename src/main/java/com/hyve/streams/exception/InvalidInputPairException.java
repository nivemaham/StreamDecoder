package com.hyve.streams.exception;

/**
 * Thrown when invalid input is passed
 * @author nive
 *
 */
public class InvalidInputPairException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public InvalidInputPairException() {
	}

	// Constructor that accepts a message
	public InvalidInputPairException(String message) {
		super(message);
	}
}
