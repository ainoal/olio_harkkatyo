package com.example.olio_harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class draw_tool extends AppCompatActivity {

    Context context = draw_tool.this;
    private LineGraphSeries<DataPoint> data_series1;
    private LineGraphSeries<DataPoint> data_series2;
    private LineGraphSeries<DataPoint> data_series3;
    private LineGraphSeries<DataPoint> data_series4;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_tool);
        GraphView gv = (GraphView) findViewById(R.id.graph);
        String userName = getIntent().getStringExtra("username");
        int app = getIntent().getIntExtra("application",0); //values: 1=co2, 2=weight, 3=activity, 4=sleep


        DataManager dm = new DataManager();
        dm.init(context);

        user = (User) dm.loadUsers(userName);

        data_series1 = new LineGraphSeries<>();
        data_series2 = new LineGraphSeries<>();
        data_series3 = new LineGraphSeries<>();
        data_series4 = new LineGraphSeries<>();

            try {
                drawGraph(gv, app);
            } catch (JSONException e) {
                e.printStackTrace();
        }
    }

    public void drawGraph(GraphView gv, int app) throws JSONException {
        double x=1;
        double y_1;
        double y_2;
        double y_3;
        double y_4;
        GridLabelRenderer glr = gv.getGridLabelRenderer();
        gv.getViewport().setXAxisBoundsManual(true);
        gv.getViewport().setMinX(1);


        switch(app) {
            case 1:

                this.setTitle("CO2 calculator");
                gv.setTitle("Total (BLK), meat(RED), plant(PNK), Dairy(WHT) emission");
                glr.setVerticalAxisTitle("Emission estimate in kg CO2 eq. / year");
                glr.setHorizontalAxisTitle("Calculated data points");
                ArrayList<String> co2List = user.getCO2List();              //calling arraylists from user data

                //going through lines and converting to JSON for easy manipulation, CO2 in string to json, rest in float
                for (int i = 0; i<co2List.size(); i++){
                    String line = co2List.get(i);
                    JSONObject jsonobject = new JSONObject(line);
                    y_1 = jsonobject.getDouble("Total");
                    data_series1.appendData(new DataPoint(x, y_1), true, 365);
                    y_2 = jsonobject.getDouble("Meat");
                    data_series2.appendData(new DataPoint(x, y_2), true, 365);
                    y_3 = jsonobject.getDouble("Plant");
                    data_series3.appendData(new DataPoint(x, y_3), true, 365);  //could add more
                    y_4 = jsonobject.getDouble("Dairy");
                    data_series4.appendData(new DataPoint(x, y_4), true, 365);

                    x = x + 1;
                }
                //making things prettier
                gv.getViewport().setMaxX(x);
                gv.setBackgroundColor(Color.argb(80, 0, 221, 0));
                data_series1.setColor(Color.BLACK);
                data_series2.setColor(Color.RED);
                data_series3.setColor(Color.argb(255, 225, 110, 195));
                data_series4.setColor(Color.WHITE);
                gv.addSeries(data_series1);
                gv.addSeries(data_series2);
                gv.addSeries(data_series3);
                gv.addSeries(data_series4);

                break;
            case 2:

                this.setTitle("Weight");
                gv.setTitle("Weight change over time");
                glr.setVerticalAxisTitle("Weight in kg");
                glr.setHorizontalAxisTitle("Days");
                ArrayList<Float> weightList = user.getWeightList();
                for (int i = 0; i<weightList.size(); i++){
                    String line = String.valueOf(weightList.get(i));
                    y_1 = Double.parseDouble(line);
                    data_series1.appendData(new DataPoint(x, y_1), true, 365);
                    x = x + 1;
                }
                gv.getViewport().setMaxX(x+1);
                data_series1.setColor(Color.BLACK);
                gv.setBackgroundColor(Color.argb(150, 255, 150, 80));
                gv.addSeries(data_series1);
                break;
            case 3:

                this.setTitle("Activity");
                gv.setTitle("Your daily activity");
                glr.setVerticalAxisTitle("Activity in hours");
                glr.setHorizontalAxisTitle("Days");
                ArrayList<Float> activityList = user.getActivityList();
                for (int i = 0; i<activityList.size(); i++){
                    String line = String.valueOf(activityList.get(i));
                    System.out.println("Arvo, "+i+" :"+line);
                    y_1 = Double.parseDouble(line);
                    data_series1.appendData(new DataPoint(x, y_1), true, 365);
                    x = x + 1;
                }
                gv.getViewport().setMaxX(x+1);
                gv.setBackgroundColor(Color.argb(225, 225, 225, 60));
                gv.addSeries(data_series1);
                break;

            case 4:

                this.setTitle("Sleep");
                gv.setTitle("Your nightly sleep");
                glr.setVerticalAxisTitle("Sleep in hours");
                glr.setHorizontalAxisTitle("Nights");
                ArrayList<Float> sleepList = user.getSleepList();
                for (int i = 0; i<sleepList.size(); i++){
                    String line = String.valueOf(sleepList.get(i));
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