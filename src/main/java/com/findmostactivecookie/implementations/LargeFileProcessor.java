package com.findmostactivecookie.implementations;

import org.w3c.dom.css.CSSValue;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class LargeFileProcessor {

    String filePath;
    String userDate;


    public LargeFileProcessor(String filePath, String userDate) {
        this.filePath = filePath;
        this.userDate = userDate;
    }

    private HashMap<String, Long> processlargeFiles() {
        HashMap<String, Long> cookieCount = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");
                String cookieId = parts[0];
                String date = parts[1].split("T")[0];

                if (userDate.equals(date)) {
                    cookieCount.put(cookieId, cookieCount.getOrDefault(cookieId, 0L) + 1);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return cookieCount;
    }

    public List<String> getMostActiveCookies(){

        HashMap<String, Long> cookieCount = processlargeFiles();

        ArrayList<String> cookieIds = new ArrayList<>();


        Long max = Collections.max(cookieCount.values());

        for(String cookieId : cookieCount.keySet()){
            if(cookieCount.get(cookieId) == max){
                cookieIds.add(cookieId);

            }
        }
        return cookieIds;

    }

}
