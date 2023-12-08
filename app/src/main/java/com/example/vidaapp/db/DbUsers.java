package com.example.vidaapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.vidaapp.models.Facturas;

import java.util.ArrayList;

public class DbUsers extends DBConnect {

    public static final String T_USERS = "users";
    Context context;

    public static final String CREATE_T_USERS = "create table "+T_USERS+"(" +
            "Id integer primary key autoincrement," +
            "User_N char (20) not null," +
            "User_Pw char (10) not null," +
            "User_Ps char (50) not null," +
            "User_Rs char (50) not null)";

    public DbUsers(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertUser(String user, String pasword, String question, String answer) {
        long id = 0;
        try {
            DBConnect conn = new DBConnect(context);
            SQLiteDatabase db = conn.getWritableDatabase();
            ContentValues val = new ContentValues();

            val.put("User_N", user);
            val.put("User_Pw", pasword);
            val.put("User_Ps", question);
            val.put("User_Rs", answer);
            id = db.insert(T_USERS, null, val);
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
        DBConnect conn = new DBConnect(context);
        SQLiteDatabase db = conn.getWritableDatabase();
        Cursor cursorUser = null;
        cursorUser = db.rawQuery("select Id from "+T_USERS+" where User_N='"+user+"' and User_Pw='"+pasword+"'", null);
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
        DBConnect conn = new DBConnect(context);
        SQLiteDatabase db = conn.getWritableDatabase();
        Cursor cursorUser = null;
        cursorUser = db.rawQuery("select User_Pw from "+T_USERS+" where User_N='"+user+"' and User_Ps='"+pregunta+"' and User_Rs='"+respuesta+"'", null);
        if(cursorUser.moveToFirst())
        {
            pw = cursorUser.getString(0);
        }
        cursorUser.close();
        conn.close();
        return pw;
    }

    public int totalUsers() {
        int count = 0;
        try {
            DBConnect conn = new DBConnect(context);
            SQLiteDatabase db = conn.getReadableDatabase();
            Cursor cursor = db.rawQuery("select Id from " + T_USERS, null);
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
            DBConnect conn = new DBConnect(context);
            SQLiteDatabase db = conn.getReadableDatabase();
            Cursor cursor = db.rawQuery("select Id from " + T_USERS+" where User_N='"+user+"'", null);
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

    public int acutlizapw( String user, String pw){
        int id = 0;
        try {
            DBConnect conn = new DBConnect(context);
            SQLiteDatabase db = conn.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("User_Pw", pw);
            id = db.update(T_USERS, cv, "User_N='"+user+"'", null);
        }
        catch (Exception ex){
            ex.toString();
        }
        return id;
    }

}
