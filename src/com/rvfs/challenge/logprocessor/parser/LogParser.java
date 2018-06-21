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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.rvfs.challenge.logprocessor.domain.LogRegexpPattern;
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

		Consumer<String> logsConsumer = (log) -> {
			LogObjectBuilder logBuilder = new LogObjectBuilder();

			String dateTimestamp = matchPattern(LogRegexpPattern.DATETIME.getPattern(), log);
			LocalDateTime localDate = LocalDateTime.parse(StringUtils.trim(dateTimestamp),
					DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS"));

			LogObject logObject = logBuilder.setDateTimestamp(localDate).createLogObject();

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
	 * Match a pattern.
	 *
	 * @param pattern
	 *            Regexp Pattern to test.
	 * @param logLine
	 *            Line of log.
	 * @return Piece of log found.
	 */
	private static String matchPattern(String pattern, String logLine) {
		Pattern logPattern = Pattern.compile(pattern);
		Matcher matcher = logPattern.matcher(logLine);

		if (matcher.find()) {
			return matcher.group(0);
		}

		return StringUtils.EMPTY;
	}
}