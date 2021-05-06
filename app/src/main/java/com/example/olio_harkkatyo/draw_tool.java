package com.example.olio_harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Scanner;

public class draw_tool extends AppCompatActivity {

    private LineGraphSeries<DataPoint> data_series1;
    private LineGraphSeries<DataPoint> data_series2;
    private LineGraphSeries<DataPoint> data_series3;
    User user = User.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_tool);
        GraphView gv = (GraphView) findViewById(R.id.graph);
        String fileName = getIntent().getStringExtra("filename");
        int app = getIntent().getIntExtra("application",0); //values: 1=co2, 2=weight, 3=...

        System.out.println("numero: "+app +" ja tiedostonimi: "+fileName);

        data_series1 = new LineGraphSeries<>();
        data_series2 = new LineGraphSeries<>();
        data_series3 = new LineGraphSeries<>();

            try {
                drawCO2Graph(gv, fileName, app);
                System.out.println("Piirtäjään: numero: "+app +" ja tiedostonimi: "+fileName+"\n");
            } catch (JSONException e) {
                e.printStackTrace();
        }
    }

    public void loadMainActivity(View v){
        Intent intent = new Intent(draw_tool.this, MainActivity.class);
        startActivity(intent);
    }

    public String readHistory(String fileName){
        DataManager dm = DataManager.getInstance();
        String FileData = dm.readFile(fileName);
        System.out.println("Data luettu: " +fileName+"\n");


        return FileData;
    }

    public void drawCO2Graph(GraphView gv, String fileName, int app) throws JSONException {
        double x=0;
        double y_1;
        double y_2;
        double y_3;
        String data = readHistory(fileName);
        Scanner sc = new Scanner(data);
        GridLabelRenderer glr = gv.getGridLabelRenderer();
        gv.getViewport().setXAxisBoundsManual(true);
        gv.getViewport().setMinX(1);


        switch(app) {
            case 1:
                gv.setTitle("Total (BLK), meat(RED), plant(PNK) emission");
                glr.setVerticalAxisTitle("Emission estimate in kg CO2 eq. / year");
                glr.setHorizontalAxisTitle("Calculated data points");

                while (sc.hasNextLine()) {

                    String line = sc.nextLine();
                    JSONObject jsonobject = new JSONObject(line);
                    y_1 = jsonobject.getDouble("Total");
                    data_series1.appendData(new DataPoint(x, y_1), true, 365);
                    y_2 = jsonobject.getDouble("Meat");
                    data_series2.appendData(new DataPoint(x, y_2), true, 365);
                    y_3 = jsonobject.getDouble("Plant");
                    data_series3.appendData(new DataPoint(x, y_3), true, 365);
                    x = x + 1;
                }
                gv.getViewport().setMaxX(x);
                gv.setBackgroundColor(Color.argb(80, 0, 221, 0));
                data_series1.setColor(Color.BLACK);
                data_series2.setColor(Color.RED);
                data_series3.setColor(Color.argb(255, 225, 110, 195));
                gv.addSeries(data_series1);
                gv.addSeries(data_series2);
                gv.addSeries(data_series3);
                break;
            case 2:
                gv.setTitle("Weight change over time");
                glr.setVerticalAxisTitle("Weight in kg");
                glr.setHorizontalAxisTitle("Days");
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    y_1 = Double.parseDouble(line);
                    data_series1.appendData(new DataPoint(x, y_1), true, 365);
                    x = x + 1;
                }
                gv.getViewport().setMaxX(x+1);
                gv.addSeries(data_series1);
                break;
            case 3:
                gv.setTitle("Your daily activity");
                glr.setVerticalAxisTitle("Activity in hours");
                glr.setHorizontalAxisTitle("Days");
                System.out.println("Mentiin piirtää\n");
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    y_1 = Double.parseDouble(line);
                    data_series1.appendData(new DataPoint(x, y_1), true, 365);
                    x = x + 1;
                }
                gv.getViewport().setMaxX(x+1);
                gv.setBackgroundColor(Color.argb(225, 225, 225, 60));
                gv.addSeries(data_series1);
                break;

            case 4:
                gv.setTitle("Your nightly sleep");
                glr.setVerticalAxisTitle("Sleep in hours");
                glr.setHorizontalAxisTitle("Nights");
                while (sc.hasNextLine()) {

                    String line = sc.nextLine();
                    y_1 = Double.parseDouble(line);
                    data_series1.appendData(new DataPoint(x, y_1), true, 365);
                    x = x + 1;
                }
                gv.getViewport().setMaxX(x+1);
                gv.setBackgroundColor(Color.argb(60, 50, 0, 255));
                gv.addSeries(data_series1);
                break;

            default:
                break;
        }

    }
}