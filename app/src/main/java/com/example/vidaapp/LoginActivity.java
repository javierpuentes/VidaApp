package com.example.vidaapp;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vidaapp.dbVida.Db_QueriesUsers;
import com.example.vidaapp.dbVida.Db_Services;


public class LoginActivity extends AppCompatActivity {
    private Button b3, b4;
    private TextView t1, t2, t3;
    private ImageButton ib1;

    String v1, v2;

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
        t3 = findViewById(R.id.txtVTotal);
        t1.setFocusable(true);
        t1.requestFocus();

        Intent receive= getIntent();
        int totalUser = receive.getIntExtra("total",0);

        t3.setText(" Usuarios registrados: "+totalUser+" ");

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1.getText().toString().trim();
                t2.getText().toString().trim();
                if(t1.getText().toString().trim().isEmpty() || t2.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Se requieren los datos de acceso", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Db_Services adUser = new Db_Services(LoginActivity.this);
                    long id = adUser.loginUser(t1.getText().toString().trim(), t2.getText().toString().trim());
                    if(id > 0)
                    {
                        Toast.makeText(getApplicationContext(),
                                "Registro Exitoso, usuario No. "+id,Toast.LENGTH_LONG).show();
                        try {
                            sleep(500);
                            finish();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        Db_QueriesUsers user = new Db_QueriesUsers(0, "","", "","");
                        user.loginUser(id, t1.getText().toString().trim());
                        Intent i = new Intent(LoginActivity.this, AccountActivity.class);
                        i.putExtra("idUser", id);
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