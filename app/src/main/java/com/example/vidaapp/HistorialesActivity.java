package com.example.vidaapp;

import android.os.Bundle;
import android.widget.ImageButton;

import com.example.vidaapp.adapter.HistorialAdapter;
import com.example.vidaapp.databinding.ActivityHistorialesBinding;
import com.example.vidaapp.db.DBConsHistory;
import com.example.vidaapp.models.Historial;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class HistorialesActivity extends AppCompatActivity {
    private ActivityHistorialesBinding binding;
    ImageButton imgbtnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistorialesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        HistorialAdapter listHistorial = new HistorialAdapter(consultarHistorial());

        binding.setAdapterHis(listHistorial);


    }

    private List<Historial> consultarHistorial() {


            DBConsHistory datos = new DBConsHistory(getApplicationContext());
            datos.open();
            List<Historial> listHist = datos.historialConsultas();
            datos.close();

            return listHist;
}
}