package com.example.olio_harkkatyo;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String username;
    private String name;
    private float weight;
    private float idealWeight;
    private float activityGoal;
    private int birthMonth;
    private int birthDay;
    private int birthYear;
    public ArrayList<Float> sleepList = new ArrayList<>();
    public ArrayList<Float> activityList = new ArrayList<>();
    public ArrayList<Float> weightList = new ArrayList<>();
    public ArrayList<String> co2List = new ArrayList<>();




    User(String username, String name, float fWeight, float fIdealWeight, float fActivityGoal, int birthMonth, int birthDay, int birthYear) {
        this.username = username;
        this.name = name;
        this.weight = fWeight;
        this.idealWeight = fIdealWeight;
        this.activityGoal = fActivityGoal;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.birthYear = birthYear;
    }


    public String getName() {
        return name;
    }

    public String getUsername(){return username; }

    public void setName(String name) {
        this.name = name;
    }

    public float getWeight() {
        return weight;
    }

    public void setCO2List(String line) {
        manipulateStringList(co2List, line);
    }

    public void setSleepList(float line) {
        manipulateFloatList(sleepList, line);
    }

    public void setWeightList(float weight) {
            manipulateFloatList(weightList, weight);
            this.weight = weight;
    }

    public void setActivityList(float line) {
        manipulateFloatList(activityList, line);
    }

    public float getIdealWeight() {
        return idealWeight;
    }

    public void setIdealWeight(float idealWeight) {
        this.idealWeight = idealWeight;
    }

    public float getActivityGoal() {
        return activityGoal;
    }

    public void setActivityGoal(float activityGoal) {
        this.activityGoal = activityGoal;
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

    public void manipulateFloatList(ArrayList<Float> list, float value){
        if (list.size()>365){                                                   //keeping list size at 1 year
            list.remove(0);
        }
        list.add(value);
    }

    public void manipulateStringList(ArrayList<String> list, String line){
        if (list.size()>365){
            list.remove(0);
        }
        list.add(line);
    }

    public ArrayList<String> getCO2List() {
        return co2List;
    }

    public ArrayList<Float> getWeightList() {
        return weightList;
    }

    public ArrayList<Float> getActivityList() {
        return activityList;
    }

    public ArrayList<Float> getSleepList() {
        return sleepList;
    }

    public ArrayList<Float> twoWeekHistory(ArrayList<Float> list){
        ArrayList<Float> history = new ArrayList<>();
        int bookmark = list.size();

        if(list.size() > 14) {
            for (int i = 0; i < 14; i++) {
                bookmark = bookmark-1;
                history.add(list.get(bookmark));
            }
        } else {
            for (int i = 0; i<list.size(); i++){
                history.add(list.get(i));
            }
        }
        return history;
    }
}
