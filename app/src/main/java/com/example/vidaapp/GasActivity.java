package com.example.vidaapp;

import static java.lang.Thread.sleep;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vidaapp.dbVida.Db_QueriesPagos;
import com.example.vidaapp.dbVida.Db_QueriesUsers;
import com.example.vidaapp.dbVida.Db_Services;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;


public class GasActivity extends AppCompatActivity {
    ArrayList<Db_QueriesPagos> listaPagos;
    public Button btn1, btn2;
    public LinearLayout ln1, ln2;
    public Spinner sp1, sp2;

    public int year, selyear, filas, nummes, tipoServ;

    public long idUser;

    public String elmes;

    public TextView tv1, rm1, rm2, rm3, rm4, rmaxc, rmenc;

    public EditText et1, et2, et3;

    public TableLayout registros;

    public ImageButton imgbtnback, im1, im2, im3;

    public float vl1, vl2, vl3, vl4, maxCon, minCon, consumido, pagado, acumulado;
    public DecimalFormat formato;
    public Animation anibtn;

    protected void onStart(Bundle savedInstanceState){
        Toast.makeText(GasActivity.this, "¡Servicios iniciados!", Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gas);
        //listaPagos = new ArrayList<>();

        formato = new DecimalFormat();
        formato.setMaximumFractionDigits(2);
        tipoServ = 1;

        Intent receive= getIntent();
        idUser = receive.getLongExtra("idUser",0);
        if(idUser == 0)
        {
            Intent i =new Intent(GasActivity.this, LoginActivity.class);
            startActivity(i);
        }
        anibtn = AnimationUtils.loadAnimation(this,R.anim.animar);
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

        rmaxc = findViewById(R.id.mayorconsumo);
        rmenc = findViewById(R.id.menorconsumo);

        rmenc.setBackgroundColor(Color.GREEN);
        rmaxc.setBackgroundColor(Color.RED);

        im1 = findViewById(R.id.closeServ);
        im2 = findViewById(R.id.imgbtnagua);
        im3 = findViewById(R.id.imgbtnenergia);

        ln1 = findViewById(R.id.informacionServicio);
        ln2 = findViewById(R.id.registroServcio);
        btn1 = findViewById(R.id.btnRG1);
        btn2 = findViewById(R.id.btnRG2);
        imgbtnback = findViewById(R.id.imgbtnback);
        sp1 = findViewById(R.id.txtAnioRegistro);
        sp2 = findViewById(R.id.mes_spinner);
        tv1 = findViewById(R.id.txtRegAnio);
        registros = findViewById(R.id.registrosGas);

        et1 = findViewById(R.id.txtRegConsumoFac);
        et2 = findViewById(R.id.txtRegPagoFac);
        et3 = findViewById(R.id.txtRegValMed);

        ArrayAdapter<Integer> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, anios);
        sp1.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.meses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(adapter);
        sp2.setPrompt("Seleccione el mes");

