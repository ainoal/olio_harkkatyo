package com.example.olio_harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Credentials;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText createName;
    private EditText createPasswrd;
    private Button create;

    public static Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        createName = findViewById(R.id.etCreateName);
        createPasswrd = findViewById(R.id.etCreatePasswrd);
        create = findViewById(R.id.btnCreate);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String createUsername = createName.getText().toString();
                String createPassword = createPasswrd.getText().toString();

                if(confirm(createUsername, createPassword)){
                    account = new Account(createUsername, createPassword);
                    startActivity( new Intent(CreateAccountActivity.this, MainActivity.class));
                    Toast.makeText(CreateAccountActivity.this,"New account created successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean confirm(String username, String password){
        boolean foundDigit = false;
        boolean foundLCLetter = false;
        boolean foundUCLetter = false;


        if(username.isEmpty() || password.isEmpty() ){
            Toast.makeText(this,"Please fill in all the fields!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password.length() < 12 ) {
            Toast.makeText(this, "Password must be at least 12 characters long!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(password.length() >= 12 ) {
            for (int i = 0; i < password.length(); i++){
                char c = password.charAt(i);
                if (Character.isDigit(c)){
                    foundDigit = true;
                }
                else if (Character.isLetter(c)) {
                    if (Character.isLowerCase(c)) {
                        foundLCLetter = true;
                    } else if (Character.isUpperCase(c)) {
                        foundUCLetter = true;
                    }
                }
                if (foundDigit && foundLCLetter && foundUCLetter){
                    break;
                }
            }
        }
        if (foundDigit && foundLCLetter && foundUCLetter){
            return true;
        }
        else {
            Toast.makeText(this,"Please fill in all the fields correctly!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}