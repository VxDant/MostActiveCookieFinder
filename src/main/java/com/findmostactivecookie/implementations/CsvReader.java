package com.findmostactivecookie.implementations;

import com.findmostactivecookie.interfaces.FileReader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;



@Component
public class CsvReader implements FileReader {
    private static final Logger LOGGER = Logger.getLogger(CsvReader.class.getName());

    public List<String> readLogFile(String filename) throws IOException {
        Path filePath = Paths.get(filename);
        if (!Files.exists(filePath)) {
            LOGGER.warning("File not found at specified path: " + filename);
        }
        List<String> lines = Files.readAllLines(Paths.get(filename));
        if (lines.isEmpty()) {
            LOGGER.info("The file exists but is empty: " + filename);
        }

        return lines;
    }
}