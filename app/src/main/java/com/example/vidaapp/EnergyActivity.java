package com.example.vidaapp;

import androidx.appcompat.app.AppCompatActivity;
import com.example.vidaapp.databinding.ActivityEnergyBinding;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.vidaapp.db.DBConsHistory;
import com.example.vidaapp.models.Historial;
import java.text.DecimalFormat;
import java.text.ParseException;

public class EnergyActivity extends AppCompatActivity {

    private ActivityEnergyBinding binding;
    ImageButton imgbtnback, imgbtnWater, imgbtnGas;
    Button consultar;
    Spinner spinner;

    TextView valorPago , consumo, historicos;
    EditText valorUnidad, lecActual, lecAnterior;
    private DBConsHistory dbApp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEnergyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        imgbtnback= binding.imgbtnback;
        imgbtnGas= binding.imgbtnGas;
        imgbtnWater= binding.imgbtnWater;
        valorUnidad = binding.valorKV;
        lecActual = binding.lecActual;
        lecAnterior = binding.lecAnterior;
        consumo = binding.consumoKV;
        spinner= binding.spEstratos;
        valorPago = binding.valorPago;
        consultar = binding.btnConsultar;
        historicos = binding.btnHistoricos;

        Intent receive= getIntent();
        long idUser = receive.getLongExtra("idUser",0);
        if(idUser == 0)
        {
            Intent i =new Intent(EnergyActivity.this, LoginActivity.class);
            startActivity(i);
        }


        Integer[] opciones = {1,2,3,4,5,6};

        ArrayAdapter <Integer> spAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, opciones);
        spinner.setAdapter(spAdapter);

        imgbtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent i = new Intent(EnergyActivity.this, AccountActivity.class);
                    i.putExtra("idUser", idUser);
                    startActivity(i);
                }
        });

        imgbtnWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EnergyActivity.this, WaterActivity.class);
                i.putExtra("idUser", idUser);
                startActivity(i);
            }
        });

        imgbtnGas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EnergyActivity.this, NaturalGasActivity.class);
                i.putExtra("idUser", idUser);
                startActivity(i);
            }
        });

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Historial reg = hisConsumo(spinner, valorUnidad, lecActual, lecAnterior, valorPago);
                System.out.println(reg);
                saveHistorial(reg);
                Toast.makeText(getApplicationContext(),
                        "Registro Exitoso",Toast.LENGTH_LONG).show();
            }
        });

        historicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EnergyActivity.this, HistorialesActivity.class);
                startActivity(i);
            }
        });


    }

    public Integer calcularUnidad(int a, int b){

        int totalUnidad = a - b;
        consumo.setText("Su gasto a la fecha es de " + totalUnidad + " kilovatios");
        return totalUnidad;
    }

    public Float subsidio(int est, int totalUnidad, float valor){

        int conSub = 130;
        float E1 = 0.6F;
        float E2 = 0.5F;
        float E3 = 0.15F;
        float desc = 0;

        if (totalUnidad < conSub){
            conSub = totalUnidad;
        }
        switch (est){
            case 1:
                desc = valor * conSub * E1;
                break;
            case 2:
                desc = valor * conSub * E2;
                break;
            case 3:
                desc = valor * conSub * E3;
                break;
            case 4:
            case 5:
            case 6:
                desc = 0;
                break;
        }
        return desc;
    }

    public Historial hisConsumo(Spinner est, EditText valorUnit,  EditText a, EditText b, TextView pago) {
        String servicio = "Energia";
        int imgServicio = R.drawable.bombilla;

        int lecFin = Integer.parseInt(a.getText().toString());
        int lecIni = Integer.parseInt(b.getText().toString());

        int totalKV = calcularUnidad(lecFin, lecIni);

        int estrato = Integer.parseInt(est.getSelectedItem().toString());
        DecimalFormat formatter = new DecimalFormat("#.##");

        try {
            float valorKV = formatter.parse(valorUnit.getText().toString()).floatValue();
            float subsidio = subsidio(estrato, totalKV, valorKV);
            float aPagar;
            int valorPago;

            if (estrato <= 3) {
                aPagar = totalKV * valorKV - subsidio;
            } else  {
                aPagar = totalKV * valorKV;
            }
            valorPago= (int)(Math.round(aPagar));
            pago.setText("$" + valorPago);

            return new Historial(servicio, estrato, valorKV, lecIni, lecFin, totalKV, valorPago, imgServicio );

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveHistorial(Historial consulta){

        try {
            dbApp = new DBConsHistory(getApplicationContext());
            dbApp.open();
            dbApp.guardarCosulta(
                    consulta.getServicio(),
                    consulta.getEstrato(),
                    consulta.getValorUnit(),
                    consulta.getLec_ini(),
                    consulta.getLec_fin(),
                    consulta.getConsumo(),
                    consulta.getValorPago(),
                    consulta.getImage());
            dbApp.close();
        }catch (Exception error){
            error.printStackTrace();
        }

    }

}