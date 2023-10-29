package com.example.vidaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class RecoveryActivity extends AppCompatActivity {

    public Button bb1;
    public Spinner sp1;
    public TextView txtrep, dtscuenta;
    public int item;
    public String mipregunta, mirespuesta, pregunta, respuesta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery);

        pregunta = "Fecha de nacimiento";
        respuesta = "31-10-1975";
        bb1 = findViewById(R.id.btnRec1);
        sp1 = findViewById(R.id.preg_spinner);
        dtscuenta = findViewById(R.id.txtCuentaUsr);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.preguntas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);

        txtrep = findViewById(R.id.txtVresp);

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
                if(item == 0 || mirespuesta.isEmpty())
                {

                    Toast.makeText(getApplicationContext(), "¡Por favor completa los campos!", Toast.LENGTH_LONG).show();
                }
                else if(mipregunta.equals(pregunta) && mirespuesta.equals(respuesta))
                {
                    Toast.makeText(getApplicationContext(), "¡Hemos encontrado tu cuenta de acceso!", Toast.LENGTH_LONG).show();
                    dtscuenta.setText("Tus datos son: Usuario => 123456@");
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "¡Por favor verifica los datos, no coinciden!", Toast.LENGTH_LONG).show();
                    txtrep.setText("");
                }
            }
        });

    }

}