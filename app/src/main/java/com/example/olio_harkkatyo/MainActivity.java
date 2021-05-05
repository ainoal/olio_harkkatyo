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
    PhysicalActivity phs = new PhysicalActivity();
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
                    mainView();                                             //TODO testaamisen avuksi tyhjä login, poista!!!
                    //Toast.makeText(MainActivity.this, "Please fill in all the required fields.", Toast.LENGTH_SHORT).show();
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
        final float[] sleep = new float[1];
        final float[] activity = new float[1];
        final float[] weight = new float[1];

        setContentView(R.layout.activity_mainview);

        SeekBar seekbarSleep = findViewById(R.id.seekBarSleep);
        SeekBar seekbarActivity = findViewById(R.id.seekBarActivity);
        SeekBar seekbarWeight = findViewById(R.id.seekBarWeight);
        Button buttonWeight = findViewById(R.id.buttonWeight);
        Button buttonActivity = findViewById(R.id.buttonActivity);
        Button buttonCO2 = findViewById(R.id.buttonCO2);
        Button buttonSave = findViewById(R.id.buttonSave);

        PhysicalActivity pa = new PhysicalActivity();

        seekbarSleep.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                /* Sleep time choices between 0h and 16h */
                sleep[0] = (float) (progress / 6.25);
                System.out.println("SeekbarSleep: " + sleep[0]);
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
                /* Physical activity choices between 0h and 10h */
                activity[0] = (float) (progress / 10.0);
                System.out.println("SeekbarActivity: " + activity[0]);
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
                float currentWeight;

                /* User can choose their weight in range currentWeight +- 20kg.
                This way the seek bar is customized for each individual user, and thus it
                is easier to use. */
                // TODO set current weight according to User weight
                currentWeight = (float) 50.0;
                weight[0] = (float) (progress / 2.5) - 10 + currentWeight;

                System.out.println("SeekbarWeight: " + weight[0]);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("ButtonSave: OnClickListener successful");
                // TODO sleep, activity and weight on User -> write on file
                pa.saveDaily(activity[0]); // test
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
                pa.ActivityToGoal(); // for testing purposes
                activityDrawingTool();
            }
        });

        buttonCO2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                co2Activity(v);
            }
        });
    }

    public void sleepDrawingTool(View v){ //TODO set button to draw
        String saveFile = slt.getSaveFile();
        int ID = slt.getAppID();
        Intent intent = new Intent(MainActivity.this, draw_tool.class);
        intent.putExtra("filename", saveFile);
        intent.putExtra("application", ID);
        startActivity(intent);
    }

    public void activityDrawingTool() { //TODO set button to draw
        String saveFile = phs.getSaveFile();
        int ID = phs.getAppID();
        System.out.println("ID: "+ID+"Save file: "+saveFile);
        Intent intent = new Intent(MainActivity.this, draw_tool.class);
        intent.putExtra("filename", saveFile);
        intent.putExtra("application", ID);
        startActivity(intent);
    }

}
