package com.example.olio_harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
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

        ArrayList<Integer> birthday = datePicker();
        bday = findViewById(R.id.btnBday);
        bday.setText(getTodaysDate());

        applyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = userName.getText().toString().trim();
                String weight = userWeight.getText().toString().trim();
                String idealWeight = userIdealWeight.getText().toString().trim();
                int birthMonth = birthday.get(0);
                int birthDay = birthday.get(1);
                int birthYear = birthday.get(2);

                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(weight) || TextUtils.isEmpty(idealWeight)){
                    Toast.makeText(UserProfile.this, "Please fill in all the fields!", Toast.LENGTH_SHORT).show();
                } else {
                    float fWeight = Float.parseFloat(weight);
                    float fIdealWeight = Float.parseFloat(idealWeight);

                    user = new User(name, fWeight, fIdealWeight, birthMonth, birthDay, birthYear);
                    startActivity(new Intent(UserProfile.this, MainActivity.class));
                    Toast.makeText(UserProfile.this, "User information applied successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private String getTodaysDate(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        month = month + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return makeDateString(year, month, day);
    }

    private ArrayList<Integer> datePicker(){
        ArrayList<Integer> birthday = new ArrayList<>();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1; //Jan = 0
                String date = makeDateString(year, month, day);
                bday.setText(date);

                Integer bmonth = month;
                Integer bday = day;
                Integer byear = year;
                birthday.add(bmonth);
                birthday.add(bday);
                birthday.add(byear);
            }
        };

       Calendar calendar = Calendar.getInstance();
       int year = calendar.get(Calendar.YEAR);
       int month = calendar.get(Calendar.MONTH);
       int day = calendar.get(Calendar.DAY_OF_MONTH);
       int style = AlertDialog.THEME_HOLO_LIGHT;
       datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
       datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
       return birthday;
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
