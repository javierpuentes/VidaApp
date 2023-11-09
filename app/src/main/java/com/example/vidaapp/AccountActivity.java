package com.example.vidaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AccountActivity extends AppCompatActivity {

    ImageButton ib4;

    public Button ib2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ib4 = findViewById(R.id.imageButton4);
        ib2 = findViewById(R.id.btnUsr2);
        ib4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(AccountActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(AccountActivity.this, GasActivity.class);
                startActivity(i);
            }
        });
    }
}