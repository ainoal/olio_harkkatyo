package com.example.olio_harkkatyo;

public class WeightManagement {
    float weight;

    public static class IdealWeight extends WeightManagement {
        float idealWeight;

        IdealWeight(float weight, float idealWeight) {
            this.weight = weight;
            this.idealWeight = idealWeight;
        }

        public void setIdealWeight(float inputIdeal) {
            this.idealWeight = inputIdeal;
        }

        public float comparison() {
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
}
