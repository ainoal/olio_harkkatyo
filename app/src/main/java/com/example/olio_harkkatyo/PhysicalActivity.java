package com.example.olio_harkkatyo;

import java.util.Scanner;

public class PhysicalActivity {
    String fileName = "testfile0.txt";
    int appID = 3;

    public PhysicalActivity(){}

    public void changeGoal(float newGoal) {
        String inputText = Float.toString(newGoal);

        // TODO User goal --> inputText
    }

    public void saveDaily(float dailyActivity) {
        String inputText = Float.toString(dailyActivity);

        DataManager dm = DataManager.getInstance();
        dm.writeFile(fileName, inputText);
    }

    public float ActivityToGoal() {
        float dailyAverage;
        float goal;
        float difference = 0;
        float historicalActivity = 0;
        String line;
        Integer lineCount = 0;
        String inputText;

        DataManager dm = DataManager.getInstance();

        String file = dm.readFile(fileName);

        Scanner avgScanner = new Scanner(file);

        while (avgScanner.hasNextLine()){
            line = avgScanner.nextLine();
            historicalActivity += Float.parseFloat(line.trim());
            lineCount++;
            }

        if (lineCount != 0) {   // If there's sleep activity data in the file
            dailyAverage = historicalActivity / lineCount;
            goal = (float) 5.0; // TODO Get goal from user info
            difference = goal - dailyAverage;
        } else {
            return 1000;  // return an impossible number as a sign that the user hasn't input any data
        }

        inputText = Float.toString(difference);
        System.out.print("DIFFERENCE: ");
        System.out.println(inputText);

        return difference;
    }

    public String getSaveFile(){
        return fileName;
    }

    public int getAppID(){
        return appID;
    }

}
