package com.example.vidaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {
    private Button b3, b4;
    private TextView t1, t2;
    private ImageButton ib1;

    String v1, v2, v3, v4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ib1=findViewById(R.id.imageButton1);
        v1 = "Root";
        v2 = "123456";

        b3 = findViewById(R.id.btnM3);
        b4 = findViewById(R.id.btnM4);
        t1 = findViewById(R.id.txtTextuser);
        t2 = findViewById(R.id.txtTextclave);
        t1.setFocusable(true);
        t1.requestFocus();

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v3 = t1.getText().toString().trim();
                v4 = t2.getText().toString().trim();
                if(v1.isEmpty() || v4.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Se requieren los datos de acceso", Toast.LENGTH_LONG).show();
                }
                else {
                    if (v1.equals(v3) && v2.equals(v4)) {
                        Intent i = new Intent(LoginActivity.this, AccountActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "¡Datos correctos, ¡Verifique los datos!", Toast.LENGTH_LONG).show();
                        t1.setText("");
                        t2.setText("");
                        t1.requestFocus();
                    }
                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(LoginActivity.this, RecoveryActivity.class);
                startActivity(i);
            }
        });

        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(LoginActivity.this, MainActivity.class);
                startActivity(back);
            }
        });
    }
}