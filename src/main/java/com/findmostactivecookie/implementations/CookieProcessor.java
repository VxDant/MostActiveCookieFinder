package com.findmostactivecookie.implementations;

import com.findmostactivecookie.Entities.CookieLog;
import com.findmostactivecookie.interfaces.CookieContentAnalyzer;
import com.findmostactivecookie.interfaces.FileReader;
import com.findmostactivecookie.repositories.CookieLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class CookieProcessor {
    private final FileReader fileReader;
    private final CookieContentAnalyzer cookieContentAnalyzer;
    private final CookieLogRepository cookieLogRepository;

    @Autowired
    public CookieProcessor(FileReader fileReader, CookieContentAnalyzer cookieContentAnalyzer, CookieLogRepository cookieLogRepository) {
        this.fileReader = fileReader;
        this.cookieContentAnalyzer = cookieContentAnalyzer;
        this.cookieLogRepository = cookieLogRepository;
    }

    public void process(String filename, String date) {
        List<String> logLines = readLogFile(filename);
        List<String> mostActiveCookies = analyzeCookies(logLines, date);
        displayResults(mostActiveCookies);
    }

    private List<String> readLogFile(String filename) {
        try {
            return fileReader.readLogFile(filename);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    private List<String> analyzeCookies(List<String> logLines, String date) {
        return cookieContentAnalyzer.findMostActiveCookies(logLines, date);
    }

    private void displayResults(List<String> mostActiveCookies) {
        mostActiveCookies.forEach(System.out::println);

        List<CookieLog> list1 = cookieLogRepository.findAll();

        System.out.println(list1);
    }
}
