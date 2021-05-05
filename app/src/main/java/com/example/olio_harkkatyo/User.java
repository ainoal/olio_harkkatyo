package com.example.olio_harkkatyo;

public class User {
    private String name;
    private float weight;
    private float idealWeight;

    //Tänne tarvitaan vielä birthday

    User(String name, float weight, float idealWeight) {
        this.name = name;
        this.weight = weight;
        this.idealWeight = idealWeight;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getIdealWeight() {
        return idealWeight;
    }

    public void setIdealWeight(float idealWeight) {
        this.idealWeight = idealWeight;
    }
}
