package com.example.vidaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AccountActivity extends AppCompatActivity {

    ImageButton ib4, home;
    Button noticias, sugerencias, eventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        noticias = findViewById(R.id.btnNoticias);
        sugerencias = findViewById(R.id.btnSugerencias);
        eventos = findViewById(R.id.btnEventos);
        home = findViewById(R.id.btnhome);
        ib4 = findViewById(R.id.imageButton4);


        ib4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(AccountActivity.this, MainActivity.class);
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