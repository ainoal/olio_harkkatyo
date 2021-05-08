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

        DataManager dm = DataManager.getInstance();
        dm.writeFile(fileName, inputText);
    }

    public float ActivityToGoal(User user) {
        float dailyAverage;
        float goal;
        float difference;
        float historicalActivity = 0;
        int i;

        //DataManager dm = DataManager.getInstance();
        //String file = dm.readFile(fileName);

        activityHistory = user.activityList;

        if (activityHistory.size() >= 1) {
            goal = (float) 5.0; // TODO Get goal from user info
            for(i=0; i<activityHistory.size(); i++ ) {
                historicalActivity += activityHistory.get(i);
            }
            dailyAverage = historicalActivity / activityHistory.size();
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

    public String getSaveFile(){
        return fileName;
    }

    public int getAppID(){
        return appID;
    }

}
