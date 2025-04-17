package com.findmostactivecookie.interfaces;

import java.util.List;
import java.util.stream.Stream;

public interface CookieContentAnalyzer {
    List<String> findMostActiveCookies(Stream<String> logLines, String date);
}