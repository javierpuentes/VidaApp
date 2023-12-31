package com.example.vidaapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vidaapp.databinding.ActivityLoginBinding;
import com.example.vidaapp.databinding.ActivityNewPasswBinding;
import com.example.vidaapp.db.DbFacturas;
import com.example.vidaapp.db.DbUsers;

public class NewPasswActivity extends AppCompatActivity {

    private ActivityNewPasswBinding binding;
    public EditText clave;
    public TextView txtc2, txtc3, txtc4, txtc5, txtc6;

    public int ma, mi, ch, di;

    private Button b1;

    public long idUser;

    public String[] datos;

    public ImageButton imbt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewPasswBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent receive= getIntent();
        String user = receive.getStringExtra("infoUser");
        if(user.isEmpty())
        {
            Intent i =new Intent(NewPasswActivity.this, LoginActivity.class);
            startActivity(i);
        }
        //"infoUser", pw+","+micuenta+","+mipregunta+","+mirespuesta)
        datos = user.split(",");
        clave = binding.txtTextclave2;
        txtc5 = binding.txtNseg;
        txtc4 = binding.txtVuser;
        txtc3 = binding.txtVpregun;
        txtc2 = binding.txtVresp;
        b1 = binding.btnRec111;
        txtc6 = binding.txtCamioHecho;
        imbt1 = binding.imageButton2;

        txtc4.setText(txtc4.getText()+" "+datos[1]);
        txtc3.setText(txtc3.getText()+" "+datos[2]);
        txtc2.setText(txtc2.getText()+" "+datos[3]);
        //idUser = Long.parseLong(datos[1]);
        ma = mi = ch = di = 0;
        clave.requestFocus();

        DbUsers adUpdate = new DbUsers(NewPasswActivity.this);

        imbt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewPasswActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        clave.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Validar una contraseña
                //i => posición ingresada
                if (clave.getText().toString().trim().length() > 0) {
                    String pw = clave.getText().toString();
                    if (pw.length() == i) {
                        i--;
                    }
                    char c = pw.charAt(i);
                    if (Character.isUpperCase(c))
                        ma++;
                    else if (Character.isLowerCase(c))
                        mi++;
                    else if (Character.isDigit(c))
                        di++;
                    if (c >= 33 && c <= 46 || c == 64) {
                        ch++;
                    }

                    if (pw.length() >= 4 && ma > 0 && mi > 0 && di > 0 && ch > 0) {
                        txtc5.setText("La contraseña " + pw + " es segura ");
                        txtc5.setBackgroundColor(Color.GREEN);
                    } else {
                        txtc5.setText("La contraseña " + pw + " no es segura, mejorela ");
                        txtc5.setBackgroundColor(Color.RED);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String miclave = clave.getText().toString().trim();
                if(miclave.length() >= 4)
                {
                    if(miclave.equals(datos[0]))
                    {
                        Toast.makeText(getApplicationContext(), "¡La contraseña ya se está usando!",
                                Toast.LENGTH_LONG).show();
                        clave.setError("La contraseña ya se está usando");
                    }
                    else
                    {
                        int id = adUpdate.acutlizapw(datos[1], miclave);
                        if(id > 0)
                        {
                            Toast.makeText(getApplicationContext(), "¡La contraseña se ha actualizado! " + id,
                                    Toast.LENGTH_LONG).show();
                            txtc6.setVisibility(View.VISIBLE);
                            b1.setVisibility(View.INVISIBLE);
                            new AlertDialog.Builder(NewPasswActivity.this)
                                    .setTitle("¡La contraseña se ha actualizado!")
                                    .setMessage("Valídela desde el botón de regreso")
                                    .setPositiveButton("Aceptar", null)
                                    .show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "¡Por favor mejore la contraseña!",
                            Toast.LENGTH_LONG).show();
                    if(miclave.isEmpty())
                        clave.setError("La contraseña no cumple los requisitos");
                }
            }
        });
    }
}