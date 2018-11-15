package com.codechallenge;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    private static final String SCHEDULES = "schedules.csv";
    private static LinkedHashMap<String, String> hourAvailability;

    public static void main(String[] args) throws Exception{
        setDaySchedule();

        String fileLine = "";
        InputStream fileInputStream = Main.class.getResourceAsStream(SCHEDULES);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

        while((fileLine = bufferedReader.readLine()) != null){
            String name = fileLine.substring(0, fileLine.indexOf(','));
            String meetingTimes = fileLine.substring(fileLine.indexOf(','));

            for (Map.Entry<String, String> entry : hourAvailability.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();

                if (!meetingTimes.contains(key)){
                    value = value + "|" + name;
                    hourAvailability.put(key, value);
                }
            }
        }

    }

    private static void setDaySchedule(){
        hourAvailability = new LinkedHashMap<>();
        String timeOfDay = "12:00";
        String timeIndicator = "AM";

        for(int i=0; i < 48; i ++){
            hourAvailability.put(timeOfDay  + timeIndicator, "");

            if(timeOfDay.equals("11:30")){
                timeIndicator = "PM";
            }

            if(timeOfDay.endsWith("00")){
                timeOfDay = timeOfDay.split(":")[0] + ":30";
            }else{
                int hour = Integer.parseInt(timeOfDay.split(":")[0]);
                hour++;
                if(hour == 13){
                    hour = 1;
                }
                timeOfDay = hour + ":00";
            }
        }
    }
}
