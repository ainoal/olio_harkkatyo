package com.example.olio_harkkatyo;

import java.util.ArrayList;

public class SleepTracker {
    String evaluation = "";
    int appID = 4;              //direction to inform drawing app

    public ArrayList<Float> sleepHistory = new ArrayList<>();
    public float twoWeekAverage;

    public SleepTracker(){ }


       public String compareSleepTimes(String userName){
        int under_avg = 0;
        int over_avg = 0;
        float total = 0;
        float days = 0;
        DataManager dm = DataManager.getInstance();
        User user = (User) dm.loadUsers(userName);
        sleepHistory = user.twoWeekHistory(user.getSleepList());

        for (int j = 0; j <sleepHistory.size(); j++){           //gets total time slept ni last 14 days
            total = total+sleepHistory.get(j);
            days++;
        }
        twoWeekAverage = total/days;

        for (int i = 0; i < sleepHistory.size(); i++){          //see if times vary greatly
            if (sleepHistory.get(i) > twoWeekAverage+2){
                over_avg++;
            } if (sleepHistory.get(i) < twoWeekAverage-2){
                under_avg++;
            }
        }
        if ((over_avg > 3) || (under_avg > 3) || ((over_avg+under_avg) > 3)){
            evaluation = "Sleep irregular!";
            System.out.println(evaluation);

        } else {
            evaluation = "Sleep under control!";
            System.out.println(evaluation);}

        return evaluation;
    }

    /* Calculate 14-day average sleep */
    public float averageSleep(User user) {
        float sleepCount= 0;
        float dailyAverage;
        int i;

        sleepHistory = user.twoWeekHistory(user.getActivityList());

        if (sleepHistory.size() >= 1) {
            for(i=0; i<sleepHistory.size(); i++ ) {
                sleepCount += sleepHistory.get(i);
            }
            dailyAverage = sleepCount / sleepHistory.size();
        }  else {
            /* If ArrayList user.SleepList<> is empty, return an impossible difference
             * as a sign that the user has not input any data yet */
            dailyAverage = (float) -1;
        }

        return dailyAverage;
    }

    public int getAppID(){
        return appID;
    }
}