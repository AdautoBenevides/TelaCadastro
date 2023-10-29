package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.data.DatabaseContract;
import com.example.myapplication.data.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    TextView cadastro;

    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cadastro = findViewById(R.id.cadastro);


        cadastro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent it = new Intent(MainActivity.this, Aplicativo.class);

                startActivity(it);

            }
        });


        login = findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = ((EditText) findViewById(R.id.edtUsuario)).getText().toString();
                String senha = ((EditText) findViewById(R.id.edtSenha)).getText().toString();


                if (verificarCredenciais(usuario, senha)) {
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Credenciais inválidas", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private boolean verificarCredenciais(String usuario, String senha) {

        DatabaseHelper dbHelper = new DatabaseHelper(this);


        SQLiteDatabase db = dbHelper.getReadableDatabase();


        String[] projection = {
                DatabaseContract.COLUMN_NOME,
                DatabaseContract.COLUMN_SENHA
        };

        String selection = DatabaseContract.COLUMN_NOME + " = ? AND " + DatabaseContract.COLUMN_SENHA + " = ?";
        String[] selectionArgs = {usuario, senha};

        Cursor cursor = db.query(
                DatabaseContract.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        boolean credenciaisValidas = cursor.getCount() > 0;

        // Certifique-se de fechar o banco de dados após a operação
        cursor.close();
        db.close();

        return credenciaisValidas;
    }

}
