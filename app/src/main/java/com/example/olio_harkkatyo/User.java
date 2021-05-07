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
    public ArrayList<Float> sleepList = new ArrayList<>();
    public ArrayList<Float> activityList = new ArrayList<>();
    public ArrayList<Float> weightList = new ArrayList<>();
    public ArrayList<String> co2List = new ArrayList<>();




    User(String name, float fWeight, float fIdealWeight, int birthMonth, int birthDay, int birthYear) {
        this.name = name;
        this.weight = fWeight;
        this.idealWeight = fIdealWeight;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.birthYear = birthYear;






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

    public ArrayList getCO2List() {
        return co2List;
    }

    public void setWeight(float weight) {
        manipulateFloatList(weightList, weight);
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

    public void manipulateFloatList(ArrayList list, float value){
        if (list.size()>365){
            list.remove(0);
        }
        while(list.size()<365) {
            list.add(value+1);
            list.add(value-4);
        }
        for(int i=0; i<list.size(); i++){
            System.out.println("Listan arvot: "+list.get(i)+"\n");
        }


    }
}
