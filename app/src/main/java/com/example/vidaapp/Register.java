package com.example.vidaapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vidaapp.databinding.ActivityRecoveryBinding;
import com.example.vidaapp.databinding.ActivityRegisterBinding;
import com.example.vidaapp.db.DbUsers;
import com.example.vidaapp.models.User;



public class Register extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private ImageButton ib2;
    private Button b5, b25;
    public Spinner sp1;
    public String pregunta, micuenta, miclave, mirespuesta;
    public int item;
    public EditText cuenta, clave, respuesta;
    public TextView titulo, txtv1, txtv2, txtv3, txtv4, txtc1, txtc2, txtc3, txtc4, txtc5, txtc6, txtc7;
    private static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,10}$";
    public CardView informacioncuenta;
    public CheckBox rbt1;
    public int ma, mi, ch, di;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        txtv1 = binding.datoscuentanombre;
        txtv2 = binding.datoscuentaclave;
        txtv3 = binding.datoscuentapregunta;
        txtv4 = binding.datoscuentarespuesta;

        txtc1 = binding.txtVuser2;
        txtc2 = binding.txtVclave2;
        txtc3 = binding.txtPseg;
        txtc4 = binding.respPseg;

        cuenta = binding.txtTextuser2;
        clave = binding.txtTextclave2;
        ib2=binding.imageButton2;
        b5 = binding.btnM5;
        b25 = binding.btnM25;
        sp1 = binding.segSpinner;
        rbt1 = binding.tratamiento;
        respuesta = binding.txtResPegSeg;
        titulo = binding.tituloCuenta;
        informacioncuenta = binding.informacionUsr;
        txtc5 = binding.txtNseg;
        txtc6 = binding.txtCseg;
        txtc7 = binding.idDbUser;
        ma = mi = ch = di = 0;

        cuenta.requestFocus();
        clave.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Validar una contraseña
                //i => posición ingresada
                if (clave.getText().toString().trim().length() > 0)
                {
                    String pw = clave.getText().toString();
                    if (pw.length() == i)
                    {
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

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.preguntas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item = position;
                pregunta = parent.getItemAtPosition(position).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                micuenta = cuenta.getText().toString().trim();
                miclave = clave.getText().toString().trim();
                mirespuesta = respuesta.getText().toString().trim();
                if(item == 0 || micuenta.isEmpty() || miclave.isEmpty() || mirespuesta.isEmpty() || rbt1.isChecked()== false)
                {
                    Toast.makeText(getApplicationContext(), "¡Todos los campos son requeridos!",
                    Toast.LENGTH_LONG).show();
                    if(micuenta.isEmpty())
                        cuenta.setError("Se requiere el nombre de usuario");
                    if(miclave.isEmpty())
                        clave.setError("Se requiere la contraseña");
                    if(item == 0)
                    {
                        TextView errorTextview = (TextView) sp1.getSelectedView();
                        errorTextview.setError("Seleccione la pregunta de seguridad");
                    }
                    if(mirespuesta.isEmpty())
                        respuesta.setError("Se requiere la respuesta de seguridad");
                    if(rbt1.isChecked()== false)
                        rbt1.setError("Debe aceptar el tratamiento de datos");
                }
                else
                {
                    DbUsers adUser = new DbUsers(Register.this);
                    long id = adUser.validateUser(micuenta);

                    if(id > 0)
                    {
                        Toast.makeText(getApplicationContext(), "¡El nombre de usuario ya se está utilizando \n por favor cambielo!",
                                Toast.LENGTH_LONG).show();
                        cuenta.setBackgroundColor(Color.RED);
                        cuenta.requestFocus();
                        new AlertDialog.Builder(Register.this)
                                .setTitle("Nombre de usuario duplicado")
                                .setMessage("¡El nombre de usuario ya se está utilizando \npor favor cambielo!")
                                .setPositiveButton("Aceptar", null)
                                .show();
                    }
                    else {
                        rbt1.setVisibility(View.INVISIBLE);
                        cuenta.setVisibility(View.INVISIBLE);
                        clave.setVisibility(View.INVISIBLE);
                        respuesta.setVisibility(View.INVISIBLE);
                        txtc1.setVisibility(View.INVISIBLE);
                        txtc2.setVisibility(View.INVISIBLE);
                        txtc3.setVisibility(View.INVISIBLE);
                        txtc4.setVisibility(View.INVISIBLE);
                        sp1.setVisibility(View.INVISIBLE);
                        b5.setVisibility(View.INVISIBLE);
                        txtc5.setVisibility(View.INVISIBLE);
                        txtc6.setVisibility(View.INVISIBLE);
                        titulo.setText("Cuenta creada con éxito. ¡Buenvenido!");
                        txtv1.setVisibility(View.VISIBLE);
                        txtv2.setVisibility(View.VISIBLE);
                        txtv3.setVisibility(View.VISIBLE);
                        txtv4.setVisibility(View.VISIBLE);
                        txtc7.setVisibility(View.VISIBLE);
                        b25.setVisibility(View.VISIBLE);
                        txtv1.setText(txtv1.getText().toString() + micuenta);
                        txtv2.setText(txtv2.getText().toString() + miclave);
                        txtv3.setText(txtv3.getText().toString() + pregunta);
                        txtv4.setText(txtv4.getText().toString() + mirespuesta);

                        id = adUser.insertUser(micuenta, miclave, pregunta, mirespuesta);
                        if (id > 0) {
                            Toast.makeText(getApplicationContext(), "¡Usuario creado en la base de datos!",
                                    Toast.LENGTH_LONG).show();
                            txtc7.setText(txtc7.getText().toString() + id);
                            User user = new User(0, "", "", "", "");
                            user.loginUser(id, micuenta);
                        } else {
                            Toast.makeText(getApplicationContext(), "¡Error al guardar el usuario en la la base de datos!",
                                    Toast.LENGTH_LONG).show();
                            txtc7.setText(txtc7.getText().toString() + "¡Error!");
                        }
                    }
                }

                /*
                Intent intent=new Intent(Register.this,LoginActivity.class);
                startActivity(intent);
                 */
            }
        });

        b25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back= new Intent(Register.this, LoginActivity.class);
                startActivity(back);
            }
        });
        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back= new Intent(Register.this,MainActivity.class);
                startActivity(back);
            }
        });
    }
}