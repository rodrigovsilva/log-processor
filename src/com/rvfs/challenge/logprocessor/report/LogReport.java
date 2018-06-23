package com.rvfs.challenge.logprocessor.report;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.rvfs.challenge.logprocessor.model.LogObject;

/**
 * Class responsible to draw and report logs.
 * 
 * @author Rodrigo Vinicius Ferreira da Silva
 *
 */
public class LogReport {

	/**
	 * Print out top resources with highest average request of duration.
	 * 
	 * @param logs
	 * @param numberOfRequest
	 */
	public static void printTopResourcesWithHighestAverageRequestDuration(List<LogObject> logs, long numberOfRequest) {

		System.out.println("***  PRINTING OU TOP RESOURCES WITH HIGHEST AVERAGE REQUEST OF DURATION ***");

		// order by local date time
		logs.sort(Comparator.comparing(LogObject::getRequestDurationInMillis).reversed());

		logs.stream().filter(l -> (StringUtils.isNotEmpty(l.getResourceName()))).limit(numberOfRequest)
				.forEach(l -> System.out.println(new StringBuilder(l.getResourceName()).append(": ")
						.append(l.getRequestDurationInMillis()).append(" - ").append(l.toString())));

	}

	/**
	 * Draw Histogram.
	 * 
	 * @param logs
	 *            Logs to prepare histogram.
	 */
	public static void drawHistogram(List<LogObject> logs) {

		System.out.println("***  DRAWING HISTOGRAM OF HOURLY NUMBER OF REQUESTS ******************");

		// order by local date time
		logs.sort(Comparator.comparing(LogObject::getDateTimestamp));

		// grouping by date and hour of day
		Map<String, Long> histogram = logs.stream()
				.collect(Collectors.groupingBy(LogObject::getDateTimeGrouping, Collectors.counting()));

		// sort histogram by key
		histogram.entrySet().stream().sorted(Map.Entry.comparingByKey());

		// print the histogram
		histogram.forEach((String t, Long u) -> System.out.println(new StringBuilder(t).append(": ").append(u)));
	}

}
