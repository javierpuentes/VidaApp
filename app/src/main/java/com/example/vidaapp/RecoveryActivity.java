package com.example.vidaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class RecoveryActivity extends AppCompatActivity {

    public Button bb1, bb11;
    public ImageButton imbb1;
    public Spinner sp1;
    public TextView txtrep, dtscuenta, cuentausr, usrinfo;
    public int item;
    public String mipregunta, mirespuesta, micuenta, pregunta, respuesta, cuenta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery);

        pregunta = "Fecha de nacimiento";
        respuesta = "31-10-1975";
        cuenta = "Root";
        bb1 = findViewById(R.id.btnRec1);
        bb11 = findViewById(R.id.btnRec11);
        sp1 = findViewById(R.id.preg_spinner);
        dtscuenta = findViewById(R.id.txtCuentaUsr);
        txtrep = findViewById(R.id.txtVresp);
        cuentausr = findViewById(R.id.txtTextuser);
        usrinfo = findViewById(R.id.textView4);
        imbb1 = findViewById(R.id.imageButton1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.preguntas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item = position;
                if(item > 0)
                {
                    mipregunta = parent.getItemAtPosition(position).toString().trim();
                }
                //Toast.makeText(parent.getContext(), "¡Item seleccionado! "+parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                //Toast.makeText(parent.getContext(), "¡Item seleccionado! "+position, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mirespuesta = txtrep.getText().toString().trim();
                micuenta = cuentausr.getText().toString().trim();
                if(item == 0 || mirespuesta.isEmpty() || micuenta.isEmpty())
                {

                    Toast.makeText(getApplicationContext(), "¡Por favor complete todos los campos!", Toast.LENGTH_LONG).show();
                }
                else if(mipregunta.equals(pregunta) && mirespuesta.equals(respuesta) && micuenta.equals(cuenta))
                {
                    Toast.makeText(getApplicationContext(), "¡Hemos encontrado su cuenta de acceso!", Toast.LENGTH_LONG).show();
                    dtscuenta.setText("Buenas noticias,sus datos son: "+micuenta+" => 123456");
                    usrinfo.setVisibility(View.INVISIBLE);
                    bb11.setVisibility(View.VISIBLE);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "¡Por favor verifique los datos, no coinciden!", Toast.LENGTH_LONG).show();
                    txtrep.setText("");
                }
            }
        });

        bb11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(RecoveryActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
        imbb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(RecoveryActivity.this, MainActivity.class);
                startActivity(back);
            }
        });

    }

}