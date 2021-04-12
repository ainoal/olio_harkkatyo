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
    View v;
    Context context;
    Button buttonRead;
    Button buttonWrite;
    String fileName;
    String inputText;
    private EditText Username;
    private EditText Password;
    private Button Login;
    boolean confirmed = false;
    
    public MainActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;

        /*****************************************************************************************/
        // For testing DataManager, will get rid of the buttons + other extra stuff later on
        buttonRead = (Button) findViewById(R.id.buttonRead);
        buttonWrite = (Button) findViewById(R.id.buttonWrite);
        fileName = "testfile.txt";
        inputText = "DataManager\nTiedoston luku ja kirjoitus\n:)";

        DataManager dm = new DataManager();

        buttonWrite.setOnClickListener(v -> dm.writeFile(v, context, fileName, inputText));
        buttonRead.setOnClickListener(v -> dm.readFile(v, context, fileName));
        /*****************************************************************************************/
        Username = findViewById(R.id.etUsername);
        Password = findViewById(R.id.etPassword);
        Login = findViewById(R.id.btnLogin);

        Login.setOnClickListener(new View.OnClickListener() {
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
        });
    }

    }

    public void co2Activity (View v){
        Intent intent = new Intent(MainActivity.this, co2_calculator.class);
        startActivity(intent);
    }
    private boolean confirm(String username, String password){
        if(username.equals(CreateAccountActivity.credentials.getUsername()) && password.equals(CreateAccountActivity.credentials.getPassword())){
            return true;
        }
        return false;
    }
}