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
        if(username.isEmpty() || password.length() < 8 ){
            Toast.makeText(this,"Please fill in all the fields correctly!", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}