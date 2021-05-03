package com.example.olio_harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import org.w3c.dom.ls.LSOutput;

public class MainActivity extends AppCompatActivity {
    Context context = MainActivity.this;
    private EditText username;
    private EditText password;
    private Button login;
    private Button signUp;
    boolean confirmed = false;
    SleepTracker slt = new SleepTracker(); //luonti testausta varten, siirretään varmaan toiseen aktiviteettiin

    private Button profileTester; //Testiä varten

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
        profileTester = findViewById(R.id.btnProfileTester);

        profileTester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UserProfile.class));
            }
        });

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
                        Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                        mainView(); // go to the main app view
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
            return username.equals(CreateAccountActivity.account.getUsername()) && password.equals(CreateAccountActivity.account.getPassword());
        }

        return false;
    }

    public void mainView() {
        SeekBar seekbarSleep;
        SeekBar seekbarActivity;
        SeekBar seekbarWeight;
        Button buttonWeight;
        Button buttonActivity;
        Button buttonCO2;

        setContentView(R.layout.activity_mainview);

        seekbarSleep = findViewById(R.id.seekBarSleep);
        seekbarActivity = findViewById(R.id.seekBarActivity);
        seekbarWeight = findViewById(R.id.seekBarWeight);
        buttonWeight = findViewById(R.id.buttonWeight);
        buttonActivity = findViewById(R.id.buttonActivity);
        buttonCO2 = findViewById(R.id.buttonCO2);

        seekbarSleep.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                System.out.println("SeekbarSleep: progress changed");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekbarActivity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                System.out.println("SeekbarActivity: progress changed");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekbarWeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                System.out.println("SeekbarWeight: progress changed");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        buttonWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("ButtonSleep: OnClickListener successful");
                // -> Weight drawer?
            }
        });

        buttonActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("ButtonActivity: OnClickListener successful");
                // TODO physical activity view where the user can display their previous physical activity
            }
        });

        buttonCO2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                co2Activity(v);
            }
        });
    }
}