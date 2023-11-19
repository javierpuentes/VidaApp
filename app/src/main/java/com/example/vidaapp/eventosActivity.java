package com.example.vidaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class eventosActivity extends AppCompatActivity {

    Button noticias, sugerencias;
    ImageButton regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);

        noticias = findViewById(R.id.btnNoticias);
        sugerencias = findViewById(R.id.btnSugerencias);
        regresar = findViewById(R.id.btnRegresar);


        noticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(eventosActivity.this, noticiasActivity.class);
                startActivity(i);
            }
        });
        sugerencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(eventosActivity.this, sugerenciasActivity.class);
                startActivity(i);
            }
        });
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(eventosActivity.this, AccountActivity.class);
                startActivity(i);
            }
        });
    }
}