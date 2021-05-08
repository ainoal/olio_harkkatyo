package com.example.olio_harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

public class co2_calculator extends AppCompatActivity {

    SeekBar beef;
    SeekBar pig;
    SeekBar fish;
    SeekBar cheese;
    SeekBar milk;
    SeekBar rice;
    SeekBar salad;
    SeekBar egg;
    SeekBar restaurant;
    Spinner diet;
    User user;
    int ID = 1;
    String userName;
    TextView co2_display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_co2_calculator);

        this.setTitle("CO2 calculator");
        co2_display = findViewById(R.id.co2_info);

        userName = getIntent().getStringExtra("username");

        diet = findViewById(R.id.spinnerDiet);              //creating drop down menu for diet selection
        ArrayList<String> dietList = new ArrayList<>();
        String diet_0 = "omnivore";
        String diet_1 = "vegan";
        String diet_2 = "vegetarian";
        dietList.add(diet_0);
        dietList.add(diet_1);
        dietList.add(diet_2);

        ArrayAdapter<String> dietAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, dietList);
        diet.setAdapter(dietAdapter);

        TextView beef_text = findViewById(R.id.beefView);
        beef = findViewById(R.id.beefBar);
        TextView pig_text = findViewById(R.id.pigView);
        pig = findViewById(R.id.pigBar);
        TextView fish_text = findViewById(R.id.fishView);
        fish = findViewById(R.id.fishBar);
        TextView cheese_text = findViewById(R.id.cheeseView);
        cheese = findViewById(R.id.cheeseBar);
        TextView milk_text = findViewById(R.id.milkView);
        milk = findViewById(R.id.milkBar);
        TextView rice_text = findViewById(R.id.riceView);
        rice = findViewById(R.id.riceBar);
        TextView salad_text = findViewById(R.id.saladView);
        salad = findViewById(R.id.saladBar);
        TextView egg_text = findViewById(R.id.eggView);
        egg = findViewById(R.id.eggBar);
        TextView restaurant_text = findViewById(R.id.restaurantView);
        restaurant = findViewById(R.id.restaurantBar);





        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); //networking access fix
        StrictMode.setThreadPolicy(policy);

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(3);

        SeekBar.OnSeekBarChangeListener co2_listener = new SeekBar.OnSeekBarChangeListener() {  //Setting listener for sliders
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {    //
                switch (seekBar.getId()) {
                    case R.id.beefBar:
                        beef_text.setText(getString(R.string.beef_and_lamb)+": "+ (df.format((float)progress/125))+getString(R.string.kg_week)); //average 0.4kg/week
                        break;
                    case R.id.pigBar:
                        pig_text.setText(getString(R.string.pig_and_poultry)+": "+(df.format((float)progress/50))+getString(R.string.kg_week)); //average 1.0kg/week
                        break;
                    case R.id.fishBar:
                        fish_text.setText(getString(R.string.fish_and_shellfish)+": "+(df.format((float)progress/83))+getString(R.string.kg_week)); //average 0.6kg/week
                        break;
                    case R.id.cheeseBar:
                        cheese_text.setText(getString(R.string.cheese)+": "+(df.format((float)progress/166))+getString(R.string.kg_week)); //average 0.3kg/week
                        break;
                    case R.id.milkBar:
                        milk_text.setText(getString(R.string.milk)+": "+(df.format((float)progress/13))+getString(R.string.kg_week)); //average 3.8kg/week
                        break;
                    case R.id.riceBar:
                        rice_text.setText(getString(R.string.rice)+": "+(df.format((float)progress/555))+getString(R.string.kg_week)); //average 0.09kg/week
                        break;
                    case R.id.saladBar:
                        salad_text.setText(getString(R.string.winter_salad)+": "+(df.format((float)progress/36))+getString(R.string.kg_week)); //average 1.4kg/week
                        break;
                    case R.id.eggBar:
                        egg_text.setText(getString(R.string.eggs)+": "+(progress/16)+getString(R.string.kpl_week)); //average 3kpl/week
                        break;
                    case R.id.restaurantBar:
                        restaurant_text.setText(getString(R.string.restaurant_spending)+": "+(progress)+getString(R.string.money_week));
                        break;

                    default:
                        throw new IllegalStateException("Unexpected value: " + seekBar.getId());
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
    };
        beef.setOnSeekBarChangeListener(co2_listener);          //applying listeners for sliders
        pig.setOnSeekBarChangeListener(co2_listener);
        fish.setOnSeekBarChangeListener(co2_listener);
        cheese.setOnSeekBarChangeListener(co2_listener);
        milk.setOnSeekBarChangeListener(co2_listener);
        rice.setOnSeekBarChangeListener(co2_listener);
        salad.setOnSeekBarChangeListener(co2_listener);
        egg.setOnSeekBarChangeListener(co2_listener);
        restaurant.setOnSeekBarChangeListener(co2_listener);


    }



    public void readJSON(View v) throws JSONException { //saving data in json format and displaying
        String json = getJSON();
        DataManager dm = DataManager.getInstance();
        user = (User) dm.loadUsers(userName);
        if( json != null) {
            user.setCO2List(json);
            dm.saveUser(userName, user);
            JSONObject jsonobject = new JSONObject(json);
            Double y_1 = jsonobject.getDouble("Total");
            Double y_2 = jsonobject.getDouble("Meat");
            Double y_3 = jsonobject.getDouble("Plant");
            Double y_4 = jsonobject.getDouble("Dairy");
            co2_display.setText(String.format("Yearly CO2 emissions estimate in kg:\n Total: %s Meat: %s Plant: %s Dairy: %s",
                    String.format(Locale.ENGLISH,"%,.1f", y_1), String.format(Locale.ENGLISH,"%,.1f", y_2),
                    String.format(Locale.ENGLISH,"%,.1f", y_3), String.format(Locale.ENGLISH,"%,.1f", y_4)));
        }


    }

    public String  getJSON(){
        URL url;
        String response = null;
        int beef_value = beef.getProgress();
        int pig_value = pig.getProgress();
        int fish_value = fish.getProgress();
        int cheese_value = beef.getProgress();
        int milk_value = pig.getProgress();
        int rice_value = fish.getProgress();
        int salad_value = beef.getProgress();
        int egg_value = pig.getProgress();
        int restaurant_value = restaurant.getProgress();

        try {
            url = new URL("https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/FoodCalculator" +
                    "?query.diet="+diet.getSelectedItem()+              //takes selected item from dropdown menú
                    "&query.beefLevel="+ beef_value*2 +                 //bar values from 0 to 100 -> 50*2 = 100%, max 200%
                    "&query.fishLevel="+ fish_value*2 +
                    "&query.porkPoultryLevel="+ pig_value*2 +
                    "&query.dairyLevel="+ cheese_value*2 +
                    "&query.cheeseLevel="+ milk_value*2 +
                    "&query.riceLevel="+ rice_value*2 +
                    "&query.eggLevel="+ salad_value*2 +
                    "&query.winterSaladLevel="+ egg_value*2 +
                    "&query.restaurantSpending="+restaurant_value +
                    "&api_key=diary");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();  //fetching data from source
            conn.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader((new InputStreamReader(in)));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = br.readLine()) != null){
                sb.append(line);
            }

            response = sb.toString();
            in.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
        }

        public void loadDrawingTool(View v) {
        DataManager dm = DataManager.getInstance();
            user = (User) dm.loadUsers(userName);
            if (user.getCO2List().size() > 1) {
                Intent intent = new Intent(co2_calculator.this, draw_tool.class);
                intent.putExtra("username", userName);
                intent.putExtra("application", ID);      //application ID defined for every drawable for switch-case
                startActivity(intent);
            } else {
                Toast.makeText(co2_calculator.this, "Need at least two entries to draw!", Toast.LENGTH_SHORT).show();
            }
        }


    }