package com.example.olio_harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Context context = MainActivity.this;
    private EditText username;
    private EditText password;
    private Button login;
    private Button signUp;
    boolean confirmed = false;
    SleepTracker slt = new SleepTracker(); //luonti testausta varten, siirretään varmaan toiseen aktiviteettiin

    public MainActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        login = findViewById(R.id.btnLogin);
        signUp = findViewById(R.id.btnSignUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateAccountActivity.class));
            }
        });



        DataManager dm = DataManager.getInstance();
        dm.init(context);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputUsername = username.getText().toString();
                String inputPassword = password.getText().toString();
                if(inputUsername.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill in all the required fields.", Toast.LENGTH_SHORT).show();
                } else {
                    confirmed = confirm(inputUsername,inputPassword);
                    if(!confirmed){
                        Toast.makeText(MainActivity.this, "Invalid credentials!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Login succesful!", Toast.LENGTH_SHORT).show();
                        //Code for new activity
                    }
            }
        }
    });

    }

    public void co2Activity (View v){
        Intent intent = new Intent(MainActivity.this, co2_calculator.class);
        startActivity(intent);
    }

    public void sleepActivity (View v){
        slt.setHistory(7.5f);
    }

    private boolean confirm(String username, String password){

        if(CreateAccountActivity.account != null){
            if(username.equals(CreateAccountActivity.account.getUsername()) && password.equals(CreateAccountActivity.account.getPassword())){
                return true;
            }
        }


        return false;
    }
}