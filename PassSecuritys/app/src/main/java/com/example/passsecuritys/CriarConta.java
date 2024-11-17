package com.example.passsecuritys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Database;
import androidx.room.Room;

import com.example.passsecuritys.database.Databse;
import com.example.passsecuritys.database.dao.dao;

import java.util.List;

public class CriarConta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       Button ButtonCriarConta = findViewById(R.id.ButtonCriarConta);
       EditText email = findViewById(R.id.text_email);
       EditText nome = findViewById(R.id.text_name);
       EditText password = findViewById(R.id.text_pass);
        TextView text_login = findViewById(R.id.text_login);

        //Botao usado para criar a conta de login
       ButtonCriarConta.setOnClickListener((event) -> {
           new databaseconect(email.getText().toString(), nome.getText().toString(), password.getText().toString());
       });
       // Redirecionar para a atividade de login
       text_login.setOnClickListener((event) -> { startActivity(new Intent(CriarConta.this,Login.class)); });

        new RSAEncryptionHelper(getApplicationContext());
    }

    // Thread para conectar a base de dados
    private  class databaseconect extends Thread{
        private String email;
        private String nome;
        private String password;

        public databaseconect(String email, String nome, String password){
            this.email = email;
            this.nome = nome;
            this.password = password;
            start();
        }

        @Override
        public void run(){
            Databse db = Room.databaseBuilder(getApplicationContext(), Databse.class, "User").build();
            dao Dao = db.Userdao();
            User U = new User(nome, password, email);
            List<User> usrs = Dao.getAll();
            try {
                Dao.inserir(U);
                Log.d("Ana Insert", "Utilizador criado com sucesso");
            }catch (Exception e){
                Log.d("Ana Insert", e.toString());
            }
        }
    }
}



