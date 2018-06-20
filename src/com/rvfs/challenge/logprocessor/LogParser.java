package com.rvfs.challenge.logprocessor;

import com.rvfs.challenge.logprocessor.exception.LogParserException;
import com.rvfs.challenge.logprocessor.model.LogObject;
import com.rvfs.challenge.logprocessor.model.LogObjectBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Log parser.
 */
public class LogParser {

    /**
     * This method parse each line of a log file.
     *
     * @param fileName Log filename.
     * @throws LogParserException To handle parse errors.
     */
    public static void parseFile(String fileName) throws LogParserException {

        // regexp for datetimestamp: (^[^\\(]+)
        // regexp for threadId: \(([^)]+)\)
        // regexp for userContext \[([^)]+)\]
        // regexp for request / uri / payload: \/(.*?)\in
        // regexp for request time: (?<=in )(?s)(.*$)
        List<LogObject> parsedLogs = new ArrayList<>();

        Consumer<LogObject> logsConsumer = (log) -> {
            parsedLogs.add(new LogObjectBuilder().setDateTimestamp(Calendar.getInstance()).createLogObject());
        };

        try (Stream<String> stream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {
            stream.forEachOrdered(System.out::println);
        } catch (IOException e) {
            throw new LogParserException("There is a problem to read the log file.", e);
        }
    }


}