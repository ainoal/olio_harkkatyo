package com.example.olio_harkkatyo;

import java.util.ArrayList;

public class WeightManagement {
    float weight;
    float idealWeight;
    int appID = 2;

    public ArrayList<Float> weightHistory = new ArrayList<>();

    public void setIdealWeight(float inputIdeal) {
        this.idealWeight = inputIdeal;
    }

    public float comparison(User user) {
        weight = user.getWeight();
        idealWeight = user.getIdealWeight();
        float difference = weight - idealWeight;

        System.out.println("Weight:" + weight);
        System.out.println("Ideal:" + idealWeight);
        System.out.println("DIFF:" + difference);
        return difference;
    }

    /* Get user's weight change during the last 14 days */
    public float getChange(User user) {
        float change;
        weightHistory = user.twoWeekHistory(user.getWeightList());

        if (weightHistory.size() > 1) {
            float first = weightHistory.get(0);
            float last = weightHistory.get(weightHistory.size() - 1);
            change = last - first;
        } else  {
            /* Nothing to compare here since the user has only input their weight
            * 0 or 1 times. Return an impossible change as sign of this. */
            change = 1000;
        }

        return change;
    }

    public int getAppID(){
        return appID;
    }
}