        //Instancia de facturas
        Db_Services adFactura = new Db_Services(GasActivity.this);
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
        //Regreso
        imgbtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgbtnback.startAnimation(anibtn);
                Intent i = new Intent(GasActivity.this, AccountActivity.class);
                i.putExtra("idUser", idUser);
                startActivity(i);
            }
        });

        //Historico Agua
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                im2.startAnimation(anibtn);
                Intent i = new Intent(GasActivity.this, AguaActivity.class);
                i.putExtra("idUser", idUser);
                startActivity(i);
            }
        });

        //Historico Energia
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                im3.startAnimation(anibtn);
                Intent i = new Intent(GasActivity.this, EnergiaActivity.class);
                i.putExtra("idUser", idUser);
                startActivity(i);
            }
        });

        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ln2.setVisibility(View.INVISIBLE);
                ln1.setVisibility(View.VISIBLE);
            }
        });

        //Guardar nueva factura y mostrar registro
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(et1.getText().toString().isEmpty() || et2.getText().toString().isEmpty() || nummes == 0)
                {
                    Toast.makeText(GasActivity.this, "¡Serequieren los datos obligatorios de la factura!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(buscarmes(registros, elmes) == true)
                    {
                        ln2.setVisibility(View.INVISIBLE);
                        ln1.setVisibility(View.VISIBLE);

                        //Normalizar a 2 decimales

                        et1.setText(String.format( "%.2f", Float.parseFloat(et1.getText().toString().trim())));
                        et2.setText(String.format( "%.2f", Float.parseFloat(et2.getText().toString().trim())));
                        if(et3.getText().toString().trim().isEmpty())
                        {
                            et3.setText("0.0");
                        }
                        et3.setText(String.format( "%.2f", Float.parseFloat(et3.getText().toString().trim())));

                        consumido = Float.parseFloat(et1.getText().toString()); //Consumo
                        pagado = Float.parseFloat(et2.getText().toString()); //Pago
                        acumulado = Float.parseFloat(et3.getText().toString()); //Acumulado

                        vl1 += consumido; //Consumo
                        vl2 += Float.parseFloat(et2.getText().toString()); //Pago
                        if(filas == 1)
                        {
                            maxCon = minCon = consumido;
                        }
                        else
                        {
                            if (consumido > maxCon)
                            {
                                maxCon = consumido;
                            }
                            if(consumido < minCon)
                            {
                                minCon = consumido;
                            }
                        }
                        llenarTabla(elmes, consumido, Float.parseFloat(et2.getText().toString().trim()), selyear);
                        if(filas > 2)
                        {
                            buscamasymenos(registros);
                        }
                        //Db_Services adFactura = new Db_Services(GasActivity.this);
                        long id = adFactura.insertFactura(idUser, tipoServ, elmes, selyear, consumido, pagado, acumulado);
                        if(id > 0)
                        {
                            Toast.makeText(getApplicationContext(), "¡Histórico creado en la base de datos!",
                                    Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "¡Error al guardar histórico en la la base de datos!",
                                    Toast.LENGTH_LONG).show();
                        }
                        //Toast.makeText(GasActivity.this, "Filas: " + filas, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //Lista de años, consulta a la base de datos y asigna regiatros nuevos para el año seleccionado
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv1.setText("Año: "+parent.getItemAtPosition(position).toString().trim());
                selyear = (int) parent.getItemAtPosition(position);
                vl1 = vl2 = 0;
                rm1.setText("");
                rm2.setText("");
                rm3.setText("");
                rm4.setText("");
                if(filas > 1)
                {
                    //Toast.makeText(GasActivity.this, "Filas: " + filas, Toast.LENGTH_SHORT).show();
                    registros.removeViews(1, (filas - 1));
                    filas = 1;
                }
                //Recuperar registros del año actual o el seleccionado

                listaPagos = adFactura.mostrarPagos(selyear, tipoServ, idUser);
                try {
                    if(listaPagos.isEmpty())
                    {
                        Toast.makeText(GasActivity.this, "¡No posee registros almacenados de este servicio!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        for (Db_QueriesPagos i : listaPagos)
                        {
                            consumido = i.getConsumption();
                            vl1 += consumido; //Consumo
                            vl2 += i.getPrice(); //Pago
                            if (filas == 1) {
                                maxCon = minCon = consumido;
                            }
                            else
                            {
                                if (consumido > maxCon) {
                                    maxCon = consumido;
                                }
                                if (consumido < minCon) {
                                    minCon = consumido;
                                }
                            }
                            /*
                            try {
                                sleep(2000);
                                //startActivity(login);
                                finish();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                             */
                            //Toast.makeText(GasActivity.this, "" + i.getMonth() + ", " + i.getConsumption() + ", " + i.getPrice() + ", " + i.getYear(), Toast.LENGTH_SHORT).show();
                            llenarTabla("" + i.getMonth(), consumido, i.getPrice(), i.getYear());
                            if (filas > 2)
                            {
                                buscamasymenos(registros);
                            }
                        }
                    }
                }
                catch (Exception e)
                {
                    throw new RuntimeException(e);
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

    public void buscamasymenos(TableLayout servicio){
        for (int i = 1; i < servicio.getChildCount(); i++)
        {
            TableRow v = (TableRow) servicio.getChildAt(i);
            servicio.getChildAt(i).setBackgroundColor(Color.WHITE);
            TextView v2 = (TextView) v.getChildAt(1);//Consumo

            if(Float.parseFloat(v2.getText().toString().trim()) == maxCon)
            {
                servicio.getChildAt(i).setBackgroundColor(Color.RED);
            }
            if(Float.parseFloat(v2.getText().toString().trim()) == minCon)
            {
                servicio.getChildAt(i).setBackgroundColor(Color.GREEN);
            }
        }
    }
    public void llenarTabla(String mes, double consumo, double precio, int year){
        TableRow fila = new TableRow(GasActivity.this);
        //MES
        TextView celdam = new TextView(GasActivity.this);
        celdam.setText(mes);
        celdam.setWidth(250);
        celdam.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        fila.addView(celdam);

        //CONSUMO
        TextView celdac = new TextView(GasActivity.this);
        celdac.setText(formato.format(consumo));
        celdac.setWidth(200);
        celdac.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        fila.addView(celdac);

        //PRECIO
        TextView celdap = new TextView(GasActivity.this);
        celdap.setText(formato.format(precio));
        celdap.setWidth(200);
        celdap.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        fila.addView(celdap);

        //AÑO
        TextView celday = new TextView(GasActivity.this);
        celday.setText("" + year);
        celday.setWidth(100);
        celday.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        fila.addView(celday);
        registros.addView(fila);

        promedios();
        filas++;
        //Toast.makeText(GasActivity.this, "¡Hasta aquí llama bien!", Toast.LENGTH_SHORT).show();
    }

    public void promedios() {
        /*
        try {
            sleep(2000);
            //startActivity(login);
            finish();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
         */
        rm1.setText(formato.format(vl1)+" M\u00B3");
        rm2.setText("$ "+vl2);
        rm3.setText(formato.format(vl1/filas)+" M\u00B3");
        rm4.setText(formato.format(vl2/filas));
    }
    public boolean buscarmes (TableLayout servicio, String mes)
    {
        boolean ret = true;
        for (int i = 1; i < servicio.getChildCount(); i++)
        {
            TableRow v = (TableRow) servicio.getChildAt(i);
            servicio.getChildAt(i).setBackgroundColor(Color.WHITE);
            TextView v2 = (TextView) v.getChildAt(0); //Mes

            if(mes.equals(v2.getText().toString()))
            {
                Toast.makeText(this, "Mes duplicado: " + v2.getText().toString(), Toast.LENGTH_SHORT).show();
                ret = false;
            }
            /*
            for (int i3 = 0; i3 < 1; i3 ++)
            {
                TextView v2 = (TextView) v.getChildAt(i3);
                if(mes.equals(v2.getText().toString()))
                {
                    Toast.makeText(this, "Mes duplicado: " + v2.getText().toString(), Toast.LENGTH_SHORT).show();
                    ret = false;
                }
            }
             */
        }
        return ret;
    }
}