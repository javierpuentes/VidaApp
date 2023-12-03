package com.example.vidaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AccountActivity extends AppCompatActivity {

    ImageButton  home, imgbtnback;
    Button noticias, sugerencias, eventos, mano_select;

    Button btnWater, btnGas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        mano_select = findViewById(R.id.manoselect);
        //Recibir el id del usuario desde el login o el register
        Intent receive= getIntent();
        long idUser = receive.getLongExtra("idUser",0);
        if(idUser == 0)
        {
            Intent i =new Intent(AccountActivity.this, LoginActivity.class);
            startActivity(i);
        }
        else
        {
            mano_select.setText(""+idUser);
        }

        noticias = findViewById(R.id.btnNoticias);
        sugerencias = findViewById(R.id.btnSugerencias);
        eventos = findViewById(R.id.btnEventos);
        home = findViewById(R.id.btnhome);

        btnWater = findViewById(R.id.btnWater);
        btnGas = findViewById(R.id.btnGas);
        imgbtnback= findViewById(R.id.imageButton4);

        imgbtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AccountActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        btnWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(AccountActivity.this, WaterActivity.class);
                i.putExtra("idUser", idUser);
                startActivity(i);
            }
        });
        btnGas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(AccountActivity.this, GasActivity.class);
                i.putExtra("idUser", idUser);
                startActivity(i);
            }
        });
        noticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AccountActivity.this, noticiasActivity.class);
                i.putExtra("idUser", idUser);
                startActivity(i);
            }
        });
        sugerencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AccountActivity.this, sugerenciasActivity.class);
                i.putExtra("idUser", idUser);
                startActivity(i);
            }
        });
        eventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AccountActivity.this, eventosActivity.class);
                i.putExtra("idUser", idUser);
                startActivity(i);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AccountActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}