package com.example.myapplication.data;

public class DatabaseContract {
    public static final String DATABASE_NAME = "MyApplication.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "Usuarios";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_SENHA = "senha";

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID    +  " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOME  +  " TEXT, " +
                    COLUMN_EMAIL +  " TEXT," +
                    COLUMN_SENHA +  " TEXT) " ;

    public static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;
}

