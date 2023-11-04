package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.dao.UserDao;
import com.example.myapplication.model.User;


public class EditarUsuario extends AppCompatActivity {

    Button btnEditar;

    EditText edtUsuario, edtEmail;

    User user = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);

        btnEditar = findViewById(R.id.btnEditar);

        edtUsuario  = findViewById(R.id.edtUsuario);
        edtEmail = findViewById(R.id.edtEmail);

        SharedPreferences sp = getSharedPreferences("appLogin", Context.MODE_PRIVATE);
        String email = sp.getString("email", "null");

        user.setEmail(email);
        UserDao uDao = new UserDao(getApplicationContext(), user);
        user = uDao.obterUserByEmail();

        edtEmail.setHint(user.getEmail());
        edtUsuario.setHint(user.getNome());

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User userEdit = new User();

                String novoNome = edtUsuario.getText().toString();
                String novoEmail = edtEmail.getText().toString();

                String emailoriginal = user.getEmail();

                userEdit.setNome(novoNome);
                userEdit.setEmail(novoEmail);

                UserDao uDao = new UserDao(getApplicationContext(), userEdit);

                if (edtEmail.getText().toString().isEmpty()){

                    edtEmail.setError("Preencha esse campo!");

                } else if(edtUsuario.getText().toString().isEmpty()){
                    edtUsuario.setError("Preencha esse campo!");
                }else{

                    uDao.update(emailoriginal);

                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("email", edtEmail.getText().toString());
                    editor.commit();

                    Toast.makeText(EditarUsuario.this, "Usuario editado com sucesso!", Toast.LENGTH_SHORT).show();

                    Intent it = new Intent(EditarUsuario.this, Aplicativo.class);
                    startActivity(it);

                }


            }
        });
    }
}
