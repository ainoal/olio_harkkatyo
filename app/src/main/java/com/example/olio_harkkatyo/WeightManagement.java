package com.example.olio_harkkatyo;

public class WeightManagement {
    float weight;

    public class IdealWeight extends WeightManagement {
        float idealWeight;

        public void setIdealWeight(float inputIdeal) {
            idealWeight = inputIdeal;
        }

        public float comparison() {
            float difference = idealWeight - weight;
            return difference;
        }

    }

    public class WeightChange extends WeightManagement {

        public void setWeight(float inputWeight) {
            weight = inputWeight;
        }

        public float getChange() { // Change between which exact points in time?
            float change = 0;
            return change;
        }

        public void createHistory() {

        }
    }
}
