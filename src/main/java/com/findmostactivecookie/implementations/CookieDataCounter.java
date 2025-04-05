package com.findmostactivecookie.implementations;

import java.util.HashMap;
import java.util.List;

public class CookieDataCounter {

    public CookieDataCounter(List<String[]> csvData, String userGivenDate){
        HashMap<String, Integer> mapCount = new HashMap<>();

        for(String[] row: csvData){

            String date  = row[1].split("T")[0];
            String cookie = row[0];


            if(date.equals(userGivenDate)){
                mapCount.put(cookie,mapCount.getOrDefault(cookie,0)+1);
            }


        }
        System.out.println(mapCount);
    }
}
