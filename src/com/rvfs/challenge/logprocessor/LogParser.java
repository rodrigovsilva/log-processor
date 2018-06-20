package com.rvfs.challenge.logprocessor;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Log parser.
 */
public class LogParser {

    /**
     * This method parse each line of a log file.
     */
    public static void parseFile(String fileName){

        try (Stream<String> stream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)){
            stream.forEachOrdered(System.out::println);
        } catch(IOException e){
            e.printStackTrace();
            System.err.println("There is a problem to read the log file.");
        } catch(Exception e){
            e.printStackTrace();
            System.err.println("There is a problem to parse the log file.");
        }
    }
}