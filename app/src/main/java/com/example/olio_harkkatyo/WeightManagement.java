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
        return difference;
    }

    public void setWeight(float inputWeight) {
        this.weight = inputWeight;
    }

    public float getChange() { // Change during last 14 days
        float change = 0;

        float first = weightHistory.get(0);
        float last = weightHistory.get(weightHistory.size() - 1);

        change = last - first;
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
