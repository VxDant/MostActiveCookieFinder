package com.findmostactivecookie;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CookieAnalyzerWithStream {
    private final CSVReaderAsStream csvReader;

    public CookieAnalyzerWithStream() {
        this.csvReader = new CSVReaderAsStream();
    }

    /**
     * Finds the most active cookies for a given date using Java streams
     * @param filePath Path to the cookie log file
     * @param date Date to analyze in YYYY-MM-DD format
     * @return Set of most active cookies
     * @throws IOException If file cannot be read
     */
    public Set<String> findMostActiveCookies(String filePath, String date) throws IOException {
        try (Stream<String> lines = csvReader.readCSVAsStream(filePath, true)) {

            Map<String, Long> cookieCounts = lines
                    .map(line -> line.split(","))
                    .filter(parts -> parts.length >= 2)
                    .filter(parts -> date.equals(parts[1].split("T")[0]))
                    .map(parts -> parts[0])  // Extract cookie
                    .collect(Collectors.groupingBy(
                            Function.identity(),
                            Collectors.counting()
                    ));

            if (cookieCounts.isEmpty()) {
                return Collections.emptySet();
            }

            Long maxCount = cookieCounts.values().stream()
                    .max(Long::compare)
                    .orElse(0L);

            return cookieCounts.entrySet().stream()
                    .filter(entry -> entry.getValue().equals(maxCount))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toSet());
        }
    }
}
