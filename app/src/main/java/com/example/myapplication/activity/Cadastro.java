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

public class Cadastro extends AppCompatActivity {

    Button btnCadastro ;

    EditText email, senha, nome;

    UserDao uDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        email = findViewById(R.id.edtEmail);
        senha = findViewById(R.id.edtSenha);
        nome = findViewById(R.id.edtUsuario);

        btnCadastro = findViewById(R.id.btnEditar);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_ = email.getText().toString();
                String nome_ = nome.getText().toString();
                String senha_ = senha.getText().toString();

                if(email.getText().toString().isEmpty()) {
                    email.setError("Preencha o campo email");
                }else if(nome.getText().toString().isEmpty()){
                    nome.setError("Preencha o campo nome");
                }else if(senha.getText().toString().isEmpty()){
                    senha.setError("Preencha o campo senha");
                }

                User dados = new User(email_, nome_, senha_);

                uDao = new UserDao(getApplicationContext(), dados);

                if(uDao.inserir()){

                    SharedPreferences sp = getSharedPreferences("appLogin", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("email", email.getText().toString());
                    editor.putString("nome", nome.getText().toString());
                    editor.commit();

                    Intent it = new Intent(Cadastro.this, Aplicativo.class);
                    startActivity(it);
                }else{
                    Toast.makeText(Cadastro.this,"Não foi possível realizar o cadastro", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



//    public void dialPhoneNumber(String phoneNumber) {
//        Intent intent = new Intent(Intent.ACTION_CALL);
//        intent.setData(Uri.parse("tel:" + phoneNumber));
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
//    }

    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        // intent.putExtra(Intent.EXTRA_STREAM, attachment);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}