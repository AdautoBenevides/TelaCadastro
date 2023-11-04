package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.dao.UserDao;
import com.example.myapplication.model.User;

public class DeletarUsuario extends AppCompatActivity {


    Button btnDelete, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletar_usuario);

        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        SharedPreferences sp = getSharedPreferences("appLogin", Context.MODE_PRIVATE);
        String email = sp.getString("email", "abc");

        User user = new User();
        user.setEmail(email);
        UserDao uDao = new UserDao(getApplicationContext(), user);
        user = uDao.obterUserByEmail();

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean excluido = uDao.delete();

                if (excluido) {
                    Toast.makeText(DeletarUsuario.this, "Usuário excluído com sucesso", Toast.LENGTH_SHORT).show();

                    Intent it = new Intent(DeletarUsuario.this, MainActivity.class);
                    startActivity(it);
                }else{
                    Toast.makeText(DeletarUsuario.this, "Falha ao excluir usuário", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(DeletarUsuario.this, Aplicativo.class);
                startActivity(it);
            }
        });



    }
}