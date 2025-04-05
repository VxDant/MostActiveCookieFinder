package com.findmostactivecookie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BruteForceMain {

    public static void main(String[] args){


        if(args.length < 4 || !Objects.equals(args[0], "-f") || !Objects.equals(args[2], "-d")){
            return;
        }

        String filename = args[1];
        String userGivenDate = args[3];

        Path file = Paths.get(filename);

        try{
            HashMap<String,Integer> cookieCountMap = new HashMap<>();

            List<String> lines =  Files.readAllLines(file);


            for(String line: lines){

                if(line.startsWith("cookie")){
                    continue;
                }
                String[] parts = line.split(",");

                String cookie = parts[0];
                String date = parts[1].split("T")[0];

                if(date.equals(userGivenDate)){
                    cookieCountMap.put(cookie,cookieCountMap.getOrDefault(cookie,0)+1);
                }

            }
            int max = Collections.max(cookieCountMap.values());

            for(Map.Entry<String, Integer> item : cookieCountMap.entrySet()){
                if(item.getValue() == max){
                    System.out.println(item.getKey());
                }
            }

        }
        catch (IOException e){
            System.out.println(e);
        }
    }
}
