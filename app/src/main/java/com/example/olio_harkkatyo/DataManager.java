package com.example.olio_harkkatyo;

import android.content.Context;

import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class DataManager {
    private Context appContext;
    private static DataManager dm= new DataManager();
    private final String userData = "accounts.txt";

    public void init(Context context) {
        if(appContext == null) {
            this.appContext = context;
        }
    }

    private Context getContext() {
        return appContext;
    }

    public static synchronized DataManager getInstance() { return dm; }

    public String readFile(String fileName) {
        appContext = getInstance().getContext();

        String s;
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

    public void saveUser(String fileName, Object user) { //Saves serialized user
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

    public Object loadUsers(String fileName) { //Loads serialized user
        Object user = null;
        appContext = getInstance().getContext();
        try {
            ObjectInputStream ois = new ObjectInputStream(appContext.openFileInput(fileName+".ser"));

            user = ois.readObject();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
          }
        return user;
    }


    public void saveAccount(Account account){
        String psw = account.getPassword();
        String usr = account.getUsername();

        appContext = getInstance().getContext();
        try {
           OutputStreamWriter osw = new OutputStreamWriter(appContext.openFileOutput(userData, Context.MODE_APPEND));
            osw.write(usr+":user&pass:"+psw+"\n");
            osw.close();
        } catch (IOException e) {
            Log.e("IOException", "IOException while writing to a file.");
        }
    }

    public ArrayList<String> getAccountData() {                 //loads all registered accounts and puts on list
        ArrayList<String> accounts = new ArrayList<>();

        String userFile = readFile(userData);
        Scanner sc = new Scanner(userFile);

        while(sc.hasNextLine()){
            String line = sc.nextLine();
            accounts.add(line);
        }

        return accounts;
    }
}
