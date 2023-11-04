package com.example.myapplication.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.helper.DBHelper;
import com.example.myapplication.model.User;

import java.util.ArrayList;

public class UserDao {

    private User user;
    private DBHelper db;

    public UserDao(Context ctx, User user) {
        this.db = new DBHelper(ctx);
        this.user = user;
    }

    public boolean vericarEmailESenha(){

        SQLiteDatabase dbLite = this.db.getReadableDatabase();

        String sql = "SELECT * FROM user where email = ? AND senha = ?";
        Cursor cursor = dbLite.rawQuery(sql, new String[]{this.user.getEmail(), this.user.getSenha()});

        if (cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }

    }

    public boolean inserir (){
        SQLiteDatabase dbLite = this.db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome",this.user.getNome());
        cv.put("senha", this.user.getSenha());
        cv.put("email", this.user.getEmail());

        long ret = dbLite.insert("user",
                null,
                cv);

        if (ret > 0){
            return true;
        }
        return false;
    }

    public boolean update(String email){

        SQLiteDatabase dbLite = this.db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome",this.user.getNome());
        cv.put("email", this.user.getEmail());

        long ret = dbLite.update("user", cv,"email = ?", new String[]{email} );
        if (ret > 0){
            return true;
        }
        return false;
    }

    public boolean delete(){

        SQLiteDatabase dbLite = this.db.getWritableDatabase();
        long ret = dbLite.delete("user","email = ?", new String[]{this.user.getEmail()} );

        if (ret > 0){
            return true;
        }
        return false;
    }

    public User obterUserByEmail(){

        SQLiteDatabase dbLite = this.db.getReadableDatabase();
        String sql = "Select * From user where email = ?; ";
        Cursor c = dbLite.rawQuery(sql,new String[]{this.user.getEmail()});
        if(c != null){
            c.moveToFirst();
        }

        this.user.setEmail(c.getString(0));
        this.user.setSenha(c.getString(1));
        this.user.setNome(c.getString(2));
        return this.user;

    }

    private Cursor listarUsers(){

        SQLiteDatabase dbLite = this.db.getReadableDatabase();

        String sql = "SELECT id as _id, nome From user;";
        Cursor c = dbLite.rawQuery(sql,null);

        if(c != null){
            c.moveToFirst();
        }

        return c;
    }
    public ArrayList<User> listarUsersArray(){

        ArrayList<User> list = new ArrayList<>();

        Cursor c = this.listarUsers();

        while (!c.isAfterLast()){
            User u = new User(
                    c.getString(0),
                    c.getString(2),
                    c.getString(1)
            );
            list.add(u);

        }

        return list;
    }



}
