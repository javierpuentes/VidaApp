package com.example.vidaapp.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.vidaapp.BR;
import com.example.vidaapp.databinding.ItemHistorialBinding;
import com.example.vidaapp.models.Historial;
import java.util.List;

public class HistorialAdapter extends RecyclerView.Adapter<HistorialAdapter.ConsHistViewHolder>   {


    private List<Historial> listaHistorial;
    Context context;

    public HistorialAdapter(List<Historial> listaHistorial) {
        this.listaHistorial = listaHistorial;

        Log.d("inside", "constructor");
    }

    @NonNull
    @Override
    public HistorialAdapter.ConsHistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("insideA", "creanadoVista");

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemHistorialBinding binding = ItemHistorialBinding.inflate(layoutInflater, parent, false);

        Log.d("insideA", "saliendo saliendo");

        return new ConsHistViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HistorialAdapter.ConsHistViewHolder holder, int position) {
        Log.d("insideA", "SetDatos");
        System.out.println(listaHistorial.size());
        Historial historial = listaHistorial.get(position);

        holder.bind(historial);
        holder.consHistBinding.ImageServicio.setImageResource(historial.getImage());

        Log.d("insideA", "logrado");
    }
    @Override
    public int getItemCount() {
        if (listaHistorial != null) {
            return listaHistorial.size();
        } else {
            return 0;
        }
    }


    public class ConsHistViewHolder extends RecyclerView.ViewHolder {
        private ItemHistorialBinding consHistBinding;
        public ConsHistViewHolder(ItemHistorialBinding binding) {
            super(binding.getRoot());
            this.consHistBinding = binding;

        }

        public void bind(Object obj) {
            Log.d("entrando", "seteando");
            consHistBinding.setVariable(BR.historial, obj);
            System.out.println(obj);
            consHistBinding.executePendingBindings();

            Log.d("entrando", "seteado finalizado");
        }
    }
    public void cardClicked(Historial f) {
        Toast.makeText(context.getApplicationContext(), "You clicked " + f.getValorPago(),
                Toast.LENGTH_LONG).show();
    }
}






