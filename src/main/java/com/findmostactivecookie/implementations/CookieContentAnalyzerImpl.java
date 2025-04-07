package com.findmostactivecookie.implementations;

import com.findmostactivecookie.Entities.CookieLog;
import com.findmostactivecookie.interfaces.CookieContentAnalyzer;
import com.findmostactivecookie.repositories.CookieLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.logging.Logger;

@Component
public class CookieContentAnalyzerImpl implements CookieContentAnalyzer {
    public static final Logger LOGGER = Logger.getLogger(CookieContentAnalyzerImpl.class.getName());

    @Autowired
    private CookieLogRepository cookieLogRepository;


    public List<String> findMostActiveCookies(List<String> logLines, String date) {
        Map<String, Integer> cookieCount = new HashMap<>();
        int maxCount = 0;

        for (String line : logLines) {
            if (line.startsWith("cookie")) continue;

            String[] parts = line.split(",");
            //Skip process to analyze of log line if log entry is malformed
            if (parts.length != 2) {
                LOGGER.warning("Malformed log entry: " + line);
                continue;
            }

            String[] timeParts = parts[1].split("T");
            //Skip process to analyze log lines if log entry has invalid date time stamp
            if (timeParts.length == 0) {
                LOGGER.warning("Invalid timestamp format: " + parts[1]);
                continue;
            }

            String cookie = parts[0];
            String timestamp = timeParts[0];



            try{
                OffsetDateTime timestamp1 = OffsetDateTime.parse(parts[1], DateTimeFormatter.ISO_OFFSET_DATE_TIME);
                CookieLog cookieLog = new CookieLog();
                cookieLog.setCookie(cookie);
                cookieLog.setTimestamp(timestamp1);


                cookieLogRepository.save(cookieLog);
            }
            catch (DateTimeParseException e)
            {
                LOGGER.warning("Invalid timestamp encountered, skipping log line: " + parts[1]);
                continue;
            }


            if (date.equals(timestamp)) {
                int count = cookieCount.getOrDefault(cookie, 0) + 1;
                cookieCount.put(cookie, count);
                maxCount = Math.max(maxCount, count);
            }
        }

        final int max = maxCount;
        return cookieCount.entrySet().stream()
                .filter(entry -> entry.getValue() == max)
                .map(Map.Entry::getKey)
                .toList();
    }
}
