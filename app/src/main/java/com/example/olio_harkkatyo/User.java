package com.example.olio_harkkatyo;

import android.content.Context;

import java.io.Serializable;

public class User implements Serializable {
    private Context appContext;
    private String name;
    private float weight;
    private float idealWeight;
    private int birthMonth;
    private int birthDay;
    private int birthYear;

    public void init(Context context) {
        if(appContext == null) {
            this.appContext = context;
        }
    }

    private static User U = new User(null, 0,0,0,0,0);

    User(String name, float fWeight, float fIdealWeight, int birthMonth, int birthDay, int birthYear) {
        this.name = name;
        this.weight = fWeight;
        this.idealWeight = fIdealWeight;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.birthYear = birthYear;
    }

    public static synchronized User getInstance() { return U; }

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
