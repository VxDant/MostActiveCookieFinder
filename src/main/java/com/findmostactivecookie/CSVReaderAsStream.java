package com.findmostactivecookie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

public class CSVReaderAsStream {

    private static final int BUFFER_SIZE = 8 * 1024 * 1024;

    /**
     * Creates a stream of lines from a CSV file using BufferedReader
     * This approach is memory efficient and works well with Java streams
     *
     * @param filePath Path to the CSV file
     * @param skipHeader Whether to skip the header line
     * @return Stream of lines from the file
     * @throws IOException If file cannot be read
     */
    public Stream<String> readCSVAsStream(String filePath, boolean skipHeader) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath), BUFFER_SIZE);

        if (skipHeader) {
            reader.readLine();
        }


        // Create stream and ensure reader is closed when stream is closed
        return reader.lines().onClose(() -> {
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException("Failed to close reader", e);
            }
        });
    }
}
