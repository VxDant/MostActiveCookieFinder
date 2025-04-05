package com.findmostactivecookie.implementations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CsvBufferedReader {

    /**
     * Reads a CSV file using BufferedReader - efficient for large files
     * @param filePath Path to the CSV file
     * @param hasHeader Whether the CSV has a header row to skip
     * @return List of String arrays, where each array contains one row's values
     */
    public static List<String[]> readCSVWithBufferedReader(String filePath, boolean hasHeader) {
        List<String[]> csvData = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Skip header if present
            if (hasHeader) {
                line = br.readLine();
            }// Header skipped

            // Read data rows
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                csvData.add(values);
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }

        return csvData;
    }

    /**
     * Reads a CSV file using NIO Files - simpler code but loads entire file into memory
     * @param filePath Path to the CSV file
     * @param hasHeader Whether the CSV has a header row to skip
     * @return List of String arrays, where each array contains one row's values
     */
    public static List<String[]> readCSVWithNIO(String filePath, boolean hasHeader) {
        List<String[]> csvData = new ArrayList<>();

        try {
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);

            // Skip header if present
            if (hasHeader && !lines.isEmpty()) {
                lines.remove(0);
            }

            // Process data rows
            for (String line : lines) {
                String[] values = line.split(",");
                csvData.add(values);
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }

        return csvData;
    }

    /**
     * Reads a CSV file using Java 8 Streams
     * @param filePath Path to the CSV file
     * @param hasHeader Whether the CSV has a header row to skip
     * @return List of String arrays, where each array contains one row's values
     */
    public static List<String[]> readCSVWithStreams(String filePath, boolean hasHeader) {
        try {
            Path path = Paths.get(filePath);
            return Files.lines(path)
                    .skip(hasHeader ? 1 : 0)
                    .map(line -> line.split(","))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Handles quoted CSV values (e.g., "value,with,commas")
     * @param filePath Path to the CSV file
     * @param hasHeader Whether the CSV has a header row to skip
     * @return List of String arrays, where each array contains one row's values
     */
    public static List<String[]> readCSVWithQuotedValues(String filePath, boolean hasHeader) {
        List<String[]> csvData = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Skip header if present
            if (hasHeader && (line = br.readLine()) != null) {
                // Header skipped
            }

            // Read data rows
            while ((line = br.readLine()) != null) {
                csvData.add(parseCSVLine(line));
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }

        return csvData;
    }

    /**
     * Custom parser for CSV lines that handles quoted values
     * @param line A single line from the CSV file
     * @return Array of values from the line
     */
    private static String[] parseCSVLine(String line) {
        List<String> values = new ArrayList<>();
        StringBuilder currentValue = new StringBuilder();
        boolean inQuotes = false;

        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                values.add(currentValue.toString());
                currentValue = new StringBuilder();
            } else {
                currentValue.append(c);
            }
        }

        // Add the last value
        values.add(currentValue.toString());

        return values.toArray(new String[0]);
    }

    /**
     * Example usage of the CSV reader methods
     */
    public static void main(String[] args) {
        String filePath = "src/main/java/com/findmostactivecookie/log/cookie_log.csv";
        boolean hasHeader = true;
        String userGivenDate = "2018-12-06";


        // Choose the appropriate method based on your needs
        List<String[]> data = readCSVWithBufferedReader(filePath, true);

        CookieDataCounter cookieDataCounter = new CookieDataCounter(data, userGivenDate);


//        // Print the data for demonstration
//        for (String[] row : data) {
//            System.out.println(Arrays.toString(row));
//        }
    }
}