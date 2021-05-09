package com.example.olio_harkkatyo;

import java.util.ArrayList;

public class PhysicalActivity {
    int appID = 3;

    public ArrayList<Float> activityHistory = new ArrayList<>();

    public PhysicalActivity(){}

    /* Calculate how much user's average daily activity during the past 14 days
    * differs from the physical activity goal that they have set  */
    public float activityToGoal(User user) {
        float dailyAverage;
        float goal;
        float difference;

        dailyAverage = averageActivity(user);

        if (dailyAverage >= 0) {
            goal = user.getActivityGoal();
            difference = goal - dailyAverage;
        }  else {
            /* If ArrayList user.ActivityList<> is empty, return an impossible difference
            * as a sign that the user has not input any data yet */
            difference = 1000;
        }

        return difference;
    }

    /* Calculate 14-day average activity */
    public float averageActivity(User user) {
        float historicalActivity = 0;
        float dailyAverage;
        int i;

        activityHistory = user.twoWeekHistory(user.getActivityList());

        if (activityHistory.size() >= 1) {
            for(i=0; i<activityHistory.size(); i++ ) {
                historicalActivity += activityHistory.get(i);
            }
            dailyAverage = historicalActivity / activityHistory.size();
        }  else {
            /* If ArrayList user.ActivityList<> is empty, return an impossible difference
             * as a sign that the user has not input any data yet */
            dailyAverage = (float) -1;
        }

        return dailyAverage;
    }

    public int getAppID(){
        return appID;
    }

}
