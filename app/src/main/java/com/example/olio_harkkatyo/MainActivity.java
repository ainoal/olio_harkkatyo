package com.example.olio_harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Context context = MainActivity.this;
    private EditText Username;
    private EditText Password;
    private Button Login;
    boolean confirmed = false;
    SleepTracker slt = new SleepTracker(); //luonti testausta varten, siirretään varmaan toiseen aktiviteettiin
    
    public MainActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username = findViewById(R.id.etUsername);
        Password = findViewById(R.id.etPassword);
        Login = findViewById(R.id.btnLogin);



        DataManager dm = DataManager.getInstance();
        dm.init(context);

        /*Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputUsername = Username.getText().toString();
                String inputPassword = Password.getText().toString();
                if(inputUsername.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill in all the required fields.", Toast.LENGTH_SHORT).show();
                } else {
                    confirmed = confirm(inputUsername,inputPassword);
                    if(!confirmed){
                        Toast.makeText(MainActivity.this, "Invalid credentials!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Login succesful!", Toast.LENGTH_SHORT).show();
                        //Code for new activity
                    }
            }
        }
    });*/

    }

    public void co2Activity (View v){
        Intent intent = new Intent(MainActivity.this, co2_calculator.class);
        startActivity(intent);
    }

    public void sleepActivity (View v){
        slt.setHistory(7.5f);
    }

    /*private boolean confirm(String username, String password){
        if(username.equals(CreateAccountActivity.credentials.getUsername()) && password.equals(CreateAccountActivity.credentials.getPassword())){
            return true;
        }
        return false;
    }*/
}