package com.example.olio_harkkatyo;

import android.content.Context;
import android.view.View;

public class PhysicalActivity {
    String fileName = "testfile.txt";
    String goalFile = "goalfile.txt";

    public void changeGoal(float newGoal, DataManager dm) {
        String inputText = Float.toString(newGoal);

        dm.writeFile(goalFile, inputText);
    }

    public void SaveDaily(DataManager dm) {
        String inputText = "DataManager\nTiedoston luku ja kirjoitus\n:)"; // Testi

        dm.writeFile(fileName, inputText);
    }

    public Integer ActivityToGoal(DataManager dm) {
        Integer dailyAverage;
        Integer difference = 0;
        Integer lineCount = 0;
        String line;
        float historicalActivity =  0;
        float goal;

        String file = dm.readFile(fileName);
        String file2 = dm.readFile(goalFile);

        /*
        while (käydään läpi rivit) {
            historicalActivity += Float.parseFloat(line.trim());
        }
        dailyAverage = historicalActivity / lineCount;

        goal = Float.parseFloat(file2.trim());

        difference = goal - dailyAverage;
        System.out.println(difference); // testi
         */

        return difference;
    }
}
