package com.example.olio_harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class UserProfile extends AppCompatActivity {

    private EditText userName;
    private EditText userWeight;
    private Button applyInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        this.setTitle("Profile information");
    }
}