package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.data.DatabaseContract;
import com.example.myapplication.data.DatabaseHelper;

public class Aplicativo extends AppCompatActivity {

    Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cadastrar = findViewById(R.id.btnCadastrar);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = ((EditText) findViewById(R.id.edtUsuario)).getText().toString();
                String email = ((EditText) findViewById(R.id.edtEmail)).getText().toString();
                String senha = ((EditText) findViewById(R.id.edtSenha)).getText().toString();

                if (nome.equals("") || senha.equals("") || email.equals("")) {

                    Toast.makeText(Aplicativo.this, "Preencha todos os campos para prosseguir ", Toast.LENGTH_SHORT).show();
                } else {


                    DatabaseHelper dbHelper = new DatabaseHelper(Aplicativo.this);

                    SQLiteDatabase db = dbHelper.getWritableDatabase();


                    ContentValues values = new ContentValues();
                    values.put(DatabaseContract.COLUMN_NOME, nome);
                    values.put(DatabaseContract.COLUMN_EMAIL, email);
                    values.put(DatabaseContract.COLUMN_SENHA, senha);

                    long newRowId = db.insert(DatabaseContract.TABLE_NAME, null, values);

                    db.close();

                    if (newRowId != -1) {
                        Toast.makeText(Aplicativo.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                        Intent it = new Intent(Aplicativo.this, MainActivity.class);

                        startActivity(it);
                    } else {
                        Toast.makeText(Aplicativo.this, "Falha ao cadastrar usuário.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}