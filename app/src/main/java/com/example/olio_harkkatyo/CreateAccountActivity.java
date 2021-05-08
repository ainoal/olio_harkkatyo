package com.example.olio_harkkatyo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Credentials;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class CreateAccountActivity extends AppCompatActivity {
    private Context context = CreateAccountActivity.this;
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

        DataManager dm = DataManager.getInstance();
        dm.init(context);

        create.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                String createUsername = createName.getText().toString();
                String createPassword = createPasswrd.getText().toString();

                if(confirm(createUsername, createPassword)){

                    account = new Account(createUsername, createPassword);
                    dm.saveAccount(account);
                    Intent intent = new Intent(CreateAccountActivity.this, UserProfile.class);
                    intent.putExtra("username", createUsername);                                        //send username to UserProfile for savefile name
                    startActivity(intent);
                    Toast.makeText(CreateAccountActivity.this,"New account created successfully!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    private boolean confirm(String username, String password){

        String pattern = "[a-zA-Z0-9]*";
        /* a-z = any character between a and z
        A-Z = any character between A and Z
        0-9 = any digit between 0 and 9
        *  = 0 or more instances */

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
            if (password.matches(pattern)) {
                Toast.makeText(this, "Password must contain at least 1 special character!", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                for (int i = 0; i < password.length(); i++) {
                    char c = password.charAt(i);
                    if (Character.isDigit(c)) {
                        foundDigit = true;
                    } else if (Character.isLetter(c)) {
                        if (Character.isLowerCase(c)) {
                            foundLCLetter = true;
                        } else if (Character.isUpperCase(c)) {
                            foundUCLetter = true;
                        }
                    }
                    if (foundDigit && foundLCLetter && foundUCLetter) {
                        break;
                    }
                }
            }
        }
        if (foundDigit && foundLCLetter && foundUCLetter){
            return true;
        }
        else {
            Toast.makeText(this,"Password must contain at least 1 digit, 1 upper and 1 lower case!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
/*
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public byte[] hashPSW(String password){
        byte[] hashedPSW = null;
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        System.out.println("Tänne mentiin\n");
        try {
            System.out.println("Täällä käytiin\n");
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            hashedPSW = md.digest(password.getBytes(StandardCharsets.UTF_8));
            System.out.println("Tämä saatiin: "+hashedPSW);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hashedPSW;
    }
*/
}