package com.example.vidaapp;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vidaapp.databinding.ActivityEnergyBinding;
import com.example.vidaapp.databinding.ActivityMainBinding;
import com.example.vidaapp.db.DBConnect;
import com.example.vidaapp.db.DbUsers;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private Button b0, b1, b2;
    private TextView tw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tw = binding.txtVTotal;

        //Se requiere para crear pero después se puede omitir ya que la validación la haría el insert
        DBConnect condb = new DBConnect (MainActivity.this);
        SQLiteDatabase db = condb.getWritableDatabase();
        if(db != null)
        {
            Toast.makeText(MainActivity.this,"¡Base de datos conectada!",
                    Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(MainActivity.this,"¡Error al inicializar la base de datos!",
                    Toast.LENGTH_LONG).show();
        }

        b1 = binding.btnM1;
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbUsers adUser = new DbUsers(MainActivity.this);
                int total = adUser.totalUsers();
                if(total > 0)
                {
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    i.putExtra("total", total);
                    startActivity(i);
                }
                else
                {

                    tw.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(),
                            "¡No hay usuarios registrados, cree su cuenta!",Toast.LENGTH_LONG).show();
                    try {
                        sleep(2000);
                        //startActivity(login);
                        //finish();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        b2 = binding.btnM2;
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });

        b0 = binding.btnM0;
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        
        }
}