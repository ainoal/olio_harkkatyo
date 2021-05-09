package com.example.olio_harkkatyo;

import java.util.ArrayList;

public class Account {
    private String username;
    private String password;
    private ArrayList<String> UserList = new ArrayList<>();

    Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        password = password;
    }

    public void setUserList(ArrayList<String> users){ UserList = users; }

    public ArrayList<String> getUserList(){ return UserList; }
}

