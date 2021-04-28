package com.example.olio_harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserProfile extends AppCompatActivity {

    private EditText userName;
    private EditText userWeight;
    private Button applyInfo;

    public static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        this.setTitle("Profile information");

        userName = findViewById(R.id.etUserName);
        userWeight = findViewById(R.id.etUserWeight);
        applyInfo = findViewById(R.id.btnApplyInfo);
        applyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = userName.getText().toString();
                float weight = Float.valueOf(userWeight.getText().toString());

                user = new User(name, weight);
                //startActivity( new Intent(UserProfile.this, MainActivity.class)); ??
                Toast.makeText(UserProfile.this,"User information applied successfully!", Toast.LENGTH_SHORT).show();

            }

        });
    }
}
