package com.findmostactivecookie;

import com.findmostactivecookie.implementations.CookieContentAnalyzerImpl;
import com.findmostactivecookie.repositories.CookieLogRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class) // This tells JUnit to use Mockito
public class CookieContentAnalyzerImplTest {

    @InjectMocks
    private CookieContentAnalyzerImpl cookieContentAnalyzer; // Inject mocks into this class

    @Mock
    private CookieLogRepository cookieLogRepository; // Mock the repository


    @Test
    public void testFindMostActiveCookies_SingleMostActiveCookie_ReturnSingleMatchingCookie() {
        List<String> logLines = Arrays.asList(
                "cookie,timestamp",
                "Axyzxyz,2023-01-01T10:00:00+00:00",
                "Axyzxyz,2023-01-01T11:00:00+00:00",
                "Bxyzxyz,2023-01-01T10:00:00+00:00",
                "Cxyzxyz,2023-01-01T10:00:00+00:00"
        );

        // No need to mock repository method calls here, as we're not testing the database interaction
        List<String> mostActiveCookies = cookieContentAnalyzer.findMostActiveCookies(logLines, "2023-01-01");

        assertEquals(1, mostActiveCookies.size());
        assertTrue(mostActiveCookies.contains("Axyzxyz"));
        assertFalse(mostActiveCookies.contains("Bxyzxyz"));
    }

    @Test
    public void testFindMostActiveCookies_MultipleMostActiveCookies_ReturnAllMatchingCookies() {
        List<String> logLines = Arrays.asList(
                "cookie,timestamp",
                "Axyzxyz,2023-01-01T10:00:00+00:00",
                "Axyzxyz,2023-01-01T11:00:00+00:00",
                "Bxyzxyz,2023-01-01T10:00:00+00:00",
                "Bxyzxyz,2023-01-01T11:00:00+00:00",
                "Cxyzxyz,2023-01-01T11:00:00+00:00"
        );

        List<String> mostActiveCookies = cookieContentAnalyzer.findMostActiveCookies(logLines, "2023-01-01");

        assertEquals(2, mostActiveCookies.size());
        assertTrue(mostActiveCookies.contains("Axyzxyz"));
        assertTrue(mostActiveCookies.contains("Bxyzxyz"));
        assertFalse(mostActiveCookies.contains("Cxyzxyz"));
    }

    @Test
    public void testFindMostActiveCookies_NoMatchingDate_ReturnEmptyList() {
        List<String> logLines = Arrays.asList(
                "cookie,timestamp",
                "Axyzxyz,2023-01-01T10:00:00+00:00",
                "Bxyzxyz,2023-01-01T11:00:00+00:00"
        );

        List<String> mostActiveCookies = cookieContentAnalyzer.findMostActiveCookies(logLines, "2023-01-02");

        assertTrue(mostActiveCookies.isEmpty());
    }

    @Test
    public void testFindMostActiveCookies_EmptyLogLines_ReturnEmptyList() {
        List<String> logLines = Arrays.asList("cookie,timestamp");

        List<String> mostActiveCookies = cookieContentAnalyzer.findMostActiveCookies(logLines, "2023-01-01");

        assertTrue(mostActiveCookies.isEmpty());
    }

    @Test
    public void testFindMostActiveCookies_SkipMalformedLogLine() {
        List<String> logLines = Arrays.asList(
                "cookie,timestamp",
                "Axyzxyz,2023-01-01T10:00:00+00:00",
                "Bxyzxyz2023-01-01T11:00:00+00:00", // Malformed entry
                "Cxyzxyz,2023-01-01T12:00:00+00:00"
        );

        List<String> mostActiveCookies = cookieContentAnalyzer.findMostActiveCookies(logLines, "2023-01-01");

        assertEquals(2, mostActiveCookies.size());
        assertTrue(mostActiveCookies.contains("Axyzxyz"));
        assertFalse(mostActiveCookies.contains("Bxyzxyz"));
    }

    @Test
    public void testFindMostActiveCookies_SkipInvalidTimeStamp() {
        List<String> logLines = Arrays.asList(
                "cookie,timestamp",
                "Axyzxyz,INVALID_TIMESTAMP", // Invalid timestamp
                "Bxyzxyz,2023-01-01T11:00:00+00:00"
        );

        List<String> mostActiveCookies = cookieContentAnalyzer.findMostActiveCookies(logLines, "2023-01-01");

        assertEquals(1, mostActiveCookies.size());
        assertTrue(mostActiveCookies.contains("Bxyzxyz"));
        assertFalse(mostActiveCookies.contains("Axyzxyz"));
    }
}
