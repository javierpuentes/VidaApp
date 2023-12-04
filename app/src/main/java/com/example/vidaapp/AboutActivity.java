package com.example.vidaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vidaapp.databinding.ActivityRegisterBinding;

public class AboutActivity extends AppCompatActivity {
    private TextView textView1;
    View myView;
    ImageView regresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        textView1 = findViewById(R.id.vidaapp);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        myView = findViewById(R.id.vidaapp);
        myView.startAnimation(animation);


        regresar = findViewById(R.id.Buttonregresar);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AboutActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }}