package com.example.vidaapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.vidaapp.databinding.ActivityWaterBinding;

public class WaterActivity extends AppCompatActivity {

    private ActivityWaterBinding binding;

    ImageButton imgbtnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityWaterBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        imgbtnback= findViewById(R.id.imgbtnback);

        imgbtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WaterActivity.this, AccountActivity.class);
                startActivity(i);
            }
        });

    }
}