package com.example.vidaapp;

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

import com.example.vidaapp.dbVida.Db_Services;

public class NewPasswActivity extends AppCompatActivity {

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
        Intent receive= getIntent();
        String user = receive.getStringExtra("infoUser");
        if(user.isEmpty())
        {
            Intent i =new Intent(NewPasswActivity.this, LoginActivity.class);
            startActivity(i);
        }
        //"infoUser", pw+","+micuenta+","+mipregunta+","+mirespuesta)
        datos = user.split(",");
        setContentView(R.layout.activity_new_passw);
        clave = findViewById(R.id.txtTextclave2);
        txtc5 =findViewById(R.id.txtNseg);
        txtc4 =findViewById(R.id.txtVuser);
        txtc3 =findViewById(R.id.txtVpregun);
        txtc2 =findViewById(R.id.txtVresp);
        b1 = findViewById(R.id.btnRec111);
        txtc6 =findViewById(R.id.txtCamioHecho);
        imbt1 = findViewById(R.id.imageButton2);

        txtc4.setText(txtc4.getText()+" "+datos[1]);
        txtc3.setText(txtc3.getText()+" "+datos[2]);
        txtc2.setText(txtc2.getText()+" "+datos[3]);
        //idUser = Long.parseLong(datos[1]);
        ma = mi = ch = di = 0;
        clave.requestFocus();

        Db_Services adUpdate = new Db_Services(NewPasswActivity.this);

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
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String pw = clave.getText().toString();
                // Validar una contraseña
                //i => posición ingresada
                char c = pw.charAt(i);
                if(Character.isUpperCase(c))
                    ma++;
                else if(Character.isLowerCase(c))
                    mi++;
                else if(Character.isDigit(c))
                    di++;
                if (c >= 33 && c <= 46 || c == 64)
                {
                    ch++;
                }

                if(pw.length() >= 4 && ma > 0 && mi > 0 && di > 0 && ch > 0)
                {
                    txtc5.setText("La contraseña "+pw+" es segura ");
                    txtc5.setBackgroundColor(Color.GREEN);
                }
                else
                {
                    txtc5.setText("La contraseña "+pw+" no es segura, mejorela ");
                    txtc5.setBackgroundColor(Color.RED);
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
                        }
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "¡Por favor mejore la contraseña!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}