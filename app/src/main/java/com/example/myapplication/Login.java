package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.myapplication.data.DatabaseContract;
import com.example.myapplication.data.DatabaseHelper;

public class Login extends AppCompatActivity {

    TextView usuario, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplicativo);

        usuario = (TextView) findViewById(R.id.usuario);
        email = (TextView) findViewById(R.id.email);

        String nomeUsuario = "";
        String emailUsuario = "";

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                DatabaseContract.COLUMN_NOME,
                DatabaseContract.COLUMN_EMAIL
        };

        String selection = null;
        String[] selectionArgs = null;
        Cursor cursor = db.query(
                DatabaseContract.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if (cursor.moveToNext()) {
            nomeUsuario = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.COLUMN_NOME));
            emailUsuario = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.COLUMN_EMAIL));
            
        }

        cursor.close();
        db.close();

    }
}