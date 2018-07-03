package com.rvfs.challenge.logprocessor.report;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

		System.out.println("******** TOP RESOURCES WITH HIGHEST AVERAGE REQUEST OF DURATION ********");

		// order by local date time
		logs.sort(Comparator.comparing(LogObject::getRequestDurationInMillis).reversed());

		// collect and group all resources
		Map<String, Long> topResourcesCounting = logs.stream()
				.filter(log -> StringUtils.isNotEmpty(log.getResourceName()))
				.collect(Collectors.groupingBy(LogObject::getResourceName,
						Collectors.collectingAndThen(Collectors.summarizingLong(LogObject::getRequestDurationInMillis),
								summarize -> summarize.getSum() / summarize.getCount())));

		// collect and group all uri and actions
		Map<String, Long> topUriCounting = logs.stream().filter(log -> StringUtils.isNotEmpty(log.getUri()))
				.collect(Collectors.groupingBy(LogObject::getUri,
						Collectors.collectingAndThen(Collectors.summarizingLong(LogObject::getRequestDurationInMillis),
								summarize -> summarize.getSum() / summarize.getCount())));

		Map<String, Long> totalCounting = new LinkedHashMap<>();
		totalCounting.putAll(topResourcesCounting);
		totalCounting.putAll(topUriCounting);

		// ordering all grouping couting
		Map<String, Long> sortedTotalCounting = totalCounting.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(
						Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

		// print out top request average
		sortedTotalCounting.entrySet().stream().limit(numberOfRequest)
				.forEach((e) -> System.out.println(new StringBuilder(e.getKey()).append(": ").append(e.getValue())));

		System.out.println("************************************************************************");

	}

	/**
	 * Draw Histogram.
	 * 
	 * @param logs
	 *            Logs to prepare histogram.
	 */
	public static void drawHistogram(List<LogObject> logs) {
		System.out.println("************************************************************************");
		System.out.println("**********  DRAWING HISTOGRAM OF HOURLY NUMBER OF REQUESTS *************");

		// order by local date time
		logs.sort(Comparator.comparing(LogObject::getDateTimestamp));

		// grouping by date and hour of day
		Map<String, Long> histogram = logs.stream()
				.collect(Collectors.groupingBy(LogObject::getDateTimeGrouping, Collectors.counting()));

		// sort histogram by key
		histogram.entrySet().stream().sorted(Map.Entry.comparingByKey());

		// print the histogram
		histogram.forEach((String t, Long u) -> System.out.println(new StringBuilder(t).append(": ").append(u)));
		System.out.println("************************************************************************");
	}

}
