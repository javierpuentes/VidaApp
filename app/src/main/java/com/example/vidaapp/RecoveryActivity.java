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

import com.example.vidaapp.databinding.ActivityNewPasswBinding;
import com.example.vidaapp.databinding.ActivityRecoveryBinding;
import com.example.vidaapp.db.DbUsers;


public class RecoveryActivity extends AppCompatActivity {

    private ActivityRecoveryBinding binding;
    public Button bb1, bb11, bb111;
    public ImageButton imbb1;
    public Spinner sp1;
    public TextView txtrep, dtscuenta, cuentausr, usrinfo;
    public int item;
    public String mipregunta, mirespuesta, micuenta, pregunta, respuesta, cuenta;

    public String pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecoveryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pregunta = "Fecha de nacimiento";
        respuesta = "31-10-1975";
        cuenta = "Root";
        bb1 = binding.btnRec1;
        bb11 = binding.btnRec11;
        bb111 = binding.btnRec111;
        sp1 = binding.pregSpinnerRec;
        dtscuenta = binding.txtCuentaUsr;
        txtrep = binding.txtVresp;
        cuentausr = binding.txtTextuser;
        usrinfo = binding.textView4;
        imbb1 = binding.imageButton1;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.preguntas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);
        cuentausr.requestFocus();

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
                    if(micuenta.isEmpty())
                        cuentausr.setError("Se requiere el nombre de usuario");
                    if(mirespuesta.isEmpty())
                        txtrep.setError("Se requiere la respuesta de seguridad");
                    if(item == 0)
                    {
                        // set error message on spinner
                        TextView errorTextview = (TextView) sp1.getSelectedView();
                        errorTextview.setError("Seleccione la pregunta de seguridad");
                    }
                }
                else
                {
                    DbUsers adUser = new DbUsers(RecoveryActivity.this);
                    pw = adUser.RecoveryUser(mipregunta, mirespuesta, micuenta);
                    if (!pw.isEmpty()) {

                        Toast.makeText(getApplicationContext(), "¡Hemos encontrado su cuenta de acceso!", Toast.LENGTH_LONG).show();
                        dtscuenta.setText("Buenas noticias,sus datos son: " + micuenta + " => "+pw);
                        usrinfo.setVisibility(View.INVISIBLE);
                        bb11.setVisibility(View.VISIBLE);
                        bb111.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "¡Por favor verifique los datos, no coinciden!", Toast.LENGTH_LONG).show();
                        txtrep.setText("");
                        cuentausr.requestFocus();
                    }
                }
            }
        });

        //Cambiar contraseña
        bb111.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RecoveryActivity.this, NewPasswActivity.class);
                i.putExtra("infoUser", pw+","+micuenta+","+mipregunta+","+mirespuesta);
                startActivity(i);
            }
        });

        //Ir a registro - loggin
        bb11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RecoveryActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
        //Volver al inicio
        imbb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RecoveryActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

}