package com.example.vidaapp;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.vidaapp.databinding.ActivityHisConsGasBinding;

public class HisConsGasActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityHisConsGasBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHisConsGasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




    }


}