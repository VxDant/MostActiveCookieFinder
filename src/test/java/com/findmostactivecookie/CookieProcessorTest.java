package com.findmostactivecookie;

import com.findmostactivecookie.implementations.CookieProcessor;
import com.findmostactivecookie.interfaces.CookieContentAnalyzer;
import com.findmostactivecookie.interfaces.FileReader;
import org.junit.Before;
import org.junit.Test;


import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CookieProcessorTest {
    private FileReader fileReaderMock;
    private CookieContentAnalyzer cookieContentAnalyzerMock;
    private CookieProcessor cookieProcessor;

    @Before
    public void setUp() {
        fileReaderMock = mock(FileReader.class);
        cookieContentAnalyzerMock = mock(CookieContentAnalyzer.class);
        cookieProcessor = new CookieProcessor(fileReaderMock, cookieContentAnalyzerMock);
    }

    @Test
    public void testProcess_ValidFile_ProcessesMostActiveCookies_Happy_Path() throws IOException {
        List<String> logLines = Arrays.asList(
                "cookie,timestamp",
                "Axyzxyz,2023-01-01T10:00:00+00:00",
                "Axyzxyz,2023-01-01T11:00:00+00:00",
                "Bxyzxyz,2023-01-01T10:00:00+00:00"
        );
        when(fileReaderMock.readLogFile("test.csv")).thenReturn(logLines);
        when(cookieContentAnalyzerMock.findMostActiveCookies(logLines, "2023-01-01"))
                .thenReturn(Collections.singletonList("Axyzxyz"));

        cookieProcessor.process("test.csv", "2023-01-01");
        verify(fileReaderMock).readLogFile("test.csv");
        verify(cookieContentAnalyzerMock).findMostActiveCookies(logLines, "2023-01-01");

    }

    @Test
    public void testProcess_ValidFile_DateWithNoMatchingCookies_ReturnsEmptyList() throws IOException {
        List<String> logLines = Arrays.asList(
                "cookie,timestamp",
                "Axyzxyz,2023-01-01T10:00:00+00:00"
        );
        when(fileReaderMock.readLogFile("test.csv")).thenReturn(logLines);
        when(cookieContentAnalyzerMock.findMostActiveCookies(logLines, "2023-01-02"))
                .thenReturn(Collections.emptyList());

        cookieProcessor.process("test.csv", "2023-01-02");
        verify(fileReaderMock).readLogFile("test.csv");
        verify(cookieContentAnalyzerMock).findMostActiveCookies(logLines, "2023-01-02");
    }

    @Test
    public void testProcess_ValidFile_MultipleMostActiveCookies_ReturnsAll() throws IOException {
        List<String> logLines = Arrays.asList(
                "cookie,timestamp",
                "Axyzxyz,2023-01-01T10:00:00+00:00",
                "Axyzxyz,2023-01-01T11:00:00+00:00",
                "Bxyzxyz,2023-01-01T10:00:00+00:00",
                "Bxyzxyz,2023-01-01T11:00:00+00:00"
        );
        when(fileReaderMock.readLogFile("test.csv")).thenReturn(logLines);
        when(cookieContentAnalyzerMock.findMostActiveCookies(logLines, "2023-01-01"))
                .thenReturn(Arrays.asList("Axyzxyz", "Bxyzxyz"));

        cookieProcessor.process("test.csv", "2023-01-01");
        verify(fileReaderMock).readLogFile("test.csv");
        verify(cookieContentAnalyzerMock).findMostActiveCookies(logLines, "2023-01-01");
    }

    @Test
    public void testProcess_EmptyFile_ReturnsEmptyList() throws IOException {
        when(fileReaderMock.readLogFile("test.csv")).thenReturn(Collections.emptyList());
        when(cookieContentAnalyzerMock.findMostActiveCookies(Collections.emptyList(), "2023-01-01"))
                .thenReturn(Collections.emptyList());

        cookieProcessor.process("test.csv", "2023-01-01");
        verify(fileReaderMock).readLogFile("test.csv");
        verify(cookieContentAnalyzerMock).findMostActiveCookies(Collections.emptyList(), "2023-01-01");
    }

    @Test
    public void testProcess_FileReadError_HandlesIOExceptionGracefully() throws IOException {
        when(fileReaderMock.readLogFile("test.csv")).thenThrow(new IOException("File not found"));

        cookieProcessor.process("test.csv", "2023-01-01");
        verify(fileReaderMock).readLogFile("test.csv");

//        assertThrows(IOException.class, () -> cookieProcessor.process("test.csv", "2023-01-01"));


    }
}
