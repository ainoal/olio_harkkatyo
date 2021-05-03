package com.example.olio_harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class draw_tool extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_tool);
    }

    public void loadMainActivity(View v){
        Intent intent = new Intent(draw_tool.this, MainActivity.class);
        startActivity(intent);
    }
}