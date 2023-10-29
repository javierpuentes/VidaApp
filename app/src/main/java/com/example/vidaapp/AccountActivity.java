package com.example.vidaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AccountActivity extends AppCompatActivity {

    Button btnEnergy, btnWater, btnGas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        btnEnergy = findViewById(R.id.btnEnergy);
        btnWater = findViewById(R.id.btnWater);
        btnGas = findViewById(R.id.btnGas);

        btnEnergy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(AccountActivity.this, EnergyActivity.class);
                startActivity(i);
            }
        });
        btnWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(AccountActivity.this, WaterActivity.class);
                startActivity(i);
            }
        });
        btnGas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(AccountActivity.this, GasActivity.class);
                startActivity(i);
            }
        });
    }
}