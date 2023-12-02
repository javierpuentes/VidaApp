package com.example.vidaapp;

import android.os.Bundle;
import android.widget.ImageButton;

import com.example.vidaapp.adapter.HistorialAdapter;
import com.example.vidaapp.db.DBConsHistory;
import com.example.vidaapp.models.Historial;
import androidx.appcompat.app.AppCompatActivity;
import com.example.vidaapp.databinding.ActivityHisConstEnergyBinding;
import java.util.List;

public class HisConstEnergyActivity extends AppCompatActivity {
    private ActivityHisConstEnergyBinding binding;
    ImageButton imgbtnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHisConstEnergyBinding.inflate(getLayoutInflater());
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