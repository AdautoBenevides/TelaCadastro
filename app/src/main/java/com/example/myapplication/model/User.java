package com.example.myapplication.model;

public class User {

    private String Nome;

    private String Email;

    private String Senha;

    public User(String email, String nome, String senha) {
        this.Email = email;
        this.Nome = nome;
        this.Senha = senha;
    }

    public User(String email, String senha) {
        this.Email = email;
        this.Senha = senha;
    }

    public User() {
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        this.Nome = nome;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        this.Senha = senha;
    }
}
