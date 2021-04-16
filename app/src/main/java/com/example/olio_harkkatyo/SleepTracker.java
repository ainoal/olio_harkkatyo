package com.example.olio_harkkatyo;

import java.util.ArrayList;

public class SleepTracker {

    public Float sleepTime;
    public ArrayList<Float> sleepHistory = new ArrayList<>();


    public void setHistory(Float sleepTime){ //adds latest value to a file

        //TODO use data manager to add future slider value to file
    }

    public ArrayList getHistory(){              //gets n previous values
        float previousSleepTime = 0;

        //TODO use data manager to fetch values from file, append to sleepHistory


        return sleepHistory;
    }

    public float avgSleepTime(){
        float monthlyAverage = 0;

        //TODO get up to 30 latest values from sleepHistory and calculate average

        return monthlyAverage;
    }

    public String compareSleepTimes(){
        String evaluation = "";

        //TODO compare times from last ??week?? and inform frequent irregularities


        return evaluation;
    }


}