package com.example.passsecuritys;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

@Entity(tableName = "User")
public class User {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    public  String email;

    @ColumnInfo(name = "passwords")
    private ArrayList<PassWord> passWords;

    @ColumnInfo(name = "nome")
    private String nome;

    @ColumnInfo(name = "password")
    private String password;

    public User(String nome, String password, String email){
        this.email = email;
        this.password = password;
        this.nome = nome;
        passWords = new ArrayList<>();
    }

    public ArrayList<PassWord> getPassWords() {
        return passWords;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {return email;}

    public String getPassword() {
        return password;
    }

    public void setPassWords(ArrayList<PassWord> passWords) {
        this.passWords = passWords;
    }

    public void Addpassword(PassWord P){
        passWords.add(P);
    }

    public String toString(){
        String str = "";
        for (PassWord p : passWords){
            str += p.toString() + "\n";
        }
        return "Name = " + nome + "\tEmail = " + email + " \tPass = " + password + " \tPasswords = \n" + str;
    }
}
