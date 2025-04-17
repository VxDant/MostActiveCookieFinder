//package com.findmostactivecookie;
//
//import com.findmostactivecookie.implementations.CookieContentAnalyzerImpl;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//public class CookieContentAnalyzerImplTest {
//    private com.findmostactivecookie.interfaces.CookieContentAnalyzer cookieContentAnalyzer;
//
//    @Before
//    public void setUp() {
//        cookieContentAnalyzer = new CookieContentAnalyzerImpl();
//    }
//
//    @Test
//    public void testFindMostActiveCookies_SingleMostActiveCookie_ReturnSingleMatchingCookie() {
//        List<String> logLines = Arrays.asList(
//                "cookie,timestamp",
//                "Axyzxyz,2023-01-01T10:00:00+00:00",
//                "Axyzxyz,2023-01-01T11:00:00+00:00",
//                "Bxyzxyz,2023-01-01T10:00:00+00:00",
//                "Cxyzxyz,2023-01-01T10:00:00+00:00"
//        );
//
//        List<String> mostActiveCookies = cookieContentAnalyzer.findMostActiveCookies(logLines, "2023-01-01");
//
//        assertEquals(1, mostActiveCookies.size());
//        assertTrue(mostActiveCookies.contains("Axyzxyz"));
//        assertFalse(mostActiveCookies.contains("Bxyzxyz"));
//
//    }
//
//    @Test
//    public void testFindMostActiveCookies_MultipleMostActiveCookies_ReturnAllMatchingCookies() {
//        List<String> logLines = Arrays.asList(
//                "cookie,timestamp",
//                "Axyzxyz,2023-01-01T10:00:00+00:00",
//                "Axyzxyz,2023-01-01T11:00:00+00:00",
//                "Bxyzxyz,2023-01-01T10:00:00+00:00",
//                "Bxyzxyz,2023-01-01T11:00:00+00:00",
//                "Cxyzxyz,2023-01-01T11:00:00+00:00"
//        );
//
//        List<String> mostActiveCookies = cookieContentAnalyzer.findMostActiveCookies(logLines, "2023-01-01");
//
//        assertEquals(2, mostActiveCookies.size());
//        assertTrue(mostActiveCookies.contains("Axyzxyz"));
//        assertTrue(mostActiveCookies.contains("Bxyzxyz"));
//        assertFalse(mostActiveCookies.contains("Cxyzxyz"));
//
//    }
//
//    @Test
//    public void testFindMostActiveCookies_NoMatchingDate_ReturnEmptyList() {
//        List<String> logLines = Arrays.asList(
//                "cookie,timestamp",
//                "Axyzxyz,2023-01-01T10:00:00+00:00",
//                "Bxyzxyz,2023-01-01T11:00:00+00:00"
//        );
//
//        List<String> mostActiveCookies = cookieContentAnalyzer.findMostActiveCookies(logLines, "2023-01-02");
//
//        assertTrue(mostActiveCookies.isEmpty());
//    }
//
//    @Test
//    public void testFindMostActiveCookies_EmptyLogLines_ReturnEmptyList() {
//        List<String> logLines = Arrays.asList("cookie,timestamp");
//
//        List<String> mostActiveCookies = cookieContentAnalyzer.findMostActiveCookies(logLines, "2023-01-01");
//
//        assertTrue(mostActiveCookies.isEmpty());
//    }
//
//    @Test
//    public void testFindMostActiveCookies_SkipMalformedLogLine() {
//        List<String> logLines = Arrays.asList(
//                "cookie,timestamp",
//                "Axyzxyz,2023-01-01T10:00:00+00:00",
//                "Bxyzxyz2023-01-01T11:00:00+00:00",
//                "Cxyzxyz,2023-01-01T12:00:00+00:00"
//        );
//
//        List<String> mostActiveCookies = cookieContentAnalyzer.findMostActiveCookies(logLines, "2023-01-01");
//
//        assertEquals(2, mostActiveCookies.size());
//        assertTrue(mostActiveCookies.contains("Axyzxyz"));
//        assertFalse(mostActiveCookies.contains("Bxyzxyz"));
//
//    }
//
//    @Test
//    public void testFindMostActiveCookies_SkipInvalidTimeStamp() {
//        List<String> logLines = Arrays.asList(
//                "cookie,timestamp",
//                "Axyzxyz,INVALID_TIMESTAMP",
//                "Bxyzxyz,2023-01-01T11:00:00+00:00"
//        );
//
//        List<String> mostActiveCookies = cookieContentAnalyzer.findMostActiveCookies(logLines, "2023-01-01");
//
//        assertEquals(1, mostActiveCookies.size());
//        assertTrue(mostActiveCookies.contains("Bxyzxyz"));
//        assertFalse(mostActiveCookies.contains("Axyzxyz"));
//
//    }
//}