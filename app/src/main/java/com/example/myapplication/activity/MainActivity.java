package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.dao.UserDao;
import com.example.myapplication.model.User;

public class MainActivity extends AppCompatActivity {

    EditText edtEmail, edtSenha;

    Button btnLogin;

    UserDao uDao;

    TextView cadastro;

    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        btnLogin = findViewById(R.id.btnLogin);
        cadastro = findViewById(R.id.cadastro);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sp = getSharedPreferences("appLogin", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sp.edit();
                editor.putString("email", edtEmail.getText().toString());
                editor.commit();

                uDao = new UserDao(getApplicationContext(),
                        new User(edtEmail.getText().toString(),
                                edtSenha.getText().toString()));

                if(uDao.vericarEmailESenha()){
                    Intent it = new Intent(MainActivity.this, Aplicativo.class);
                    startActivity(it);
                }else{
                    Toast.makeText(MainActivity.this,
                            "Dados Incorretos", Toast.LENGTH_SHORT).show();
                }

            }
        });

        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, Cadastro.class);

                startActivity(it);
            }
        });

    }
}
