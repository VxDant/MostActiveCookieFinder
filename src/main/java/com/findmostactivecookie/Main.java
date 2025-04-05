package com.findmostactivecookie;
import com.findmostactivecookie.implementations.CookieProcessor;

import com.findmostactivecookie.implementations.LargeFileProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        if (args.length < 4 || !"-f".equals(args[0]) || !"-d".equals(args[2])) {
            System.out.println("Usage: java com.findmostactivecookie.Main -f <filename> -d <date>");
            return;
        }

        String filename = args[1];
        String date = args[3];

        LargeFileProcessor largeFileProcessor = new LargeFileProcessor(filename,date);

        System.out.println(largeFileProcessor.getMostActiveCookies());

//        ApplicationContext context = SpringApplication.run(Main.class, args);
//        CookieProcessor cookieProcessor = context.getBean(CookieProcessor.class);
//
//        cookieProcessor.process(filename, date);

    }
}