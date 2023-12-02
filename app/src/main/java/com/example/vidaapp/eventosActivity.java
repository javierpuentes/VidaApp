package com.example.vidaapp;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.HOUR;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.work.Data;
import androidx.work.WorkManager;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

public class eventosActivity extends AppCompatActivity {

    Button noticias, sugerencias, btnfecha, btnhora, btnGuardar, btnEliminar;
    TextView edtFecha, edtHora;
    ImageButton regresar;

    Calendar actual = Calendar.getInstance();
    Calendar calendar = Calendar.getInstance();

    private int minutos, hora, dia, mes, anio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);

        noticias = findViewById(R.id.btnNoticias);
        sugerencias = findViewById(R.id.btnSugerencias);
        regresar = findViewById(R.id.btnRegresar);

        btnfecha = findViewById(R.id.btnFecha);
        btnhora = findViewById(R.id.btnHora);
        edtFecha = findViewById(R.id.edtFecha);
        edtHora = findViewById(R.id.edtHora);
        btnGuardar = findViewById(R.id.btnGuardarN);
        btnEliminar = findViewById(R.id.btnEliminarN);

        noticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(eventosActivity.this, noticiasActivity.class);
                startActivity(i);
            }
        });
        sugerencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(eventosActivity.this, sugerenciasActivity.class);
                startActivity(i);
            }
        });
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(eventosActivity.this, AccountActivity.class);
                startActivity(i);
            }
        });

        btnfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anio = actual.get(Calendar.YEAR);
                mes = actual.get(Calendar.MONTH);
                dia = actual.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                        calendar.set(Calendar.DAY_OF_MONTH, d);
                        calendar.set(Calendar.MONTH, m);
                        calendar.set(Calendar.YEAR, y);

                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        String strDate = format.format(calendar.getTime());
                        edtFecha.setText(strDate);
                    }
                },anio,mes,dia);
                datePickerDialog.show();
            }
        });
        btnhora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hora = actual.get(Calendar.HOUR_OF_DAY);
                minutos = actual.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int h, int m) {
                        calendar.set(Calendar.HOUR_OF_DAY, h);
                        calendar.set(Calendar.MINUTE,m);

                        edtHora.setText(String.format("%02d:%02d",h,m));
                    }
                },hora, minutos, true);
                timePickerDialog.show();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tag = generateKey();
                Long alertTime = calendar.getTimeInMillis() - System.currentTimeMillis();
                int random = (int)(Math.random()*50+1);

                Data data = guardarData("Notificacion workmanager", "Tienes un Evento proximo a comenzar VIDAPP", random);
                Workmanagernoti.GuardarNoti(alertTime, data, "tag1");

                Toast.makeText(eventosActivity.this, "Evento Guardado ¡Estar atento a la Notificacion ", Toast.LENGTH_SHORT).show();
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarNoti("tag1");

            }
        });

    }
    private void eliminarNoti (String tag){
        WorkManager.getInstance(this).cancelAllWorkByTag(tag);
        Toast.makeText(eventosActivity.this, "Evento Eliminado", Toast.LENGTH_SHORT).show();
    }

    private String generateKey(){
        return UUID.randomUUID().toString();
    }

    private Data guardarData (String titulo, String detalle, int id_noti){
        return new Data.Builder()
                .putString("titulo", titulo)
                .putString("detalle", detalle)
                .putInt("id_noti",id_noti).build();
    }

}







