package com.example.olio_harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    View v;
    Context context;
    Button buttonRead;
    Button buttonWrite;
    String fileName;
    String inputText;

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
    }
}