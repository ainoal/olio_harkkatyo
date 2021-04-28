package com.example.olio_harkkatyo;

import android.content.Context;
import android.view.View;

import java.util.Scanner;

public class PhysicalActivity {
    String fileName = "testfile.txt";
    String goalFile = "goalfile.txt";

    public void changeGoal(float newGoal) {
        String inputText = Float.toString(newGoal);

        DataManager dm = DataManager.getInstance();
        dm.writeFile(goalFile, inputText);
    }

    public void SaveDaily(float dailyActivity) {
        String inputText = Float.toString(dailyActivity);

        DataManager dm = DataManager.getInstance();
        dm.writeFile(fileName, inputText);
    }

    public float ActivityToGoal() {       // float goal parametrinä?
        float dailyAverage;
        float goal;
        float difference = 0;
        float historicalActivity = 0;
        String line;
        Integer lineCount = 0;
        String inputText;

        DataManager dm = DataManager.getInstance();

        String file = dm.readFile(fileName);
        String file2 = dm.readFile(goalFile);

        Scanner avgScanner = new Scanner(file);

        while (avgScanner.hasNextLine()){
            line = avgScanner.nextLine();
            historicalActivity += Float.parseFloat(line.trim());
            lineCount++;
            }

        if (lineCount != 0) {   // If there's sleep activity data in the file
            dailyAverage = historicalActivity / lineCount;
            goal = Float.parseFloat(file2.trim());
            difference = goal - dailyAverage;
        } else {
            return 1000;  // return an impossible number as a sign that the user hasn't input any data
        }

        inputText = Float.toString(difference);
        System.out.print("DIFFERENCE: ");
        System.out.println(inputText);

        return difference;
    }
}
