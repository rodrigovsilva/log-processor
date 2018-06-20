package com.rvfs.challenge.logprocessor;

import java.io.File;
import java.util.Date;

/**
 * Log processor class.
 */
public class LogProcessor {

    /**
     * Main method.
     * 
     * @param args Args to be used in the program.
     */
    public static void main(String[] args) {

        
        if (args.length == 2) {
            
            LogParser.parseFile(args[0]);

            System.out.println("File readed: " + args[0]);
            System.out.println("Number to print out the top elements: " + args[1]);
        } else {
            System.err.println("The program expect 2(two) parameters: \n1 - the log filename  \n2 - a number to print out top 'n' elements with highest average request duration" + args.length);
            System.exit(0);
        }        
    }

}