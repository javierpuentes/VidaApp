package com.example.vidaapp.dbVida;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbConn extends SQLiteOpenHelper {

    private static final int DB_V = 3;
    private static final String DB_N = "db_vida.db";
    public static final String T_USER = "t_user";
    public static final String T_SERVICES = "r_services";

    public DbConn(@Nullable Context context) {
        super(context, DB_N, null, DB_V);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+T_USER+"(" +
                "Id integer primary key autoincrement," +
                "User_N char (20) not null," +
                "User_Pw char (10) not null," +
                "User_Ps char (50) not null," +
                "User_Rs char (50) not null)");

        db.execSQL("create table "+T_SERVICES+"(" +
                "Id integer primary key autoincrement," +
                "User long not null," +
                "Type integer not null," +
                "Month char (20) not null," +
                "Year integer not null," +
                "Consumption real not null," +
                "Price real not null," +
                "Measurer real)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table "+T_USER);
        db.execSQL("drop table "+T_SERVICES);
        onCreate(db);
    }
}
