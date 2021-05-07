package com.example.olio_harkkatyo;

import java.util.ArrayList;

public class WeightManagement {
    float weight;
    float idealWeight;
    int appID = 2;
    String saveFile = "weight_history.txt";

    public ArrayList<Float> weightHistory = new ArrayList<>();

    public void setIdealWeight(float inputIdeal) {
        this.idealWeight = inputIdeal;
    }

    public float comparison(User user) {
        weight = user.getWeight();
        idealWeight = user.getIdealWeight();
        float difference = idealWeight - weight;

        System.out.println("Weight:" + weight);
        System.out.println("Ideal:" + idealWeight);
        System.out.println("DIFF:" + difference);
        return difference;
    }

    public float getChange(User user) { // Change during last 14 days
        float change = 0;
        weightHistory = user.weightList;

        if (weightHistory.size() > 1) {
            float first = weightHistory.get(0);
            float last = weightHistory.get(weightHistory.size() - 1);
            change = last - first;
        } else if (weightHistory.size() == 1) {
            // Nothing to compare here since the user has input only 1 weight
        } else {
            // Empty ArrayList user.WeightList<>
        }

        return change;
    }

    public void createHistory() {

    }

    public String getSaveFile(){
        return saveFile;
    }

    public int getAppID(){
        return appID;
    }
}
