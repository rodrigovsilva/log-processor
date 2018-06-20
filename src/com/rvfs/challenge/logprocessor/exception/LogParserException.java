package com.rvfs.challenge.logprocessor.exception;

/**
 * Exception for handling errors when there are errors in the parse log.
 */
public class LogParserException extends Exception {

    public LogParserException(String message) {
        super(message);
    }

    public LogParserException(String message, Throwable e) {
        super(message, e);
    }
}
