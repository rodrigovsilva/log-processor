package com.rvfs.challenge.logprocessor;

import com.rvfs.challenge.logprocessor.exception.LogParserException;
import com.rvfs.challenge.logprocessor.parser.LogParser;

import org.apache.commons.lang3.StringUtils;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Log processor class.
 */
public class LogProcessor {

    /**
     * default date time pattern.
     */
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
            .withZone(ZoneId.systemDefault());

    /**
     * Main method.
     *
     * @param args Args to be used in the program.
     */
    public static void main(String[] args) {

        System.out.println("******************************************************************");
        System.out.println("************************* LOG PROCESSOR **************************");
        Instant begin = Instant.now();
        try {

            if (args.length == 2) {
                String filename = args[0];

                if (StringUtils.isNumeric(args[1])) {
                    long numberOfTopRequests = Long.valueOf(args[1]);

                    LogParser.parseFile(filename);

                    System.out.println("File readed: " + args[0]);
                    System.out.println("Number to print out the top elements: " + numberOfTopRequests);
                } else {
                    throw new LogParserException("The number of top requests must be numeric without digits.");
                }
            } else {
                throw new LogParserException("The program expect 2(two) parameters: \n1 - the log filename  \n2 - a number to print out top 'n' elements with highest average request duration" + args.length);
            }

        } catch (LogParserException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("There is a problem to read the log file.");
        } finally {
            Instant end = Instant.now();

            Duration duration = Duration.between(begin, end);

            System.out.println("Start time: ".concat(DTF.format(begin)));
            System.out.println("End time: ".concat(DTF.format(end)));
            System.out.println("Parse duration in milliseconds: ".concat(String.valueOf(duration.toMillis())));

            System.out.println("***************************************************************");
            System.out.println("***************************************************************");

            System.exit(0);
        }
    }

}