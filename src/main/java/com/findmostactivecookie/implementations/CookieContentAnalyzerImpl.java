package com.findmostactivecookie.implementations;

import com.findmostactivecookie.interfaces.CookieContentAnalyzer;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Stream;


@Component
public class CookieContentAnalyzerImpl implements CookieContentAnalyzer {
    private static final String TIMESTAMP_SEPARATOR = "T";

    public static final Logger LOGGER = Logger.getLogger(CookieContentAnalyzerImpl.class.getName());

    public List<String> findMostActiveCookies(Stream<String> logLines, String date) {
        Map<String, Integer> cookieCount = new HashMap<>();
        int maxCount = 0;
        final int[] count = {0};

        System.out.println("Below is the process to analyze stream of logs matching with the date given by the user: ");

        logLines.skip(1)
                .map(line -> line.split(","))
                .map(line -> new String[] {line[0], line[1].split("T")[0]})
                .filter(line -> line[1].equals(date))
                .forEach( line ->
                {
                    count[0] = cookieCount.getOrDefault(line[0],0);
                    cookieCount.put(line[0], count[0] +1);

                });


        maxCount = Collections.max(cookieCount.values());

        System.out.println(cookieCount);

        final int maxCountFinal = maxCount;


        return cookieCount.entrySet().stream()
                .filter(entry -> entry.getValue() == maxCountFinal)
                .map(entry -> entry.getKey())
                .toList();

//        for (String line : logLines1) {
//
//            String[] parts = line.split(",");
//
//            if(Boolean.FALSE.equals(hasProperFormat(line))){
//                continue;
//            }
//
//            String[] timeParts = parts[1].split("T");
//            //Skip process to analyze log lines if log entry has invalid date time stamp
//            if (timeParts.length == 0) {
//                LOGGER.warning("Invalid timestamp format: " + parts[1]);
//                continue;
//            }
//
//            String cookie = parts[0];
//            String timestamp = timeParts[0];
//
//            if (date.equals(timestamp)) {
//                int count = cookieCount.getOrDefault(cookie, 0) + 1;
//                cookieCount.put(cookie, count);
//                maxCount = Math.max(maxCount, count);
//            }
//        }
//
//        final int max = maxCount;
//        return cookieCount.entrySet().stream()
//                .filter(entry -> entry.getValue() == max)
//                .map(Map.Entry::getKey)
//                .toList();
//    }
//
//    public Boolean hasProperFormat(String line){
//
//        if(line.split(",").length == 2){
//            return true;
//        }
//        else{
//            return false;
//        }

    }

}
