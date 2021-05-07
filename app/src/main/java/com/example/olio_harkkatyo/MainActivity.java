package com.example.olio_harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Context context = MainActivity.this;
    private EditText username;
    private EditText password;
    private Button login;
    private Button signUp;
    boolean confirmed = false;
    PhysicalActivity phs = new PhysicalActivity();
    SleepTracker slt = new SleepTracker(); //luonti testausta varten, siirretään varmaan toiseen aktiviteettiin
    WeightManagement wgt = new WeightManagement();
    User u;

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

        DataManager dm = DataManager.getInstance();
        dm.init(context);

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






        //testi käyttäjän tallennus ja luku
        User juser = new User("uasd","asd",1,1,2,3,123);
        System.out.println("Ideal weight pitäs olla 1: "+juser.getIdealWeight()+" oikee paino 1: "+juser.getWeight());

        dm.saveUser(juser.getUsername(), juser);
        User useri = (User) dm.loadUsers(juser.getUsername());
        System.out.println("TESTI RIVI\n" +
                "################################\n" +
                "painou: "+useri.getIdealWeight()+
                "\n######################################");
        u = useri;


        ////TODO tallennetaanko userit tiedostoon, josta haetaan aina kaikki tallennetut userit, jos tietoja muutetaan niin kirjoitetaan koko tiedosto uusiks?

        //WeightManagement.IdealWeight idealWeight = new WeightManagement.IdealWeight(juser.getWeight(),juser.getIdealWeight());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputUsername = username.getText().toString();
                String inputPassword = password.getText().toString();
                if(inputUsername.isEmpty() || inputPassword.isEmpty()) {
                    //u = (User) dm.loadUsers("uasd");
                    mainView(u);                                             //TODO testaamisen avuksi tyhjä login, poista!!!
                    //Toast.makeText(MainActivity.this, "Please fill in all the required fields.", Toast.LENGTH_SHORT).show();
                } else {
                    confirmed = confirm(inputUsername,inputPassword);
                    if(!confirmed){
                        Toast.makeText(MainActivity.this, "Invalid credentials!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();

                        u = (User) dm.loadUsers(inputUsername);
                        //User u = (User) getIntent().getSerializableExtra("user");
                        mainView(u); // go to the main app view
                    }
                }
            }
        });
    }

    public void co2Activity (View v){
            Intent intent = new Intent(MainActivity.this, co2_calculator.class);
            //System.out.println("Käyttäjänimi on: "+ u.getUsername());

            //intent.putExtra("username", "uasd"); //TODO TESTIÄ VARTEN, POISTA
            intent.putExtra("username", u.getUsername());
            startActivity(intent);
     }

    private boolean confirm(String username, String password){

        if(CreateAccountActivity.account != null){
            return username.equals(CreateAccountActivity.account.getUsername()) && password.equals(CreateAccountActivity.account.getPassword());
        }

        return false;
    }

    public void mainView(User user) {
        final float[] sleep = new float[1];
        final float[] activity = new float[1];
        final float[] weight = new float[1];
        String sleepInfo = "Your average sleep time: ";  // TODO add user sleep time to this string
        String activityInfo = "Your average daily activity: ";
        String weightInfo = "Weight info: ";

        setContentView(R.layout.activity_mainview);

        SeekBar seekbarSleep = findViewById(R.id.seekBarSleep);
        SeekBar seekbarActivity = findViewById(R.id.seekBarActivity);
        SeekBar seekbarWeight = findViewById(R.id.seekBarWeight);
        Button buttonSleep = findViewById(R.id.buttonSleep);
        Button buttonActivity = findViewById(R.id.buttonActivity);
        Button buttonWeight = findViewById(R.id.buttonWeight);
        Button buttonCO2 = findViewById(R.id.buttonCO2);
        Button buttonSave = findViewById(R.id.buttonSave);
        TextView sleepInfoView = findViewById(R.id.sleepInfo);
        TextView activityInfoView = findViewById(R.id.activityInfo);
        TextView weightInfoView = findViewById(R.id.weightInfo);

        sleepInfoView.setText(sleepInfo);
        activityInfoView.setText(activityInfo);
        weightInfoView.setText(weightInfo);

        PhysicalActivity pa = new PhysicalActivity();

        seekbarSleep.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String msg = slt.compareSleepTimes();

                /* Sleep time choices between 0h and 16h */
                sleep[0] = (float) (progress / 6.25);
                /* Round to the nearest half an hour */
                sleep[0] = (float) (Math.round(sleep[0] * 2) / 2.0);

                String si = sleepInfo.concat("\nYour sleep time today: " + sleep[0]);
                si = si.concat("\n" + msg);
                sleepInfoView.setText(si);
                System.out.println("SeekbarSleep: " + sleep[0]);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        seekbarActivity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                /* Physical activity choices between 0h and 10h */
                activity[0] = (float) (progress / 10.0);
                /* Round to the nearest half an hour */
                activity[0] = (float) (Math.round(activity[0] * 2) / 2.0);

                String ai = activityInfo.concat("\nYour activity today: " + activity[0]);
                activityInfoView.setText(ai);
                System.out.println("SeekbarActivity: " + activity[0]);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        seekbarWeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float currentWeight;
                WeightManagement wm= new WeightManagement();

                /* User can choose their weight in range currentWeight +- 20kg.
                This way the seek bar is customized for each individual user, and thus it
                is easier to use. */
                currentWeight = user.getWeight();
                if (currentWeight <= 20) { // To avoid possibility of negative weight
                    weight[0] = (float) (progress / 2.5);
                } else {
                    weight[0] = (float) (progress / 2.5) - 20 + currentWeight;
                    weight[0] = (float) (Math.round(weight[0] * 10) / 10.0);
                }

                String wi = weightInfo.concat("\nYour weight: " + weight[0]);
                wi = wi.concat("\nYour ideal weight: " + user.getIdealWeight());

                /* Set info box message about how far user is from their ideal weight */
                if (wm.comparison(user) < 0) {
                    wi = wi.concat("\nYou are " + Math.abs(wm.comparison(user)) + "kg under your ideal weight.");
                } else if (wm.comparison(user) == 0) {
                    wi = wi.concat("\nYou are in your ideal weight! :)");
                } else {
                    wi = wi.concat("\nYou are " + wm.comparison(user) + "kg over your ideal weight.");
                }
                weightInfoView.setText(wi);

                //System.out.println(change.getChange());
                System.out.println("SeekbarWeight: " + weight[0]);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager dm = DataManager.getInstance();
                System.out.println("ButtonSave: OnClickListener successful\nUsername is: "+u.getUsername());   //TODO poistoon kommentoidut
                /*System.out.println(user.getWeight()+" old weight \n");
                user.setWeight(weight[0]);
                System.out.println(user.getWeight()+ " updated weight \n");
                pa.saveDaily(activity[0]); // test
                slt.setHistory(sleep[0]);*/
                //user = (User) dm.loadUsers(u.getUsername());
                u.setWeightList(weight[0]);
                u.setSleepList(sleep[0]);
                u.setActivityList(activity[0]);
                dm.saveUser(u.getUsername(),u);


           }
        });

        buttonSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("ButtonSleep: OnClickListener successful");
                sleepDrawingTool();
            }
        });

        buttonActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("ButtonActivity: OnClickListener successful");
                pa.ActivityToGoal(); // for testing purposes
                activityDrawingTool(); //draw test
                //sleepDrawingTool();      //draw test
            }
        });

        buttonWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("ButtonWeight: OnClickListener successful");
                weightDrawingTool();
            }
        });

        buttonCO2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                co2Activity(v);
            }
        });
    }

    public void sleepDrawingTool(){ //TODO set to a button to draw, currently starting at line 200 activity click
        if(u.getSleepList().size() > 1) {
            int ID = slt.getAppID();
            Intent intent = new Intent(MainActivity.this, draw_tool.class);
            intent.putExtra("username", u.getUsername());
            intent.putExtra("application", ID);
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "Need at least two entries to draw!", Toast.LENGTH_SHORT).show();
        }
    }

    public void activityDrawingTool() { //TODO set to a button to draw activity
        if(u.getSleepList().size() > 1) {
            int ID = phs.getAppID();
            System.out.println("ID: " + ID + "Save file: " + u.getUsername());
            Intent intent = new Intent(MainActivity.this, draw_tool.class);
            intent.putExtra("username", u.getUsername());
            intent.putExtra("application", ID);
            startActivity(intent);
        }else {
            Toast.makeText(MainActivity.this, "Need at least two entries to draw!", Toast.LENGTH_SHORT).show();
        }
    }

    public void weightDrawingTool() { //TODO set to a button to draw weight
        if(u.getSleepList().size() > 1) {
            int ID = wgt.getAppID();
            System.out.println("ID: " + ID + "Save file: " + u.getUsername());
            Intent intent = new Intent(MainActivity.this, draw_tool.class);
            intent.putExtra("username", u.getUsername());
            intent.putExtra("application", ID);
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "Need at least two entries to draw!", Toast.LENGTH_SHORT).show();
        }
    }

}
