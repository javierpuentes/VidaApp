package com.example.vidaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class LoginActivity extends AppCompatActivity {
    private Button b6, b7;
    private TextView t1, t2;

    String v1, v2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        v1 = "Usuario";
        v2 = "123456@";

        b6 = findViewById(R.id.btnM6);
        b7 = findViewById(R.id.btnM7);
        t1 = findViewById(R.id.txtTextuser);
        t2 = findViewById(R.id.txtTextclave);
        t1.setFocusable(true);
        t1.requestFocus();

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), t2.getText(), Toast.LENGTH_LONG).show();
                if(v1.equals(t1.getText().toString()) && v2.equals(t2.getText().toString()))
                {
                    Intent i =new Intent(LoginActivity.this, AccountActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "¡Datos incorrectos, por favor verifica!", Toast.LENGTH_LONG).show();
                    t1.setText("");
                    t2.setText("");
                    t1.requestFocus();

                }
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(LoginActivity.this, RecoveryActivity.class);
                startActivity(i);
            }
        });
    }
}