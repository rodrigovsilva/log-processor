package com.rvfs.challenge.logprocessor.report;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.rvfs.challenge.logprocessor.model.LogObject;

public class LogReport {

	public static void drawHistogram(List<LogObject> logs) {

		System.out.println("***********  DRAWING HISTOGRAM OF HOURLY NUMBER OF REQUESTS ***********");

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
