package com.example.olio_harkkatyo;

public class WeightManagement {
    float weight;
    int appID = 2;
    String saveFile = "weight_history.txt";

    public static class IdealWeight extends WeightManagement {
        float idealWeight;

        public void setIdealWeight(float inputIdeal) {
            this.idealWeight = inputIdeal;
        }

        public float comparison(User user) {
            weight = user.getWeight();
            idealWeight = user.getIdealWeight();
            float difference = idealWeight - weight;
            return difference;
        }

    }

    public static class WeightChange extends WeightManagement {

        WeightChange(float weight) {
            this.weight = weight;
        }

        public void setWeight(float inputWeight) {
            this.weight = inputWeight;
        }

        public float getChange() { // Change between which exact points in time?
            float change = 0;
            return change;
        }

        public void createHistory() {

        }
    }

    public String getSaveFile(){
        return saveFile;
    }

    public int getAppID(){
        return appID;
    }
}
