package com.example.olio_harkkatyo;

import android.content.Context;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class DataManager {

    public void writeFile(View v, Context context, String fileName, String text) {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            osw.write(text);
            osw.close();
        } catch (IOException e) {
            Log.e("IOException", "IOException while writing to a file.");
        }
    }

    public void readFile(View v, Context context, String fileName) {
        try {
            InputStream is = context.openFileInput(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String s = "";
            String fileContent = "";

            while ((s = br.readLine()) != null) {
                fileContent = fileContent.concat(s);
            }

            System.out.println(fileContent);
            is.close();

        } catch (IOException e) {
            Log.e("IOException", "IOException while reading a file.");
        } finally {
            System.out.println("READ");
        }
    }
}