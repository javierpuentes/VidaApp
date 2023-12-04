package com.example.vidaapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBConnect extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NOMBRE = "VidaApp";


    public DBConnect(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBConsHistory.CREATE_T_HISTORIAL);
        db.execSQL(DbUsers.CREATE_T_USERS);
        db.execSQL(DbFacturas.CREATE_T_FACTURAS);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBConsHistory.T_HISTORIAL);
        db.execSQL("DROP TABLE IF EXISTS " + DbUsers.T_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + DbFacturas.T_FACTURAS);

        onCreate(db);
    }
}
