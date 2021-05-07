package com.example.olio_harkkatyo;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class DataManager {
    private Context appContext;
    private ArrayList<User> users = new ArrayList<>();
    private static DataManager dm= new DataManager();
    private String userData = "accounts.txt";

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
                fileContent = fileContent.concat(s+"\n");
            }

            is.close();

        } catch (IOException e) {
            Log.e("IOException", "IOException while reading a file.");
        }

        return fileContent;
    }

    public void saveUser(String fileName, Object user) { //tänne siis lista usereista, jotka kirjoitetaan tiedostoon?
        appContext = getInstance().getContext();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(appContext.openFileOutput(fileName+".ser", Context.MODE_PRIVATE));
            oos.writeObject(user);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object loadUsers(String fileName) { //tänne palautukseen ArrayList, jossa kaikki userit?
        Object user = null;
        appContext = getInstance().getContext();
        try {
            ObjectInputStream ois = new ObjectInputStream(appContext.openFileInput(fileName));

            user = ois.readObject();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
          }
        return user;
    }

    //TODO pitää varmaan tehdä erillinen luku ja kirjoitus käyttäjä+salasana tiedostolle.

    public void saveAccount(Account account){
        String psw = account.getPassword();
        String usr = account.getUsername();
        appContext = getInstance().getContext();

        try {
            OutputStreamWriter osw = new OutputStreamWriter(appContext.openFileOutput(userData, Context.MODE_APPEND));
            osw.write(usr+":"+psw + "\n");
            osw.close();
        } catch (IOException e) {
            Log.e("IOException", "IOException while writing to a file.");
        }
    }

}
