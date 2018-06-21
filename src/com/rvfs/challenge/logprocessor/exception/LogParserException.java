package com.rvfs.challenge.logprocessor.exception;

/**
 * Exception for handling errors when there are errors in the parse log.
 * 
 * @author Rodrigo Vinicius
 */
public class LogParserException extends Exception {

	/**
	 * Serial UID.
	 */
	private static final long serialVersionUID = 1L;

	public LogParserException(String message) {
		super(message);
	}

	public LogParserException(String message, Throwable e) {
		super(message, e);
	}
}
