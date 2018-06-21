package com.rvfs.challenge.logprocessor.domain;

/**
 * All log regexp patterns.
 *
 * regexp for datetimestamp: (^[^\\(]+) regexp for threadId: \(([^)]+)\) regexp
 * for userContext \[([^)]+)\] regexp for request / uri / payload: \/(.*?)\in
 * regexp for request time: "
 * 
 * @author Rodrigo Vinicius
 */
public enum LogRegexpPattern {

	DATETIME("(^[^\\\\(]+)"),

	THREAD_ID("\\(([^)]+)\\)"),

	USER_CONTEXT("\\[([^)]+)\\]"),

	URI_REQUEST_PAYLOAD("\\/(.*?)\\in"),

	REQUEST_DURATION("\\/(.*?)\\in");

	private String pattern;

	LogRegexpPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getPattern() {
		return pattern;
	}
}