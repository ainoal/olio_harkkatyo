package com.example.olio_harkkatyo;

public class User {
    private String name;
    private float weight;
    //Tänne tarvitaan vielä birthday

    User(String name, float weight) {
        this.name = name;
        this.weight = weight;
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
}
