package com.example.vidaapp.dbVida;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Db_Services extends DbConn {
    Context context;
    public Db_Services(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertUser(String user, String pasword, String question, String answer) {
        long id = 0;
        try {
            DbConn conn = new DbConn(context);
            SQLiteDatabase db = conn.getWritableDatabase();
            ContentValues val = new ContentValues();

            val.put("User_N", user);
            val.put("User_Pw", pasword);
            val.put("User_Ps", question);
            val.put("User_Rs", answer);
            id = db.insert(T_USER, null, val);
            db.close();
            conn.close();

        }
        catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    public long loginUser(String user, String pasword) {
        long id = 0;
        DbConn conn = new DbConn(context);
        SQLiteDatabase db = conn.getWritableDatabase();
        Cursor cursorUser = null;
        cursorUser = db.rawQuery("select Id from "+T_USER+" where User_N='"+user+"' and User_Pw='"+pasword+"'", null);
        if(cursorUser.moveToFirst())
        {
            id = cursorUser.getInt(0);
        }
        cursorUser.close();
        conn.close();
        return id;
    }

    public String RecoveryUser(String pregunta, String respuesta, String user) {
        String pw = "";
        DbConn conn = new DbConn(context);
        SQLiteDatabase db = conn.getWritableDatabase();
        Cursor cursorUser = null;
        cursorUser = db.rawQuery("select User_Pw from "+T_USER+" where User_N='"+user+"' and User_Ps='"+pregunta+"' and User_Rs='"+respuesta+"'", null);
        if(cursorUser.moveToFirst())
        {
            pw = cursorUser.getString(0);
        }
        cursorUser.close();
        conn.close();
        return pw;
    }

    public long insertFactura(long user, int type, String month, int year, float consumption, float price, float measurer) {
        long id = 0;
        try {
            DbConn conn = new DbConn(context);
            SQLiteDatabase db = conn.getWritableDatabase();
            ContentValues val = new ContentValues();

            val.put("User", user);
            val.put("Type", type);
            val.put("Month", month);
            val.put("Year", year);
            val.put("Consumption", consumption);
            val.put("Price", price);
            val.put("Measurer", measurer);
            id = db.insert(T_SERVICES, null, val);
            db.close();
            conn.close();
        }
        catch (Exception ex){
            ex.toString();
        }

        return id;
    }
    public ArrayList <Db_QueriesPagos> mostrarPagos( int year, int type, long user){
        DbConn conn = new DbConn(context);
        SQLiteDatabase db = conn.getWritableDatabase();
        ArrayList<Db_QueriesPagos> listaPagos = new ArrayList<>();
        Db_QueriesPagos pagos = null;
        Cursor cursorPagos = null;
        cursorPagos = db.rawQuery("select Id, Month, Year, Consumption, Price, Measurer from "+T_SERVICES+" where Year='"+year+"' and Type='"+type+"' and User='"+user+"'", null);
        if(cursorPagos.moveToFirst())
        {
            do
            {
                pagos = new Db_QueriesPagos();
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

    public int acutlizapw( long user, String pw){
        int id = 0;
        try {
            DbConn conn = new DbConn(context);
            SQLiteDatabase db = conn.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("User_Pw", pw);
            id = db.update(T_USER, cv, "Id="+user, null);
        }
        catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    public int totalUsers() {
        int count = 0;
        try {
            DbConn conn = new DbConn(context);
            SQLiteDatabase db = conn.getReadableDatabase();
            Cursor cursor = db.rawQuery("select Id from " + T_USER, null);
            if(cursor.moveToFirst())
            {
                count = cursor.getCount();

            }
            cursor.close();
            conn.close();
        }
        catch (Exception ex){
            ex.toString();
        }
        return count;
    }
    public long validateUser(String user) {
        long id = 0;
        try {
            DbConn conn = new DbConn(context);
            SQLiteDatabase db = conn.getReadableDatabase();
            Cursor cursor = db.rawQuery("select Id from " + T_USER+" where User_N='"+user+"'", null);
            if(cursor.moveToFirst())
            {
                id = cursor.getLong(0);
            }
            cursor.close();
            conn.close();
        }
        catch (Exception ex){
            ex.toString();
        }
        return id;
    }
}
