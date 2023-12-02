package com.example.vidaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class EnergyActivity extends AppCompatActivity {

    ImageButton imgbtnback;
    Spinner spinner;
    TextView sp_text;

    public int tipoServ;
    public long idUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_energy);

        tipoServ = 2;

        Intent receive= getIntent();
        idUser = receive.getLongExtra("idUser",0);
        if(idUser == 0)
        {
            Intent i =new Intent(EnergyActivity.this, LoginActivity.class);
            startActivity(i);
        }

        imgbtnback= findViewById(R.id.imgbtnback);

        imgbtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EnergyActivity.this, AccountActivity.class);
                startActivity(i);
            }
        });

        spinner=(Spinner) findViewById(R.id.sp_estratos);
        sp_text=(TextView) findViewById(R.id.sp_selection);

        Integer[] opciones = {1,2,3,4,5,6};

        ArrayAdapter <Integer> spAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, opciones);
        spinner.setAdapter(spAdapter);
    }
}