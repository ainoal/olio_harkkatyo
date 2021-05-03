package com.example.olio_harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.ArrayList;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_co2_calculator);


        diet = findViewById(R.id.spinnerDiet);
        ArrayList<String> dietList = new ArrayList<>();
        String diet_0 = "omnivore";
        String diet_1 = "vegan";
        String diet_2 = "vegetarian";
        dietList.add(diet_0);
        dietList.add(diet_1);
        dietList.add(diet_2);
        ArrayAdapter<String> dietAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, dietList);
        diet.setAdapter(dietAdapter);
        /*diet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {                 //listener for dropdown if we want to do something on selected
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
*/
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



        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); //jotain nettiyhteyden hyväksymistä
        StrictMode.setThreadPolicy(policy);

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(3);

        SeekBar.OnSeekBarChangeListener co2_listener = new SeekBar.OnSeekBarChangeListener() {  //kuuntelija liukuvalikolle
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {    //
                switch (seekBar.getId()) {

                    case R.id.beefBar:
                        beef_text.setText("Beef and lamb: "+String.valueOf(df.format((float)progress/125))+"kg/week"); //average 0.4kg/week
                        break;
                    case R.id.pigBar:
                        pig_text.setText("Chicken and poultry: "+String.valueOf(df.format((float)progress/50))+"kg/week"); //average 1.0kg/week
                        break;
                    case R.id.fishBar:
                        fish_text.setText("Fish and shellfish: "+String.valueOf(df.format((float)progress/83))+"kg/week"); //average 0.6kg/week
                        break;
                    case R.id.cheeseBar:
                        cheese_text.setText("Cheese: "+String.valueOf(df.format((float)progress/166))+"kg/week"); //average 0.3kg/week
                        break;
                    case R.id.milkBar:
                        milk_text.setText("Milk: "+String.valueOf(df.format((float)progress/13))+"kg/week"); //average 3.8kg/week
                        break;
                    case R.id.riceBar:
                        rice_text.setText("Rice: "+String.valueOf(df.format((float)progress/555))+"kg/week"); //average 0.09kg/week
                        break;
                    case R.id.saladBar:
                        salad_text.setText("Winter salad: "+String.valueOf(df.format((float)progress/36))+"kg/week"); //average 1.4kg/week
                        break;
                    case R.id.eggBar:
                        egg_text.setText("Eggs: "+String.valueOf(progress/16)+"kpl/week"); //average 3kpl/week
                        break;
                    case R.id.restaurantBar:
                        restaurant_text.setText("Restaurant spending: "+String.valueOf(progress)+"€/week");
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
        beef.setOnSeekBarChangeListener(co2_listener);          //kuuntelija liitetään liukuvalikoihin
        pig.setOnSeekBarChangeListener(co2_listener);
        fish.setOnSeekBarChangeListener(co2_listener);
        cheese.setOnSeekBarChangeListener(co2_listener);
        milk.setOnSeekBarChangeListener(co2_listener);
        rice.setOnSeekBarChangeListener(co2_listener);
        salad.setOnSeekBarChangeListener(co2_listener);
        egg.setOnSeekBarChangeListener(co2_listener);
        restaurant.setOnSeekBarChangeListener(co2_listener);


    }



    public void readJSON(View v) {
        String json = getJSON();
        System.out.println(json);
        DataManager dm = DataManager.getInstance();
        dm.writeFile("co2_history.json", json);



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

        System.out.println("Values: " +beef_value +" and " + fish_value +" also "+ pig_value+"valittu spinner tavara: "+diet.getSelectedItem());
        try {
            url = new URL("https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/FoodCalculator" +        //bar value from 0 to 100 -> 50*2 = 100%, max 200%
                    "?query.diet="+diet.getSelectedItem()+              //takes selected item from dropdown menú
                    //"&query.lowCarbonPreference=true" +               //low carbon preference diet, do we even care?
                    "&query.beefLevel="+ beef_value*2 +
                    "&query.fishLevel="+ fish_value*2 +
                    "&query.porkPoultryLevel="+ pig_value*2 +
                    "&query.dairyLevel="+ cheese_value*2 +
                    "&query.cheeseLevel="+ milk_value*2 +
                    "&query.riceLevel="+ rice_value*2 +
                    "&query.eggLevel="+ salad_value*2 +
                    "&query.winterSaladLevel="+ egg_value*2 +
                    "&query.restaurantSpending="+restaurant_value +
                    "&api_key=diary");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader((new InputStreamReader(in)));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line = br.readLine()) != null){
                sb.append(line+"\n");
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

        public void loadDrawingTool(View v){
        Intent intent = new Intent(co2_calculator.this, draw_tool.class);
            startActivity(intent);
        }


    }