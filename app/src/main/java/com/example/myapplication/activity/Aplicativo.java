package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.dao.UserDao;
import com.example.myapplication.model.User;

public class Aplicativo extends AppCompatActivity {

    TextView txtEmail;
    TextView txtNome;
    ImageView deleteUser, editUser, logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplicativo);

        txtEmail = findViewById(R.id.email);
        txtNome = findViewById(R.id.usuario);

        deleteUser = findViewById(R.id.imgvDelete);
        editUser = findViewById(R.id.imgvEdit);
        logout = findViewById(R.id.imgvLogout);

        SharedPreferences sp = getSharedPreferences("appLogin", Context.MODE_PRIVATE);
        String email = sp.getString("email", "abc");

        User user = new User();
        user.setEmail(email);
        UserDao uDao = new UserDao(getApplicationContext(), user);
        user = uDao.obterUserByEmail();

        txtEmail.setText(user.getEmail());
        txtNome.setText(user.getNome());

        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(Aplicativo.this, DeletarUsuario.class);

                startActivity(it);
            }
        });

        editUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Aplicativo.this, EditarUsuario.class);

                startActivity(it);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(Aplicativo.this, "Saindo.. Volte Sempre :)", Toast.LENGTH_SHORT).show();

                Intent it = new Intent(Aplicativo.this, SplashActivity.class);
                startActivity(it);
            }
        });

    }


}