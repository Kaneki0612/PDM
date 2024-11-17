package com.example.passsecuritys;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.passsecuritys.database.Databse;
import com.example.passsecuritys.database.dao.dao;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button ButtonLogin = findViewById(R.id.ButtonLogin);
        EditText email = findViewById(R.id.text_email);
        EditText password = findViewById(R.id.text_pass);

        //Botao para fazer login
        ButtonLogin.setOnClickListener((event) -> {
            if (email.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                startActivity(new Intent(Login.this, Show_users.class));
            }
            new databaseconect(email.getText().toString(),password.getText().toString());
        });
    }
    // Thread para conectar a base de dados
    private class databaseconect extends Thread{
        private String email;
        private String password;

        public databaseconect(String email, String password){
            this.email = email;
            this.password = password;
            start();
        }
        private boolean existe(List<User> e, String email, String password){
            for(User f : e){
                if (f.getEmail().equals(email) && f.getPassword().equals(password)) return true;
            }
            return false;
        }
        @Override
        public void run(){
            Databse db = Room.databaseBuilder(getApplicationContext(), Databse.class, "User").build();
            dao Dao = db.Userdao();
            List<User> usrs = Dao.getAll();
            Log.d("Ana Consult", String.valueOf(usrs.size()));
            for(User u : usrs){
                Log.d("Ana Consult", u.toString());
            }
            if (existe(usrs,email,password)) {
                Intent intent = new Intent(Login.this, Homepage.class);
                intent.putExtra("User", email);
                startActivity(intent);
            }
            else Log.d("Ana Consult", "User NÂO existe");
        }
    }
}