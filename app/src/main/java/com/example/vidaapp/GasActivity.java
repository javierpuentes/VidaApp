package com.example.vidaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;


public class GasActivity extends AppCompatActivity {
    ImageButton imgbtnback;
    public Button btn1, btn2;
    public LinearLayout ln1, ln2;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas);

        imgbtnback= findViewById(R.id.imgbtnback);
        ln1 = findViewById(R.id.informacionServicio);
        ln2 = findViewById(R.id.registroServcio);
        btn1 = findViewById(R.id.btnRG1);
        btn2 = findViewById(R.id.btnRG2);

        imgbtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GasActivity.this, AccountActivity.class);
                startActivity(i);
            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ln1.setVisibility(View.INVISIBLE);
                ln2.setVisibility(View.VISIBLE);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ln2.setVisibility(View.INVISIBLE);
                ln1.setVisibility(View.VISIBLE);
            }
            });

        }
    }


