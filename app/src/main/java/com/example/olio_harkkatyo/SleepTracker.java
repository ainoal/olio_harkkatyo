package com.example.olio_harkkatyo;

import android.content.Intent;
import android.view.View;

import java.util.ArrayList;
import java.util.Scanner;

public class SleepTracker {
    String evaluation = "";
    String saveFile = "sleep_history.txt";

    public ArrayList<Float> sleepHistory = new ArrayList<>();
    public float twoWeekAverage;

    public SleepTracker(){



    }

    public void setHistory(Float sleepTime){ //adds latest value to a file
        System.out.println("Slept: "+sleepTime);
        DataManager dm = DataManager.getInstance();
        dm.writeFile(saveFile, sleepTime.toString());
        //TODO aseta slider aktiviteettiin ja syötä luku tänne

        getHistory();
        avgSleepTime();
        System.out.println("Average sleep time of last 14 days: "+twoWeekAverage);
        compareSleepTimes();


    }

    public void getHistory(){              //gets n previous values
        DataManager dm = DataManager.getInstance();
        String history_string = dm.readFile("sleep_history.txt");

        Scanner scanner = new Scanner(history_string);

        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            sleepHistory.add(Float.parseFloat(line));
            if (sleepHistory.size() > 14){
                sleepHistory.remove(0);
            }
        }
    }

    public void avgSleepTime(){
        float total = 0;
        for (int i = 0; i < sleepHistory.size(); i++){
            total = total+sleepHistory.get(i);
        }
        twoWeekAverage = total / sleepHistory.size();
        //TODO ??14?? latest values from sleepHistory and calculate average

    }

    public void compareSleepTimes(){
        //TODO what is reasonable deviation?
        int under_avg = 0;
        int over_avg = 0;
        for (int i = 0; i < sleepHistory.size(); i++){
            if (sleepHistory.get(i) > twoWeekAverage+1){
                over_avg++;
            } if (sleepHistory.get(i) < twoWeekAverage-1){
                under_avg++;
            }
        }
        //TODO set UI and textbox for messages
        if ((over_avg > 3) || (under_avg > 3) || ((over_avg+under_avg) > 3)){
            evaluation = "Sleep irregular";
            System.out.println(evaluation);

        } else {
            evaluation = "Sleep under control!";
            System.out.println(evaluation);}
    }
}