package com.example.soccerscout.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbAux extends SQLiteOpenHelper {

    private static  int DATABASE_VERSION = 1;
    private static  String DATABASE_NOMBRE = "SoccerScout";


    public DbAux(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE usuarios " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre VARCHAR(25) NOT NULL, " +
                "correo  VARCHAR(25) NOT NULL, " +
                "contrasena VARCHAR(25) NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE usuarios");
        onCreate(sqLiteDatabase);
    }
}
