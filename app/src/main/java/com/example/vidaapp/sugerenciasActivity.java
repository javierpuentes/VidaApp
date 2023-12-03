package com.example.vidaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class sugerenciasActivity extends AppCompatActivity {

    Button noticias, eventos;
    ImageButton regresar;

    public long idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consejos);

        Intent receive= getIntent();
        idUser = receive.getLongExtra("idUser",0);
        if(idUser == 0)
        {
            Intent i =new Intent(sugerenciasActivity.this, LoginActivity.class);
            startActivity(i);
        }

        noticias = findViewById(R.id.btnNoticias);
        eventos = findViewById(R.id.btnEventos);
        regresar = findViewById(R.id.btnRegresar);

        noticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(sugerenciasActivity.this, noticiasActivity.class);
                i.putExtra("idUser", idUser);
                startActivity(i);
            }
        });

        eventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(sugerenciasActivity.this, eventosActivity.class);
                i.putExtra("idUser", idUser);
                startActivity(i);
            }
        });
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(sugerenciasActivity.this, AccountActivity.class);
                i.putExtra("idUser", idUser);
                startActivity(i);
            }
        });
    }
}