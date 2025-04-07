package com.findmostactivecookie.Entities;

import jakarta.persistence.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "cookie_logs")
public class CookieLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cookie;

    private OffsetDateTime timestamp;

    public CookieLog(){

    }

    public CookieLog(String cookie, OffsetDateTime timestamp) {
        this.cookie = cookie;
        this.timestamp = timestamp;
    }

    public String getCookie() { return cookie; }
    public void setCookie(String cookie) { this.cookie = cookie; }

    public OffsetDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(OffsetDateTime timestamp) { this.timestamp = timestamp; }

    @Override
    public String toString() {
        return "CookieLog{" +
                "cookie='" + cookie + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }




//    public static void main(String[] args) throws IOException {
//
//        String filepath = "src/main/java/com/findmostactivecookie/log/cookie_log.csv";
//
//        Stream<String> lines = Files.lines(Paths.get(filepath));
//
//        List<String[]> cookieLogs = new ArrayList<>();
//
//        cookieLogs = lines.map(x -> x.split(",")).collect(Collectors.toList());
//
//        System.out.println(cookieLogs);
//
//
//
//
//    }
}