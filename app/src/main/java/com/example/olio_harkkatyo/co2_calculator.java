package com.example.olio_harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.SeekBar;
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

public class co2_calculator extends AppCompatActivity {

    SeekBar beef;
    SeekBar pig;
    SeekBar fish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_co2_calculator);

        TextView beef_text = findViewById(R.id.beefView);
        beef = (SeekBar) findViewById(R.id.beefBar);
        TextView pig_text = findViewById(R.id.pigView);
        pig = (SeekBar) findViewById(R.id.pigBar);
        TextView fish_text = findViewById(R.id.fishView);
        fish = (SeekBar) findViewById(R.id.fishBar);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); //jotain nettiyhteyden hyväksymistä
        StrictMode.setThreadPolicy(policy);


        SeekBar.OnSeekBarChangeListener co2_listener = new SeekBar.OnSeekBarChangeListener() {  //kuuntelija liukuvalikolle
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {    //
                switch (seekBar.getId()) {

                    case R.id.beefBar:
                        beef_text.setText(String.valueOf(progress));
                        break;
                    case R.id.pigBar:
                        pig_text.setText(String.valueOf(progress));
                        break;
                    case R.id.fishBar:
                        fish_text.setText(String.valueOf(progress));
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

    }



    public void readJSON(View v) {
        String json = getJSON();
        System.out.println(json);
    }

    public String  getJSON(){
        URL url;
        String response = null;
        int beef_value = beef.getProgress();
        int pig_value = pig.getProgress();
        int fish_value = fish.getProgress();
        System.out.println("Values: " +beef_value +" and " + fish_value +" also "+ pig_value);
        try {
            url = new URL("https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/FoodCalculator" +
                    "?query.diet=omnivore" +
                    "&query.lowCarbonPreference=true" +
                    "&query.beefLevel="+ beef_value +
                    "&query.fishLevel="+ fish_value +
                    "&query.porkPoultryLevel="+ pig_value +
                    "&query.dairyLevel=10" +
                    "&query.cheeseLevel=10" +
                    "&query.riceLevel=10" +
                    "&query.eggLevel=10" +
                    "&query.winterSaladLevel=10" +
                    "&query.restaurantSpending=10" +
                    "&api_key=diary");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader((new InputStreamReader(in)));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line = br.readLine()) != null){
                sb.append(line).append("\n");
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


    }