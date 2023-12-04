package com.example.vidaapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vidaapp.databinding.ActivityWaterBinding;
import com.example.vidaapp.db.DBConsHistory;
import com.example.vidaapp.models.Historial;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;
import java.text.ParseException;

public class WaterActivity extends AppCompatActivity {

    private ActivityWaterBinding binding;
    ImageButton imgbtnback, imgbtnEnergy, imgbtnGas;
    Button consultar;
    Spinner spinner;
    TextView valorPago , consumoKV, historicos;
    EditText valorKV, lecActual, lecAnterior;
    private DBConsHistory dbApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityWaterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        imgbtnback= binding.imgbtnback;
        imgbtnEnergy= binding.imgbtnEnergy;
        imgbtnGas= binding.imgbtnGas;
        valorKV = binding.valorKV;
        lecActual = binding.lecActual;
        lecAnterior = binding.lecAnterior;
        consumoKV = binding.consumoKV;
        spinner= binding.spEstratos;
        valorPago = binding.valorPago;
        consultar = binding.btnConsultar;
        historicos = binding.btnHistoricos;

        Integer[] opciones = {1,2,3,4,5,6};

        ArrayAdapter<Integer> spAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, opciones);
        spinner.setAdapter(spAdapter);


        imgbtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WaterActivity.this, AccountActivity.class);
                startActivity(i);
            }
        });

        imgbtnEnergy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WaterActivity.this, EnergyActivity.class);
                startActivity(i);
            }
        });

        imgbtnGas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WaterActivity.this, NaturalGasActivity.class);
                startActivity(i);
            }
        });

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Historial reg = hisConsumo(spinner, valorKV, lecActual, lecAnterior, valorPago);
                System.out.println(reg);
                saveHistorial(reg);
                Toast.makeText(getApplicationContext(),
                        "Registro Exitoso",Toast.LENGTH_LONG).show();
            }
        });

        historicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WaterActivity.this, HistorialesActivity.class);
                startActivity(i);
            }
        });

    }

    public Integer calcularKV(int a, int b){

        int consumo = a - b;
        consumoKV.setText("Su gasto a la fecha son " + consumo + " kilovatios");
        return consumo;
    }

    public Float subsidio(int est, int consumo, float valor){

        int conSub = 12;
        float E1 = 0.6F;
        float E2 = 0.4F;
        float E3 = 0.125F;
        float desc = 0;

        if (consumo< conSub){
            conSub = consumo;
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
        String servicio = "Acueducto";
        int imgServicio = R.drawable.water;

        int lecFin = Integer.parseInt(a.getText().toString());
        int lecIni = Integer.parseInt(b.getText().toString());

        int consumo = calcularKV(lecFin, lecIni);

        int estrato = Integer.parseInt(est.getSelectedItem().toString());
        DecimalFormat formatter = new DecimalFormat("#.##");

        try {
            float valorUnidad = formatter.parse(valorUnit.getText().toString()).floatValue();
            float subsidio = subsidio(estrato, consumo, valorUnidad);
            float aPagar;
            int valorPago;

            if (estrato <= 3) {
                aPagar = consumo * valorUnidad - subsidio;
            } else  {
                aPagar = consumo * valorUnidad;
            }
            valorPago= (int)(Math.round(aPagar));
            pago.setText("$" + valorPago);

            return new Historial(servicio, estrato, valorUnidad, lecIni, lecFin, consumo, valorPago, imgServicio );

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