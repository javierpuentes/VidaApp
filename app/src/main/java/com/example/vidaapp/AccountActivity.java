package com.example.vidaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AccountActivity extends AppCompatActivity {

<<<<<<< HEAD
    ImageButton ib4, home;
    Button noticias, sugerencias, eventos;
=======
    Button btnEnergy, btnWater, btnGas;

>>>>>>> origin/views_Edward

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
<<<<<<< HEAD

        noticias = findViewById(R.id.btnNoticias);
        sugerencias = findViewById(R.id.btnSugerencias);
        eventos = findViewById(R.id.btnEventos);
        home = findViewById(R.id.btnhome);
        ib4 = findViewById(R.id.imageButton4);


        ib4.setOnClickListener(new View.OnClickListener() {
=======

        btnEnergy = findViewById(R.id.btnEnergy);
        btnWater = findViewById(R.id.btnWater);
        btnGas = findViewById(R.id.btnGas);

        btnEnergy.setOnClickListener(new View.OnClickListener() {
>>>>>>> origin/views_Edward
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
        noticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AccountActivity.this, noticiasActivity.class);
                startActivity(i);
            }
        });
        sugerencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AccountActivity.this, sugerenciasActivity.class);
                startActivity(i);
            }
        });
        eventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AccountActivity.this, eventosActivity.class);
                startActivity(i);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AccountActivity.this, MainActivity.class);
            }
        });
    }
}