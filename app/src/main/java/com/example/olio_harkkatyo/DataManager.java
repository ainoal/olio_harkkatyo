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

    public void writeFile(Context context, String fileName, String text) {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            osw.write(text);
            osw.close();
        } catch (IOException e) {
            Log.e("IOException", "IOException while writing to a file.");
        }
    }

    public void appendFile(Context context, String fileName, String text) {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_APPEND));
            osw.write(text);
            osw.close();
        } catch (IOException e) {
            Log.e("IOException", "IOException while writing to a file.");
        }
    }

    public String readFile(Context context, String fileName) {
        String s = "";
        String fileContent = "";

        try {
            InputStream is = context.openFileInput(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

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

        return fileContent;
    }
}
