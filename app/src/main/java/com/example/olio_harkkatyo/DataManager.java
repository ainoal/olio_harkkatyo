package com.example.olio_harkkatyo;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class DataManager {
    private Context appContext;
    private static DataManager dm= new DataManager();

    public void init(Context context) {
        if(appContext == null) {
            this.appContext = context;
        }
    }

    private Context getContext() {
        return appContext;
    }

    public static synchronized DataManager getInstance() { return dm; }

    public void writeFile(String fileName, String text) {
        appContext = getInstance().getContext();

        try {
            OutputStreamWriter osw = new OutputStreamWriter(appContext.openFileOutput(fileName, Context.MODE_APPEND));
            osw.write(text + "\n");
            osw.close();
        } catch (IOException e) {
            Log.e("IOException", "IOException while writing to a file.");
        }
    }

    public String readFile(String fileName) {
        appContext = getInstance().getContext();

        String s = "";
        String fileContent = "";

        try {
            InputStream is = appContext.openFileInput(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            while ((s = br.readLine()) != null) {
                fileContent = fileContent.concat(s);
            }

            is.close();

        } catch (IOException e) {
            Log.e("IOException", "IOException while reading a file.");
        }

        return fileContent;
    }
}
