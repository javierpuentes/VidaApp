package com.example.vidaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;

public class GasActivity extends AppCompatActivity {
    public Button btn1, btn2;
    public LinearLayout ln1, ln2;
    public Spinner sp1, sp2;

    public int year, selyear, filas, nummes;

    public String elmes;

    public TextView tv1, rm1, rm2, rm3, rm4;

    public EditText et1, et2;

    public TableLayout registros;

    public ImageButton im1;

    public float vl1, vl2, vl3, vl4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas);

        ArrayList<Integer> anios = new ArrayList<>();
        LocalDate dt= LocalDate.now();
        selyear = year = (int) dt.getYear();
        filas = 1;
        nummes = 0;
        for(int i= year; i>=2000; i--)
        {
            anios.add(i);
        }

        rm1 = findViewById(R.id.totalconsumo);
        rm2 = findViewById(R.id.totalpago);
        rm3 = findViewById(R.id.promedioconsumo);
        rm4 = findViewById(R.id.promediopago);

        im1 = findViewById(R.id.closeServ);

        ln1 = findViewById(R.id.informacionServicio);
        ln2 = findViewById(R.id.registroServcio);
        btn1 = findViewById(R.id.btnRG1);
        btn2 = findViewById(R.id.btnRG2);
        sp1 = findViewById(R.id.txtAnioRegistro);
        sp2 = findViewById(R.id.mes_spinner);
        tv1 = findViewById(R.id.txtRegAnio);
        registros = findViewById(R.id.registrosGas);

        et1 = findViewById(R.id.txtRegConsumoFac);
        et2 = findViewById(R.id.txtRegPagoFac);

        ArrayAdapter<Integer> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, anios);
        sp1.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.meses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(adapter);
        sp2.setPrompt("Seleccione el mes");
        //sp2.setGravity(1);
        //sp2.setDropDownVerticalOffset(-2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ln1.setVisibility(View.INVISIBLE);
                ln2.setVisibility(View.VISIBLE);
                et1.requestFocus();
            }
        });

        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ln2.setVisibility(View.INVISIBLE);
                ln1.setVisibility(View.VISIBLE);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(et1.getText().toString().isEmpty() || et2.getText().toString().isEmpty() || nummes == 0)
                {
                    Toast.makeText(GasActivity.this, "¡Serequieren los datos de la factura!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(buscarmes(registros, elmes) == true)
                    {
                        ln2.setVisibility(View.INVISIBLE);
                        ln1.setVisibility(View.VISIBLE);


                        TableRow fila = new TableRow(GasActivity.this);

                        //MES
                        TextView celdam = new TextView(GasActivity.this);
                        celdam.setText(elmes);
                        celdam.setWidth(250);
                        celdam.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        fila.addView(celdam);

                        //CONSUMO
                        vl1 += Float.parseFloat(et1.getText().toString());
                        TextView celdac = new TextView(GasActivity.this);
                        celdac.setText(et1.getText().toString());
                        celdac.setWidth(200);
                        celdac.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        fila.addView(celdac);

                        //PRECIO
                        vl2 += Float.parseFloat(et2.getText().toString());
                        TextView celdap = new TextView(GasActivity.this);
                        celdap.setText(et2.getText().toString());
                        celdap.setWidth(200);
                        celdap.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        fila.addView(celdap);

                        //AÑO
                        TextView celday = new TextView(GasActivity.this);
                        celday.setText("" + selyear);
                        celday.setWidth(100);
                        celday.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        fila.addView(celday);

                        registros.addView(fila);
                        promedios();
                        filas++;
                        //Toast.makeText(GasActivity.this, "Filas: " + filas, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //años
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv1.setText("Año: "+parent.getItemAtPosition(position).toString().trim());
                selyear = (int) parent.getItemAtPosition(position);
                if(filas > 1)
                {
                    //Toast.makeText(GasActivity.this, "Filas: " + filas, Toast.LENGTH_SHORT).show();
                    registros.removeViews(1, (filas - 1));
                    filas = 1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //meses
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nummes = position;
                elmes = parent.getItemAtPosition(position).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    public void promedios() {
        rm1.setText(""+vl1+" M\u00B3");
        rm2.setText("$ "+vl2);
        rm3.setText(String.format( "%.2f", (vl1/filas))+" M\u00B3");
        rm4.setText(String.format( "$ %.2f", (vl2/filas)));

    }
    public boolean buscarmes (TableLayout servicio, String mes)
    {
        boolean ret = true;
        for (int i = 1; i < servicio.getChildCount(); i++)
        {
            TableRow v = (TableRow) servicio.getChildAt(i);
            for (int i3 = 0; i3 < 1; i3 ++)
            {
                TextView v2 = (TextView) v.getChildAt(i3);
                if(mes.equals(v2.getText().toString()))
                {
                    Toast.makeText(this, "Mes duplicado: " + v2.getText().toString(), Toast.LENGTH_SHORT).show();
                    ret = false;
                }
            }
        }
        return ret;
    }

}