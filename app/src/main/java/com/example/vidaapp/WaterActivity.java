package com.example.vidaapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class WaterActivity extends AppCompatActivity {

    ImageButton imgbtnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        imgbtnback= findViewById(R.id.imgbtnback);

        imgbtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WaterActivity.this, AccountActivity.class);
                startActivity(i);
            }
        });

    }
}