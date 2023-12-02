package com.example.vidaapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.vidaapp.models.Historial;

import java.util.ArrayList;

public class DBConsHistory extends DBConnect {

    public static final String T_HISTORIAL = "his_consultas";
    public static final String ID = "id";
    public static final String SERVICIO = "servicio";
    public static final String ESTRATO = "estrato";
    public static final String VALOR_UNIT = "valorUnit";
    public static final String LEC_INI = "lec_ini";
    public static final String LEC_FIN = "lec_fin";
    public static final String CONSUMO = "consumo";
    public static final String VALOR_PAGO = "valorPago";


    public static final String CREATE_T_HISTORIAL =   "CREATE TABLE " + T_HISTORIAL +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "servicio TEXT NOT NULL, " +
            "estrato INTEGER NOT NULL, " +
            "valorUnit NUMERIC NOT NULL, " +
            "lec_ini INTEGER NOT NULL, " +
            "lec_fin INTEGER NOT NULL, " +
            "consumo INTEGER NOT NULL, " +
            "valorPago INTEGER NOT NULL)";

    private DBConnect conn ;
    private SQLiteDatabase conDB;

    Context context;

    public DBConsHistory(Context context) {
        super(context);
        this.context = context;
        conn = new DBConnect(context);
    }

    public DBConsHistory open() throws SQLException{
        conDB = conn.getWritableDatabase();
        return this;
    }

    public void close(){conn.close();}

    public void guardarCosulta(String servicio, int estrato, float valor_unit, int lec_ini, int lec_fin, int consumo, int valorPago){
        ContentValues c_historial = new ContentValues();
        c_historial.put(SERVICIO, servicio);
        c_historial.put(ESTRATO, estrato);
        c_historial.put(VALOR_UNIT, valor_unit);
        c_historial.put(LEC_INI, lec_ini);
        c_historial.put(LEC_FIN, lec_fin);
        c_historial.put(CONSUMO, consumo);
        c_historial.put(VALOR_PAGO, valorPago);
        this.conDB.insert(T_HISTORIAL, null, c_historial);
    }

    public ArrayList<Historial> historialConsultas(){

        DBConnect dbHistorial = new DBConnect(context.getApplicationContext());
        SQLiteDatabase db = dbHistorial.getWritableDatabase();

        ArrayList<Historial> listaHistorial = new ArrayList<>();
        Cursor cursorHistorial;

        cursorHistorial = db.rawQuery("SELECT * FROM " + T_HISTORIAL + " ORDER BY id DESC ", null);

        if(cursorHistorial.moveToFirst()){
            do{
                listaHistorial.add(new Historial(
                        cursorHistorial.getString(1),
                        cursorHistorial.getInt(2),
                        cursorHistorial.getFloat(3),
                        cursorHistorial.getInt(4),
                        cursorHistorial.getInt(5),
                        cursorHistorial.getInt(6),
                        cursorHistorial.getInt(7)));
            }while(cursorHistorial.moveToNext());
        }
        cursorHistorial.close();
        System.out.println(listaHistorial);
        return listaHistorial;
    }
}
