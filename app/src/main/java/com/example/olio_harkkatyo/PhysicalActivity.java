package com.example.olio_harkkatyo;

import android.content.Context;
import android.view.View;

public class PhysicalActivity {
    View v;
    Context context = v.getContext(); // ?? joku fiksu tapa kontekstille
    String fileName = "testfile.txt";
    String goalFile = "goalfile.txt";

    public void changeGoal(float newGoal) { // Kirjoitetaanko tiedostoon vai tallennetaanko muuttujaan?
        String inputText = Float.toString(newGoal);
        DataManager dm = new DataManager();

        dm.writeFile(context, goalFile, inputText);
    }

    public void SaveDaily() {
        String inputText = "DataManager\nTiedoston luku ja kirjoitus\n:)"; // Testi
        DataManager dm = new DataManager();

        dm.appendFile(context, fileName, inputText);
    }

    public Integer ActivityToGoal() {
        Integer dailyAverage;
        Integer difference = 0;
        Integer lineCount = 0;
        String line;
        float historicalActivity =  0;
        float goal;

        DataManager dm = new DataManager();

        String file = dm.readFile(context, fileName);
        String file2 = dm.readFile(context, goalFile);

        /*
        while (käydään läpi rivit) {    // DataManager palauttaisi ArrayListin? Vai Stringin niin kuin nyt?
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
