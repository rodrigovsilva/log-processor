package com.rvfs.challenge.logprocessor.parser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.rvfs.challenge.logprocessor.domain.LogDataPattern;
import com.rvfs.challenge.logprocessor.exception.LogParserException;
import com.rvfs.challenge.logprocessor.model.LogObject;
import com.rvfs.challenge.logprocessor.model.LogObjectBuilder;

/**
 * Log parser class.
 * 
 * @author Rodrigo Vinicius
 *
 */
public class LogParser {

	/**
	 * This method parse each line of a log file.
	 *
	 * @param fileName
	 *            Log filename.
	 * @throws LogParserException
	 *             To handle parse errors.
	 */
	public static void parseFile(String fileName) throws LogParserException {

		List<LogObject> parsedLogs = new ArrayList<>();

		Consumer<String> logsConsumer = (logLine) -> {
			LogObjectBuilder logBuilder = new LogObjectBuilder();

			LogObject logObject = logBuilder//
					.setDateTimestamp(parseDateTimestamp(logLine)) //
					.setThreadId(parseThreadId(logLine))//
					.setUserContext(parseUserContext(logLine)) //
					.setUri(parseUriRequestPayload(logLine)) //
					.setRequestDurationInMillis(parseRequestDuration(logLine)) //
					.createLogObject();

			System.out.println(logObject.toString());

			parsedLogs.add(logObject);
		};

		try (Stream<String> stream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {
			stream.forEachOrdered(logsConsumer);
		} catch (IOException e) {
			throw new LogParserException("There is a problem to read the log file.", e);
		}
	}

	/**
	 * Parse datetimestamp data.
	 * 
	 * @param logLine
	 *            Log line.
	 * @return datetimestamp data parsed.
	 */
	private static LocalDateTime parseDateTimestamp(String logLine) {
		String dateTimestamp = LogDataPattern.DATETIME.matchPattern(logLine);
		return LocalDateTime.parse(StringUtils.trim(dateTimestamp),
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS"));
	}

	/**
	 * Parse thread id data..
	 * 
	 * @param logLine
	 *            log line.
	 * @return thread id data parsed.
	 */
	private static String parseThreadId(String logLine) {
		return LogDataPattern.THREAD_ID.matchPattern(logLine);
	}

	/**
	 * Parse user context data.
	 * 
	 * @param logLine
	 *            log line.
	 * @return user context data parsed.
	 */
	private static String parseUserContext(String logLine) {
		return LogDataPattern.USER_CONTEXT.matchPattern(logLine);
	}

	/**
	 * Parse URI, Request and Payload data.
	 * 
	 * @param logLine
	 *            Log line.
	 * @return URI, Request and Payload data parsed.
	 */
	private static String parseUriRequestPayload(String logLine) {
		String data = LogDataPattern.URI_REQUEST_PAYLOAD.matchPattern(logLine);

		return data;
	}

	private String parseUri(String parsedData) {
		if (!StringUtils.containsAny(parsedData, ".do")) {
			String[] dataReqPayload = StringUtils.split(parsedData, StringUtils.SPACE);
			
		}

		return StringUtils.EMPTY;
	}

	/**
	 * Parse request duration.
	 * 
	 * @param logLine
	 *            Log line.
	 * @return request duration parsed.
	 */
	private static long parseRequestDuration(String logLine) {
		String data = LogDataPattern.REQUEST_DURATION.matchPattern(logLine);
		return StringUtils.isNumeric(data) ? Long.parseLong(data) : 0;
	}

}