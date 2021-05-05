package com.example.olio_harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class UserProfile extends AppCompatActivity {

    private EditText userName;
    private EditText userWeight;
    private EditText userIdealWeight;
    private Button applyInfo;
    private DatePickerDialog datePickerDialog;
    private Button bday;



    public static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        this.setTitle("Profile information");

        userName = findViewById(R.id.etUserName);
        userWeight = findViewById(R.id.etUserWeight);
        userIdealWeight = findViewById(R.id.etUserIdealWeight);
        applyInfo = findViewById(R.id.btnApplyInfo);
        applyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = userName.getText().toString();
                float weight = Float.valueOf(userWeight.getText().toString());
                float idealWeight = Float.valueOf(userIdealWeight.getText().toString());
                //Tänne tarvitaan vielä birthday
                user = new User(name, weight, idealWeight);
                startActivity( new Intent(UserProfile.this, MainActivity.class));
                Toast.makeText(UserProfile.this,"User information applied successfully!", Toast.LENGTH_SHORT).show();

            }

        });

        datePicker();
        bday = findViewById(R.id.btnBday);
        bday.setText(getTodaysDate());

    }
    private String getTodaysDate(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        month = month + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return makeDateString(year, month, day);
    }
    private void datePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1; //Jan = 0
                String date = makeDateString(year, month, day);
                bday.setText(date);
            }
        };

       Calendar calendar = Calendar.getInstance();
       int year = calendar.get(Calendar.YEAR);
       int month = calendar.get(Calendar.MONTH);
       int day = calendar.get(Calendar.DAY_OF_MONTH);
       int style = AlertDialog.THEME_HOLO_LIGHT;
       datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private String makeDateString(int year, int month, int day){
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month){
        if(month == 1) {
            return "JAN";
        }
        if(month == 2) {
            return "FEB";
        }
        if(month == 3) {
            return "MAR";
        }
        if(month == 4) {
            return "APR";
        }
        if(month == 5) {
            return "MAY";
        }
        if(month == 6) {
            return "JUN";
        }
        if(month == 7) {
            return "JUL";
        }
        if(month == 8) {
            return "AUG";
        }
        if(month == 9) {
            return "SEP";
        }
        if(month == 10) {
            return "OCT";
        }
        if(month == 11) {
            return "NOV";
        }
        if(month == 12) {
            return "DEC";
        }
        //default (should never happen), makes the red go away
        return "JAN";
    }
    public void openDatePicker(View view){
        datePickerDialog.show();
    }
}
