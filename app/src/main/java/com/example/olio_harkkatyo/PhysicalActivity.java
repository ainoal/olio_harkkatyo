package com.example.olio_harkkatyo;

import java.util.ArrayList;
import java.util.Scanner;

public class PhysicalActivity {
    String fileName = "testfile0.txt";
    int appID = 3;

    public ArrayList<Float> activityHistory = new ArrayList<>();

    public PhysicalActivity(){}

    public void changeGoal(float newGoal, User user) {
        String inputText = Float.toString(newGoal);

        // TODO User goal --> inputText
    }

    public void saveDaily(float dailyActivity, User user) {
        String inputText = Float.toString(dailyActivity);


    }

    public float activityToGoal(User user) {
        float dailyAverage;
        float goal;
        float difference;

        //DataManager dm = DataManager.getInstance();
        //String file = dm.readFile(fileName);

        dailyAverage = averageActivity(user);

        if (dailyAverage >= 0) {
            goal = 5;//user.getActivityGoal();
            difference = goal - dailyAverage;
        }  else {
            //  ArrayList user.ActivityList<> is empty
            difference = 1000; // return an impossible number as a sign that the user hasn't input any data
        }

        String inputText = Float.toString(difference);
        System.out.print("DIFFERENCE: ");
        System.out.println(inputText);

        return difference;
    }

    public float averageActivity(User user) {
        float historicalActivity = 0;
        float dailyAverage;
        int i;

        activityHistory = user.activityList;

        if (activityHistory.size() >= 1) {
            for(i=0; i<activityHistory.size(); i++ ) {
                historicalActivity += activityHistory.get(i);
            }
            dailyAverage = historicalActivity / activityHistory.size();
        }  else {
            //  ArrayList user.ActivityList<> is empty
            dailyAverage = (float) -1; // return an impossible number as a sign that the user hasn't input any data
        }

        return dailyAverage;
    }

    public String getSaveFile(){
        return fileName;
    }

    public int getAppID(){
        return appID;
    }

}
