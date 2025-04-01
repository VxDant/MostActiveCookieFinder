package com.findmostactivecookie.interfaces;

import java.util.List;

public interface CookieContentAnalyzer {
    List<String> findMostActiveCookies(List<String> logLines, String date);
}