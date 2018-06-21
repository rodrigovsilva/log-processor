package com.rvfs.challenge.logprocessor.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * All log regexp patterns.
 *
 * 
 * @author Rodrigo Vinicius
 */
public enum LogDataPattern {

	/**
	 * Get chars until a open parentheses char.
	 */
	DATETIME("(^[^\\\\(]+)", 0),

	/**
	 * Get chars between two parentheses.
	 */
	THREAD_ID("\\(([^)]+)\\)", 1),

	/**
	 * Get chars inside brackets.
	 */
	USER_CONTEXT("\\[([^)]+)\\]", 1),

	/**
	 * Get all chars between '/' and ' in ' to separate in URI, queryparams,
	 * request and request payload.
	 */
	URI_REQUEST_PAYLOAD("(?<=\\/)(.*)(?= in )", 1),

	/**
	 * Get all chars after
	 */
	REQUEST_DURATION("(?<= in )(.*)", 1);

	private String pattern;

	private int group;

	LogDataPattern(String pattern, int group) {
		this.pattern = pattern;
		this.group = group;
	}

	public String getPattern() {
		return pattern;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	/**
	 * Match a pattern.
	 *
	 * @param pattern
	 *            Regexp Pattern to test.
	 * @param logLine
	 *            Line of log.
	 * @param group
	 *            Group of the match to get.
	 * @return Piece of log found.
	 */
	public String matchPattern(String logLine) {
		Pattern logPattern = Pattern.compile(this.pattern);
		Matcher matcher = logPattern.matcher(logLine);

		if (matcher.find()) {
			return matcher.group(this.group);
		}

		return StringUtils.EMPTY;
	}

}