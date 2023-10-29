package com.example.vidaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class RecoveryActivity extends AppCompatActivity {
    private Button b5;
    private ImageButton ib4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery);
        b5=findViewById(R.id.btnM5);
        ib4=findViewById(R.id.imageButton4);

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back=new Intent(RecoveryActivity.this,LoginActivity.class);
                startActivity(back);
            }
        });

        ib4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(RecoveryActivity.this,MainActivity.class);
                startActivity(back);
            }
        });
    }
}