package com.example.vidaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class noticiasActivity extends AppCompatActivity {

    Button noticias, eventos, sugerencias;
    ImageButton regresar;
    public long idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);

        Intent receive= getIntent();
        idUser = receive.getLongExtra("idUser",0);
        if(idUser == 0)
        {
            Intent i =new Intent(noticiasActivity.this, LoginActivity.class);
            startActivity(i);
        }

        noticias = findViewById(R.id.btnNoticias);
        eventos = findViewById(R.id.btnEventos);
        sugerencias = findViewById(R.id.btnSugerencias);
        regresar = findViewById(R.id.btnRegresar);

        eventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(noticiasActivity.this, eventosActivity.class);
                i.putExtra("idUser", idUser);
                startActivity(i);
            }
        });
        sugerencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(noticiasActivity.this, sugerenciasActivity.class);
                i.putExtra("idUser", idUser);
                startActivity(i);
            }
        });

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(noticiasActivity.this, AccountActivity.class);
                i.putExtra("idUser", idUser);
                startActivity(i);
            }
        });


    }
}