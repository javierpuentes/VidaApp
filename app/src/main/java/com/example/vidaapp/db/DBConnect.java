package com.example.vidaapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBConnect extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "VidaApp";


    public DBConnect(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase vidaAppDB) {
        vidaAppDB.execSQL(DBConsHistory.CREATE_T_HISTORIAL);
    }
    @Override
    public void onUpgrade(SQLiteDatabase vidaAPPdb, int oldVersion, int newVersion) {
        vidaAPPdb.execSQL("DROP TABLE IF EXISTS " + DBConsHistory.T_HISTORIAL);
        onCreate(vidaAPPdb);
    }
}
