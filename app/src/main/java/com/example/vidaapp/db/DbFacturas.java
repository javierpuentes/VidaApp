package com.example.vidaapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.vidaapp.models.Facturas;

import java.util.ArrayList;

public class DbFacturas extends DBConnect {

    public static final String T_FACTURAS = "facturas";
    Context context;
    public static final String CREATE_T_FACTURAS = "create table "+T_FACTURAS+"(" +
            "Id integer primary key autoincrement," +
            "User long not null," +
            "Type integer not null," +
            "Month char (20) not null," +
            "Year integer not null," +
            "Consumption real not null," +
            "Price real not null," +
            "Measurer real)";

    public DbFacturas(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertFactura(long user, int type, String month, int year, float consumption, float price, float measurer) {
        long id = 0;
        try {
            DBConnect conn = new DBConnect(context);
            SQLiteDatabase db = conn.getWritableDatabase();
            ContentValues val = new ContentValues();

            val.put("User", user);
            val.put("Type", type);
            val.put("Month", month);
            val.put("Year", year);
            val.put("Consumption", consumption);
            val.put("Price", price);
            val.put("Measurer", measurer);
            id = db.insert(T_FACTURAS, null, val);
            db.close();
            conn.close();
        }
        catch (Exception ex){
            ex.toString();
        }

        return id;
    }
    public ArrayList<Facturas> mostrarPagos(int year, int type, long user){
        DBConnect conn = new DBConnect(context);
        SQLiteDatabase db = conn.getWritableDatabase();
        ArrayList<Facturas> listaPagos = new ArrayList<>();
        Facturas pagos = null;
        Cursor cursorPagos = null;
        cursorPagos = db.rawQuery("select Id, Month, Year, Consumption, Price, Measurer from "+T_FACTURAS+" where Year='"+year+"' and Type='"+type+"' and User='"+user+"'", null);
        if(cursorPagos.moveToFirst())
        {
            do
            {
                pagos = new Facturas();
                pagos.setId(cursorPagos.getInt(0));
                pagos.setMonth(cursorPagos.getString(1));
                pagos.setYear(cursorPagos.getInt(2));
                pagos.setConsumption(cursorPagos.getFloat(3));
                pagos.setPrice(cursorPagos.getFloat(4));
                pagos.setMeasurer(cursorPagos.getFloat(5));
                listaPagos.add(pagos);
            } while (cursorPagos.moveToNext());
        }
        cursorPagos.close();
        conn.close();
        return listaPagos;
    }
}
