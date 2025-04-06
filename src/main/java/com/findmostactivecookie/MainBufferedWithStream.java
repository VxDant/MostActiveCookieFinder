package com.findmostactivecookie;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

public class MainBufferedWithStream {
    public static void main(String[] args) {
        // Parse command line arguments
        if (args.length < 4 || !Objects.equals(args[0], "-f") || !Objects.equals(args[2], "-d")) {
            System.out.println("Usage: java -jar findmostactivecookie.jar -f filename -d date");
            return;
        }

        String filename = args[1];
        String date = args[3];

        CookieAnalyzerWithStream analyzer = new CookieAnalyzerWithStream();

        try {
            long startTime = System.currentTimeMillis();

            Set<String> mostActiveCookies = analyzer.findMostActiveCookies(filename, date);

            long endTime = System.currentTimeMillis();

            if (mostActiveCookies.isEmpty()) {
                System.out.println("No cookies found for date: " + date);
                return;
            }

            // Print results
            mostActiveCookies.forEach(System.out::println);

            // Report execution time
            System.err.println("Processing completed in " + (endTime - startTime) + "ms");

        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        }
    }
}