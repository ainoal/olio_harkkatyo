package com.example.olio_harkkatyo;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String name;
    private float weight;
    private float idealWeight;
    private int birthMonth;
    private int birthDay;
    private int birthYear;




    User(String name, float fWeight, float fIdealWeight, int birthMonth, int birthDay, int birthYear) {
        this.name = name;
        this.weight = fWeight;
        this.idealWeight = fIdealWeight;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.birthYear = birthYear;

        ArrayList<Float> sleepList = new ArrayList<>();
        ArrayList<Float> activityList = new ArrayList<>();
        ArrayList<Float> weightList = new ArrayList<>();
        ArrayList<String> co2List = new ArrayList<>();


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
        /*weightList.add(weight);*/
    }

    public float getIdealWeight() {
        return idealWeight;
    }

    public void setIdealWeight(float idealWeight) {
        this.idealWeight = idealWeight;
    }

    public int getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(int birthMonth) {
        this.birthMonth = birthMonth;
    }

    public int getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(int birthDay) {
        this.birthDay = birthDay;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}
