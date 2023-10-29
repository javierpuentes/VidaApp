package com.example.vidaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


import androidx.appcompat.app.AppCompatActivity;


public class GasActivity extends AppCompatActivity {
    ImageButton imgbtnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas);

        imgbtnback= findViewById(R.id.imgbtnback);

        imgbtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GasActivity.this, AccountActivity.class);
                startActivity(i);
            }
        });


    }

}