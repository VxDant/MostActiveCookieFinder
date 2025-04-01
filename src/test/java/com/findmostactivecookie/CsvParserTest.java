package com.findmostactivecookie;

import com.findmostactivecookie.implementations.CsvReader;
import com.findmostactivecookie.interfaces.FileReader;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.Assert.*;

public class CsvParserTest {
    private FileReader fileReader;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() {
        fileReader = new CsvReader();
    }

    @Test
    public void testReadLogFile_ValidFile_WithHeaderAndLogs_HappyPath() throws IOException {
        File logFile = temporaryFolder.newFile("test_log.csv");
        List<String> testLines = List.of(
                "cookie,timestamp",
                "Axyzxyz,2023-01-01T10:00:00+00:00",
                "Bxyzxyz,2023-01-01T11:00:00+00:00"
        );
        Files.write(logFile.toPath(), testLines);

        List<String> readLines = fileReader.readLogFile(logFile.getAbsolutePath());

        assertNotNull(readLines);
        assertEquals(3, readLines.size());
        assertEquals("cookie,timestamp", readLines.get(0));
        assertEquals("Axyzxyz,2023-01-01T10:00:00+00:00", readLines.get(1));
    }

    @Test(expected = IOException.class)
    public void testReadLogFile_NoFilePresent_UnhappyPath_ThrowException() throws IOException {
        fileReader.readLogFile("non_existent_file.csv");
    }

    @Test
    public void testReadLogFile_FileIsEmpty_UnhappyPath_NoException() throws IOException {
        File emptyFile = temporaryFolder.newFile("empty_log.csv");

        List<String> readLines = fileReader.readLogFile(emptyFile.getAbsolutePath());

        assertNotNull(readLines);
        assertTrue(readLines.isEmpty());
    }

    @Test
    public void testReadLogFile_FileWithOnlyHeader_NoException() throws IOException {
        File headerFile = temporaryFolder.newFile("header_only_log.csv");
        List<String> headerLine = List.of("cookie,timestamp");
        Files.write(headerFile.toPath(), headerLine);

        List<String> readLines = fileReader.readLogFile(headerFile.getAbsolutePath());

        assertNotNull(readLines);
        assertEquals(1, readLines.size());
        assertEquals("cookie,timestamp", readLines.get(0));
    }

    @Test
    public void testReadLogFile_LargeFile_thousand_lines_of_cookies() throws IOException {
        // Create a large file with multiple entries
        File largeFile = temporaryFolder.newFile("large_log.csv");
        StringBuilder largeContent = new StringBuilder("cookie,timestamp\n");

        for (int i = 0; i < 1000; i++) {
            largeContent.append("Axyzxyz").append(i)
                    .append(",2023-01-01T10:00:00+00:00\n");
        }

        Files.writeString(largeFile.toPath(), largeContent.toString());

        List<String> readLines = fileReader.readLogFile(largeFile.getAbsolutePath());

        assertNotNull(readLines);
        assertEquals(1001, readLines.size());
        assertEquals("cookie,timestamp", readLines.get(0));
    }
}