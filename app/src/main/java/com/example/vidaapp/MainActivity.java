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

import com.example.vidaapp.dbVida.DbConn;
import com.example.vidaapp.dbVida.Db_Services;

public class MainActivity extends AppCompatActivity {

    private Button b1, b2;
    private TextView tw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tw = findViewById(R.id.txtVTotal);

        //Se requiere para crear pero después se puede omitir ya que la validación la haría el insert
        DbConn condb = new DbConn (MainActivity.this);
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

        b1 = findViewById(R.id.btnM1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Db_Services adUser = new Db_Services(MainActivity.this);
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

        b2 = findViewById(R.id.btnM2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });

    }
}